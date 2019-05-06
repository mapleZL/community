package com.ejavashop.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberAddressReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersAndOrdersProductVO;
import com.ejavashop.model.member.MemberModel;


@Component(value = "ordersModel")
public class OrdersModel {
    private static Logger                   log = LogManager.getLogger(OrdersModel.class);
    

    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private OrdersReadDao                   ordersReadDao;
    @Resource
    private ProductWriteDao                 productWriteDao;
    @Resource
    private SellerReadDao                   sellerReadDao;
    @Resource
    private SellerWriteDao                  sellerWriteDao;
    @Resource
    private OrdersReadDao                   orderReadDao;
    @Resource
    private MemberWriteDao                  memberWriteDao;
    @Resource
    private MemberAddressReadDao           memberAddressReadDao;
    @Resource
    private MemberModel                     memberModel;
    
    /**
     * 根据id取得订单
     * @param  ordersId
     * @return
     */
    public Orders getOrdersById(Integer ordersId) {
        return ordersReadDao.get(ordersId);
    }


    /**
     * 根据订单ID获取订单号
     * @param id
     * @return
     */
    public String getOrderSnById(Integer id) {
        if (id == null) {
            throw new BusinessException("id不能为null");
        }
        Orders orders = ordersReadDao.get(id);
        if (orders.getOrderSn() == null) {
            throw new BusinessException("没有该订单:" + orders.getId());
        }
        return orders.getOrderSn();
    }

    /**
     * 保存订单
     * @param  orders
     * @return
     */
    public Integer saveOrders(Orders orders) {
        return ordersWriteDao.insert(orders);
    }

    /**
    * 更新订单
    * @param  orders
    * @return
    */
    public Integer updateOrders(Orders orders) {
        return ordersWriteDao.update(orders);
    }

    /**
     * 根据订单ID删除订单
     * @param ordersId
     * @return
     */
    public boolean deleteOrder(Integer ordersId) {
        if (ordersId == null)
            throw new BusinessException("删除订单[" + ordersId + "]时出错");
        return ordersWriteDao.delete(ordersId) > 0;
    }

    public Integer getOrdersCount(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount(queryMap);
    }

    public Integer getOrdersCount2(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount2(queryMap);
    }

    public Integer getOrdersCount1(Map<String, String> queryMap) {
        return ordersReadDao.getOrdersCount1(queryMap);
    }

    public List<Orders> getOrders(Map<String, String> queryMap, Integer start,
                                  Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getOrders(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 导出excel准备的
     * @param queryMap
     * @param start
     * @param size
     * @return
     * @throws Exception
     */
    public List<OrdersAndOrdersProductVO> getOrdersAndOrdersProductVO(Map<String, String> queryMap,
                                                                      Integer start,
                                                                      Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<OrdersAndOrdersProductVO> list = ordersReadDao.getOrdersAndOrdersProductVO(parammap,
            start, size);
        for (OrdersAndOrdersProductVO o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    public List<Orders> getordersInStore(Map<String, String> queryMap, Integer start,
                                         Integer size) throws Exception {
        Map<String, Object> parammap = new HashMap<String, Object>(queryMap);
        List<Orders> list = ordersReadDao.getordersInStore(parammap, start, size);
        for (Orders o : list) {
            o.setSellerName(sellerReadDao.get(o.getSellerId()).getSellerName());
        }
        return list;
    }

    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    public Integer getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        return ordersReadDao.getOrderNumByMIdAndState(memberId, orderState);
    }

}
