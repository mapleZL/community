package com.ejavashop.service.impl.operate;

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
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.model.operate.ProductPackageModel;
import com.ejavashop.service.operate.IProductPackageService;

@Service(value = "productPackageService")
public class ProductPackageServiceImpl implements IProductPackageService {
    private static Logger       log = LogManager.getLogger(ProductPackageServiceImpl.class);

    @Resource
    private ProductPackageModel productPackageModel;

    /**
    * 根据id取得二次加工套餐设定
    * @param  productPackageId
    * @return
    */
    @Override
    public ServiceResult<ProductPackage> getProductPackageById(Integer productPackageId) {
        ServiceResult<ProductPackage> result = new ServiceResult<ProductPackage>();
        try {
            result.setResult(productPackageModel.getProductPackageById(productPackageId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPackageService][getProductPackageById]根据id[" + productPackageId
                      + "]取得二次加工套餐设定时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPackageService][getProductPackageById]根据id[" + productPackageId
                      + "]取得二次加工套餐设定时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存二次加工套餐设定
     * @param  productPackage
     * @return
     */
    @Override
    public ServiceResult<Integer> saveProductPackage(ProductPackage productPackage) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productPackageModel.saveProductPackage(productPackage));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPackageService][saveProductPackage]保存二次加工套餐设定时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPackageService][saveProductPackage]保存二次加工套餐设定时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新二次加工套餐设定
    * @param  productPackage
    * @return
    * @see com.ejavashop.service.ProductPackageService#updateProductPackage(ProductPackage)
    */
    @Override
    public ServiceResult<Integer> updateProductPackage(ProductPackage productPackage) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productPackageModel.updateProductPackage(productPackage));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductPackageService][updateProductPackage]更新二次加工套餐设定时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductPackageService][updateProductPackage]更新二次加工套餐设定时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductPackage>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ProductPackage>> serviceResult = new ServiceResult<List<ProductPackage>>();
        try {
            serviceResult.setResult(productPackageModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ProductPackageServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ProductPackageServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}