package com.dhlk.light.factoryconstruction.datamap;

import com.dhlk.light.factoryconstruction.annotation.DeviceReportType;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * DeviceReportMap key 指令枚举类型, value 对应命令service实现类
 *
 * @author wzx
 * @date 2021/8/18
 */
@Component
public class DeviceReportMap implements ApplicationContextAware, InitializingBean {

    private Map<CommandTypeEnum, DeviceReportService> deviceReportMap = new HashMap<CommandTypeEnum, DeviceReportService>();

    private ApplicationContext applicationContext;

    @Override
    public void afterPropertiesSet() {
        Map<String, Object> beansWithAnnotation = this.applicationContext.getBeansWithAnnotation(DeviceReportType.class);
        beansWithAnnotation.forEach((k, v) -> {
            Class<?> deviceReportClass =  v.getClass();
            CommandTypeEnum type = deviceReportClass.getAnnotation(DeviceReportType.class).type();
            //将对象加入map中,type作为key
            this.deviceReportMap.put(type, (DeviceReportService) v);
        });

    }

    @Override
    public void setApplicationContext(@Nullable ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public DeviceReportService get(CommandTypeEnum type) {
        return this.deviceReportMap.get(type);
    }
}