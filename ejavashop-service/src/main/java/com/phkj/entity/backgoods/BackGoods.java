package com.phkj.entity.backgoods;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>back_goods</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>主键</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>订单id</td></tr>
 *   <tr><td>ordersSn</td><td>{@link java.lang.String}</td><td>orders_sn</td><td>varchar</td><td>订单号</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 *   <tr><td>backNum</td><td>{@link java.lang.Integer}</td><td>back_num</td><td>int</td><td>退货总数量</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>int</td><td>退货状态 1：审核中2：审核通过3：审核不通过4：退货中5：核实中6：已完成</td></tr>
 *   <tr><td>backMan</td><td>{@link java.lang.String}</td><td>back_man</td><td>varchar</td><td>退货人</td></tr>
 *   <tr><td>backMemberId</td><td>{@link java.lang.Integer}</td><td>back_member_id</td><td>int</td><td>退货人id</td></tr>
 *   <tr><td>backReason</td><td>{@link java.lang.String}</td><td>back_reason</td><td>varchar</td><td>退货原因</td></tr>
 *   <tr><td>checkMan</td><td>{@link java.lang.String}</td><td>check_man</td><td>varchar</td><td>审核人</td></tr>
 *   <tr><td>checkReason</td><td>{@link java.lang.String}</td><td>check_reason</td><td>varchar</td><td>审核原因</td></tr>
 *   <tr><td>checkTime</td><td>{@link java.util.Date}</td><td>check_time</td><td>datetime</td><td>审核时间</td></tr>
 *   <tr><td>wellNum</td><td>{@link java.lang.Integer}</td><td>well_num</td><td>int</td><td>正品数量</td></tr>
 *   <tr><td>badNum</td><td>{@link java.lang.Integer}</td><td>bad_num</td><td>int</td><td>次品数量</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>电话</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>手机</td></tr>
 *   <tr><td>email</td><td>{@link java.lang.String}</td><td>email</td><td>varchar</td><td>邮箱</td></tr>
 * </table>
 *
 */
public class BackGoods implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer ordersId;
 	private java.lang.String ordersSn;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.Integer backNum;
 	private java.lang.Integer status;
 	private java.lang.String backMan;
 	private java.lang.Integer backMemberId;
 	private java.lang.String backReason;
 	private java.lang.String checkMan;
 	private java.lang.String checkReason;
 	private java.util.Date checkTime;
 	private java.lang.Integer wellNum;
 	private java.lang.Integer badNum;
 	private java.lang.String mobile;
 	private java.lang.String phone;
 	private java.lang.String email;
 	
 		
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
 		
	/**
     * 获取退货状态 1：审核中2：审核通过3：审核不通过4：退货中5：核实中6：已完成
     */
	public java.lang.Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置退货状态 1：审核中2：审核通过3：审核不通过4：退货中5：核实中6：已完成
     */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
 		
	/**
     * 获取退货人
     */
	public java.lang.String getBackMan(){
		return this.backMan;
	}
 		
	/**
     * 设置退货人
     */
	public void setBackMan(java.lang.String backMan){
		this.backMan = backMan;
	}
 		
	/**
     * 获取退货人id
     */
	public java.lang.Integer getBackMemberId(){
		return this.backMemberId;
	}
 		
	/**
     * 设置退货人id
     */
	public void setBackMemberId(java.lang.Integer backMemberId){
		this.backMemberId = backMemberId;
	}
 		
	/**
     * 获取退货原因
     */
	public java.lang.String getBackReason(){
		return this.backReason;
	}
 		
	/**
     * 设置退货原因
     */
	public void setBackReason(java.lang.String backReason){
		this.backReason = backReason;
	}
 		
	/**
     * 获取审核人
     */
	public java.lang.String getCheckMan(){
		return this.checkMan;
	}
 		
	/**
     * 设置审核人
     */
	public void setCheckMan(java.lang.String checkMan){
		this.checkMan = checkMan;
	}
 		
	/**
     * 获取审核原因
     */
	public java.lang.String getCheckReason(){
		return this.checkReason;
	}
 		
	/**
     * 设置审核原因
     */
	public void setCheckReason(java.lang.String checkReason){
		this.checkReason = checkReason;
	}
 		
	/**
     * 获取审核时间
     */
	public java.util.Date getCheckTime(){
		return this.checkTime;
	}
 		
	/**
     * 设置审核时间
     */
	public void setCheckTime(java.util.Date checkTime){
		this.checkTime = checkTime;
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
     * 获取电话
     */
	public java.lang.String getMobile(){
		return this.mobile;
	}
 		
	/**
     * 设置电话
     */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
 		
	/**
     * 获取手机
     */
	public java.lang.String getPhone(){
		return this.phone;
	}
 		
	/**
     * 设置手机
     */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
 		
	/**
     * 获取邮箱
     */
	public java.lang.String getEmail(){
		return this.email;
	}
 		
	/**
     * 设置邮箱
     */
	public void setEmail(java.lang.String email){
		this.email = email;
	}
 }