package com.phkj.vo.order;

public class SendingGoodsVO {
	 private Integer productId; //新增商品sku的ID，在front三方仓储计算运费使用【仝照美】
	 
	 private Integer fullContainerQuantity;
	 
     private String productName;
     
     private String normName;
     
     private String productSku;
     
     private String images;
     
     private Integer number;
     
     private Integer deliveredSum;
     
     private Integer deliveredNum;
     
     private Integer orderGoodsId; 
     

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getNormName() {
		return normName;
	}

	public void setNormName(String normName) {
		this.normName = normName;
	}

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getDeliveredSum() {
		return deliveredSum;
	}

	public void setDeliveredSum(Integer deliveredSum) {
		this.deliveredSum = deliveredSum;
	}

	public Integer getDeliveredNum() {
		return deliveredNum;
	}

	public void setDeliveredNum(Integer deliveredNum) {
		this.deliveredNum = deliveredNum;
	}

	public Integer getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getFullContainerQuantity() {
		return fullContainerQuantity;
	}

	public void setFullContainerQuantity(Integer fullContainerQuantity) {
		this.fullContainerQuantity = fullContainerQuantity;
	}
	
}
