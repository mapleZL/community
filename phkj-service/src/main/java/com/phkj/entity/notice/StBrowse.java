package com.phkj.entity.notice;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>st_browse</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>noticeId</td><td>{@link java.lang.Long}</td><td>notice_id</td><td>bigint</td><td>关联通告id</td></tr>
 *   <tr><td>browseVolume</td><td>{@link java.lang.Integer}</td><td>browse_volume</td><td>int</td><td>浏览量</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建日期</td></tr>
 *   <tr><td>modifyId</td><td>{@link java.lang.Integer}</td><td>modify_id</td><td>int</td><td>修改人</td></tr>
 *   <tr><td>modifyTime</td><td>{@link java.util.Date}</td><td>modify_time</td><td>datetime</td><td>修改日期</td></tr>
 *   <tr><td>sts</td><td>{@link java.lang.Integer}</td><td>sts</td><td>tinyint</td><td>使用状态</td></tr>
 * </table>
 *
 */
public class StBrowse implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.Long noticeId;
 	private java.lang.Long browseVolume;
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
     * 获取关联通告id
     */
	public java.lang.Long getNoticeId(){
		return this.noticeId;
	}
 		
	/**
     * 设置关联通告id
     */
	public void setNoticeId(java.lang.Long noticeId){
		this.noticeId = noticeId;
	}
 		
	/**
     * 获取浏览量
     */
	public java.lang.Long getBrowseVolume(){
		return this.browseVolume;
	}
 		
	/**
     * 设置浏览量
     */
	public void setBrowseVolume(java.lang.Long browseVolume){
		this.browseVolume = browseVolume;
	}
 		
	/**
     * 获取创建人
     */
	public java.lang.Integer getCreateId(){
		return this.createId;
	}
 		
	/**
     * 设置创建人
     */
	public void setCreateId(java.lang.Integer createId){
		this.createId = createId;
	}
 		
	/**
     * 获取创建日期
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置创建日期
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取修改人
     */
	public java.lang.Integer getModifyId(){
		return this.modifyId;
	}
 		
	/**
     * 设置修改人
     */
	public void setModifyId(java.lang.Integer modifyId){
		this.modifyId = modifyId;
	}
 		
	/**
     * 获取修改日期
     */
	public java.util.Date getModifyTime(){
		return this.modifyTime;
	}
 		
	/**
     * 设置修改日期
     */
	public void setModifyTime(java.util.Date modifyTime){
		this.modifyTime = modifyTime;
	}
 		
	/**
     * 获取使用状态
     */
	public java.lang.Integer getSts(){
		return this.sts;
	}
 		
	/**
     * 设置使用状态
     */
	public void setSts(java.lang.Integer sts){
		this.sts = sts;
	}
 }