package com.dhlk.light.factoryconstruction.enums;

/**
 * 设备消息类型
 * @author yangfan
 * @since 2021-08-25
 */
public enum  MessageEnum {

    /**
     * 主动上报
     */
    INITIATIVE_REPORT("1","主动上报"),

    /**
     * 被动上报
     */
    PASSIVE_REPORT("2","被动上报"),


    /**
     * 命令请求
     */
    COMMAND_REQUEST("3","命令请求"),

    ;
    /**
     * 人感状态
     */
    private final String type;

    /**
     * 描述
     */
    private final String desc;

    MessageEnum(String type, String desc){
        this.type =type;
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }
}
