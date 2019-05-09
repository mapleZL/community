package com.phkj.entity.parter;

import java.io.Serializable;
import java.math.BigDecimal;

public class SalesDetailsVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4411933118603801144L;
	
	
	private Integer ordersId;
	private String orderSn;
	private String name;
	private BigDecimal moneyProduct = new BigDecimal(0);
	private BigDecimal servicePrice = new BigDecimal(0);
	private BigDecimal labelPrice = new BigDecimal(0);
	private BigDecimal moneyLogistics = new BigDecimal(0);
	private BigDecimal moneyOrder = new BigDecimal(0);
	private BigDecimal returnMoneyOrder = new BigDecimal(0);
	private BigDecimal endMoney = new BigDecimal(0);
	private String ordersTime;
	private Integer number = 0;
	private Integer returnNumber = 0;
	private String addressAll;
	
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getMoneyProduct() {
		return moneyProduct;
	}
	public void setMoneyProduct(BigDecimal moneyProduct) {
		this.moneyProduct = moneyProduct;
	}
	public BigDecimal getServicePrice() {
		return servicePrice;
	}
	public void setServicePrice(BigDecimal servicePrice) {
		this.servicePrice = servicePrice;
	}
	public BigDecimal getLabelPrice() {
		return labelPrice;
	}
	public void setLabelPrice(BigDecimal labelPrice) {
		this.labelPrice = labelPrice;
	}
	public BigDecimal getMoneyLogistics() {
		return moneyLogistics;
	}
	public void setMoneyLogistics(BigDecimal moneyLogistics) {
		this.moneyLogistics = moneyLogistics;
	}
	public BigDecimal getMoneyOrder() {
		return moneyOrder;
	}
	public void setMoneyOrder(BigDecimal moneyOrder) {
		this.moneyOrder = moneyOrder;
	}
	public BigDecimal getReturnMoneyOrder() {
		return returnMoneyOrder;
	}
	public void setReturnMoneyOrder(BigDecimal returnMoneyOrder) {
		this.returnMoneyOrder = returnMoneyOrder;
	}
	public BigDecimal getEndMoney() {
		return endMoney;
	}
	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getReturnNumber() {
		return returnNumber;
	}
	public void setReturnNumber(Integer returnNumber) {
		this.returnNumber = returnNumber;
	}
	public String getOrdersTime() {
		return ordersTime;
	}
	public void setOrdersTime(String ordersTime) {
		this.ordersTime = ordersTime;
	}
	public String getAddressAll() {
		return addressAll;
	}
	public void setAddressAll(String addressAll) {
		this.addressAll = addressAll;
	}
}
