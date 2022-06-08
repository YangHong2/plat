package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 光感配置处理
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class LightFeelConfigReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("光感配置上报处理:{}",data);

        LightFeelConfigData lightFeelConfigData = new LightFeelConfigData();
        //开关状态
        String onOff = data.substring(0, 2);
        lightFeelConfigData.setOnOff(DataUtil.getLongStr(onOff));
        //照度上限值
        lightFeelConfigData.setIllumiHighest(DataUtil.getLongStr(data.substring(2, 6)));
        //照度上限值对应亮度最小值
        lightFeelConfigData.setIllumiHighestMin(DataUtil.getLongStr(data.substring(6, 8)));
        //照度下限值
        lightFeelConfigData.setIllumiLowest(DataUtil.getLongStr(data.substring(8, 12)));
        //照度下限值对应亮度亮度最大值
        lightFeelConfigData.setIllumiLowestMax(DataUtil.getLongStr(data.substring(12, 14)));
        //应用模式选择
        lightFeelConfigData.setIllumiMode(DataUtil.getLongStr(data.substring(14, 16)));

        //删除设备发送记录
        DeviceCommandCheckMap.remove(deviceOriginalProtocol.getId(),CommandTypeEnum.OBTAIN_LIGHT_FEEL.getCommandType());
        return lightFeelConfigData;
    }

    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.LIGHT_CONFIG_REPORT;
    }


}
