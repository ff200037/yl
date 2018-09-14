package com.yile.micro.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yile.micro.common.utils.FastJson;
import com.yile.micro.common.utils.Json;

/**
 * Json 转换 fastjson 实现.
 */
public class FastJson extends Json {
	
	public static FastJson getJson() {
		return new FastJson();
	}
	
	public String toJson(Object object) {
		// 优先使用对象级的属性 datePattern, 然后才是全局性的 defaultDatePattern
		String dp = datePattern != null ? datePattern : getDefaultDatePattern();
		if (dp == null) {
			return JSON.toJSONString(object);
		} else {
			return JSON.toJSONStringWithDateFormat(object, dp, SerializerFeature.WriteDateUseDateFormat);	
		}
	}
	
	public <T> T parse(String jsonString, Class<T> type) {
		return JSON.parseObject(jsonString, type);
	}
}


