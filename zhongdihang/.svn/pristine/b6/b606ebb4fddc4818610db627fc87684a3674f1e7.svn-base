package com.zhongdihang.bankdispatch.config;

import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/15 18:02
 * @Modified By：
 * @Version: V1.0.0
 */
@ControllerAdvice
public class GlobalExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(value = {BusinessException.class})
    public ResultModel businessException(BusinessException ex) {
        return ResultModel.error(ResultStatus.FAILURE.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AuthenticationException.class})
    public ResultModel authenticationException(BusinessException ex) {
        return ResultModel.error(ResultStatus.FAILURE.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResultModel accessDeniedException(BusinessException ex) {
        return ResultModel.error(ResultStatus.FAILURE.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResultModel usernameNotFoundException(UsernameNotFoundException ex) {
        return ResultModel.error(ResultStatus.FAILURE.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResultModel badCredentialsException(BadCredentialsException ex) {
        return ResultModel.error(ResultStatus.FAILURE.getCode(), ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = {Exception.class})
    public ResultModel servrtException(Exception ex) {
        return ResultModel.error(ResultStatus.SERVER_ERROR, "服务器发生未知错误！");
    }

    @ResponseBody
    @ExceptionHandler(BindException.class)
    public ResultModel bindExceptionHandler(BindException ex){
        // TODO: 2017/8/31
        return ResultModel.ok();
    }
}
