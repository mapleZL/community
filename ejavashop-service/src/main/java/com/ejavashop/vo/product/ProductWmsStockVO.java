package com.ejavashop.vo.product;

import java.io.Serializable;

public class ProductWmsStockVO implements Serializable {

	private static final long serialVersionUID = 1L;
    
	//库存
	private Integer stock;
	//状态
	private Integer state;
	//货主
	private String company;
	//sku
	private String sku;
	
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
}
