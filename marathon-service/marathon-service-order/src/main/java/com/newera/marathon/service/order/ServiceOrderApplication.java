package com.newera.marathon.service.order;

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
@EnableFeignClients(basePackages = {"com.newera.marathon.microface.cos"})
@EnableDiscoveryClient
@EnableCaching
@ComponentScan(basePackages = {
		"com.newera.marathon.microface.cos",
		"com.spaking.boot.starter",
		"com.newera.marathon.ext",
		"com.newera.marathon.mq",
		"com.newera.marathon.service"})
public class ServiceOrderApplication {

	public static void main(String[] args) {
		//System.setProperty("es.set.netty.runtime.available.processors", "false");
		SpringApplication.run(ServiceOrderApplication.class, args);
	}

}
