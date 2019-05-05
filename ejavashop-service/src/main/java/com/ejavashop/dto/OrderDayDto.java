package com.ejavashop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单量统计DTO
 *                       
 * @Filename: OrderDayDto.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class OrderDayDto implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4464930279201326931L;

    private String            orderDay;
    private BigDecimal        moneyProduct;
    private BigDecimal        moneyLogistics;
    private BigDecimal        moneyOrder;
    private BigDecimal        moneyPaidBalance;
    private BigDecimal        moneyPaidReality;
    private BigDecimal        moneyBack;
    private BigDecimal        moneyDiscount;
    private BigDecimal        servicePrice     = BigDecimal.ZERO;
    private BigDecimal        labelPrice       = BigDecimal.ZERO;

    private Integer           count;

    public String getOrderDay() {
        return orderDay;
    }

    public void setOrderDay(String orderDay) {
        this.orderDay = orderDay;
    }

    public BigDecimal getMoneyProduct() {
        return moneyProduct;
    }

    public void setMoneyProduct(BigDecimal moneyProduct) {
        this.moneyProduct = moneyProduct;
    }

    public BigDecimal getMoneyLogistics() {
        return moneyLogistics;
    }

    public void setMoneyLogistics(BigDecimal moneyLogistics) {
        this.moneyLogistics = moneyLogistics;
    }

    public BigDecimal getMoneyOrder() {
        return moneyOrder;
    }

    public void setMoneyOrder(BigDecimal moneyOrder) {
        this.moneyOrder = moneyOrder;
    }

    public BigDecimal getMoneyPaidBalance() {
        return moneyPaidBalance;
    }

    public void setMoneyPaidBalance(BigDecimal moneyPaidBalance) {
        this.moneyPaidBalance = moneyPaidBalance;
    }

    public BigDecimal getMoneyPaidReality() {
        return moneyPaidReality;
    }

    public void setMoneyPaidReality(BigDecimal moneyPaidReality) {
        this.moneyPaidReality = moneyPaidReality;
    }

    public BigDecimal getMoneyBack() {
        return moneyBack;
    }

    public void setMoneyBack(BigDecimal moneyBack) {
        this.moneyBack = moneyBack;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getMoneyDiscount() {
        return moneyDiscount;
    }

    public void setMoneyDiscount(BigDecimal moneyDiscount) {
        this.moneyDiscount = moneyDiscount;
    }

    public BigDecimal getServicePrice() {
        return servicePrice;
    }

    public void setServicePrice(BigDecimal servicePrice) {
        this.servicePrice = servicePrice;
    }

    public BigDecimal getLabelPrice() {
        return labelPrice;
    }

    public void setLabelPrice(BigDecimal labelPrice) {
        this.labelPrice = labelPrice;
    }

}
