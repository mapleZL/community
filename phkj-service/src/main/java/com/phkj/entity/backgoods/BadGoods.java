package com.phkj.entity.backgoods;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>bad_goods</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>主键</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>订单id</td></tr>
 *   <tr><td>ordersSn</td><td>{@link java.lang.String}</td><td>orders_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品id</td></tr>
 *   <tr><td>productSku</td><td>{@link java.lang.String}</td><td>product_sku</td><td>varchar</td><td>商品sku</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>供应商id</td></tr>
 *   <tr><td>badNum</td><td>{@link java.lang.Integer}</td><td>bad_num</td><td>int</td><td>次品数量</td></tr>
 *   <tr><td>productName</td><td>{@link java.lang.String}</td><td>product_name</td><td>varchar</td><td>商品名称</td></tr>
 *   <tr><td>sellerName</td><td>{@link java.lang.String}</td><td>seller_name</td><td>varchar</td><td>供应商名称</td></tr>
 *   <tr><td>backGoodsId</td><td>{@link java.lang.Integer}</td><td>back_goods_id</td><td>int</td><td>退货id</td></tr>
 * </table>
 *
 */
public class BadGoods implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer ordersId;
 	private java.lang.String ordersSn;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.Integer productId;
 	private java.lang.String productSku;
 	private java.lang.Integer sellerId;
 	private java.lang.Integer badNum;
 	private java.lang.String productName;
 	private java.lang.String sellerName;
 	private java.lang.Integer backGoodsId;
 	
 		
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
     * 获取订单id
     */
	public java.lang.Integer getOrdersId(){
		return this.ordersId;
	}
 		
	/**
     * 设置订单id
     */
	public void setOrdersId(java.lang.Integer ordersId){
		this.ordersId = ordersId;
	}
 		
	/**
     * 获取订单号
     */
	public java.lang.String getOrdersSn(){
		return this.ordersSn;
	}
 		
	/**
     * 设置订单号
     */
	public void setOrdersSn(java.lang.String ordersSn){
		this.ordersSn = ordersSn;
	}
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取updateTime
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置updateTime
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
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
     * 获取供应商id
     */
	public java.lang.Integer getSellerId(){
		return this.sellerId;
	}
 		
	/**
     * 设置供应商id
     */
	public void setSellerId(java.lang.Integer sellerId){
		this.sellerId = sellerId;
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
     * 获取商品名称
     */
	public java.lang.String getProductName(){
		return this.productName;
	}
 		
	/**
     * 设置商品名称
     */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
 		
	/**
     * 获取供应商名称
     */
	public java.lang.String getSellerName(){
		return this.sellerName;
	}
 		
	/**
     * 设置供应商名称
     */
	public void setSellerName(java.lang.String sellerName){
		this.sellerName = sellerName;
	}
 		
	/**
     * 获取退货id
     */
	public java.lang.Integer getBackGoodsId(){
		return this.backGoodsId;
	}
 		
	/**
     * 设置退货id
     */
	public void setBackGoodsId(java.lang.Integer backGoodsId){
		this.backGoodsId = backGoodsId;
	}
 }