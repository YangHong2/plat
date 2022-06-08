package com.dhlk.light.test.service;

/**
 * @Description mqtt本地消息订阅接口
 * @Author lpsong
 * @Date 2020/6/1
 */
public interface MqttService {
    void subsribe(String topic, String mess);
}
