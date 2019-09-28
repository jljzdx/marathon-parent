package com.newera.marathon.service.cos;

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
@EnableFeignClients(basePackages = {"com.newera.marathon.microface.cust"})
@EnableDiscoveryClient
@EnableCaching
@ComponentScan(basePackages = {"com.spaking.boot.starter","com.newera.marathon.microface.cust","com.newera.marathon.service"})
public class ServiceCosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceCosApplication.class, args);
	}

}
