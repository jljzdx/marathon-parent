package com.newera.marathon.base.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan({"com.newera.marathon.base.*.mapper"})
@ComponentScan(basePackages = {"com.spaking.boot.starter", "com.newera.marathon.base.sso"})
@EnableDiscoveryClient
//@EnableFeignClients(basePackages = {"com.newera.marathon.microface.cms"})
public class SsoApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(SsoApplication.class, args);
    }

}