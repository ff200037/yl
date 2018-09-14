package com.yile.micro.common.utils;

import com.yile.micro.common.utils.FastJson;
import com.yile.micro.common.utils.FastJsonFactory;
import com.yile.micro.common.utils.IJsonFactory;
import com.yile.micro.common.utils.Json;

/**
 * IJsonFactory 的 fastjson 实现.
 */
public class FastJsonFactory implements IJsonFactory {
	
	private static final FastJsonFactory me = new FastJsonFactory();
	
	public static FastJsonFactory me() {
		return me;
	}
	
	public Json getJson() {
		return new FastJson();
	}
	
}





