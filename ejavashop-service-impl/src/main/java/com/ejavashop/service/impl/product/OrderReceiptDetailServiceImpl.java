package com.ejavashop.service.impl.product;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.OrderReceiptDetail;
import com.ejavashop.service.product.IOrderReceiptDetailService;

@Service(value = "orderReceiptDetailService")
public class OrderReceiptDetailServiceImpl implements IOrderReceiptDetailService {
	private static Logger      log = LogManager.getLogger(OrderReceiptDetailServiceImpl.class);
	
	@Resource
	private com.ejavashop.model.product.OrderReceiptDetailModel orderReceiptDetailModel;
    
     /**
     * 根据id取得order_receipt_detail对象
     * @param  orderReceiptDetailId
     * @return
     */
    @Override
    public ServiceResult<OrderReceiptDetail> getOrderReceiptDetailById(Integer orderReceiptDetailId) {
        ServiceResult<OrderReceiptDetail> result = new ServiceResult<OrderReceiptDetail>();
        try {
            result.setResult(orderReceiptDetailModel.getOrderReceiptDetailById(orderReceiptDetailId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptDetailService][getOrderReceiptDetailById]根据id["+orderReceiptDetailId+"]取得order_receipt_detail对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptDetailService][getOrderReceiptDetailById]根据id["+orderReceiptDetailId+"]取得order_receipt_detail对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(orderReceiptDetailModel.saveOrderReceiptDetail(orderReceiptDetail));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptDetailService][saveOrderReceiptDetail]保存order_receipt_detail对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptDetailService][saveOrderReceiptDetail]保存order_receipt_detail对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新order_receipt_detail对象
     * @param  orderReceiptDetail
     * @return
     * @see com.ejavashop.service.OrderReceiptDetailService#updateOrderReceiptDetail(OrderReceiptDetail)
     */
     @Override
     public ServiceResult<Integer> updateOrderReceiptDetail(OrderReceiptDetail orderReceiptDetail) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(orderReceiptDetailModel.updateOrderReceiptDetail(orderReceiptDetail));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptDetailService][updateOrderReceiptDetail]更新order_receipt_detail对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptDetailService][updateOrderReceiptDetail]更新order_receipt_detail对象时出现未知异常：",
                e);
        }
        return result;
     }
}