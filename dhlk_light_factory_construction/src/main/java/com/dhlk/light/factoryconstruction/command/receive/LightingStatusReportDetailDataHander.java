package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightingStatusData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import org.springframework.stereotype.Component;

/**
 * 照明状态上报数据处理
 * @author yangfan
 * @since 2021-08-13
 */
@Component
public class LightingStatusReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){
        String data = deviceOriginalProtocol.getData();
        //智能灯具
        LightingStatusData lightingStatusData = new LightingStatusData();
        //00 03 92 16
        String voltage =  DataUtil.getDecimalStr(data.substring(0,8));
        lightingStatusData.setVoltage(voltage);

        //电流 00 00 01 87
        String electricCurrent = DataUtil.getDecimalStr(data.substring(8,16));
        lightingStatusData.setElectricCurrent(electricCurrent);

        //功率 00 01 49 e1
        String power = DataUtil.getDecimalStr(data.substring(16,24));
        lightingStatusData.setPower(power);

        //累计电能 00 01 fa fe
        String totalEnergy =  DataUtil.getDecimalStr(data.substring(24,32));
        lightingStatusData.setTotalEnergy(totalEnergy);

        //继电器状态 01
        String relayStatus = DataUtil.getLongStr(data.substring(32,34));
        lightingStatusData.setRelayStatus(relayStatus);

        //灯亮度 32
        String lampBrightness = DataUtil.getLongStr(data.substring(34,36));
        lightingStatusData.setLampBrightness(lampBrightness);

        return lightingStatusData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.LIGTH_STATUS_REPORT;
    }
}
