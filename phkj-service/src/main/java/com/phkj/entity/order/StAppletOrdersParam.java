package com.phkj.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author ：zl
 * @date ：Created in 2019/6/4 11:40
 * @description：订单入参
 * @modified By：
 * @version: 0.0.1$
 */
public class StAppletOrdersParam implements Serializable {

    /**
     *
     seller_id			商家id
     member_id			会员id
     member_name			会员名称
     money_product		商品总金额
     mobile				收货人号码
     name				收货人名称
     address_info		详细地址

     money_price			商品单价
     number				商品数量
     product_id			商品id
     product_name		商品名称
     */

    private Integer sellerId;
    private Integer memberId;
    private String memberName;
    private java.math.BigDecimal moneyProduct;
    private String addressInfo;
    private String name;
    private String mobile;
    private java.math.BigDecimal moneyPrice;
    private Integer productId;
    private String productName;
    private Integer number;
    private String remark;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public BigDecimal getMoneyPrice() {
        return moneyPrice;
    }

    public void setMoneyPrice(BigDecimal moneyPrice) {
        this.moneyPrice = moneyPrice;
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
