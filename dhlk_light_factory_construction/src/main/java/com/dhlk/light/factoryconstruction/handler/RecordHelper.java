package com.dhlk.light.factoryconstruction.handler;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.dhlk.light.factoryconstruction.datamap.PrintReportCommandDeviceMap;
import com.dhlk.light.factoryconstruction.datamap.SocketClinetMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.MessageEnum;
import com.dhlk.light.factoryconstruction.mapper.DeviceDataInfoMapper;
import com.dhlk.light.factoryconstruction.mapper.DeviceLogMapper;
import com.dhlk.light.factoryconstruction.mapper.UpdateRecordMapper;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceDataInfo;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceLog;
import com.dhlk.light.factoryconstruction.pojo.entity.UpdateRecord;
import com.dhlk.light.factoryconstruction.pojo.entity.command.*;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.HumanFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.WifiConfigData;
import com.dhlk.light.factoryconstruction.pojo.vo.DebugMessageVO;
import com.dhlk.light.factoryconstruction.websocket.WebsocketServerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日志记录
 * @author yangfan
 * @since 2021-08-17
 */
@Slf4j
@Component
public class RecordHelper{


    private static DeviceDataInfoMapper deviceDataInfoMapper;

    private static UpdateRecordMapper updateRecordMapper;

    private static DeviceLogMapper deviceLogMapper;

    @Autowired
    public void setUpdateRecordMapper(UpdateRecordMapper updateRecordMapper) {
        RecordHelper.updateRecordMapper = updateRecordMapper;
    }

    @Autowired
    public void setDeviceDataInfoMapper(DeviceDataInfoMapper deviceDataInfoMapper) {
        RecordHelper.deviceDataInfoMapper = deviceDataInfoMapper;
    }

    @Autowired
    public void setDeviceLogMapper(DeviceLogMapper deviceLogMapper) {
        RecordHelper.deviceLogMapper = deviceLogMapper;
    }


    /**
     * 记录命令日志
     * @param commandTypeEnum 命令枚举
     * @param command 命令字符串
     * @param sn 设备sn号
     */
    public static void recordCommandLog(CommandTypeEnum commandTypeEnum,String command,String sn) {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //推送消息
        websocketMessagePush(commandTypeEnum,command,sn,dateTimeFormatter.format(now));

        DeviceLog commandLog = new DeviceLog();
        commandLog.setMessage(command);
        commandLog.setMessageType(commandTypeEnum.getMessageType());
        commandLog.setCreateTime(now);
        commandLog.setUpdateTime(now);
        commandLog.setSn(sn);
        commandLog.setCommandType(commandTypeEnum.getCommandType());

        deviceLogMapper.insert(commandLog);

    }
    /**
     * websockert消息推送
     * @param commandTypeEnum 命令类型枚举
     * @param command 命令
     * @param sn 设备sn
     * @param time 时间
     */
    private static void websocketMessagePush(CommandTypeEnum commandTypeEnum,String command,String sn,String time) {
        SocketHandler socketHandler = SocketClinetMap.get(sn);
        Socket socket = socketHandler.getSocket();
        int port = socket.getLocalPort();

        String commandType = commandTypeEnum.getCommandType();

        //推送消息标识
        boolean pushMessageFlag = false;

        //如果不为主动上报命令，则推送消息
        if(!MessageEnum.INITIATIVE_REPORT.getType().equals(commandTypeEnum.getMessageType())){
            pushMessageFlag =true;
        }else{//判定是否需要打印日志
            LocalDateTime expireTime = PrintReportCommandDeviceMap.getExpireTime(port, sn);
            if (expireTime != null && expireTime.isAfter(LocalDateTime.now())) {
                pushMessageFlag = true;
            }
        }
        if(pushMessageFlag){
            DebugMessageVO debugMessageVO = new DebugMessageVO();
            debugMessageVO.setMessage(command.replace(" ","" ));
            debugMessageVO.setSn(sn);
            debugMessageVO.setMessageType(commandTypeEnum.getMessageType());
            debugMessageVO.setCommandType(commandType);
            debugMessageVO.setTime(time);
            WebsocketServerUtil.websocketDebugMessagePush(String.valueOf(port), debugMessageVO);
        }
    }

    /**
     * 记录修改日志
     * @param command 命令
     */
    public static void recordUpdateLog(Command command){
        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();

        //设置wifi
        if(commandTypeEnum.equals(CommandTypeEnum.SET_WIFI_CONFIG)){
            //设置wifi日志
            wifiLog(command);
        }else if(commandTypeEnum.equals(CommandTypeEnum.BRIGHTNESS)){
            //设置灯亮度日志
            brightnessLog(command);
        }else if(commandTypeEnum.equals(CommandTypeEnum.LIGHT_FEEL)){
            //设置光感日志
            lightFeelLog(command);
        }else if(commandTypeEnum.equals(CommandTypeEnum.HUMAN_FEEL)){
            //设置人感日志
            humanFeelLog(command);
        }else if(commandTypeEnum.equals(CommandTypeEnum.REPORT_TIME_SLOT)){
            //记录设置实时上报时隙日志
            timeSlotLog(command);
        }

    }

    /**
     * 记录设置实时上报时隙日志
     * @param command 命令
     */
    private static void timeSlotLog(Command command) {
        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
        //设备唯一标识
        String id = command.getId();

        String before = getBefore(id,commandTypeEnum);

        CommandParam commandParam = command.getCommandParam();

        ReportTimeParam reportTimeParam = (ReportTimeParam) commandParam;

        insertUpdateRecord(id,commandTypeEnum.getCommandType(),before, reportTimeParam.getTimeSlot());
    }

    /**
     * 记录 设置人感日志
     * @param command 命令
     */
    private static void humanFeelLog(Command command) {

        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
        //设备唯一标识
        String id = command.getId();

        String before = getBefore(id,commandTypeEnum);

        CommandParam commandParam = command.getCommandParam();

        HumanFeelParam humanFeelParam = (HumanFeelParam) commandParam;

        HumanFeelConfigData humanFeelConfigData = new HumanFeelConfigData();
        humanFeelConfigData.setStatus(humanFeelParam.getHumanFlag());
        humanFeelConfigData.setTriggerDelayTime(humanFeelParam.getContinuedTime());
        humanFeelConfigData.setNoTriggereFadeTime(humanFeelParam.getGradientTime());
        humanFeelConfigData.setMinBrightness(humanFeelParam.getBrightnessLowest());
        humanFeelConfigData.setMaxBrightness(humanFeelParam.getBrightnessHighest());

        insertUpdateRecord(id,commandTypeEnum.getCommandType(),before, JSONUtil.toJsonStr(humanFeelConfigData));
    }

    /**
     * 记录 设置光感日志
     * @param command 命令
     */
    private static void lightFeelLog(Command command) {

        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
        //设备唯一标识
        String id = command.getId();

        String before = getBefore(id,commandTypeEnum);

        CommandParam commandParam = command.getCommandParam();

        LightFeelParam lightFeelParam = (LightFeelParam) commandParam;

        LightFeelConfigData lightFeelConfigData = new LightFeelConfigData();
        lightFeelConfigData.setIllumiHighest(lightFeelParam.getIllumiHighest());
        lightFeelConfigData.setIllumiHighestMin(lightFeelParam.getIllumiHighestMin());
        lightFeelConfigData.setIllumiLowest(lightFeelParam.getIllumiLowest());
        lightFeelConfigData.setIllumiLowestMax(lightFeelParam.getIllumiLowestMax());
        lightFeelConfigData.setIllumiMode(lightFeelParam.getIllumiMode());
        lightFeelConfigData.setOnOff(lightFeelParam.getOnOff());
        insertUpdateRecord(id,commandTypeEnum.getCommandType(),before, JSONUtil.toJsonStr(lightFeelConfigData));
    }


    /**
     * 记录 灯亮度日志
     * @param command 命令
     */
    private static void brightnessLog(Command command) {
        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
        //设备唯一标识
        String id = command.getId();

        String before = getBefore(id,commandTypeEnum);

        CommandParam commandParam = command.getCommandParam();

        BrightnessParam brightnessParam = (BrightnessParam) commandParam;

        insertUpdateRecord(id,commandTypeEnum.getCommandType(),before, String.valueOf(brightnessParam.getBrightness()));
    }

    /**
     * 记录 设置wifi日志
     * @param command 命令
     */
    private static void wifiLog(Command command) {
        //命令类型
        CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
        //设备唯一标识
        String id = command.getId();

        String before = getBefore(id,commandTypeEnum);

        CommandParam commandParam = command.getCommandParam();

        WifiParam wifiParam = (WifiParam) commandParam;
        WifiConfigData wifiConfigData = new WifiConfigData();
        wifiConfigData.setIp(wifiParam.getIp());
        wifiConfigData.setPassword(wifiParam.getPassword());
        wifiConfigData.setSsId(wifiParam.getSsId());
        wifiConfigData.setDualFrequency(wifiParam.getDualFrequency());
        wifiConfigData.setModule(wifiParam.getModule());
        insertUpdateRecord(id,commandTypeEnum.getCommandType(),before, JSONUtil.toJsonStr(wifiConfigData));
    }

    /**
     * 获取修改前数据
     * @param id 设备唯一标识
     * @param commandTypeEnum 命令类型
     * @return 修改前数据
     */
    private static String getBefore(String id, CommandTypeEnum commandTypeEnum) {

        DeviceDataInfo lastDeviceDataInfo = getLastDeviceDataInfo(id);

        String before = null;

        if(lastDeviceDataInfo!=null){
            //设置wifi
            if(commandTypeEnum.equals(CommandTypeEnum.SET_WIFI_CONFIG)){
                before = lastDeviceDataInfo.getWifiConfig();
            }else if(CommandTypeEnum.BRIGHTNESS.equals(commandTypeEnum)){//亮度值
                before = lastDeviceDataInfo.getBrightness();
            }else if(CommandTypeEnum.REPORT_TIME_SLOT.equals(commandTypeEnum)){//设置实时上报时隙
                before = lastDeviceDataInfo.getTimeSlot();
            }else if(CommandTypeEnum.HUMAN_FEEL.equals(commandTypeEnum)){//人感
                before = lastDeviceDataInfo.getHumanConfig();
            }else if(CommandTypeEnum.LIGHT_FEEL.equals(commandTypeEnum)){//光感
                before = lastDeviceDataInfo.getLightConfig();
            }
        }
        return before;
    }


    /**
     * 查询最新设备信息
     * @param id 设备id
     * @return 最新设备信息
     */
    private static DeviceDataInfo getLastDeviceDataInfo(String id) {
        LambdaQueryWrapper<DeviceDataInfo> queryDeviceInfoWrapper = Wrappers.lambdaQuery(DeviceDataInfo.class).
                eq(DeviceDataInfo::getSn, id);
        //查询设备信息
        return deviceDataInfoMapper.selectOne(queryDeviceInfoWrapper);
    }

    /**
     * 插入更新记录
     * @param id 设备唯一标识
     * @param commandType 命令类型
     * @param before 修改前
     */
    public static void insertUpdateRecord(String id,String commandType,String before,String after){
        UpdateRecord insertRecord = new UpdateRecord();
        insertRecord.setSn(id);
        insertRecord.setCommandType(commandType);
        insertRecord.setBefore(before);
        insertRecord.setAfter(after);
        LocalDateTime now = LocalDateTime.now();
        insertRecord.setCreateTime(now);
        insertRecord.setUpdateTime(now);
        int insert = updateRecordMapper.insert(insertRecord);
        log.info("插入更新记录表成功，插入条数:{}",insert);
    }
}
