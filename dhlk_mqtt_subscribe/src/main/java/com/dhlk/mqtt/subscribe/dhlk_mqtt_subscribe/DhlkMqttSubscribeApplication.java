package com.dhlk.mqtt.subscribe.dhlk_mqtt_subscribe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.dhlk")
@SpringBootApplication
@EnableDiscoveryClient
public class DhlkMqttSubscribeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DhlkMqttSubscribeApplication.class, args);
//		HttpClientMqtt client = new HttpClientMqtt();
//		client.start();
	}

}
