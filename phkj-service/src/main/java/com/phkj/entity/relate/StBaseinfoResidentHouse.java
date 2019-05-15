package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 居民房屋关系表
 * <p>Table: <strong>st_baseinfo_resident_house</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>主键</td></tr>
 *   <tr><td>personStockId</td><td>{@link java.lang.Long}</td><td>person_stock_id</td><td>bigint</td><td>人员库id</td></tr>
 *   <tr><td>residentId</td><td>{@link java.lang.Long}</td><td>resident_id</td><td>bigint</td><td>居民id(街道使用加入的)</td></tr>
 *   <tr><td>houseId</td><td>{@link java.lang.Long}</td><td>house_id</td><td>bigint</td><td>房屋id</td></tr>
 *   <tr><td>residentiaId</td><td>{@link java.lang.Long}</td><td>residentia_id</td><td>bigint</td><td>小区id</td></tr>
 *   <tr><td>subareaId</td><td>{@link java.lang.Long}</td><td>subarea_id</td><td>bigint</td><td>分区id</td></tr>
 *   <tr><td>houseType</td><td>{@link java.lang.String}</td><td>house_type</td><td>char</td><td>房屋使用类型(1自住2出租)</td></tr>
 *   <tr><td>relationship</td><td>{@link java.lang.String}</td><td>relationship</td><td>char</td><td>与户主关系(1本人2家庭成员3亲戚朋友4雇佣5其他)</td></tr>
 *   <tr><td>peopleHouseSame</td><td>{@link java.lang.String}</td><td>people_house_same</td><td>char</td><td>人户一致（Y, N）</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 * </table>
 *
 */
public class StBaseinfoResidentHouse implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long personStockId;
 	private java.lang.Long residentId;
 	private java.lang.Long houseId;
 	private java.lang.Long residentiaId;
 	private java.lang.Long subareaId;
 	private java.lang.String houseType;
 	private java.lang.String relationship;
 	private java.lang.String peopleHouseSame;
 	private java.util.Date createTime;
 	private java.lang.Long createUserId;
 	private java.util.Date modifyTime;
 	private java.lang.Long modifyUserId;
 	private java.lang.String sts;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	
 		
	/**
     * 获取主键
     */
	public java.lang.Long getId(){
		return this.id;
	}
 		
	/**
     * 设置主键
     */
	public void setId(java.lang.Long id){
		this.id = id;
	}
 		
	/**
     * 获取人员库id
     */
	public java.lang.Long getPersonStockId(){
		return this.personStockId;
	}
 		
	/**
     * 设置人员库id
     */
	public void setPersonStockId(java.lang.Long personStockId){
		this.personStockId = personStockId;
	}
 		
	/**
     * 获取居民id(街道使用加入的)
     */
	public java.lang.Long getResidentId(){
		return this.residentId;
	}
 		
	/**
     * 设置居民id(街道使用加入的)
     */
	public void setResidentId(java.lang.Long residentId){
		this.residentId = residentId;
	}
 		
	/**
     * 获取房屋id
     */
	public java.lang.Long getHouseId(){
		return this.houseId;
	}
 		
	/**
     * 设置房屋id
     */
	public void setHouseId(java.lang.Long houseId){
		this.houseId = houseId;
	}
 		
	/**
     * 获取小区id
     */
	public java.lang.Long getResidentiaId(){
		return this.residentiaId;
	}
 		
	/**
     * 设置小区id
     */
	public void setResidentiaId(java.lang.Long residentiaId){
		this.residentiaId = residentiaId;
	}
 		
	/**
     * 获取分区id
     */
	public java.lang.Long getSubareaId(){
		return this.subareaId;
	}
 		
	/**
     * 设置分区id
     */
	public void setSubareaId(java.lang.Long subareaId){
		this.subareaId = subareaId;
	}
 		
	/**
     * 获取房屋使用类型(1自住2出租)
     */
	public java.lang.String getHouseType(){
		return this.houseType;
	}
 		
	/**
     * 设置房屋使用类型(1自住2出租)
     */
	public void setHouseType(java.lang.String houseType){
		this.houseType = houseType;
	}
 		
	/**
     * 获取与户主关系(1本人2家庭成员3亲戚朋友4雇佣5其他)
     */
	public java.lang.String getRelationship(){
		return this.relationship;
	}
 		
	/**
     * 设置与户主关系(1本人2家庭成员3亲戚朋友4雇佣5其他)
     */
	public void setRelationship(java.lang.String relationship){
		this.relationship = relationship;
	}
 		
	/**
     * 获取人户一致（Y, N）
     */
	public java.lang.String getPeopleHouseSame(){
		return this.peopleHouseSame;
	}
 		
	/**
     * 设置人户一致（Y, N）
     */
	public void setPeopleHouseSame(java.lang.String peopleHouseSame){
		this.peopleHouseSame = peopleHouseSame;
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
     * 获取创建人
     */
	public java.lang.Long getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置创建人
     */
	public void setCreateUserId(java.lang.Long createUserId){
		this.createUserId = createUserId;
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
     * 获取修改人
     */
	public java.lang.Long getModifyUserId(){
		return this.modifyUserId;
	}
 		
	/**
     * 设置修改人
     */
	public void setModifyUserId(java.lang.Long modifyUserId){
		this.modifyUserId = modifyUserId;
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