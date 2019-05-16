package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 居民车位表
 * <p>Table: <strong>st_baseinfo_resident_carport</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>序号</td></tr>
 *   <tr><td>residentinfoId</td><td>{@link java.lang.Long}</td><td>residentinfo_id</td><td>bigint</td><td>居民id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>车主</td></tr>
 *   <tr><td>parkinglotId</td><td>{@link java.lang.Long}</td><td>parkinglot_id</td><td>bigint</td><td>车位id</td></tr>
 *   <tr><td>carNo</td><td>{@link java.lang.String}</td><td>car_no</td><td>varchar</td><td>车牌</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>车位类型</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.String}</td><td>create_user_id</td><td>varchar</td><td>创建者</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.String}</td><td>modify_user_id</td><td>varchar</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 * </table>
 *
 */
public class StBaseinfoResidentCarport implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long residentinfoId;
 	private java.lang.String name;
 	private java.lang.Long parkinglotId;
 	private java.lang.String carNo;
 	private java.lang.String type;
 	private java.lang.String createUserId;
 	private java.util.Date createTime;
 	private java.lang.String modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.String sts;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	
 		
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
     * 获取居民id
     */
	public java.lang.Long getResidentinfoId(){
		return this.residentinfoId;
	}
 		
	/**
     * 设置居民id
     */
	public void setResidentinfoId(java.lang.Long residentinfoId){
		this.residentinfoId = residentinfoId;
	}
 		
	/**
     * 获取车主
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置车主
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取车位id
     */
	public java.lang.Long getParkinglotId(){
		return this.parkinglotId;
	}
 		
	/**
     * 设置车位id
     */
	public void setParkinglotId(java.lang.Long parkinglotId){
		this.parkinglotId = parkinglotId;
	}
 		
	/**
     * 获取车牌
     */
	public java.lang.String getCarNo(){
		return this.carNo;
	}
 		
	/**
     * 设置车牌
     */
	public void setCarNo(java.lang.String carNo){
		this.carNo = carNo;
	}
 		
	/**
     * 获取车位类型
     */
	public java.lang.String getType(){
		return this.type;
	}
 		
	/**
     * 设置车位类型
     */
	public void setType(java.lang.String type){
		this.type = type;
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
 }