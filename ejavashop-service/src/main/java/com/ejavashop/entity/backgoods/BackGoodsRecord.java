package com.ejavashop.entity.backgoods;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>back_goods_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>主键</td></tr>
 *   <tr><td>backGoodsId</td><td>{@link java.lang.Integer}</td><td>back_goods_id</td><td>int</td><td>退货商品id</td></tr>
 *   <tr><td>productSku</td><td>{@link java.lang.String}</td><td>product_sku</td><td>varchar</td><td>商品sku</td></tr>
 *   <tr><td>wellNum</td><td>{@link java.lang.Integer}</td><td>well_num</td><td>int</td><td>正品数量</td></tr>
 *   <tr><td>badNum</td><td>{@link java.lang.Integer}</td><td>bad_num</td><td>int</td><td>次品数量</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品id</td></tr>
 *   <tr><td>backNum</td><td>{@link java.lang.Integer}</td><td>back_num</td><td>int</td><td>退货总数量</td></tr>
 * </table>
 *
 */
public class BackGoodsRecord implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer backGoodsId;
 	private java.lang.String productSku;
 	private java.lang.Integer wellNum;
 	private java.lang.Integer badNum;
 	private java.lang.Integer productId;
 	private java.lang.Integer backNum;
 	
 		
	/**
     * 获取主键
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置主键
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取退货商品id
     */
	public java.lang.Integer getBackGoodsId(){
		return this.backGoodsId;
	}
 		
	/**
     * 设置退货商品id
     */
	public void setBackGoodsId(java.lang.Integer backGoodsId){
		this.backGoodsId = backGoodsId;
	}
 		
	/**
     * 获取商品sku
     */
	public java.lang.String getProductSku(){
		return this.productSku;
	}
 		
	/**
     * 设置商品sku
     */
	public void setProductSku(java.lang.String productSku){
		this.productSku = productSku;
	}
 		
	/**
     * 获取正品数量
     */
	public java.lang.Integer getWellNum(){
		return this.wellNum;
	}
 		
	/**
     * 设置正品数量
     */
	public void setWellNum(java.lang.Integer wellNum){
		this.wellNum = wellNum;
	}
 		
	/**
     * 获取次品数量
     */
	public java.lang.Integer getBadNum(){
		return this.badNum;
	}
 		
	/**
     * 设置次品数量
     */
	public void setBadNum(java.lang.Integer badNum){
		this.badNum = badNum;
	}
 		
	/**
     * 获取商品id
     */
	public java.lang.Integer getProductId(){
		return this.productId;
	}
 		
	/**
     * 设置商品id
     */
	public void setProductId(java.lang.Integer productId){
		this.productId = productId;
	}
 		
	/**
     * 获取退货总数量
     */
	public java.lang.Integer getBackNum(){
		return this.backNum;
	}
 		
	/**
     * 设置退货总数量
     */
	public void setBackNum(java.lang.Integer backNum){
		this.backNum = backNum;
	}
 }