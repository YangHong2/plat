package com.dhlk.light.factoryconstruction.datamap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 记录命令检查
 * @author yangfan
 * @since 2021-08-18
 */
@Component
public class DeviceCommandCheckMap {

    /**
     * 设备检查过期秒数
     */
    public static Integer deviceCommandCheckExpireSeconds;

    @Value("${device.command.checkexpireseconds}")
    public  void setDeviceCommandCheckExpireSeconds(Integer deviceCommandCheckExpireSeconds) {
        DeviceCommandCheckMap.deviceCommandCheckExpireSeconds = deviceCommandCheckExpireSeconds;
    }

    private DeviceCommandCheckMap(){

    }
    /**
     * 存放服务端端口号 设备 map
     */
    private static final ConcurrentMap<String,LocalDateTime > SN_COMMAND_MAP = new ConcurrentHashMap<>();

    private static final String SPLIT = "_";

    /**
     * 检查命令是否在执行中，如果在执行中则返回false,如果未在执行中返回true,且将数据放入list
     * @param id 设备id
     * @param checkCommandType 命令类型
     * @return 命令是否在执行中
     */
    public static boolean checkAndAdd(String id, String checkCommandType){
        String key = id + SPLIT + checkCommandType;
        boolean commandExecuteFlag = false;
        if(SN_COMMAND_MAP.containsKey(key)){
            LocalDateTime commandExpireTime = SN_COMMAND_MAP.get(key);
            if(LocalDateTime.now().isAfter(commandExpireTime)){
                commandExecuteFlag =true;
            }
        }else{
            commandExecuteFlag =true;
            SN_COMMAND_MAP.put(key,LocalDateTime.now().plusSeconds(deviceCommandCheckExpireSeconds));
        }
        return commandExecuteFlag;
    }

    /**
     * 删除一个设备命令
     * @param id 设备唯一标识
     */
    public static void remove(String id,String commandType){
        String key = id + SPLIT + commandType;
        SN_COMMAND_MAP.remove(key);
    }
}
