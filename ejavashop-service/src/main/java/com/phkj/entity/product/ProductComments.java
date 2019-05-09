package com.phkj.entity.product;

import java.io.Serializable;

public class ProductComments implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1112347703968929528L;

    /** 商品评论状态：1、评价 */
    public static final int   STATE_1          = 1;

    /** 商品评论状态：2、审核通过，前台显示 */
    public static final int   STATE_2          = 2;

    /** 商品评论状态：3、删除 */
    public static final int   STATE_3          = 3;

    private Integer           id;
    private Integer           userId;
    private String            userName;
    private Integer           grade;
    private String            content;
    private String            sellerAttitude;
    private java.util.Date    createTime;
    private Integer           productId;
    private Integer           productGoodsId;                         // 货品ID
    private Integer           sellerId;
    private String            orderSn;
    private Integer           replyId;
    private String            replyName;
    private String            replyContent;
    private Integer           state;
    private Integer           adminId;
    private Integer           description;
    private Integer           serviceAttitude;
    private Integer           productSpeed;
    private Integer           logisticsSpeed;
    private Integer           expressAttitude;

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String            productName;                            // 产品名称
    private String            productLeadLittle;                      // 产品主图 小图
    private String            type;                                   // 评价类型 1、全部 2、好评 3、中评 4、差评
    private String            sellerName;                             // 商家名称
    private Integer           paramProductId;                         // 产品ID 用于分页传值
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

    /**
     * 获取id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取评价人ID
     */
    public Integer getUserId() {
        return this.userId;
    }

    /**
     * 设置评价人ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取评价人账号
     */
    public String getUserName() {
        return this.userName;
    }

    /**
     * 设置评价人账号
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取评分(1到5)
     */
    public Integer getGrade() {
        return this.grade;
    }

    /**
     * 设置评分(1到5)
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取评价内容
     */
    public String getContent() {
        return this.content;
    }

    /**
     * 设置评价内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评价商家服务
     */
    public String getSellerAttitude() {
        return this.sellerAttitude;
    }

    /**
     * 设置评价商家服务
     */
    public void setSellerAttitude(String sellerAttitude) {
        this.sellerAttitude = sellerAttitude;
    }

    /**
     * 获取评价时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置评价时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取评价商品
     */
    public Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置评价商品
     */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取所属商家
     */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置所属商家
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取订单编号
     */
    public String getOrderSn() {
        return this.orderSn;
    }

    /**
     * 设置订单编号
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    /**
     * 获取回复人ID
     */
    public Integer getReplyId() {
        return this.replyId;
    }

    /**
     * 设置回复人ID
     */
    public void setReplyId(Integer replyId) {
        this.replyId = replyId;
    }

    /**
     * 获取回复内容
     */
    public String getReplyName() {
        return this.replyName;
    }

    /**
     * 设置回复内容
     */
    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    /**
     * 获取1、评价；2、审核通过，前台显示；3、删除
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、评价；2、审核通过，前台显示；3、删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取审核上架人
     */
    public Integer getAdminId() {
        return this.adminId;
    }

    /**
     * 设置审核上架人
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取描述相符(1到5星)
     */
    public Integer getDescription() {
        return this.description;
    }

    /**
     * 设置描述相符(1到5星)
     */
    public void setDescription(Integer description) {
        this.description = description;
    }

    /**
     * 获取服务态度(1到5星)
     */
    public Integer getServiceAttitude() {
        return this.serviceAttitude;
    }

    /**
     * 设置服务态度(1到5星)
     */
    public void setServiceAttitude(Integer serviceAttitude) {
        this.serviceAttitude = serviceAttitude;
    }

    /**
     * 获取发货速度(1到5星)
     */
    public Integer getProductSpeed() {
        return this.productSpeed;
    }

    /**
     * 设置发货速度(1到5星)
     */
    public void setProductSpeed(Integer productSpeed) {
        this.productSpeed = productSpeed;
    }

    /**
     * 获取物流态度（1到5星）
     */
    public Integer getLogisticsSpeed() {
        return this.logisticsSpeed;
    }

    /**
     * 设置物流态度（1到5星）
     */
    public void setLogisticsSpeed(Integer logisticsSpeed) {
        this.logisticsSpeed = logisticsSpeed;
    }

    /**
     * 获取快递员态度(1到5星)
     */
    public Integer getExpressAttitude() {
        return this.expressAttitude;
    }

    /**
     * 设置快递员态度(1到5星)
     */
    public void setExpressAttitude(Integer expressAttitude) {
        this.expressAttitude = expressAttitude;
    }

    /**
     * 获取货品ID
     */
    public Integer getProductGoodsId() {
        return this.productGoodsId;
    }

    /**
     * 设置货品ID
     */
    public void setProductGoodsId(Integer productGoodsId) {
        this.productGoodsId = productGoodsId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductLeadLittle() {
        return productLeadLittle;
    }

    public void setProductLeadLittle(String productLeadLittle) {
        this.productLeadLittle = productLeadLittle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Integer getParamProductId() {
        return paramProductId;
    }

    public void setParamProductId(Integer paramProductId) {
        this.paramProductId = paramProductId;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}