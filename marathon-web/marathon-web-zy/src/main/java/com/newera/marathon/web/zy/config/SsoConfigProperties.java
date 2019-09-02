package com.newera.marathon.web.zy.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("sso.server")
@RefreshScope
@Data
public class SsoConfigProperties {
    private String urls = "";

    private String logoutPath = "";

    private String excludedPaths = "";
}
