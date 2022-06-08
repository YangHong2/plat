package com.dhlk.light.factoryconstruction.enums;

/**
 * 是否枚举类
 *
 * @author wzx
 * @date 2021/8/16
 */
public enum YesNoEnum {

    /**
     * 是
     */
    YES("1", "是"),

    /**
     * 否
     */
    NO("0", "否"),

    ;


    private final String code;

    private final String desc;


    YesNoEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }
}
