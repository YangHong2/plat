package com.dhlk.light.factoryconstruction;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author wzx
 * @date 2021/8/9 17:04
 */
@SpringBootApplication
@ComponentScan("com.dhlk")
public class DhlkLightFactoryConstructionApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhlkLightFactoryConstructionApplication.class, args);
    }

}
