package com.siroint.simulator.mqtt;

import com.siroint.simulator.service.MqttCloudService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description
 * @Author lpsong
 * @Date 2020/6/1
 */
@Configuration
public class MqttLocalSubsribe {
    private Logger log = LoggerFactory.getLogger(MqttLocalSubsribe.class);
    @Value("${mqtt.host}")
    public String HOST = "tcp://192.168.64.29:1883";
    @Value("${mqtt.username}")
    public String username = "dhlk";
    @Value("${mqtt.password}")
    public String password = "dhlktech";
//    @Value("${mqtt.connectionLost}")
    public int connectionLost = 10;
    @Autowired
    private MqttCloudService mqttLocalService;

    private String[] topic = {"bi_light/#"};

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
                options = mqttConnect.getOptions();
                options.setUserName(username);
                options.setPassword(password.toCharArray());
                mqttClient.setCallback(new MqttCallback() {
                    public void connectionLost(Throwable cause){
                        cause.printStackTrace();
                        log.info(System.currentTimeMillis()+"本地订阅连接断开....."+cause);
                        localStop();
                        localStart();
                    }
                    public void deliveryComplete(IMqttDeliveryToken token) {
                        log.info("本地连接完成" + token.isComplete());
                    }
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        mqttLocalService.localSubsribe(topic, message.toString());
                    }
                });
                if (mqttClient.isConnected()) {//这里的逻辑是如果连接成功就重新连接
                    mqttClient.disconnect();
                }
                mqttClient.connect(options);
                if(mqttClient.isConnected()){
                    mqttClient.subscribe(topic, Qos);
                    log.info(System.currentTimeMillis()+"本地连接成功，已开始订阅消息···");
                }
            } catch (MqttException e) {
                log.info(System.currentTimeMillis()+"本地MQTT连接出错···"+e);
                return null;
            }
        }
        return mqttClient;
    }
    public void localStart(){
        for (int i = 0; i < connectionLost; i++) {
            try {
                Thread.sleep(10000);
                log.info(System.currentTimeMillis() + "本地mqtt订阅第"+i+"次正在连接···");
                newConnect();
                if (mqttClient.isConnected()) {//连接成功，跳出连接
                    log.info(System.currentTimeMillis() + "本地mqtt订阅重新连接成功...");
                    break;
                }else {
                    //若连接失败则关闭资源准备下一次重连
                    localStop();
                    log.info(System.currentTimeMillis() + "本地mqtt订阅第"+i+"次连接失败...");
                }
            } catch (Exception e1) {
                log.info(System.currentTimeMillis() + "本地mqtt订阅第"+i+"次连接失败..."+e1);
            }
        }
    }
    public void localStop(){
        try{
            // 断开连接
            mqttClient.disconnect();
            // 关闭客户端
            mqttClient.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            mqttClient = null;
        }
    }

    @Bean
    public void startLocal() {
        //为了避免连接MQTT耽搁项目启动时间 连接mqtt用线程
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                localStart();
            }
        });
     }
}
