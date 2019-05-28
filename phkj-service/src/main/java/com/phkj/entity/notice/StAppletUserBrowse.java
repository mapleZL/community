package com.phkj.entity.notice;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>st_applet_user_browse</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>rId</td><td>{@link java.lang.Integer}</td><td>r_id</td><td>int</td><td>关联头条id</td></tr>
 *   <tr><td>browse</td><td>{@link java.lang.Integer}</td><td>browse</td><td>int</td><td>browse</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>浏览人id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>modifyId</td><td>{@link java.lang.Integer}</td><td>modify_id</td><td>int</td><td>modifyId</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>modifyTime</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.Integer}</td><td>sts</td><td>tinyint</td><td>状态</td></tr>
 * </table>
 *
 */
public class StAppletUserBrowse implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.Integer rId;
 	private java.lang.Integer browse;
 	private java.lang.Integer createId;
 	private java.util.Date createTime;
 	private java.lang.Integer modifyId;
 	private java.util.Date modifyTime;
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
     * 获取关联头条id
     */
	public java.lang.Integer getRId(){
		return this.rId;
	}
 		
	/**
     * 设置关联头条id
     */
	public void setRId(java.lang.Integer rId){
		this.rId = rId;
	}
 		
	/**
     * 获取browse
     */
	public java.lang.Integer getBrowse(){
		return this.browse;
	}
 		
	/**
     * 设置browse
     */
	public void setBrowse(java.lang.Integer browse){
		this.browse = browse;
	}
 		
	/**
     * 获取浏览人id
     */
	public java.lang.Integer getCreateId(){
		return this.createId;
	}
 		
	/**
     * 设置浏览人id
     */
	public void setCreateId(java.lang.Integer createId){
		this.createId = createId;
	}
 		
	/**
     * 获取createTime
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置createTime
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取modifyId
     */
	public java.lang.Integer getModifyId(){
		return this.modifyId;
	}
 		
	/**
     * 设置modifyId
     */
	public void setModifyId(java.lang.Integer modifyId){
		this.modifyId = modifyId;
	}
 		
	/**
     * 获取modifyTime
     */
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
 		
	/**
     * 设置modifyTime
     */
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
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
 }