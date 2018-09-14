package com.yile.micro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
//@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ImportResource({"classpath:web-dubbo.xml"})
@SpringBootApplication
@MapperScan("com.yile.micro.controller.system.mapper")
public class WebApplication {
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(WebApplication.class);
        springApplication.run(args);
	}
}
