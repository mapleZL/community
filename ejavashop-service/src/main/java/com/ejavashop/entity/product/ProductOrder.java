package com.ejavashop.entity.product;

import java.io.Serializable;
/**
 * 楼层排序表
 * <p>Table: <strong>product_order</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>sortType</td><td>{@link java.lang.String}</td><td>sort_type</td><td>varchar</td><td>楼层说明</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>int</td><td>排序</td></tr>
 *   <tr><td>sortType</td><td>{@link java.lang.String}</td><td>sort_type</td><td>varchar</td><td>楼层类型</td></tr>
 *   <tr><td>sort</td><td>{@link java.lang.Integer}</td><td>sort</td><td>int</td><td>排序号</td></tr>
 *   <tr><td>sortType</td><td>{@link java.lang.String}</td><td>sort_type</td><td>varchar</td><td>楼层号</td></tr>
 * </table>
 *
 */
public class ProductOrder implements Serializable {
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 8359687111777918021L;
	private java.lang.Integer id;
 	private java.lang.Integer productId;
 	private java.lang.String sortType;
 	private java.lang.Integer sort;
 		
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
     * 获取商品ID
     */
	public java.lang.Integer getProductId(){
		return this.productId;
	}
 		
	/**
     * 设置商品ID
     */
	public void setProductId(java.lang.Integer productId){
		this.productId = productId;
	}
 		
 		
	/**
     * 设置楼层说明
     */
	public void setSortType(java.lang.String sortType){
		this.sortType = sortType;
	}
 		
	/**
     * 获取排序
     */
	public java.lang.Integer getSort(){
		return this.sort;
	}
 		
	/**
     * 设置排序
     */
	public void setSort(java.lang.Integer sort){
		this.sort = sort;
	}
 		
	/**
     * 获取楼层类型
     */
	public java.lang.String getSortType(){
		return this.sortType;
	}
 }