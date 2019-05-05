package com.ejavashop.service.impl.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;
import com.ejavashop.model.product.OrderReceiptModel;
import com.ejavashop.service.product.IOrderReceiptService;

@Service(value = "orderReceiptService")
public class OrderReceiptServiceImpl implements IOrderReceiptService {
	private static Logger      log = LogManager.getLogger(OrderReceiptServiceImpl.class);
	
	@Resource
	private OrderReceiptModel orderReceiptModel;
    
     /**
     * 根据id取得order_receipt对象
     * @param  orderReceiptId
     * @return
     */
    @Override
    public ServiceResult<OrderReceipt> getOrderReceiptById(Integer orderReceiptId) {
        ServiceResult<OrderReceipt> result = new ServiceResult<OrderReceipt>();
        try {
            result.setResult(orderReceiptModel.getOrderReceiptById(orderReceiptId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptService][getOrderReceiptById]根据id["+orderReceiptId+"]取得order_receipt对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptService][getOrderReceiptById]根据id["+orderReceiptId+"]取得order_receipt对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存order_receipt对象
     * @param  orderReceipt
     * @return
     */
     @Override
     public ServiceResult<Integer> saveOrderReceipt(OrderReceipt orderReceipt) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(orderReceiptModel.saveOrderReceipt(orderReceipt));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptService][saveOrderReceipt]保存order_receipt对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptService][saveOrderReceipt]保存order_receipt对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新order_receipt对象
     * @param  orderReceipt
     * @return
     * @see com.ejavashop.service.OrderReceiptService#updateOrderReceipt(OrderReceipt)
     */
     @Override
     public ServiceResult<Integer> updateOrderReceipt(OrderReceipt orderReceipt) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(orderReceiptModel.updateOrderReceipt(orderReceipt));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IOrderReceiptService][updateOrderReceipt]更新order_receipt对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	//oms 回传入库单 需要 记录错误日志
        	result.setSuccess(false);
            result.setMessage(e.getMessage());
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IOrderReceiptService][updateOrderReceipt]更新order_receipt对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<OrderReceipt>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<OrderReceipt>> serviceResult = new ServiceResult<List<OrderReceipt>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(orderReceiptModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<OrderReceipt> list = orderReceiptModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerComplaintServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerComplaintServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OrderReceiptDetail>> getReceiptProductDetailByOrdersId(String ordersId) {
        ServiceResult<List<OrderReceiptDetail>> serviceResult = new ServiceResult<List<OrderReceiptDetail>>();
        try {
            serviceResult.setResult(orderReceiptModel.getReceiptProductDetailByOrdersId(ordersId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error(
                "[OrdersProductService][getOrdersProductByOId]根据订单号获取对应的网单:" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[OrdersProductService][getOrdersProductByOId]根据订单号获取对应的网单:", e);
        }
        return serviceResult;
    }

    @Override
    public void auditSendGoods(String ordersId, String type) {
        orderReceiptModel.auditSendGoods(ordersId,type);
    }

	@Override
	public ServiceResult<OrderReceipt> getOrderReceiptByOrdersSn(String OrdersSn) {
		 ServiceResult<OrderReceipt> serviceResult = new ServiceResult<OrderReceipt>();
	        try {
	            serviceResult.setResult(orderReceiptModel.getByOrdersId(OrdersSn));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	            log.error(
	                "[OrdersProductService][getOrderReceiptByOrdersSn]根据订单号获取订单:" + e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[OrdersProductService][getOrderReceiptByOrdersSn]根据订单号获取订单:", e);
	        }
	        return serviceResult;
	}
}