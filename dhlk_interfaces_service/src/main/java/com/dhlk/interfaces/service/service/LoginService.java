package com.dhlk.interfaces.service.service;

import com.dhlk.domain.Result;

public interface LoginService {
    /**
     * 登录
     */
    Result login(String loginName,String password);

    /**
     * 校验token
     * @author  gchen
     * @return  Result
     */
    Result checkToken();
}
