package com.dhlk.web.mqtt.controller;

import com.dhlk.domain.Result;
import com.dhlk.web.mqtt.service.MqttSendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mqtt")
@Api(value = "mqtt服务")
public class MqttController {
    @Autowired
    private MqttSendService mqttSendService;
    @GetMapping("/sendMessage")
    public Result sendMessage(@RequestParam("topic")String topic,@RequestParam("biInfo")String biInfo){
        return mqttSendService.sendMessage(topic,biInfo);
    }
}
