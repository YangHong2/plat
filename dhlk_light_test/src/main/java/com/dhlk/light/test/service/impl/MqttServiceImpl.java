package com.dhlk.light.test.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhlk.light.test.mqtt.MqttSender;
import com.dhlk.light.test.service.MqttService;
import com.dhlk.light.test.service.RedisService;
import com.dhlk.light.test.util.LedConst;
import com.dhlk.light.test.util.LedEnum;
import com.dhlk.light.test.util.LedResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/23
 */
@Service
@Slf4j
public class MqttServiceImpl implements MqttService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private MqttSender mqttSender;

    @Override
    public void subsribe(String topic, String jsonStr) {
        //灯数据订阅
        JSONObject result = null;
        Integer comType = null;
        try {
            result = JSONObject.parseObject(jsonStr);
            comType = result.getInteger("cmd_type");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (topic.contains(LedConst.HARDTOLOCAL_TOPIC_BILIGHT)) {
            if (comType == LedEnum.POWER.getState()) {//订阅灯电能数据
                ledPower(result);
            } else if (comType == LedEnum.BACKRESULT.getState()) {//返回发送命令执行结果
                mqttCommandBack(jsonStr);
            }
        }else if(topic.contains(LedConst.LOCALTOHARD_TOPIC_DHLKLIGHT)){
            LedResult<Map> ledResult = JSONObject.parseObject(jsonStr, LedResult.class);
            Map<String,Object> params= new HashMap<>();

            switch (LedEnum.getLedEnumByState(ledResult.getCmd_type())){
                case ONOFF:
                    params.put("on_off",result.getJSONObject("data").getInteger("on_off"));
                    break;
            }
//            ledResult.setCmd_type(LedEnum.BACKRESULT.getState());
            ledResult.setData(params);
            log.info("返回结果：{}",JSONObject.toJSONString(ledResult));
            mqttSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT,JSONObject.toJSONString(ledResult));
        }
    }
    /**
     * 将订阅(本地MQTT)灯的能耗数据存入数据库，并将实时数据发送给云MQTT
     *
     * @param result
     * @return
     */
    public void ledPower(JSONObject result) {
        redisService.set(LedConst.REDIS_POWER + result.get("SN"), JSON.toJSONString(result), 30);//增加30秒
    }
    /**
     * 返回发送命令执行结果
     * @param jsonStr
     * @return
     */
    public void mqttCommandBack(String jsonStr) {
        JSONObject result = JSONObject.parseObject(jsonStr);
        if (result != null) {
            //发送给云端
            JSONObject data = JSONObject.parseObject(result.get("data").toString());
            if (data != null) {
                redisService.set(result.get("SN") + "_" + result.get("ts"), data.get("cmd_result"), 3);
            }
        }
    }
}
