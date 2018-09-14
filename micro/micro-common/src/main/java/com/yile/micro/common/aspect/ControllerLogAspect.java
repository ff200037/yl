package com.yile.micro.common.aspect;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

@Aspect
@Component
@Order(1)
public class ControllerLogAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired(required=false)
    private HttpServletRequest request;
	@Pointcut("execution(public * com.yile.micro.controller.*.*.*(..))")
	public void weblog() {
	}

	@Before("weblog()")
	public void dobefore(JoinPoint point) {
		Date beginTime=new Date();
		String remoteAddr=request.getRemoteAddr();//请求的IP
        String requestUri=request.getRequestURI();//请求的Uri
        String method=request.getMethod();        //请求的方法类型(post/get)
        Map<String,String[]> params=request.getParameterMap();
        logger.info("controller开始时间: {},IP: {},URI: {},TYPE: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(beginTime), remoteAddr,requestUri,method);
        logger.info("controller请求参数：{}",JSON.toJSONString(params));
	}
	
	@AfterReturning(returning = "ret", pointcut = "weblog()")
    public void doafter(Object ret) {
		Date endTime=new Date();
		logger.info("controller结束时间: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime));
		logger.info("controller返回值:{}",JSON.toJSONString(ret));
	}
	@AfterThrowing(throwing = "e", pointcut = "weblog()")
	public void dothrowing(JoinPoint point,Throwable e) {
		logger.info("controller异常信息:{}",e.getMessage());
	}
}
