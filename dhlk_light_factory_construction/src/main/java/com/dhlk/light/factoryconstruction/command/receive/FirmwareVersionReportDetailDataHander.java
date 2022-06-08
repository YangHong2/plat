package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.FirmwareVersionData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 固件版本回复处理
 * @author yangfan
 * @since 2021-08-19
 */
@Component
@Slf4j
public class FirmwareVersionReportDetailDataHander implements ReportDetailDataHander{

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol) {
        String data = deviceOriginalProtocol.getData();
        log.debug("固件版本上报处理:{}",data);

        FirmwareVersionData firmwareVersionData = new FirmwareVersionData();

        byte[] bytes = DataUtil.hexStringToBytes(data);
        String version = new String(bytes);
        version = version.replace("\0", "");

        firmwareVersionData.setVersion(version);
        //删除设备发送记录
        DeviceCommandCheckMap.remove(deviceOriginalProtocol.getId(),CommandTypeEnum.FIRMWARE_VERSION.getCommandType());
        return firmwareVersionData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.FIRMWARE_VERSION_REPORT;
    }
}
