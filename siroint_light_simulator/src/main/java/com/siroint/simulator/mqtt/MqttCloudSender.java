package com.siroint.simulator.mqtt;

import com.siroint.simulator.comm.LedConst;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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
@Component
public class MqttCloudSender {
    private Logger log = LoggerFactory.getLogger(MqttCloudSender.class);
    @Value("${mqtt.host}")
    public  String HOST="tcp://192.168.64.27:1883";
    @Value("${mqtt.username}")
    public  String username;
    @Value("${mqtt.password}")
    public   String password;

    public MqttClient client;

    private   MqttConnect mqttConnect = new MqttConnect();

    private   MqttConnectOptions options;


    public synchronized boolean connectCloud(){
        try {
            //防止重复创建MQTTClient实例
            if (client == null) {
                //就是这里的clientId，服务器用来区分用户的，不能重复
                client = new MqttClient(HOST, "C_send"+UUID.randomUUID().toString(), new MemoryPersistence());// MemoryPersistence设置clientid的保存形式，默认为以内存保存
                options = mqttConnect.getOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                options.setMaxInflight(1000);
            }
            if (!client.isConnected()) {
                client.connect(options);
                log.info(System.currentTimeMillis() + "云端发送连接完成...");
            }
        } catch (Exception e) {
            log.info(System.currentTimeMillis() + "云端发送连接断开...");
        }
        return client.isConnected();
    }

    public synchronized void sendMQTTMessage(String topic,String msg) throws MqttException {
        //log.info("云端发送状态->"+redisService.get(LedConst.REDIS_MQTTISRIGHT));
        //如果云端mqtt判断状态为空，设置0
               boolean temp= connectCloud();
                if (temp) {
                    MqttMessage message = new MqttMessage(msg.getBytes());
                    message.setQos(0);
                    message.setRetained(false);
                    message.setPayload(msg.getBytes());
                    client.publish(topic, message);
                } else {
                    client.close();
                    client = null;
                    //开启定时重连机制
                    reConcatMqtt();
                    System.out.println("云端发送mqtt断开....");
                }
    }



    private synchronized void reConcatMqtt(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        Future future = executorService.scheduleAtFixedRate(new  Runnable() {
            @Override
            public void run() {
                try {
                   boolean isTemp= connectCloud();
                   log.info(System.currentTimeMillis() + "云端sender连接断开，正在尝试重新连接...");
                   if(isTemp){
                       log.info("云端sender重连成功....."+System.currentTimeMillis());
                       executorService.shutdown();
                   }
                } catch (Exception e) {
                    log.info(System.currentTimeMillis() + "云端mqtt发送重连报错...");
                    e.printStackTrace();
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
