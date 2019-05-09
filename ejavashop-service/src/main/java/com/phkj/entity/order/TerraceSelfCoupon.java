package com.phkj.entity.order;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>terrace_self_coupon</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>tradeNo</td><td>{@link java.lang.String}</td><td>trade_no</td><td>varchar</td><td>订单支付码</td></tr>
 *   <tr><td>couponSn</td><td>{@link java.lang.String}</td><td>coupon_sn</td><td>varchar</td><td>红包码</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>int</td><td>订单状态</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>couponId</td><td>{@link java.lang.Integer}</td><td>coupon_id</td><td>int</td><td>红包码ID</td></tr>
 *   <tr><td>couponValue</td><td>{@link java.math.BigDecimal}</td><td>coupon_value</td><td>decimal</td><td>红包使用金额</td></tr>
 * </table>
 *
 */
public class TerraceSelfCoupon implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -4925011500322494012L;
    private java.lang.Integer id;
 	private java.lang.String tradeNo;
 	private java.lang.String couponSn;
 	private java.lang.Integer memberId;
 	private java.lang.Integer status;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.Integer couponId;
 	private java.math.BigDecimal couponValue;
 	
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return  this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取订单支付码
     */
	public java.lang.String getTradeNo(){
		return this.tradeNo;
	}
 		
	/**
     * 设置订单支付码
     */
	public void setTradeNo(java.lang.String tradeNo){
		this.tradeNo = tradeNo;
	}
 		
	/**
     * 获取红包码
     */
	public java.lang.String getCouponSn(){
		return this.couponSn;
	}
 		
	/**
     * 设置红包码
     */
	public void setCouponSn(java.lang.String couponSn){
		this.couponSn = couponSn;
	}
 		
	/**
     * 获取会员ID
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置会员ID
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取订单状态
     */
	public java.lang.Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置订单状态
     */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
 		
	/**
     * 获取创建时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置创建时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取更新时间
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置更新时间
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 		
	/**
     * 获取红包码ID
     */
	public java.lang.Integer getCouponId(){
		return this.couponId;
	}
 		
	/**
     * 设置红包码ID
     */
	public void setCouponId(java.lang.Integer couponId){
		this.couponId = couponId;
	}
 		
	/**
     * 获取红包使用金额
     */
	public java.math.BigDecimal getCouponValue(){
		return this.couponValue;
	}
 		
	/**
     * 设置红包使用金额
     */
	public void setCouponValue(java.math.BigDecimal couponValue){
		this.couponValue = couponValue;
	}
 }