package com.dhlk.light.factoryconstruction.common.exception;

import com.dhlk.light.factoryconstruction.common.result.ResultCode;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author jisen wang
 * @Date 2020/9/10
 */
@Getter
public class BaseException extends RuntimeException{

    private ResultCode resultCode;

    public BaseException(ResultCode resultCode) {
        super(resultCode.format());
        this.resultCode = resultCode;
    }

    public BaseException(ResultCode resultCode,String errorMsg) {
        super(resultCode.format(resultCode.getCode(),errorMsg));
        this.resultCode = resultCode;
        this.resultCode.setMsg(errorMsg);
    }

    public BaseException(Throwable cause, ResultCode resultCode) {
        super(resultCode.format(), cause);
        this.resultCode = resultCode;
    }

    public BaseException(Throwable cause, ResultCode resultCode,String errorMsg) {
        super(resultCode.format(resultCode.getCode(), errorMsg), cause);
        this.resultCode = resultCode;
        this.resultCode.setMsg(errorMsg);
    }
}

