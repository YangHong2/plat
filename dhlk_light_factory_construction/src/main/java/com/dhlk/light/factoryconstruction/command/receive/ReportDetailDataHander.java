package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;

/**
 * 上报数据处理
 *
 */
public interface ReportDetailDataHander {




    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol);

    /**
     * 获取处理类型
     * @return 处理类型
     */
    CommandTypeEnum getCommandType();

    
}
