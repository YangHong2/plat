package com.dhlk.subcontract.subcontract_service;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@MapperScan(basePackages="com.dhlk.subcontract.dao")
@EnableSwagger2
@ComponentScan("com.dhlk")
@SpringBootApplication
public class SubcontractApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubcontractApplication.class,args);
    }
}
