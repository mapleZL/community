package com.ejavashop.vo.product;

import java.io.Serializable;

public class ListProductPriceVO2 implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1693613211293377423L;
	
	private Integer id;
	private String sellerName;
	private String productCode;
	private Integer number;
	private String price1;
	private String price2;
	
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
}
