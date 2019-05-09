package com.phkj.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.dto.OrderDayDto;
import com.phkj.dto.OrderFinanceDayDto;
import com.phkj.dto.OrdersReturnDto;
import com.phkj.dto.ProductSaleDto;
import com.phkj.entity.member.Member;
import com.phkj.entity.order.Orders;
import com.phkj.entity.order.OrdersAndOrdersProductVO;
import com.phkj.entity.order.OrdersTradeSerial;
import com.phkj.entity.parter.CategorySalesVO;
import com.phkj.entity.parter.ParterTuijian;
import com.phkj.entity.parter.SalesDetailsVO;
import com.phkj.entity.parter.SalesPersonVO;
import com.phkj.entity.parter.SumParterSaleVO;
import com.phkj.entity.product.Product;
import com.phkj.vo.order.BookingSendGoodsVO;
import com.phkj.vo.order.SendingGoodsVO;

/**
 * 订单表
 * 
 * @Filename: OrdersReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface OrdersReadDao {
	
    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    Integer getOrderNumByMIdAndState(@Param("memberId") Integer memberId,
                                     @Param("orderState") Integer orderState);

    Orders get(@Param("id") java.lang.Integer id);

    /**
     * 根据商家ID及时间获取已经完成的订单
     * @param sellerId 商家ID
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<Orders> getSettleOrders(@Param("sellerId") Integer sellerId,
                                 @Param("startTime") String startTime,
                                 @Param("endTime") String endTime);

    Orders getByOrderSn(String orderSn);
    
    Integer getOrdersCount(@Param("queryMap") Map<String, String> queryMap);
    Integer getOrdersCount1(@Param("queryMap") Map<String, String> queryMap);
    Integer getOrdersCount2(@Param("queryMap") Map<String, String> queryMap);
    
    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    List<OrderDayDto> getOrderDayDto(@Param("queryMap") Map<String, String> queryMap);
    
    /**
     * 统计每天订单量 0913 按订单明细统计商品金额
     * @param queryMap
     * @return
     */
    List<OrderDayDto> getOrderDay0913Dto(@Param("queryMap") Map<String, String> queryMap);
    
    /**
     * 统计每天订单量 0913 按订单明细统计商品金额
     * @param queryMap
     * @return
     */
    public List<OrderFinanceDayDto> getOrderFinanceDayDto(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 获取deliverTime前发货的订单
     * 
     * @param deliverTime
     * @return
     */
    List<Orders> getUnfinishedOrders(@Param("deliverTime") String deliverTime);

    Integer queryCount(Map<String, Object> map);

    List<Orders> queryList(Map<String, Object> map);

    /**
     * 获取deliverTime前发货的订单
     * 
     * @param cancelTime
     * @return
     */
    List<Orders> getUnPaiedOrders(@Param("cancelTime") String cancelTime);

    /**
     * 根据商家ID获取商家的销售总金额
     * @param sellerId
     * @return
     */
    OrderDayDto getSumMoneyOrderBySellerId(Integer sellerId);

    /**
     * 根据商家ID查询订单量
     * @param sellerId
     * @return
     */
    Integer getCountBySellerId(Integer sellerId);

    /**
     * 根据订单号获取商家ID
     * @param orderSn
     */
    Orders getOrderByOrderSn(@Param("orderSn") String orderSn);

    /**
     * 获得用户的订单数量
     * @param memberId
     * @return
     */
    Integer getCountByMemberId(@Param("memberId") Integer memberId);
    

    List<Orders> getOrders2(@Param("queryMap") Map<String, Object> queryMap,
            @Param("start") Integer start, @Param("size") Integer size);
    
    List<Orders> getOrders(@Param("queryMap") Map<String, Object> queryMap,
            @Param("start") Integer start, @Param("size") Integer size);
    
    String getOrderStateComment();
    
    List<ProductSaleDto> getProductSale(Map<String, Object> queryMap);
    
    List<Product> getProductByOrderId(Integer orderid);
    
    BookingSendGoodsVO listUserInfo(Integer orderId);
    
    BookingSendGoodsVO listUserInfoLimit1(Integer orderId);
    
    List<SendingGoodsVO> listGoodsInfo(Integer ordersId);
    
    List<Orders> getordersInStore(@Param("queryMap")Map<String, Object> queryMap, @Param("start")Integer start, @Param("size")Integer size);

    List<Orders>getWaitSendGoodsOrders();
    
    List<Orders>getOmsWaitSendGoodsOrders();
    
    
    List<SumParterSaleVO>getSumParterSales(@Param("memberId") Integer memberId ,@Param("year") Integer year,@Param("signNo")String signNo);
    
    
    List<SumParterSaleVO>getNewSumParterSalesYears(@Param("memberId") Integer memberId,@Param("type") Integer type,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo);
    
    Integer getSalesCount2(@Param("memberId") Integer memberId,@Param("signNo")String signNo);
    
    Integer getSalesPersonCount(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<SalesPersonVO>getSalesPerson(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<CategorySalesVO>getCategorySales(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo);
    
    List<SalesDetailsVO>getSalesDetails(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("start") Integer start,@Param("size") Integer size,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    Integer getSalesDetailsCount(@Param("memberId") Integer memberId,@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("signNo")String signNo,@Param("areaId")String areaId);
    
    List<SalesDetailsVO> getSalesDetailsByOrdersId(@Param("ordersId") Integer ordersId);

    List<Orders> getAllRelationOrdersBgId(Integer id);
	
	
    
    /**
     * 查询关联总订单号
     */
    OrdersTradeSerial getOrderSn(@Param("tradeSn") String tradeSn);
    
    /**
     * 查询所有已经付款的定金订
     * @param id
     * @return
     */
    List<Orders> getActBiddingState5(@Param("actBiddingId") Integer actBiddingId);
    
    List<OrdersReturnDto> goodsReturnRate(Map<String, String> queryMap);
    
    /**
     * 导出excel
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<OrdersAndOrdersProductVO> getOrdersAndOrdersProductVO(@Param("queryMap") Map<String, Object> queryMap,
    		@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据支付编码获取订单列表
     * @param orderSn
     * @return
     */
    List<Orders> getOrdersByTradeNo(@Param("tradeNo") String tradeNo);
    
    
    List<ParterTuijian> getParterTuijianSales(@Param("areaId")String areaId,@Param("listStartTime")String listStartTime,@Param("listEndTime")String listEndTime
    		,@Param("memberId")String memberId,@Param("memberTuijianId")String memberTuijianId,@Param("signNo")String signNo);

    List<Orders> getUnPaiedOrdersLs(@Param("cancelTime")String cancelTime, @Param("limitTime")String limitTime);


    Integer getCountByProductGoodsId(@Param("memberId")Integer memberId, @Param("productGoodsId")Integer productGoodsId, @Param("beginTime")String beginTime, @Param("endTime")String endTime, @Param("tFlag")Integer tFlag);

    List<Member> getLastLoginMemberList(@Param("lastLoginTime") String lastLoginTime);
    
    
}
