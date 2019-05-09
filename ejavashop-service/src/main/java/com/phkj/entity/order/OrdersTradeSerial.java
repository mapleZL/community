package com.phkj.entity.order;

import java.io.Serializable;

/**
 * 订单交易序列
 * <p>Table: <strong>orders_trade_serial</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>tradeSn</td><td>{@link java.lang.String}</td><td>trade_sn</td><td>varchar</td><td>交易号</td></tr>
 *   <tr><td>relationOrderSn</td><td>{@link java.lang.String}</td><td>relation_order_sn</td><td>varchar</td><td>总订单号</td></tr>
 * </table>
 *
 */
public class OrdersTradeSerial implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 9049224516267664863L;
    private java.lang.String  tradeSn;                                //交易号
    private java.lang.String  relationOrderSn;                        //总订单号

    private int paymentState;
    /**
     * 获取交易号
     */
    public java.lang.String getTradeSn() {
        return this.tradeSn;
    }

    /**
     * 设置交易号
     */
    public void setTradeSn(java.lang.String tradeSn) {
        this.tradeSn = tradeSn;
    }

    /**
     * 获取总订单号
     */
    public java.lang.String getRelationOrderSn() {
        return this.relationOrderSn;
    }

    /**
     * 设置总订单号
     */
    public void setRelationOrderSn(java.lang.String relationOrderSn) {
        this.relationOrderSn = relationOrderSn;
    }

	public int getPaymentState() {
		return paymentState;
	}

	public void setPaymentState(int paymentState) {
		this.paymentState = paymentState;
	}
    
    
}