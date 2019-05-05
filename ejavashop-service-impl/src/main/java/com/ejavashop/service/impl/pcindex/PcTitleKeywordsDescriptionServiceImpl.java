package com.ejavashop.service.impl.pcindex;

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
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;
import com.ejavashop.model.pcindex.PcTitleKeywordsDescriptionModel;
import com.ejavashop.service.pcindex.IPcTitleKeywordsDescriptionService;

@Service(value = "pcTitleKeywordsDescriptionService")
public class PcTitleKeywordsDescriptionServiceImpl implements IPcTitleKeywordsDescriptionService {
	private static Logger      log = LogManager.getLogger(PcTitleKeywordsDescriptionServiceImpl.class);
	
	@Resource
	private PcTitleKeywordsDescriptionModel pcTitleKeywordsDescriptionModel;
    
     /**
     * 根据id取得pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescriptionId
     * @return
     */
    @Override
    public ServiceResult<PcTitleKeywordsDescription> getPcTitleKeywordsDescriptionById(Integer pcTitleKeywordsDescriptionId) {
        ServiceResult<PcTitleKeywordsDescription> result = new ServiceResult<PcTitleKeywordsDescription>();
        try {
            result.setResult(pcTitleKeywordsDescriptionModel.getPcTitleKeywordsDescriptionById(pcTitleKeywordsDescriptionId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPcTitleKeywordsDescriptionService][getPcTitleKeywordsDescriptionById]根据id["+pcTitleKeywordsDescriptionId+"]取得pc_title_keywords_description对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPcTitleKeywordsDescriptionService][getPcTitleKeywordsDescriptionById]根据id["+pcTitleKeywordsDescriptionId+"]取得pc_title_keywords_description对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     */
     @Override
     public ServiceResult<Integer> savePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(pcTitleKeywordsDescriptionModel.savePcTitleKeywordsDescription(pcTitleKeywordsDescription));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPcTitleKeywordsDescriptionService][savePcTitleKeywordsDescription]保存pc_title_keywords_description对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPcTitleKeywordsDescriptionService][savePcTitleKeywordsDescription]保存pc_title_keywords_description对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新pc_title_keywords_description对象
     * @param  pcTitleKeywordsDescription
     * @return
     * @see com.ejavashop.service.PcTitleKeywordsDescriptionService#updatePcTitleKeywordsDescription(PcTitleKeywordsDescription)
     */
     @Override
     public ServiceResult<Integer> updatePcTitleKeywordsDescription(PcTitleKeywordsDescription pcTitleKeywordsDescription) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(pcTitleKeywordsDescriptionModel.updatePcTitleKeywordsDescription(pcTitleKeywordsDescription));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPcTitleKeywordsDescriptionService][updatePcTitleKeywordsDescription]更新pc_title_keywords_description对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPcTitleKeywordsDescriptionService][updatePcTitleKeywordsDescription]更新pc_title_keywords_description对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<PcTitleKeywordsDescription> getByPath(String visitPath) {
        ServiceResult<PcTitleKeywordsDescription> result = new ServiceResult<PcTitleKeywordsDescription>();
        try {
            result.setResult(pcTitleKeywordsDescriptionModel.getByPath(visitPath));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPcTitleKeywordsDescriptionService][getByPath]根据id["+visitPath+"]取得pc_title_keywords_description对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPcTitleKeywordsDescriptionService][getByPath]根据id["+visitPath+"]取得pc_title_keywords_description对象时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PcTitleKeywordsDescription>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<PcTitleKeywordsDescription>> serviceResult = new ServiceResult<List<PcTitleKeywordsDescription>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(pcTitleKeywordsDescriptionModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<PcTitleKeywordsDescription> list = pcTitleKeywordsDescriptionModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPcTitleKeywordsDescriptionService][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPcTitleKeywordsDescriptionService][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}