package com.dhlk.light.test.util;

import com.alibaba.fastjson.JSONObject;
import com.dhlk.light.test.entity.IntensityInfo;
import com.dhlk.light.test.entity.LedWifiInfo;
import com.dhlk.light.test.entity.PeopleFeelInfo;
import com.dhlk.light.test.mqtt.MqttSender;
import com.dhlk.light.test.service.RedisService;
import com.dhlk.utils.CheckUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.*;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/24
 */
@Component
public class LedOperate {
    @Autowired
    private MqttSender mqttSender;
    @Autowired
    private RedisService redisService;


    public  List<String> ledOnOrOff(String sns, Integer status){
        if (!CheckUtils.isNull(sns)) {
            Map<String,Object> params=new HashMap();
            params.put("on_off", status);
            Long ts = System.currentTimeMillis();
            for (String sn : sns.split(",")) {
                sendMessToLocalMqtt(new LedResult(sn, LedEnum.ONOFF, params, ts));
            }
            System.out.println(status == 0 ?"开灯命令发送完成":"关灯命令发送完成");
            return sendToMqttIsSuccess(sns, ts);
        }
        return null;
    }
    public  List<String> brightness(String sns, Integer bright){
        if (!CheckUtils.isNull(sns)) {
            Map<String,Object> params=new HashMap();
            params.put("shine", bright);
            Long ts = System.currentTimeMillis();
            for (String sn : sns.split(",")) {
                sendMessToLocalMqtt(new LedResult(sn, LedEnum.LIGHTNESS, params, ts));
            }
            System.out.println("调光命令发送完成");
            //比对给没有mqtt未返回的灯重新发送
            return sendToMqttIsSuccess(sns, ts);
        }
        return null;
    }

    /**
     * 设置灯wifi
     */
    public List<String>  wifiContro(String sns, Integer wfMode) {
        if (!CheckUtils.isNull(sns)) {
            LedWifiInfo ledWifiInfo=new LedWifiInfo("FYX", "dhlktech", "192.168.2.223", 2, wfMode);
            Long ts = System.currentTimeMillis();
            for (String sn : sns.split(",")) {
                sendMessToLocalMqtt(new LedResult(sn, LedEnum.SETWIFI, ledWifiInfo, ts));
            }
            System.out.println("WIFI设置命令发送完成");
            //比对给没有mqtt未返回的灯重新发送
            return sendToMqttIsSuccess(sns, ts);
        }
        return null;
    }
    /**
     * 人感控制
     */
    public List<String>  pelpleControl(String sns, PeopleFeelInfo peopleFeelInfo) {
        if (!CheckUtils.isNull(sns)) {
            Long ts = System.currentTimeMillis();
            for (String sn : sns.split(",")) {
                sendMessToLocalMqtt(new LedResult(sn, LedEnum.SETPEOPLEFELL, peopleFeelInfo, ts));
            }
            System.out.println("人感设置命令发送完成");
            //比对给没有mqtt未返回的灯重新发送
            return sendToMqttIsSuccess(sns, ts);
        }
        return null;
    }
    /**
     * 光感控制
     */
    public List<String>  lightControl(String sns, IntensityInfo intensityInfo) {
        if (!CheckUtils.isNull(sns)) {
            Long ts = System.currentTimeMillis();
            for (String sn : sns.split(",")) {
                sendMessToLocalMqtt(new LedResult(sn, LedEnum.SETLIGHTFELL, intensityInfo, ts));
            }
            System.out.println("光感设置命令发送完成");
            //比对给没有mqtt未返回的灯重新发送
            return sendToMqttIsSuccess(sns, ts);
        }
        return null;
    }
    /**
     * 本地mqtt消息发送
     * @param ledResult
     * @return
     */
    public void sendMessToLocalMqtt(LedResult ledResult) {
        try{
            mqttSender.sendMQTTMessage(LedConst.LOCALTOHARD_TOPIC_DHLKLIGHT, JSONObject.toJSONString(ledResult));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 硬件返回结果比对
     * @param sns
     * @param ts
     * @return
     */
    private List<String> sendToMqttIsSuccess(String sns, Long ts) {
        List<String> ledSns = new ArrayList<>(Arrays.asList(sns.split(",")));
        CountDownLatch countDown = new CountDownLatch(1);//设置线程阻塞等待
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future future = executorService.scheduleAtFixedRate(new Runnable() {
            Object backRes = null;
            private int count = 0;
            @Override
            public void run() {
                count++;
                for (String sn : sns.split(",")) {
                    backRes = redisService.get(sn + "_" + ts);
                    //循环了10次，等待了100毫秒，仍无返回结果，则进行重新给mqtt发送消息
                    if (backRes != null) {
                        ledSns.remove(sn);
                    }
                }
                if (CollectionUtils.isEmpty(ledSns) || count > 10) {
                    executorService.shutdown();
                    countDown.countDown();
                }
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
        try {
            countDown.await();//阻塞在这里,等到所有线程返回结果
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ledSns;
    }
}