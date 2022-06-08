package com.dhlk.light.factoryconstruction.handler.command.receive;

import com.dhlk.light.factoryconstruction.enums.CommandReceiveHanderEnum;
import com.dhlk.light.factoryconstruction.handler.SocketHandler;

/**
 * 命令接收处理类接口定义
 * @author yangfan
 * @since 2021-08-12
 */
public interface CommandReceiveHander<T>  {


    /**
     * 接收消息
     * @param commandStr 消息字符串
     * @param socketHandler socket
     * @return 消息处理完成返回
     */
    T receiveCommand(String commandStr, SocketHandler socketHandler);

    /**
     *得到处理枚举
     * @return  处理枚举
     */
    CommandReceiveHanderEnum getCommandReceiveHanderEnum();
}
