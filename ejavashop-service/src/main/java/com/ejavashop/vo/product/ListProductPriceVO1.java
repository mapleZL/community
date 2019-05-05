package com.ejavashop.vo.product;

import java.io.Serializable;

public class ListProductPriceVO1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 982478732627226147L;
	
	private Integer id;
	private String sellerName;
	private String productCode;
	private String price;
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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
