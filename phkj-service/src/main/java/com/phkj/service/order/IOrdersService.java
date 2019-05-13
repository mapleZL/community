package com.phkj.service.order;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.dto.OrderDayDto;
import com.phkj.dto.OrderFinanceDayDto;
import com.phkj.entity.member.Member;
import com.phkj.entity.order.Orders;
import com.phkj.entity.order.OrdersAndOrdersProductVO;
import com.phkj.entity.seller.SellerUser;
import com.phkj.entity.system.SystemAdmin;
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

}