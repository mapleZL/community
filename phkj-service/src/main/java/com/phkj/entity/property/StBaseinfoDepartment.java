package com.phkj.entity.property;

import java.io.Serializable;
/**
 * 部门维护表
 *
 */
public class StBaseinfoDepartment implements Serializable {
 
 	private Long id;
 	private String name;
 	private String code;
 	private String orgCode;
 	private Long topId;
 	private String region;
 	private String topOrgCode;
 	private Long createUserId;
 	private java.util.Date createTime;
 	private Long modifyUserId;
 	private java.util.Date modifyTime;
 	private String sts;
 	
 		
	/**
     * 获取序号
     */
	public Long getId(){
		return this.id;
	}
 		
	/**
     * 设置序号
     */
	public void setId(Long id){
		this.id = id;
	}
 		
	/**
     * 获取部门名称
     */
	public String getName(){
		return this.name;
	}
 		
	/**
     * 设置部门名称
     */
	public void setName(String name){
		this.name = name;
	}
 		
	/**
     * 获取部门编号
     */
	public String getCode(){
		return this.code;
	}
 		
	/**
     * 设置部门编号
     */
	public void setCode(String code){
		this.code = code;
	}
 		
	/**
     * 获取组织机构编码
     */
	public String getOrgCode(){
		return this.orgCode;
	}
 		
	/**
     * 设置组织机构编码
     */
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}
 		
	/**
     * 获取topId
     */
	public Long getTopId(){
		return this.topId;
	}
 		
	/**
     * 设置topId
     */
	public void setTopId(Long topId){
		this.topId = topId;
	}
 		
	/**
     * 获取region
     */
	public String getRegion(){
		return this.region;
	}
 		
	/**
     * 设置region
     */
	public void setRegion(String region){
		this.region = region;
	}
 		
	/**
     * 获取市级机构编码
     */
	public String getTopOrgCode(){
		return this.topOrgCode;
	}
 		
	/**
     * 设置市级机构编码
     */
	public void setTopOrgCode(String topOrgCode){
		this.topOrgCode = topOrgCode;
	}
 		
	/**
     * 获取创建人
     */
	public Long getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置创建人
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
     * 获取修改人
     */
	public Long getModifyUserId(){
		return this.modifyUserId;
	}
 		
	/**
     * 设置修改人
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
     * 获取表状态
     */
	public String getSts(){
		return this.sts;
	}
 		
	/**
     * 设置表状态
     */
	public void setSts(String sts){
		this.sts = sts;
	}
 }