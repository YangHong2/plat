package com.dhlk.light.test.task;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.light.test.mqtt.MqttSender;
import com.dhlk.light.test.util.LedConst;
import com.dhlk.light.test.util.LedEnum;
import com.dhlk.light.test.util.LedResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
    private MqttSender mqttSender;

    /**
     *  本地能耗存储
     * 灯上下线记录监控
     */
    @Scheduled(fixedDelay = 1000*10)
    public void addTestData(){
        LedResult<Map> ledResult = new LedResult<>();
        ledResult.setCmd_type(LedEnum.DEVLIGHT.getState());
        String [] sns = {"test1","test3","test2"};
        List<String> list = Arrays.asList(sns);
        list.forEach(sn->{
            ledResult.setSN(sn);
            Map<String,Object> paramsMap = new HashMap<>();

            EnergyData data = new EnergyData();
            data.setBrt_ness(100);
            data.setE_E(new BigDecimal(Math.random()/10).setScale(4, RoundingMode.HALF_UP));
            data.setE_P(new BigDecimal(Math.random()).setScale(4, RoundingMode.HALF_UP));
            data.setE_V(new BigDecimal(220));
            data.setOn_off(1);
            data.setE_I(new BigDecimal(Math.random()).setScale(4, RoundingMode.HALF_UP));
            paramsMap.put("E_E",data.getE_E());
            paramsMap.put("E_P",data.getE_P());
            paramsMap.put("E_V",data.getE_V());
            paramsMap.put("E_I",data.getE_I());
            paramsMap.put("on_off",data.getOn_off());
            paramsMap.put("brt_ness",data.getBrt_ness());
            ledResult.setData(paramsMap);
            mqttSender.sendMQTTMessage(LedConst.HARDTOLOCAL_TOPIC_BILIGHT, JSONObject.toJSONString(ledResult));
        });
    }

    @Data
    class EnergyData{

        private BigDecimal E_V;

        private BigDecimal E_P;

        private BigDecimal E_E;

        private BigDecimal E_I;

        private Integer on_off;

        private Integer brt_ness;
    }
}
