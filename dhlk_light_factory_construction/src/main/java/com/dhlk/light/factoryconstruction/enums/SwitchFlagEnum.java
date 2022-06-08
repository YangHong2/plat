package com.dhlk.light.factoryconstruction.enums;

/**
 * 命令定义
 * @author yangfan
 * @since 2021-08-10
 */
public enum SwitchFlagEnum {

    /**
     * 开灯
     */
    OPEN("01","开灯"),

    /**
     * 关灯
     */
    CLOSE("00","关灯"),

    /**
     * 开启异常
     */
    OPEN_EXCEPTION("10","开启异常"),

    /**
     * 关闭异常
     */
    CLOSE_EXCEPTION("11","关闭异常"),

    ;


    private final String code;

    private final String desc;


    SwitchFlagEnum(String code, String desc){
        this.code =code;
        this.desc =desc;
    }

    public String getCode() {
        return code;
    }
}
