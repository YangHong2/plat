package com.dhlk.mqtt.subscribe.controller;

import com.dhlk.domain.Result;
import com.dhlk.mqtt.subscribe.service.impl.MqttSendServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/mqtt")
public class MqttController {
    @Resource
    private MqttSendServiceImpl mqttSendService;
    @GetMapping("/sendMessage")
    public Result sendMessage(@RequestParam("topic")String topic,@RequestParam("biInfo")String biInfo){
        return mqttSendService.sendMessage(topic,biInfo);
    }
}
