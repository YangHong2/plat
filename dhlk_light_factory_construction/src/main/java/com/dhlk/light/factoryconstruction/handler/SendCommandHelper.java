package com.dhlk.light.factoryconstruction.handler;

import com.dhlk.light.factoryconstruction.command.send.CommandSendHander;
import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.enums.CommandTypeEnum;
import com.dhlk.light.factoryconstruction.enums.SwitchFlagEnum;
import com.dhlk.light.factoryconstruction.pojo.entity.Command;
import com.dhlk.light.factoryconstruction.pojo.entity.command.*;
import com.dhlk.light.factoryconstruction.pojo.vo.SendCommandVO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 发送命令帮助类
 * @author yangfan
 * @since 2021-08-13
 */
@Component
public class SendCommandHelper implements ApplicationContextAware {

    private static final Map<CommandTypeEnum,CommandSendHander> COMMAND_SEND_HANDER_MAP = new HashMap<>();

    private SendCommandHelper(){

    }

    /**
     * 开灯
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static  List<SendCommandVO> openLed(String ...ids){
        return switchOper(SwitchFlagEnum.OPEN,ids);
    }

    /**
     * 关灯
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static  List<SendCommandVO> closeLed(String ...ids){
        return switchOper(SwitchFlagEnum.CLOSE,ids);
    }

    /**
     * 继电器操作
     * @param switchFlagEnum 继电器操作枚举
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static  List<SendCommandVO> switchOper(SwitchFlagEnum switchFlagEnum,String ...ids){
        SwitchParam switchParam = new SwitchParam();
        switchParam.setSwitchFlag(switchFlagEnum.getCode());
        return sendCommand(CommandTypeEnum.SWITCH,switchParam,ids);
    }

    /**
     * 闪一闪
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static List<SendCommandVO> flashingLight(String ...ids){
        return sendCommand(CommandTypeEnum.FLASHING_LIGHT,null,ids);
    }


    /**
     * 读取人感配置
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static List<SendCommandVO> humanFellConfig(String ...ids){
        return sendCommand(CommandTypeEnum.HUMAN_FEELING_CONFIG_COMMAND,null,ids);
    }

    /**
     * 获取wifi配置
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static List<SendCommandVO> getWifiConfig(String ...ids){
        return sendCommand(CommandTypeEnum.GET_WIFI_CONFIG,null,ids);
    }

    /**
     * 重启灯具
     * @param ids 设备唯一标识列表
     * @return 结果
     */
    public static List<SendCommandVO> rebootLed(String ...ids){
        return sendCommand(CommandTypeEnum.REBOOT_LED,null,ids);
    }

    /**
     * 启动IPA
     * @param ids
     * @return
     */
    public static List<SendCommandVO> startIPA(String ...ids){
        return sendCommand(CommandTypeEnum.START_IPA,null,ids);
    }

    /**
     * 灯具亮度设置
     * @param brightnessParam
     * @param ids
     * @return 结果
     */
    public static List<SendCommandVO> brightness(BrightnessParam brightnessParam,String ...ids){
        return sendCommand(CommandTypeEnum.BRIGHTNESS,brightnessParam,ids);
    }

    /**
     * @Description 电能清零
     * @Date 2021/8/16
     * @Param [brightnessParam, ids]
     **/
    public static List<SendCommandVO> energyCleared(String ...ids){
        return sendCommand(CommandTypeEnum.ENERGY_CLEARED,null,ids);
    }

    /**
     * @Description AP复位
     * @Date 2021/8/16
     * @Param [apParam, ids]
     **/
    public static List<SendCommandVO> apReset(APParam apParam,String ...ids){
        return sendCommand(CommandTypeEnum.AP_RESET,apParam,ids);
    }

    /**
     * @Description REPORT_TIME_SLOT
     * @Date 2021/8/16
     * @Param [apParam, ids]
     **/
    public static List<SendCommandVO> reportTimeSlot(ReportTimeParam reportTimeParam, String ...ids){
        return sendCommand(CommandTypeEnum.REPORT_TIME_SLOT, reportTimeParam,ids);
    }

    /**
     * @Description 人感设置
     * @Date 2021/8/16
     * @Param [humanFeelParam, ids]
     **/
    public static List<SendCommandVO> humanFeel(HumanFeelParam humanFeelParam, String ...ids){
        return sendCommand(CommandTypeEnum.HUMAN_FEEL, humanFeelParam,ids);
    }
    /**
     * @Description 光感设置
     * @Date 2021/8/16
     * @Param [lightFeelParam, ids]
     **/
    public static List<SendCommandVO> lightFeel(LightFeelParam lightFeelParam, String ...ids){
        return sendCommand(CommandTypeEnum.LIGHT_FEEL, lightFeelParam,ids);
    }
    /**
     * @Description: wifi设置
     * @Date 2021/8/16
     * @Param [wifiParam, ids]
     **/
    public static List<SendCommandVO> wifi(WifiParam wifiParam, String ...ids){
        return sendCommand(CommandTypeEnum.SET_WIFI_CONFIG, wifiParam,ids);
    }


    /**
     * 获取固件版本
     * @param ids 设备唯一标识列表
     * @return 处理结果
     */
    public static List<SendCommandVO> getFirmwareVersion(String ...ids){
        return sendCommand(CommandTypeEnum.FIRMWARE_VERSION,null,ids);
    }

    /**
     * @Description 获取光感配置
     * @Date 2021/8/16
     * @Param [brightnessParam, ids]
     **/
    public static List<SendCommandVO> obtainLightFeel(String ...ids){
        return sendCommand(CommandTypeEnum.OBTAIN_LIGHT_FEEL,null,ids);
    }

    /**
     * @Description 获取实时上报时隙
     * @Date 2021/8/16
     * @Param [brightnessParam, ids]
     **/
    public static List<SendCommandVO> obtainTimeSlot(String ...ids){
        return sendCommand(CommandTypeEnum.OBTAIN_TIME_SLOT,null,ids);
    }
    /**
     * 发送命令
     * @param commandTypeEnum 设备类型
     * @param commandParam 命令参数
     * @param ids 设备唯一标识列表
     * @return 命令处理结果
     */
    public static List<SendCommandVO> sendCommand( CommandTypeEnum commandTypeEnum, CommandParam commandParam,String ...ids){
        List<SendCommandVO> sendCommandVoList = new ArrayList<>();
        for(String id : ids){
            Command command  = new Command();
            command.setId(id);
            command.setCommandTypeEnum(commandTypeEnum);
            command.setCommandParam(commandParam);

            CommandSendHander commandSendHander = COMMAND_SEND_HANDER_MAP.get(commandTypeEnum);
            if(commandSendHander==null){
                throw new BaseException(ParamErrorResultCodeEnum.PARAM_INVALID,"配置异常");
            }
            SendCommandVO sendCommandVO = commandSendHander.sendCommand(command);


            sendCommandVoList.add(sendCommandVO);
        }
        return sendCommandVoList;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CommandSendHander> stringCommandSendHanderMap = applicationContext.getBeansOfType(CommandSendHander.class);
        Collection<CommandSendHander> commandSendHanders = stringCommandSendHanderMap.values();

        for(CommandSendHander commandSendHander : commandSendHanders){
            List<CommandTypeEnum> commandTypeList = commandSendHander.getCommandTypeList();
            for(CommandTypeEnum commandType : commandTypeList){
                COMMAND_SEND_HANDER_MAP.put(commandType,commandSendHander);
            }
        }

    }
}
