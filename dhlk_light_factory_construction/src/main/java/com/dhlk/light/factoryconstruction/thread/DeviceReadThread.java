package com.dhlk.light.factoryconstruction.thread;

import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.handler.SendCommandHelper;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;

/**
 * 设备信息读取线程对象
 *
 * @author wzx
 * @date 2021/8/26
 */
@Slf4j
public class DeviceReadThread implements Callable<Boolean> {
    /**
     * 设备信息
     */
    private LedData ledData;

    public DeviceReadThread(LedData ledData) {
        this.ledData = ledData;
    }

    @Override
    public Boolean call() {
        try {
            ledData.setReadFlag(DeviceReadEnum.LOADING.getCode());
            //获取wifi配置
            if (!DeviceReadEnum.FINISH.getCode().equals(ledData.getWifiReadFlag())
            ) {
                List<SendCommandVO> result = SendCommandHelper.getWifiConfig(ledData.getSn());
                log.debug("{}->获取wifi配置命令执行结果-> {}",
                        ledData.getSn(),
                        Optional.ofNullable(result).map(r -> r.get(0).getMessage()).orElse("没有反馈"));
            }
            //获取人感配置
            do {
                Thread.sleep(100);
            } while (!DeviceReadEnum.FINISH.getCode().equals(ledData.getWifiReadFlag()));
            if (!DeviceReadEnum.FINISH.getCode().equals(ledData.getHumanConfigReadFlag())) {
                List<SendCommandVO> result = SendCommandHelper.humanFellConfig(ledData.getSn());
                log.debug("{}->获取人感配置命令执行结果-> {}",
                        ledData.getSn(),
                        Optional.ofNullable(result).map(r -> r.get(0).getMessage()).orElse("没有反馈"));
            }
            //获取光感配置
            do {
                Thread.sleep(100);
            } while (!DeviceReadEnum.FINISH.getCode().equals(ledData.getHumanConfigReadFlag()));
            if (!DeviceReadEnum.FINISH.getCode().equals(ledData.getLightConfigReadFlag())) {
                List<SendCommandVO> result = SendCommandHelper.obtainLightFeel(ledData.getSn());
                log.debug("{}->获取光感配置命令执行结果-> {}",
                        ledData.getSn(),
                        Optional.ofNullable(result).map(r -> r.get(0).getMessage()).orElse("没有反馈"));
            }
            //获取固件版本
            do {
                Thread.sleep(100);
            } while (!DeviceReadEnum.FINISH.getCode().equals(ledData.getLightConfigReadFlag()));
            if (!DeviceReadEnum.FINISH.getCode().equals(ledData.getVersionReadFlag())) {
                List<SendCommandVO> result = SendCommandHelper.getFirmwareVersion(ledData.getSn());
                log.debug("{}->获取固件版本命令执行结果-> {}",
                        ledData.getSn(),
                        Optional.ofNullable(result).map(r -> r.get(0).getMessage()).orElse("没有反馈"));
            }
            //获取上报时隙  时隙立即发送经常没有返回 多等待一会
            do {
                Thread.sleep(500);
            } while (!DeviceReadEnum.FINISH.getCode().equals(ledData.getVersionReadFlag()));
            if (!DeviceReadEnum.FINISH.getCode().equals(ledData.getReportTimeSlotReadFlag())) {
                List<SendCommandVO> result = SendCommandHelper.obtainTimeSlot(ledData.getSn());
                log.debug("{}->获取上报时隙命令执行结果-> {}",
                        ledData.getSn(),
                        Optional.ofNullable(result).map(r -> r.get(0).getMessage()).orElse("没有反馈"));
            }
            //全部完成
            do {
                Thread.sleep(100);
            } while (!DeviceReadEnum.FINISH.getCode().equals(ledData.getReportTimeSlotReadFlag()));
        } catch (InterruptedException e) {
            log.error("初始化设备: {} 任务被中断", ledData.getSn());
        } catch (Exception e) {
            log.error("初始化设备: {} 数据失败", ledData.getSn(), e);
        }
        return DeviceReadEnum.FINISH.getCode().equals(ledData.getReportTimeSlotReadFlag());
    }
}