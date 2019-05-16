package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 车位信息
 * <p>Table: <strong>st_baseinfo_parking_lot</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>序号</td></tr>
 *   <tr><td>subareaId</td><td>{@link java.lang.Long}</td><td>subarea_id</td><td>bigint</td><td>分区ID</td></tr>
 *   <tr><td>residentiaId</td><td>{@link java.lang.Long}</td><td>residentia_id</td><td>bigint</td><td>小区ID</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>编号</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>类型</td></tr>
 *   <tr><td>longitude</td><td>{@link java.math.BigDecimal}</td><td>longitude</td><td>decimal</td><td>经度</td></tr>
 *   <tr><td>latitude</td><td>{@link java.math.BigDecimal}</td><td>latitude</td><td>decimal</td><td>纬度</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>shiftCarId</td><td>{@link java.lang.Long}</td><td>shift_car_id</td><td>bigint</td><td>一键移车id</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>innnerType</td><td>{@link java.lang.String}</td><td>innner_type</td><td>varchar</td><td>innnerType</td></tr>
 *   <tr><td>parkingStatus</td><td>{@link java.lang.String}</td><td>parking_status</td><td>varchar</td><td>parkingStatus</td></tr>
 * </table>
 *
 */
public class StBaseinfoParkingLot implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long subareaId;
 	private java.lang.Long residentiaId;
 	private java.lang.String code;
 	private java.lang.String type;
 	private java.math.BigDecimal longitude;
 	private java.math.BigDecimal latitude;
 	private java.lang.Long createUserId;
 	private java.util.Date createTime;
 	private java.lang.Long modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.String sts;
 	private java.lang.Long shiftCarId;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.String innnerType;
 	private java.lang.String parkingStatus;
 	
 		
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
     * 获取分区ID
     */
	public java.lang.Long getSubareaId(){
		return this.subareaId;
	}
 		
	/**
     * 设置分区ID
     */
	public void setSubareaId(java.lang.Long subareaId){
		this.subareaId = subareaId;
	}
 		
	/**
     * 获取小区ID
     */
	public java.lang.Long getResidentiaId(){
		return this.residentiaId;
	}
 		
	/**
     * 设置小区ID
     */
	public void setResidentiaId(java.lang.Long residentiaId){
		this.residentiaId = residentiaId;
	}
 		
	/**
     * 获取编号
     */
	public java.lang.String getCode(){
		return this.code;
	}
 		
	/**
     * 设置编号
     */
	public void setCode(java.lang.String code){
		this.code = code;
	}
 		
	/**
     * 获取类型
     */
	public java.lang.String getType(){
		return this.type;
	}
 		
	/**
     * 设置类型
     */
	public void setType(java.lang.String type){
		this.type = type;
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
     * 获取一键移车id
     */
	public java.lang.Long getShiftCarId(){
		return this.shiftCarId;
	}
 		
	/**
     * 设置一键移车id
     */
	public void setShiftCarId(java.lang.Long shiftCarId){
		this.shiftCarId = shiftCarId;
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
     * 获取innnerType
     */
	public java.lang.String getInnnerType(){
		return this.innnerType;
	}
 		
	/**
     * 设置innnerType
     */
	public void setInnnerType(java.lang.String innnerType){
		this.innnerType = innnerType;
	}
 		
	/**
     * 获取parkingStatus
     */
	public java.lang.String getParkingStatus(){
		return this.parkingStatus;
	}
 		
	/**
     * 设置parkingStatus
     */
	public void setParkingStatus(java.lang.String parkingStatus){
		this.parkingStatus = parkingStatus;
	}
 }