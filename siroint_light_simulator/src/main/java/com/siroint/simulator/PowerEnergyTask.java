package com.siroint.simulator;

import com.alibaba.fastjson.JSONObject;
import com.siroint.simulator.comm.EnergyData;
import com.siroint.simulator.comm.LedConst;
import com.siroint.simulator.comm.ParamConsts;
import com.siroint.simulator.mqtt.MqttCloudSender;
import com.siroint.simulator.util.LedEnum;
import com.siroint.simulator.util.LedResult;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
* @author xmdeng
* @date 2021/5/18 15:41
*/
@Component
@Slf4j
@EnableScheduling
public class PowerEnergyTask {

    @Autowired
    private MqttCloudSender mqttCloudSender;

    /**
     *  本地能耗存储
     * 灯上下线记录监控
     */
    @Scheduled(fixedDelay = 1000*4)
    public void addTestData(){
        LedResult<Map> ledResult = new LedResult<>();
        ledResult.setCmd_type(LedEnum.DEVLIGHT.getState());
        for(int i = 0 ; i < 200 ; i++){
            ParamConsts.snList.add("test20210918"+String.format("%2d",i));
        }
        ParamConsts.snList.add("00202031000013");
        ParamConsts.snList.forEach(sn->{
            ledResult.setSN(sn);
            Map<String,Object> paramsMap = new HashMap<>();

            EnergyData data = new EnergyData();
            data.setBrt_ness(100);
            data.setE_E(new BigDecimal(Math.random()/10).setScale(4, RoundingMode.HALF_UP));
            data.setE_P(new BigDecimal(Math.random()).setScale(4, RoundingMode.HALF_UP));
            data.setE_V(new BigDecimal(220));
            data.setOn_off(11);
            data.setE_I(new BigDecimal(Math.random()).setScale(4, RoundingMode.HALF_UP));
            paramsMap.put("E_E",data.getE_E());
            paramsMap.put("E_P",data.getE_P());
            paramsMap.put("E_V",data.getE_V());
            paramsMap.put("E_I",data.getE_I());
            paramsMap.put("on_off",data.getOn_off());
            paramsMap.put("brt_ness",data.getBrt_ness());
            ledResult.setData(paramsMap);
            try {
                log.info(JSONObject.toJSONString(ledResult));//TOPIC_LOCALTOCLOUD
                mqttCloudSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT, JSONObject.toJSONString(ledResult));
            } catch (MqttException e) {
                e.printStackTrace();
            }
        });
    }

    @Scheduled(fixedDelay = 1000*30)
    public void testSwitch(){
        String [] testSn = {"00202037111112"};
        for(String sn:testSn){
            LedResult<Map> ledResult = new LedResult<>();
            ledResult.setSN(sn);
            ledResult.setDev_type(4);
            ledResult.setCmd_type(0);
            Map<String,String> data = new HashMap<>();
            data.put("energy_ts","1100033556480");
            ledResult.setData(data);
            try {
                //TOPIC_LOCALTOCLOUD
                log.info(JSONObject.toJSONString(ledResult));
                mqttCloudSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT, JSONObject.toJSONString(ledResult));
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
