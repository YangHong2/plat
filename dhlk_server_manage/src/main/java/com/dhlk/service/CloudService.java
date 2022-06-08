package com.dhlk.service;

import com.dhlk.domain.Result;

/**
 * 消息发送至云端的服务
 * @author xmdeng
 * @date 2021/8/2 13:28
 */
public interface CloudService {

    /**
     * 发送服务信息至云端
     * @param result 本地一体机服务信息
     */
    void sendServiceInfo(Result result);
}
