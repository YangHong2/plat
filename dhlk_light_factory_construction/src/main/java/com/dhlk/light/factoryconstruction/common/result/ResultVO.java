package com.dhlk.light.factoryconstruction.common.result;


import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/9
 */
@Data
public class ResultVO<T> implements Serializable {

    private static final long serialVersionUID = 5014042091101833076L;

    /**
     * 成功标志
     */
    private Boolean success;
    /**
     * 返回处理消息
     */
    private String msg;
    /**
     * 返回数据对象
     */
    private T data;
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 时间戳
     */
    private Long timestamp;

    public static <T> ResultVO<T> ok() {
        return ok(null);
    }

    public static <T> ResultVO<T> ok(T data) {
        ResultVO<T> r = new ResultVO<>();
        r.setCode(SuccessResultCodeEnum.SUCCESS.getCode());
        r.setMsg(SuccessResultCodeEnum.SUCCESS.getMsg());
        r.setSuccess(true);
        r.setData(data);
        r.setTimestamp(System.currentTimeMillis());
        return r;
    }

    public static <T> ResultVO<T> fail(ResultCode resultCode) {
        return fail(resultCode, resultCode.getMsg());
    }

    public static <T> ResultVO<T> fail(ResultCode resultCode, String errorMsg) {
        ResultVO<T> r = new ResultVO<>();
        r.setCode(resultCode.getCode());
        r.setMsg(errorMsg);
        r.setSuccess(false);
        r.setTimestamp(System.currentTimeMillis());
        return r;
    }

}
