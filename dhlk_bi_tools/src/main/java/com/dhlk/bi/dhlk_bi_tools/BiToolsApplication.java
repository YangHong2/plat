package com.dhlk.bi.dhlk_bi_tools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan("com.dhlk")
@MapperScan(basePackages="com.dhlk.bi.dao")
public class BiToolsApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiToolsApplication.class,args);
    }
}
