package com.dhlk.light.factoryconstruction.datamap;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 打印上报命令行设备Map
 * @author yangfan
 * @since 2021-08-19
 */
public class PrintReportCommandDeviceMap {

    private PrintReportCommandDeviceMap(){

    }

    private static final ConcurrentMap<Integer, ConcurrentHashMap<String,LocalDateTime>> PRINT_REPORT_COMMAND_DEVICE_MAP = new ConcurrentHashMap<>();


    /**
     * 放入打印命令行设备数据
     * @param port 监听端口
     * @param sn 设备sn号
     * @param expireTime 过期时间
      */
    public static void put(Integer port, String sn,LocalDateTime expireTime){
        ConcurrentHashMap<String, LocalDateTime> deviceMap = PRINT_REPORT_COMMAND_DEVICE_MAP.get(port);
        if(deviceMap==null){
            deviceMap = new ConcurrentHashMap<>();
        }
        deviceMap.put(sn,expireTime);
        PRINT_REPORT_COMMAND_DEVICE_MAP.put(port,deviceMap);
    }


    /**
     * 得到设备打印命令行的过期时间
     * @param port 端口
     * @param sn 设备sn
     * @return 打印命令行的过期时间
     */
    public static LocalDateTime getExpireTime(Integer port,String sn){
        LocalDateTime expireTime = null;
        ConcurrentMap<String, LocalDateTime> deviceMap = PRINT_REPORT_COMMAND_DEVICE_MAP.get(port);
        if(deviceMap!=null){
            expireTime = deviceMap.get(sn);
        }
        return expireTime;
    }


}
