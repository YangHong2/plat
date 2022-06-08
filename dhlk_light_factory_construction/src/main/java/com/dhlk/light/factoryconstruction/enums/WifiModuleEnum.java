package com.dhlk.light.factoryconstruction.enums;

/**
 * wifi模块
 * @author yangfan
 * @since 2021-08-17
 */
public enum WifiModuleEnum {

    /**
     * WG219 模块
     */
    WG219("00","WG219 模块"),

    /**
     * E103-W01模块
     */
    E103_W01("01","E103-W01模块"),

    /**
     * 汉枫HF_LPD130模块
     */
    HF_LPD130("01","汉枫HF_LPD130模块"),

    ;

    /**
     * 模块代码
     */
    private final String code;

    /**
     * 描述
     */
    private final String desc;


    WifiModuleEnum(String code, String desc){
        this.code =code;
        this.desc =desc;
    }

    public String getCode() {
        return code;
    }
}
