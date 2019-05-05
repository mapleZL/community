package com.ejavashop.entity.member;

import java.io.Serializable;

public class MemberHouse implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.Integer memberId;
 	private java.lang.String memberName;
 	private java.lang.String villageName;
 	private java.lang.String houseBlock;
 	private java.lang.String houseUnit;
 	private java.lang.String houseNumber;
 	private java.util.Date createDate;
 	private java.util.Date examineDate;
 	private java.lang.Integer examineUserId;
 	private java.lang.Integer status;
 	private java.lang.String deleted;
 	
 		
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
	
	public java.lang.String getMemberName() {
        return memberName;
    }

    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    /**
     * 获取memberId
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置memberId
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取villageName
     */
	public java.lang.String getVillageName(){
		return this.villageName;
	}
 		
	/**
     * 设置villageName
     */
	public void setVillageName(java.lang.String villageName){
		this.villageName = villageName;
	}
 		
	/**
     * 获取houseBlock
     */
	public java.lang.String getHouseBlock(){
		return this.houseBlock;
	}
 		
	/**
     * 设置houseBlock
     */
	public void setHouseBlock(java.lang.String houseBlock){
		this.houseBlock = houseBlock;
	}
 		
	/**
     * 获取houseUnit
     */
	public java.lang.String getHouseUnit(){
		return this.houseUnit;
	}
 		
	/**
     * 设置houseUnit
     */
	public void setHouseUnit(java.lang.String houseUnit){
		this.houseUnit = houseUnit;
	}
 		
	/**
     * 获取houseNumber
     */
	public java.lang.String getHouseNumber(){
		return this.houseNumber;
	}
 		
	/**
     * 设置houseNumber
     */
	public void setHouseNumber(java.lang.String houseNumber){
		this.houseNumber = houseNumber;
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
     * 获取examineDate
     */
	public java.util.Date getExamineDate(){
		return this.examineDate;
	}
 		
	/**
     * 设置examineDate
     */
	public void setExamineDate(java.util.Date examineDate){
		this.examineDate = examineDate;
	}
 		
	/**
     * 获取examineUserId
     */
	public java.lang.Integer getExamineUserId(){
		return this.examineUserId;
	}
 		
	/**
     * 设置examineUserId
     */
	public void setExamineUserId(java.lang.Integer examineUserId){
		this.examineUserId = examineUserId;
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
     * 获取deleted
     */
	public java.lang.String getDeleted(){
		return this.deleted;
	}
 		
	/**
     * 设置deleted
     */
	public void setDeleted(java.lang.String deleted){
		this.deleted = deleted;
	}

    @Override
    public String toString() {
        return "MemberHouse [id=" + id + ", memberId=" + memberId + ", villageName=" + villageName
               + ", houseBlock=" + houseBlock + ", houseUnit=" + houseUnit + ", houseNumber="
               + houseNumber + ", createDate=" + createDate + ", examineDate=" + examineDate
               + ", examineUserId=" + examineUserId + ", status=" + status + ", deleted=" + deleted
               + "]";
    }
	
 }