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
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.model.product.WmsClassifyModel;
import com.ejavashop.service.product.IWmsClassifyService;

@Service(value = "wmsClassifyService")
public class WmsClassifyServiceImpl implements IWmsClassifyService {
	private static Logger      log = LogManager.getLogger(WmsClassifyServiceImpl.class);
	
	@Resource
	private WmsClassifyModel wmsClassifyModel;
    
     /**
     * 根据id取得wms_classify对象
     * @param  wmsClassifyId
     * @return
     */
    @Override
    public ServiceResult<WmsClassify> getWmsClassifyById(Integer wmsClassifyId) {
        ServiceResult<WmsClassify> result = new ServiceResult<WmsClassify>();
        try {
            result.setResult(wmsClassifyModel.getWmsClassifyById(wmsClassifyId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IWmsClassifyService][getWmsClassifyById]根据id["+wmsClassifyId+"]取得wms_classify对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IWmsClassifyService][getWmsClassifyById]根据id["+wmsClassifyId+"]取得wms_classify对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存wms_classify对象
     * @param  wmsClassify
     * @return
     */
     @Override
     public ServiceResult<Integer> saveWmsClassify(WmsClassify wmsClassify) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(wmsClassifyModel.saveWmsClassify(wmsClassify));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IWmsClassifyService][saveWmsClassify]保存wms_classify对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IWmsClassifyService][saveWmsClassify]保存wms_classify对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新wms_classify对象
     * @param  wmsClassify
     * @return
     * @see com.ejavashop.service.WmsClassifyService#updateWmsClassify(WmsClassify)
     */
     @Override
     public ServiceResult<Integer> updateWmsClassify(WmsClassify wmsClassify) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(wmsClassifyModel.updateWmsClassify(wmsClassify));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IWmsClassifyService][updateWmsClassify]更新wms_classify对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IWmsClassifyService][updateWmsClassify]更新wms_classify对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<WmsClassify>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<WmsClassify>> serviceResult = new ServiceResult<List<WmsClassify>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(wmsClassifyModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<WmsClassify> list = wmsClassifyModel.page(param);

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
    public ServiceResult<List<WmsClassify>> getWmsCategoryList(Integer state) {
        ServiceResult<List<WmsClassify>> result = new ServiceResult<List<WmsClassify>>();
        try {
            result.setResult(wmsClassifyModel.getWmsCategoryList(state));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IWmsClassifyService][getWmsCategoryList]取得wms_classify对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IWmsClassifyService][getWmsCategoryList]取得wms_classify对象时出现未知异常：",
                e);
        }
        return result;
    }
}