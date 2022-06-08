package com.dhlk.light.factoryconstruction.enums;

/**
 * 需记录命令修改记录的命令枚举
 * @author yangfan
 * @since 2021-08-18
 */
public enum  UpdateRecordCommandTypeEnum {

    /**
     * 设置wifi配置
     */
    SET_WIFI_CONFIG("82", "设置wifi配置"),


    /**
     * 亮度值
     */
    BRIGHTNESS("86","亮度值设置"),


    /**
     * 设置人感
     */
    HUMAN_FEEL("84","设置人感"),

    /**
     * 设置光感
     */
    LIGHT_FEEL("92","设置光感"),

    /**
     * 设置实时上报时隙
     */
    SET_TIME_SLOT("8a","设置实时上报时隙"),

    FIRMWARE_UPGRADE("999","固件升级"),
    ;

    /**
     * 命令类型
     */
    private final String commandType;

    /**
     * 描述
     */
    private final String desc;


    UpdateRecordCommandTypeEnum(String commandType, String desc){
        this.commandType =commandType;
        this.desc =desc;
    }

    public static UpdateRecordCommandTypeEnum getCommandTypeEnum(String commandType){
        for(UpdateRecordCommandTypeEnum commandTypeEnum:UpdateRecordCommandTypeEnum.values()){
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
}
