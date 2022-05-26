package com.dhlk.interfaces.service.service.impl;

import com.alibaba.fastjson.JSON;
import com.dhlk.domain.Result;
import com.dhlk.entity.basicmodule.User;
import com.dhlk.interfaces.service.dao.UserDao;
import com.dhlk.interfaces.service.service.AppMenuService;
import com.dhlk.interfaces.service.service.LoginService;
import com.dhlk.interfaces.service.service.RedisService;
import com.dhlk.interfaces.service.util.HeaderUtil;
import com.dhlk.interfaces.service.util.JWTUtil;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserDao userDao;
    @Resource
    private AppMenuService appMenuServiceImpl;
    @Autowired
    private RedisService redisService;
    @Resource
    private HeaderUtil headerUtil;
    @Override
    public Result login(String loginName, String password) {
        String secret = EncryUtils.md5(password,loginName);
        User user = userDao.loginCheck(loginName, secret);
        if(user != null){
            Map<String, Set> permissions = appMenuServiceImpl.getPermissionsByLoginName(user);
            String token = JWTUtil.sign(loginName, secret, user.getTenantId());
            //返回前端需要数据
            Map<String,Object> map = new HashMap<>();
            map.put("permissions",permissions);
            map.put("token",token);
            map.put("loginUser",user);
            return ResultUtils.success(map);
        }
        return ResultUtils.error("用户名、密码错误");
    }

    @Override
    public Result checkToken() {
        String token = headerUtil.cloudToken();
        if(redisService.hasKey(Const.TOKEN_CACHE_ITEM_PREFIX+token)){
            User user = JSON.parseObject(redisService.get(Const.TOKEN_CACHE_ITEM_PREFIX + token).toString(), User.class);
            Map<String, Set> permissions = appMenuServiceImpl.getPermissionsByLoginName(user);
            //返回前端需要数据
            Map<String,Object> map = new HashMap<>();
            map.put("permissions",permissions);
            map.put("expire",redisService.getExpire(Const.TOKEN_CACHE_ITEM_PREFIX+token));
            map.put("token",token);
            map.put("loginUser",user);
            return ResultUtils.success(map);
        }
        return ResultUtils.error("token检验失败");
    }
}
