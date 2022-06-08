package com.dhlk.light.test.service;

import com.dhlk.domain.Result;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/24
 */
public interface LightService {
    /**
     *  灯开关
     * @param sns
     * @param status
     * @return
     */
    Result openOrClose(String sns, Integer status);
}