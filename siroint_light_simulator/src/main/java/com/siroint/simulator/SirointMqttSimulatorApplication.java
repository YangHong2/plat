package com.siroint.simulator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SirointMqttSimulatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(SirointMqttSimulatorApplication.class, args);
	}

}
