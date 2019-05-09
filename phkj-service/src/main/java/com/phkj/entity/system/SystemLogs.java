package com.phkj.entity.system;

import java.io.Serializable;
/**
 * 系统操作日志表
 * <p>Table: <strong>system_logs</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>optId</td><td>{@link java.lang.Integer}</td><td>opt_id</td><td>int</td><td>操作人ID</td></tr>
 *   <tr><td>optName</td><td>{@link java.lang.String}</td><td>opt_name</td><td>varchar</td><td>操作人姓名</td></tr>
 *   <tr><td>optContent</td><td>{@link java.lang.String}</td><td>opt_content</td><td>varchar</td><td>操作内容</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>int</td><td>创建时间</td></tr>
 *   <tr><td>memberRuleId</td><td>{@link java.lang.Integer}</td><td>member_rule_id</td><td>int</td><td>member_rule</td></tr>
 *   <tr><td>optObject</td><td>{@link java.lang.String}</td><td>opt_object</td><td>varchar</td><td>操作表</td></tr>
 *   <tr><td>optObjectId</td><td>{@link java.lang.Integer}</td><td>opt_object_id</td><td>int</td><td>操作表ID</td></tr>
 * </table>
 *
 */
public class SystemLogs implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer optId;
 	private java.lang.String optName;
 	private java.lang.String optContent;
    private java.util.Date    createTime;                              //createTime
 	private java.lang.Integer memberRuleId;
 	private java.lang.String optObject;
 	private java.lang.Integer optObjectId;
 	private String optType;                                           //操作类型insert, update, select
 	private String optOther;                                          //其他操作记录
 		
	public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }

    public String getOptOther() {
        return optOther;
    }

    public void setOptOther(String optOther) {
        this.optOther = optOther;
    }

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
     * 获取操作人ID
     */
	public java.lang.Integer getOptId(){
		return this.optId;
	}
 		
	/**
     * 设置操作人ID
     */
	public void setOptId(java.lang.Integer optId){
		this.optId = optId;
	}
 		
	/**
     * 获取操作人姓名
     */
	public java.lang.String getOptName(){
		return this.optName;
	}
 		
	/**
     * 设置操作人姓名
     */
	public void setOptName(java.lang.String optName){
		this.optName = optName;
	}
 		
	/**
     * 获取操作内容
     */
	public java.lang.String getOptContent(){
		return this.optContent;
	}
 		
	/**
     * 设置操作内容
     */
	public void setOptContent(java.lang.String optContent){
		this.optContent = optContent;
	}
 		
	public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取member_rule
     */
	public java.lang.Integer getMemberRuleId(){
		return this.memberRuleId;
	}
 		
	/**
     * 设置member_rule
     */
	public void setMemberRuleId(java.lang.Integer memberRuleId){
		this.memberRuleId = memberRuleId;
	}
 		
	/**
     * 获取操作表
     */
	public java.lang.String getOptObject(){
		return this.optObject;
	}
 		
	/**
     * 设置操作表
     */
	public void setOptObject(java.lang.String optObject){
		this.optObject = optObject;
	}
 		
	/**
     * 获取操作表ID
     */
	public java.lang.Integer getOptObjectId(){
		return this.optObjectId;
	}
 		
	/**
     * 设置操作表ID
     */
	public void setOptObjectId(java.lang.Integer optObjectId){
		this.optObjectId = optObjectId;
	}
 }