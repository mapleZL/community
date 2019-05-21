package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>system_appfile</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>序号</td></tr>
 *   <tr><td>topCode</td><td>{@link java.lang.String}</td><td>top_code</td><td>varchar</td><td>顶端编码</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>文件名称</td></tr>
 *   <tr><td>path</td><td>{@link java.lang.String}</td><td>path</td><td>varchar</td><td>服务上路径</td></tr>
 *   <tr><td>fileType</td><td>{@link java.lang.String}</td><td>file_type</td><td>varchar</td><td>文件类型</td></tr>
 *   <tr><td>fileSize</td><td>{@link java.lang.String}</td><td>file_size</td><td>varchar</td><td>文件大小</td></tr>
 *   <tr><td>objectType</td><td>{@link java.lang.String}</td><td>object_type</td><td>varchar</td><td>对象小类</td></tr>
 *   <tr><td>objectId</td><td>{@link java.lang.Long}</td><td>object_id</td><td>bigint</td><td>目标对象ID</td></tr>
 *   <tr><td>objectModule</td><td>{@link java.lang.String}</td><td>object_module</td><td>varchar</td><td>对象大类</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>char</td><td>状态</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>orgCode</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>topOrgCode</td></tr>
 *   <tr><td>subareaCode</td><td>{@link java.lang.String}</td><td>subarea_code</td><td>varchar</td><td>subareaCode</td></tr>
 * </table>
 *
 */
public class SystemAppfile implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.String topCode;
 	private java.lang.String name;
 	private java.lang.String path;
 	private java.lang.String fileType;
 	private java.lang.String fileSize;
 	private java.lang.String objectType;
 	private java.lang.Long objectId;
 	private java.lang.String objectModule;
 	private java.lang.Long createUserId;
 	private java.util.Date createTime;
 	private java.lang.Long modifyUserId;
 	private java.util.Date modifyTime;
 	private java.lang.String sts;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.String subareaCode;
 	
 		
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
     * 获取顶端编码
     */
	public java.lang.String getTopCode(){
		return this.topCode;
	}
 		
	/**
     * 设置顶端编码
     */
	public void setTopCode(java.lang.String topCode){
		this.topCode = topCode;
	}
 		
	/**
     * 获取文件名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置文件名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取服务上路径
     */
	public java.lang.String getPath(){
		return this.path;
	}
 		
	/**
     * 设置服务上路径
     */
	public void setPath(java.lang.String path){
		this.path = path;
	}
 		
	/**
     * 获取文件类型
     */
	public java.lang.String getFileType(){
		return this.fileType;
	}
 		
	/**
     * 设置文件类型
     */
	public void setFileType(java.lang.String fileType){
		this.fileType = fileType;
	}
 		
	/**
     * 获取文件大小
     */
	public java.lang.String getFileSize(){
		return this.fileSize;
	}
 		
	/**
     * 设置文件大小
     */
	public void setFileSize(java.lang.String fileSize){
		this.fileSize = fileSize;
	}
 		
	/**
     * 获取对象小类
     */
	public java.lang.String getObjectType(){
		return this.objectType;
	}
 		
	/**
     * 设置对象小类
     */
	public void setObjectType(java.lang.String objectType){
		this.objectType = objectType;
	}
 		
	/**
     * 获取目标对象ID
     */
	public java.lang.Long getObjectId(){
		return this.objectId;
	}
 		
	/**
     * 设置目标对象ID
     */
	public void setObjectId(java.lang.Long objectId){
		this.objectId = objectId;
	}
 		
	/**
     * 获取对象大类
     */
	public java.lang.String getObjectModule(){
		return this.objectModule;
	}
 		
	/**
     * 设置对象大类
     */
	public void setObjectModule(java.lang.String objectModule){
		this.objectModule = objectModule;
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
     * 获取subareaCode
     */
	public java.lang.String getSubareaCode(){
		return this.subareaCode;
	}
 		
	/**
     * 设置subareaCode
     */
	public void setSubareaCode(java.lang.String subareaCode){
		this.subareaCode = subareaCode;
	}
 }