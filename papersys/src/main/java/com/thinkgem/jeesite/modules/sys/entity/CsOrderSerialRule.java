/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单编规则Entity
 * @author gaozy
 * @version 2016-10-26
 */
public class CsOrderSerialRule extends DataEntity<CsOrderSerialRule> {
	
	private static final long serialVersionUID = 1L;
	private String orgCode;		// 公司简码
	private String shopCode;		// 店铺简码
	private String symbol1;		// 连接符1
	private String dateFormat;		// 日期格式
	private String symbol2;		// 连接符2
	private String symbol3;		// 连接符3
	
	public CsOrderSerialRule() {
		super();
	}

	public CsOrderSerialRule(String id){
		super(id);
	}

	@Length(min=0, max=50, message="公司简码长度必须介于 0 和 50 之间")
	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	
	@Length(min=0, max=50, message="店铺简码长度必须介于 0 和 50 之间")
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	
	@Length(min=0, max=10, message="连接符1长度必须介于 0 和 10 之间")
	public String getSymbol1() {
		return symbol1;
	}

	public void setSymbol1(String symbol1) {
		this.symbol1 = symbol1;
	}
	
	@Length(min=0, max=20, message="日期格式长度必须介于 0 和 20 之间")
	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	@Length(min=0, max=10, message="连接符2长度必须介于 0 和 10 之间")
	public String getSymbol2() {
		return symbol2;
	}

	public void setSymbol2(String symbol2) {
		this.symbol2 = symbol2;
	}
	
	@Length(min=0, max=10, message="连接符3长度必须介于 0 和 10 之间")
	public String getSymbol3() {
		return symbol3;
	}

	public void setSymbol3(String symbol3) {
		this.symbol3 = symbol3;
	}
	
}