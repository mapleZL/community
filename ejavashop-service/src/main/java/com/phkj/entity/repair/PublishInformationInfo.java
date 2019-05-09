package com.phkj.entity.repair;

import java.io.Serializable;
/**
 * 
 *
 */
public class PublishInformationInfo implements Serializable {
 
 	private Long id;
 	private String name;
 	private String userId;
 	private String userName;
 	private String examineId;
 	private java.util.Date examineTime;
 	private String repairId;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private String type;
 	private String title;
 	private String telPhone;
 	private java.util.Date startTime;
 	private java.util.Date endTime;
 	private Integer status;
 	private String detail;
 	private String img;
 	
 		
	/**
     * 获取id
     */
	public Long getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(Long id){
		this.id = id;
	}
 		
	/**
     * 获取名称
     */
	public String getName(){
		return this.name;
	}
 		
	/**
     * 设置名称
     */
	public void setName(String name){
		this.name = name;
	}
 		
	/**
     * 获取发布人id
     */
	public String getUserId(){
		return this.userId;
	}
 		
	/**
     * 设置发布人id
     */
	public void setUserId(String userId){
		this.userId = userId;
	}
 		
	/**
     * 获取发布人姓名
     */
	public String getUserName(){
		return this.userName;
	}
 		
	/**
     * 设置发布人姓名
     */
	public void setUserName(String userName){
		this.userName = userName;
	}
 		
	/**
     * 获取审核人
     */
	public String getExamineId(){
		return this.examineId;
	}
 		
	/**
     * 设置审核人
     */
	public void setExamineId(String examineId){
		this.examineId = examineId;
	}
 		
	/**
     * 获取审核时间
     */
	public java.util.Date getExamineTime(){
		return this.examineTime;
	}
 		
	/**
     * 设置审核时间
     */
	public void setExamineTime(java.util.Date examineTime){
		this.examineTime = examineTime;
	}
 		
	/**
     * 获取维修员id
     */
	public String getRepairId(){
		return this.repairId;
	}
 		
	/**
     * 设置维修员id
     */
	public void setRepairId(String repairId){
		this.repairId = repairId;
	}
 		
	/**
     * 获取发布时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置发布时间
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
     * 获取报修类型
     */
	public String getType(){
		return this.type;
	}
 		
	/**
     * 设置报修类型
     */
	public void setType(String type){
		this.type = type;
	}
 		
	/**
     * 获取title
     */
	public String getTitle(){
		return this.title;
	}
 		
	/**
     * 设置title
     */
	public void setTitle(String title){
		this.title = title;
	}
 		
	/**
     * 获取联系电话
     */
	public String getTelPhone(){
		return this.telPhone;
	}
 		
	/**
     * 设置联系电话
     */
	public void setTelPhone(String telPhone){
		this.telPhone = telPhone;
	}
 		
	/**
     * 获取预约开始时间
     */
	public java.util.Date getStartTime(){
		return this.startTime;
	}
 		
	/**
     * 设置预约开始时间
     */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
 		
	/**
     * 获取预约结束时间
     */
	public java.util.Date getEndTime(){
		return this.endTime;
	}
 		
	/**
     * 设置预约结束时间
     */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
 		
	/**
     * 获取状态，1待审核 2已审核 0审核不通过
     */
	public Integer getStatus(){
		return this.status;
	}
 		
	/**
     * 设置状态，1待审核 2已审核 0审核不通过
     */
	public void setStatus(Integer status){
		this.status = status;
	}
 		
	/**
     * 获取报修详情
     */
	public String getDetail(){
		return this.detail;
	}
 		
	/**
     * 设置报修详情
     */
	public void setDetail(String detail){
		this.detail = detail;
	}
 		
	/**
     * 获取图片
     */
	public String getImg(){
		return this.img;
	}
 		
	/**
     * 设置图片
     */
	public void setImg(String img){
		this.img = img;
	}
 }