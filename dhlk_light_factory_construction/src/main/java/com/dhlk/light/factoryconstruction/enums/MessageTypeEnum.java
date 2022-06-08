package com.dhlk.light.factoryconstruction.enums;

/**
 * 消息类型
 *
 * @author wzx
 * @date 2021/8/13
 */
public enum MessageTypeEnum {
    /**
     * websocket消息类型 定时多条设备消息
     */
    TYPE_TIMING_DEVICES(0, "定时所有设备消息"),
    /**
     * websocket消息类型 实时多条设备消息
     */
    TYPE_REAL_TIME_DEVICES(1, "实时多条设备消息"),
    /**
     * websocket消息类型 实时单条设备消息
     */
    TYPE_REAL_TIME_DEVICE(2, "实时单条设备消息"),
    /**
     * websocket消息类型 实时单条执行消息
     */
    TYPE_REAL_TIME_EXECUTE(3, "实时单条执行消息"),
    /**
     * DEBUG 上报命令消息推送
     */
    DEBUG_REPORT_MESSAGE(4, "DEBUG 上报命令消息推送"),
    /**
     * 设备离线消息推送
     */
    OFFLINE_MESSAGE(5, "设备离线消息推送"),

    ;

    private final int type;

    private final String desc;

    MessageTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }
}
