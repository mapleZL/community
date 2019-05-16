package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 人员车辆登记表
 * <p>Table: <strong>st_baseinfo_resident_car</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>序号</td></tr>
 *   <tr><td>personStockId</td><td>{@link java.lang.Long}</td><td>person_stock_id</td><td>bigint</td><td>人员id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>carNo</td><td>{@link java.lang.String}</td><td>car_no</td><td>varchar</td><td>车牌号</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>人员类型</td></tr>
 *   <tr><td>time</td><td>{@link java.util.Date}</td><td>time</td><td>datetime</td><td>登记时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.String}</td><td>create_user_id</td><td>varchar</td><td>创建者</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.String}</td><td>modify_user_id</td><td>varchar</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>carType</td><td>{@link java.lang.String}</td><td>car_type</td><td>varchar</td><td>carType</td></tr>
 *   <tr><td>isOtherCity</td><td>{@link java.lang.String}</td><td>is_other_city</td><td>varchar</td><td>isOtherCity</td></tr>
 * </table>
 *
 */
public class StBaseinfoResidentCar implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long personStockId;
 	private java.lang.String name;
 	private java.lang.String mobile;
 	private java.lang.String carNo;
 	private java.lang.String type;
 	private java.util.Date time;
 	private java.lang.String createUserId;
 	private java.util.Date createTime;
 	private java.lang.String modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.String sts;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.String carType;
 	private java.lang.String isOtherCity;
 	
 		
	/**
     * 获取序号
     */
	public java.lang.Long getId(){
		return this.id;
	}
 		
	/**
     * 设置序号
     */
	public void setId(java.lang.Long id){
		this.id = id;
	}
 		
	/**
     * 获取人员id
     */
	public java.lang.Long getPersonStockId(){
		return this.personStockId;
	}
 		
	/**
     * 设置人员id
     */
	public void setPersonStockId(java.lang.Long personStockId){
		this.personStockId = personStockId;
	}
 		
	/**
     * 获取姓名
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置姓名
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取手机号
     */
	public java.lang.String getMobile(){
		return this.mobile;
	}
 		
	/**
     * 设置手机号
     */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
 		
	/**
     * 获取车牌号
     */
	public java.lang.String getCarNo(){
		return this.carNo;
	}
 		
	/**
     * 设置车牌号
     */
	public void setCarNo(java.lang.String carNo){
		this.carNo = carNo;
	}
 		
	/**
     * 获取人员类型
     */
	public java.lang.String getType(){
		return this.type;
	}
 		
	/**
     * 设置人员类型
     */
	public void setType(java.lang.String type){
		this.type = type;
	}
 		
	/**
     * 获取登记时间
     */
	public java.util.Date getTime(){
		return this.time;
	}
 		
	/**
     * 设置登记时间
     */
	public void setTime(java.util.Date time){
		this.time = time;
	}
 		
	/**
     * 获取创建者
     */
	public java.lang.String getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置创建者
     */
	public void setCreateUserId(java.lang.String createUserId){
		this.createUserId = createUserId;
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
     * 获取修改人
     */
	public java.lang.String getModifyUserId(){
		return this.modifyUserId;
	}
 		
	/**
     * 设置修改人
     */
	public void setModifyUserId(java.lang.String modifyUserId){
		this.modifyUserId = modifyUserId;
	}
 		
	/**
     * 获取修改时间
     */
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
 		
	/**
     * 设置修改时间
     */
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}
 		
	/**
     * 获取状态
     */
	public java.lang.String getSts(){
		return this.sts;
	}
 		
	/**
     * 设置状态
     */
	public void setSts(java.lang.String sts){
		this.sts = sts;
	}
 		
	/**
     * 获取orgCode
     */
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}
 		
	/**
     * 设置orgCode
     */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
 		
	/**
     * 获取topOrgCode
     */
	public java.lang.String getTopOrgCode(){
		return this.topOrgCode;
	}
 		
	/**
     * 设置topOrgCode
     */
	public void setTopOrgCode(java.lang.String topOrgCode){
		this.topOrgCode = topOrgCode;
	}
 		
	/**
     * 获取carType
     */
	public java.lang.String getCarType(){
		return this.carType;
	}
 		
	/**
     * 设置carType
     */
	public void setCarType(java.lang.String carType){
		this.carType = carType;
	}
 		
	/**
     * 获取isOtherCity
     */
	public java.lang.String getIsOtherCity(){
		return this.isOtherCity;
	}
 		
	/**
     * 设置isOtherCity
     */
	public void setIsOtherCity(java.lang.String isOtherCity){
		this.isOtherCity = isOtherCity;
	}
 }