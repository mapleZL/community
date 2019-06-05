package com.phkj.entity.seller;

import java.io.Serializable;

/**
 * 商家表
 * <p>Table: <strong>st_applet_seller</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>sellerName</td><td>{@link java.lang.String}</td><td>seller_name</td><td>varchar</td><td>店铺名称</td></tr>
 *   <tr><td>sellerLogo</td><td>{@link java.lang.String}</td><td>seller_logo</td><td>varchar</td><td>店铺logo</td></tr>
 *   <tr><td>sellerGrade</td><td>{@link java.lang.Integer}</td><td>seller_grade</td><td>int</td><td>店铺等级</td></tr>
 *   <tr><td>scoreService</td><td>{@link java.lang.String}</td><td>score_service</td><td>varchar</td><td>店铺评分服务</td></tr>
 *   <tr><td>scoreDeliverGoods</td><td>{@link java.lang.String}</td><td>score_deliver_goods</td><td>varchar</td><td>店铺评分发货</td></tr>
 *   <tr><td>scoreDescription</td><td>{@link java.lang.String}</td><td>score_description</td><td>varchar</td><td>店铺评分描述</td></tr>
 *   <tr><td>productNumber</td><td>{@link java.lang.Integer}</td><td>product_number</td><td>int</td><td>商品数量</td></tr>
 *   <tr><td>collectionNumber</td><td>{@link java.lang.Integer}</td><td>collection_number</td><td>int</td><td>店铺收藏</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>saleMoney</td><td>{@link java.math.BigDecimal}</td><td>sale_money</td><td>decimal</td><td>店铺总销售金额</td></tr>
 *   <tr><td>orderCount</td><td>{@link java.lang.Integer}</td><td>order_count</td><td>int</td><td>店铺总订单量</td></tr>
 *   <tr><td>orderCountOver</td><td>{@link java.lang.Integer}</td><td>order_count_over</td><td>int</td><td>店铺完成订单量</td></tr>
 *   <tr><td>sellerKeyword</td><td>{@link java.lang.String}</td><td>seller_keyword</td><td>varchar</td><td>SEO关键字</td></tr>
 *   <tr><td>sellerDes</td><td>{@link java.lang.String}</td><td>seller_des</td><td>varchar</td><td>SEO店铺描述</td></tr>
 *   <tr><td>auditStatus</td><td>{@link java.lang.Integer}</td><td>audit_status</td><td>tinyint</td><td>审核状态 1、待审核；2、审核通过；3、冻结</td></tr>
 *   <tr><td>storeSlide</td><td>{@link java.lang.String}</td><td>store_slide</td><td>text</td><td>店铺轮播图 </td></tr>
 *   <tr><td>sellerCode</td><td>{@link java.lang.String}</td><td>seller_code</td><td>varchar</td><td>商家编码</td></tr>
 *   <tr><td>taxLicense</td><td>{@link java.lang.String}</td><td>tax_license</td><td>varchar</td><td>税务登记证</td></tr>
 *   <tr><td>organization</td><td>{@link java.lang.String}</td><td>organization</td><td>varchar</td><td>组织机构代码证</td></tr>
 *   <tr><td>bussinessLicense</td><td>{@link java.lang.String}</td><td>bussiness_license</td><td>varchar</td><td>营业执照</td></tr>
 * </table>
 *
 */
public class StAppletSeller implements Serializable {

    private java.lang.Integer    id;
    private java.lang.Integer    memberId;
    private java.lang.String     name;
    private java.lang.String     sellerName;
    private java.lang.String     sellerLogo;
    private java.lang.Integer    sellerGrade;
    private java.lang.String     scoreService;
    private java.lang.String     scoreDeliverGoods;
    private java.lang.String     scoreDescription;
    private java.lang.Integer    productNumber;
    private java.lang.Integer    collectionNumber;
    private java.util.Date       createTime;
    private java.math.BigDecimal saleMoney;
    private java.lang.Integer    orderCount;
    private java.lang.Integer    orderCountOver;
    private java.lang.String     sellerKeyword;
    private java.lang.String     sellerDes;
    private java.lang.Integer    auditStatus;
    private java.lang.String     storeSlide;
    private java.lang.String     sellerCode;
    private java.lang.String     taxLicense;
    private java.lang.String     organization;
    private java.lang.String     bussinessLicense;

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
     * 获取用户ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取用户名
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置用户名
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取店铺名称
     */
    public java.lang.String getSellerName() {
        return this.sellerName;
    }

    /**
     * 设置店铺名称
     */
    public void setSellerName(java.lang.String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 获取店铺logo
     */
    public java.lang.String getSellerLogo() {
        return this.sellerLogo;
    }

    /**
     * 设置店铺logo
     */
    public void setSellerLogo(java.lang.String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    /**
     * 获取店铺等级
     */
    public java.lang.Integer getSellerGrade() {
        return this.sellerGrade;
    }

    /**
     * 设置店铺等级
     */
    public void setSellerGrade(java.lang.Integer sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    /**
     * 获取店铺评分服务
     */
    public java.lang.String getScoreService() {
        return this.scoreService;
    }

    /**
     * 设置店铺评分服务
     */
    public void setScoreService(java.lang.String scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * 获取店铺评分发货
     */
    public java.lang.String getScoreDeliverGoods() {
        return this.scoreDeliverGoods;
    }

    /**
     * 设置店铺评分发货
     */
    public void setScoreDeliverGoods(java.lang.String scoreDeliverGoods) {
        this.scoreDeliverGoods = scoreDeliverGoods;
    }

    /**
     * 获取店铺评分描述
     */
    public java.lang.String getScoreDescription() {
        return this.scoreDescription;
    }

    /**
     * 设置店铺评分描述
     */
    public void setScoreDescription(java.lang.String scoreDescription) {
        this.scoreDescription = scoreDescription;
    }

    /**
     * 获取商品数量
     */
    public java.lang.Integer getProductNumber() {
        return this.productNumber;
    }

    /**
     * 设置商品数量
     */
    public void setProductNumber(java.lang.Integer productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 获取店铺收藏
     */
    public java.lang.Integer getCollectionNumber() {
        return this.collectionNumber;
    }

    /**
     * 设置店铺收藏
     */
    public void setCollectionNumber(java.lang.Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
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
     * 获取店铺总销售金额
     */
    public java.math.BigDecimal getSaleMoney() {
        return this.saleMoney;
    }

    /**
     * 设置店铺总销售金额
     */
    public void setSaleMoney(java.math.BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    /**
     * 获取店铺总订单量
     */
    public java.lang.Integer getOrderCount() {
        return this.orderCount;
    }

    /**
     * 设置店铺总订单量
     */
    public void setOrderCount(java.lang.Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 获取店铺完成订单量
     */
    public java.lang.Integer getOrderCountOver() {
        return this.orderCountOver;
    }

    /**
     * 设置店铺完成订单量
     */
    public void setOrderCountOver(java.lang.Integer orderCountOver) {
        this.orderCountOver = orderCountOver;
    }

    /**
     * 获取SEO关键字
     */
    public java.lang.String getSellerKeyword() {
        return this.sellerKeyword;
    }

    /**
     * 设置SEO关键字
     */
    public void setSellerKeyword(java.lang.String sellerKeyword) {
        this.sellerKeyword = sellerKeyword;
    }

    /**
     * 获取SEO店铺描述
     */
    public java.lang.String getSellerDes() {
        return this.sellerDes;
    }

    /**
     * 设置SEO店铺描述
     */
    public void setSellerDes(java.lang.String sellerDes) {
        this.sellerDes = sellerDes;
    }

    /**
     * 获取审核状态 1、待审核；2、审核通过；3、冻结
     */
    public java.lang.Integer getAuditStatus() {
        return this.auditStatus;
    }

    /**
     * 设置审核状态 1、待审核；2、审核通过；3、冻结
     */
    public void setAuditStatus(java.lang.Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取店铺轮播图 
     */
    public java.lang.String getStoreSlide() {
        return this.storeSlide;
    }

    /**
     * 设置店铺轮播图 
     */
    public void setStoreSlide(java.lang.String storeSlide) {
        this.storeSlide = storeSlide;
    }

    /**
     * 获取商家编码
     */
    public java.lang.String getSellerCode() {
        return this.sellerCode;
    }

    /**
     * 设置商家编码
     */
    public void setSellerCode(java.lang.String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * 获取税务登记证
     */
    public java.lang.String getTaxLicense() {
        return this.taxLicense;
    }

    /**
     * 设置税务登记证
     */
    public void setTaxLicense(java.lang.String taxLicense) {
        this.taxLicense = taxLicense;
    }

    /**
     * 获取组织机构代码证
     */
    public java.lang.String getOrganization() {
        return this.organization;
    }

    /**
     * 设置组织机构代码证
     */
    public void setOrganization(java.lang.String organization) {
        this.organization = organization;
    }

    /**
     * 获取营业执照
     */
    public java.lang.String getBussinessLicense() {
        return this.bussinessLicense;
    }

    /**
     * 设置营业执照
     */
    public void setBussinessLicense(java.lang.String bussinessLicense) {
        this.bussinessLicense = bussinessLicense;
    }
}