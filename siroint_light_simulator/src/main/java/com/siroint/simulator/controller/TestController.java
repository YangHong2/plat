package com.siroint.simulator.controller;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.domain.Result;
import com.dhlk.utils.ResultUtils;
import com.siroint.simulator.comm.LedConst;
import com.siroint.simulator.feign.DhlkWebFeign;
import com.siroint.simulator.mqtt.MqttCloudSender;
import com.siroint.simulator.util.LedResult;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xmdeng
 * @date 2021/7/23 12:42
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    private DhlkWebFeign dhlkWebFeign;

    @Autowired
    private MqttCloudSender mqttCloudSender;

    @GetMapping("/test/get")
    public Result test(){
        return dhlkWebFeign.kaptcha();
    }

    @PostMapping("/test/post")
    public Result testPost(){
        return dhlkWebFeign.savePower(new ArrayList<>());
    }

    @GetMapping("/oper/switch")
    public Result operSwitch(@RequestParam("energyTs") String energyTs){
        LedResult<Map> ledResult = new LedResult<>();
        ledResult.setSN("test2021081001");
        ledResult.setDev_type(4);
        ledResult.setCmd_type(0);
        Map<String,String> data = new HashMap<>();
        data.put("energy_ts",energyTs);
        ledResult.setData(data);
        try {
            log.info(JSONObject.toJSONString(ledResult));
            mqttCloudSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT, JSONObject.toJSONString(ledResult));
        } catch (MqttException e) {
            return ResultUtils.failure();
        }
        return ResultUtils.success();
    }
}
