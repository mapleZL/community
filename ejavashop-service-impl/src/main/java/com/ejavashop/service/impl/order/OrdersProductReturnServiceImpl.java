package com.ejavashop.service.impl.order;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersProductReturn;
import com.ejavashop.model.order.OrdersProductReturnModel;
import com.ejavashop.service.order.IOrdersProductReturnService;

@Service(value = "ordersProductReturnService")
public class OrdersProductReturnServiceImpl implements IOrdersProductReturnService {
	private static Logger      log = LogManager.getLogger(OrdersProductReturnServiceImpl.class);
	
	@Resource
	private OrdersProductReturnModel ordersProductReturnModel;
    
     /**
     * 根据id取得orders_product_return对象
     * @param  ordersProductReturnId
     * @return
     */
    @Override
    public ServiceResult<OrdersProductReturn> getOrdersProductReturnById(Integer ordersProductReturnId) {
        ServiceResult<OrdersProductReturn> result = new ServiceResult<OrdersProductReturn>();
        try {
            result.setResult(ordersProductReturnModel.getOrdersProductReturnById(ordersProductReturnId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductReturnService][getOrdersProductReturnById]根据id["+ordersProductReturnId+"]取得orders_product_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductReturnService][getOrdersProductReturnById]根据id["+ordersProductReturnId+"]取得orders_product_return对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存orders_product_return对象
     * @param  ordersProductReturn
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrdersProductReturn(OrdersProductReturn ordersProductReturn) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersProductReturnModel.saveOrdersProductReturn(ordersProductReturn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductReturnService][saveOrdersProductReturn]保存orders_product_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductReturnService][saveOrdersProductReturn]保存orders_product_return对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新orders_product_return对象
     * @param  ordersProductReturn
     * @return
     * @see com.ejavashop.service.OrdersProductReturnService#updateOrdersProductReturn(OrdersProductReturn)
     */
     @Override
     public ServiceResult<Integer> updateOrdersProductReturn(OrdersProductReturn ordersProductReturn) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersProductReturnModel.updateOrdersProductReturn(ordersProductReturn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersProductReturnService][updateOrdersProductReturn]更新orders_product_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersProductReturnService][updateOrdersProductReturn]更新orders_product_return对象时出现未知异常：",
                e);
        }
        return result;
     }
}