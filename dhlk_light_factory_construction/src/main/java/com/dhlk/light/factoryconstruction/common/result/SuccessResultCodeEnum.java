package com.dhlk.light.factoryconstruction.common.result;

/**
 * <p>
 *  成功
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/11
 */
public enum SuccessResultCodeEnum implements ResultCode {

    SUCCESS(200, "成功"),

    ;

    private int code;

    private String msg;

    SuccessResultCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {
        this.msg = msg;
    }
}
