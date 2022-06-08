package com.dhlk.light.factoryconstruction.enums;

/**
 * wifi双频
 * @author yangfan
 * @since 2021-08-17
 */
public enum  DualFrequencyEnum {

    /**
     * 2.4G
     */
    TWO_FOUR_G("01","2.4G"),

    /**
     * 5G
     */
    FIVE_G("02","5G"),

    /**
     * 2.4G和5G
     */
    TWO_FOUR_G_AND_FIVE_G("03","2.4G和5G"),

    ;

    /**
     * 双频代码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;


    DualFrequencyEnum(String code, String desc){
        this.code =code;
        this.desc =desc;
    }

    public String getCode() {
        return code;
    }
}
