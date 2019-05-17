package com.phkj.entity.product;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>product_sku_other</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Long}</td><td>id</td><td>bigint</td><td>id</td></tr>
 *   <tr><td>productGoodsSku</td><td>{@link java.lang.String}</td><td>product_goods_sku</td><td>varchar</td><td>productGoodsSku</td></tr>
 *   <tr><td>skuOther</td><td>{@link java.lang.String}</td><td>sku_other</td><td>varchar</td><td>skuOther</td></tr>
 *   <tr><td>createtime</td><td>{@link java.util.Date}</td><td>createtime</td><td>datetime</td><td>createtime</td></tr>
 * </table>
 *
 */
public class ProductSkuOther implements Serializable {
 
 	/**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Long id;
 	private java.lang.Integer productId;
 	private java.lang.String skuOther;
 	private java.util.Date createtime;
 	
 		
	/**
     * 获取id
     */
	public java.lang.Long getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Long id){
		this.id = id;
	}

    public java.lang.Integer getProductId() {
        return productId;
    }

    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取skuOther
     */
	public java.lang.String getSkuOther(){
		return this.skuOther;
	}
 		
	/**
     * 设置skuOther
     */
	public void setSkuOther(java.lang.String skuOther){
		this.skuOther = skuOther;
	}
 		
	/**
     * 获取createtime
     */
	public java.util.Date getCreatetime(){
		return this.createtime;
	}
 		
	/**
     * 设置createtime
     */
	public void setCreatetime(java.util.Date createtime){
		this.createtime = createtime;
	}
 }