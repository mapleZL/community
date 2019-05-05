package com.ejavashop.entity.parter;

import java.io.Serializable;
import java.math.BigDecimal;

public class SumParterSaleVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2546872473952624799L;
	//月份
	private Integer month;
	//客户数
	private Integer bussineeNo = 0;
	//交易额
	private BigDecimal saleMoney = new BigDecimal(0);
	//新增客户数
	private Integer newBussineeNo = 0 ;
	//新增交易额
	private BigDecimal newSaleMoney = new BigDecimal(0);
	//年
	private String years;
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getBussineeNo() {
		return bussineeNo;
	}
	public void setBussineeNo(Integer bussineeNo) {
		this.bussineeNo = bussineeNo;
	}
	public BigDecimal getSaleMoney() {
		return saleMoney;
	}
	public void setSaleMoney(BigDecimal saleMoney) {
		this.saleMoney = saleMoney;
	}
	public Integer getNewBussineeNo() {
		return newBussineeNo;
	}
	public void setNewBussineeNo(Integer newBussineeNo) {
		this.newBussineeNo = newBussineeNo;
	}
	public BigDecimal getNewSaleMoney() {
		return newSaleMoney;
	}
	public void setNewSaleMoney(BigDecimal newSaleMoney) {
		this.newSaleMoney = newSaleMoney;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}

}
