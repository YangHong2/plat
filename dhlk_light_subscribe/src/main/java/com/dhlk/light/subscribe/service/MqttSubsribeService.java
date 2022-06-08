package com.dhlk.light.subscribe.service;


import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @Description mqtt消息订阅接口
 * @Author lpsong
 * @Date 2020/6/1
 */
public interface MqttSubsribeService {

    void subsribe(String topic,String mess);

    void subsriberMessage(String topic,MqttMessage message);
}
