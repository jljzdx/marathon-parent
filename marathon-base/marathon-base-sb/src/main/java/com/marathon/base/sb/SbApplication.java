package com.marathon.base.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCaching
@RestController
public class SbApplication
{

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SbApplication.class, args);
	}

	@GetMapping("/json")
	public String getJson(){
		return "test request";
	}

	@GetMapping("/ept")
	public String testException(){
		System.out.println(0/0);
		return "test exception";
	}

	@GetMapping("/trd")
	public String testThread(){
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i = 0; i < 100; i++)
				{
					System.out.println(i);
				}
			}
		});
		return "test thread";
	}

}
