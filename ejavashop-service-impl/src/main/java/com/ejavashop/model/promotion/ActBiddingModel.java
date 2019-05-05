package com.ejavashop.model.promotion;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.bidding.ActBiddingPriceReadDao;
import com.ejavashop.dao.promotion.read.bidding.ActBiddingReadDao;
import com.ejavashop.dao.promotion.read.bidding.ActBiddingTypeReadDao;
import com.ejavashop.dao.promotion.write.bidding.ActBiddingPriceWriteDao;
import com.ejavashop.dao.promotion.write.bidding.ActBiddingTypeWriteDao;
import com.ejavashop.dao.promotion.write.bidding.ActBiddingWriteDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;

@Component(value = "actBiddingModel")
public class ActBiddingModel {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ActBiddingModel.class);

    @Resource
    private ActBiddingTypeWriteDao         actBiddingTypeWriteDao;

    @Resource
    private ActBiddingTypeReadDao          actBiddingTypeReadDao;

    @Resource
    private ActBiddingWriteDao             actBiddingWriteDao;

    @Resource
    private ActBiddingPriceReadDao         actBiddingPriceReadDao;

    @Resource
    private ActBiddingReadDao              actBiddingReadDao;

    @Resource
    private ActBiddingPriceWriteDao        actBiddingPriceWriteDao;

    @Resource
    private OrdersWriteDao                 ordersWriteDao;
    @Resource
    private OrdersReadDao                 ordersReadDao;

    @Resource
    private OrdersProductReadDao          ordersProductReadDao;
    @Resource
    private OrdersProductWriteDao          ordersProductWriteDao;

    @Resource
    private DataSourceTransactionManager   promotionTransactionManager;

    /**
     * 根据id取得团购分类
     * @param  actBiddingTypeId
     * @return
     */
    public ActBiddingType getActBiddingTypeById(Integer actBiddingTypeId) {
        return actBiddingTypeReadDao.get(actBiddingTypeId);
    }

    /**
     * 保存团购分类
     * @param  actBiddingType
     * @return
     */
    public Integer saveActBiddingType(ActBiddingType actBiddingType) {
        this.dbActBiddingTypeConstrains(actBiddingType);
        return actBiddingTypeWriteDao.insert(actBiddingType);
    }

    /**
    * 更新团购分类
    * @param  actBiddingType
    * @return
    */
    public Integer updateActBiddingType(ActBiddingType actBiddingType) {
        this.dbActBiddingTypeConstrains(actBiddingType);
        return actBiddingTypeWriteDao.update(actBiddingType);
    }

    private void dbActBiddingTypeConstrains(ActBiddingType actBiddingType) {
        actBiddingType.setName(StringUtil.dbSafeString(actBiddingType.getName(), false, 20));
        actBiddingType.setCreateName(StringUtil.dbSafeString(actBiddingType.getCreateName(), true,
            50));
        actBiddingType.setUpdateName(StringUtil.dbSafeString(actBiddingType.getUpdateName(), true,
            50));
    }

    /**
     * 分页查询团购分类
     * @param queryMap
     * @param pager
     * @return
     */
    public List<ActBiddingType> getActBiddingTypes(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(actBiddingTypeReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        return actBiddingTypeReadDao.getActBiddingTypes(queryMap, start, size);
    }

    /**
     * 删除团购分类
     * @param id
     * @return
     */
    public Boolean delActBiddingType(Integer id) {
        int count = actBiddingWriteDao.countByType(id);
        if (count > 0) {
            throw new BusinessException("此分类下面有商品，不能进行删除操作。");
        }
        return actBiddingTypeWriteDao.del(id) > 0;
    }

    /**
     * 团购分类的启用
     * @param id
     * @return
     */
    public Boolean auditYesActBiddingType(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        boolean mark = false;
        try {
            ActBiddingType actBiddingType = actBiddingTypeReadDao.get(id);
            if (actBiddingType.getState().intValue() != ActBiddingType.ACTGROUPTYPE_STATE_0) {
                throw new BusinessException("状态只有停用才能启用");
            }
            mark = actBiddingTypeWriteDao.audit(id, ActBiddingType.ACTGROUPTYPE_STATE_1) > 0;

            actBiddingWriteDao.updateByTypeState(id, ActBidding.TYPE_STATE_1);

            promotionTransactionManager.commit(status);
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
        return mark;
    }

    /**
     * 团购分类的停用
     * @param id
     * @return
     */
    public Boolean auditNoActBiddingType(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);
        boolean mark = false;
        try {
            ActBiddingType actBiddingType = actBiddingTypeReadDao.get(id);
            if (actBiddingType.getState().intValue() != ActBiddingType.ACTGROUPTYPE_STATE_1) {
                throw new BusinessException("状态只有停用才能启用");
            }
            mark = actBiddingTypeWriteDao.audit(id, ActBiddingType.ACTGROUPTYPE_STATE_0) > 0;

            actBiddingWriteDao.updateByTypeState(id, ActBidding.TYPE_STATE_0);

            promotionTransactionManager.commit(status);
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
        return mark;
    }

    /**
     * 查询所有可用的团购分类
     * @return
     */
    public List<ActBiddingType> getActBiddingTypesAll() {
        return actBiddingTypeReadDao.getAll();
    }

    /**
     * 查询所有可用的团购分类
     * @return
     */
    public List<ActBiddingType> getActBiddingTypesFront() {
        return actBiddingTypeReadDao.getAll();
    }

    /////////////////////////////////////
    /**
     * 根据id取得集合竞价
     * @param  actBiddingId
     * @return
     */
    public ActBidding getActBiddingById(Integer actBiddingId) {
        return actBiddingReadDao.get(actBiddingId);
    }

    /**
     * 保存集合竞价
     * @param  actBidding
     * @return
     */
    public Integer saveActBidding(ActBidding actBidding) {
        this.dbConstrains(actBidding);
        return actBiddingWriteDao.insert(actBidding);
    }

    /**
    * 更新集合竞价
    * @param  actBidding
    * @return
    */
    public Integer updateActBidding(ActBidding actBidding) {
        this.dbConstrains(actBidding);
        return actBiddingWriteDao.update(actBidding);
    }

    private void dbConstrains(ActBidding actBidding) {
        actBidding.setName(StringUtil.dbSafeString(actBidding.getName(), false, 65535));
        actBidding.setDescinfo(StringUtil.dbSafeString(actBidding.getDescinfo(), false, 65535));
        actBidding.setAuditName(StringUtil.dbSafeString(actBidding.getAuditName(), false, 50));
        actBidding.setReason(StringUtil.dbSafeString(actBidding.getReason(), false, 200));
    }

    /**
     * 统计集合竞价
     * @param queryMap
     * @return
     */
    public int getActBiddingsCount(Map<String, String> queryMap) {
        return actBiddingReadDao.getActBiddingsCount(queryMap);
    }

    /**
     * 分页查询集合竞价
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<ActBidding> getActBiddings(Map<String, String> queryMap, Integer start, Integer size) {
        return actBiddingReadDao.getActBiddings(queryMap, start, size);
    }

    /**
     * 保存集合竞价和阶梯价格 
     * @param actBidding
     * @return
     */
    public Boolean saveActBiddingAndPrice(ActBidding actBidding,
                                          List<ActBiddingPrice> actBiddingPrices) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);

        try {
            actBiddingWriteDao.insert(actBidding);

            if (actBiddingPrices != null && actBiddingPrices.size() > 0) {
                for (ActBiddingPrice actBiddingPrice : actBiddingPrices) {
                    actBiddingPrice.setActBiddingId(actBidding.getId());
                    actBiddingPriceWriteDao.insert(actBiddingPrice);
                }
            }

            promotionTransactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 更新集合竞价ID取得阶梯价格
     * @param id
     * @return
     */
    public List<ActBiddingPrice> getActBiddingByIds(Integer id) {
        return actBiddingPriceReadDao.getActBiddingByIds(id);
    }

    public Boolean updateActBiddingAndPrice(ActBidding actBidding,
                                            List<ActBiddingPrice> actBiddingPrices) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = promotionTransactionManager.getTransaction(def);

        try {
            actBiddingWriteDao.update(actBidding);

            actBiddingPriceWriteDao.delActBiddingByIds(actBidding.getId());

            if (actBiddingPrices != null && actBiddingPrices.size() > 0) {
                for (ActBiddingPrice actBiddingPrice : actBiddingPrices) {
                    actBiddingPrice.setActBiddingId(actBidding.getId());
                    actBiddingPriceWriteDao.insert(actBiddingPrice);
                }
            }

            promotionTransactionManager.commit(status);
            return true;
        } catch (BusinessException be) {
            promotionTransactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            promotionTransactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 更新集合竞价状态
     * @param id
     * @param state
     * @return
     */
    public Boolean updateActBiddingState(Integer id, int state) {
        return actBiddingWriteDao.updateState(id, state) > 0;
    }

    /**
     * 更新集合竞价发布状态
     * @param id
     * @param actState
     * @return
     */
    public Boolean updateActBiddingActState(Integer id, int actState) {
        return actBiddingWriteDao.updateActState(id, actState) > 0;
    }

    /**
     * 集合竞价定时器，结束活动，生成尾款订单 TODO 最后添加定时器
     * @return
     */
    public Boolean jobBiddingService() {
        System.out.println("-------定时器执行了--------");
        //第一步，关闭过期的集合竞价
        List<ActBidding> actBiddings = actBiddingReadDao.getAllEnd();
        for (ActBidding actBidding : actBiddings) {
            try { //批量处理，一个出现异常其他不需要改变
                actBiddingWriteDao.updateActState(actBidding.getId(), ActBidding.ACTIVITY_STATE_3);
                System.out.println("----关闭集合竞价---" + actBidding.getId());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[ActBiddingModel][jobBiddingService]关闭集合竞价出现异常，请手动检查，集合竞价ID为--"
                          + actBidding.getId() + "--异常信息：" + e.getMessage());
            }
        }

        //第二步，查询所有关闭集合竞价并且没有生成尾款订单的集合竞价
        List<ActBidding> actBiddingsExecuteStateNo = actBiddingReadDao.getExecuteStateNo();
        for (ActBidding actBidding : actBiddingsExecuteStateNo) {
            try {//批量处理，异常吃掉
                actBiddingWriteDao.updateExecuteState(actBidding.getId(),
                    ActBidding.EXECUTE_STATE_1);

                List<ActBiddingPrice> actBiddingPrices = actBiddingPriceReadDao
                    .getActBiddingByIds(actBidding.getId());
                BigDecimal price = getPriceNow(actBidding, actBiddingPrices).subtract(
                    actBidding.getFirstPrice()); //计算单个集合竞价的尾款金额

                //关闭所有没有付款的定金订单 
                ordersWriteDao.updateCloseActBidding(actBidding.getId());

                //查询所有已经付款的定金订单
                List<Orders> orderss = ordersReadDao.getActBiddingState5(actBidding.getId());
                for (Orders orders : orderss) { //生成尾款订单
                    System.out.println("----订单ID-----" + orders.getId());

                    List<OrdersProduct> ordersProducts = ordersProductReadDao.getByOrderId(orders
                        .getId());
                    OrdersProduct ordersProduct = ordersProducts.get(0);//集合竞价订单和网单是一对一的关系

                    //结算尾款的价格
                    BigDecimal moneyOrder = price
                        .multiply(new BigDecimal(ordersProduct.getNumber()));

                    String orderSnNow = RandomUtil.getOrderSn();
                    String orderSn = orders.getOrderSn();
                    orders.setRelationOrderSn(orderSnNow);
                    ordersWriteDao.update(orders);//更新定金订单，关联尾款订单号

                    //生成尾款订单
                    orders.setOrderType(Orders.ORDER_TYPE_10);
                    orders.setRelationOrderSn(orderSn);
                    orders.setOrderSn(orderSnNow);
                    orders.setOrderState(Orders.ORDER_STATE_1);
                    orders.setPayTime(null);
                    orders.setPaymentStatus(Orders.PAYMENT_STATUS_0);
                    orders.setMoneyProduct(moneyOrder);
                    orders.setMoneyOrder(moneyOrder);
                    orders.setMoneyLogistics(new BigDecimal(0));
                    orders.setMoneyPaidBalance(new BigDecimal(0));
                    orders.setMoneyPaidReality(new BigDecimal(0));
                    orders.setMoneyCoupon(new BigDecimal(0));
                    orders.setMoneyActFull(new BigDecimal(0));
                    orders.setMoneyDiscount(new BigDecimal(0));
                    orders.setMoneyBack(new BigDecimal(0));
                    orders.setMoneyIntegral(new BigDecimal(0));
                    orders.setIntegral(0);
                    orders.setPaymentName("在线支付");
                    orders.setPaymentCode(Orders.PAYMENT_CODE_ONLINE);
                    orders.setFinishTime(null);
                    orders.setTradeSn("");
                    orders.setId(null);
                    ordersWriteDao.insert(orders);

                    //处理网单
                    ordersProduct.setOrdersId(orders.getId());
                    ordersProduct.setOrdersSn(orders.getOrderSn());
                    ordersProduct.setMoneyPrice(price);
                    ordersProduct.setMoneyAmount(moneyOrder);
                    ordersProduct.setId(null);
                    ordersProductWriteDao.insert(ordersProduct);

                    //TODO 此处可以加代码给用户通知，比如短信，或者邮件，不通知可以不用加
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("[ActBiddingModel][jobBiddingService]集合竞价生成尾款订单出现异常，请手动检查，集合竞价ID为--"
                          + actBidding.getId() + "--异常信息：" + e.getMessage());
            }
        }

        return true;
    }

    /**
     * 获取阶梯竞价的价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     */
    private BigDecimal getPriceNow(ActBidding actBidding, List<ActBiddingPrice> actBiddingPrices) {
        BigDecimal priceNow = null;
        if (actBiddingPrices == null || actBiddingPrices.size() == 0) {
            return actBidding.getPrice();
        }
        int number = actBidding.getVirtualSaleNum().intValue() + actBidding.getSaleNum().intValue();
        if (actBiddingPrices.get(0).getSaleNum().intValue() > number) {
            return actBidding.getPrice();
        }
        int count = actBiddingPrices.size();
        if (actBiddingPrices.get(count - 1).getSaleNum() <= number) {
            return actBiddingPrices.get(count - 1).getPrice();
        }

        for (ActBiddingPrice actBiddingPrice : actBiddingPrices) {
            if (actBiddingPrice.getSaleNum().intValue() <= number) {
                priceNow = actBiddingPrice.getPrice();
            }
        }
        return priceNow;
    }

    public int getActBiddingsFrontCount(Map<String, String> queryMap) {
        return actBiddingReadDao.getActBiddingsFrontCount(queryMap);
    }

    public List<ActBidding> getActBiddingsFront(Map<String, String> queryMap, Integer start,
                                                Integer size) {
        return actBiddingReadDao.getActBiddingsFront(queryMap, start, size);
    }

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    public Boolean updateActBiddingStateAndAuditOpinion(Integer id, int state, String auditOpinion) {
        return actBiddingWriteDao.updateStateAndAuditOpinion(id, state, auditOpinion);
    }

    /**
     * 取得该团购所属分类下的前5个商品
     * @param type
     * @param number
     * @return
     */
    public List<ActBidding> getActBiddingsByType(int type, int number) {
        return actBiddingReadDao.getActBiddingsByType(type, number);
    }

}
