package com.ejavashop.entity.wmsinterface;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>bank_card</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>������</th><th nowrap>��������</th><th nowrap>�ֶ���</th><th nowrap>�ֶ�����</th><th nowrap>˵��</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>����</td></tr>
 *   <tr><td>bankName</td><td>{@link java.lang.String}</td><td>bank_name</td><td>varchar</td><td>�������</td></tr>
 *   <tr><td>bankCode</td><td>{@link java.lang.String}</td><td>bank_code</td><td>varchar</td><td>���б���</td></tr>
 *   <tr><td>cardBin</td><td>{@link java.lang.String}</td><td>card_bin</td><td>varchar</td><td>��bin</td></tr>
 * </table>
 *
 */
public class BankCard implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String bankName;
 	private java.lang.String bankCode;
 	private java.lang.String cardBin;
 	
 		
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
     * ��ȡ�������
     */
	public java.lang.String getBankName(){
		return this.bankName;
	}
 		
	/**
     * �����������
     */
	public void setBankName(java.lang.String bankName){
		this.bankName = bankName;
	}
 		
	/**
     * ��ȡ���б���
     */
	public java.lang.String getBankCode(){
		return this.bankCode;
	}
 		
	/**
     * �������б���
     */
	public void setBankCode(java.lang.String bankCode){
		this.bankCode = bankCode;
	}
 		
	/**
     * ��ȡ��bin
     */
	public java.lang.String getCardBin(){
		return this.cardBin;
	}
 		
	/**
     * ���ÿ�bin
     */
	public void setCardBin(java.lang.String cardBin){
		this.cardBin = cardBin;
	}
 }