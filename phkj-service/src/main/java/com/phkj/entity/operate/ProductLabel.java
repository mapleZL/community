package com.phkj.entity.operate;

import java.io.Serializable;

/**
 * 标签管理
 * <p>Table: <strong>product_label</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>标签名称</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>所属客户，0是平台标，否则存用户ID</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>状态：0，不显示；1、显示</td></tr>
 *   <tr><td>sku</td><td>{@link java.lang.String}</td><td>sku</td><td>varchar</td><td>sku</td></tr>
 *   <tr><td>norms</td><td>{@link java.lang.String}</td><td>norms</td><td>varchar</td><td>规格</td></tr>
 *   <tr><td>netWeight</td><td>{@link java.lang.String}</td><td>net_weight</td><td>varchar</td><td>净量</td></tr>
 *   <tr><td>grossWeight</td><td>{@link java.lang.String}</td><td>gross_weight</td><td>varchar</td><td>毛重</td></tr>
 *   <tr><td>costPrice</td><td>{@link java.math.BigDecimal}</td><td>cost_price</td><td>decimal</td><td>成本价</td></tr>
 *   <tr><td>marketPrice</td><td>{@link java.math.BigDecimal}</td><td>market_price</td><td>decimal</td><td>市场价</td></tr>
 *   <tr><td>stock</td><td>{@link java.lang.Integer}</td><td>stock</td><td>int</td><td>库存</td></tr>
 *   <tr><td>brand</td><td>{@link java.lang.String}</td><td>brand</td><td>varchar</td><td>品牌</td></tr>
 *   <tr><td>describe</td><td>{@link java.lang.String}</td><td>describe</td><td>text</td><td>说明</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class ProductLabel implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 3841294774784040191L;
    private java.lang.Integer    id;
    private java.lang.String     name;
    private java.lang.Integer    memberId;
    private java.lang.Integer    state;
    private java.lang.String     sku;
    private java.lang.String     norms;
    private java.lang.String     netWeight;
    private java.lang.String     grossWeight;
    private java.math.BigDecimal costPrice;
    private java.math.BigDecimal marketPrice;
    private java.lang.Integer    stock;
    private java.lang.String     brand;
    private java.lang.String     describe;
    private java.lang.String     image;
    private java.lang.Integer    createId;
    private java.lang.String     createName;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;

    //-------------------------custom--------------------------//
    private String               memberName;

    //-------------------------custom--------------------------//

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
     * 获取标签名称
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置标签名称
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取所属客户，0是平台标，否则存用户ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置所属客户，0是平台标，否则存用户ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取状态：0，不显示；1、显示
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态：0，不显示；1、显示
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取sku
     */
    public java.lang.String getSku() {
        return this.sku;
    }

    /**
     * 设置sku
     */
    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }

    /**
     * 获取规格
     */
    public java.lang.String getNorms() {
        return this.norms;
    }

    /**
     * 设置规格
     */
    public void setNorms(java.lang.String norms) {
        this.norms = norms;
    }

    /**
     * 获取净量
     */
    public java.lang.String getNetWeight() {
        return this.netWeight;
    }

    /**
     * 设置净量
     */
    public void setNetWeight(java.lang.String netWeight) {
        this.netWeight = netWeight;
    }

    /**
     * 获取毛重
     */
    public java.lang.String getGrossWeight() {
        return this.grossWeight;
    }

    /**
     * 设置毛重
     */
    public void setGrossWeight(java.lang.String grossWeight) {
        this.grossWeight = grossWeight;
    }

    /**
     * 获取成本价
     */
    public java.math.BigDecimal getCostPrice() {
        return this.costPrice;
    }

    /**
     * 设置成本价
     */
    public void setCostPrice(java.math.BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取市场价
     */
    public java.math.BigDecimal getMarketPrice() {
        return this.marketPrice;
    }

    /**
     * 设置市场价
     */
    public void setMarketPrice(java.math.BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取库存
     */
    public java.lang.Integer getStock() {
        return this.stock;
    }

    /**
     * 设置库存
     */
    public void setStock(java.lang.Integer stock) {
        this.stock = stock;
    }

    /**
     * 获取品牌
     */
    public java.lang.String getBrand() {
        return this.brand;
    }

    /**
     * 设置品牌
     */
    public void setBrand(java.lang.String brand) {
        this.brand = brand;
    }

    /**
     * 获取说明
     */
    public java.lang.String getDescribe() {
        return this.describe;
    }

    /**
     * 设置说明
     */
    public void setDescribe(java.lang.String describe) {
        this.describe = describe;
    }

    /**
     * 获取图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取createId
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置createId
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取createName
     */
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 设置createName
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}