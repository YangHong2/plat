package com.dhlk.config;

import com.dhlk.Interceptor.AuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xmdeng
 * @date 2021/9/26 14:31
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public AuthenticationInterceptor authenticationInterceptor(){
        return new  AuthenticationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor()).excludePathPatterns(getExecutePathPattern());
    }

    public List<String> getExecutePathPattern(){
        List<String> patterns = new ArrayList<>();
        patterns.add("/login");
        patterns.add("/logout");
        patterns.add("/getTenantCode");
        return patterns;
    }
}
