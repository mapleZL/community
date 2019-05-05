package com.ejavashop.entity.member;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>personal_tailor_picture</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>personalTailorId</td><td>{@link java.lang.Long}</td><td>personal_tailor_id</td><td>bigint</td><td>私人订制主表ID</td></tr>
 *   <tr><td>tailorDescription</td><td>{@link java.lang.String}</td><td>tailor_description</td><td>varchar</td><td>图片样款</td></tr>
 * </table>
 *
 */
public class PersonalTailorPicture implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.Integer personalTailorId;
 	private java.lang.String tailorDescription;
 	
 		
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
     * 获取私人订制主表ID
     */
	public java.lang.Integer getPersonalTailorId(){
		return this.personalTailorId;
	}
 		
	/**
     * 设置私人订制主表ID
     */
	public void setPersonalTailorId(java.lang.Integer personalTailorId){
		this.personalTailorId = personalTailorId;
	}
 		
	/**
     * 获取图片样款
     */
	public java.lang.String getTailorDescription(){
		return this.tailorDescription;
	}
 		
	/**
     * 设置图片样款
     */
	public void setTailorDescription(java.lang.String tailorDescription){
		this.tailorDescription = tailorDescription;
	}
 }