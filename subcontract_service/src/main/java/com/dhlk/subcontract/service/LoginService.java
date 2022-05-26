package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.SubpackageUser;

public interface LoginService {
    /**
     * 登录
     */
    Result login(SubpackageUser subpackageUser);

    /**
     * 获取验证码
     */
    Result acquireAuthCode(String companyEmail);

    /**
     * 注册
     */
    Result register(SubpackageUser subpackageUser);

    /**
     * 验证验证码
     */
    Result verifyAuthCode(String companyEmail,String authCode);

    /**
     * 登出
     */
    Result logout();
}
