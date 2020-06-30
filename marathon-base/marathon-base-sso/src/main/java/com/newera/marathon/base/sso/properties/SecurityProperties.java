package com.newera.marathon.base.sso.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author MicroBin
 * @description：属性配置类
 * @date 2020/6/16 12:13 下午
 */
@Component
@ConfigurationProperties(prefix = "template.security")
public class SecurityProperties {

    // 会将 template.security.authentication 下面的值绑定到AuthenticationProperties对象上
    private AuthenticationProperties authentication;

    public AuthenticationProperties getAuthentication() {
        return authentication;
    }

    public void setAuthentication(AuthenticationProperties authentication) {
        this.authentication = authentication;
    }
}
