package com.dhlk.subcontract.service.impl;

import com.alibaba.fastjson.JSON;
import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Company;
import com.dhlk.entity.sub.Menu;
import com.dhlk.entity.sub.ServerEmail;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.enums.ResultEnum;
import com.dhlk.exceptions.MyException;
import com.dhlk.jwt.JWTUtil;
import com.dhlk.service.RedisService;
import com.dhlk.subcontract.dao.CompanyDao;
import com.dhlk.subcontract.dao.MenuDao;
import com.dhlk.subcontract.dao.ServerEmailDao;
import com.dhlk.subcontract.dao.SubpackageUserDao;
import com.dhlk.subcontract.service.LoginService;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private SubpackageUserDao subpackageUserDao;
    @Resource
    private RedisService redisServiceImpl;
    @Resource
    private MenuDao menuDao;
    @Resource
    private ServerEmailDao serverEmailDao;
    @Resource
    private CompanyDao companyDao;

    @Override
    public Result login(SubpackageUser subpackageUser) {
        if(CheckUtils.isNull(subpackageUser.getLoginName()) || CheckUtils.isNull(subpackageUser.getPassword())){
            return ResultUtils.error("用户名密码不能为空");
        }
        SubpackageUser loginUser = subpackageUserDao.findByLoginName(subpackageUser.getLoginName());
        if(loginUser == null){
            return ResultUtils.error("用户名密码错误");
        }
        //对登录密码进行加密
        String encryptPassword = EncryUtils.md5(subpackageUser.getPassword(), Const.SIGN);
        if(!encryptPassword.equals(loginUser.getPassword())){
            return ResultUtils.error("用户名密码错误");
        }
        if(loginUser.getStatus() != null && loginUser.getStatus() != 0){
            return ResultUtils.error("用户已被禁用");
        }
        if(loginUser.getCompany().getIsBlacklist() != null && loginUser.getCompany().getIsBlacklist() != 0){
            return ResultUtils.error("登录失败");
        }
        switch (loginUser.getCompany().getAuditStatus()){
            case 0:
                return ResultUtils.error("该用户待审核");
            case 2:
                return ResultUtils.error("该用户审核不通过");
        }
        return ResultUtils.success(getTokenMap(loginUser,Const.TOKEN_LOSE_TIME));
    }

    @Override
    public Result acquireAuthCode(String companyEmail) {
        //生成四位验证码
        // String authCode = String.format("%4d", (int)(Math.random()*10000)).replace(" ", "0");
        String authCode ="1234";
/*        //查询服务端邮箱
        ServerEmail serverEmail = serverEmailDao.findServerEmail();
        String content = serverEmail.getContent();
        if(serverEmail.getContent() != null){
            content = serverEmail.getContent().replace(Const.AUTH_CODE_MARK,authCode);
        }

        SendEmailUtil sendEmailUtil = new SendEmailUtil();
        Map<String,String> map = new HashMap<>();
        map.put(companyEmail,"to");
        try {
            sendEmailUtil.send(map,"登录验证码",content,null,serverEmail.getSmtpAddress(),
                               serverEmail.getEmailAddress(),serverEmail.getEmailPassword());
        } catch (MessagingException e) {
            return ResultUtils.error("邮件发送异常");
        } catch (UnsupportedEncodingException e) {
            return ResultUtils.error("文件编码异常");
        }*/
        //设置验证的有效时间存入redis中
        boolean set = redisServiceImpl.set(Const.AUTH_CODE_PRE + companyEmail, authCode, Const.AUTH_CODE_TIME);
        return set?ResultUtils.success("发送成功"):ResultUtils.failure();
    }

    @Override
    @Transactional
    public Result register(SubpackageUser subpackageUser) {
        //判重
        if(subpackageUserDao.isRepeatLoginName(subpackageUser) > 0){
            return ResultUtils.error("登录账号已存在");
        }
        if(subpackageUserDao.isRepeatCompanyEmail(subpackageUser) > 0){
            return ResultUtils.error("企业邮箱已存在");
        }
        Company company = subpackageUser.getCompany();
        int insert = companyDao.insert(company);
        if(insert > 0){
            subpackageUser.setCompanyId(company.getId());
            subpackageUser.setPassword(EncryUtils.md5(subpackageUser.getPassword(), Const.SIGN));
            insert = subpackageUserDao.insert(subpackageUser);
            if(insert > 0){
                return ResultUtils.success();
            }else{
                throw new MyException("1000","用户注册失败");
            }
        }
        return ResultUtils.failure();
    }

    @Override
    public Result verifyAuthCode(String companyEmail,String authCode) {
        if(CheckUtils.isNull(authCode) || CheckUtils.isNull(companyEmail)){
            return ResultUtils.error(ResultEnum.PARAM_ERR.getStateInfo());
        }
        if(!redisServiceImpl.hasKey(Const.AUTH_CODE_PRE + companyEmail)){
            return ResultUtils.failure();
        }
        if(authCode.equals(redisServiceImpl.get(Const.AUTH_CODE_PRE + companyEmail).toString())){
            redisServiceImpl.del(Const.AUTH_CODE_PRE + companyEmail);
            SubpackageUser loginUser = subpackageUserDao.findByEmail(companyEmail);
            if(loginUser == null){
                return ResultUtils.success();
            }else{
                return ResultUtils.success(getTokenMap(loginUser, Const.TOKEN_LOSE_TIME));
            }
        }
        return ResultUtils.failure();
    }

    @Override
    public Result logout() {
        try{
            HttpServletRequest request = HttpContextUtil.getRequest();
            String token = request.getHeader(Const.TOKEN_HEADER);
            if(redisServiceImpl.hasKey(Const.TOKEN_CACHE_ITEM_PREFIX+token)){
                //删除redis里缓存的token
                redisServiceImpl.del(Const.TOKEN_CACHE_ITEM_PREFIX+token);
            }
            return ResultUtils.success();
        }catch (MyException e){
            return ResultUtils.failure();
        }
    }

    private Map<String, Set> getPermissions(SubpackageUser subpackageUser){
        Map<String, Set> map = new HashMap<>();
        //获取用户的权限菜单集合
        List<Menu> menus = menuDao.findByUser(subpackageUser.getId());
        Set<String> perms = new HashSet<>();
        Set<String> codes = new HashSet<>();
        if (!CheckUtils.isNull(menus)) {
            menus.forEach(menu -> {
                perms.add(menu.getPerms());
                codes.add(menu.getCode());
            });
        }
        map.put("perms", perms);
        map.put("codes", codes);
        return map;
    }

    private List<Menu> getPermissionsList(SubpackageUser subpackageUser){
        Map<String, Set> map = new HashMap<>();
        //获取用户的权限菜单集合
        List<Menu> menus = menuDao.findByUser(subpackageUser.getId());
        return menus;
    }

    //将登录信息及token验证令牌缓存到redis中并返回生成用户登录信息map
    private Map<String, Object> getTokenMap(SubpackageUser loginUser,long loseTime){
        //获取加密token
        String token = JWTUtil.sign(loginUser.getLoginName(), loginUser.getPassword());
        //将用户的访问令牌存入redis中,自定义token前缀+token令牌作为key,用户信息作为value,设置过期时间
        String userInfo = JSON.toJSONString(loginUser);
        redisServiceImpl.set(Const.TOKEN_CACHE_ITEM_PREFIX +token,userInfo,loseTime);

        //将用户权限缓存到redis中  用户id为key，权限集合为value
        Map<String, Set> permissions = this.getPermissions(loginUser);
        List<Menu> permissionsList = this.getPermissionsList(loginUser);
        String permissionsJson = "";
        if(permissions.get("perms") != null){
            permissionsJson = JSON.toJSONString(permissions.get("perms"));
        }
        redisServiceImpl.set(Const.PERMISSIONS_CACHE_ITEM_PREFIX+loginUser.getLoginName(),permissionsJson,loseTime);

        //返回前端需要数据
        Map<String,Object> map = new HashMap<>();
        map.put("permissions",permissions.get("codes"));
        map.put("permissionsList",permissionsList);
        map.put("token",token);
        map.put("loginUser",loginUser);
        return map;
    }
}
