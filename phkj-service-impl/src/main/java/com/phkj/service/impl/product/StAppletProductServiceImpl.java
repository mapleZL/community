package com.phkj.service.impl.product;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.product.StAppletProduct;
import com.phkj.model.product.StAppletProductModel;
import com.phkj.service.product.IStAppletProductService;

@Service(value = "stAppletProductService")
public class StAppletProductServiceImpl implements IStAppletProductService {
	private static Logger      log = LogManager.getLogger(StAppletProductServiceImpl.class);
	
	@Autowired
	private StAppletProductModel stAppletProductModel;
    
     /**
     * 根据id取得商品表
     * @param  stAppletProductId
     * @return
     */
    @Override
    public ServiceResult<StAppletProduct> getStAppletProductById(Integer stAppletProductId) {
        ServiceResult<StAppletProduct> result = new ServiceResult<StAppletProduct>();
        try {
            result.setResult(stAppletProductModel.getStAppletProductById(stAppletProductId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletProductService][getStAppletProductById]根据id["+stAppletProductId+"]取得商品表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletProductService][getStAppletProductById]根据id["+stAppletProductId+"]取得商品表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存商品表
     * @param  stAppletProduct
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletProduct(StAppletProduct stAppletProduct) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletProductModel.saveStAppletProduct(stAppletProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletProductService][saveStAppletProduct]保存商品表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletProductService][saveStAppletProduct]保存商品表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新商品表
     * @param  stAppletProduct
     * @return
     * @see com.phkj.service.StAppletProductService#updateStAppletProduct(StAppletProduct)
     */
     @Override
     public ServiceResult<Integer> updateStAppletProduct(StAppletProduct stAppletProduct) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletProductModel.updateStAppletProduct(stAppletProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletProductService][updateStAppletProduct]更新商品表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletProductService][updateStAppletProduct]更新商品表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<Boolean> updateOrCreate(StAppletProduct product) {
        // TODO Auto-generated method stub
        return null;
    }
}