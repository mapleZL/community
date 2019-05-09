package com.phkj.entity.member;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>member_car</strong>
 */
public class MemberCar implements Serializable {
 
 	private Integer id;
 	private Integer memberId;
 	private String vehicleType;
 	private String vehicleStructure;
 	private String vehicleNumber;
 	private java.util.Date createDate;
 	private java.util.Date examineDate;
 	private Integer examineUserId;
 	private Integer status;
 	private Integer deleted;
 	
 		
	/**
     * ��ȡid
     */
	public Integer getId(){
		return this.id;
	}
 		
	/**
     * ����id
     */
	public void setId(Integer id){
		this.id = id;
	}
 		
	/**
     * ��ȡ������id
     */
	public Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * ���ù�����id
     */
	public void setMemberId(Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * ��ȡ�����ͺ�
     */
	public String getVehicleType(){
		return this.vehicleType;
	}
 		
	/**
     * ���ó����ͺ�
     */
	public void setVehicleType(String vehicleType){
		this.vehicleType = vehicleType;
	}
 		
	/**
     * ��ȡ�������࣬eg�����ᳵ
     */
	public String getVehicleStructure(){
		return this.vehicleStructure;
	}
 		
	/**
     * ���ó������࣬eg�����ᳵ
     */
	public void setVehicleStructure(String vehicleStructure){
		this.vehicleStructure = vehicleStructure;
	}
 		
	/**
     * ��ȡ����
     */
	public String getVehicleNumber(){
		return this.vehicleNumber;
	}
 		
	/**
     * ���ó���
     */
	public void setVehicleNumber(String vehicleNumber){
		this.vehicleNumber = vehicleNumber;
	}
 		
	/**
     * ��ȡ��������
     */
	public java.util.Date getCreateDate(){
		return this.createDate;
	}
 		
	/**
     * ���ô�������
     */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
 		
	/**
     * ��ȡ�������
     */
	public java.util.Date getExamineDate(){
		return this.examineDate;
	}
 		
	/**
     * �����������
     */
	public void setExamineDate(java.util.Date examineDate){
		this.examineDate = examineDate;
	}
 		
	/**
     * ��ȡ�����
     */
	public Integer getExamineUserId(){
		return this.examineUserId;
	}
 		
	/**
     * ���������
     */
	public void setExamineUserId(Integer examineUserId){
		this.examineUserId = examineUserId;
	}
 		
	/**
     * ��ȡ1��ʾ����ˣ�2���ͨ����0��˲�ͨ��
     */
	public Integer getStatus(){
		return this.status;
	}
 		
	/**
     * ����1��ʾ����ˣ�2���ͨ����0��˲�ͨ��
     */
	public void setStatus(Integer status){
		this.status = status;
	}
 		
	/**
     * ��ȡ0��ʾ��ɾ����1��ʾδɾ��
     */
	public Integer getDeleted(){
		return this.deleted;
	}
 		
	/**
     * ����0��ʾ��ɾ����1��ʾδɾ��
     */
	public void setDeleted(Integer deleted){
		this.deleted = deleted;
	}
 }