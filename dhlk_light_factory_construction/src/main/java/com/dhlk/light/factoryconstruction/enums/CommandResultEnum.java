package com.dhlk.light.factoryconstruction.enums;

/**
 * 命令返回结果
 * @author yangfan
 * @since 2021-08-12
 */
public enum  CommandResultEnum {

    /**
     * 命令处理成功
     */
    SUCCESS(0,"命令处理成功"),

    /**
     * 失败
     */
    FAIL(1,"失败"),

    /**
     * 丢失设备连接
     */
    LOST_CONNECTION(2,"丢失设备连接"),

    /**
     * 命令已发送
     */
    COMMAND_SEND(3,"命令已发送"),

    /**
     * 命令已经执行,不能重复执行
     */
    EXECUTEING(4,"命令已经执行,不能重复执行"),
    ;

    private final Integer code;

    private final String message;


    CommandResultEnum(Integer code,String message){
        this.code =code;
        this.message =message;
    }


    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
