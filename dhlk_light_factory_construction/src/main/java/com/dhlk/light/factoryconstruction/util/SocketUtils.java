package com.dhlk.light.factoryconstruction.util;

import com.dhlk.light.factoryconstruction.service.SocketServer;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * socket工具类
 *
 * @author wzx
 * @date 2021/8/10
 */
@Slf4j
public class SocketUtils {
    /**
     * 存放创建的socket服务端
     */
    public final static ConcurrentMap<String, SocketServer> SOCKET_SERVER_MAP = new ConcurrentHashMap<>();

    /**
     * 创建socket服务端
     *
     * @param port 端口号
     */
    public static synchronized void createServer(String port) {
        if (!SOCKET_SERVER_MAP.containsKey(port)) {
            SocketServer socketServer = new SocketServer(port);
            socketServer.start();
            SOCKET_SERVER_MAP.put(port, socketServer);
        }
    }

    /**
     * 关闭socket服务端
     *
     * @param port 端口号
     */
    public static synchronized void closeServer(String port) {
        if (SOCKET_SERVER_MAP.containsKey(port)) {
            SOCKET_SERVER_MAP.get(port).close();
        }
    }

    /**
     * 将接收到的数据转换为16进制字符串
     *
     * @param bytes byte数组
     * @return 字符串
     */
    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            String hex = Integer.toHexString(0xFF & aByte);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * 是否正在监听
     *
     * @param port 端口号
     * @return 是否
     */
    public static boolean isRunning(String port) {
        return SOCKET_SERVER_MAP.containsKey(port);
    }

}
