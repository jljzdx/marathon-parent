package com.newera.marathon.base.sso.exception;

import org.springframework.security.core.AuthenticationException;


/**
 * @author MicroBin
 * @description：验证码相关异常类
 * @date 2020/6/21 9:23 下午
 */
public class ValidateCodeException extends AuthenticationException
{
    public ValidateCodeException(String msg, Throwable t) {
        super(msg, t);
    }

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
