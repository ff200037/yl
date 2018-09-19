package com.yile.micro.common.aspect;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

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
		if(point!=null && point.getArgs()!=null && point.getArgs().length > 0)
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
    public void doafter(Object ret) {
		if(ret!=null)
			logger.info("service返回值:{}",JSON.toJSONString(ret));
	}
	@AfterThrowing(throwing = "e", pointcut = "servicelog()")
	public void dothrowing(JoinPoint point,Throwable e) {
		if(point!=null && point.getArgs()!=null && point.getArgs().length > 0)
			printLog(point);
		logger.info("service异常:{}",JSON.toJSONString(getThrowableStrRep(e)));
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static String[] getThrowableStrRep(Throwable throwable) {
		if (throwable == null) {
			return new String[0];
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		throwable.printStackTrace(pw);
		pw.flush();
		LineNumberReader reader = new LineNumberReader(new StringReader(
				sw.toString()));
		ArrayList lines = new ArrayList();
		try {
			String line = reader.readLine();
			while (line != null) {
				lines.add(line);
				line = reader.readLine();
			}
		} catch (IOException ex) {
			lines.add(ex.toString());
		}
		String[] rep = new String[lines.size()];
		lines.toArray(rep);
		return rep;
	}
}
