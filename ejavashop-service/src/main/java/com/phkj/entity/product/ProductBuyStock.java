package com.phkj.entity.product;

import java.io.Serializable;

/**
 * 
 * <p>Table: <strong>product_buy_stock</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>goodsId</td><td>{@link java.lang.Integer}</td><td>goods_id</td><td>int</td><td>货品ID</td></tr>
 *   <tr><td>sku</td><td>{@link java.lang.String}</td><td>sku</td><td>varchar</td><td>sku</td></tr>
 *   <tr><td>stock</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>库存少于此数值开始启用限制</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>0，停用；1、使用</td></tr>
 *   <tr><td>grade1</td><td>{@link java.math.BigDecimal}</td><td>grade1</td><td>decimal</td><td>注册会员可以购买的比例（0到1之间）</td></tr>
 *   <tr><td>grade2</td><td>{@link java.math.BigDecimal}</td><td>grade2</td><td>decimal</td><td>铜牌会员可以购买的比例（0到1之间）</td></tr>
 *   <tr><td>grade3</td><td>{@link java.math.BigDecimal}</td><td>grade3</td><td>decimal</td><td>银牌会员可以购买的比例（0到1之间）</td></tr>
 *   <tr><td>grade4</td><td>{@link java.math.BigDecimal}</td><td>grade4</td><td>decimal</td><td>金牌会员可以购买的比例（0到1之间）</td></tr>
 *   <tr><td>grade5</td><td>{@link java.math.BigDecimal}</td><td>grade5</td><td>decimal</td><td>钻石会员可以购买的比例（0到1之间）</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateId</td><td>{@link java.lang.Integer}</td><td>update_id</td><td>int</td><td>updateId</td></tr>
 *   <tr><td>updateName</td><td>{@link java.lang.String}</td><td>update_name</td><td>varchar</td><td>updateName</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class ProductBuyStock implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = -4079916443693882158L;
    private java.lang.Integer    id;
    private java.lang.Integer    productId;
    private java.lang.Integer    goodsId;
    private java.lang.String     sku;
    private java.lang.Integer    stock;
    private java.lang.Integer    state;
    private java.math.BigDecimal grade1;
    private java.math.BigDecimal grade2;
    private java.math.BigDecimal grade3;
    private java.math.BigDecimal grade4;
    private java.math.BigDecimal grade5;
    private java.lang.Integer    createId;
    private java.lang.String     createName;
    private java.util.Date       createTime;
    private java.lang.Integer    updateId;
    private java.lang.String     updateName;
    private java.util.Date       updateTime;

    private String               productName;                             //商品名称
    private String               normName;                                //规格值
    private Integer              productStock;                            //sku库存

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
     * 获取商品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置商品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取货品ID
     */
    public java.lang.Integer getGoodsId() {
        return this.goodsId;
    }

    /**
     * 设置货品ID
     */
    public void setGoodsId(java.lang.Integer goodsId) {
        this.goodsId = goodsId;
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
     * 获取0，停用；1、使用
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置0，停用；1、使用
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取注册会员可以购买的比例（0到1之间）
     */
    public java.math.BigDecimal getGrade1() {
        return this.grade1;
    }

    /**
     * 设置注册会员可以购买的比例（0到1之间）
     */
    public void setGrade1(java.math.BigDecimal grade1) {
        this.grade1 = grade1;
    }

    /**
     * 获取铜牌会员可以购买的比例（0到1之间）
     */
    public java.math.BigDecimal getGrade2() {
        return this.grade2;
    }

    /**
     * 设置铜牌会员可以购买的比例（0到1之间）
     */
    public void setGrade2(java.math.BigDecimal grade2) {
        this.grade2 = grade2;
    }

    /**
     * 获取银牌会员可以购买的比例（0到1之间）
     */
    public java.math.BigDecimal getGrade3() {
        return this.grade3;
    }

    /**
     * 设置银牌会员可以购买的比例（0到1之间）
     */
    public void setGrade3(java.math.BigDecimal grade3) {
        this.grade3 = grade3;
    }

    /**
     * 获取金牌会员可以购买的比例（0到1之间）
     */
    public java.math.BigDecimal getGrade4() {
        return this.grade4;
    }

    /**
     * 设置金牌会员可以购买的比例（0到1之间）
     */
    public void setGrade4(java.math.BigDecimal grade4) {
        this.grade4 = grade4;
    }

    /**
     * 获取钻石会员可以购买的比例（0到1之间）
     */
    public java.math.BigDecimal getGrade5() {
        return this.grade5;
    }

    /**
     * 设置钻石会员可以购买的比例（0到1之间）
     */
    public void setGrade5(java.math.BigDecimal grade5) {
        this.grade5 = grade5;
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
     * 获取updateId
     */
    public java.lang.Integer getUpdateId() {
        return this.updateId;
    }

    /**
     * 设置updateId
     */
    public void setUpdateId(java.lang.Integer updateId) {
        this.updateId = updateId;
    }

    /**
     * 获取updateName
     */
    public java.lang.String getUpdateName() {
        return this.updateName;
    }

    /**
     * 设置updateName
     */
    public void setUpdateName(java.lang.String updateName) {
        this.updateName = updateName;
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

    public java.lang.Integer getStock() {
        return stock;
    }

    public void setStock(java.lang.Integer stock) {
        this.stock = stock;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getNormName() {
        return normName;
    }

    public void setNormName(String normName) {
        this.normName = normName;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

}