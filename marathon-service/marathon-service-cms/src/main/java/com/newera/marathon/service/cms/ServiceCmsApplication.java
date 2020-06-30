package com.newera.marathon.service.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@MapperScan({"com.newera.marathon.service.*.mapper"})
@EnableFeignClients(basePackages = {"com.newera.marathon.microface.cos"})
@ComponentScan(basePackages = {"com.newera.marathon.microface.cos", "com.spaking.boot.starter", "com.newera.marathon.mq",
        "com.newera.marathon.service"})
public class ServiceCmsApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ServiceCmsApplication.class, args);
    }

}
