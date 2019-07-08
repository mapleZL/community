package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoOrganization;
import com.phkj.model.relate.StBaseinfoOrganizationModel;
import com.phkj.service.relate.IStBaseinfoOrganizationService;

@Service(value = "stBaseinfoOrganizationService")
public class StBaseinfoOrganizationServiceImpl implements IStBaseinfoOrganizationService {
	private static Logger      log = LogManager.getLogger(StBaseinfoOrganizationServiceImpl.class);
	
	@Resource
	private StBaseinfoOrganizationModel stBaseinfoOrganizationModel;
    
     /**
     * 根据id取得街道小区组织表
     * @param  stBaseinfoOrganizationId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoOrganization> getStBaseinfoOrganizationById(Long stBaseinfoOrganizationId) {
        ServiceResult<StBaseinfoOrganization> result = new ServiceResult<StBaseinfoOrganization>();
        try {
            result.setResult(stBaseinfoOrganizationModel.getStBaseinfoOrganizationById(stBaseinfoOrganizationId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoOrganizationService][getStBaseinfoOrganizationById]根据id["+stBaseinfoOrganizationId+"]取得街道小区组织表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoOrganizationService][getStBaseinfoOrganizationById]根据id["+stBaseinfoOrganizationId+"]取得街道小区组织表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存街道小区组织表
     * @param  stBaseinfoOrganization
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoOrganizationModel.saveStBaseinfoOrganization(stBaseinfoOrganization));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoOrganizationService][saveStBaseinfoOrganization]保存街道小区组织表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoOrganizationService][saveStBaseinfoOrganization]保存街道小区组织表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新街道小区组织表
     * @param  stBaseinfoOrganization
     * @return
     * @see com.phkj.service.StBaseinfoOrganizationService#updateStBaseinfoOrganization(StBaseinfoOrganization)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoOrganization(StBaseinfoOrganization stBaseinfoOrganization) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoOrganizationModel.updateStBaseinfoOrganization(stBaseinfoOrganization));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoOrganizationService][updateStBaseinfoOrganization]更新街道小区组织表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoOrganizationService][updateStBaseinfoOrganization]更新街道小区组织表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public List<StBaseinfoOrganization> getOrganizationByRegion(String region) {
        return stBaseinfoOrganizationModel.getOrganizationByRegion(region);
    }

    @Override
    public List<String> getRelationOrgations(String villageCode) {
        return stBaseinfoOrganizationModel.getRelationOrgations(villageCode);
    }

    @Override
    public StBaseinfoOrganization getOrganization(String villageCode) {
        return stBaseinfoOrganizationModel.getOrganization(villageCode);
    }
}