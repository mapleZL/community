package com.ejavashop.entity.shopm.pcindex;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>pc_title_keywords_description</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>path</td><td>{@link java.lang.String}</td><td>path</td><td>varchar</td><td>path</td></tr>
 *   <tr><td>title</td><td>{@link java.lang.String}</td><td>title</td><td>varchar</td><td>title</td></tr>
 *   <tr><td>keywords</td><td>{@link java.lang.String}</td><td>keywords</td><td>varchar</td><td>keywords</td></tr>
 *   <tr><td>description</td><td>{@link java.lang.String}</td><td>description</td><td>varchar</td><td>description</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 *   <tr><td>applyPage</td><td>{@link java.lang.String}</td><td>apply_page</td><td>varchar</td><td>applyPage</td></tr>
 * </table>
 *
 */
public class PcTitleKeywordsDescription implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.String path;
 	private java.lang.String title;
 	private java.lang.String keywords;
 	private java.lang.String description;
 	private java.lang.Integer createId;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.String applyPage;
 	
 	public static String TITLE_DEFAULT = "大袜网-做袜子生意 就上大袜网";                  //默认SEO title
 	
 	public static String KEYWORDS_DEFAULT = "大袜网,袜子品牌,袜子厂家,袜子批发,袜子加盟";    //默认SEO keywords
 	
 	public static String DESCRIPTION_DEFAULT = "大袜网是全品类袜子采购、袜子批发、袜子加工的一站式服务平台，做袜子生意，就上大袜网。";  ////默认SEO description
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
     * 获取path
     */
	public java.lang.String getPath(){
		return this.path;
	}
 		
	/**
     * 设置path
     */
	public void setPath(java.lang.String path){
		this.path = path;
	}
 		
	/**
     * 获取title
     */
	public java.lang.String getTitle(){
		return this.title;
	}
 		
	/**
     * 设置title
     */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
 		
	/**
     * 获取keywords
     */
	public java.lang.String getKeywords(){
		return this.keywords;
	}
 		
	/**
     * 设置keywords
     */
	public void setKeywords(java.lang.String keywords){
		this.keywords = keywords;
	}
 		
	/**
     * 获取description
     */
	public java.lang.String getDescription(){
		return this.description;
	}
 		
	/**
     * 设置description
     */
	public void setDescription(java.lang.String description){
		this.description = description;
	}
 		
	/**
     * 获取createId
     */
	public java.lang.Integer getCreateId(){
		return this.createId;
	}
 		
	/**
     * 设置createId
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
 		
	/**
     * 获取applyPage
     */
	public java.lang.String getApplyPage(){
		return this.applyPage;
	}
 		
	/**
     * 设置applyPage
     */
	public void setApplyPage(java.lang.String applyPage){
		this.applyPage = applyPage;
	}
 }