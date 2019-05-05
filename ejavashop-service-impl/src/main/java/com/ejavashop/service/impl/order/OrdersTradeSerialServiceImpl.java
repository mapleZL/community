package com.ejavashop.service.impl.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.model.order.OrdersTradeSerialModel;
import com.ejavashop.service.order.IOrdersTradeSerialService;

@Service(value = "ordersTradeSerialService")
public class OrdersTradeSerialServiceImpl implements IOrdersTradeSerialService {
    private static Logger          log = LogManager.getLogger(OrdersTradeSerialServiceImpl.class);

    @Resource
    private OrdersTradeSerialModel ordersTradeSerialModel;

    /**
    * 根据id取得订单交易序列
    * @param  ordersTradeSerialId
    * @return
    */
    @Override
    public ServiceResult<OrdersTradeSerial> getOrdersTradeSerialById(String ordersTradeSerialId) {
        ServiceResult<OrdersTradeSerial> result = new ServiceResult<OrdersTradeSerial>();
        try {
            result.setResult(ordersTradeSerialModel.getOrdersTradeSerialById(ordersTradeSerialId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersTradeSerialService][getOrdersTradeSerialById]根据id["
                      + ordersTradeSerialId + "]取得订单交易序列时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersTradeSerialService][getOrdersTradeSerialById]根据id["
                      + ordersTradeSerialId + "]取得订单交易序列时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存订单交易序列
     * @param  ordersTradeSerial
     * @return
     */
    @Override
    public ServiceResult<Integer> saveOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersTradeSerialModel.saveOrdersTradeSerial(ordersTradeSerial));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersTradeSerialService][saveOrdersTradeSerial]保存订单交易序列时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersTradeSerialService][saveOrdersTradeSerial]保存订单交易序列时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新订单交易序列
    * @param  ordersTradeSerial
    * @return
    * @see com.ejavashop.service.OrdersTradeSerialService#updateOrdersTradeSerial(OrdersTradeSerial)
    */
    @Override
    public ServiceResult<Integer> updateOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersTradeSerialModel.updateOrdersTradeSerial(ordersTradeSerial));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersTradeSerialService][updateOrdersTradeSerial]更新订单交易序列时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersTradeSerialService][updateOrdersTradeSerial]更新订单交易序列时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<OrdersTradeSerial>> page(Map<String, String> queryMap,
                                                       PagerInfo pager) {
        ServiceResult<List<OrdersTradeSerial>> serviceResult = new ServiceResult<List<OrdersTradeSerial>>();
        try {
            serviceResult.setResult(ordersTradeSerialModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[OrdersTradeSerialServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersTradeSerialModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[OrdersTradeSerialServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrdersTradeSerial> getByRelationOrderSn(String relationOrderSn) {
        ServiceResult<OrdersTradeSerial> result = new ServiceResult<OrdersTradeSerial>();
        try {
            result.setResult(ordersTradeSerialModel.getByRelationOrderSn(relationOrderSn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
        }
        return result;
    }

    @Override
    public ServiceResult<OrdersTradeSerial> existsByOrders(String orderPaySn) {
        ServiceResult<OrdersTradeSerial> serviceResult = new ServiceResult<OrdersTradeSerial>();
        try {
            serviceResult.setResult(ordersTradeSerialModel.existsByOrders(orderPaySn));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[OrdersTradeSerialServiceImpl][existsByOrders] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}