package com.phkj.entity.product;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>order_receipt_detail</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>预约入库单号</td></tr>
 *   <tr><td>SKU</td><td>{@link java.lang.Integer}</td><td>SKU</td><td>int</td><td>SKU</td></tr>
 *   <tr><td>numberProbaby</td><td>{@link java.lang.Integer}</td><td>number_probaby</td><td>int</td><td>预计入库数量</td></tr>
 * </table>
 *
 */
public class OrderReceiptDetail implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -9035199841708630880L;
    private java.lang.Integer id;
 	private java.lang.String ordersId;
 	private java.lang.String SKU;
 	private java.lang.Integer numberProbaby;
 	
 		
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
     * 获取预约入库单号
     */
	public java.lang.String getOrdersId(){
		return this.ordersId;
	}
 		
	/**
     * 设置预约入库单号
     */
	public void setOrdersId(java.lang.String ordersId){
		this.ordersId = ordersId;
	}
 		
	/**
     * 获取SKU
     */
	public java.lang.String getSKU(){
		return this.SKU;
	}
 		
	/**
     * 设置SKU
     */
	public void setSKU(java.lang.String SKU){
		this.SKU = SKU;
	}
 		
	/**
     * 获取预计入库数量
     */
	public java.lang.Integer getNumberProbaby(){
		return this.numberProbaby;
	}
 		
	/**
     * 设置预计入库数量
     */
	public void setNumberProbaby(java.lang.Integer numberProbaby){
		this.numberProbaby = numberProbaby;
	}
 }