package com.phkj.entity.parter;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>parter_sign</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>������</th><th nowrap>��������</th><th nowrap>�ֶ���</th><th nowrap>�ֶ�����</th><th nowrap>˵��</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>�ϻ���id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.String}</td><td>member_id</td><td>varchar</td><td>memberId</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.String}</td><td>status</td><td>varchar</td><td>״̬0�����ã�1������</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>��ֹʱ��</td></tr>
 *   <tr><td>createMan</td><td>{@link java.lang.String}</td><td>create_man</td><td>varchar</td><td>������</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.String}</td><td>id</td><td>varchar</td><td>�ϻ���id</td></tr>
 * </table>
 *
 */
public class ParterSign implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String memberId;
 	private java.lang.String status;
 	private java.util.Date startTime;
 	private java.util.Date endTime;
 	private java.lang.String createMan;
 	private java.util.Date createTime;
 	private java.lang.String memberName;
 	private java.lang.String memberRealName;
 	private java.lang.String parterType;
 	private java.lang.String signNo;
 		
	/**
     * ��ȡ�ϻ���id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * ���úϻ���id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * ��ȡmemberId
     */
	public java.lang.String getMemberId(){
		return this.memberId;
	}
 		
	/**
     * ����memberId
     */
	public void setMemberId(java.lang.String memberId){
		this.memberId = memberId;
	}
 		
	/**
     * ��ȡ״̬0�����ã�1������
     */
	public java.lang.String getStatus(){
		return this.status;
	}
 		
	/**
     * ����״̬0�����ã�1������
     */
	public void setStatus(java.lang.String status){
		this.status = status;
	}
 		
	/**
     * ��ȡ����ʱ��
     */
	public java.util.Date getStartTime(){
		return this.startTime;
	}
 		
	/**
     * ���ÿ���ʱ��
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
     * ��ȡ������
     */
	public java.lang.String getCreateMan(){
		return this.createMan;
	}
 		
	/**
     * ���ô�����
     */
	public void setCreateMan(java.lang.String createMan){
		this.createMan = createMan;
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

	public java.lang.String getMemberName() {
		return memberName;
	}

	public void setMemberName(java.lang.String memberName) {
		this.memberName = memberName;
	}

	public java.lang.String getMemberRealName() {
		return memberRealName;
	}

	public void setMemberRealName(java.lang.String memberRealName) {
		this.memberRealName = memberRealName;
	}

	public java.lang.String getParterType() {
		return parterType;
	}

	public void setParterType(java.lang.String parterType) {
		this.parterType = parterType;
	}

	public java.lang.String getSignNo() {
		return signNo;
	}

	public void setSignNo(java.lang.String signNo) {
		this.signNo = signNo;
	}
 }