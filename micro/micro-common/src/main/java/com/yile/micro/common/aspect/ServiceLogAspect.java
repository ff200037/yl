package com.yile.micro.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
@Order(2)
public class ServiceLogAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(public * com.yile.micro.*.service.*.*(..))")
	public void servicelog() {
	}

	@Before("servicelog()")
	public void dobefore(JoinPoint point) {
		printLog(point);
		logger.info("service请求参数：{}",JSON.toJSONString(point.getArgs()));
	}

	private void printLog(JoinPoint point) {
		String class_name = point.getTarget().getClass().getName();
        String method_name = point.getSignature().getName();
        //重新定义日志
        logger.info("service类名：{}",class_name);
        logger.info("service方法名：{}",method_name);
	}
	
	@AfterReturning(returning = "ret", pointcut = "servicelog()")
    public void doafter(JoinPoint point,Object ret) {
		logger.info("service返回值:{}",JSON.toJSONString(ret));
	}
	@AfterThrowing(throwing = "e", pointcut = "servicelog()")
	public void dothrowing(JoinPoint point,Throwable e) {
		printLog(point);
		logger.info("service异常信息:{}",e.getMessage());
	}
}
