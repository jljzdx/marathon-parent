package com.newera.marathon.service.cust;

import com.newera.marathon.service.cust.mapper.CustomerMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.InputStream;
import java.util.HashMap;

@MapperScan({"com.newera.marathon.service.*.mapper"})
@EnableTransactionManagement
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
@ComponentScan(basePackages = {"com.spaking.boot.starter","com.newera.marathon.service"})
public class ServiceCustApplication {

	public static void main(String[] args) throws Exception {
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession sqlSession = sqlSessionFactory.openSession();
		CustomerMapper customerMapper = sqlSession.getMapper(CustomerMapper.class);

		SpringApplication.run(ServiceCustApplication.class, args);
		HashMap map = new HashMap();
		map.get("");
	}

}
