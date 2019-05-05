package com.ejavashop.service.impl.product;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductSkuOther;
import com.ejavashop.model.product.ProductSkuOtherModel;
import com.ejavashop.service.product.IProductSkuOtherService;

@Service(value = "productSkuOtherService")
public class ProductSkuOtherServiceImpl implements IProductSkuOtherService {
	private static Logger      log = LogManager.getLogger(ProductSkuOtherServiceImpl.class);
	
	@Resource
	private ProductSkuOtherModel productSkuOtherModel;
    
     /**
     * 根据id取得product_sku_other对象
     * @param  productSkuOtherId
     * @return
     */
    @Override
    public ServiceResult<ProductSkuOther> getProductSkuOtherById(long productSkuOtherId) {
        ServiceResult<ProductSkuOther> result = new ServiceResult<ProductSkuOther>();
        try {
            result.setResult(productSkuOtherModel.getProductSkuOtherById(productSkuOtherId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductSkuOtherService][getProductSkuOtherById]根据id["+productSkuOtherId+"]取得product_sku_other对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductSkuOtherService][getProductSkuOtherById]根据id["+productSkuOtherId+"]取得product_sku_other对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    
    public ServiceResult<List<ProductSkuOther>> queryProductSkuOtherByProductId(int productId) {
        ServiceResult<List<ProductSkuOther>> result = new ServiceResult<List<ProductSkuOther>>();
        try {
            result.setResult(productSkuOtherModel.queryProductSkuOtherByProductId(productId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductSkuOtherService][queryProductSkuOtherByProductId]根据id["+productId+"]取得product_sku_other对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductSkuOtherService][queryProductSkuOtherByProductId]根据id["+productId+"]取得product_sku_other对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存product_sku_other对象
     * @param  productSkuOther
     * @return
     */
     @Override
     public ServiceResult<Integer> saveProductSkuOther(ProductSkuOther productSkuOther) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productSkuOtherModel.saveProductSkuOther(productSkuOther));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductSkuOtherService][saveProductSkuOther]保存product_sku_other对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductSkuOtherService][saveProductSkuOther]保存product_sku_other对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新product_sku_other对象
     * @param  productSkuOther
     * @return
     * @see com.ejavashop.service.ProductSkuOtherService#updateProductSkuOther(ProductSkuOther)
     */
     @Override
     public ServiceResult<Integer> updateProductSkuOther(ProductSkuOther productSkuOther) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productSkuOtherModel.updateProductSkuOther(productSkuOther));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductSkuOtherService][updateProductSkuOther]更新product_sku_other对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductSkuOtherService][updateProductSkuOther]更新product_sku_other对象时出现未知异常：",
                e);
        }
        return result;
     }
}