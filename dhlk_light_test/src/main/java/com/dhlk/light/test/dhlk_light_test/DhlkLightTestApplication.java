package com.dhlk.light.test.dhlk_light_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan("com.dhlk")
@MapperScan(basePackages="com.dhlk.light.test.dao")
@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DhlkLightTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhlkLightTestApplication.class, args);
    }

}
