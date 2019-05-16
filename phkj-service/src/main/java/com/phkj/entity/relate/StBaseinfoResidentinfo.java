package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 居民信息
 * <p>Table: <strong>st_baseinfo_residentinfo</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>主键</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>居民姓名</td></tr>
 *   <tr><td>sex</td><td>{@link java.lang.String}</td><td>sex</td><td>varchar</td><td>性别</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>租房开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>租房结束时间</td></tr>
 *   <tr><td>idNumber</td><td>{@link java.lang.String}</td><td>id_number</td><td>varchar</td><td>身份证号</td></tr>
 *   <tr><td>idAddress</td><td>{@link java.lang.String}</td><td>id_address</td><td>varchar</td><td>身份证地址</td></tr>
 *   <tr><td>birthDate</td><td>{@link java.util.Date}</td><td>birth_date</td><td>datetime</td><td>出生日期</td></tr>
 *   <tr><td>nation</td><td>{@link java.lang.String}</td><td>nation</td><td>varchar</td><td>民族</td></tr>
 *   <tr><td>age</td><td>{@link java.lang.Long}</td><td>age</td><td>bigint</td><td>年龄</td></tr>
 *   <tr><td>ageSegment</td><td>{@link java.lang.String}</td><td>age_segment</td><td>varchar</td><td>年龄分段</td></tr>
 *   <tr><td>validBegin</td><td>{@link java.util.Date}</td><td>valid_begin</td><td>datetime</td><td>有效期开始</td></tr>
 *   <tr><td>validEnd</td><td>{@link java.util.Date}</td><td>valid_end</td><td>datetime</td><td>有效期至</td></tr>
 *   <tr><td>ever</td><td>{@link java.lang.String}</td><td>ever</td><td>varchar</td><td>永久有效</td></tr>
 *   <tr><td>capturePicId</td><td>{@link java.lang.Long}</td><td>capture_pic_id</td><td>bigint</td><td>capturePicId</td></tr>
 *   <tr><td>politicalStatus</td><td>{@link java.lang.String}</td><td>political_status</td><td>char</td><td>政治面貌(1群众、2少先队员、3共青团员、4预备党员、5共产党员、6民主党派)</td></tr>
 *   <tr><td>degree</td><td>{@link java.lang.String}</td><td>degree</td><td>char</td><td>文化程度/学历(包括1博士、2硕士、3本科、4大专、5中专/中技、6技工学校、7高中、8初中、9小学、10文盲/半文盲)</td></tr>
 *   <tr><td>maritalStatus</td><td>{@link java.lang.String}</td><td>marital_status</td><td>char</td><td>婚姻状态(0=未知 1=已婚 2=未婚 3=离异)</td></tr>
 *   <tr><td>householdRegisterType</td><td>{@link java.lang.String}</td><td>household_register_type</td><td>char</td><td>户籍性质1=常住人口 2=流动人口</td></tr>
 *   <tr><td>hasPermit</td><td>{@link java.lang.String}</td><td>has_permit</td><td>char</td><td>有无办理居住证(Y，N)</td></tr>
 *   <tr><td>permitImg</td><td>{@link java.lang.String}</td><td>permit_img</td><td>varchar</td><td>居住证照片</td></tr>
 *   <tr><td>householdRegisterCode</td><td>{@link java.lang.String}</td><td>household_register_code</td><td>varchar</td><td>户籍号</td></tr>
 *   <tr><td>householdRegisterAddress</td><td>{@link java.lang.String}</td><td>household_register_address</td><td>varchar</td><td>户籍地址</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>qq</td><td>{@link java.lang.String}</td><td>qq</td><td>varchar</td><td>qq号</td></tr>
 *   <tr><td>wechat</td><td>{@link java.lang.String}</td><td>wechat</td><td>varchar</td><td>微信</td></tr>
 *   <tr><td>contactor</td><td>{@link java.lang.String}</td><td>contactor</td><td>varchar</td><td>紧急联系人</td></tr>
 *   <tr><td>contactorMobile</td><td>{@link java.lang.String}</td><td>contactor_mobile</td><td>varchar</td><td>紧急联系人电话</td></tr>
 *   <tr><td>guardian</td><td>{@link java.lang.String}</td><td>guardian</td><td>varchar</td><td>监护人</td></tr>
 *   <tr><td>hasHouseholder</td><td>{@link java.lang.String}</td><td>has_householder</td><td>char</td><td>是否户主</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>lastDoorTime</td><td>{@link java.util.Date}</td><td>last_door_time</td><td>datetime</td><td>最后进门时间</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>sts</td></tr>
 *   <tr><td>carNo</td><td>{@link java.lang.String}</td><td>car_no</td><td>varchar</td><td>车牌号</td></tr>
 *   <tr><td>encryptionIdNumber</td><td>{@link java.lang.String}</td><td>encryption_id_number</td><td>varchar</td><td>encryptionIdNumber</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>residentiaId</td><td>{@link java.lang.Long}</td><td>residentia_id</td><td>bigint</td><td>residentiaId</td></tr>
 *   <tr><td>subareaId</td><td>{@link java.lang.Long}</td><td>subarea_id</td><td>bigint</td><td>subareaId</td></tr>
 *   <tr><td>label</td><td>{@link java.lang.String}</td><td>label</td><td>varchar</td><td>人员标签</td></tr>
 *   <tr><td>nationality</td><td>{@link java.lang.String}</td><td>nationality</td><td>varchar</td><td>国籍</td></tr>
 *   <tr><td>warning</td><td>{@link java.lang.String}</td><td>warning</td><td>varchar</td><td>是否预警</td></tr>
 * </table>
 *
 */
public class StBaseinfoResidentinfo implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.String name;
 	private java.lang.String sex;
 	private java.util.Date startTime;
 	private java.util.Date endTime;
 	private java.lang.String idNumber;
 	private java.lang.String idAddress;
 	private java.util.Date birthDate;
 	private java.lang.String nation;
 	private java.lang.Long age;
 	private java.lang.String ageSegment;
 	private java.util.Date validBegin;
 	private java.util.Date validEnd;
 	private java.lang.String ever;
 	private java.lang.Long capturePicId;
 	private java.lang.String politicalStatus;
 	private java.lang.String degree;
 	private java.lang.String maritalStatus;
 	private java.lang.String householdRegisterType;
 	private java.lang.String hasPermit;
 	private java.lang.String permitImg;
 	private java.lang.String householdRegisterCode;
 	private java.lang.String householdRegisterAddress;
 	private java.lang.String mobile;
 	private java.lang.String qq;
 	private java.lang.String wechat;
 	private java.lang.String contactor;
 	private java.lang.String contactorMobile;
 	private java.lang.String guardian;
 	private java.lang.String hasHouseholder;
 	private java.lang.String remark;
 	private java.util.Date lastDoorTime;
 	private java.util.Date createTime;
 	private java.lang.Long createUserId;
 	private java.util.Date modifyTime;
 	private java.lang.Long modifyUserId;
 	private java.lang.String sts;
 	private java.lang.String carNo;
 	private java.lang.String encryptionIdNumber;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.Long residentiaId;
 	private java.lang.Long subareaId;
 	private java.lang.String label;
 	private java.lang.String nationality;
 	private java.lang.String warning;
 	
 		
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
     * 获取居民姓名
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置居民姓名
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取性别
     */
	public java.lang.String getSex(){
		return this.sex;
	}
 		
	/**
     * 设置性别
     */
	public void setSex(java.lang.String sex){
		this.sex = sex;
	}
 		
	/**
     * 获取租房开始时间
     */
	public java.util.Date getStartTime(){
		return this.startTime;
	}
 		
	/**
     * 设置租房开始时间
     */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
 		
	/**
     * 获取租房结束时间
     */
	public java.util.Date getEndTime(){
		return this.endTime;
	}
 		
	/**
     * 设置租房结束时间
     */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
 		
	/**
     * 获取身份证号
     */
	public java.lang.String getIdNumber(){
		return this.idNumber;
	}
 		
	/**
     * 设置身份证号
     */
	public void setIdNumber(java.lang.String idNumber){
		this.idNumber = idNumber;
	}
 		
	/**
     * 获取身份证地址
     */
	public java.lang.String getIdAddress(){
		return this.idAddress;
	}
 		
	/**
     * 设置身份证地址
     */
	public void setIdAddress(java.lang.String idAddress){
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
     * 获取民族
     */
	public java.lang.String getNation(){
		return this.nation;
	}
 		
	/**
     * 设置民族
     */
	public void setNation(java.lang.String nation){
		this.nation = nation;
	}
 		
	/**
     * 获取年龄
     */
	public java.lang.Long getAge(){
		return this.age;
	}
 		
	/**
     * 设置年龄
     */
	public void setAge(java.lang.Long age){
		this.age = age;
	}
 		
	/**
     * 获取年龄分段
     */
	public java.lang.String getAgeSegment(){
		return this.ageSegment;
	}
 		
	/**
     * 设置年龄分段
     */
	public void setAgeSegment(java.lang.String ageSegment){
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
	public java.lang.String getEver(){
		return this.ever;
	}
 		
	/**
     * 设置永久有效
     */
	public void setEver(java.lang.String ever){
		this.ever = ever;
	}
 		
	/**
     * 获取capturePicId
     */
	public java.lang.Long getCapturePicId(){
		return this.capturePicId;
	}
 		
	/**
     * 设置capturePicId
     */
	public void setCapturePicId(java.lang.Long capturePicId){
		this.capturePicId = capturePicId;
	}
 		
	/**
     * 获取政治面貌(1群众、2少先队员、3共青团员、4预备党员、5共产党员、6民主党派)
     */
	public java.lang.String getPoliticalStatus(){
		return this.politicalStatus;
	}
 		
	/**
     * 设置政治面貌(1群众、2少先队员、3共青团员、4预备党员、5共产党员、6民主党派)
     */
	public void setPoliticalStatus(java.lang.String politicalStatus){
		this.politicalStatus = politicalStatus;
	}
 		
	/**
     * 获取文化程度/学历(包括1博士、2硕士、3本科、4大专、5中专/中技、6技工学校、7高中、8初中、9小学、10文盲/半文盲)
     */
	public java.lang.String getDegree(){
		return this.degree;
	}
 		
	/**
     * 设置文化程度/学历(包括1博士、2硕士、3本科、4大专、5中专/中技、6技工学校、7高中、8初中、9小学、10文盲/半文盲)
     */
	public void setDegree(java.lang.String degree){
		this.degree = degree;
	}
 		
	/**
     * 获取婚姻状态(0=未知 1=已婚 2=未婚 3=离异)
     */
	public java.lang.String getMaritalStatus(){
		return this.maritalStatus;
	}
 		
	/**
     * 设置婚姻状态(0=未知 1=已婚 2=未婚 3=离异)
     */
	public void setMaritalStatus(java.lang.String maritalStatus){
		this.maritalStatus = maritalStatus;
	}
 		
	/**
     * 获取户籍性质1=常住人口 2=流动人口
     */
	public java.lang.String getHouseholdRegisterType(){
		return this.householdRegisterType;
	}
 		
	/**
     * 设置户籍性质1=常住人口 2=流动人口
     */
	public void setHouseholdRegisterType(java.lang.String householdRegisterType){
		this.householdRegisterType = householdRegisterType;
	}
 		
	/**
     * 获取有无办理居住证(Y，N)
     */
	public java.lang.String getHasPermit(){
		return this.hasPermit;
	}
 		
	/**
     * 设置有无办理居住证(Y，N)
     */
	public void setHasPermit(java.lang.String hasPermit){
		this.hasPermit = hasPermit;
	}
 		
	/**
     * 获取居住证照片
     */
	public java.lang.String getPermitImg(){
		return this.permitImg;
	}
 		
	/**
     * 设置居住证照片
     */
	public void setPermitImg(java.lang.String permitImg){
		this.permitImg = permitImg;
	}
 		
	/**
     * 获取户籍号
     */
	public java.lang.String getHouseholdRegisterCode(){
		return this.householdRegisterCode;
	}
 		
	/**
     * 设置户籍号
     */
	public void setHouseholdRegisterCode(java.lang.String householdRegisterCode){
		this.householdRegisterCode = householdRegisterCode;
	}
 		
	/**
     * 获取户籍地址
     */
	public java.lang.String getHouseholdRegisterAddress(){
		return this.householdRegisterAddress;
	}
 		
	/**
     * 设置户籍地址
     */
	public void setHouseholdRegisterAddress(java.lang.String householdRegisterAddress){
		this.householdRegisterAddress = householdRegisterAddress;
	}
 		
	/**
     * 获取手机号
     */
	public java.lang.String getMobile(){
		return this.mobile;
	}
 		
	/**
     * 设置手机号
     */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
 		
	/**
     * 获取qq号
     */
	public java.lang.String getQq(){
		return this.qq;
	}
 		
	/**
     * 设置qq号
     */
	public void setQq(java.lang.String qq){
		this.qq = qq;
	}
 		
	/**
     * 获取微信
     */
	public java.lang.String getWechat(){
		return this.wechat;
	}
 		
	/**
     * 设置微信
     */
	public void setWechat(java.lang.String wechat){
		this.wechat = wechat;
	}
 		
	/**
     * 获取紧急联系人
     */
	public java.lang.String getContactor(){
		return this.contactor;
	}
 		
	/**
     * 设置紧急联系人
     */
	public void setContactor(java.lang.String contactor){
		this.contactor = contactor;
	}
 		
	/**
     * 获取紧急联系人电话
     */
	public java.lang.String getContactorMobile(){
		return this.contactorMobile;
	}
 		
	/**
     * 设置紧急联系人电话
     */
	public void setContactorMobile(java.lang.String contactorMobile){
		this.contactorMobile = contactorMobile;
	}
 		
	/**
     * 获取监护人
     */
	public java.lang.String getGuardian(){
		return this.guardian;
	}
 		
	/**
     * 设置监护人
     */
	public void setGuardian(java.lang.String guardian){
		this.guardian = guardian;
	}
 		
	/**
     * 获取是否户主
     */
	public java.lang.String getHasHouseholder(){
		return this.hasHouseholder;
	}
 		
	/**
     * 设置是否户主
     */
	public void setHasHouseholder(java.lang.String hasHouseholder){
		this.hasHouseholder = hasHouseholder;
	}
 		
	/**
     * 获取备注
     */
	public java.lang.String getRemark(){
		return this.remark;
	}
 		
	/**
     * 设置备注
     */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
 		
	/**
     * 获取最后进门时间
     */
	public java.util.Date getLastDoorTime(){
		return this.lastDoorTime;
	}
 		
	/**
     * 设置最后进门时间
     */
	public void setLastDoorTime(java.util.Date lastDoorTime){
		this.lastDoorTime = lastDoorTime;
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
     * 获取sts
     */
	public java.lang.String getSts(){
		return this.sts;
	}
 		
	/**
     * 设置sts
     */
	public void setSts(java.lang.String sts){
		this.sts = sts;
	}
 		
	/**
     * 获取车牌号
     */
	public java.lang.String getCarNo(){
		return this.carNo;
	}
 		
	/**
     * 设置车牌号
     */
	public void setCarNo(java.lang.String carNo){
		this.carNo = carNo;
	}
 		
	/**
     * 获取encryptionIdNumber
     */
	public java.lang.String getEncryptionIdNumber(){
		return this.encryptionIdNumber;
	}
 		
	/**
     * 设置encryptionIdNumber
     */
	public void setEncryptionIdNumber(java.lang.String encryptionIdNumber){
		this.encryptionIdNumber = encryptionIdNumber;
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
     * 获取人员标签
     */
	public java.lang.String getLabel(){
		return this.label;
	}
 		
	/**
     * 设置人员标签
     */
	public void setLabel(java.lang.String label){
		this.label = label;
	}
 		
	/**
     * 获取国籍
     */
	public java.lang.String getNationality(){
		return this.nationality;
	}
 		
	/**
     * 设置国籍
     */
	public void setNationality(java.lang.String nationality){
		this.nationality = nationality;
	}
 		
	/**
     * 获取是否预警
     */
	public java.lang.String getWarning(){
		return this.warning;
	}
 		
	/**
     * 设置是否预警
     */
	public void setWarning(java.lang.String warning){
		this.warning = warning;
	}
 }