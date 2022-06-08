package com.dhlk.light.factoryconstruction.command.receive;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * wifi配置
 * @author yangfan
 * @since 2021-08-13
 */
@Slf4j
@Component
public class WifiConfigReportDetailDataHander implements ReportDetailDataHander {

    /**
     * 处理上报数据
     * @param deviceOriginalProtocol 原始协议
     * @return 报告详细数据
     */
    @Override
    public ReportDetailData doProtocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol){

        String data = deviceOriginalProtocol.getData();
        log.debug("wifi配置上报处理:{}",data);

        WifiConfigData wifiConfigData = new WifiConfigData();
        //模块
        String module = data.substring(0,2);
        wifiConfigData.setModule(DataUtil.getLongStr(module));
        //双频
        String dualFrequency = data.substring(2,4);
        wifiConfigData.setDualFrequency(DataUtil.getLongStr(dualFrequency));
        //ssid
        String ssid = data.substring(4,68);

        byte[] ssidBytes = DataUtil.hexStringToBytes(ssid);
        ssid = new String(ssidBytes);
        ssid = ssid.replace("\0", "");
        wifiConfigData.setSsId(ssid);
        //密码
        String password = data.substring(68,100);

        byte[] passwordBytes = DataUtil.hexStringToBytes(password);
        password = new String(passwordBytes);
        password = password.replace("\0", "");
        wifiConfigData.setPassword(password);
        //ip
        String ip = DataUtil.getLongStr(data.substring(100,102))+"."
                + DataUtil.getLongStr(data.substring(102,104))+"."
                + DataUtil.getLongStr(data.substring(104,106))+"."
                + DataUtil.getLongStr(data.substring(106,108));

        wifiConfigData.setIp(ip);
        //删除设备发送记录
        DeviceCommandCheckMap.remove(deviceOriginalProtocol.getId(),CommandTypeEnum.GET_WIFI_CONFIG.getCommandType());

        return wifiConfigData;
    }

    /**
     * 获取处理类型
     * @return 处理类型
     */
    @Override
    public CommandTypeEnum getCommandType() {
        return CommandTypeEnum.WIFI_CONFIG_REPORT;
    }


}

