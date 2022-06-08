package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.datamap.DeviceCommandCheckMap;
import com.dhlk.light.factoryconstruction.datamap.SocketClinetMap;
import com.dhlk.light.factoryconstruction.enums.CommandResultEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.handler.ProtocolHelper;
import com.dhlk.light.factoryconstruction.handler.RecordHelper;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import com.dhlk.light.factoryconstruction.util.CrcUtil;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 发送命令处理
 * @author yangfan
 * @since 2021-08-12
 */
@Slf4j
public abstract  class AbstractCommandSendHander implements CommandSendHander {

   /**
    * 发送命令
    * @param command 命令请求
    */
   @Override
   public  SendCommandVO sendCommand(Command command) {
      SendCommandVO sendCommandVO = new SendCommandVO();
      String id = command.getId();
      sendCommandVO.setId(id);
      CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
      //获取命令16进制字符串
      Protocol protocol = getProtocol(command);
      SocketHandler socketHandler = SocketClinetMap.get(id);

      if(socketHandler!=null){
         //检查命令是否可发送
         if(!DeviceCommandCheckMap.checkAndAdd(id, commandTypeEnum.getCommandType())){
            sendCommandVO.setCode(CommandResultEnum.EXECUTEING.getCode());
            sendCommandVO.setMessage(CommandResultEnum.EXECUTEING.getMessage());
         }else{
            try {
               socketHandler.send(protocol);
               sendCommandVO.setCode(CommandResultEnum.COMMAND_SEND.getCode());
               sendCommandVO.setMessage(CommandResultEnum.COMMAND_SEND.getMessage());
            }catch (Exception e){
               sendCommandVO.setCode(CommandResultEnum.FAIL.getCode());
               sendCommandVO.setMessage(CommandResultEnum.FAIL.getMessage());
               //发送失败删除发送记录
               DeviceCommandCheckMap.remove(id,commandTypeEnum.getCommandType());
            }
         }

      }else{
         sendCommandVO.setCode(CommandResultEnum.LOST_CONNECTION.getCode());
         sendCommandVO.setMessage(CommandResultEnum.LOST_CONNECTION.getMessage());
      }

      if(CommandResultEnum.COMMAND_SEND.getCode().equals(sendCommandVO.getCode())){

         //记录命令日志
         RecordHelper.recordCommandLog(command.getCommandTypeEnum(),protocol.toString(),command.getId());
         //recordUpdateLog
         RecordHelper.recordUpdateLog(command);


      }



      return sendCommandVO;
   }

   /**
    * 通过命令参数得到获取命令16进制字符串
    * @param command 命令
    * @return 命令字符串
    */
   public Protocol getProtocol(Command command) {
      //协议头处理
      Protocol protocol =  commandHead(command);

      //协议数据处理
      protocolDataHander(protocol,command);


      byte[] checkDigitBytes = ProtocolHelper.getCheckDigitStr(protocol);


      protocol.setCheckDigit(CrcUtil.crc8Maxim(checkDigitBytes,0,checkDigitBytes.length));

      //包尾
      byte[] packageEnd = new byte[3];
      packageEnd[0] = (byte)0x00;
      packageEnd[1] = (byte)0x5a;
      packageEnd[2] = (byte)0x5a;
      protocol.setPackageEnd(packageEnd);



      return protocol;
   }


   /**
    * 协议头处理
    * @param command 命令
    * @return 原始协议对象
    */
   private Protocol commandHead(Command command) {
      Protocol protocol = new Protocol();
      byte[] pageHeadBytes = new byte[2];
      pageHeadBytes[0] = (byte)0xA5;
      pageHeadBytes[1] = (byte)0xA5;
      protocol.setHead(pageHeadBytes);


      //协议版本
      protocol.setVersion((byte)0x01);

      //寻址类型
      protocol.setAddressingType((byte)0x00);

      //组id
      protocol.setGroupId((byte)0xff);

      String id = command.getId();
      //id
      byte[] idBytes = DataUtil.hexStringToBytes(id);
      protocol.setIdBytes(idBytes);

      //设备类型
      protocol.setDeviceType((byte)0x00);

      CommandTypeEnum commandTypeEnum = command.getCommandTypeEnum();
      //命令类型
      String commandType = commandTypeEnum.getCommandType();
      //命令类型
      protocol.setCommandType((byte)Integer.parseInt(commandType,16));

      protocol.setTimestamp(getTimestamp());


      //预留
      byte[] reserved = new byte[4];
      reserved[0] = (byte)0xff;
      reserved[1] = (byte)0xff;
      reserved[2] = (byte)0xff;
      reserved[3] = (byte)0xff;
      protocol.setReserved(reserved);

      return protocol;
   }


   /**
    * 获取当前时间戳16进制字符串
    * @return 当前时间戳16进制字符串
    */
   private  byte[] getTimestamp(){
      ZoneId zone = ZoneId.systemDefault();
      LocalDateTime now = LocalDateTime.now();
      Instant instant = now.atZone(zone).toInstant();
      long timestamp = instant.toEpochMilli();

      byte[] byteArr = new byte[6];
      byteArr[0]=(byte)(timestamp >> 40);
      byteArr[1]=(byte)(timestamp >> 32);
      byteArr[2]=(byte)(timestamp >> 24);
      byteArr[3]=(byte)(timestamp >> 16);
      byteArr[4]=(byte)(timestamp >> 8);
      //timestamp >> 0
      byteArr[5]=(byte)(timestamp);
      return byteArr;
   }

   /**
    * 协议数据处理
    * @param command 命令
    */
   public abstract void protocolDataHander(Protocol protocol,Command command);

}
