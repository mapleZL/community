package com.phkj.service.impl.order;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.model.order.StAppletOrdersProductModel;
import com.phkj.service.order.IStAppletOrdersProductService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

@Service(value = "stAppletOrdersProductService")
public class StAppletOrdersProductServiceImpl implements IStAppletOrdersProductService {
	private static Logger      log = LogManager.getLogger(StAppletOrdersProductServiceImpl.class);
	
	@Resource
	private StAppletOrdersProductModel stAppletOrdersProductModel;
    
     /**
     * 根据id取得网单表
     * @param  stAppletOrdersProductId
     * @return
     */
    @Override
    public ServiceResult<StAppletOrdersProduct> getStAppletOrdersProductById(Integer stAppletOrdersProductId) {
        ServiceResult<StAppletOrdersProduct> result = new ServiceResult<StAppletOrdersProduct>();
        try {
            result.setResult(stAppletOrdersProductModel.getStAppletOrdersProductById(stAppletOrdersProductId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersProductService][getStAppletOrdersProductById]根据id["+stAppletOrdersProductId+"]取得网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersProductService][getStAppletOrdersProductById]根据id["+stAppletOrdersProductId+"]取得网单表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletOrdersProductModel.saveStAppletOrdersProduct(stAppletOrdersProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersProductService][saveStAppletOrdersProduct]保存网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersProductService][saveStAppletOrdersProduct]保存网单表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     @Override
     public ServiceResult<Integer> updateStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletOrdersProductModel.updateStAppletOrdersProduct(stAppletOrdersProduct));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersProductService][updateStAppletOrdersProduct]更新网单表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersProductService][updateStAppletOrdersProduct]更新网单表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<StAppletOrdersProduct>> getProductList(String orderSn) {
        ServiceResult<List<StAppletOrdersProduct>> serviceResult = new ServiceResult<>();
        List<StAppletOrdersProduct> list = stAppletOrdersProductModel.getProductList(orderSn);
        serviceResult.setResult(list);
        return serviceResult;
    }
}