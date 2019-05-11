package com.phkj.entity.repair;

import java.io.Serializable;

/**
 * 
 * @Filename: StAppletRepairMember.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class StAppletRepairMember implements Serializable {
 
    private static final long serialVersionUID = 273179654281875640L;
    private java.lang.Integer id;
 	private java.lang.Integer userId;
 	private java.lang.String userName;
 	private java.lang.Integer schedulingDay;
 	private java.lang.Integer createUserId;
 	private java.util.Date createTime;
 	private java.lang.Integer modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.Integer sts;
 	
 		
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
     * 获取userId
     */
	public java.lang.Integer getUserId(){
		return this.userId;
	}
 		
	/**
     * 设置userId
     */
	public void setUserId(java.lang.Integer userId){
		this.userId = userId;
	}
 		
	/**
     * 获取userName
     */
	public java.lang.String getUserName(){
		return this.userName;
	}
 		
	/**
     * 设置userName
     */
	public void setUserName(java.lang.String userName){
		this.userName = userName;
	}
 		
	/**
     * 获取schedulingDay
     */
	public java.lang.Integer getSchedulingDay(){
		return this.schedulingDay;
	}
 		
	/**
     * 设置schedulingDay
     */
	public void setSchedulingDay(java.lang.Integer schedulingDay){
		this.schedulingDay = schedulingDay;
	}
 		
	/**
     * 获取createUserId
     */
	public java.lang.Integer getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置createUserId
     */
	public void setCreateUserId(java.lang.Integer createUserId){
		this.createUserId = createUserId;
	}
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取modifyUserId
     */
	public java.lang.Integer getModifyUserId(){
		return this.modifyUserId;
	}
 		
	/**
     * 设置modifyUserId
     */
	public void setModifyUserId(java.lang.Integer modifyUserId){
		this.modifyUserId = modifyUserId;
	}
 		
	/**
     * 获取modifyTime
     */
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
 		
	/**
     * 设置modifyTime
     */
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}
 		
	/**
     * 获取sts
     */
	public java.lang.Integer getSts(){
		return this.sts;
	}
 		
	/**
     * 设置sts
     */
	public void setSts(java.lang.Integer sts){
		this.sts = sts;
	}
 }