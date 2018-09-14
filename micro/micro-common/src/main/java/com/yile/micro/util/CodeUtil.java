package com.yile.micro.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class CodeUtil {
	
	public static String getAppNo() {
		String format = "yyyyMMddHHmmssSSS";
		DateFormat df = new SimpleDateFormat(format);
		return df.format(new Date()) + getThree();
	}

	private static String getThree() {
		Random random = new Random();
		return String.format("%1$03d", random.nextInt(1000));
	}
	public static String get16Code() {
		String formate = "YYYYMMddHHmmss";
		SimpleDateFormat df = new SimpleDateFormat(formate);
		Random random = new Random();
		String code=df.format(new Date())+String.format("%1$02d", random.nextInt(100));
		return code;
	}
}
