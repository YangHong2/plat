package com.dhlk.light.factoryconstruction.service.impl;

import com.dhlk.light.factoryconstruction.annotation.DeviceReportType;
import com.dhlk.light.factoryconstruction.enums.MessageTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.service.DeviceReportService;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import org.springframework.stereotype.Service;

import static com.dhlk.light.factoryconstruction.enums.CommandTypeEnum.EXECUTE_CALLBACK;

/**
 * 执行反馈上报信息处理
 *
 * @author wzx
 * @since 2021-08-17
 */
@Service
@DeviceReportType(type = EXECUTE_CALLBACK)
public class ExecuteCallbackServiceImpl implements DeviceReportService {

    @Override
    public void handle(LedData ledData, DeviceReportData deviceReportData, String port) {
//        WebsocketServerUtil.sendRealTimeMessage(
//                port,
//                MessageTypeEnum.TYPE_REAL_TIME_EXECUTE.getType(),
//                deviceReportData.getReportDetailData());
    }
}
