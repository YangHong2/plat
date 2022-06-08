package com.dhlk.light.factoryconstruction.handler;

import com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.command.receive.CommandReceiveHander;
import com.dhlk.light.factoryconstruction.pojo.entity.DeviceOriginalProtocol;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 协议帮助类
 * @author yangfan
 * @since 2021-08-10
 */
@Slf4j
@Component
public class ProtocolHelper implements ApplicationContextAware {

    private static final Map<CommandReceiveHanderEnum, CommandReceiveHander> COMMAND_RECEIVE_HANDER_MAP = new HashMap<>();

    private ProtocolHelper(){

    }

    public  static  final String COMMMAND_SPLIT =" ";

    public static String getCommandStr(DeviceOriginalProtocol protocol){

        String commandStr = "";
        if(protocol!=null){
            commandStr = plus(commandStr,protocol.getHead());
            commandStr = plus(commandStr,protocol.getVersion());
            commandStr = plus(commandStr,protocol.getAddressingType());
            commandStr = plus(commandStr,protocol.getGroupId());
            commandStr = plus(commandStr,protocol.getId());
            commandStr = plus(commandStr,protocol.getDeviceType());
            commandStr = plus(commandStr,protocol.getCommandType());
            commandStr = plus(commandStr,protocol.getTimestamp());
            commandStr = plus(commandStr,protocol.getReserved());
            commandStr = plus(commandStr,protocol.getDataLength());
            if(!StringUtils.isEmpty(protocol.getData())){
                commandStr = plus(commandStr,protocol.getData());
            }
            commandStr = plus(commandStr,protocol.getCheckDigit());
            commandStr = plus(commandStr,protocol.getPackageEnd());
        }
        return commandStr;
    }

    /**
     * 得到校验位字符串
     * @param protocol 原始协议
     * @return 校验位字符串
     */
    public static byte[] getCheckDigitStr(Protocol protocol){

        byte[] checkBytes = {protocol.getHead()[0], protocol.getHead()[1]
                , protocol.getVersion(), protocol.getAddressingType(), protocol.getGroupId(), protocol.getIdBytes()[0]
                , protocol.getIdBytes()[1], protocol.getIdBytes()[2], protocol.getIdBytes()[3], protocol.getIdBytes()[4]
                , protocol.getIdBytes()[5], protocol.getIdBytes()[6], protocol.getDeviceType(), protocol.getCommandType()
                , protocol.getTimestamp()[0], protocol.getTimestamp()[1], protocol.getTimestamp()[2], protocol.getTimestamp()[3]
                , protocol.getTimestamp()[4], protocol.getTimestamp()[5], protocol.getReserved()[0]
                , protocol.getReserved()[1], protocol.getReserved()[2], protocol.getReserved()[3]
                , protocol.getDataLength()};
        int length = checkBytes.length;
        byte[] data = protocol.getData();

        if(data!=null){
            checkBytes = Arrays.copyOf(checkBytes,length+data.length);
            System.arraycopy(data, 0, checkBytes, length, data.length);
        }
        return checkBytes;

    }


    /**
     * 得到校验位字符串
     * @param protocol 原始协议
     * @return 校验位字符串
     */
    public static byte[] getBytes(Protocol protocol){

        byte[] checkBytes = {protocol.getHead()[0], protocol.getHead()[1]
                , protocol.getVersion(), protocol.getAddressingType(), protocol.getGroupId(), protocol.getIdBytes()[0]
                , protocol.getIdBytes()[1], protocol.getIdBytes()[2], protocol.getIdBytes()[3], protocol.getIdBytes()[4]
                , protocol.getIdBytes()[5], protocol.getIdBytes()[6], protocol.getDeviceType(), protocol.getCommandType()
                , protocol.getTimestamp()[0], protocol.getTimestamp()[1], protocol.getTimestamp()[2], protocol.getTimestamp()[3]
                , protocol.getTimestamp()[4], protocol.getTimestamp()[5], protocol.getReserved()[0]
                , protocol.getReserved()[1], protocol.getReserved()[2], protocol.getReserved()[3]
                , protocol.getDataLength()};
        int length = checkBytes.length;
        byte[] data = protocol.getData();

        if(data!=null){
            checkBytes = Arrays.copyOf(checkBytes,length+data.length);
            System.arraycopy(data, 0, checkBytes, length, data.length);
        }

        length = checkBytes.length;
        checkBytes = Arrays.copyOf(checkBytes,checkBytes.length+4);
        checkBytes[length] = protocol.getCheckDigit();
        checkBytes[length+1] = protocol.getPackageEnd()[0];
        checkBytes[length+2] = protocol.getPackageEnd()[1];
        checkBytes[length+3] = protocol.getPackageEnd()[2];



        return checkBytes;

    }

    /**
     * 字符串相加
     * @param commandStr 命令字符串
     * @param data 数据
     * @return 字符串
     */
    private static String plus(String commandStr,String data){
        if(StringUtils.isEmpty(commandStr)){
            return data;
        }
        if(data==null){
            return commandStr;
        }
        return commandStr+COMMMAND_SPLIT+data;

    }

    /**
     * 得到命令处理类
     * @param commandStr 命令字符串
     * @return 命令处理类
     */
    public static CommandReceiveHander<?> getProtocolType(String commandStr){
        CommandReceiveHanderEnum  commandReceiveHanderEnum= null;
        commandStr = commandStr.replace(" ", "");
        if(commandStr.startsWith(CommandReceiveHanderEnum.FIRMWARE_UPGRADE.getCommandStartStr())){
            commandReceiveHanderEnum = CommandReceiveHanderEnum.FIRMWARE_UPGRADE;
        }else if(commandStr.startsWith(CommandReceiveHanderEnum.NOMAL.getCommandStartStr())){
            commandReceiveHanderEnum = CommandReceiveHanderEnum.NOMAL;
        }

        return COMMAND_RECEIVE_HANDER_MAP.get(commandReceiveHanderEnum);

    }


    /**
     * 得到命令类型描述
     * @param commandTypeByte 命令类型字符串
     * @return 命令类型描述
     */
    public static String getCommandTypeStr(byte commandTypeByte){
        String commandType = DataUtil.bytesToHex(commandTypeByte);
        log.info("commandType:"+commandType);
        CommandTypeEnum commandTypeEnum = CommandTypeEnum.getCommandTypeEnum(commandType);
        if(commandTypeEnum!=null){
            return commandTypeEnum.getDesc();
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CommandReceiveHander> stringCommandReceiveHanderMap = applicationContext.getBeansOfType(CommandReceiveHander.class);
        Collection<CommandReceiveHander> dataHanderList = stringCommandReceiveHanderMap.values();
        for(CommandReceiveHander commandReceiveHander : dataHanderList){
            COMMAND_RECEIVE_HANDER_MAP.put(commandReceiveHander.getCommandReceiveHanderEnum(),commandReceiveHander);
        }
    }


}
