package com.dhlk.interfaces.service.realm;

import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.dao.UserDao;
import com.dhlk.interfaces.service.util.JWTToken;
import com.dhlk.interfaces.service.util.JWTUtil;
import com.dhlk.utils.CheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }


    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException{
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的
        String token = (String) auth.getCredentials();
        log.info("进入shiro认证");
        String loginName = JWTUtil.getUsername(token);
        if(CheckUtils.isNull(loginName)){
            throw new AuthenticationException("token错误");
        }
        User user = userDao.findByLoginName(loginName);
        if(user == null || !JWTUtil.verify(token,loginName,user.getPassword())){
           throw new AuthenticationException("token错误");
        }
        log.info("认证完毕");
        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}
