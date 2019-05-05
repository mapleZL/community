package com.ejavashop.entity.order;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>send_goods_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>bookingGoodsId</td><td>{@link java.lang.Integer}</td><td>booking_goods_id</td><td>int</td><td>预约发货ID</td></tr>
 *   <tr><td>sku</td><td>{@link java.lang.String}</td><td>sku</td><td>varchar</td><td>商品编码</td></tr>
 *   <tr><td>deliveredSum</td><td>{@link java.lang.Integer}</td><td>delivered_sum</td><td>int</td><td>已发货数量</td></tr>
 * </table>
 *
 */
public class SendGoodsRecord implements Serializable {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
 	private java.lang.Integer bookingGoodsId;
 	private java.lang.String sku;
 	private java.lang.Integer deliveredSum;
 	private java.lang.Integer orderGoodsId;
 		
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
     * 获取预约发货ID
     */
	public java.lang.Integer getBookingGoodsId(){
		return this.bookingGoodsId;
	}
 		
	/**
     * 设置预约发货ID
     */
	public void setBookingGoodsId(java.lang.Integer bookingGoodsId){
		this.bookingGoodsId = bookingGoodsId;
	}
 		
	/**
     * 获取商品编码
     */
	public java.lang.String getSku(){
		return this.sku;
	}
 		
	/**
     * 设置商品编码
     */
	public void setSku(java.lang.String sku){
		this.sku = sku;
	}
 		
	/**
     * 获取已发货数量
     */
	public java.lang.Integer getDeliveredSum(){
		return this.deliveredSum;
	}
 		
	/**
     * 设置已发货数量
     */
	public void setDeliveredSum(java.lang.Integer deliveredSum){
		this.deliveredSum = deliveredSum;
	}

	public java.lang.Integer getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(java.lang.Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	
 }