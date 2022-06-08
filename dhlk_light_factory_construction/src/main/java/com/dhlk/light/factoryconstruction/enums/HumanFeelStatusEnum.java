package com.dhlk.light.factoryconstruction.enums;

/**
 * 人感开关状态枚举
 * @author yangfan
 * @since 2021-08-13
 */
public enum HumanFeelStatusEnum {
    /**
     * 开
     */
    OPEN("1","开"),

    /**
     * 关
     */
    CLOASE("0","关"),

    ;
    /**
     * 人感状态
     */
    private final String status;

    /**
     * 描述
     */
    private final String desc;

    HumanFeelStatusEnum(String status, String desc){
        this.status =status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }
}
