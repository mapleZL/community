package com.ejavashop.service.impl.order;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersProductError;
import com.ejavashop.model.order.OrdersProductErrorModel;
import com.ejavashop.service.order.IOrdersProductErrorService;

@Service(value = "ordersProductErrorService")
public class OrdersProductErrorServiceImpl implements IOrdersProductErrorService {
	private static Logger      log = LogManager.getLogger(OrdersProductErrorServiceImpl.class);
	
	@Resource
	private OrdersProductErrorModel ordersProductErrorModel;
    
     /**
     * 根据id取得网单表
     * @param  ordersProductErrorId
     * @return
     */
    @Override
    public ServiceResult<OrdersProductError> getOrdersProductErrorById(Integer ordersProductErrorId) {
        ServiceResult<OrdersProductError> result = new ServiceResult<OrdersProductError>();
        try {
            result.setResult(ordersProductErrorModel.getOrdersProductErrorById(ordersProductErrorId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductErrorService][getOrdersProductErrorById]根据id["+ordersProductErrorId+"]取得网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductErrorService][getOrdersProductErrorById]根据id["+ordersProductErrorId+"]取得网单表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存网单表
     * @param  ordersProductError
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrdersProductError(OrdersProductError ordersProductError) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersProductErrorModel.saveOrdersProductError(ordersProductError));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductErrorService][saveOrdersProductError]保存网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductErrorService][saveOrdersProductError]保存网单表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新网单表
     * @param  ordersProductError
     * @return
     * @see com.ejavashop.service.OrdersProductErrorService#updateOrdersProductError(OrdersProductError)
     */
     @Override
     public ServiceResult<Integer> updateOrdersProductError(OrdersProductError ordersProductError) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersProductErrorModel.updateOrdersProductError(ordersProductError));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductErrorService][updateOrdersProductError]更新网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductErrorService][updateOrdersProductError]更新网单表时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<OrdersProductError>> geterrorordersproduct(
			String ordersErroeSn) {
		ServiceResult<List<OrdersProductError>> result = new ServiceResult<List<OrdersProductError>>();
        try {
            result.setResult(ordersProductErrorModel.geterrorordersproduct(ordersErroeSn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductErrorService][geterrorordersproduct]查询网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductErrorService][geterrorordersproduct]查询网单表时出现未知异常：",
                e);
        }
        return result;
	}
}