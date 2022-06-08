package com.dhlk.light.factoryconstruction.datamap;

import com.dhlk.light.factoryconstruction.mapper.DeviceHisIpMapper;
import com.dhlk.light.factoryconstruction.pojo.bo.DeviceHisIpBO;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceHisIp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 记录历史ip
 * @author yangfan
 * @since 2021-08-18
 */
@Component
public class HisIpMap implements CommandLineRunner {

    private static final ConcurrentMap<String, String> DEVICE_HIS_IP_MAP = new ConcurrentHashMap<>();

    private static DeviceHisIpMapper deviceHisIpMapper;

    @Autowired
    public void setDeviceHisIpMap(DeviceHisIpMapper deviceHisIpMapper) {
        HisIpMap.deviceHisIpMapper = deviceHisIpMapper;
    }

    /**
     * 初始化执行
     * @param args 参数
     */
    @Override
    public void run(String... args) {
        List<DeviceHisIpBO> deviceHisIpList = deviceHisIpMapper.selectDeviceLastIp();

        for(DeviceHisIpBO deviceHisIpBO : deviceHisIpList){
            DEVICE_HIS_IP_MAP.put(deviceHisIpBO.getSn(),deviceHisIpBO.getHisIp());
        }
    }

    /**
     * 放入一个设备对应ip
     * @param sn 设备唯一标识
     */
    public  static void put(String sn, String ip){
        String currentIp = DEVICE_HIS_IP_MAP.get(sn);

        //如果ip没有变化直接返回
        if(ip.equals(currentIp)){
            return;
        }

        DEVICE_HIS_IP_MAP.put(sn,ip);
        //插入数据库
        DeviceHisIp deviceHisIp = new DeviceHisIp();
        LocalDateTime now = LocalDateTime.now();
        deviceHisIp.setCreateTime(now);
        deviceHisIp.setUpdateTime(now);
        deviceHisIp.setHisIp(ip);
        deviceHisIp.setSn(sn);
        deviceHisIpMapper.insert(deviceHisIp);
    }

}
