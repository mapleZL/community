package com.ejavashop.vo.product;

import java.io.Serializable;

public class ListProductPriceVO3 implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6909629667389750670L;
	private Integer id;
	private String sellerName;
	private String productCode;
	private Integer number;
	private String state1;
	private String state2;
	private String state3;
	private String price1;
	private String price2;
	private String price3;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getState1() {
		return state1;
	}
	public void setState1(String state1) {
		this.state1 = state1;
	}
	public String getState2() {
		return state2;
	}
	public void setState2(String state2) {
		this.state2 = state2;
	}
	public String getState3() {
		return state3;
	}
	public void setState3(String state3) {
		this.state3 = state3;
	}
	public String getPrice1() {
		return price1;
	}
	public void setPrice1(String price1) {
		this.price1 = price1;
	}
	public String getPrice2() {
		return price2;
	}
	public void setPrice2(String price2) {
		this.price2 = price2;
	}
	public String getPrice3() {
		return price3;
	}
	public void setPrice3(String price3) {
		this.price3 = price3;
	}
}
