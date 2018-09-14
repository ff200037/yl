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
@ImportResource({"classpath:system-dubbo.xml"})
@MapperScan("com.yile.micro.system.mapper")
public class SystemApplication extends BaseApplication{
	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SystemApplication.class);
        springApplication.run(args);
	}
}