package com.ejavashop.entity.parter;

import java.io.Serializable;
import java.math.BigDecimal;

import com.ejavashop.core.StringUtil;

public class CategorySalesVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1555220401275158553L;
	private String brandName;
	private String brandName1;
	private Integer memberNo = 0;
	private Integer returnMemberNo = 0;
	private Integer skuNo = 0;
	private Integer returnSkuNo = 0;
	private BigDecimal salesNo = new BigDecimal(0);
	private BigDecimal returnSalesNo = new BigDecimal(0);
	private Integer no = 0;
	private BigDecimal sales = new BigDecimal(0);
	public String getBrandName() {
		if(StringUtil.isEmpty(brandName)){
			return brandName1;
		}else{
			return brandName;
		}
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Integer memberNo) {
		this.memberNo = memberNo;
	}
	public Integer getReturnMemberNo() {
		return returnMemberNo;
	}
	public void setReturnMemberNo(Integer returnMemberNo) {
		this.returnMemberNo = returnMemberNo;
	}
	public Integer getSkuNo() {
		return skuNo;
	}
	public void setSkuNo(Integer skuNo) {
		this.skuNo = skuNo;
	}
	public Integer getReturnSkuNo() {
		return returnSkuNo;
	}
	public void setReturnSkuNo(Integer returnSkuNo) {
		this.returnSkuNo = returnSkuNo;
	}
	public BigDecimal getSalesNo() {
		return salesNo;
	}
	public void setSalesNo(BigDecimal salesNo) {
		this.salesNo = salesNo;
	}
	public BigDecimal getReturnSalesNo() {
		return returnSalesNo;
	}
	public void setReturnSalesNo(BigDecimal returnSalesNo) {
		this.returnSalesNo = returnSalesNo;
	}
	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public BigDecimal getSales() {
		return sales;
	}
	public void setSales(BigDecimal sales) {
		this.sales = sales;
	}
	public String getBrandName1() {
		return brandName1;
	}
	public void setBrandName1(String brandName1) {
		this.brandName1 = brandName1;
	}
}
