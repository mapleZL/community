package com.ejavashop.service.impl.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersError;
import com.ejavashop.model.order.OrdersErrorModel;
import com.ejavashop.service.order.IOrdersErrorService;

@Service(value = "ordersErrorService")
public class OrdersErrorServiceImpl implements IOrdersErrorService {
	private static Logger      log = LogManager.getLogger(OrdersErrorServiceImpl.class);
	
	@Resource
	private OrdersErrorModel ordersErrorModel;
    
     /**
     * 根据id取得订单
     * @param  ordersErrorId
     * @return
     */
    @Override
    public ServiceResult<OrdersError> getOrdersErrorById(Integer ordersErrorId) {
        ServiceResult<OrdersError> result = new ServiceResult<OrdersError>();
        try {
            result.setResult(ordersErrorModel.getOrdersErrorById(ordersErrorId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersErrorService][getOrdersErrorById]根据id["+ordersErrorId+"]取得订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersErrorService][getOrdersErrorById]根据id["+ordersErrorId+"]取得订单时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存订单
     * @param  ordersError
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrdersError(OrdersError ordersError) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersErrorModel.saveOrdersError(ordersError));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersErrorService][saveOrdersError]保存订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersErrorService][saveOrdersError]保存订单时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新订单
     * @param  ordersError
     * @return
     * @see com.ejavashop.service.OrdersErrorService#updateOrdersError(OrdersError)
     */
     @Override
     public ServiceResult<Integer> updateOrdersError(OrdersError ordersError) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersErrorModel.updateOrdersError(ordersError));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersErrorService][updateOrdersError]更新订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersErrorService][updateOrdersError]更新订单时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<OrdersError>> getOrdersErrorByOrdersSn(String ordersSn,
			Map<String, String> queryMap) {
		ServiceResult<List<OrdersError>> result = new ServiceResult<List<OrdersError>>();
        try {
            result.setResult(ordersErrorModel.getOrdersErrorByOrdersSn(ordersSn,queryMap));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersErrorService][getOrdersErrorByOrdersSn]查询订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersErrorService][getOrdersErrorByOrdersSn]查询订单时出现未知异常：",
                e);
        }
        return result;
	}
}