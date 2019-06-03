package com.phkj.entity.cart;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 商城购物车
 * <p>Table: <strong>st_applet_cart</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>count</td><td>{@link java.lang.Integer}</td><td>count</td><td>int</td><td>数量</td></tr>
 *   <tr><td>specId</td><td>{@link java.lang.String}</td><td>spec_id</td><td>varchar</td><td>规格ID，多个规格用逗号分隔</td></tr>
 *   <tr><td>specInfo</td><td>{@link java.lang.String}</td><td>spec_info</td><td>varchar</td><td>规格详情</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>产品ID</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productGoodsId</td><td>{@link java.lang.Integer}</td><td>product_goods_id</td><td>int</td><td>货品ID</td></tr>
 *   <tr><td>checked</td><td>{@link java.lang.Integer}</td><td>checked</td><td>tinyint</td><td>是否选中0未选中、1选中</td></tr>
 *   <tr><td>productPackageId</td><td>{@link java.lang.Integer}</td><td>product_package_id</td><td>int</td><td>套餐id</td></tr>
 *   <tr><td>isSelfLabel</td><td>{@link java.lang.Integer}</td><td>is_self_label</td><td>tinyint</td><td>是否自供标 1-是 0-否</td></tr>
 * </table>
 *
 */
public class StAppletCart implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.util.Date    createTime;
    private java.lang.Integer memberId;
    private java.lang.Integer count;
    private java.lang.String  specId;
    private java.lang.String  specInfo;
    private java.lang.Integer productId;
    private java.lang.Integer sellerId;
    private java.lang.Integer productGoodsId;
    private java.lang.Integer checked;
    private java.lang.Integer productPackageId;
    private java.lang.Integer isSelfLabel;
    private String            villageCode;
    private BigDecimal        unitPrice;

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取数量
     */
    public java.lang.Integer getCount() {
        return this.count;
    }

    /**
     * 设置数量
     */
    public void setCount(java.lang.Integer count) {
        this.count = count;
    }

    /**
     * 获取规格ID，多个规格用逗号分隔
     */
    public java.lang.String getSpecId() {
        return this.specId;
    }

    /**
     * 设置规格ID，多个规格用逗号分隔
     */
    public void setSpecId(java.lang.String specId) {
        this.specId = specId;
    }

    /**
     * 获取规格详情
     */
    public java.lang.String getSpecInfo() {
        return this.specInfo;
    }

    /**
     * 设置规格详情
     */
    public void setSpecInfo(java.lang.String specInfo) {
        this.specInfo = specInfo;
    }

    /**
     * 获取产品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置产品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取商家ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取货品ID
     */
    public java.lang.Integer getProductGoodsId() {
        return this.productGoodsId;
    }

    /**
     * 设置货品ID
     */
    public void setProductGoodsId(java.lang.Integer productGoodsId) {
        this.productGoodsId = productGoodsId;
    }

    /**
     * 获取是否选中0未选中、1选中
     */
    public java.lang.Integer getChecked() {
        return this.checked;
    }

    /**
     * 设置是否选中0未选中、1选中
     */
    public void setChecked(java.lang.Integer checked) {
        this.checked = checked;
    }

    /**
     * 获取套餐id
     */
    public java.lang.Integer getProductPackageId() {
        return this.productPackageId;
    }

    /**
     * 设置套餐id
     */
    public void setProductPackageId(java.lang.Integer productPackageId) {
        this.productPackageId = productPackageId;
    }

    /**
     * 获取是否自供标 1-是 0-否
     */
    public java.lang.Integer getIsSelfLabel() {
        return this.isSelfLabel;
    }

    /**
     * 设置是否自供标 1-是 0-否
     */
    public void setIsSelfLabel(java.lang.Integer isSelfLabel) {
        this.isSelfLabel = isSelfLabel;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

}