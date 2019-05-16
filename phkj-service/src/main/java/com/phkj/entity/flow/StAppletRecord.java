package com.phkj.entity.flow;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>st_applet_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>rId</td><td>{@link String}</td><td>r_id</td><td>varchar</td><td>关联数据id</td></tr>
 *   <tr><td>type</td><td>{@link String}</td><td>type</td><td>varchar</td><td>任务类型</td></tr>
 *   <tr><td>createUserName</td><td>{@link String}</td><td>create_user_name</td><td>varchar</td><td>操作人名称</td></tr>
 *   <tr><td>createUserId</td><td>{@link String}</td><td>create_user_id</td><td>varchar</td><td>操作人id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>timestamp</td><td>操作时间</td></tr>
 *   <tr><td>remark</td><td>{@link String}</td><td>remark</td><td>varchar</td><td>备注</td></tr>
 *   <tr><td>sts</td><td>{@link Integer}</td><td>sts</td><td>tinyint</td><td>状态0表示删除，1表示未删除</td></tr>
 * </table>
 *
 */
public class StAppletRecord implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private Long id;
 	private String rId;
 	private String type;
 	private String createUserName;
 	private String createUserId;
 	private java.util.Date createTime;
 	private String remark;
 	private Integer sts;
 	
 		
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
     * 获取关联数据id
     */
	public String getRId(){
		return this.rId;
	}
 		
	/**
     * 设置关联数据id
     */
	public void setRId(String rId){
		this.rId = rId;
	}
 		
	/**
     * 获取任务类型
     */
	public String getType(){
		return this.type;
	}
 		
	/**
     * 设置任务类型
     */
	public void setType(String type){
		this.type = type;
	}
 		
	/**
     * 获取操作人名称
     */
	public String getCreateUserName(){
		return this.createUserName;
	}
 		
	/**
     * 设置操作人名称
     */
	public void setCreateUserName(String createUserName){
		this.createUserName = createUserName;
	}
 		
	/**
     * 获取操作人id
     */
	public String getCreateUserId(){
		return this.createUserId;
	}
 		
	/**
     * 设置操作人id
     */
	public void setCreateUserId(String createUserId){
		this.createUserId = createUserId;
	}
 		
	/**
     * 获取操作时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置操作时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
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
     * 获取状态0表示删除，1表示未删除
     */
	public Integer getSts(){
		return this.sts;
	}
 		
	/**
     * 设置状态0表示删除，1表示未删除
     */
	public void setSts(Integer sts){
		this.sts = sts;
	}
 }