package com.dhlk.light.factoryconstruction.handler.command.receive;

import com.alibaba.fastjson.JSON;
import com.dhlk.light.factoryconstruction.command.receive.ReportDetailDataHander;
import com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.CommandQueue;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceReportData;
import com.dhlk.light.factoryconstruction.pojo.entity.ReportDetailData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 命令接收处理类
 * @author yangfan
 * @since 2021-08-12
 */
@Slf4j
@Component
public class NomalCommandReceiveHander implements CommandReceiveHander<DeviceReportData>,ApplicationContextAware {

    private static final Map<CommandTypeEnum,ReportDetailDataHander> DATA_HANDER_MAP = new HashMap<>();

    private NomalCommandReceiveHander(){

    }

    /**
     * 接收消息
     * @param commandStr 消息字符串
     * @param socketHandler socket
     * @return 消息处理完成返回
     */
    @Override
    public  DeviceReportData receiveCommand(String commandStr, SocketHandler socketHandler) {
        log.debug("命令command:{}",commandStr);

        //得到协议原始数据对象
        DeviceOriginalProtocol deviceOriginalProtocol = getDeviceOriginalProtocol(commandStr);

        DeviceReportData deviceReportData = new DeviceReportData();
        deviceReportData.setId(deviceOriginalProtocol.getId());
        deviceReportData.setDeviceType(DataUtil.getLongStr(deviceOriginalProtocol.getDeviceType()));
        deviceReportData.setCommandType(deviceOriginalProtocol.getCommandType());

        deviceReportData.setDataTime(getDataDateTime(deviceOriginalProtocol.getTimestamp()));

        //处理数据
        ReportDetailData reportDetailData = protocolDataHander(deviceOriginalProtocol,deviceReportData);

        deviceReportData.setReportDetailData(reportDetailData);

        return deviceReportData;
    }

    /**
     * 获取数据时间
     * @param timestamp 16进制时间戳字符串
     * @return 数据时间
     */
    private static LocalDateTime getDataDateTime(String timestamp){
        BigInteger bigint=new BigInteger(timestamp, 16);
        long numb=bigint.longValue();

        Instant instant = Instant.ofEpochMilli(numb);
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

    /**
     * 解析命令字符串成为 得到协议原始数据对象
     * 测试 ： a5 a5 01 ff ff 01 20 20 50 00 59 62 00 00 00 00 00 00 00 00 ff ff ff ff 12 00 03 92 16 00 00 01 87 00 01 49 e1 00 01 fa fe 01 32 8b 00 5a 5a
     * @param command 命令字符串
     * @return 设备协议对象
     */
    public  DeviceOriginalProtocol getDeviceOriginalProtocol(String command) {

        CommandQueue commandQueue = new CommandQueue(command);
        DeviceOriginalProtocol deviceProtocol = new DeviceOriginalProtocol();

        //包头  a5 a5
        deviceProtocol.setHead(commandQueue.next(2));

        //版本 01
        deviceProtocol.setVersion(commandQueue.next(1));
        //寻址类型 ff
        deviceProtocol.setAddressingType(DataUtil.getLongStr(commandQueue.next(1)));

        //组id ff
        deviceProtocol.setGroupId(commandQueue.next(1));

        //设备id  01 20 20 50 00 59 62
        deviceProtocol.setId(commandQueue.next(7));

        //设备类型 00
        deviceProtocol.setDeviceType(DataUtil.getLongStr(commandQueue.next(1)));

        //命令类型 00
        String commandType = commandQueue.next(1);
        deviceProtocol.setCommandType(commandType);

        //时间戳 00 00 00 00 00 00
        String timestamp = commandQueue.next(6);
        deviceProtocol.setTimestamp(timestamp);

        //预留 ff ff ff ff
        String reserved =commandQueue.next(4);
        deviceProtocol.setReserved(reserved);

        //数据长度 12
        String dataLength =  commandQueue.next(1);
        deviceProtocol.setDataLength(dataLength);

        //10进制数据长度
        int dataLengthTen = Integer.parseInt(DataUtil.getLongStr(dataLength));
        deviceProtocol.setData(commandQueue.next(dataLengthTen));

        //校验位 fa 8b
        String checkDigit =commandQueue.next(1);
        deviceProtocol.setCheckDigit(checkDigit);

        //00 5a 5a
        String packageEnd = commandQueue.next(3);
        deviceProtocol.setPackageEnd(packageEnd);

        String jsonstr = JSON.toJSONString(deviceProtocol);
        log.debug("原始协议数据:{}",jsonstr);
        return deviceProtocol;
    }

    /**
     * 数据处理
     * @param deviceOriginalProtocol 原始协议
     * @param deviceReportData 上报数据
     * @return 上报数据详情
     */
    public  ReportDetailData protocolDataHander(DeviceOriginalProtocol deviceOriginalProtocol, DeviceReportData deviceReportData){
        String commandType = deviceReportData.getCommandType();

        CommandTypeEnum commandTypeEnum = CommandTypeEnum.getCommandTypeEnum(commandType);

        if(commandTypeEnum==null){
            log.error("commandType错误:{}",commandType);
            return null;
        }
        ReportDetailDataHander reportDetailDataHander = DATA_HANDER_MAP.get(commandTypeEnum);
        return reportDetailDataHander.doProtocolDataHander(deviceOriginalProtocol);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, ReportDetailDataHander> stringReportDetailDataHanderMap = applicationContext.getBeansOfType(ReportDetailDataHander.class);
        Collection<ReportDetailDataHander> dataHanderList = stringReportDetailDataHanderMap.values();
        for(ReportDetailDataHander reportDetailDataHander : dataHanderList){
            DATA_HANDER_MAP.put(reportDetailDataHander.getCommandType(),reportDetailDataHander);
        }
    }

    @Override
    public CommandReceiveHanderEnum getCommandReceiveHanderEnum() {
        return CommandReceiveHanderEnum.NOMAL;
    }
}
