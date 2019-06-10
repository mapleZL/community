package com.phkj.service.impl.product;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.product.ProductPicture;
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
      * 保存或者是更新商品
      * @param product
      * @return
      * @see com.phkj.service.product.IStAppletProductService#updateOrCreate(com.phkj.entity.product.StAppletProduct)
      */
    @Override
    public ServiceResult<Boolean> updateOrCreate(StAppletProduct product, List<ProductPicture> pictures) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            if (product.getId() > 0) {
                stAppletProductModel.updateStAppletProduct(product, pictures);
            } else {
                stAppletProductModel.saveStAppletProduct(product, pictures);
            }
            result.setSuccess(true);
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("保存或更新商品时发生错误：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> checkProductBySPUAndSellerId(Map<String, String> queryMap) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(stAppletProductModel.checkProductBySPUAndSellerId(queryMap));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductBykeyWord keyWords:");
            log.error("ProductServiceImpl getProductById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<StAppletProduct>> pageProduct(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<StAppletProduct>> serviceResult = new ServiceResult<List<StAppletProduct>>();
        try {
            serviceResult.setResult(stAppletProductModel.pageProduct(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
                      + " pager:" + JSON.toJSONString(pager));
            log.error("ProductServiceImpl pageProduct exception:", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<Boolean> delProduct(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(stAppletProductModel.delProduct(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl delProduct productId:" + id);
            log.error("ProductServiceImpl delProduct exception:", e);
        }
        return serviceResult;
    }
    
    @Override
    public ServiceResult<Integer> updateByIds(Map<String, Object> param, List<Integer> ids) {

        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(stAppletProductModel.updateByIds(param, ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateByIds param1:" + JSON.toJSONString(param)
                      + System.lineSeparator() + "param2:" + JSON.toJSONString(ids));
            log.error("ProductServiceImpl updateByIds exception:", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<Integer> update(StAppletProduct stAppletProduct) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(stAppletProductModel.update(stAppletProduct));
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateByIds param1:" + JSON.toJSONString(stAppletProduct));
            log.error("ProductServiceImpl updateByIds exception:", e);
        }
        return serviceResult;
    }


    @Override
    public List<StAppletProduct> list(Integer pageNum, Integer pageSize, Integer productCateId,
                                      String villageCode, String search) {
        List<StAppletProduct> list = null;
        try {
            Integer start = (pageNum - 1) * pageSize;
            list = stAppletProductModel.list(start, pageSize, productCateId, villageCode, search);
        } catch (Exception e) {
            log.error("ProductServiceImpl updateByIds param:" + pageNum + pageSize + productCateId + villageCode);
            log.error("ProductServiceImpl list exception:", e);
        }
        return list;
    }


    @Override
    public Integer count(Integer productCateId, String villageCode, String search) {
        return stAppletProductModel.count(productCateId, villageCode, search);
    }
}