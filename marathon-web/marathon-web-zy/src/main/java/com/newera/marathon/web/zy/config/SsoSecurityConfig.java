package com.newera.marathon.web.zy.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author MicroBin
 * @description：单点登录
 * @date 2020/6/20 11:39 上午
 */
@EnableOAuth2Sso // 开启单点登录功能
@Configuration
@Slf4j
public class SsoSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Value("${sso-server-url}")
    private String ssoServerUrl;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        log.info("SSO Address: {}",ssoServerUrl);
        http.authorizeRequests()
                // 首页所有人都可以访问
                .antMatchers("/login").permitAll()
                //其他要认证后才可以访问，如 /member
                .anyRequest().authenticated().and().logout()
                //当前应用退出后，会交给某个处理
                // 请求认证服务器将用户进行退出
                .logoutSuccessUrl(ssoServerUrl+"/logout")
                .and().headers().frameOptions().disable()//防止iframe
                .and().csrf().disable();//防止跨域伪造
    }

    /**
     * 一般是针对静态资源放行
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web)
    {
        web.ignoring().antMatchers("/jquery/**", "/layuiadmin/**", "/myself/**");
    }
}
