package com.newera.marathon.web.zy.config;

import com.spaking.boot.starter.cas.filter.SsoWebFilter;
import com.spaking.boot.starter.cas.utils.SsoConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SsoConfig {

    @Autowired
    private SsoConfigProperties ssoConfigProperties;

    @Bean
    public FilterRegistrationBean xxlSsoFilterRegistration() {
        // xxl-sso, filter init
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setName("SsoWebFilter");
        registration.setOrder(1);
        registration.addUrlPatterns("/*");
        registration.setFilter(new SsoWebFilter());
        registration.addInitParameter(SsoConstant.SSO_SERVERS, ssoConfigProperties.getUrls());
        registration.addInitParameter(SsoConstant.SSO_LOGOUT_PATH, ssoConfigProperties.getLogoutPath());
        registration.addInitParameter(SsoConstant.SSO_EXCLUDED_PATHS, ssoConfigProperties.getExcludedPaths());
        return registration;
    }
}
