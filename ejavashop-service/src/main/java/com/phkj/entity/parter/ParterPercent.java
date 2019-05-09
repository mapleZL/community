package com.phkj.entity.parter;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>parter_percent</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>������</th><th nowrap>��������</th><th nowrap>�ֶ���</th><th nowrap>�ֶ�����</th><th nowrap>˵��</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>������</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>��ʼʱ��</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>��ֹʱ��</td></tr>
 *   <tr><td>percent</td><td>{@link java.math.BigDecimal}</td><td>percent</td><td>decimal</td><td>Ӷ�����</td></tr>
 *   <tr><td>parterSignId</td><td>{@link java.lang.String}</td><td>parter_sign_id</td><td>varchar</td><td>�ϻ���id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.String}</td><td>id</td><td>varchar</td><td>id</td></tr>
 * </table>
 *
 */
public class ParterPercent implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String type;
 	private java.util.Date startTime;
 	private java.util.Date endTime;
 	private java.math.BigDecimal percent;
 	private java.lang.String parterSignId;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.String percentType;
 	
 	
 	public static final String PERCENT_TYPE_SELF="self";
 	public static final String PERCENT_TYPE_RECOMMOND="recommond";
 	
 	
 	private java.lang.String signNo;
 	private java.lang.String memberRealName;
 	
 		
	/**
     * ��ȡid
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * ����id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * ��ȡ������
     */
	public java.lang.String getType(){
		return this.type;
	}
 		
	/**
     * ����������
     */
	public void setType(java.lang.String type){
		this.type = type;
	}
 		
	/**
     * ��ȡ��ʼʱ��
     */
	public java.util.Date getStartTime(){
		return this.startTime;
	}
 		
	/**
     * ���ÿ�ʼʱ��
     */
	public void setStartTime(java.util.Date startTime){
		this.startTime = startTime;
	}
 		
	/**
     * ��ȡ��ֹʱ��
     */
	public java.util.Date getEndTime(){
		return this.endTime;
	}
 		
	/**
     * ������ֹʱ��
     */
	public void setEndTime(java.util.Date endTime){
		this.endTime = endTime;
	}
 		
	/**
     * ��ȡӶ�����
     */
	public java.math.BigDecimal getPercent(){
		return this.percent;
	}
 		
	/**
     * ����Ӷ�����
     */
	public void setPercent(java.math.BigDecimal percent){
		this.percent = percent;
	}
 		
	/**
     * ��ȡ�ϻ���id
     */
	public java.lang.String getParterSignId(){
		return this.parterSignId;
	}
 		
	/**
     * ���úϻ���id
     */
	public void setParterSignId(java.lang.String parterSignId){
		this.parterSignId = parterSignId;
	}
 		
	/**
     * ��ȡ����ʱ��
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * ���ô���ʱ��
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * ��ȡ����ʱ��
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * ���ø���ʱ��
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}

	public java.lang.String getSignNo() {
		return signNo;
	}

	public void setSignNo(java.lang.String signNo) {
		this.signNo = signNo;
	}

	public java.lang.String getMemberRealName() {
		return memberRealName;
	}

	public void setMemberRealName(java.lang.String memberRealName) {
		this.memberRealName = memberRealName;
	}

	public java.lang.String getPercentType() {
		return percentType;
	}

	public void setPercentType(java.lang.String percentType) {
		this.percentType = percentType;
	}
 		
 }