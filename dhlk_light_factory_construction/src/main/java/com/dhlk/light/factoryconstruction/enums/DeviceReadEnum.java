package com.dhlk.light.factoryconstruction.enums;

import lombok.Getter;

/**
 * 设备信息读取枚举类
 *
 * @author wzx
 * @date 2021/8/16
 */
public enum DeviceReadEnum {
    /**
     * 准备读取
     */
    READY("0", "准备读取"),

    /**
     * 读取中
     */
    LOADING("1", "读取中"),

    /**
     * 读取完毕
     */
    FINISH("2", "读取完毕");

    @Getter
    private final String code;

    @Getter
    private final String desc;

    DeviceReadEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
