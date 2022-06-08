package com.dhlk.light.factoryconstruction.enums;

/**
 * 命令定义
 * @author yangfan
 * @since 2021-08-10
 */
public enum  CommandTypeEnum {

    /**
     * 照明状态上报
     */
    LIGTH_STATUS_REPORT("1","00", "照明状态上报"),

    /**
     * 人感状态上报
     */
    HUMAN_FEELING_REPORT("1","01",  "人感状态上报"),

    /**
     * 光感数据上报
     */
    LIGHT_FEELING_REPORT("1","02",  "光感数据上报"),

    /**
     * wifi配置
     */
    WIFI_CONFIG_REPORT("2","03",  "wifi配置"),

    /**
     * 固件版本回复
     */
    FIRMWARE_VERSION_REPORT("2","08",  "固件版本回复"),

    /**
     * 人感配置返回
     */
    HUMAN_FEELING_CONFIG_REPORT("2","04", "人感配置返回"),

    /**
     * 上报时隙返回
     */
    TIME_SLOT_REPORT("2","05", "上报时隙返回"),

    /**
     * 执行反馈
     */
    EXECUTE_CALLBACK("2","06",  "执行反馈"),

    /**
     * 光感配置返回
     */
    LIGHT_CONFIG_REPORT("2","09",  "光感配置返回"),


    /**
     * 控制继电器
     */
    SWITCH("3","87","继电器开关"),

    /**
     * 获取人感配置
     */
    HUMAN_FEELING_CONFIG_COMMAND("3","83","获取人感配置"),

    /**
     * 闪灯
     */
    FLASHING_LIGHT("3","93", "闪一闪"),

    /**
     * 获取wifi配置
     */
    GET_WIFI_CONFIG("3","81","获取wifi配置"),

    /**
     * 设置wifi配置
     */
    SET_WIFI_CONFIG("3","82", "设置wifi配置"),

    /**
     * 重启灯具
     */
    REBOOT_LED("3","85","重启灯具"),

    /**
     * 亮度值
     */
    BRIGHTNESS("3","86","亮度值"),

    /**
     * 电能清零
     */
    ENERGY_CLEARED("3","8e","电能清零"),

    /**
     * AP复位
     */
    AP_RESET("3","94","AP复位"),

    /**
     * 设置实时上报时隙
     */
    REPORT_TIME_SLOT("3","8a","设置实时上报时隙"),

    /**
     * 设置人感
     */
    HUMAN_FEEL("3","84","设置人感"),

    /**
     * 获取固件版本
     */
    FIRMWARE_VERSION("3","8d","获取固件版本"),

    /**
     * 设置光感
     */
    LIGHT_FEEL("3","92","设置光感"),


    START_IPA("3","8f","启动IAP"),

    /**
     * 获取光感配置
     */
    OBTAIN_LIGHT_FEEL("3","91","获取光感配置"),

    /**
     * 获取实时上报时隙
     */
    OBTAIN_TIME_SLOT("3","8b","获取实时上报时隙"),
    ;

    /**
     * 消息类型
     * @see MessageEnum
     */
    private final String messageType;

    /**
     * 命令类型
     */
    private final String commandType;


    /**
     * 描述
     */
    private final String desc;


    CommandTypeEnum(String messageType,String commandType, String desc){
        this.messageType = messageType;
        this.commandType =commandType;
        this.desc =desc;
    }

    public static CommandTypeEnum getCommandTypeEnum(String commandType){
        for(CommandTypeEnum commandTypeEnum:CommandTypeEnum.values()){
            if(commandTypeEnum.getCommandType().equals(commandType)){
                return commandTypeEnum;
            }
        }
        return null;
    }


    public String getCommandType() {
        return commandType;
    }

    public String getDesc() {
        return desc;
    }

    public String getMessageType() {
        return messageType;
    }
}
