package com.newera.marathon.job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
		"com.newera.marathon.microface.cos",
		"com.newera.marathon.microface.cms"})
@ComponentScan(basePackages = {
		"com.newera.marathon.job",
		"com.newera.marathon.mq",
		"com.newera.marathon.microface.cos",
		"com.newera.marathon.microface.cms"})
public class JobApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobApplication.class, args);
	}

}
