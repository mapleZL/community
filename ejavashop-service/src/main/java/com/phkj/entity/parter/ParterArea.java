package com.phkj.entity.parter;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>parter_area</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>������</th><th nowrap>��������</th><th nowrap>�ֶ���</th><th nowrap>�ֶ�����</th><th nowrap>˵��</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>����</td></tr>
 *   <tr><td>parterSignId</td><td>{@link java.lang.String}</td><td>parter_sign_id</td><td>varchar</td><td>�ϻ���id</td></tr>
 *   <tr><td>provinceId</td><td>{@link java.lang.String}</td><td>province_id</td><td>varchar</td><td>ʡ</td></tr>
 *   <tr><td>cityId</td><td>{@link java.lang.String}</td><td>city_id</td><td>varchar</td><td>��</td></tr>
 *   <tr><td>areaId</td><td>{@link java.lang.String}</td><td>area_id</td><td>varchar</td><td>��/��</td></tr>
 *   <tr><td>craeteTime</td><td>{@link java.util.Date}</td><td>craete_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>����ʱ��</td></tr>
 *   <tr><td>createMan</td><td>{@link java.lang.String}</td><td>create_man</td><td>varchar</td><td>������</td></tr>
 *   <tr><td>id</td><td>{@link java.lang.String}</td><td>id</td><td>varchar</td><td>����</td></tr>
 * </table>
 *
 */
public class ParterArea implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String parterSignId;
 	private java.lang.String provinceId;
 	private java.lang.String cityId;
 	private java.lang.String areaId;
 	private java.util.Date craeteTime;
 	private java.util.Date updateTime;
 	private java.lang.String createMan;
 	private java.lang.String parterSignName;
 	private java.lang.String regionName;
 	
 	
 		
	/**
     * ��ȡ����
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * ��������
     */
	public void setId(java.lang.Integer id){
		this.id = id;
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
     * ��ȡʡ
     */
	public java.lang.String getProvinceId(){
		return this.provinceId;
	}
 		
	/**
     * ����ʡ
     */
	public void setProvinceId(java.lang.String provinceId){
		this.provinceId = provinceId;
	}
 		
	/**
     * ��ȡ��
     */
	public java.lang.String getCityId(){
		return this.cityId;
	}
 		
	/**
     * ������
     */
	public void setCityId(java.lang.String cityId){
		this.cityId = cityId;
	}
 		
	/**
     * ��ȡ��/��
     */
	public java.lang.String getAreaId(){
		return this.areaId;
	}
 		
	/**
     * ������/��
     */
	public void setAreaId(java.lang.String areaId){
		this.areaId = areaId;
	}
 		
	/**
     * ��ȡ����ʱ��
     */
	public java.util.Date getCraeteTime(){
		return this.craeteTime;
	}
 		
	/**
     * ���ô���ʱ��
     */
	public void setCraeteTime(java.util.Date craeteTime){
		this.craeteTime = craeteTime;
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

	public java.lang.String getParterSignName() {
		return parterSignName;
	}

	public void setParterSignName(java.lang.String parterSignName) {
		this.parterSignName = parterSignName;
	}

	public java.lang.String getRegionName() {
		return regionName;
	}
	public void setRegionName(java.lang.String regionName) {
		this.regionName = regionName;
	}
 }