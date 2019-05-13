package com.phkj.entity.member;

import java.io.Serializable;

public class MemberParkingLot implements Serializable {
 
    private static final long serialVersionUID = -8013285459324268237L;
    private java.lang.Integer id;
 	private java.lang.Integer memberId;
 	private java.lang.String position;
 	private java.lang.String lotType;
 	private java.lang.String lockType;
 	private java.util.Date validityDate;
 	private java.lang.String vehicleNumber;
 	private java.lang.String belonger;
 	private java.lang.Integer status;
 	private java.util.Date createDate;
 	private java.util.Date examineDate;
 	private java.lang.Integer examineUserId;
 	private java.lang.Integer deleted;
 	private java.lang.String phoneNum;
 	private String img;
 	
 		
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
     * 获取关联人id
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置关联人id
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取位置
     */
	public java.lang.String getPosition(){
		return this.position;
	}
 		
	/**
     * 设置位置
     */
	public void setPosition(java.lang.String position){
		this.position = position;
	}
 		
	/**
     * 获取车位类型：月租、自有
     */
	public java.lang.String getLotType(){
		return this.lotType;
	}
 		
	/**
     * 设置车位类型：月租、自有
     */
	public void setLotType(java.lang.String lotType){
		this.lotType = lotType;
	}
 		
	/**
     * 获取锁类型
     */
	public java.lang.String getLockType(){
		return this.lockType;
	}
 		
	/**
     * 设置锁类型
     */
	public void setLockType(java.lang.String lockType){
		this.lockType = lockType;
	}
 		
	/**
     * 获取有效期
     */
	public java.util.Date getValidityDate(){
		return this.validityDate;
	}
 		
	/**
     * 设置有效期
     */
	public void setValidityDate(java.util.Date validityDate){
		this.validityDate = validityDate;
	}
 		
	/**
     * 获取车牌号
     */
	public java.lang.String getVehicleNumber(){
		return this.vehicleNumber;
	}
 		
	/**
     * 设置车牌号
     */
	public void setVehicleNumber(java.lang.String vehicleNumber){
		this.vehicleNumber = vehicleNumber;
	}
 		
	/**
     * 获取所属人
     */
	public java.lang.String getBelonger(){
		return this.belonger;
	}
 		
	/**
     * 设置所属人
     */
	public void setBelonger(java.lang.String belonger){
		this.belonger = belonger;
	}
 		
	/**
     * 获取status
     */
	public java.lang.Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置status
     */
	public void setStatus(java.lang.Integer status){
		this.status = status;
	}
 		
	/**
     * 获取createDate
     */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
 		
	/**
     * 设置createDate
     */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
 		
	/**
     * 获取审核日期
     */
	public java.util.Date getExamineDate(){
		return this.examineDate;
	}
 		
	/**
     * 设置审核日期
     */
	public void setExamineDate(java.util.Date examineDate){
		this.examineDate = examineDate;
	}
 		
	/**
     * 获取审核人
     */
	public java.lang.Integer getExamineUserId(){
		return this.examineUserId;
	}
 		
	/**
     * 设置审核人
     */
	public void setExamineUserId(java.lang.Integer examineUserId){
		this.examineUserId = examineUserId;
	}
 		
	/**
     * 获取0表示删除，1表示未删除
     */
	public java.lang.Integer getDeleted(){
		return this.deleted;
	}
 		
	/**
     * 设置0表示删除，1表示未删除
     */
	public void setDeleted(java.lang.Integer deleted){
		this.deleted = deleted;
	}
 		
	/**
     * 获取手机号码
     */
	public java.lang.String getPhoneNum(){
		return this.phoneNum;
	}
 		
	/**
     * 设置手机号码
     */
	public void setPhoneNum(java.lang.String phoneNum){
		this.phoneNum = phoneNum;
	}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
 }