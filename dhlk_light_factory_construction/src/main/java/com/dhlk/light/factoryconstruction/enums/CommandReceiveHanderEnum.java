package com.dhlk.light.factoryconstruction.enums;

/**
 * 命令接口处理类型枚举
 * @author yangfan
 * @since 2021-08-18
 */
public enum  CommandReceiveHanderEnum {

    /**
     * 普通命令
     */
    NOMAL("a5a5","普通命令"),


    /**
     * 固件升级命令
     */
    FIRMWARE_UPGRADE("a5a5a5","固件升级命令"),

    ;

    private String commandStartStr;

    private String desc;

    CommandReceiveHanderEnum(String commandStartStr,String desc){
        this.commandStartStr =commandStartStr;
        this.desc = desc;
    }

    public static CommandReceiveHanderEnum getCommandReceiveHanderEnum(String commandStartStr){
        for(CommandReceiveHanderEnum commandReceiveHanderEnum: CommandReceiveHanderEnum.values()){
            if(commandReceiveHanderEnum.getCommandStartStr().equals(commandStartStr)){
                return commandReceiveHanderEnum;
            }
        }
        return null;
    }

    public String getCommandStartStr() {
        return commandStartStr;
    }
}
