package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 发送命令处理
 * 无参数发送处理类
 * @author yangfan
 * @since 2021-08-12
 */
@Slf4j
@Component
public  class NoParamCommandSendHander extends AbstractCommandSendHander {

   /**
    * 协议数据处理
    * @param protocol 协议
    * @param command 命令请求
    */
   @Override
   public void protocolDataHander(Protocol protocol, Command command) {
      protocol.setDataLength((byte)0x00);
   }


   @Override
   public List<CommandTypeEnum> getCommandTypeList() {
      List<CommandTypeEnum> commandList = new ArrayList<>();
      commandList.add(CommandTypeEnum.FLASHING_LIGHT);
      commandList.add(CommandTypeEnum.HUMAN_FEELING_CONFIG_COMMAND);
      commandList.add(CommandTypeEnum.GET_WIFI_CONFIG);
      commandList.add(CommandTypeEnum.REBOOT_LED);
      commandList.add(CommandTypeEnum.ENERGY_CLEARED);
      commandList.add(CommandTypeEnum.START_IPA);
      commandList.add(CommandTypeEnum.OBTAIN_LIGHT_FEEL);
      commandList.add(CommandTypeEnum.OBTAIN_TIME_SLOT);
      return commandList;
   }
}
