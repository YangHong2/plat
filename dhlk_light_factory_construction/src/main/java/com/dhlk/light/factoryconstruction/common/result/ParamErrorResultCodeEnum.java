package com.dhlk.light.factoryconstruction.common.result;

/**
 * <p>
 *   1000~1999参数错误
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/11
 */
public enum ParamErrorResultCodeEnum implements ParamErrorResultCode {

    PARAM_INVALID(1000, "参数无效"),
    /**
     * 表单重复提交
     */
    PARAM_REPEAT_SUBMIT(1100, "您已提交，请勿重复操作"),
    ;

    private int code;

    private String msg;

    ParamErrorResultCodeEnum(int code, String msg) {
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
