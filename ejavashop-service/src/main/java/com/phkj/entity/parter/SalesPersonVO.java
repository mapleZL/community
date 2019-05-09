package com.phkj.entity.parter;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesPersonVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1732319215486730610L;
	
	private String userName;
	private String mobile;
	private String memberName;
	private String addressInfo;
	private String addressAll;
	private BigDecimal sales =  new BigDecimal(0);
	private Integer number;
	private String ordersTime;
	private Integer sort;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAddressInfo() {
		return addressInfo;
	}
	public void setAddressInfo(String addressInfo) {
		this.addressInfo = addressInfo;
	}
	public String getAddressAll() {
		return addressAll;
	}
	public void setAddressAll(String addressAll) {
		this.addressAll = addressAll;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getOrdersTime() {
		return ordersTime;
	}
	public void setOrdersTime(String ordersTime) {
		this.ordersTime = ordersTime;
	}
}
