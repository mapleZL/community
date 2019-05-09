package com.phkj.entity.product;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>order_receipt</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>ordersId</td><td>{@link java.lang.Integer}</td><td>orders_id</td><td>int</td><td>预约入库单号</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>SPU</td><td>{@link java.lang.Integer}</td><td>SPU</td><td>int</td><td>品牌</td></tr>
 *   <tr><td>totalAmount</td><td>{@link java.lang.Integer}</td><td>total_amount</td><td>int</td><td>预计入库总量</td></tr>
 *   <tr><td>dateProbaby</td><td>{@link java.util.Date}</td><td>date_probaby</td><td>datetime</td><td>预计到库时间</td></tr>
 *   <tr><td>createtime</td><td>{@link java.util.Date}</td><td>createtime</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>systemId</td><td>{@link java.lang.Integer}</td><td>system_id</td><td>int</td><td>用户ID</td></tr>
 * </table>
 *
 */
public class OrderReceipt implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
 	private java.lang.String ordersId;
 	private java.lang.Integer sellerId;
 	private java.lang.String SPU;
 	private java.lang.Integer totalAmount;
 	private java.lang.String dateProbaby;
 	private java.util.Date createtime;
 	private java.util.Date updatetime;
 	private java.lang.Integer systemId;
    private java.lang.Integer status;
    private String sellerName;
    private String systemUserName;
    private Integer updateId;
    private String updateName;
    
    //已匹单
    public static final int ORDER_RECEIPT_STATUS = 3;
    
 	public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSystemUserName() {
        return systemUserName;
    }

    public void setSystemUserName(String systemUserName) {
        this.systemUserName = systemUserName;
    }

    public java.util.Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(java.util.Date updatetime) {
        this.updatetime = updatetime;
    }

    public java.lang.Integer getStatus() {
        return status;
    }

    public void setStatus(java.lang.Integer status) {
        this.status = status;
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
     * 获取预约入库单号
     */
	public java.lang.String getOrdersId(){
		return this.ordersId;
	}
 		
	/**
     * 设置预约入库单号
     */
	public void setOrdersId(java.lang.String ordersId){
		this.ordersId = ordersId;
	}
 		
	/**
     * 获取商家ID
     */
	public java.lang.Integer getSellerId(){
		return this.sellerId;
	}
 		
	/**
     * 设置商家ID
     */
	public void setSellerId(java.lang.Integer sellerId){
		this.sellerId = sellerId;
	}
 		
	/**
     * 获取品牌
     */
	public java.lang.String getSPU(){
		return this.SPU;
	}
 		
	/**
     * 设置品牌
     */
	public void setSPU(java.lang.String SPU){
		this.SPU = SPU;
	}
 		
	/**
     * 获取预计入库总量
     */
	public java.lang.Integer getTotalAmount(){
		return this.totalAmount;
	}
 		
	/**
     * 设置预计入库总量
     */
	public void setTotalAmount(java.lang.Integer totalAmount){
		this.totalAmount = totalAmount;
	}
 		
	/**
     * 获取预计到库时间
     */
	public java.lang.String getDateProbaby(){
		return this.dateProbaby;
	}
 		
	/**
     * 设置预计到库时间
     */
	public void setDateProbaby(java.lang.String dateProbaby){
		this.dateProbaby = dateProbaby;
	}
 		
	/**
     * 获取创建时间
     */
	public java.util.Date getCreatetime(){
		return this.createtime;
	}
 		
	/**
     * 设置创建时间
     */
	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}
 		
	/**
     * 获取用户ID
     */
	public java.lang.Integer getSystemId(){
		return this.systemId;
	}
 		
	/**
     * 设置用户ID
     */
	public void setSystemId(java.lang.Integer systemId){
		this.systemId = systemId;
	}
 }