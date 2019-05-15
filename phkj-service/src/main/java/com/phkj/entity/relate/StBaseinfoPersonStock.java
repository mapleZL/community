package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 人员库
 * <p>Table: <strong>st_baseinfo_person_stock</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>主键</td></tr>
 *   <tr><td>residentiaId</td><td>{@link java.lang.Long}</td><td>residentia_id</td><td>bigint</td><td>小区id</td></tr>
 *   <tr><td>subareaId</td><td>{@link java.lang.Long}</td><td>subarea_id</td><td>bigint</td><td>分区id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>姓名</td></tr>
 *   <tr><td>sex</td><td>{@link java.lang.String}</td><td>sex</td><td>varchar</td><td>性别</td></tr>
 *   <tr><td>householdType</td><td>{@link java.lang.String}</td><td>household_type</td><td>varchar</td><td>户籍类型</td></tr>
 *   <tr><td>nativePlace</td><td>{@link java.lang.String}</td><td>native_place</td><td>varchar</td><td>籍贯</td></tr>
 *   <tr><td>idNumber</td><td>{@link java.lang.String}</td><td>id_number</td><td>varchar</td><td>身份证号</td></tr>
 *   <tr><td>idAddress</td><td>{@link java.lang.String}</td><td>id_address</td><td>varchar</td><td>身份证地址</td></tr>
 *   <tr><td>birthDate</td><td>{@link java.util.Date}</td><td>birth_date</td><td>datetime</td><td>出生日期</td></tr>
 *   <tr><td>nation</td><td>{@link java.lang.String}</td><td>nation</td><td>varchar</td><td>民族</td></tr>
 *   <tr><td>age</td><td>{@link java.lang.Long}</td><td>age</td><td>bigint</td><td>年龄</td></tr>
 *   <tr><td>ageSegment</td><td>{@link java.lang.String}</td><td>age_segment</td><td>varchar</td><td>年龄分段</td></tr>
 *   <tr><td>validBegin</td><td>{@link java.util.Date}</td><td>valid_begin</td><td>datetime</td><td>有效期开始</td></tr>
 *   <tr><td>validEnd</td><td>{@link java.util.Date}</td><td>valid_end</td><td>datetime</td><td>有效期至</td></tr>
 *   <tr><td>ever</td><td>{@link java.lang.String}</td><td>ever</td><td>varchar</td><td>永久有效</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>手机号</td></tr>
 *   <tr><td>sourceTable</td><td>{@link java.lang.String}</td><td>source_table</td><td>varchar</td><td>来源表</td></tr>
 *   <tr><td>sourceId</td><td>{@link java.lang.Long}</td><td>source_id</td><td>bigint</td><td>来源表id</td></tr>
 *   <tr><td>capturePicId</td><td>{@link java.lang.Long}</td><td>capture_pic_id</td><td>bigint</td><td>人脸图片id</td></tr>
 *   <tr><td>personType</td><td>{@link java.lang.String}</td><td>person_type</td><td>char</td><td>人员类型(1常住2常来往3访客)</td></tr>
 *   <tr><td>effectiveState</td><td>{@link java.lang.String}</td><td>effective_state</td><td>char</td><td>生效状态:Y|N</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>sts</td></tr>
 *   <tr><td>hkPersonId</td><td>{@link java.lang.String}</td><td>hk_person_id</td><td>varchar</td><td>87对应的personId</td></tr>
 *   <tr><td>hkGroupPersonId</td><td>{@link java.lang.String}</td><td>hk_group_person_id</td><td>varchar</td><td>87上人员分组中的id(白名单id)</td></tr>
 *   <tr><td>hkPersonNo</td><td>{@link java.lang.String}</td><td>hk_person_no</td><td>varchar</td><td>hkPersonNo</td></tr>
 *   <tr><td>encryptionIdNumber</td><td>{@link java.lang.String}</td><td>encryption_id_number</td><td>varchar</td><td>encryptionIdNumber</td></tr>
 *   <tr><td>personIdcardDataId</td><td>{@link java.lang.Long}</td><td>person_idcard_data_id</td><td>bigint</td><td>来源idcard表中的id</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>hkGroupPersonFlag</td><td>{@link java.lang.String}</td><td>hk_group_person_flag</td><td>varchar</td><td>标志该人员是否添加87平台的人员分组</td></tr>
 * </table>
 *
 */
public class StBaseinfoPersonStock implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Long residentiaId;
 	private java.lang.Long subareaId;
 	private java.lang.String name;
 	private java.lang.String sex;
 	private java.lang.String householdType;
 	private java.lang.String nativePlace;
 	private java.lang.String idNumber;
 	private java.lang.String idAddress;
 	private java.util.Date birthDate;
 	private java.lang.String nation;
 	private java.lang.Long age;
 	private java.lang.String ageSegment;
 	private java.util.Date validBegin;
 	private java.util.Date validEnd;
 	private java.lang.String ever;
 	private java.lang.String mobile;
 	private java.lang.String sourceTable;
 	private java.lang.Long sourceId;
 	private java.lang.Long capturePicId;
 	private java.lang.String personType;
 	private java.lang.String effectiveState;
 	private java.util.Date createTime;
 	private java.lang.Long createUserId;
 	private java.util.Date modifyTime;
 	private java.lang.Long modifyUserId;
 	private java.lang.String sts;
 	private java.lang.String hkPersonId;
 	private java.lang.String hkGroupPersonId;
 	private java.lang.String hkPersonNo;
 	private java.lang.String encryptionIdNumber;
 	private java.lang.Long personIdcardDataId;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.String hkGroupPersonFlag;
 	
 		
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
     * 获取小区id
     */
	public java.lang.Long getResidentiaId(){
		return this.residentiaId;
	}
 		
	/**
     * 设置小区id
     */
	public void setResidentiaId(java.lang.Long residentiaId){
		this.residentiaId = residentiaId;
	}
 		
	/**
     * 获取分区id
     */
	public java.lang.Long getSubareaId(){
		return this.subareaId;
	}
 		
	/**
     * 设置分区id
     */
	public void setSubareaId(java.lang.Long subareaId){
		this.subareaId = subareaId;
	}
 		
	/**
     * 获取姓名
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置姓名
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
     * 获取户籍类型
     */
	public java.lang.String getHouseholdType(){
		return this.householdType;
	}
 		
	/**
     * 设置户籍类型
     */
	public void setHouseholdType(java.lang.String householdType){
		this.householdType = householdType;
	}
 		
	/**
     * 获取籍贯
     */
	public java.lang.String getNativePlace(){
		return this.nativePlace;
	}
 		
	/**
     * 设置籍贯
     */
	public void setNativePlace(java.lang.String nativePlace){
		this.nativePlace = nativePlace;
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
     * 获取来源表
     */
	public java.lang.String getSourceTable(){
		return this.sourceTable;
	}
 		
	/**
     * 设置来源表
     */
	public void setSourceTable(java.lang.String sourceTable){
		this.sourceTable = sourceTable;
	}
 		
	/**
     * 获取来源表id
     */
	public java.lang.Long getSourceId(){
		return this.sourceId;
	}
 		
	/**
     * 设置来源表id
     */
	public void setSourceId(java.lang.Long sourceId){
		this.sourceId = sourceId;
	}
 		
	/**
     * 获取人脸图片id
     */
	public java.lang.Long getCapturePicId(){
		return this.capturePicId;
	}
 		
	/**
     * 设置人脸图片id
     */
	public void setCapturePicId(java.lang.Long capturePicId){
		this.capturePicId = capturePicId;
	}
 		
	/**
     * 获取人员类型(1常住2常来往3访客)
     */
	public java.lang.String getPersonType(){
		return this.personType;
	}
 		
	/**
     * 设置人员类型(1常住2常来往3访客)
     */
	public void setPersonType(java.lang.String personType){
		this.personType = personType;
	}
 		
	/**
     * 获取生效状态:Y|N
     */
	public java.lang.String getEffectiveState(){
		return this.effectiveState;
	}
 		
	/**
     * 设置生效状态:Y|N
     */
	public void setEffectiveState(java.lang.String effectiveState){
		this.effectiveState = effectiveState;
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
     * 获取87对应的personId
     */
	public java.lang.String getHkPersonId(){
		return this.hkPersonId;
	}
 		
	/**
     * 设置87对应的personId
     */
	public void setHkPersonId(java.lang.String hkPersonId){
		this.hkPersonId = hkPersonId;
	}
 		
	/**
     * 获取87上人员分组中的id(白名单id)
     */
	public java.lang.String getHkGroupPersonId(){
		return this.hkGroupPersonId;
	}
 		
	/**
     * 设置87上人员分组中的id(白名单id)
     */
	public void setHkGroupPersonId(java.lang.String hkGroupPersonId){
		this.hkGroupPersonId = hkGroupPersonId;
	}
 		
	/**
     * 获取hkPersonNo
     */
	public java.lang.String getHkPersonNo(){
		return this.hkPersonNo;
	}
 		
	/**
     * 设置hkPersonNo
     */
	public void setHkPersonNo(java.lang.String hkPersonNo){
		this.hkPersonNo = hkPersonNo;
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
     * 获取来源idcard表中的id
     */
	public java.lang.Long getPersonIdcardDataId(){
		return this.personIdcardDataId;
	}
 		
	/**
     * 设置来源idcard表中的id
     */
	public void setPersonIdcardDataId(java.lang.Long personIdcardDataId){
		this.personIdcardDataId = personIdcardDataId;
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
     * 获取标志该人员是否添加87平台的人员分组
     */
	public java.lang.String getHkGroupPersonFlag(){
		return this.hkGroupPersonFlag;
	}
 		
	/**
     * 设置标志该人员是否添加87平台的人员分组
     */
	public void setHkGroupPersonFlag(java.lang.String hkGroupPersonFlag){
		this.hkGroupPersonFlag = hkGroupPersonFlag;
	}
 }