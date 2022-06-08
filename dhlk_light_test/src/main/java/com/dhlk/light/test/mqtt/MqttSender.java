package com.dhlk.light.test.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/6/1
 */
@Component
public class MqttSender {
    private Logger log = LoggerFactory.getLogger(MqttSender.class);
    @Value("${mqtt.host}")
    public  String HOST;
    @Value("${mqtt.username}")
    public   String username;
    @Value("${mqtt.password}")
    public   String password;

    private static final String clientid = "L_send"+UUID.randomUUID().toString();

    public MqttClient client;


    private  MqttConnect mqttConnect = new MqttConnect();

    private MqttConnectOptions options;

    public synchronized boolean connectLocal() throws MqttException {
        try {
            //防止重复创建MQTTClient实例
            if (client == null) {
                //就是这里的clientId，服务器用来区分用户的，不能重复
                client = new MqttClient(HOST, clientid, new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
                options = mqttConnect.getOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                options.setMaxInflight(1000);
            }
            //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
            if (!client.isConnected()) {
                client.connect(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("本地连接异常....");
        }
        return client.isConnected();
    }

    public void sendMQTTMessage(String topic,String msg) {
        try {
            log.info("主题：{},信息:{}",topic,msg);
            if (this.connectLocal()) {
                MqttMessage message = new MqttMessage(msg.getBytes());
                message.setQos(0);
                client.publish(topic, message);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
}
