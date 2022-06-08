package com.dhlk.light.factoryconstruction.thread;

import com.dhlk.light.factoryconstruction.constants.SocketConstant;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.service.SocketServer;
import com.dhlk.light.factoryconstruction.util.ThreadPoolUtils;
import lombok.extern.slf4j.Slf4j;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * 监听线程对象
 *
 * @author wzx
 * @date 2021/8/10
 */
@Slf4j
public class ListeningThread extends Thread {

    private final SocketServer socketServer;

    private final ServerSocket serverSocket;

    private boolean isRunning;

    public ListeningThread(SocketServer socketServer) {
        this.socketServer = socketServer;
        this.serverSocket = socketServer.getServerSocket();
        isRunning = true;
    }

    @Override
    public void run() {
        int count = 0;
        while (isRunning) {
            if (serverSocket.isClosed()) {
                isRunning = false;
                break;
            }
            try {
                log.info("socket服务端 port：{}，正在监听 第{}个客户端", serverSocket.getLocalPort(), ++count);
                Socket socket;
                socket = serverSocket.accept();
                if (socketServer.getExistConnectionThreadList().size() > SocketConstant.MAX_SOCKET_THREAD_NUM) {
                    //socket线程数量超过最大值
                    socket.close();
                    log.error("socket服务端 port:{},连接数量超过最大值", serverSocket.getLocalPort());
                }
                SocketHandler connectionThread = new SocketHandler(socket, socketServer);
                socketServer.getExistConnectionThreadList().add(connectionThread);
                ThreadPoolUtils.execute(connectionThread);
            } catch (Exception e) {
                if (isRunning){
                    log.error("socket服务端 port:{},监听线程运行失败:", serverSocket.getLocalPort(), e);
                }
            }
        }
        log.info("socket服务端 port:{}，监听结束", serverSocket.getLocalPort());
    }

    /**
     * 关闭当前服务端下所有socket客户端
     */
    public void stopRunning() {
        for (SocketHandler currentThread : socketServer.getExistConnectionThreadList()) {
            currentThread.stopRunning();
        }
        isRunning = false;
    }
} 