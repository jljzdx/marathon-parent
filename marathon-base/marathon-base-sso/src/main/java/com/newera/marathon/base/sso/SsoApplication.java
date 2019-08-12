package com.newera.marathon.base.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.spaking.boot.starter","com.newera.marathon.microface.system","com.newera.marathon.base.sso"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.newera.marathon.microface.system"})
public class SsoApplication {

	public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
	}

}