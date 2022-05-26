package com.dhlk.interfaces.service.dhlk_interfaces_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@ComponentScan("com.dhlk")
@MapperScan(basePackages="com.dhlk.interfaces.service.dao")
@SpringBootApplication
@EnableSwagger2
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class DhlkInterfacesServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DhlkInterfacesServiceApplication.class, args);
    }

}
