package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 光感数据上报处理
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class LightFeelReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("光感数据上报处理:{}",data);
        LightFeelData lightFeelData = new LightFeelData();

        String lightFeelValue = data.substring(0, 4);

        lightFeelData.setLightFeelValue(DataUtil.getLongStr(lightFeelValue));
        return lightFeelData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.LIGHT_FEELING_REPORT;
    }
}
