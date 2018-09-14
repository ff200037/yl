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
@ImportResource({"classpath:coupon-dubbo.xml"})
@MapperScan("com.yile.micro.coupon.mapper")
public class CouponApplication extends BaseApplication{
	public static void main(String[] args) {
		SpringApplication.run(CouponApplication.class, args);
      
	}
}