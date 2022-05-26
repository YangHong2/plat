package com.dhlk.mqtt.subscribe.utils;

import org.eclipse.paho.client.mqttv3.*;
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
public class MqttSendServer {
    private Logger log = LoggerFactory.getLogger(MqttSendServer.class);
    @Value("${mqtt.host}")
    public  String HOST;
    @Value("${mqtt.username}")
    public  String username;
    @Value("${mqtt.password}")
    public   String password;
    private static final String clientid = UUID.randomUUID().toString();

    public MqttClient client;

    public MqttTopic mqttTopic;

    public MqttMessage message;

    public void connect() throws MqttException {
        //防止重复创建MQTTClient实例
        if (client==null || !client.isConnected()) {
            //就是这里的clientId，服务器用来区分用户的，不能重复
            client = new MqttClient(HOST, clientid, new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(false);
            // 设置连接的用户名
            options.setUserName(username);
            // 设置连接的密码
            options.setPassword(password.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            client.connect(options);
        }
    }


    public  boolean publish(MqttTopic topic , MqttMessage message) throws MqttPersistenceException,
            MqttException {
        MqttDeliveryToken token = topic.publish(message);
        token.waitForCompletion();
        return token.isComplete();
    }

    /**
     * MQTT发送指令
     * @throws MqttException
     */
    public boolean sendMQTTMessage(String topic,String data) {
        boolean isSend = false;
        try {
            this.connect();
            mqttTopic = client.getTopic(topic);
            message = new MqttMessage();
            //消息等级
            //level 0：最多一次的传输
            //level 1：至少一次的传输，(鸡肋)
            //level 2： 只有一次的传输
            message.setQos(0);
            //如果重复消费，则把值改为true,然后发送一条空的消息，之前的消息就会覆盖，然后在改为false
            message.setRetained(false);

            message.setPayload(data.getBytes());
            isSend=publish(mqttTopic, message);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return isSend;
    }
}