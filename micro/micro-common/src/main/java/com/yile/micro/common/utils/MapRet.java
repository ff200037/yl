package com.yile.micro.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.yile.micro.common.utils.MapRet;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class MapRet extends HashMap {
	
	private static final String STATE = "returnCode";
	private static final String STATE_OK = "10000";
	private static final String STATE_FAIL = "30000";
	
	// 以下为旧工作模式下的常量名
	private static final String OLD_STATE_OK = "isOk";
	private static final String OLD_STATE_FAIL = "isFail";
	
	// 默认为新工作模式
	private static boolean newWorkMode = true;
	
	public static void setToOldWorkMode() {
		newWorkMode = false;
	}
	
	public MapRet() {
	}
	
	public static MapRet by(Object key, Object value) {
		return new MapRet().set(key, value);
	}
	
	public static MapRet create(Object key, Object value) {
		return new MapRet().set(key, value);
	}
	
	public static MapRet create() {
		return new MapRet();
	}
	
	public static MapRet ok() {
		return new MapRet().setOk();
	}
	
	public static MapRet ok(Object key, Object value) {
		return ok().set(key, value);
	}
	
	public static MapRet fail() {
		return new MapRet().setFail();
	}
	
	public static MapRet fail(Object key, Object value) {
		return fail().set(key, value);
	}
	
	public MapRet setOk() {
		if (newWorkMode) {
			super.put(STATE, STATE_OK);
		} else {
			super.put(OLD_STATE_OK, Boolean.TRUE);
			super.put(OLD_STATE_FAIL, Boolean.FALSE);
		}
		return this;
	}
	
	public MapRet setFail() {
		if (newWorkMode) {
			super.put(STATE, STATE_FAIL);
		} else {
			super.put(OLD_STATE_FAIL, Boolean.TRUE);
			super.put(OLD_STATE_OK, Boolean.FALSE);
		}
		return this;
	}
	
	public boolean isOk() {
		if (newWorkMode) {
			Object state = get(STATE);
			if (STATE_OK.equals(state)) {
				return true;
			}
			if (STATE_FAIL.equals(state)) {
				return false;
			}
		} else {
			Boolean isOk = (Boolean)get(OLD_STATE_OK);
			if (isOk != null) {
				return isOk;
			}
			Boolean isFail = (Boolean)get(OLD_STATE_FAIL);
			if (isFail != null) {
				return !isFail;
			}
		}
		
		throw new IllegalStateException("调用 isOk() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
	}
	
	public boolean isFail() {
		if (newWorkMode) {
			Object state = get(STATE);
			if (STATE_FAIL.equals(state)) {
				return true;
			}
			if (STATE_OK.equals(state)) {
				return false;
			}
		} else {
			Boolean isFail = (Boolean)get(OLD_STATE_FAIL);
			if (isFail != null) {
				return isFail;
			}
			Boolean isOk = (Boolean)get(OLD_STATE_OK);
			if (isOk != null) {
				return !isOk;
			}
		}
		
		throw new IllegalStateException("调用 isFail() 之前，必须先调用 ok()、fail() 或者 setOk()、setFail() 方法");
	}
	
	public MapRet set(Object key, Object value) {
		super.put(key, value);
		return this;
	}
	
	public MapRet set(Map map) {
		super.putAll(map);
		return this;
	}
	
	public MapRet set(MapRet ret) {
		super.putAll(ret);
		return this;
	}
	
	public MapRet delete(Object key) {
		super.remove(key);
		return this;
	}
	
	public <T> T getAs(Object key) {
		return (T)get(key);
	}
	
	public String getStr(Object key) {
		Object s = get(key);
		return s != null ? s.toString() : null;
	}
	
	public Integer getInt(Object key) {
		Number n = (Number)get(key);
		return n != null ? n.intValue() : null;
	}
	
	public Long getLong(Object key) {
		Number n = (Number)get(key);
		return n != null ? n.longValue() : null;
	}
	
	public Number getNumber(Object key) {
		return (Number)get(key);
	}
	
	public Boolean getBoolean(Object key) {
		return (Boolean)get(key);
	}
	
	/**
	 * key 存在，并且 value 不为 null
	 */
	public boolean notNull(Object key) {
		return get(key) != null;
	}
	
	/**
	 * key 不存在，或者 key 存在但 value 为null
	 */
	public boolean isNull(Object key) {
		return get(key) == null;
	}
	
	/**
	 * key 存在，并且 value 为 true，则返回 true
	 */
	public boolean isTrue(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == true));
	}
	
	/**
	 * key 存在，并且 value 为 false，则返回 true
	 */
	public boolean isFalse(Object key) {
		Object value = get(key);
		return (value instanceof Boolean && ((Boolean)value == false));
	}
	
	public boolean equals(Object ret) {
		return ret instanceof MapRet && super.equals(ret);
	}
}


