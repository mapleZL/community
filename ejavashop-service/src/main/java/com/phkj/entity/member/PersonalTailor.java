package com.phkj.entity.member;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>personal_tailor</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>联系人姓名</td></tr>
 *   <tr><td>mobile</td><td>{@link java.lang.String}</td><td>mobile</td><td>varchar</td><td>联系电话</td></tr>
 *   <tr><td> qq</td><td>{@link java.lang.String}</td><td> qq</td><td>varchar</td><td>QQ</td></tr>
 *   <tr><td>weixin</td><td>{@link java.lang.String}</td><td>weixin</td><td>varchar</td><td>微信</td></tr>
 * </table>
 *
 */
public class PersonalTailor implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer       id;
 	private java.lang.String        name;
 	private java.lang.String        mobile;
 	private java.lang.String        qq;
 	private java.lang.String        weixin;
 	private java.lang.String        description;
 	private java.lang.Integer       memberId;
 	private java.util.Date          createTime;
 	private java.util.Date          updateTime;
 	
 	//------------------------------------------------------
 	private java.lang.String        tailorDescription;
 	private java.lang.String        memberName;
 		
	public java.lang.String getTailorDescription() {
        return tailorDescription;
    }

    public void setTailorDescription(java.lang.String tailorDescription) {
        this.tailorDescription = tailorDescription;
    }

    public java.lang.String getMemberName() {
        return memberName;
    }

    public void setMemberName(java.lang.String memberName) {
        this.memberName = memberName;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public java.util.Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public java.lang.Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
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
     * 获取联系人姓名
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置联系人姓名
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取联系电话
     */
	public java.lang.String getMobile(){
		return this.mobile;
	}
 		
	/**
     * 设置联系电话
     */
	public void setMobile(java.lang.String mobile){
		this.mobile = mobile;
	}
 		
	/**
     * 获取QQ
     */
	public java.lang.String getQq(){
		return this.qq;
	}
 		
	/**
     * 设置QQ
     */
	public void setQq(java.lang.String  qq){
		this.qq =  qq;
	}
 		
	/**
     * 获取微信
     */
	public java.lang.String getWeixin(){
		return this.weixin;
	}
 		
	/**
     * 设置微信
     */
	public void setWeixin(java.lang.String weixin){
		this.weixin = weixin;
	}
 }