package com.dhlk.interfaces.service.handler;

import com.dhlk.domain.Result;
import com.dhlk.enums.ResultEnum;
import com.dhlk.exceptions.MyException;
import com.dhlk.utils.Convert;
import com.dhlk.utils.ResultUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * @Description 统一异常处理
 * @Author gchen
 * @Date 2020/3/31
 */
@RestControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler(value = UnauthorizedException.class)//处理访问方法时权限不足问题
    @ResponseBody
    public Result defaultErrorHandler(UnauthorizedException e)  {
        return ResultUtils.error(ResultEnum.NO_PERMISS);
    }

    @ExceptionHandler(value = UnauthenticatedException.class)//处理访问方法时权限不足问题
    @ResponseBody
    public Result errorHandler(UnauthenticatedException e)  {
        return ResultUtils.error(ResultEnum.NO_LOGIN);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    public Result requestSupportException(HttpRequestMethodNotSupportedException e){//处理非法请求
        e.printStackTrace();
        return ResultUtils.error(ResultEnum.URL_ERROR.getStateInfo());
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result Exception(Exception e) {
       e.printStackTrace();
        return ResultUtils.error(ResultEnum.UNKNOWN_ERR.getState(), ResultEnum.UNKNOWN_ERR.getStateInfo());
    }

}
