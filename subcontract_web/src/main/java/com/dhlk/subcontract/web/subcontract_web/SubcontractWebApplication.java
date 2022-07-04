package com.dhlk.subcontract.web.subcontract_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients(basePackages = {"com.dhlk.subcontract.web.service"})
@ComponentScan("com.dhlk.subcontract.web")
public class SubcontractWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SubcontractWebApplication.class,args);
    }
}
