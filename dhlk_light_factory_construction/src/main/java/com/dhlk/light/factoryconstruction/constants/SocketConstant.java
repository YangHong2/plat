package com.dhlk.light.factoryconstruction.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 常量类
 *
 * @author wzx
 * @date 2021/8/10
 */
@Component
public class SocketConstant {

    /**
     * 心跳频率 10s都没交互过的连接剔除出去
     */
    public static final long HEART_RATE = 10 * 1000;

    /**
     * 最多监听1000个socket线程，超过的直接拒绝
     */
    public static final int MAX_SOCKET_THREAD_NUM = 1000;
    /**
     * 重试次数：3
     */
    public static final int RETRY_COUNT = 3;
    /**
     * 端口号
     */
    public static final String PORT = "port";
    /**
     * 会话id
     */
    public static final String SESSION_ID = "sessionId";
    @Value("${device.read.retry-count}")
    private int deviceRetryCount;
    @Value("${device.read.max-time}")
    private int deviceMaxTime;
    @Value("${device.read.count}")
    public int deviceReadCount;
    /**
     * 读取重试次数
     */
    public static int retryCount;
    /**
     * 读取超时时间
     */
    public static int maxTime;
    /**
     * 读取设备信息线程数
     */
    public static int corePoolSize;

    @PostConstruct
    private void postConstruct() {
        retryCount = deviceRetryCount;
        maxTime = deviceMaxTime;
        corePoolSize = deviceReadCount;
    }
}
