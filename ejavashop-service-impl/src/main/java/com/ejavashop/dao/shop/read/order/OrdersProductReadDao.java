package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.dto.ProductDayDto;
import com.ejavashop.entity.order.OrdersProduct;

/**
 * 网单管理
 *                       
 * @Filename: OrdersProductReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface OrdersProductReadDao {

    OrdersProduct get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<OrdersProduct> queryList(Map<String, Object> map);
    /**
     * 根据订单ID和商家ID查询网单
     * @param ordersId 订单ID
     * @param sellerId 商家ID
     * @return
     */
   List<OrdersProduct> getByOrderIdAndSellerId(@Param("ordersId") Integer ordersId,
                                                @Param("sellerId") Integer sellerId);

    /**
     * 根据订单ID获取网单
     * @param ordersId
     * @return
     */
    List<OrdersProduct> getByOrderId(@Param("ordersId") Integer ordersId);

    /**
     * 统计商品每天的销量
     * @param queryMap
     * @return
     */
    List<ProductDayDto> getProductDayDto(@Param("queryMap") Map<String, String> queryMap);


    /**
     * 根据订单ID获取网单
     * @param ordersId
     * @return
     */
    List<OrdersProduct> getByOrderIds(@Param("ordersId") String ordersId);

    List<OrdersProduct> getOrdersProductByOrderSn(@Param("ordersSn") String ordersSn);

    Integer getCountBgSnAndSku(@Param("productId")Integer productId, @Param("ordersSn")String ordersSn);

//    Integer getOrdersNumByOrdersSn(@Param("ordersSn")String ordersSn);
    
    Integer getCount(Map<String, Object> queryMap);
    
    List<OrdersProduct> page(Map<String, Object> queryMap);
    
    List<OrdersProduct>getWaitedStock();
    
}
