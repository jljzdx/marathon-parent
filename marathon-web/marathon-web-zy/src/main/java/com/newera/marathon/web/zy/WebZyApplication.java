package com.newera.marathon.web.zy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spaking.boot.starter", "com.newera.marathon.microface.cms", "com.newera.marathon.web.zy"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.newera.marathon.microface.cms"})
public class WebZyApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(WebZyApplication.class, args);
    }
}
