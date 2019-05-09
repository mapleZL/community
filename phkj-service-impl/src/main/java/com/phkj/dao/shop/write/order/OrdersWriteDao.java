package com.phkj.dao.shop.write.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.order.Orders;

@Repository
public interface OrdersWriteDao {

    Orders get(Integer id);

    //Orders getByOrderSn(String orderSn);

    Integer insert(Orders orders);

    Integer update(Orders orders);

    Integer delete(Integer id);

    /*Integer getOrdersCount(@Param("queryMap") Map<String, String> queryMap);
    Integer getOrdersCount1(@Param("queryMap") Map<String, String> queryMap);
    Integer getOrdersCount2(@Param("queryMap") Map<String, String> queryMap);*/

   /* List<Orders> getOrders(@Param("queryMap") Map<String, Object> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);*/
    /**
     * 导出excel
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    /*List<OrdersAndOrdersProductVO> getOrdersAndOrdersProductVO(@Param("queryMap") Map<String, Object> queryMap,
    		@Param("start") Integer start, @Param("size") Integer size);*/

    Integer updateMoneyBack(@Param("id") Integer id, @Param("moneyBack") String moneyBack);

    /*List<OrdersReturnDto> goodsReturnRate(Map<String, String> queryMap);*/

//    String getOrderStateComment();

    //List<ProductSaleDto> getProductSale(Map<String, Object> queryMap);

  //  List<Product> getProductByOrderId(Integer orderid);
    
   /* BookingSendGoodsVO listUserInfo(Integer orderId);
    
    
    BookingSendGoodsVO listUserInfoLimit1(Integer orderId);*/

    
   // List<SendingGoodsVO> listGoodsInfo(Integer ordersId);
    
    //List<Orders> getordersInStore(@Param("queryMap")Map<String, Object> queryMap, @Param("start")Integer start, @Param("size")Integer size);
    
    //List<Orders>getWaitSendGoodsOrders();
    
    //List<Orders>getOmsWaitSendGoodsOrders();
    
    /**
     * 关闭所有没有付款的定金订单 
     * @param id
     * @return
     */
    Integer updateCloseActBidding(@Param("actBiddingId") Integer actBiddingId);

    /**
     * 查询所有已经付款的定金订
     * @param id
     * @return
     */
   // List<Orders> getActBiddingState5(@Param("actBiddingId") Integer actBiddingId);

    /**
     * 查询所有关联订单
     * @param tradeSn
     * @return
     */
    List<Orders> getOrderList(@Param("relationOrderSn") String relationOrderSn);
    /**
     * 查询关联总订单号
     */
   // OrdersTradeSerial getOrderSn(@Param("tradeSn") String tradeSn);
    
    /**
     * 根据订单号获取订单重量
     * @param orderSn
     * @return
     */
   /* Double getWeightByOrdersSn(@Param("orderSn") String orderSn);
    
    
    List<SumParterSaleVO>getSumParterSales(@Param("memberId") Integer memberId ,@Param("year") Integer year,@Param("signNo")String signNo);
    
    
    List<SumParterSaleVO>getNewSumParterSalesYears(@Param("memberId") Integer memberId,@Param("type") Integer type,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo);
    
    Integer getSalesCount2(@Param("memberId") Integer memberId,@Param("signNo")String signNo);
    
    Integer getSalesPersonCount(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<SalesPersonVO>getSalesPerson(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<CategorySalesVO>getCategorySales(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo);
    
    List<SalesDetailsVO>getSalesDetails(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    Integer getSalesDetailsCount(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<SalesDetailsVO> getSalesDetailsByOrdersId(@Param("ordersId") Integer ordersId);

    List<Orders> getAllRelationOrdersBgId(Integer id);*/
	
	/**
     * 根据支付编码获取订单列表
     * @param orderSn
     * @return
     */
    List<Orders> getOrdersByTradeNo(@Param("tradeNo") String tradeNo);

    double getWeightAddLabelNum(@Param("orderSn")String orderSn);

    double getWeightAddLabelNumTotal(@Param("tradeNo")String tradeNo);
    
    /**
     * 根据订单号获取订单重量
     * @param orderSn
     * @return
     */
    Double getWeightByOrdersSn(@Param("orderSn") String orderSn);
    
}
