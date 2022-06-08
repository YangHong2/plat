package com.dhlk.light.factoryconstruction.enums;

/**
 * 命令请求枚举定义
 * @author yangfan
 * @since 2021-08-13
 */
public enum CommandRequestEnum {

    /**
     * 开灯
     * @see CommandTypeEnum#SWITCH
     */
    OPEN_LED(1,"开灯"),

    /**
     * 关灯
     * @see CommandTypeEnum#SWITCH
     */
    CLOSE_LED(2,"关灯"),

    /**
     * 闪灯
     * @see CommandTypeEnum#FLASHING_LIGHT
     */
    FLASHING_LIGHT(3,"闪一闪"),

    /**
     * 获取人感配置
     * @see CommandTypeEnum#HUMAN_FEELING_CONFIG_REPORT
     */
    HUMAN_FEEL_CONFIG(4,"获取人感配置"),

    /**
     * 获取wifi配置
     * @see CommandTypeEnum#GET_WIFI_CONFIG
     */
    GET_WIFI_CONFIG(5,"获取wifi配置"),

    /**
     * 设置wifi配置
     * @see CommandTypeEnum#SET_WIFI_CONFIG
     */
    SET_WIFI_CONFIG(6,"设置wifi配置"),

    /**
     * 重启灯具
     * @see CommandTypeEnum#REBOOT_LED
     */
    REBOOT_LED(7,"重启灯具"),

    /**
     * 亮度值
     * @see CommandTypeEnum#BRIGHTNESS
     */
    BRIGHTNESS(8,"亮度值"),

    /**
     * 电能清零
     */
    ENERGY_CLEARED(9,"电能清零"),

    /**
     * AP复位
     */
    AP_RESET(10,"AP复位"),

    /**
     * 设置实时上报时隙
     */
    REPORT_TIME_SLOT(11,"设置实时上报时隙"),

    /**
     * 设置人感
     */
    HUMAN_FEEL(12,"设置人感"),

    /**
     * 设置光感
     */
    LIGHT_FEEL(13,"设置光感"),

    /**
     * 获取光感配置
     */
    OBTAIN_LIGHT_FEEL(14,"获取光感配置"),

    /**
     * 获取实时上报时隙
     */
    OBTAIN_TIME_SLOT(15,"获取实时上报时隙"),

    ;

    /**
     * 命令类型
     */
    private final Integer commandId;

    /**
     * 描述
     */
    private final String desc;


    CommandRequestEnum(Integer commandId, String desc){
        this.commandId =commandId;
        this.desc =desc;
    }

    public static CommandRequestEnum getCommandRequestEnum(Integer id){
        for(CommandRequestEnum commandTypeEnum: CommandRequestEnum.values()){
            if(commandTypeEnum.getCommandId().equals(id)){
                return commandTypeEnum;
            }
        }
        return null;
    }


    public Integer getCommandId() {
        return commandId;
    }
}
