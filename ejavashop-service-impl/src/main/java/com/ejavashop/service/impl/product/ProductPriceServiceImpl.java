package com.ejavashop.service.impl.product;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.ejavashop.service.product.IProductPriceService;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.model.product.ProductPriceModel;

@Service(value = "productPriceService")
public class ProductPriceServiceImpl implements IProductPriceService {
	private static Logger      log = LogManager.getLogger(ProductPriceServiceImpl.class);
	
	@Resource
	private ProductPriceModel productPriceModel;
    
     /**
     * 根据id取得product_price对象
     * @param  productPriceId
     * @return
     */
    @Override
    public ServiceResult<ProductPrice> getProductPriceById(Integer productPriceId) {
        ServiceResult<ProductPrice> result = new ServiceResult<ProductPrice>();
        try {
            result.setResult(productPriceModel.getProductPriceById(productPriceId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPriceService][getProductPriceById]根据id["+productPriceId+"]取得product_price对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPriceService][getProductPriceById]根据id["+productPriceId+"]取得product_price对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存product_price对象
     * @param  productPrice
     * @return
     */
     @Override
     public ServiceResult<Integer> saveProductPrice(ProductPrice productPrice) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productPriceModel.saveProductPrice(productPrice));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPriceService][saveProductPrice]保存product_price对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPriceService][saveProductPrice]保存product_price对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新product_price对象
     * @param  productPrice
     * @return
     * @see com.ejavashop.service.ProductPriceService#updateProductPrice(ProductPrice)
     */
     @Override
     public ServiceResult<Integer> updateProductPrice(ProductPrice productPrice) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productPriceModel.updateProductPrice(productPrice));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPriceService][updateProductPrice]更新product_price对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPriceService][updateProductPrice]更新product_price对象时出现未知异常：",
                e);
        }
        return result;
     }
}