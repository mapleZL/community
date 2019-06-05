package com.phkj.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：zl
 * @date ：Created in 2019/6/5 14:55
 * @description：订单列表返回值
 * @modified By：
 * @version: 0.0.1$
 */
public class StAppletOrdersVO implements Serializable {

    // 订单编号
    private String orderSn;
    // 商家id
    private Integer sellerId;
    // 商家名称
    private String sellerName;
    // 商家logo
    private String sellerLogo;
    // 会员id
    private Integer memberId;
    // 会员名称
    private String memberName;
    // 订单状态
    private Integer orderState;
    // 订单总金额,商品数量*商品单价
    private java.math.BigDecimal moneyProduct;
    // 详细地址
    private String addressInfo;
    // 商品图片
    private String productSku;
    // 商品详情
    private String specInfo;
    // 该订单下共几件商品
    private Integer productNum;
    // 商品单价
    private java.math.BigDecimal moneyPrice;
    // 商品数量
    private Integer number;
    // 是否删除 0、已删除 1、未删除
    private Integer deleted;
    // 商品id
    private Integer productId;
    // 商品名称
    private String productName;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public BigDecimal getMoneyProduct() {
        return moneyProduct;
    }

    public void setMoneyProduct(BigDecimal moneyProduct) {
        this.moneyProduct = moneyProduct;
    }

    public String getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public BigDecimal getMoneyPrice() {
        return moneyPrice;
    }

    public void setMoneyPrice(BigDecimal moneyPrice) {
        this.moneyPrice = moneyPrice;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
