package com.dhlk.light.test.mqtt;

import com.dhlk.light.test.service.MqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/6/1
 */
@Configuration
public class MqttRevice {
    private Logger log = LoggerFactory.getLogger(MqttRevice.class);
    @Value("${mqtt.host}")
    public String HOST;
    @Value("${mqtt.username}")
    public String username;
    @Value("${mqtt.password}")
    public String password;
    @Autowired
    private MqttService mqttService;
    //"dhlk_light/#",
    private String[] topic = {"bi_light"};

    private int[] Qos = {2};

    private MqttClient mqttClient=null;

    private MqttConnect mqttConnect = new MqttConnect();

    private MqttConnectOptions options;



    //方法实现说明 断线重连方法，如果是持久订阅，重连是不需要再次订阅，如果是非持久订阅，重连是需要重新订阅主题 取决于options.setCleanSession(true);
    // true为非持久订阅
    public  MqttClient newConnect()  {
        //防止重复创建MQTTClient实例
        if (mqttClient == null) {
            try {
                mqttClient = new MqttClient(HOST,  "L_subs"+UUID.randomUUID().toString(), new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
            } catch (MqttException e) {
                e.printStackTrace();
                return null;
            }
        }
        return mqttClient;
    }
    public Boolean startSubsribe(){
        if(mqttClient!=null){
            //如果是订阅者则添加回调类，发布不需要
            mqttClient.setCallback(new MqttCallback() {
                public void connectionLost(Throwable cause) {
                    cause.printStackTrace();
                    log.info("本地订阅连接断开....." + System.currentTimeMillis());
                    try {
                        mqttClient.close();
                        mqttClient=null;
                        newConnect();
                        reConcatMqtt();
                    } catch (MqttException e) {
                        log.info("本地订阅连接异常....." + System.currentTimeMillis());
                    }
                }
                public void deliveryComplete(IMqttDeliveryToken token) {
                    log.info("本地连接完成" + token.isComplete());
                }
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    log.error("主题：{},消息：{}",topic,message.toString());
                    mqttService.subsribe(topic, message.toString());
                }
            });
        }
        options = mqttConnect.getOptions();
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        try {
            mqttClient.connect(options);
            if(mqttClient.isConnected()){
                mqttClient.subscribe(topic, Qos);
            }
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
     }

     public void reConcatMqtt(){
         ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
         Future future = executorService.scheduleAtFixedRate(new Runnable() {
             @Override
             public void run() {
                 try {
                     log.info(System.currentTimeMillis() + "本地订阅连接断开，正在尝试重新连接...");
                     if(startSubsribe()){
                         log.info("本地订阅新开连接重连成功....."+System.currentTimeMillis());
                         executorService.shutdown();
                     }
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
             }
         }, 0, 1, TimeUnit.MINUTES);
     }
     @Bean
     public void startLocal() {
         newConnect();
         if (!startSubsribe()) {
             reConcatMqtt();
         }
     }
}
