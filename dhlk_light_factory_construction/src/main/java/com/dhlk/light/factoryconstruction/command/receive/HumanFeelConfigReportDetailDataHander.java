package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelConfigData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 人感配置
 * @author yangfan
 * @since 2021-08-13
 */
@Slf4j
@Component
public class HumanFeelConfigReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("人感配置上报处理:{}",data);

        HumanFeelConfigData humanFeelConfigData = new HumanFeelConfigData();
        //开关状态
        String status = data.substring(0, 2);
        humanFeelConfigData.setStatus(DataUtil.getLongStr(status));

        //触发延迟时间
        String triggerDelayTime = data.substring(2, 6);
        humanFeelConfigData.setTriggerDelayTime(DataUtil.getLongStr(triggerDelayTime));
        //未触发渐变时间
        String noTriggereFadeTime = data.substring(6,8);
        humanFeelConfigData.setNoTriggereFadeTime(DataUtil.getLongStr(noTriggereFadeTime));
        //人感未触发最低亮度
        String minBrightness = data.substring(8,10);
        humanFeelConfigData.setMinBrightness(DataUtil.getLongStr(minBrightness));
        //人感触发最高亮度
        String maxBrightness = data.substring(10,12);
        humanFeelConfigData.setMaxBrightness(DataUtil.getLongStr(maxBrightness));

        //删除设备发送记录
        DeviceCommandCheckMap.remove(deviceOriginalProtocol.getId(),CommandTypeEnum.HUMAN_FEELING_CONFIG_COMMAND.getCommandType());
        return humanFeelConfigData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.HUMAN_FEELING_CONFIG_REPORT;
    }


}
