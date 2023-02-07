package cn.rzpt.common.handler;

import cn.rzpt.common.lang.Result;
import cn.rzpt.common.lang.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

/**
 * @Author Wax
 * @Date 2022/11/25 8:05
 * @Version 1.0
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 全局异常处理没有指明异常类型
     * 不管什么异常都是可以捕获的
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
       // e.printStackTrace();
        log.error(e.getMessage());
       // e.printStackTrace();
        return Result.error();
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public Result error(InternalAuthenticationServiceException e) {
        log.error(e.getMessage());
        return Result.error().code(ResultCode.ARITHMETIC_EXCEPTION.getCode()).message(ResultCode.ARITHMETIC_EXCEPTION.getMessage());
    }
    @ExceptionHandler(SQLSyntaxErrorException.class)
    public Result error(SQLSyntaxErrorException e) {
        return Result.error().code(ResultCode.PARAM_NOT_COMPLETE.getCode()).message(ResultCode.PARAM_NOT_COMPLETE.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Result error(BadCredentialsException e) {
        log.error(e.getMessage());
        return Result.error().code(ResultCode.USER_CREDENTIALS_ERROR.getCode()).message(ResultCode.USER_CREDENTIALS_ERROR.getMessage());
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public Result error(UsernameNotFoundException e) {
        log.error(e.getMessage());
        return Result.error().code(ResultCode.USER_ACCOUNT_NOT_EXIST.getCode()).message(ResultCode.USER_ACCOUNT_NOT_EXIST.getMessage());
    }
    @ExceptionHandler(NullPointerException.class)
    public Result error(NullPointerException e) {
        log.error(e.getMessage());
        return Result.error().code(ResultCode.PARAM_IS_BLANK.getCode()).message(ResultCode.PARAM_IS_BLANK.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public Result error(BusinessException e) {
        log.error(e.getErrMsg());
        return Result.error().code(e.getCode()).message(e.getErrMsg());
    }
}
