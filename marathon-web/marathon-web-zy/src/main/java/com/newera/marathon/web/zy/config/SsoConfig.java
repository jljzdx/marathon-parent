package com.newera.marathon.web.zy.config;

import com.spaking.boot.starter.cas.filter.SsoWebFilter;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig {

    @Value("${sso.server}")
    private String ssoServer;

    @Value("${sso.logout.path}")
    private String ssoLogoutPath;

    @Value("${sso.excluded.paths}")
    private String ssoExcludedPaths;

    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {
        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("SsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new SsoWebFilter());
        registration.addInitParameter(SsoConstant.SSO_SERVER, ssoServer);
        registration.addInitParameter(SsoConstant.SSO_LOGOUT_PATH, ssoLogoutPath);
        registration.addInitParameter(SsoConstant.SSO_EXCLUDED_PATHS, ssoExcludedPaths);
        return registration;
    }
}
