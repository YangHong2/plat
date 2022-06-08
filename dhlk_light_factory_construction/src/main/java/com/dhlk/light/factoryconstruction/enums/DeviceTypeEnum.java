package com.dhlk.light.factoryconstruction.enums;

/**
 * 设备类型定义
 * @author yangfan
 * @since
 */
public enum DeviceTypeEnum {

    /**
     * 智能灯具
     */
    SMART_LAMPS("0","智能灯具"),


    /**
     * 智能开关
     */
    SMART_SWITCH("1","智能开关"),

    /**
     * 空调
     */
    AIR_CONDITIONER("2","空调"),

    /**
     * 电表
     */
    ELECTRIC_ETER("3","电表"),

    /**
     * 能源采集模块
     */
    ENERGY_COLLECT_MODULE("4","能源采集模块"),
    ;

    private String deviceType;

    private String desc;

    DeviceTypeEnum(String deviceType,String desc){
        this.deviceType =deviceType;
        this.desc = desc;
    }

    public String getDeviceType() {
        return deviceType;
    }
}
