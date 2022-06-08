package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.TimeSlotData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 获取实时上报时隙
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class TimeSlotReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("实时上报时隙数据上报处理:{}",data);
        TimeSlotData timeSlotData = new TimeSlotData();
        String timeSlot = DataUtil.getLongStr(data.substring(0, 2));
        timeSlotData.setTimeValue(timeSlot);
        //删除设备发送记录
        DeviceCommandCheckMap.remove(deviceOriginalProtocol.getId(),CommandTypeEnum.OBTAIN_TIME_SLOT.getCommandType());
        return timeSlotData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.TIME_SLOT_REPORT;
    }
}
