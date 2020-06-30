package com.newera.marathon.base.sso.config;

import com.newera.marathon.base.sso.properties.SecurityProperties;
import com.newera.marathon.base.sso.security.ImageCodeValidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author MicroBin
 * @description：安全配置类
 * @date 2020/6/18 9:28 上午
 */
@EnableWebSecurity // 包含了@Configuration注解
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsService customUserDetailService;

    // 配置文件参数
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private ImageCodeValidateFilter imageCodeValidateFilter;

    /**
     * password 密码模式要使用此认证管理器
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        // 内存方式存储用户信息
        // auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder.encode("123")).authorities("product");
        auth.userDetailsService(customUserDetailService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.addFilterBefore(imageCodeValidateFilter, UsernamePasswordAuthenticationFilter.class).formLogin() // 表单登录方式
                .loginPage(securityProperties.getAuthentication().getLoginPage())
                .loginProcessingUrl(securityProperties.getAuthentication().getLoginProcessingUrl()) // 登录表单提交处理url, 默认是/login
                .usernameParameter(securityProperties.getAuthentication().getUsernameParameter()) //默认的是 username
                .passwordParameter(securityProperties.getAuthentication().getPasswordParameter())  // 默认的是 password
                .successHandler(customAuthenticationSuccessHandler).failureHandler(customAuthenticationFailureHandler)
                .and()
                .authorizeRequests() // 认证请求
                .antMatchers(securityProperties.getAuthentication().getLoginPage(), securityProperties.getAuthentication().getImageCodeUrl(),
                             securityProperties.getAuthentication().getMobilePage(), securityProperties.getAuthentication().getMobileCodeUrl())
                .permitAll() // 放行/login/page不需要认证可访问
                .anyRequest().authenticated() //所有访问该应用的http请求都要通过身份认证才可以访问
        ;
        http.csrf().disable(); // 关闭跨站请求伪造;
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
        web.ignoring().antMatchers(securityProperties.getAuthentication().getStaticPaths());
    }
}
