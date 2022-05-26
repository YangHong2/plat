package com.dhlk.apis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@ComponentScan("com.dhlk")
public class DhlkApisApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhlkApisApplication.class, args);
    }

}
