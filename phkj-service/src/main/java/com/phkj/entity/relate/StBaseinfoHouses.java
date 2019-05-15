package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 
 *                       
 * @Filename: StBaseinfoHouses.java
 * @Version: 1.0
 * @date: 2019年5月14日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class StBaseinfoHouses implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long personStockId;
 	private java.lang.Long buildingId;
 	private java.lang.Long unitId;
 	private java.lang.String code;
 	private java.lang.String name;
 	private java.math.BigDecimal proportion;
 	private java.lang.Integer floorNum;
 	private java.lang.String orientation;
 	private java.lang.String useState;
 	private java.lang.String businessFlag;
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
 	private java.lang.Long label;
 	private java.lang.String housesType;
 	
 		
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
     * 获取户主人员库ID
     */
	public java.lang.Long getPersonStockId(){
		return this.personStockId;
	}
 		
	/**
     * 设置户主人员库ID
     */
	public void setPersonStockId(java.lang.Long personStockId){
		this.personStockId = personStockId;
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
     * 获取单元ID
     */
	public java.lang.Long getUnitId(){
		return this.unitId;
	}
 		
	/**
     * 设置单元ID
     */
	public void setUnitId(java.lang.Long unitId){
		this.unitId = unitId;
	}
 		
	/**
     * 获取房屋编号
     */
	public java.lang.String getCode(){
		return this.code;
	}
 		
	/**
     * 设置房屋编号
     */
	public void setCode(java.lang.String code){
		this.code = code;
	}
 		
	/**
     * 获取名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取面积
     */
	public java.math.BigDecimal getProportion(){
		return this.proportion;
	}
 		
	/**
     * 设置面积
     */
	public void setProportion(java.math.BigDecimal proportion){
		this.proportion = proportion;
	}
 		
	/**
     * 获取所在楼层
     */
	public java.lang.Integer getFloorNum(){
		return this.floorNum;
	}
 		
	/**
     * 设置所在楼层
     */
	public void setFloorNum(java.lang.Integer floorNum){
		this.floorNum = floorNum;
	}
 		
	/**
     * 获取朝向
     */
	public java.lang.String getOrientation(){
		return this.orientation;
	}
 		
	/**
     * 设置朝向
     */
	public void setOrientation(java.lang.String orientation){
		this.orientation = orientation;
	}
 		
	/**
     * 获取使用情况
     */
	public java.lang.String getUseState(){
		return this.useState;
	}
 		
	/**
     * 设置使用情况
     */
	public void setUseState(java.lang.String useState){
		this.useState = useState;
	}
 		
	/**
     * 获取是否商用
     */
	public java.lang.String getBusinessFlag(){
		return this.businessFlag;
	}
 		
	/**
     * 设置是否商用
     */
	public void setBusinessFlag(java.lang.String businessFlag){
		this.businessFlag = businessFlag;
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
 		
	/**
     * 获取标签的id
     */
	public java.lang.Long getLabel(){
		return this.label;
	}
 		
	/**
     * 设置标签的id
     */
	public void setLabel(java.lang.Long label){
		this.label = label;
	}
 		
	/**
     * 获取housesType
     */
	public java.lang.String getHousesType(){
		return this.housesType;
	}
 		
	/**
     * 设置housesType
     */
	public void setHousesType(java.lang.String housesType){
		this.housesType = housesType;
	}
 }