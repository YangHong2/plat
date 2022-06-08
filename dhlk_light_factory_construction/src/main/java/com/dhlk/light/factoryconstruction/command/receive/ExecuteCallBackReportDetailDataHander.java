package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.DeviceReadEnum;
import com.dhlk.light.factoryconstruction.enums.ExecuteCallResultEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.ExecuteCallBackData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 命令执行结果上报数据处理
 *
 * @author yangfan
 * @since 2021-08-13
 */
@Slf4j
@Component
public class ExecuteCallBackReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     *
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     * @see com.dhlk.light.factoryconstruction.enums.ExecuteCallResultEnum
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol) {

        String id = deviceOriginalProtocol.getId();
        String data = deviceOriginalProtocol.getData();
        log.info("executecallback执行反馈结果:{}", data);
        ExecuteCallBackData executeCallBackData = new ExecuteCallBackData();
        //命令类型
        String commandTypeStr = data.substring(0, 2);

        executeCallBackData.setCommandType(DataUtil.getLongStr(commandTypeStr));

        DeviceCommandCheckMap.remove(id, commandTypeStr);

        //执行结果
        String executeReuslt = data.substring(2, 4);

        //ExecuteCallResultEnum
        executeCallBackData.setExecuteReuslt(DataUtil.getLongStr(executeReuslt));
        //如果是修改命令并且执行成功，需要发送命令更新设备信息
        CommandTypeEnum commandTypeEnum = CommandTypeEnum.getCommandTypeEnum(commandTypeStr);
        //设备数据
        LedData ledData = LedPortMap.getLedDataById(id);
        if (commandTypeEnum != null
                && ledData != null
                && ExecuteCallResultEnum.SUCCESS.getResult().equals(executeCallBackData.getExecuteReuslt())) {
            switch (commandTypeEnum) {
                case SET_WIFI_CONFIG:
                    //设置wifi
                    ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setWifiReadFlag(DeviceReadEnum.READY.getCode());
                    break;
                case HUMAN_FEEL:
                    //设置人感
                    ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setHumanConfigReadFlag(DeviceReadEnum.READY.getCode());
                    break;
                case LIGHT_FEEL:
                    //设置光感
                    ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setLightConfigReadFlag(DeviceReadEnum.READY.getCode());
                    break;
                case REPORT_TIME_SLOT:
                    //设置实时上报时隙
                    ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setReportTimeSlotReadFlag(DeviceReadEnum.READY.getCode());
                    break;
                case REBOOT_LED:
                    //重启
                    ledData.setReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setWifiReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setHumanConfigReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setLightConfigReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setVersionReadFlag(DeviceReadEnum.READY.getCode());
                    ledData.setReportTimeSlotReadFlag(DeviceReadEnum.READY.getCode());
                    break;
                default:
                    break;
            }
        }

        return executeCallBackData;

    }


    /**
     * 获取处理类型
     *
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.EXECUTE_CALLBACK;
    }


}
