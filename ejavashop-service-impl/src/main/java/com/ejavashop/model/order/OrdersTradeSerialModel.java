package com.ejavashop.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.order.OrdersTradeSerialReadDao;
import com.ejavashop.dao.shop.write.order.OrdersTradeSerialWriteDao;
import com.ejavashop.entity.order.OrdersTradeSerial;

@Component
public class OrdersTradeSerialModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(OrdersTradeSerialModel.class);

    @Resource
    private OrdersTradeSerialWriteDao      ordersTradeSerialWriteDao;
    @Resource
    private OrdersTradeSerialReadDao      ordersTradeSerialReadDao;
    @Resource
    private DataSourceTransactionManager   transactionManager;

    /**
     * 根据id取得订单交易序列
     * @param  ordersTradeSerialId
     * @return
     */
    public OrdersTradeSerial getOrdersTradeSerialById(String ordersTradeSerialId) {
        return ordersTradeSerialReadDao.get(ordersTradeSerialId);
    }

    /**
     * 保存订单交易序列
     * @param  ordersTradeSerial
     * @return
     */
    public Integer saveOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial) {
        this.dbConstrains(ordersTradeSerial);
        return ordersTradeSerialWriteDao.save(ordersTradeSerial);
    }

    /**
    * 更新订单交易序列
    * @param  ordersTradeSerial
    * @return
    */
    public Integer updateOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial) {
        this.dbConstrains(ordersTradeSerial);
        return ordersTradeSerialWriteDao.update(ordersTradeSerial);
    }

    private void dbConstrains(OrdersTradeSerial ordersTradeSerial) {
        ordersTradeSerial
            .setTradeSn(StringUtil.dbSafeString(ordersTradeSerial.getTradeSn(), false, 32));
        ordersTradeSerial.setRelationOrderSn(
            StringUtil.dbSafeString(ordersTradeSerial.getRelationOrderSn(), false, 2000));
    }

    public List<OrdersTradeSerial> page(Map<String, String> queryMap,
                                        PagerInfo pager) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(ordersTradeSerialReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<OrdersTradeSerial> list = ordersTradeSerialReadDao.page(param);
        return list;
    }

    public Integer del(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id == null)
                throw new BusinessException("删除订单交易序列[" + id + "]时出错");
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("[OrdersTradeSerialModel][del] exception:" + e.getMessage());
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
        return ordersTradeSerialWriteDao.del(id);
    }

    public OrdersTradeSerial getByRelationOrderSn(String relationOrderSn) {
        return ordersTradeSerialReadDao.getByRelationOrderSn(relationOrderSn);
    }

    /**
     * 去掉时间戳和余额标志后匹配数据库
     * @param orderPaySn
     * @return
     */
    public OrdersTradeSerial existsByOrders(String orderPaySn) {
        //Terry
        List<OrdersTradeSerial> otslist = ordersTradeSerialReadDao.existsByOrdersSn(orderPaySn);
        log.debug("orderPaySn==" + orderPaySn);
        for (OrdersTradeSerial ots : otslist) {
            String relationsn = ots.getRelationOrderSn();

            int idx = relationsn.indexOf(EjavashopConfig.ORDER_SEPARATOR) + EjavashopConfig.ORDER_SEPARATOR.length() + 1;
            String sn2 = relationsn.substring(idx);
            //下单时订单号按商家id排序，保证顺序正确，再做精确匹配
            log.debug("sn2:" + sn2 + ",orderPaySn:" + orderPaySn);
            if (!StringUtil.isEmpty(sn2) && sn2.equals(orderPaySn))
                return ots;
        }
        return null;
    }
}