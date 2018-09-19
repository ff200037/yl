package com.yile.micro.common;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/**
 * 获取properties中的配置属性
 * @author 000
 *
 */
@Component
public class PropertiesMap {
	@Autowired
	private Environment env;
	private static Environment environment;

	@PostConstruct
	public void setEnvironment() {
		environment = env;
	}
	
	public static String getProperties(String key) {
		return StringUtils.isBlank(key)?"":environment.getProperty(key);
	}
}
