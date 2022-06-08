package com.dhlk.light.factoryconstruction.service.impl;

import com.dhlk.light.factoryconstruction.annotation.DeviceReportType;
import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.TimeSlotData;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dhlk.light.factoryconstruction.enums.CommandTypeEnum.TIME_SLOT_REPORT;

/**
 * 上报时隙返回信息处理
 *
 * @author wzx
 * @since 2021-08-17
 */
@Slf4j
@Service
@DeviceReportType(type = TIME_SLOT_REPORT)
public class TimeSlotReportServiceImpl implements DeviceReportService {

    @Override
    public void handle(LedData ledData, DeviceReportData deviceReportData, String port) {
        ledData.setReportTimeSlot(((TimeSlotData) deviceReportData.getReportDetailData()).getTimeValue());
        //设置为读取完毕
        ledData.setReportTimeSlotReadFlag(DeviceReadEnum.FINISH.getCode());
        log.debug("{}-> 时隙被动上报",ledData.getSn());
        WebsocketServerUtil.sendRealTimeMessage(
                port,
                MessageTypeEnum.TYPE_REAL_TIME_DEVICE.getType(),
                ledData);
    }
}
