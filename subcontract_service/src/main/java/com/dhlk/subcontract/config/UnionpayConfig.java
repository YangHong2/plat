package com.dhlk.subcontract.config;

import com.dhlk.subcontract.sdk.SDKConfig;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description
 * @Author lpsong
 * @Date 2021/3/15
 */
@Configuration
public class UnionpayConfig{

    @Bean
    public void run() {
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }
}