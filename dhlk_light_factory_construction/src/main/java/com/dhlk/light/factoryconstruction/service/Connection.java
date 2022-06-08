package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.constants.SocketConstant;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * 用于检测重试次数
 *
 * @author wzx
 * @date 2021/8/10
 */
@Slf4j
@Data
public class Connection {
    /**
     * 当前的socket连接实例
     */
    private Socket socket;
    /**
     * 当前连接线程
     */
    private SocketHandler socketHandler;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 最后一次更新时间，用于判断心跳
     */
    private LocalDateTime lastOnTime;

    public Connection(Socket socket, SocketHandler socketHandler) {
        this.socket = socket;
        this.socketHandler = socketHandler;
    }

    /**
     * 心跳超时，进行重试
     */
    void retry() {
        int count = 0;
        do {
            try {
                socket.setSendBufferSize(1);
                //更新交互时间
                this.setLastOnTime(LocalDateTime.now());
                break;
            } catch (IOException e) {
                count++;
                if (count >= SocketConstant.RETRY_COUNT) {
                    log.error("客户端{}:{} 心跳超时,重试失败:",
                            socket.getInetAddress().getHostAddress(), socket.getPort(), e);
                    //超过重试次数，说明socket客户端异常，关闭连接
                    this.socketHandler.stopRunning();
                }
            }
            //间隔一秒再尝试
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error("重连socket客户端错误:", e);
            }
        } while (count < 3);
    }
}