package com.dhlk.light.factoryconstruction.datamap;

import com.dhlk.light.factoryconstruction.pojo.vo.MessageVO;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;


/**
 * 所有端口的websocket信息map key:服务端端口号 value：消息set集合
 *
 * @author wzx
 * @since 2021-08-23
 */
public enum MessagePortMap {
    /**
     * 实例
     */
    INSTANCE;
    /**
     * 存放服务端端口号 设备 map
     */
    private static final ConcurrentMap<String, List<MessageVO>> MSG_MAP = new ConcurrentHashMap<>();

    /**
     * 放入一个消息
     *
     * @param port 服务端端口号
     * @param msg  消息对象
     */
    public static void put(String port, MessageVO msg) {
        List<MessageVO> list = MessagePortMap.get(port);
        list.add(msg);
        MSG_MAP.put(port, list);
    }

    /**
     * 删除一个服务端端口号对应的所有消息
     *
     * @param port 端口号
     */
    public static void remove(String port) {
        if (port != null) {
            MSG_MAP.remove(port);
        }
    }

    /**
     * 获取一个端口号对应的消息集合，不存在就新建一个
     *
     * @param port 端口号
     * @return 设备列表map
     */
    public static List<MessageVO> get(String port) {
        List<MessageVO> list = Collections.synchronizedList(new LinkedList<>());
        //如果该端口号尚未存入，则put一个新集合
        MSG_MAP.putIfAbsent(port, list);
        return MSG_MAP.get(port);

    }

    /**
     * 返回map
     *
     * @return 消息端口号map
     */
    public ConcurrentMap<String, List<MessageVO>> getInstance() {
        return MSG_MAP;
    }

}
