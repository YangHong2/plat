package com.dhlk.subcontract.web.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;
import com.dhlk.subcontract.web.service.fbk.LoginServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "subcontract-service", fallback = LoginServiceFbk.class)
public interface LoginService {
    /**
     * 登录
     *
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/login")
    Result login(@RequestBody SubpackageUser subpackageUser);

    /**
     * 注册
     *
     * @param subpackageUser 用户登录名和密码
     * @return token、用户信息、权限菜单
     */
    @PostMapping("/register")
    Result register(@RequestBody SubpackageUser subpackageUser);

    /**
     * 获取验证码
     *
     * @param companyEmail 企业邮箱
     * @return 发送验证码失败或成功
     */
    @PostMapping("/acquireAuthCode")
    Result acquireAuthCode(@RequestParam("companyEmail") String companyEmail);

    /**
     * 验证验证码
     *
     * @param authCode 验证码
     * @return 验证成功或失败
     */
    @PostMapping("/verifyAuthCode")
    Result verifyAuthCode(@RequestParam("companyEmail") String companyEmail,
                          @RequestParam("authCode") String authCode);

    /**
     * 登出
     */
    @GetMapping("/logout")
    Result logout();

    /**
     * 获取当前登录用户权限
     *
     * @return
     */
    @GetMapping("/permission")
    Result permission();

    /**
     * 使用TOKEN获取当前登录用户信息
     * @return
     */
    @GetMapping("/getUserInfo")
     Result tokenGetUserInfo();
}
