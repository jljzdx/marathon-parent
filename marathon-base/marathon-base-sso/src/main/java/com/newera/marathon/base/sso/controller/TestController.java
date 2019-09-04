package com.newera.marathon.base.sso.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class TestController {
    @Value("${env:unknow}")
    private String test;
    @Value("${spring.freemarker.charset:unknow}")
    private String charset;
    @Value("${spring.cloud.nacos.config.group:unknow}")
    private String port;

    @GetMapping("/test/env")
    public String testConfig(){
        return test;
    }
    @GetMapping("/test/fm")
    public String testFm(){
        return charset;
    }
    @GetMapping("/test/port")
    public String testPort(){
        return port;
    }
}
