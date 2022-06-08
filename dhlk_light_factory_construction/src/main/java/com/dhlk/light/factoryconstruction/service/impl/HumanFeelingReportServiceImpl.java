package com.dhlk.light.factoryconstruction.service.impl;

import com.dhlk.light.factoryconstruction.annotation.DeviceReportType;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelData;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dhlk.light.factoryconstruction.enums.CommandTypeEnum.HUMAN_FEELING_REPORT;

/**
 * 人感状态主动上报信息处理
 *
 * @author wzx
 * @since 2021-08-17
 */
@Slf4j
@Service
@DeviceReportType(type = HUMAN_FEELING_REPORT)
public class HumanFeelingReportServiceImpl implements DeviceReportService {

    @Override
    public void handle(LedData ledData, DeviceReportData deviceReportData, String port) {
        ledData.setHumanFeelData((HumanFeelData) deviceReportData.getReportDetailData());
        log.debug("{}-> 人感状态主动上报",ledData.getSn());
        WebsocketServerUtil.sendRealTimeMessage(
                port,
                MessageTypeEnum.TYPE_REAL_TIME_DEVICE.getType(),
                ledData);
    }
}
