package com.phkj.entity.product;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>wms_classify</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>wmsCategory</td><td>{@link java.lang.String}</td><td>wms_category</td><td>varchar</td><td>wmsCategory</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class WmsClassify implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.String  wmsCategory;
 	private java.lang.String  createUser;
 	private java.util.Date    createTime;
 	private java.util.Date    updateTime;
 	private java.lang.Integer state;
 	
 		
	public java.lang.String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(java.lang.String createUser) {
        this.createUser = createUser;
    }

    public java.lang.Integer getState() {
        return state;
    }

    public void setState(java.lang.Integer state) {
        this.state = state;
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
     * 获取wmsCategory
     */
	public java.lang.String getWmsCategory(){
		return this.wmsCategory;
	}
 		
	/**
     * 设置wmsCategory
     */
	public void setWmsCategory(java.lang.String wmsCategory){
		this.wmsCategory = wmsCategory;
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
     * 获取updateTime
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置updateTime
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 }