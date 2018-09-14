package com.yile.micro.coupon.bean;


import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yile.micro.coupon.bean.base.BaseCoupon;

/**
 * 优惠券表 -- 实体
 * t_coupon:优惠券表
 */
@SuppressWarnings("serial")
public class Coupon extends BaseCoupon implements Serializable{
	
	public String toString() {
		return new ToStringBuilder(this)
			.append("Id",getId())
			.append("CpnName",getCpnName())
			.append("CpnNo",getCpnNo())
			.append("CpnType",getCpnType())
			.append("CpnAmt",getCpnAmt())
			.append("DateType",getDateType())
			.append("EftDate",getEftDate())
			.append("InvDate",getInvDate())
			.append("EftLen",getEftLen())
			.append("CpnNum",getCpnNum())
			.append("LimGetNum",getLimGetNum())
			.append("AlrdyGetNum",getAlrdyGetNum())
			.append("CpnMarks",getCpnMarks())
			.append("CpnStatus",getCpnStatus())
			.append("UseCondType",getUseCondType())
			.append("MjMoney",getMjMoney())
			.append("CustRangeType",getCustRangeType())
			.append("ProdRangeType",getProdRangeType())
			.append("AreaRangeType",getAreaRangeType())
			.append("AreaRange",getAreaRange())
			.append("CustRange",getCustRange())
			.append("Pbe",getPbe())
			.append("QrCode",getQrCode())
			.append("Creater",getCreater())
			.append("CreateTime",getCreateTime())
			.append("Updater",getUpdater())
			.append("UpdateTime",getUpdateTime())
			.append("AscriptionPlatform",getAscriptionPlatform())
			.toString();
	}
	
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.append(getCpnName())
			.append(getCpnNo())
			.append(getCpnType())
			.append(getCpnAmt())
			.append(getDateType())
			.append(getEftDate())
			.append(getInvDate())
			.append(getEftLen())
			.append(getCpnNum())
			.append(getLimGetNum())
			.append(getAlrdyGetNum())
			.append(getCpnMarks())
			.append(getCpnStatus())
			.append(getUseCondType())
			.append(getMjMoney())
			.append(getCustRangeType())
			.append(getProdRangeType())
			.append(getAreaRangeType())
			.append(getAreaRange())
			.append(getCustRange())
			.append(getPbe())
			.append(getQrCode())
			.append(getCreater())
			.append(getCreateTime())
			.append(getUpdater())
			.append(getUpdateTime())
			.append(getAscriptionPlatform())
			.toHashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Coupon == false) return false;
		if(this == obj) return true;
		Coupon other = (Coupon)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.append(getCpnName(),other.getCpnName())
			.append(getCpnNo(),other.getCpnNo())
			.append(getCpnType(),other.getCpnType())
			.append(getCpnAmt(),other.getCpnAmt())
			.append(getDateType(),other.getDateType())
			.append(getEftDate(),other.getEftDate())
			.append(getInvDate(),other.getInvDate())
			.append(getEftLen(),other.getEftLen())
			.append(getCpnNum(),other.getCpnNum())
			.append(getLimGetNum(),other.getLimGetNum())
			.append(getAlrdyGetNum(),other.getAlrdyGetNum())
			.append(getCpnMarks(),other.getCpnMarks())
			.append(getCpnStatus(),other.getCpnStatus())
			.append(getUseCondType(),other.getUseCondType())
			.append(getMjMoney(),other.getMjMoney())
			.append(getCustRangeType(),other.getCustRangeType())
			.append(getProdRangeType(),other.getProdRangeType())
			.append(getAreaRangeType(),other.getAreaRangeType())
			.append(getAreaRange(),other.getAreaRange())
			.append(getCustRange(),other.getCustRange())
			.append(getPbe(),other.getPbe())
			.append(getQrCode(),other.getQrCode())
			.append(getCreater(),other.getCreater())
			.append(getCreateTime(),other.getCreateTime())
			.append(getUpdater(),other.getUpdater())
			.append(getUpdateTime(),other.getUpdateTime())
			.append(getAscriptionPlatform(), other.getAscriptionPlatform())
			.isEquals();
	}
}

