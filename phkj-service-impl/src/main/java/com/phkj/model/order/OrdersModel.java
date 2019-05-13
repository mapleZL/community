package com.phkj.model.order;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.member.MemberAddressReadDao;
import com.phkj.dao.shop.read.order.OrdersReadDao;
import com.phkj.dao.shop.read.seller.SellerReadDao;
import com.phkj.dao.shop.write.member.MemberWriteDao;
import com.phkj.dao.shop.write.order.OrdersWriteDao;
import com.phkj.dao.shop.write.product.ProductWriteDao;
import com.phkj.dao.shop.write.seller.SellerWriteDao;
import com.phkj.entity.order.Orders;
import com.phkj.model.member.MemberModel;


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
