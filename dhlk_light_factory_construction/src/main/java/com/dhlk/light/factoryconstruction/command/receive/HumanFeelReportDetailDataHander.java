package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 人感状态上报数据处理
 * @author yangfan
 * @since 2021-08-13
 */
@Slf4j
@Component
public class HumanFeelReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("人感上报处理:{}",data);
        HumanFeelData humanFeelData = new HumanFeelData();

        String humanFeelStatus = data.substring(0, 2);

        humanFeelData.setHumanFeelStatus(DataUtil.getLongStr(humanFeelStatus));
        return humanFeelData;
    }


    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.HUMAN_FEELING_REPORT;
    }
}
