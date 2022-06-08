package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.pojo.entity.command.BrightnessParam;
import com.dhlk.light.factoryconstruction.pojo.entity.command.CommandParam;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
/**
 * 灯亮度设置发送处理类
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class BrightnessCommandSendHander extends AbstractCommandSendHander {

    /**
     * 协议数据处理
     * @param protocol 协议
     * @param command 命令请求
     */
    @Override
    public void protocolDataHander(Protocol protocol, Command command) {

      CommandParam commandParam = command.getCommandParam();
      //灯亮度
      BrightnessParam brightnessParam = (BrightnessParam) commandParam;

      protocol.setDataLength((byte)0x01);
      byte[] dataBytes = new byte[1];
      dataBytes[0]=(byte)Integer.parseInt(DataUtil.strTo16(brightnessParam.getBrightness(),1),16);
      protocol.setData(dataBytes);
   }

   @Override
   public List<CommandTypeEnum> getCommandTypeList() {
      List<CommandTypeEnum> commandList = new ArrayList<>();
      commandList.add(CommandTypeEnum.BRIGHTNESS);
      return commandList;
   }
}
