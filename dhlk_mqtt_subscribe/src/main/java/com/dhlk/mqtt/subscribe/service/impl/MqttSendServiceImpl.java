package com.dhlk.mqtt.subscribe.service.impl;

import com.dhlk.domain.Result;
import com.dhlk.mqtt.subscribe.utils.MqttSendServer;
import com.dhlk.utils.ResultUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MqttSendServiceImpl {
    @Resource
    private MqttSendServer mqttSendServer;
    public Result sendMessage(String topic,String biInfo){
        return mqttSendServer.sendMQTTMessage(topic, biInfo)?ResultUtils.success():ResultUtils.failure();
    }
}
