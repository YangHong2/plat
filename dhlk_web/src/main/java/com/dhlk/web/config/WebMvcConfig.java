package com.dhlk.web.config;


import com.dhlk.web.Interceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 版本        修改时间        编者      备注
 * V1.0        ------        jpdong    原始版本
 * 文件说明:
 **/
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${attachment.path}")
    private String attachmentPath;

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new  AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).excludePathPatterns(getExecutePathPattern());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/doc.html", "/v2/**", "/swagger-ui.html/**","/resources/**")
                .addResourceLocations("classpath*:/META-INF/resources/","/doc.html", "/v2/**", "/swagger-ui.html/**","/resources/**");
        registry.addResourceHandler("/attach/**").addResourceLocations("file:"+attachmentPath);
    }

    public List<String> getExecutePathPattern(){
        List<String> patterns = new ArrayList<>();
        patterns.add("/app/wxLogin");
        patterns.add("/app/appLogout");
        patterns.add("/companylist/findCompanyList"); // 获取工厂列表
        patterns.add("/led/findAreasByLed"); // 施工区域列表
        patterns.add("/led/findLedsByArea");// 查询区域灯详情
        patterns.add("/group/findList");// 查询分组信息
        patterns.add("/app/lamp/overview");// 获取总览信息
        patterns.add("/app/lamp/trendChart");// 获取柱状统计数据
        patterns.add("/app/lamp/getEnergyStatistics");// 获取饼图统计数据
        patterns.add("/scheduler/findTaskSchedulerList");// 获取场景列表
        patterns.add("/scheduler/selectTaskSchedulerLogListPage");//获取场景执行日志
        return patterns;
    }
}
