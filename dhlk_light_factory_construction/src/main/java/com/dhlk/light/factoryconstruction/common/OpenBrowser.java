package com.dhlk.light.factoryconstruction.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 服务启动完成以后打开浏览器
 * @author yangfan
 * @since 2021-08-27
 */
@Component
@Slf4j
public class OpenBrowser implements CommandLineRunner {
    @Value("${open.browser.url}")
    private String url;

    @Override
    public void run(String... args){
        try {
            //可以指定自己的路径
            Runtime.getRuntime().exec("cmd   /c   start   " + url);
        } catch (Exception ex) {
            log.error("打开浏览器失败:",ex);
        }
    }
}