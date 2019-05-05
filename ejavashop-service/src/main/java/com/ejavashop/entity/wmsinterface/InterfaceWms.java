package com.ejavashop.entity.wmsinterface;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>interface_wms</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>relationId</td><td>{@link java.lang.Integer}</td><td>relation_id</td><td>int</td><td>关联id</td></tr>
 *   <tr><td>ralationTable</td><td>{@link java.lang.String}</td><td>ralation_table</td><td>varchar</td><td>关联表名</td></tr>
 *   <tr><td>sendNo</td><td>{@link java.lang.Integer}</td><td>send_no</td><td>int</td><td>发送次数</td></tr>
 *   <tr><td>sendResult</td><td>{@link java.lang.String}</td><td>send_result</td><td>varchar</td><td>发送结果</td></tr>
 *   <tr><td>syncType</td><td>{@link java.lang.String}</td><td>sync_type</td><td>varchar</td><td>同步类型</td></tr>
 *   <tr><td>syncTime</td><td>{@link java.util.Date}</td><td>sync_time</td><td>datetime</td><td>同步时间</td></tr>
 * </table>
 *
 */
public class InterfaceWms implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.String relationId;
 	private java.lang.String ralationTable;
 	private java.lang.Integer sendNo;
 	private java.lang.String sendResult;
 	private java.lang.String syncType;
 	private java.util.Date syncTime;
 	private java.lang.String errorMsg;
 	
 		
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
     * 获取关联id
     */
	public java.lang.String getRelationId(){
		return this.relationId;
	}
 		
	/**
     * 设置关联id
     */
	public void setRelationId(java.lang.String relationId){
		this.relationId = relationId;
	}
 		
	/**
     * 获取关联表名
     */
	public java.lang.String getRalationTable(){
		return this.ralationTable;
	}
 		
	/**
     * 设置关联表名
     */
	public void setRalationTable(java.lang.String ralationTable){
		this.ralationTable = ralationTable;
	}
 		
	/**
     * 获取发送次数
     */
	public java.lang.Integer getSendNo(){
		return this.sendNo;
	}
 		
	/**
     * 设置发送次数
     */
	public void setSendNo(java.lang.Integer sendNo){
		this.sendNo = sendNo;
	}
 		
	/**
     * 获取发送结果
     */
	public java.lang.String getSendResult(){
		return this.sendResult;
	}
 		
	/**
     * 设置发送结果
     */
	public void setSendResult(java.lang.String sendResult){
		this.sendResult = sendResult;
	}
 		
	/**
     * 获取同步类型
     */
	public java.lang.String getSyncType(){
		return this.syncType;
	}
 		
	/**
     * 设置同步类型
     */
	public void setSyncType(java.lang.String syncType){
		this.syncType = syncType;
	}
 		
	/**
     * 获取同步时间
     */
	public java.util.Date getSyncTime(){
		return this.syncTime;
	}
 		
	/**
     * 设置同步时间
     */
	public void setSyncTime(java.util.Date syncTime){
		this.syncTime = syncTime;
	}

	public java.lang.String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(java.lang.String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
 }