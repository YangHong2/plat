package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;

import java.util.List;

/**
 * 命令发送处理类
 * @author yangfan
 * @since 2021-08-12
 */
public interface CommandSendHander {

   /**
    * 发送命令
    * @param command 命令请求
    * @return 结果
    */
   SendCommandVO sendCommand(Command command);

   /**
    * 获取处理的命令类型列表
    * @return 处理的命令类型列表
    */
   List<CommandTypeEnum> getCommandTypeList();

}
