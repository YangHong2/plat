package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.datamap.LedPortMap;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.LedData;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.pojo.entity.command.CommandParam;
import com.dhlk.light.factoryconstruction.pojo.entity.command.LightFeelParam;
import com.dhlk.light.factoryconstruction.pojo.entity.smartlamps.LightFeelConfigData;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 光感设置发送处理类
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class LightFeelCommandSendHander extends AbstractCommandSendHander {

    /**
     * 协议数据处理
     * @param protocol 协议
     * @param command 命令请求
     */
    @Override
    public void protocolDataHander(Protocol protocol, Command command) {
      protocol.setDataLength((byte)0x08);
      CommandParam commandParam = command.getCommandParam();
      LightFeelParam lightFeelParam = (LightFeelParam) commandParam;
      byte[] dataBytes = new byte[8];
      dataBytes[0]=(byte)Integer.parseInt(DataUtil.strTo16(lightFeelParam.getOnOff(),1),16);
      String illumiHighestMinHex = DataUtil.strTo16(lightFeelParam.getIllumiHighest(), 2);
      if(illumiHighestMinHex!=null && illumiHighestMinHex.length()==4){
          dataBytes[1]=(byte)Integer.parseInt(illumiHighestMinHex.substring(0,2),16);
          dataBytes[2]=(byte)Integer.parseInt(illumiHighestMinHex.substring(2,4),16);
      }

      dataBytes[3]=(byte)Integer.parseInt(DataUtil.strTo16(lightFeelParam.getIllumiHighestMin(),1),16);

      String illumiLowest = DataUtil.strTo16(lightFeelParam.getIllumiLowest(), 2);
      if(illumiLowest!=null && illumiLowest.length()==4){
          dataBytes[4]=(byte)Integer.parseInt(illumiLowest.substring(0,2),16);
          dataBytes[5]=(byte)Integer.parseInt(illumiLowest.substring(2,4),16);
      }
      dataBytes[6]=(byte)Integer.parseInt(DataUtil.strTo16(lightFeelParam.getIllumiLowestMax(),1),16);
      dataBytes[7]=(byte)Integer.parseInt(DataUtil.strTo16(lightFeelParam.getIllumiMode(),1),16);

      protocol.setData(dataBytes);

   }

   @Override
   public List<CommandTypeEnum> getCommandTypeList() {
      List<CommandTypeEnum> commandList = new ArrayList<>();
      commandList.add(CommandTypeEnum.LIGHT_FEEL);
      return commandList;
   }
}
