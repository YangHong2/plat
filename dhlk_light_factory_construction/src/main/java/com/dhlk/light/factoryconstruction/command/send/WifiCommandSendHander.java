package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.pojo.entity.command.CommandParam;
import com.dhlk.light.factoryconstruction.pojo.entity.command.WifiParam;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * wifi设置发送处理类
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class WifiCommandSendHander extends AbstractCommandSendHander {

   /**
    * 协议数据处理
    * @param protocol 协议
    * @param command 命令请求
    */
   @Override
   public void protocolDataHander(Protocol protocol, Command command) {
      protocol.setDataLength((byte)0x36);
      CommandParam commandParam = command.getCommandParam();
      WifiParam wifiParam = (WifiParam) commandParam;
      byte[] dataBytes = new byte[54];
      dataBytes[0]=(byte)Integer.parseInt(DataUtil.strTo16(wifiParam.getModule(),1),16);
      dataBytes[1]=(byte)Integer.parseInt(DataUtil.strTo16(wifiParam.getDualFrequency(),1),16);
      byte[] bytes_ssid = wifiParam.getSsId().getBytes();
      int ssid_start = 2;
      for(int i =0;i<bytes_ssid.length;i++){
         dataBytes[ssid_start+i] = bytes_ssid[i];
      }
      byte[] bytes_ps = wifiParam.getPassword().getBytes();

      int ps_start = 34;
      for(int i =0;i<bytes_ps.length;i++){
         dataBytes[ps_start+i] = bytes_ps[i];
      }
      int ip_start = 50;
      String ip = wifiParam.getIp();
      String ipArray[] = ip.replace(".",",").split(",");
      // ip地址需要根据.分开拆分编码
      for (String ipStr : ipArray){
         dataBytes[ip_start] = (byte)Integer.parseInt(DataUtil.strTo16(ipStr,1),16);
         ip_start++;
      }
      protocol.setData(dataBytes);
   }

   @Override
   public List<CommandTypeEnum> getCommandTypeList() {
      List<CommandTypeEnum> commandList = new ArrayList<>();
      commandList.add(CommandTypeEnum.SET_WIFI_CONFIG);
      return commandList;
   }
}
