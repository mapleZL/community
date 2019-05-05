package com.ejavashop.service.impl.product;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductOrder;
import com.ejavashop.model.product.ProductOrderModel;
import com.ejavashop.service.product.IProductOrderService;

@Service(value = "productOrderService")
public class ProductOrderServiceImpl implements IProductOrderService {
	private static Logger      log = LogManager.getLogger(ProductOrderServiceImpl.class);
	
	@Resource
	private ProductOrderModel productOrderModel;
    
     /**
     * 根据id取得楼层排序表
     * @param  productOrderId
     * @return
     */
    @Override
    public ServiceResult<ProductOrder> getProductOrderById(Integer productOrderId) {
        ServiceResult<ProductOrder> result = new ServiceResult<ProductOrder>();
        try {
            result.setResult(productOrderModel.getProductOrderById(productOrderId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductOrderService][getProductOrderById]根据id["+productOrderId+"]取得楼层排序表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductOrderService][getProductOrderById]根据id["+productOrderId+"]取得楼层排序表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存楼层排序表
     * @param  productOrder
     * @return
     */
     @Override
     public ServiceResult<Integer> saveProductOrder(ProductOrder productOrder) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productOrderModel.saveProductOrder(productOrder));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductOrderService][saveProductOrder]保存楼层排序表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductOrderService][saveProductOrder]保存楼层排序表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新楼层排序表
     * @param  productOrder
     * @return
     * @see com.ejavashop.service.ProductOrderService#updateProductOrder(ProductOrder)
     */
     @Override
     public ServiceResult<Integer> updateProductOrder(ProductOrder productOrder) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productOrderModel.updateProductOrder(productOrder));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductOrderService][updateProductOrder]更新楼层排序表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductOrderService][updateProductOrder]更新楼层排序表时出现未知异常：",
                e);
        }
        return result;
     }
}