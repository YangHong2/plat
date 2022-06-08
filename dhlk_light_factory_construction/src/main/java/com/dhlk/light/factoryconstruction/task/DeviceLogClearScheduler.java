package com.dhlk.light.factoryconstruction.task;

import com.dhlk.light.factoryconstruction.mapper.DeviceLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 设备日志清理定时任务
 * @author yangfan
 * @since 2021-08-24
 */
@Component
@Slf4j
public class DeviceLogClearScheduler {

    /**
     * 命令日志保留分钟
     */
    @Value("${device.lightstatus.keepminutes}")
    public Integer keepMinutes;

    /**
     * 设备日志保留天数
     */
    @Value("${device.data.keepdays}")
    public Integer keepDays;

    @Resource
    private DeviceLogMapper deviceLogMapper;

    /**
     * 设备照明状态数据清理
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void deviceLightStatusLogClear() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime clearTime = now.minusMinutes(keepMinutes);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Integer deleteCount = deviceLogMapper.deleteLightStatusData(dateTimeFormatter.format(clearTime));
        log.debug("清理设备照明状态日志完成，删除条数：{}",deleteCount);
    }

    /**
     * 设备日志数据清理
     */
    @Scheduled(cron = "0 0 */1 * * ?")
    public void deviceLogClear() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime clearTime = now.minusDays(keepDays);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Integer deleteCount = deviceLogMapper.deleteData(dateTimeFormatter.format(clearTime));
        log.debug("清理设备日志完成，删除条数：{}",deleteCount);
    }
}
