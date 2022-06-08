package com.dhlk.light.factoryconstruction.enums;

/**
 * 人感触发状态枚举
 * @author yangfan
 * @since 2021-08-13
 */
public enum HumanFeelTriggerStatusEnum {
    /**
     * 触发
     */
    TRIGGER("1","触发"),

    /**
     * 未触发
     */
    NOT_TRIGGER("0","未触发"),

    ;
    /**
     * 人感状态
     */
    private final String status;

    /**
     * 描述
     */
    private final String desc;

    HumanFeelTriggerStatusEnum(String status, String desc){
        this.status =status;
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }
}
