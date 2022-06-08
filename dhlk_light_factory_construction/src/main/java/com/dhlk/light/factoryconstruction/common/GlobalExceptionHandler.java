package com.dhlk.light.factoryconstruction.common;


import com.dhlk.light.factoryconstruction.common.exception.BaseException;
import com.dhlk.light.factoryconstruction.common.result.ParamErrorResultCodeEnum;
import com.dhlk.light.factoryconstruction.common.result.ResultVO;
import com.dhlk.light.factoryconstruction.common.result.ServerErrorResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局异常处理
 *
 * @author yangfan
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResultVO<?> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResultVO.fail(ServerErrorResultCodeEnum.UNKNOWN);
    }

    @ExceptionHandler
    public ResultVO<?> handleException(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        String errorMsg = violations.stream().map(ConstraintViolation::getMessage).collect(Collectors.joining(";"));
        return ResultVO.fail(ParamErrorResultCodeEnum.PARAM_INVALID, errorMsg);
    }

    @ExceptionHandler
    public ResultVO<?> handleException(BindException e) {
        log.error(e.getMessage(), e);
        final BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        return ResultVO.fail(ParamErrorResultCodeEnum.PARAM_INVALID, errorMsg);
    }

    @ExceptionHandler
    public ResultVO<?> handleException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        final BindingResult bindingResult = e.getBindingResult();
        String errorMsg = bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(";"));
        return ResultVO.fail(ParamErrorResultCodeEnum.PARAM_INVALID, errorMsg);
    }

    @ExceptionHandler
    public ResultVO<?> handleException(BaseException e) {
        log.error(e.getMessage(), e);
        return ResultVO.fail(e.getResultCode());
    }

}
