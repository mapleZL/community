package com.phkj.service.impl.order;

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
import com.phkj.entity.order.Orders;
import com.phkj.entity.order.OrdersAndOrdersProductVO;
import com.phkj.entity.seller.SellerUser;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.model.order.OrdersModel;
import com.phkj.model.product.ProductModel;
import com.phkj.service.order.IOrdersService;

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
            List<Orders> list = null;
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
    public ServiceResult<List<OrdersAndOrdersProductVO>> getOrdersAndOrdersProductVO(Map<String, String> queryMap,
                                                                                     PagerInfo pager) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<Orders>> getordersInStore(Map<String, String> queryMap,
                                                        PagerInfo pager) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<Orders>> getSellerOrdersInStore(Map<String, String> queryMap,
                                                              PagerInfo pager,
                                                              SellerUser sellerUser) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type,
                                                       SellerUser sellerUser, boolean updateStore) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type,
                                                      SystemAdmin systemAdmin,
                                                      boolean updateStore) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PagerInfo countOrderWithOrderProduct(Map<String, String> queryMap, PagerInfo pager) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Boolean> cancelOrder(String tradeNo, Integer optId, String optName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Orders> getOrderWithOPById(Integer orderId, String type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Boolean> goodsReceipt(Integer ordersId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDay0913Dto(Map<String, String> queryMap) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<List<OrderFinanceDayDto>> getOrderFinanceDayDto(Map<String, String> queryMap) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ServiceResult<Boolean> jobSystemFinishOrder() {
        // TODO Auto-generated method stub
        return null;
    }

}