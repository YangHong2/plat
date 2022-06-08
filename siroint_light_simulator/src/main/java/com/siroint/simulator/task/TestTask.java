package com.siroint.simulator.task;

import com.siroint.simulator.annotation.CronScheduled;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author xmdeng
 * @date 2021/8/2 15:14
 */
@Component
@Slf4j
@EnableScheduling
public class TestTask {

    @CronScheduled(cron = "0/30 * * * * ?")
    public void testRunTask(){
        log.info("测试运行：{}", LocalDateTime.now());
    }
}
