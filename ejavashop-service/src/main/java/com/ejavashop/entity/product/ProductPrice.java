package com.ejavashop.entity.product;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>product_price</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>price1</td><td>{@link java.math.BigDecimal}</td><td>price1</td><td>decimal</td><td>price1</td></tr>
 *   <tr><td>price1S</td><td>{@link java.lang.Integer}</td><td>price1_s</td><td>int</td><td>price1S</td></tr>
 *   <tr><td>price1E</td><td>{@link java.lang.Integer}</td><td>price1_e</td><td>int</td><td>price1E</td></tr>
 *   <tr><td>price2</td><td>{@link java.math.BigDecimal}</td><td>price2</td><td>decimal</td><td>price2</td></tr>
 *   <tr><td>price2S</td><td>{@link java.lang.Integer}</td><td>price2_s</td><td>int</td><td>price2S</td></tr>
 *   <tr><td>price2E</td><td>{@link java.lang.Integer}</td><td>price2_e</td><td>int</td><td>price2E</td></tr>
 *   <tr><td>price3</td><td>{@link java.math.BigDecimal}</td><td>price3</td><td>decimal</td><td>price3</td></tr>
 *   <tr><td>price3S</td><td>{@link java.lang.Integer}</td><td>price3_s</td><td>int</td><td>price3S</td></tr>
 *   <tr><td>price3E</td><td>{@link java.lang.Integer}</td><td>price3_e</td><td>int</td><td>price3E</td></tr>
 * </table>
 *
 */
public class ProductPrice implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 5463298062342058764L;
    private java.lang.Integer id;
 	private java.math.BigDecimal price1;
 	private java.lang.Integer price1S;
 	private java.lang.Integer price1E;
 	private java.math.BigDecimal price2;
 	private java.lang.Integer price2S;
 	private java.lang.Integer price2E;
 	private java.math.BigDecimal price3;
 	private java.lang.Integer price3S;
 	private java.lang.Integer price3E;
 	private java.math.BigDecimal priceOnly;//一口价
 	private String 				brandName;//商标名称
 	private java.math.BigDecimal percentageScale1;
 	private java.math.BigDecimal percentageScale2;
 	private java.math.BigDecimal percentageScale3;
 		
	public java.math.BigDecimal getPercentageScale1() {
        return percentageScale1;
    }

    public void setPercentageScale1(java.math.BigDecimal percentageScale1) {
        this.percentageScale1 = percentageScale1;
    }

    public java.math.BigDecimal getPercentageScale2() {
        return percentageScale2;
    }

    public void setPercentageScale2(java.math.BigDecimal percentageScale2) {
        this.percentageScale2 = percentageScale2;
    }

    public java.math.BigDecimal getPercentageScale3() {
        return percentageScale3;
    }

    public void setPercentageScale3(java.math.BigDecimal percentageScale3) {
        this.percentageScale3 = percentageScale3;
    }

    /**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取price1
     */
	public java.math.BigDecimal getPrice1(){
		return this.price1;
	}
 		
	/**
     * 设置price1
     */
	public void setPrice1(java.math.BigDecimal price1){
		this.price1 = price1;
	}
 		
	/**
     * 获取price1S
     */
	public java.lang.Integer getPrice1S(){
		return this.price1S;
	}
 		
	/**
     * 设置price1S
     */
	public void setPrice1S(java.lang.Integer price1S){
		this.price1S = price1S;
	}
 		
	/**
     * 获取price1E
     */
	public java.lang.Integer getPrice1E(){
		return this.price1E;
	}
 		
	/**
     * 设置price1E
     */
	public void setPrice1E(java.lang.Integer price1E){
		this.price1E = price1E;
	}
 		
	/**
     * 获取price2
     */
	public java.math.BigDecimal getPrice2(){
		return this.price2;
	}
 		
	/**
     * 设置price2
     */
	public void setPrice2(java.math.BigDecimal price2){
		this.price2 = price2;
	}
 		
	/**
     * 获取price2S
     */
	public java.lang.Integer getPrice2S(){
		return this.price2S;
	}
 		
	/**
     * 设置price2S
     */
	public void setPrice2S(java.lang.Integer price2S){
		this.price2S = price2S;
	}
 		
	/**
     * 获取price2E
     */
	public java.lang.Integer getPrice2E(){
		return this.price2E;
	}
 		
	/**
     * 设置price2E
     */
	public void setPrice2E(java.lang.Integer price2E){
		this.price2E = price2E;
	}
 		
	/**
     * 获取price3
     */
	public java.math.BigDecimal getPrice3(){
		return this.price3;
	}
 		
	/**
     * 设置price3
     */
	public void setPrice3(java.math.BigDecimal price3){
		this.price3 = price3;
	}
 		
	/**
     * 获取price3S
     */
	public java.lang.Integer getPrice3S(){
		return this.price3S;
	}
 		
	/**
     * 设置price3S
     */
	public void setPrice3S(java.lang.Integer price3S){
		this.price3S = price3S;
	}
 		
	/**
     * 获取price3E
     */
	public java.lang.Integer getPrice3E(){
		return this.price3E;
	}
 		
	/**
     * 设置price3E
     */
	public void setPrice3E(java.lang.Integer price3E){
		this.price3E = price3E;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public java.math.BigDecimal getPriceOnly() {
		return priceOnly;
	}

	public void setPriceOnly(java.math.BigDecimal priceOnly) {
		this.priceOnly = priceOnly;
	}
	
	
 }