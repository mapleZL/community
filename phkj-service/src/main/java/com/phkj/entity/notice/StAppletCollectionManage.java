package com.phkj.entity.notice;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>st_applet_collection_manage</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>noticeId</td><td>{@link java.lang.Long}</td><td>notice_id</td><td>bigint</td><td>关联公告id</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>公告类型</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>memberId</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>用户手机号</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人id</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.Integer}</td><td>sts</td><td>tinyint</td><td>状态</td></tr>
 * </table>
 *
 */
import java.util.Date;
public class StAppletCollectionManage implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.Long noticeId;
 	private java.lang.String type;
 	private java.lang.Integer memberId;
 	private java.lang.String phone;
 	private java.lang.Integer createId;
 	private Date createTime;
 	private java.lang.String createName;
 	private java.lang.Integer sts;
 	
 		
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
     * 获取关联公告id
     */
	public java.lang.Long getNoticeId(){
		return this.noticeId;
	}
 		
	/**
     * 设置关联公告id
     */
	public void setNoticeId(java.lang.Long noticeId){
		this.noticeId = noticeId;
	}
 		
	/**
     * 获取公告类型
     */
	public java.lang.String getType(){
		return this.type;
	}
 		
	/**
     * 设置公告类型
     */
	public void setType(java.lang.String type){
		this.type = type;
	}
 		
	/**
     * 获取memberId
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置memberId
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取用户手机号
     */
	public java.lang.String getPhone(){
		return this.phone;
	}
 		
	/**
     * 设置用户手机号
     */
	public void setPhone(java.lang.String phone){
		this.phone = phone;
	}
 		
	/**
     * 获取创建人id
     */
	public java.lang.Integer getCreateId(){
		return this.createId;
	}
 		
	/**
     * 设置创建人id
     */
	public void setCreateId(java.lang.Integer createId){
		this.createId = createId;
	}
 		
	/**
     * 获取createName
     */
	public java.lang.String getCreateName(){
		return this.createName;
	}
 		
	/**
     * 设置createName
     */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
 		
	/**
     * 获取状态
     */
	public java.lang.Integer getSts(){
		return this.sts;
	}
 		
	/**
     * 设置状态
     */
	public void setSts(java.lang.Integer sts){
		this.sts = sts;
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
	
 }