package com.dhlk.light.subscribe.mqtt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dhlk.light.subscribe.service.MqttSubsribeService;
import com.dhlk.light.subscribe.service.MqttToLightServiceSubsribe;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.UUID;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/6/1
 */
@Configuration
public class MqttSubsribe {
    private Logger log = LoggerFactory.getLogger("reportLog");
    @Value("${mqtt.host}")
    public String HOST;
    @Value("${mqtt.username}")
    public String username;
    @Value("${mqtt.password}")
    public String password;

    @Autowired
    private MqttSubsribeService mqttSubsribeService;

    /**
     * 主题
     */
    private String[] topic = {"localToCloud", "CLOUD_TOPIC_LED_VERSION", "CLOUD_TOPIC_ORIPOWER_UPDATE", "CLOUD_TOPIC_ORIPOWER_SAVE", "CLOUD_TOPIC_SYNC_DATA", "CLOUD_TOPIC_FAULT", "LOCAL_TOPIC_POWER_DATASYNC", "LOCAL_TOPIC_ONLINE_DATASYNC", "LOCAL_TOPIC_PEOPLE_FEEL_DATASYNC", "CLOUD_TOPIC_ORIPOWER_SAVE", "CLOUD_TOPIC_ORIPOWER_UPDATE","localBatchToCloud"};

    public MqttClient client;

    private MqttConnectOptions options;

    private int[] Qos = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2,2};//


    //方法实现说明 断线重连方法，如果是持久订阅，重连是不需要再次订阅，如果是非持久订阅，重连是需要重新订阅主题 取决于options.setCleanSession(true);
    // true为非持久订阅
    public MqttClient connect() {
        //防止重复创建MQTTClient实例
        if (client == null) {
            try {
                client = new MqttClient(HOST, UUID.randomUUID().toString(), new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
            } catch (MqttException e) {
                e.printStackTrace();
                return null;
            }
        }
        return client;
    }

    public Boolean startSubsribe(MqttClient client) {
        if (client != null) {
            //如果是订阅者则添加回调类，发布不需要
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    log.info("云端订阅连接断开....." + System.currentTimeMillis());
                    MqttClient client = connect();
                    reConcatMqtt(client);
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    log.info("云端订阅连接完成" + token.isComplete());
                }
                @Override
                public void messageArrived(String topic, MqttMessage message)  {
                    try {
                        log.info(message.toString());
                        mqttSubsribeService.subsriberMessage(topic,message);
                    }catch (Exception e){
                        log.error(e.getMessage());
                    }

                }
            });
        }
        options = new MqttConnectOptions();
        options.setCleanSession(true);
        options.setConnectionTimeout(0);
        //设置心跳
        options.setKeepAliveInterval(10);
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        try {
            client.connect(options);
            if (client.isConnected()) {
                client.subscribe(topic, Qos);
            }
        } catch (MqttException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void reConcatMqtt(MqttClient client) {
        while (true) {
            try {
                if (startSubsribe(client)) {
                    log.info("云端订阅重连成功....." + System.currentTimeMillis());
                    break;
                }
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Bean
    public void start() {
        MqttClient client = connect();
        startSubsribe(client);
    }

}
