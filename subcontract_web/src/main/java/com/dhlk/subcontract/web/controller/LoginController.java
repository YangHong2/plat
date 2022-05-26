package com.dhlk.subcontract.web.controller;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.web.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 登录、注册、登出
 */
@RestController
@Api(value = "登录、注册、登出")
public class LoginController {
    @Resource
    private LoginService loginService;
    /**
     * 登录
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(@RequestBody SubpackageUser subpackageUser){
        return loginService.login(subpackageUser);
    }
    /**
     * 注册
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/register")
    @ApiOperation("注册")
    public Result register(@RequestBody SubpackageUser subpackageUser){
        return loginService.register(subpackageUser);
    }
    /**
     * 获取验证码
     * @param companyEmail 企业邮箱
     * @return 发送验证码失败或成功
     */
    @PostMapping("/acquireAuthCode")
    @ApiOperation("获取验证码")
    public Result acquireAuthCode(@RequestParam("companyEmail") String companyEmail){
        return loginService.acquireAuthCode(companyEmail);
    }

    /**
     * 验证验证码
     * @param authCode 验证码
     * @return 验证成功或失败
     */
    @PostMapping("/verifyAuthCode")
    @ApiOperation("验证验证码")
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
