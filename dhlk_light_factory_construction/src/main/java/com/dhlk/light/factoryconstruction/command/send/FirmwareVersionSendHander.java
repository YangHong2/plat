package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取固件版本发送处理
 * @author yangfan
 * @since 2021-08-19
 */
@Component
@Slf4j
public class FirmwareVersionSendHander extends AbstractCommandSendHander {

    /**
     * 协议数据处理
     * @param protocol 协议
     * @param command 命令请求
     */
    @Override
    public void protocolDataHander(Protocol protocol, Command command) {
        log.debug("固件版本发送数据处理:{}",command);
        protocol.setDataLength((byte)0x01);
        byte[] dataBytes = new byte[1];
        //调试回显：1byte（00:不回显，01回显）
        dataBytes[0]=(byte)Integer.parseInt(DataUtil.strTo16("01",1),16);
        protocol.setData(dataBytes);
    }

    /**
     * 获取处理的命令类型列表
     * @return 处理的命令类型列表
     */
    @Override
    public List<CommandTypeEnum> getCommandTypeList() {
        List<CommandTypeEnum> commandList = new ArrayList<>();
        commandList.add(CommandTypeEnum.FIRMWARE_VERSION);
        return commandList;
    }
}
