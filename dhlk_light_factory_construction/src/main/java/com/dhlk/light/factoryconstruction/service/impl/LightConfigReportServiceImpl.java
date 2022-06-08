package com.dhlk.light.factoryconstruction.service.impl;

import com.dhlk.light.factoryconstruction.annotation.DeviceReportType;
import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.dhlk.light.factoryconstruction.enums.CommandTypeEnum.LIGHT_CONFIG_REPORT;

/**
 * 光感配置上报信息处理
 *
 * @author wzx
 * @since 2021-08-17
 */
@Slf4j
@Service
@DeviceReportType(type = LIGHT_CONFIG_REPORT)
public class LightConfigReportServiceImpl implements DeviceReportService {

    @Override
    public void handle(LedData ledData, DeviceReportData deviceReportData, String port) {
        ledData.setLightFeelConfigData((LightFeelConfigData) deviceReportData.getReportDetailData());
        //设置为读取完毕
        ledData.setLightConfigReadFlag(DeviceReadEnum.FINISH.getCode());
        log.debug("{}-> 光感配置被动上报",ledData.getSn());
        WebsocketServerUtil.sendRealTimeMessage(
                port,
                MessageTypeEnum.TYPE_REAL_TIME_DEVICE.getType(),
                ledData);
    }
}
