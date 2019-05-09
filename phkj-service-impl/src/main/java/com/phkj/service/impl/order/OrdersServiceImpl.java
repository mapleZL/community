package com.phkj.service.impl.order;

import java.io.File;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
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
import com.phkj.model.order.OrdersModel;
import com.phkj.model.product.ProductModel;
import com.phkj.service.order.IOrdersService;
import com.phkj.vo.order.BookingSendGoodsVO;
import com.phkj.vo.order.OrderCommitVO;
import com.phkj.vo.order.OrderSuccessVO;
import com.phkj.vo.order.SendingGoodsVO;

@Service(value = "ordersService")
public class OrdersServiceImpl implements IOrdersService {
    private static Logger log = LogManager.getLogger(OrdersServiceImpl.class);

    @Resource
    private OrdersModel   ordersModel;
    
    @Resource
    private ProductModel   productModel;
    
    @Override
    public ServiceResult<Orders> getOrdersById(Integer ordersId) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrdersById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrdersBySn(String orderSn) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setSuccess(true);
        } catch (BusinessException e) {
        	serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> getOrderSnById(Integer ordersId) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
            serviceResult.setResult(ordersModel.getOrderSnById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveOrders(Orders orders) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.saveOrders(orders));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][saveOrders]保存订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][saveOrders] param:" + JSON.toJSONString(orders));
            log.error("[OrdersService][saveOrders]保存订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    /**
    * 更新订单
    * @param  orders
    * @return
    */
    @Override
    public ServiceResult<Integer> updateOrders(Orders orders) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.updateOrders(orders));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrders]修改订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
        	//oms 回传出库单 需要 记录错误日志
        	serviceResult.setSuccess(false);
        	serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrders] param:" + JSON.toJSONString(orders));
            log.error("[OrdersService][updateOrders]修改订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteOrder(Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.deleteOrder(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getOrders(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Orders> list = ordersModel.getOrders(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrders] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }
    /**
     * 导出excel 
     */
    @Override
    public ServiceResult<List<OrdersAndOrdersProductVO>> getOrdersAndOrdersProductVO(Map<String, String> queryMap, PagerInfo pager) {
    	ServiceResult<List<OrdersAndOrdersProductVO>> serviceResult = new ServiceResult<List<OrdersAndOrdersProductVO>>();
    	serviceResult.setPager(pager);
    	try {
    		Integer start = 0, size = 0;
    		if (pager != null) {
    			pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
    			start = pager.getStart();
    			size = pager.getPageSize();
    		}
    		List<OrdersAndOrdersProductVO> list = ordersModel.getOrdersAndOrdersProductVO(queryMap, start, size);
    		serviceResult.setResult(list);
    	} catch (BusinessException e) {
    		serviceResult.setSuccess(false);
    		serviceResult.setMessage(e.getMessage());
    		log.error("[OrdersService][getOrders]根据条件取得订单表时出现异常：" + e.getMessage());
    	} catch (Exception e) {
    		serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
    		log.error("[OrdersService][getOrders] param1:" + JSON.toJSONString(queryMap)
    				+ " &param2:" + JSON.toJSONString(pager));
    		log.error("[OrdersService][getOrders]根据条件取得订单表时出现未知异常：", e);
    	}
    	return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type,
                                                       SellerUser sellerUser, boolean updateStore) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
//            serviceResult
//                .setResult(ordersModel.updateOrdersBySeller(orders, type, sellerUser, updateStore));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type,
                                                      SystemAdmin systemAdmin,
                                                      boolean updateStore) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
//            serviceResult
//                .setResult(ordersModel.updateOrdersByAdmin(orders, type, systemAdmin, updateStore));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.cancelOrderBySeller(ordersId, sellerUser));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.getOrderNumByMIdAndState(memberId, orderState));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现未知异常：", e);
        }
        return serviceResult;
    }

    public PagerInfo countOrderWithOrderProduct(Map<String, String> queryMap, PagerInfo pager) {
        if (pager != null) {
            pager.setRowsCount(ordersModel.getOrdersCount2(queryMap));
        }
        return pager;
    }
    
    @Override
    public ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);//TODO
        try {
            pager = countOrderWithOrderProduct(queryMap, pager);
//            List<Orders> returnList = ordersModel.getOrderWithOrderProduct(queryMap, pager.getStart(), pager.getPageSize());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrder(String tradeNo, Integer optId, String optName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.cancelOrder(tradeNo, optId, optName));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][cancelOrder]根据ID取消用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][cancalOrder]根据ID取消用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrderWithOPById(Integer orderId,String type) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
//            serviceResult.setResult(ordersModel.getOrderWithOPById(orderId,type));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOPById]根据订单id取订单、网单及产品图片信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOPById]根据订单id 取订单、网单及产品图片信息时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommit(OrderCommitVO orderCommitVO,
                                                     String ordersType) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
//            serviceResult.setResult(ordersModel.orderCommit(orderCommitVO, ordersType));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommit]会员下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommit]会员下单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 确认收货
     * @param ordersId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<Boolean> goodsReceipt(Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.goodsReceipt(ordersId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap) {
        ServiceResult<List<OrderDayDto>> serviceResult = new ServiceResult<List<OrderDayDto>>();
        try {
//            serviceResult.setResult(ordersModel.getOrderDayDto(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:", e);
        }
        return serviceResult;
    }
    
    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDay0913Dto(Map<String, String> queryMap) {
        ServiceResult<List<OrderDayDto>> serviceResult = new ServiceResult<List<OrderDayDto>>();
        try {
//            serviceResult.setResult(ordersModel.getOrderDay0913Dto(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:", e);
        }
        return serviceResult;
    }
    
    @Override
    public ServiceResult<List<OrderFinanceDayDto>> getOrderFinanceDayDto(Map<String, String> queryMap) {
        ServiceResult<List<OrderFinanceDayDto>> serviceResult = new ServiceResult<List<OrderFinanceDayDto>>();
        try {
//            serviceResult.setResult(ordersModel.getOrderFinanceDayDto(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][OrderFinanceDayDto]统计每天收款数据时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][OrderFinanceDayDto]统计每天收款数据时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemFinishOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.jobSystemFinishOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemCancelOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.jobSystemCancelOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据关联订单 查询订单信息 用于非在线支付 查询几个关联子订单之间的信息
     * @param relationOrderSn
     * @param request
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> getOrdersByRelationOrderSn(String relationOrderSn,
                                                                    Integer memberId) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrdersByRelationOrderSn]查询订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param relationOrderSn 订单号以逗号隔开
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> orderPayBefore(String relationOrderSn,
                                                        boolean isBalancePay,
                                                        String balancePassword, Member member) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
//            serviceResult.setResult(
//                ordersModel.orderPayBefore(relationOrderSn, isBalancePay, balancePassword, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:", e);
            e.printStackTrace();
        }
        return serviceResult;
    }

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
    @Override
    public ServiceResult<Boolean> orderPayAfter(String trade_no, String total_fee, String paycode,
                                                String payname, String tradeSn,
                                                String tradeContent) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
//            serviceResult.setResult(ordersModel.orderPayAfter(trade_no, total_fee, paycode, payname,
//                tradeSn, tradeContent));
        } catch (BusinessException be) {
            be.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态辑发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForBidding(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForBidding]会员提交集合竞价订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForBidding]会员提交集合竞价订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveOrdersProductPrice(Integer orderId, String opinfo,String submitType) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForFlash]保存网单价格时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForFlash]保存网单价格时发生异常:", e);
        }
        return serviceResult;
    }
    
    
    @Override
    public ServiceResult<Boolean> saveOrdersReturn(Integer orderId, String opinfo,String returnInfo,Integer usedId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForFlash]保存网单价格时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForFlash]保存网单价格时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<BookingSendGoodsVO> listUserInfo(Integer orderId) {
        ServiceResult<BookingSendGoodsVO> serviceResult = new ServiceResult<BookingSendGoodsVO>();
        try {
            serviceResult.setSuccess(true);
        } catch (BusinessException be) {
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForFlash]查询下单用户信息出现异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForFlash]查询下单用户信息出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SendingGoodsVO>> listGoodsInfo(Integer orderId) {
        ServiceResult<List<SendingGoodsVO>> serviceResult = new ServiceResult<List<SendingGoodsVO>>();
        serviceResult.setSuccess(true);
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveOrdersProductLabel(Integer orderId, String opinfo) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getordersInStore(Map<String, String> queryMap,
                                                        PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount1(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Orders> list = ordersModel.getordersInStore(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrders] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<BigDecimal> getTransFee(String productIds, String numstrs, String transportType,
                                                 String menberAddressId) {
        ServiceResult<BigDecimal> serviceResult = new ServiceResult<BigDecimal>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getSellerOrdersInStore(Map<String, String> queryMap,
                                                              PagerInfo pager,
                                                              SellerUser sellerUser) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        if (sellerUser != null) {
            queryMap.put("q_sellerId", sellerUser.getSellerId().toString());
        } else {
            throw new BusinessException("商家未登录");
        }
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount1(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Orders> list = ordersModel.getordersInStore(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getSellerOrdersInStore]根据条件取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getSellerOrdersInStore] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrdersService][getSellerOrdersInStore]根据条件取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getOrdersByMemberId(Integer memberId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][saveOrders]查询用户是否购买过商品：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][saveOrders] param:" + JSON.toJSONString(memberId));
            log.error("[OrdersService][saveOrders]查询用户是否购买过商品：", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<List<SumParterSaleVO>> getSumParterSaleVO(
			Integer memberId,Integer year,String signNo) {
		ServiceResult<List<SumParterSaleVO>> serviceResult = new ServiceResult<List<SumParterSaleVO>>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<SumParterSaleVO>> getNewSumParterSalesYears(
			Integer memberId,Integer type,PagerInfo pager,String signNo) {
		ServiceResult<List<SumParterSaleVO>> serviceResult = new ServiceResult<List<SumParterSaleVO>>();
		serviceResult.setPager(pager);
        try {
        	Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<SalesPersonVO>> panerTotalPerson(
			Integer memberId, String startTime, String endTime, PagerInfo pager,String signNo,String areaId) {
		ServiceResult<List<SalesPersonVO>> serviceResult = new ServiceResult<List<SalesPersonVO>>();
		serviceResult.setPager(pager);
        try {
        	Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	
	@Override
	public ServiceResult<List<SalesPersonVO>> panerTotalPersonFront(
			Integer memberId, String startTime, String endTime, int start ,int size,String signNo,String areaId) {
		ServiceResult<List<SalesPersonVO>> serviceResult = new ServiceResult<List<SalesPersonVO>>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	
	@Override
	public ServiceResult<Integer> panerTotalPersonFrontCount(
			Integer memberId, String startTime, String endTime,String signNo,String areaId) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	
	@Override
	public ServiceResult<List<CategorySalesVO>> getCategorySales(
			Integer memberId, String startTime, String endTime,String signNo) {
		ServiceResult<List<CategorySalesVO>> serviceResult = new ServiceResult<List<CategorySalesVO>>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<SalesDetailsVO>> getSalesDetails(
			Integer memberId, String startTime, String endTime, PagerInfo pager,String signNo,String areaId) {
		ServiceResult<List<SalesDetailsVO>> serviceResult = new ServiceResult<List<SalesDetailsVO>>();
		serviceResult.setPager(pager);
        try {
        	Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	
	@Override
	public ServiceResult<Integer> getSalesDetailsFrontCount(
			Integer memberId, String startTime, String endTime,String signNo,String areaId) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	
	@Override
	public ServiceResult<List<SalesDetailsVO>> getSalesDetailsFront(
			Integer memberId, String startTime, String endTime, int start,int size,String signNo,String areaId) {
		ServiceResult<List<SalesDetailsVO>> serviceResult = new ServiceResult<List<SalesDetailsVO>>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}
	@Override
	public ServiceResult<List<SalesDetailsVO>> getSalesDetailsByOrdersId(
			Integer ordersId) {
		ServiceResult<List<SalesDetailsVO>> serviceResult = new ServiceResult<List<SalesDetailsVO>>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setMessage(e.getMessage());
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return serviceResult;
	}

    @Override
    public ServiceResult<Boolean> sendMessageToMember() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]系统群发短信时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemCancelOrder]系统群发短信时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> saveOrdersLeadingXls(File newFile) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]导入定时发生错误:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "导入定时发生错误。");
            log.error("[OrderService][jobSystemCancelOrder]导入定时发生错误:", e);
        }
        return serviceResult;
    }

    @Override
    public void sendMessageToSeller(Orders orders) {
        try {
        } catch (BusinessException be) {
            log.error("[OrderService]发送短信发生异常:" + be.getMessage());
        } catch (Exception e) {
            log.error("[OrderService]发送短信发生异常:", e);
        }
    }

    @Override
    public ServiceResult<Boolean> deleteOrder(String orderId, Integer id, String name) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<List<Orders>> getOrdersByTradeNo(String tradeNo) {
		ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        try {
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrdersByTradeNo]根据支付码获取订单时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersByTradeNo]根据支付码获取订单时出现未知异常：", e);
        }
        return serviceResult;
	}

    @Override
    public ServiceResult<Boolean> sendMessageToEffectiveCunstomer() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]系统群发短信时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemCancelOrder]系统群发短信时发生异常:", e);
        }
        return serviceResult;
    }

}