package com.phkj.entity.relate;

import java.io.Serializable;
/**
 * 街道小区组织表
 * <p>Table: <strong>st_baseinfo_organization</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>主键ID</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>行政区域名称</td></tr>
 *   <tr><td>orgCode</td><td>{@link java.lang.String}</td><td>org_code</td><td>varchar</td><td>行政区域编码</td></tr>
 *   <tr><td>topOrgCode</td><td>{@link java.lang.String}</td><td>top_org_code</td><td>varchar</td><td>上级行政区域编码</td></tr>
 *   <tr><td>topId</td><td>{@link java.lang.Integer}</td><td>top_id</td><td>int</td><td>上级ID</td></tr>
 *   <tr><td>region</td><td>{@link java.lang.String}</td><td>region</td><td>varchar</td><td>区域类别</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.String}</td><td>sts</td><td>varchar</td><td>状态</td></tr>
 *   <tr><td>isEnable</td><td>{@link java.lang.String}</td><td>is_enable</td><td>varchar</td><td>业务状态</td></tr>
 *   <tr><td>createUserId</td><td>{@link java.lang.Long}</td><td>create_user_id</td><td>bigint</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>modifyUserId</td><td>{@link java.lang.Long}</td><td>modify_user_id</td><td>bigint</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改时间</td></tr>
 * </table>
 *
 */
public class StBaseinfoOrganization implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.String name;
 	private java.lang.String orgCode;
 	private java.lang.String topOrgCode;
 	private java.lang.Integer topId;
 	private java.lang.String region;
 	private java.lang.String sts;
 	private java.lang.String isEnable;
 	private java.lang.Long createUserId;
 	private java.util.Date createTime;
 	private java.lang.Long modifyUserId;
 	private java.util.Date modifyTime;
 	
 		
	/**
     * 获取主键ID
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置主键ID
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取行政区域名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置行政区域名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取行政区域编码
     */
	public java.lang.String getOrgCode(){
		return this.orgCode;
	}
 		
	/**
     * 设置行政区域编码
     */
	public void setOrgCode(java.lang.String orgCode){
		this.orgCode = orgCode;
	}
 		
	/**
     * 获取上级行政区域编码
     */
	public java.lang.String getTopOrgCode(){
		return this.topOrgCode;
	}
 		
	/**
     * 设置上级行政区域编码
     */
	public void setTopOrgCode(java.lang.String topOrgCode){
		this.topOrgCode = topOrgCode;
	}
 		
	/**
     * 获取上级ID
     */
	public java.lang.Integer getTopId(){
		return this.topId;
	}
 		
	/**
     * 设置上级ID
     */
	public void setTopId(java.lang.Integer topId){
		this.topId = topId;
	}
 		
	/**
     * 获取区域类别
     */
	public java.lang.String getRegion(){
		return this.region;
	}
 		
	/**
     * 设置区域类别
     */
	public void setRegion(java.lang.String region){
		this.region = region;
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
     * 获取业务状态
     */
	public java.lang.String getIsEnable(){
		return this.isEnable;
	}
 		
	/**
     * 设置业务状态
     */
	public void setIsEnable(java.lang.String isEnable){
		this.isEnable = isEnable;
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
 }