package com.phkj.entity.parter;

import java.io.Serializable;
import java.math.BigDecimal;

public class ParterTuijian implements Serializable{
	private static final long serialVersionUID = -3264405178563418313L;
	private String parterName;
	private String areaName;
	private BigDecimal brandSales = new BigDecimal(0);
	private BigDecimal brandPercent = new BigDecimal(0);
	private BigDecimal luowaSales = new BigDecimal(0);
	private BigDecimal luowaPercent = new BigDecimal(0);
	private BigDecimal salesSum = new BigDecimal(0);
	private BigDecimal percentSum = new BigDecimal(0);
	private String startTime;
	private String endTime;
	public String getParterName() {
		return parterName;
	}
	public void setParterName(String parterName) {
		this.parterName = parterName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public BigDecimal getBrandSales() {
		return brandSales;
	}
	public void setBrandSales(BigDecimal brandSales) {
		this.brandSales = brandSales;
	}
	public BigDecimal getBrandPercent() {
		return brandPercent;
	}
	public void setBrandPercent(BigDecimal brandPercent) {
		this.brandPercent = brandPercent;
	}
	public BigDecimal getLuowaSales() {
		return luowaSales;
	}
	public void setLuowaSales(BigDecimal luowaSales) {
		this.luowaSales = luowaSales;
	}
	public BigDecimal getLuowaPercent() {
		return luowaPercent;
	}
	public void setLuowaPercent(BigDecimal luowaPercent) {
		this.luowaPercent = luowaPercent;
	}
	public BigDecimal getSalesSum() {
		return salesSum;
	}
	public void setSalesSum(BigDecimal salesSum) {
		this.salesSum = salesSum;
	}
	public BigDecimal getPercentSum() {
		return percentSum;
	}
	public void setPercentSum(BigDecimal percentSum) {
		this.percentSum = percentSum;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
