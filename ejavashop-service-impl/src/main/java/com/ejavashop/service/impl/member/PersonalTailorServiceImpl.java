package com.ejavashop.service.impl.member;

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
import com.ejavashop.entity.member.PersonalTailor;
import com.ejavashop.model.member.PersonalTailorModel;
import com.ejavashop.service.member.IPersonalTailorService;


@Service(value = "personalTailorService")
public class PersonalTailorServiceImpl implements IPersonalTailorService {
	private static Logger      log = LogManager.getLogger(PersonalTailorServiceImpl.class);
	
	@Resource
	private PersonalTailorModel personalTailorModel;
    
     /**
     * 根据id取得personal_tailor对象
     * @param  personalTailorId
     * @return
     */
    @Override
    public ServiceResult<PersonalTailor> getPersonalTailorById(Integer personalTailorId) {
        ServiceResult<PersonalTailor> result = new ServiceResult<PersonalTailor>();
        try {
            result.setResult(personalTailorModel.getPersonalTailorById(personalTailorId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorService][getPersonalTailorById]根据id["+personalTailorId+"]取得personal_tailor对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorService][getPersonalTailorById]根据id["+personalTailorId+"]取得personal_tailor对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存personal_tailor对象
     * @param  personalTailor
     * @return
     */
     @Override
     public ServiceResult<Integer> savePersonalTailor(PersonalTailor personalTailor) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(personalTailorModel.savePersonalTailor(personalTailor));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorService][savePersonalTailor]保存personal_tailor对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorService][savePersonalTailor]保存personal_tailor对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新personal_tailor对象
     * @param  personalTailor
     * @return
     * @see com.ejavashop.service.PersonalTailorService#updatePersonalTailor(PersonalTailor)
     */
     @Override
     public ServiceResult<Integer> updatePersonalTailor(PersonalTailor personalTailor) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(personalTailorModel.updatePersonalTailor(personalTailor));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorService][updatePersonalTailor]更新personal_tailor对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorService][updatePersonalTailor]更新personal_tailor对象时出现未知异常：",
                e);
        }
        return result;
     }


    @Override
    public ServiceResult<List<PersonalTailor>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<PersonalTailor>> serviceResult = new ServiceResult<List<PersonalTailor>>();
        serviceResult.setPager(pager);
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(personalTailorModel.pageCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<PersonalTailor> list = personalTailorModel.page(param);

            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBookingSendGoodsService][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IBookingSendGoodsService][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public void deleteTailor(Integer id) {
        try {
            personalTailorModel.deleteTailor(id);
        } catch (BusinessException e) {
            log.error("[IPersonalTailorService][updatePersonalTailor]更新personal_tailor对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            log.error("[IPersonalTailorService][updatePersonalTailor]更新personal_tailor对象时出现未知异常：",
                e);
        }
    }
}