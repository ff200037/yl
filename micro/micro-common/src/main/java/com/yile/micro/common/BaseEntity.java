package com.yile.micro.common;

//静态导入日期转换方法
@SuppressWarnings("serial")
public class BaseEntity implements java.io.Serializable {

	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String TIME_FORMAT = "HH:mm:ss";

	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	// 创建人姓名
	protected java.lang.String creater;
	// 创建时间
	protected java.util.Date createTime;
	// 修改人姓名
	protected java.lang.String updater;
	// 修改时间
	protected java.util.Date updateTime;

	public void setCreater(java.lang.String creater) {
		this.creater = creater;
	}

	public java.lang.String getCreater() {
		return this.creater;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setUpdater(java.lang.String updater) {
		this.updater = updater;
	}

	public java.lang.String getUpdater() {
		return this.updater;
	}

	public void setUpdateTime(java.util.Date updateTime) {
		this.updateTime = updateTime;
	}

	public java.util.Date getUpdateTime() {
		return this.updateTime;
	}

	public static String date2String(java.util.Date date, String dateFormat) {
		return null;// DateConvertUtils.format(date,dateFormat);
	}

	public static <T extends java.util.Date> T string2Date(String dateString,
			String dateFormat, Class<T> targetResultType) {
		return null;// DateConvertUtils.parse(dateString,dateFormat,targetResultType);
	}
}
