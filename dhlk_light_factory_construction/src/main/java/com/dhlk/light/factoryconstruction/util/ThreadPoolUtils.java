package com.dhlk.light.factoryconstruction.util;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * 线程池
 *
 * @author wzx
 * @date 2021/8/10
 */
public enum ThreadPoolUtils {
    /**
     *
     */
    INSTANCE;
    /**
     *
     */
    private final ExecutorService executorService;
    /**
     * 日志
     */
    private final Logger log = LoggerFactory.getLogger(ThreadPoolUtils.class);


    ThreadPoolUtils() {
        /*核心线程数大小*/
        //final int corePoolSize = Runtime.getRuntime().availableProcessors() + 1;
        //暂时设置成1000 预计最多同时创建1000个连接
        final int corePoolSize = 1000;
        /*最大线程数大小,阻塞队列占满后会在线程池中继续开启新的线程,总的线程数量不会大于最大线程数*/
        final int maxPoolSize = corePoolSize * 2;
        /*最大线程数空闲后的存活时间*/
        final long keepAliveTime = 1L;
        /*最大线程数空闲后的存活时间的单位*/
        final TimeUnit timeUnit = TimeUnit.MINUTES;
        /*阻塞队列,核心线程数占满后会把新入线程放入阻塞队列 */
        final LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(300);
        /*拒绝策略
        CallerRunsPolicy 最大线程数占满之后会执行次策略,由调用线程（提交任务的线程）处理该任务
        AbortPolicy 丢弃任务并抛出RejectedExecutionException异常
        DiscardPolicy 丢弃任务，不抛异常
        DiscardOldestPolicy 丢弃队列中最老的一个请求，也就是即将被执行的一个任务，并尝试再次提交当前任务*/
        final ThreadPoolExecutor.AbortPolicy abortPolicy = new ThreadPoolExecutor.AbortPolicy();
        ThreadFactoryBuilder threadFactoryBuilder = ThreadFactoryBuilder.create();
        threadFactoryBuilder.setUncaughtExceptionHandler((t, e) -> log.error("通用线程池: {} 异常  ", t.getName(), e));
        ThreadFactory namedThreadFactory = threadFactoryBuilder.setNamePrefix("common-pool-").build();
        //上述参数按照cpu密集型系统设置;io密集型的系统可以根据系统业务来设置
        //线程池参数设置详见 [io密集型与cpu密集型](https://blog.csdn.net/weixin_40151613/article/details/81835974)
        executorService = new ThreadPoolExecutor(corePoolSize, maxPoolSize,
                keepAliveTime, timeUnit, workQueue, namedThreadFactory, abortPolicy);
    }

    private ExecutorService getInstance() {
        return executorService;
    }

    /**
     * 需要获取返回值调用此方法
     *
     * @param callable 任务
     * @return java.util.concurrent.Future<T>
     */
    public static <T> Future<T> submit(Callable<T> callable) {
        return INSTANCE.getInstance().submit(callable);
    }


    /**
     * 不需要获取返回值调用此方法
     *
     * @param runnable 任务
     */
    public static void execute(Runnable runnable) {
        INSTANCE.getInstance().execute(runnable);
    }
}
