package com.phkj.entity.property;

import java.io.Serializable;
/**
 * 工作人员信息管理表
 *
 */
public class StBaseinfoStaffs implements Serializable {
 
 	private Long id;
 	private Long residentiaId;
 	private Long subareaId;
 	private Long loginId;
 	private String name;
 	private String sex;
 	private String idNumber;
 	private String idAddress;
 	private java.util.Date birthDate;
 	private Long capturePicId;
 	private String nation;
 	private Long age;
 	private String ageSegment;
 	private java.util.Date validBegin;
 	private java.util.Date validEnd;
 	private String ever;
 	private String politicalStatus;
 	private String degree;
 	private String maritalStatus;
 	private String carNo;
 	private String mobile;
 	private String contactor;
 	private String contactorMobile;
 	private String residence;
 	private String remark;
 	private String staffType;
 	private java.util.Date startWorkTime;
 	private java.util.Date endContractTime;
 	private Long createUserId;
 	private java.util.Date createTime;
 	private Long modifyUserId;
 	private java.util.Date modifyTime;
 	private String sts;
 	private Long jobsId;
 	private String encryptionIdNumber;
 	private String orgCode;
 	private String topOrgCode;
 	
 		
	/**
     * 获取工作人员ID
     */
	public Long getId(){
		return this.id;
	}
 		
	/**
     * 设置工作人员ID
     */
	public void setId(Long id){
		this.id = id;
	}
 		
	/**
     * 获取社区id
     */
	public Long getResidentiaId(){
		return this.residentiaId;
	}
 		
	/**
     * 设置社区id
     */
	public void setResidentiaId(Long residentiaId){
		this.residentiaId = residentiaId;
	}
 		
	/**
     * 获取小区id
     */
	public Long getSubareaId(){
		return this.subareaId;
	}
 		
	/**
     * 设置小区id
     */
	public void setSubareaId(Long subareaId){
		this.subareaId = subareaId;
	}
 		
	/**
     * 获取loginId
     */
	public Long getLoginId(){
		return this.loginId;
	}
 		
	/**
     * 设置loginId
     */
	public void setLoginId(Long loginId){
		this.loginId = loginId;
	}
 		
	/**
     * 获取姓名
     */
	public String getName(){
		return this.name;
	}
 		
	/**
     * 设置姓名
     */
	public void setName(String name){
		this.name = name;
	}
 		
	/**
     * 获取性别
     */
	public String getSex(){
		return this.sex;
	}
 		
	/**
     * 设置性别
     */
	public void setSex(String sex){
		this.sex = sex;
	}
 		
	/**
     * 获取身份证号
     */
	public String getIdNumber(){
		return this.idNumber;
	}
 		
	/**
     * 设置身份证号
     */
	public void setIdNumber(String idNumber){
		this.idNumber = idNumber;
	}
 		
	/**
     * 获取身份证地址
     */
	public String getIdAddress(){
		return this.idAddress;
	}
 		
	/**
     * 设置身份证地址
     */
	public void setIdAddress(String idAddress){
		this.idAddress = idAddress;
	}
 		
	/**
     * 获取出生日期
     */
	public java.util.Date getBirthDate(){
		return this.birthDate;
	}
 		
	/**
     * 设置出生日期
     */
	public void setBirthDate(java.util.Date birthDate){
		this.birthDate = birthDate;
	}
 		
	/**
     * 获取人脸抓拍头像id
     */
	public Long getCapturePicId(){
		return this.capturePicId;
	}
 		
	/**
     * 设置人脸抓拍头像id
     */
	public void setCapturePicId(Long capturePicId){
		this.capturePicId = capturePicId;
	}
 		
	/**
     * 获取民族
     */
	public String getNation(){
		return this.nation;
	}
 		
	/**
     * 设置民族
     */
	public void setNation(String nation){
		this.nation = nation;
	}
 		
	/**
     * 获取年龄
     */
	public Long getAge(){
		return this.age;
	}
 		
	/**
     * 设置年龄
     */
	public void setAge(Long age){
		this.age = age;
	}
 		
	/**
     * 获取年龄分段
     */
	public String getAgeSegment(){
		return this.ageSegment;
	}
 		
	/**
     * 设置年龄分段
     */
	public void setAgeSegment(String ageSegment){
		this.ageSegment = ageSegment;
	}
 		
	/**
     * 获取有效期开始
     */
	public java.util.Date getValidBegin(){
		return this.validBegin;
	}
 		
	/**
     * 设置有效期开始
     */
	public void setValidBegin(java.util.Date validBegin){
		this.validBegin = validBegin;
	}
 		
	/**
     * 获取有效期至
     */
	public java.util.Date getValidEnd(){
		return this.validEnd;
	}
 		
	/**
     * 设置有效期至
     */
	public void setValidEnd(java.util.Date validEnd){
		this.validEnd = validEnd;
	}
 		
	/**
     * 获取永久有效
     */
	public String getEver(){
		return this.ever;
	}
 		
	/**
     * 设置永久有效
     */
	public void setEver(String ever){
		this.ever = ever;
	}
 		
	/**
     * 获取政治面貌(1群众、2少先队员、3共青团员、4预备党员、5共产党员、6民主党派)
     */
	public String getPoliticalStatus(){
		return this.politicalStatus;
	}
 		
	/**
     * 设置政治面貌(1群众、2少先队员、3共青团员、4预备党员、5共产党员、6民主党派)
     */
	public void setPoliticalStatus(String politicalStatus){
		this.politicalStatus = politicalStatus;
	}
 		
	/**
     * 获取文化程度/学历(包括1博士、2硕士、3本科、4大专、5中专/中技、6技工学校、7高中、8初中、9小学、10文盲/半文盲)
     */
	public String getDegree(){
		return this.degree;
	}
 		
	/**
     * 设置文化程度/学历(包括1博士、2硕士、3本科、4大专、5中专/中技、6技工学校、7高中、8初中、9小学、10文盲/半文盲)
     */
	public void setDegree(String degree){
		this.degree = degree;
	}
 		
	/**
     * 获取婚姻状态(0=未知 1=已婚 2=未婚 3=离异)
     */
	public String getMaritalStatus(){
		return this.maritalStatus;
	}
 		
	/**
     * 设置婚姻状态(0=未知 1=已婚 2=未婚 3=离异)
     */
	public void setMaritalStatus(String maritalStatus){
		this.maritalStatus = maritalStatus;
	}
 		
	/**
     * 获取车牌
     */
	public String getCarNo(){
		return this.carNo;
	}
 		
	/**
     * 设置车牌
     */
	public void setCarNo(String carNo){
		this.carNo = carNo;
	}
 		
	/**
     * 获取手机号
     */
	public String getMobile(){
		return this.mobile;
	}
 		
	/**
     * 设置手机号
     */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}
 		
	/**
     * 获取紧急联系人
     */
	public String getContactor(){
		return this.contactor;
	}
 		
	/**
     * 设置紧急联系人
     */
	public void setContactor(String contactor){
		this.contactor = contactor;
	}
 		
	/**
     * 获取紧急联系人电话
     */
	public String getContactorMobile(){
		return this.contactorMobile;
	}
 		
	/**
     * 设置紧急联系人电话
     */
	public void setContactorMobile(String contactorMobile){
		this.contactorMobile = contactorMobile;
	}
 		
	/**
     * 获取现居住地
     */
	public String getResidence(){
		return this.residence;
	}
 		
	/**
     * 设置现居住地
     */
	public void setResidence(String residence){
		this.residence = residence;
	}
 		
	/**
     * 获取备注
     */
	public String getRemark(){
		return this.remark;
	}
 		
	/**
     * 设置备注
     */
	public void setRemark(String remark){
		this.remark = remark;
	}
 		
	/**
     * 获取工作人员类型
     */
	public String getStaffType(){
		return this.staffType;
	}
 		
	/**
     * 设置工作人员类型
     */
	public void setStaffType(String staffType){
		this.staffType = staffType;
	}
 		
	/**
     * 获取开始工作时间
     */
	public java.util.Date getStartWorkTime(){
		return this.startWorkTime;
	}
 		
	/**
     * 设置开始工作时间
     */
	public void setStartWorkTime(java.util.Date startWorkTime){
		this.startWorkTime = startWorkTime;
	}
 		
	/**
     * 获取合同到期时间
     */
	public java.util.Date getEndContractTime(){
		return this.endContractTime;
	}
 		
	/**
     * 设置合同到期时间
     */
	public void setEndContractTime(java.util.Date endContractTime){
		this.endContractTime = endContractTime;
	}
 		
	/**
     * 获取创建者ID
     */
	public Long getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置创建者ID
     */
	public void setCreateUserId(Long createUserId){
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
     * 获取修改者ID
     */
	public Long getModifyUserId(){
		return this.modifyUserId;
	}
 		
	/**
     * 设置修改者ID
     */
	public void setModifyUserId(Long modifyUserId){
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
	public String getSts(){
		return this.sts;
	}
 		
	/**
     * 设置状态
     */
	public void setSts(String sts){
		this.sts = sts;
	}
 		
	/**
     * 获取jobsId
     */
	public Long getJobsId(){
		return this.jobsId;
	}
 		
	/**
     * 设置jobsId
     */
	public void setJobsId(Long jobsId){
		this.jobsId = jobsId;
	}
 		
	/**
     * 获取encryptionIdNumber
     */
	public String getEncryptionIdNumber(){
		return this.encryptionIdNumber;
	}
 		
	/**
     * 设置encryptionIdNumber
     */
	public void setEncryptionIdNumber(String encryptionIdNumber){
		this.encryptionIdNumber = encryptionIdNumber;
	}
 		
	/**
     * 获取orgCode
     */
	public String getOrgCode(){
		return this.orgCode;
	}
 		
	/**
     * 设置orgCode
     */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
 		
	/**
     * 获取topOrgCode
     */
	public String getTopOrgCode(){
		return this.topOrgCode;
	}
 		
	/**
     * 设置topOrgCode
     */
	public void setTopOrgCode(String topOrgCode){
		this.topOrgCode = topOrgCode;
	}
 }