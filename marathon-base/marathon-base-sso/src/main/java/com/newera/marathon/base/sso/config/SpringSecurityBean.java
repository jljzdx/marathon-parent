package com.newera.marathon.base.sso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author MicroBin
 * @description：TODO
 * @date 2020/6/18 9:24 上午
 */
@Configuration
public class SpringSecurityBean
{
    @Bean // 加密方式
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
