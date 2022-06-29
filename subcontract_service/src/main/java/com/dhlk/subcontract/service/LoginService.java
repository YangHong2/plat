package com.dhlk.subcontract.service;

import com.dhlk.domain.Result;
import com.dhlk.entity.sub.Menu;
import com.dhlk.entity.sub.SubpackageUser;

import java.util.List;

public interface LoginService {
    /**
     * 获取权限
     * @return
     */
     List<Menu> permission();
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

    /**
     * 使用TOKEN获取当前登录用户信息
     * @return
     */
    Result tokenGetUserInfo();
}
