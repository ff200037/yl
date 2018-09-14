package com.yile.micro;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ImportResource({"classpath:order-service-dubbo.xml"})
@MapperScan("com.yile.micro.order.mapper")
public class OrderApplication extends BaseApplication{
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(OrderApplication.class);
        springApplication.run(args);
	}
	
	
}