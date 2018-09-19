package com.yile.micro.coupon.bean.dto;

import java.io.Serializable;

/**
 * @author moqp
 * @version 创建时间：2018年9月19日 上午8:56:49
 */
public class CouponHistoryDTO implements Serializable{
		
		private static final long serialVersionUID = -591053821727824466L;
		//主键ID
		private java.lang.Long id;
		//优惠券ID
		private java.lang.Long cpnId;
		//推广数量
		private java.lang.Long spreadNum;
		//推广方式： 1.发放  2.客户领取
		private java.lang.String spreadMode;
		//推广渠道： 1.APP  2.微信公众号  3.手机短信
		private java.lang.String spreadChannel;
		//推广时间
		private java.util.Date spreadTime;
		//推广人姓名
		private String spreadPeople;//推广人姓名
		//推广状态： 1.推广  2.未推广
		private java.lang.String spreadStatus;
		//entity cloums end
		//out cloums start
		private String areaRange;//活动区域
		private String custRange;// 优惠对象
		
		public java.lang.Long getId() {
			return id;
		}
		public void setId(java.lang.Long id) {
			this.id = id;
		}
		public java.lang.Long getCpnId() {
			return cpnId;
		}
		public void setCpnId(java.lang.Long cpnId) {
			this.cpnId = cpnId;
		}
		public java.lang.Long getSpreadNum() {
			return spreadNum;
		}
		public void setSpreadNum(java.lang.Long spreadNum) {
			this.spreadNum = spreadNum;
		}
		public java.lang.String getSpreadMode() {
			return spreadMode;
		}
		public void setSpreadMode(java.lang.String spreadMode) {
			this.spreadMode = spreadMode;
		}
		public java.lang.String getSpreadChannel() {
			return spreadChannel;
		}
		public void setSpreadChannel(java.lang.String spreadChannel) {
			this.spreadChannel = spreadChannel;
		}
		public java.util.Date getSpreadTime() {
			return spreadTime;
		}
		public void setSpreadTime(java.util.Date spreadTime) {
			this.spreadTime = spreadTime;
		}
		public String getSpreadPeople() {
			return spreadPeople;
		}
		public void setSpreadPeople(String spreadPeople) {
			this.spreadPeople = spreadPeople;
		}
		public java.lang.String getSpreadStatus() {
			return spreadStatus;
		}
		public void setSpreadStatus(java.lang.String spreadStatus) {
			this.spreadStatus = spreadStatus;
		}
		public String getAreaRange() {
			return areaRange;
		}
		public void setAreaRange(String areaRange) {
			this.areaRange = areaRange;
		}
		public String getCustRange() {
			return custRange;
		}
		public void setCustRange(String custRange) {
			this.custRange = custRange;
		}
		
		
}


