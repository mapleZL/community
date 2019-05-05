package com.ejavashop.service.impl.order;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersReturn;
import com.ejavashop.model.order.OrdersReturnModel;
import com.ejavashop.service.order.IOrdersReturnService;

@Service(value = "ordersReturnService")
public class OrdersReturnServiceImpl implements IOrdersReturnService {
	private static Logger      log = LogManager.getLogger(OrdersReturnServiceImpl.class);
	
	@Resource
	private OrdersReturnModel ordersReturnModel;
    
     /**
     * 根据id取得orders_return对象
     * @param  ordersReturnId
     * @return
     */
    @Override
    public ServiceResult<OrdersReturn> getOrdersReturnById(Integer ordersReturnId) {
        ServiceResult<OrdersReturn> result = new ServiceResult<OrdersReturn>();
        try {
            result.setResult(ordersReturnModel.getOrdersReturnById(ordersReturnId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersReturnService][getOrdersReturnById]根据id["+ordersReturnId+"]取得orders_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersReturnService][getOrdersReturnById]根据id["+ordersReturnId+"]取得orders_return对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存orders_return对象
     * @param  ordersReturn
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrdersReturn(OrdersReturn ordersReturn) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersReturnModel.saveOrdersReturn(ordersReturn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersReturnService][saveOrdersReturn]保存orders_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersReturnService][saveOrdersReturn]保存orders_return对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新orders_return对象
     * @param  ordersReturn
     * @return
     * @see com.ejavashop.service.OrdersReturnService#updateOrdersReturn(OrdersReturn)
     */
     @Override
     public ServiceResult<Integer> updateOrdersReturn(OrdersReturn ordersReturn) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(ordersReturnModel.updateOrdersReturn(ordersReturn));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrdersReturnService][updateOrdersReturn]更新orders_return对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrdersReturnService][updateOrdersReturn]更新orders_return对象时出现未知异常：",
                e);
        }
        return result;
     }
}