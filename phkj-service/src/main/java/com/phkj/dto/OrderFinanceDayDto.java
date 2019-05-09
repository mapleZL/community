package com.phkj.dto;

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
public class OrderFinanceDayDto implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4464930279201326931L;

    private String            orderDay;
    private BigDecimal        moneyPaid;
    private String        	  paymentName;
    
	public String getOrderDay() {
		return orderDay;
	}
	public void setOrderDay(String orderDay) {
		this.orderDay = orderDay;
	}
	public BigDecimal getMoneyPaid() {
		return moneyPaid;
	}
	public void setMoneyPaid(BigDecimal moneyPaid) {
		this.moneyPaid = moneyPaid;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}

    

}
