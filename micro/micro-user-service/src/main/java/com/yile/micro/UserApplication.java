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
@ImportResource({"classpath:user-dubbo.xml"})
@MapperScan("com.yile.micro.user.mapper")
public class UserApplication extends BaseApplication{
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(UserApplication.class);
        springApplication.run(args);
	}
}