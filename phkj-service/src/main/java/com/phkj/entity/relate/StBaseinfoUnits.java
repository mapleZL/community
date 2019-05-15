package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 楼幢单元信息
 * <p>Table: <strong>st_baseinfo_units</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>序号</td></tr>
 *   <tr><td>buildingId</td><td>{@link java.lang.Long}</td><td>building_id</td><td>bigint</td><td>楼幢ID</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>单元名称</td></tr>
 *   <tr><td>numberOfFloor</td><td>{@link java.lang.Integer}</td><td>number_of_floor</td><td>int</td><td>楼层数</td></tr>
 *   <tr><td>elevatorNumber</td><td>{@link java.lang.Integer}</td><td>elevator_number</td><td>int</td><td>电梯数量</td></tr>
 *   <tr><td>longitude</td><td>{@link java.math.BigDecimal}</td><td>longitude</td><td>decimal</td><td>经度</td></tr>
 *   <tr><td>latitude</td><td>{@link java.math.BigDecimal}</td><td>latitude</td><td>decimal</td><td>纬度</td></tr>
 *   <tr><td>quipmentDescription</td><td>{@link java.lang.String}</td><td>quipment_description</td><td>varchar</td><td>公共设备描述</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>serialNumber</td><td>{@link java.lang.String}</td><td>serial_number</td><td>varchar</td><td>序号</td></tr>
 *   <tr><td>residentiaId</td><td>{@link java.lang.Long}</td><td>residentia_id</td><td>bigint</td><td>residentiaId</td></tr>
 *   <tr><td>subareaId</td><td>{@link java.lang.Long}</td><td>subarea_id</td><td>bigint</td><td>subareaId</td></tr>
 * </table>
 *
 */
public class StBaseinfoUnits implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long buildingId;
 	private java.lang.String name;
 	private java.lang.Integer numberOfFloor;
 	private java.lang.Integer elevatorNumber;
 	private java.math.BigDecimal longitude;
 	private java.math.BigDecimal latitude;
 	private java.lang.String quipmentDescription;
 	private java.lang.Long createUserId;
 	private java.util.Date createTime;
 	private java.lang.Long modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.String sts;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.String serialNumber;
 	private java.lang.Long residentiaId;
 	private java.lang.Long subareaId;
 	
 		
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
     * 获取楼幢ID
     */
	public java.lang.Long getBuildingId(){
		return this.buildingId;
	}
 		
	/**
     * 设置楼幢ID
     */
	public void setBuildingId(java.lang.Long buildingId){
		this.buildingId = buildingId;
	}
 		
	/**
     * 获取单元名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置单元名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取楼层数
     */
	public java.lang.Integer getNumberOfFloor(){
		return this.numberOfFloor;
	}
 		
	/**
     * 设置楼层数
     */
	public void setNumberOfFloor(java.lang.Integer numberOfFloor){
		this.numberOfFloor = numberOfFloor;
	}
 		
	/**
     * 获取电梯数量
     */
	public java.lang.Integer getElevatorNumber(){
		return this.elevatorNumber;
	}
 		
	/**
     * 设置电梯数量
     */
	public void setElevatorNumber(java.lang.Integer elevatorNumber){
		this.elevatorNumber = elevatorNumber;
	}
 		
	/**
     * 获取经度
     */
	public java.math.BigDecimal getLongitude(){
		return this.longitude;
	}
 		
	/**
     * 设置经度
     */
	public void setLongitude(java.math.BigDecimal longitude){
		this.longitude = longitude;
	}
 		
	/**
     * 获取纬度
     */
	public java.math.BigDecimal getLatitude(){
		return this.latitude;
	}
 		
	/**
     * 设置纬度
     */
	public void setLatitude(java.math.BigDecimal latitude){
		this.latitude = latitude;
	}
 		
	/**
     * 获取公共设备描述
     */
	public java.lang.String getQuipmentDescription(){
		return this.quipmentDescription;
	}
 		
	/**
     * 设置公共设备描述
     */
	public void setQuipmentDescription(java.lang.String quipmentDescription){
		this.quipmentDescription = quipmentDescription;
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
     * 获取序号
     */
	public java.lang.String getSerialNumber(){
		return this.serialNumber;
	}
 		
	/**
     * 设置序号
     */
	public void setSerialNumber(java.lang.String serialNumber){
		this.serialNumber = serialNumber;
	}
 		
	/**
     * 获取residentiaId
     */
	public java.lang.Long getResidentiaId(){
		return this.residentiaId;
	}
 		
	/**
     * 设置residentiaId
     */
	public void setResidentiaId(java.lang.Long residentiaId){
		this.residentiaId = residentiaId;
	}
 		
	/**
     * 获取subareaId
     */
	public java.lang.Long getSubareaId(){
		return this.subareaId;
	}
 		
	/**
     * 设置subareaId
     */
	public void setSubareaId(java.lang.Long subareaId){
		this.subareaId = subareaId;
	}
 }