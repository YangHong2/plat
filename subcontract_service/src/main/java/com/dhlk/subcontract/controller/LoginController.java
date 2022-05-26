package com.dhlk.subcontract.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.service.LoginService;
import com.dhlk.systemconst.Const;
import com.dhlk.utils.ResultUtils;
import com.dhlk.utils.SendEmailUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录、注册、登出
 */
@RestController
public class LoginController {
    @Resource
    private LoginService loginService;
    @PostMapping("/sendMail")
    public Result sendMail(){
        SendEmailUtil sendEmailUtil = new SendEmailUtil();
        Map<String,String> map = new HashMap();
//        map.put("xkliu@hanlinova.com","to");
//        map.put("lpsong@hanlinova.com","to");
//        map.put("qwang@hanlinova.com","to");
//        map.put("wyang@hanlinova.com","to");
        map.put("pjliang@hanlinova.com","to");
//        map.put("ztang@hanlinova.com","to");
//        sendEmailUtil.connect(Const.receiverMap,Const.subject,Const.content,Const.filePath);
//        sendEmailUtil.connect(map, Const.subject,Const.content,Const.filePath);
//        sendEmailUtil.send();
        return ResultUtils.success();
    }

    /**
     * 登录
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/login")
    public Result login(@RequestBody SubpackageUser subpackageUser){
        return loginService.login(subpackageUser);
    }
    /**
     * 注册
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/register")
    public Result register(@RequestBody SubpackageUser subpackageUser){
        return loginService.register(subpackageUser);
    }
    /**
     * 获取验证码
     * @param companyEmail 企业邮箱
     * @return 发送验证码失败或成功
     */
    @PostMapping("/acquireAuthCode")
    public Result acquireAuthCode(@RequestParam("companyEmail") String companyEmail){
        return loginService.acquireAuthCode(companyEmail);
    }

    /**
     * 验证验证码
     * @param authCode 验证码
     * @return 验证成功或失败
     */
    @PostMapping("/verifyAuthCode")
    public Result verifyAuthCode(@RequestParam("companyEmail") String companyEmail,
                                 @RequestParam("authCode") String authCode){
        return loginService.verifyAuthCode(companyEmail,authCode);
    }

    /**
     * 登出
     */
    @ApiOperation(value = "登出")
    @GetMapping("/logout")
    public Result logout() {
        return loginService.logout();
    }
}
