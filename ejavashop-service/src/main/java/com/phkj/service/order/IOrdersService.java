package com.phkj.service.order;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.dto.OrderDayDto;
import com.phkj.dto.OrderFinanceDayDto;
import com.phkj.entity.member.Member;
import com.phkj.entity.order.Orders;
import com.phkj.entity.order.OrdersAndOrdersProductVO;
import com.phkj.entity.parter.CategorySalesVO;
import com.phkj.entity.parter.SalesDetailsVO;
import com.phkj.entity.parter.SalesPersonVO;
import com.phkj.entity.parter.SumParterSaleVO;
import com.phkj.entity.seller.SellerUser;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.vo.order.BookingSendGoodsVO;
import com.phkj.vo.order.OrderCommitVO;
import com.phkj.vo.order.OrderSuccessVO;
import com.phkj.vo.order.SendingGoodsVO;

/**
 * 订单服务
 *                       
 * @Filename: IOrdersService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IOrdersService {

    /**
     * 根据id取得订单
     * @param  ordersId
     * @return
     */
    ServiceResult<Orders> getOrdersById(Integer ordersId);

    /**
     * 根据orderSn取得订单
     * @param  orderSn
     * @return
     */
    ServiceResult<Orders> getOrdersBySn(String orderSn);

    /**
     * 根据订单ID获取订单号
     * @param id 订单ID
     * @return
     */
    ServiceResult<String> getOrderSnById(Integer ordersId);

    /**
     * 保存订单
     * @param  orders
     * @return
     */
    ServiceResult<Integer> saveOrders(Orders orders);

    /**
    * 更新订单
    * @param  orders
    * @return
    */
    ServiceResult<Integer> updateOrders(Orders orders);

    /**
     * 根据订单ID删除订单
     * @param ordersId
     * @return
     */
    ServiceResult<Boolean> deleteOrder(Integer ordersId);

    /**
     * 根据条件查询订单，PagerInfo传null返回所有符合条件的数据
     * @param queryMap key以q_开头的属性名称，比如q_orderSn
     * @param pager
     * @return
     */
    ServiceResult<List<Orders>> getOrders(Map<String, String> queryMap, PagerInfo pager);
    /**
     * 为导出Excel准备的 add by tongzhaomei
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<OrdersAndOrdersProductVO>> getOrdersAndOrdersProductVO(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<List<Orders>> getordersInStore(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<List<Orders>> getSellerOrdersInStore(Map<String, String> queryMap,
                                                       PagerInfo pager, SellerUser sellerUser);

    /**
     * 更新订单
     * @param orders
     * @param type
     * @param seller
     * @param updateStore 是否更新库存
     * @return
     */
    ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type, SellerUser sellerUser,
                                                boolean updateStore);

    /**
     * 更新订单
     * @param orders
     * @param type
     * @param systemAdmin
     * @param updateStore 是否更新库存
     * @return
     */
    ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type, SystemAdmin systemAdmin,
                                               boolean updateStore);

    /**
     * 商家取消订单，目前只有订单状态为 1、2的可以由商家取消（其中3的只能用户自己取消，不能由商家取消）
     * @param orders
     * @param type
     * @param seller
     * @param updateStore 是否更新库存
     * @return
     */
    ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser);

    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState);

    /**
     * 根据用户ID等条件获取用户订单，Order对象封装了该订单下的网单
     * <br>用户中心订单列表页用
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                         PagerInfo pager);
    

    PagerInfo countOrderWithOrderProduct(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 取消订单 目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     */
    public ServiceResult<Boolean> cancelOrder(String tradeNo, Integer optId, String optName);

    /**
     * 根据订单id 取订单、网单及产品图片信息
     * @param orderId
     * @return
     */
    ServiceResult<Orders> getOrderWithOPById(Integer orderId,String type);

    /**
     * 用户提交订单<br>
     * 1、判断是否使用余额、判断支付密码<br>
     * 2、按商家拆分订单<br>
     * 3、保存网单<br>
     * 4、清除购物车<br>
     * 5、如果使用余额，并且余额足够支付所有订单，修改支付状态、修改库存<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    ServiceResult<OrderSuccessVO> orderCommit(OrderCommitVO orderCommitVO, String ordersType);

    /**
     * 确认收货
     * @param orderId
     * @param request
     * @return
     */
    ServiceResult<Boolean> goodsReceipt(Integer ordersId);

    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap);
    
    
    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    ServiceResult<List<OrderDayDto>> getOrderDay0913Dto(Map<String, String> queryMap);
    
    /**
     * 统计每天收款数据
     * @param queryMap
     * @return
     */
    ServiceResult<List<OrderFinanceDayDto>> getOrderFinanceDayDto(Map<String, String> queryMap);

    /**
     * 系统自动完成订单<br>
     * <li>对已发货状态的订单发货时间超过15个自然日的订单进行自动完成处理
     * @return
     */
    ServiceResult<Boolean> jobSystemFinishOrder();

    /**
     * 系统自动取消24小时没有付款订单<br>
     * @return
     */
    ServiceResult<Boolean> jobSystemCancelOrder();

    /**
     * 根据关联订单 查询订单信息 用于非在线支付 查询几个关联子订单之间的信息
     * @param relationOrderSn
     * @param request
     * @return
     */
    ServiceResult<OrderSuccessVO> getOrdersByRelationOrderSn(String relationOrderSn,
                                                             Integer memberId);

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param relationOrderSn 订单号以逗号隔开
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    ServiceResult<OrderSuccessVO> orderPayBefore(String relationOrderSn, boolean isBalancePay,
                                                 String balancePassword, Member member);

    /**
     * 支付成功之后更改订单的状态
     * @param trade_no 订单
     * @param total_fee 金额
     * @param paycode 支付方式
     * @param payname 支付方式
     * @param tradeSn 交易流水号
     * @param tradeContent 交易返回信息
     * @return
     */
    ServiceResult<Boolean> orderPayAfter(String trade_no, String total_fee, String paycode,
                                         String payname, String tradeSn, String tradeContent);



    /**
     * 用户提交集合竞价订单<br>
     * @param orderCommitVO
     * @return
     */
    ServiceResult<OrderSuccessVO> orderCommitForBidding(OrderCommitVO orderCommitVO);

    /**
     * 保存网单价格
     * @param orderId 
     * @param opinfo
     * @return
     */
    ServiceResult<Boolean> saveOrdersProductPrice(Integer orderId, String opinfo,String submitType);

    /**
     *查询会员地址信息 
     */
    ServiceResult<BookingSendGoodsVO> listUserInfo(Integer orderId);

    /**
     * 查询订单商品信息
     */
    ServiceResult<List<SendingGoodsVO>> listGoodsInfo(Integer orderId);

    ServiceResult<Boolean> saveOrdersProductLabel(Integer orderId, String opinfo);


    /**
     * 第三方仓储发货计算邮费
     * @param addressId
     * @param numstrs
     * @param orderSn 
     * @return
     */
    ServiceResult<BigDecimal> getTransFee(String productIds, String numstrs, String orderSn,
                                          String memberAddressId);

    /**
     * 用户登录时判断用户是否购买过
     * @param memberId
     * @return
     */
    ServiceResult<Integer> getOrdersByMemberId(Integer memberId);
   

    /**
     * 退货保存
     * @param orderId
     * @param opinfo
     * @param integer 
     * @param submitType
     * @return
     */
    ServiceResult<Boolean> saveOrdersReturn(Integer orderId, String opinfo, String returnInfo, Integer integer);
    
    
    ServiceResult<List<SumParterSaleVO>>getSumParterSaleVO(Integer memberId,Integer year,String signNo);
    
    ServiceResult<List<SumParterSaleVO>>getNewSumParterSalesYears(Integer memberId,Integer type,PagerInfo pager,String signNo);
    
    ServiceResult<List<SalesPersonVO>> panerTotalPerson(Integer memberId,String startTime,String endTime,PagerInfo pager,String signNo,String areaId);

    ServiceResult<List<CategorySalesVO>> getCategorySales(Integer memberId,String startTime,String endTime,String signNo);
    
    ServiceResult<List<SalesDetailsVO>> getSalesDetails(Integer memberId,String startTime,String endTime,PagerInfo pager,String signNo,String areaId);
    
    ServiceResult<List<SalesDetailsVO>> getSalesDetailsByOrdersId(Integer ordersId);
    

    ServiceResult<Boolean> sendMessageToMember();

    ServiceResult<String> saveOrdersLeadingXls(File newFile);
    
    ServiceResult<Integer> getSalesDetailsFrontCount(Integer memberId, String startTime, String endTime, String signNo,String areaId);
    
    ServiceResult<List<SalesDetailsVO>> getSalesDetailsFront(Integer memberId, String startTime, String endTime, int start,int size,String signNo,String areaId);
    
    
    ServiceResult<List<SalesPersonVO>>panerTotalPersonFront(Integer memberId, String startTime, String endTime,int start, int size, String signNo, String areaId);
    
    ServiceResult<Integer>panerTotalPersonFrontCount(Integer memberId, String startTime, String endTime, String signNo, String areaId);

    void sendMessageToSeller(Orders orders);

    ServiceResult<Boolean> deleteOrder(String orderId, Integer id, String name);
    
    ServiceResult<List<Orders>> getOrdersByTradeNo (String tradeNo);

    ServiceResult<Boolean> sendMessageToEffectiveCunstomer();
}