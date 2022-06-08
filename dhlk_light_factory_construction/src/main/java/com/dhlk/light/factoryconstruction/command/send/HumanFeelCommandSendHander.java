package com.dhlk.light.factoryconstruction.command.send;

import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.Protocol;
import com.dhlk.light.factoryconstruction.pojo.entity.command.CommandParam;
import com.dhlk.light.factoryconstruction.pojo.entity.command.HumanFeelParam;
import com.dhlk.light.factoryconstruction.util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 人感设置发送处理类
 * @date 2021/8/16
 * @author lzhang
 */
@Slf4j
@Component
public class HumanFeelCommandSendHander extends AbstractCommandSendHander {

    /**
     * 协议数据处理
     * @param protocol 协议
     * @param command 命令请求
     */
    @Override
    public void protocolDataHander(Protocol protocol, Command command) {

        protocol.setDataLength((byte)0x06);

        CommandParam commandParam = command.getCommandParam();
        //灯亮度
        HumanFeelParam humanFeelParam = (HumanFeelParam) commandParam;

        byte[] dataBytes = new byte[6];
        dataBytes[0]=(byte)Integer.parseInt(DataUtil.strTo16(humanFeelParam.getHumanFlag(),1),16);

        String gradientTimeHex = DataUtil.strTo16(humanFeelParam.getContinuedTime(), 2);
        if(gradientTimeHex!=null && gradientTimeHex.length()==4){
            dataBytes[1]=(byte)Integer.parseInt(gradientTimeHex.substring(0,2),16);
            dataBytes[2]=(byte)Integer.parseInt(gradientTimeHex.substring(2,4),16);
        }
        dataBytes[3]=(byte)Integer.parseInt(DataUtil.strTo16(humanFeelParam.getGradientTime(),1),16);
        dataBytes[4]=(byte)Integer.parseInt(DataUtil.strTo16(humanFeelParam.getBrightnessLowest(),1),16);
        dataBytes[5]=(byte)Integer.parseInt(DataUtil.strTo16(humanFeelParam.getBrightnessHighest(),1),16);

        protocol.setData(dataBytes);
    }

    @Override
    public List<CommandTypeEnum> getCommandTypeList() {
        List<CommandTypeEnum> commandList = new ArrayList<>();
        commandList.add(CommandTypeEnum.HUMAN_FEEL);
        return commandList;
    }
}
