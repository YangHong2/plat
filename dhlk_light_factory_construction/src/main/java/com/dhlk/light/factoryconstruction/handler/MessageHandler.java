package com.dhlk.light.factoryconstruction.handler;

/**
 * 处理消息
 * @author wzx
 * @date 2021/8/9 17:04
 */
public interface MessageHandler {

    /**
     * 获得消息的处理函数
     *
     * @param port    端口号
     * @param message 接收到的消息
     */
    void onReceive(String port, String message);
}