package com.phkj.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/6/4 11:40
 * @description：订单入参
 * @modified By：
 * @version: 0.0.1$
 */
public class StAppletOrderBO implements Serializable {

    private List<StAppletOrdersParam> orders;

    public List<StAppletOrdersParam> getOrders() {
        return orders;
    }

    public void setOrders(List<StAppletOrdersParam> orders) {
        this.orders = orders;
    }
}
