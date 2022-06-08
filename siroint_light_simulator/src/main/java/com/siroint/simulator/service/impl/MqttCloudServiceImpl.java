package com.siroint.simulator.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.siroint.simulator.comm.EnergyData;
import com.siroint.simulator.comm.LedConst;
import com.siroint.simulator.comm.ParamConsts;
import com.siroint.simulator.mqtt.MqttCloudSender;
import com.siroint.simulator.service.MqttCloudService;
import com.siroint.simulator.util.LedEnum;
import com.siroint.simulator.util.LedResult;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xmdeng
 * @date 2021/6/2 13:39
 */
@Service
@Slf4j
public class MqttCloudServiceImpl implements MqttCloudService {

    @Resource
    private MqttCloudSender mqttCloudSender;


    @Override
    public void subsribe(String topic, String mess) {
        JSONObject result = null;
        try {
            result = JSONObject.parseObject(mess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(LedConst.TOPIC_CLOUDTOLOCAL.equals(topic)){
            LedResult<Map> ledResult = JSONObject.parseObject(mess, LedResult.class);
            Map<String,Object> params= new HashMap<>();

            switch (LedEnum.getLedEnumByState(ledResult.getCmd_type())){
                case ONOFF:
                    if(ledResult.getData().get("on_off").equals(1)){
                        ParamConsts.snList.add(ledResult.getSN());
                    }else {
                        ParamConsts.snList.remove(ledResult.getSN());
                    }

                break;
                case GRPID:
                  break;
            }
            ledResult.setCmd_type(LedEnum.DEVLIGHT.getState());
            log.info("返回结果：{}",JSONObject.toJSONString(ledResult));
            try {

                mqttCloudSender.sendMQTTMessage(LedConst.TOPIC_LOCALTOCLOUD,JSONObject.toJSONString(ledResult));
            } catch (MqttException e) {
                log.error("本地发送云端失败！");
            }
        }
    }

    @Override
    public void localSubsribe(String topic, String mess) {
        JSONObject result = null;
        try {
            result = JSONObject.parseObject(mess);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(LedConst.LOCALTOHARD_TOPIC_DHLKLIGHT.equals(topic)){
            LedResult<Map> ledResult = JSONObject.parseObject(mess, LedResult.class);
            Map<String,Object> params= new HashMap<>();

            switch (LedEnum.getLedEnumByState(ledResult.getCmd_type())){
                case ONOFF:
                    if(ledResult.getData().get("on_off").equals(1)){
                        ParamConsts.snList.add(ledResult.getSN());
                    }else {
                        ParamConsts.snList.remove(ledResult.getSN());
                    }
                    ledResult.setCmd_type(LedEnum.DEVLIGHT.getState());
                    break;
                case GRPID:
                    break;
                case SWITCH_POL:
                    try {
                        LedResult<Map> bakResult = new LedResult(ledResult.getSN(),LedEnum.BACKRESULT,4,System.currentTimeMillis());
                        mqttCloudSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT,JSONObject.toJSONString(bakResult));
                    } catch (MqttException e) {
                        log.error("本地发送云端失败！");
                    }
                      break;
            }

            log.info("返回结果：{}",JSONObject.toJSONString(ledResult));

        }
    }

}
