package com.dhlk.web.mqtt.service;

import com.dhlk.domain.Result;
import com.dhlk.web.mqtt.service.fbk.MqttSendServiceFbk;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author gchen
 * @date 2020/8/5
 * <p>
 * mqtt服务
 */
@FeignClient(value = "mqtt-service/mqtt", fallback = MqttSendServiceFbk.class)
public interface MqttSendService {
    @GetMapping("/sendMessage")
    Result sendMessage(@RequestParam("topic")String topic,@RequestParam("biInfo")String biInfo);
}
