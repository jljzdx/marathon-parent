package com.newera.marathon.service.system;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.newera.marathon.service.*.mapper"})
@EnableTransactionManagement
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@ComponentScan(basePackages = {"com.spaking.boot.starter","com.newera.marathon.service"})
public class ServiceSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceSystemApplication.class, args);
	}

}
