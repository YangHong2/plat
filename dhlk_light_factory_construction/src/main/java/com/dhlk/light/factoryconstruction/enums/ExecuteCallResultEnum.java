package com.dhlk.light.factoryconstruction.enums;

/**
 * 执行反馈命令结果
 * @author yangfan
 * @since 2021-08-13
 */
public enum  ExecuteCallResultEnum {

    /**
     * 智能灯具
     */
    SUCCESS("1","执行成功"),

    /**
     * 智能开关
     */
    FAIL("0","执行失败"),

    ;

    private String result;

    private String desc;

    ExecuteCallResultEnum(String result,String desc){
        this.result =result;
        this.desc = desc;
    }

    public String getResult() {
        return result;
    }
}
