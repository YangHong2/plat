package com.dhlk.light.factoryconstruction.service;

import com.dhlk.light.factoryconstruction.constants.SocketConstant;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.handler.MessageHandler;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.thread.ListeningThread;
import com.dhlk.light.factoryconstruction.util.SocketUtils;
import com.dhlk.light.factoryconstruction.util.ThreadPoolUtils;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * socket服务
 *
 * @author wzx
 * @date 2021/8/10
 */
@Slf4j
@Data
public class SocketServer {
    /**
     * socket服务端对象
     */
    private ServerSocket serverSocket;
    /**
     * 服务监听主线程
     */
    private ListeningThread listeningThread;
    /**
     * 消息处理器
     */
    private MessageHandler messageHandler;
    /**
     * 设备初始化线程池 每个服务端一个
     */
    private ExecutorService deviceReadExecutor;
    /**
     * 扫描已有的socket处理线程 每个服务端一个
     * 1. 没有的线程不引用
     * 2. 关注是否有心跳
     */
    private ScheduledExecutorService scheduleSocketMonitorExecutor = Executors
            .newSingleThreadScheduledExecutor(r -> new Thread(r, "socket-monitor-" + r.hashCode()));
    /**
     * 存储存活的socket客户端的线程
     */
    private List<SocketHandler> existConnectionThreadList = Collections.synchronizedList(new ArrayList<>());
    /**
     * 断开连接的,用于遍历的时候删除
     */
    private List<SocketHandler> noConnectionThreadList = Collections.synchronizedList(new ArrayList<>());

    public SocketServer(String port) {
        try {
            this.serverSocket = new ServerSocket(Integer.parseInt(port));
            this.listeningThread = new ListeningThread(this);
            ThreadFactory factory = new ThreadFactoryBuilder()
                    .setUncaughtExceptionHandler((t, e) -> log.error("服务端 {} ，设备初始线程池: {} 异常  ",
                            port, t.getName(), e))
                    .setNameFormat("init-pool-%d").build();
            //阻塞队列设置成无缓冲队列 线程池满了任务直接拒绝 下次主动上报时再初始化
            this.deviceReadExecutor = new ThreadPoolExecutor(SocketConstant.corePoolSize,
                    SocketConstant.corePoolSize,
                    SocketConstant.maxTime,
                    TimeUnit.SECONDS, new SynchronousQueue<>(),
                    factory,
                    new ThreadPoolExecutor.AbortPolicy());
        } catch (Exception e) {
            this.close();
            log.error("本地socket:{} 服务启动失败:", port, e);
        }
    }

    /**
     * 开启一个线程监听，定时线程池扫描无用连接剔除
     */
    public void start() {
        //监听
        ThreadPoolUtils.execute(listeningThread);
        //每隔5s扫一次ThreadList
        scheduleSocketMonitorExecutor.scheduleWithFixedDelay(() -> {
            //删除连接已被关闭的thread
            existConnectionThreadList.forEach(connectionThread -> {
                if (!connectionThread.isRunning()) {
                    noConnectionThreadList.add(connectionThread);
                } else {
                    //还在运行的线程需要判断心跳是否ok
                    long heartDuration = Duration.between(connectionThread.getConnection().getLastOnTime(), LocalDateTime.now()).toMillis();
                    if (heartDuration > SocketConstant.HEART_RATE) {
                        //心跳超时的剔除
                        noConnectionThreadList.add(connectionThread);
                    }
                }
            });
            //关闭已经断开连接的客户端
            //从线程池移除
            noConnectionThreadList.forEach(n -> {
                //从线程池移除
                n.getSocketServer().getExistConnectionThreadList().remove(n);
                n.stopRunning();
            });
            noConnectionThreadList.forEach(SocketHandler::stopRunning);
            noConnectionThreadList.clear();
        }, 0, 5, TimeUnit.SECONDS);
    }

    /**
     * 关闭本地socket服务端
     */
    public void close() {
        try {
            if (serverSocket != null) {
                //先关闭monitor线程池，防止遍历list的时候
                scheduleSocketMonitorExecutor.shutdownNow();
                //关闭设备数据初始线程池
                deviceReadExecutor.shutdownNow();
                //移除socket客户端
                if (listeningThread != null) {
                    listeningThread.stopRunning();
                }
                //关闭服务端
                if (!serverSocket.isClosed()) {
                    serverSocket.close();
                }
                //从map中移除
                LedPortMap.remove(serverSocket.getLocalPort() + "");
                SocketUtils.SOCKET_SERVER_MAP.remove(serverSocket.getLocalPort() + "");
                log.info("socket服务端 {}关闭成功", serverSocket.getLocalPort());
            }
        } catch (IOException e) {
            log.error("socket服务端关闭失败:", e);
        }
    }

}