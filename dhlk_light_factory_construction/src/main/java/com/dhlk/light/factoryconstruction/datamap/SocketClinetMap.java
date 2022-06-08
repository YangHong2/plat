package com.dhlk.light.factoryconstruction.datamap;

import cn.hutool.core.util.StrUtil;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * socker 客户端
 * @author yangfan
 * @since 2021-08-10
 */
public class SocketClinetMap {

    private SocketClinetMap(){

    }

    private static final ConcurrentMap<String, SocketHandler> SOCKET_CLIENT_MAP = new ConcurrentHashMap<>();

    /**
     * 放入一个设备对应客户端
     * @param id 设备唯一标识
     * @param socketHandler 客户端
      */
    public static void put(String id,SocketHandler socketHandler){
        SOCKET_CLIENT_MAP.put(id,socketHandler);
    }

    /**
     * 删除一个设备对应的客户端
     * @param id 设备唯一标识
     */
    public static void remove(String id){
        if (StrUtil.isNotEmpty(id)){
            SOCKET_CLIENT_MAP.remove(id);
        }
    }

    /**
     * 获取一个设备对应的客户端
     * @param id 设备唯一标识
     * @return 客户端
     */
    public static SocketHandler get(String id){
        return SOCKET_CLIENT_MAP.get(id);
    }

    /**
     * 获取tcp连接集合
     * @return
     */
    public static ConcurrentMap<String, SocketHandler> getSocketClient(){
        return SOCKET_CLIENT_MAP;
    }
}
