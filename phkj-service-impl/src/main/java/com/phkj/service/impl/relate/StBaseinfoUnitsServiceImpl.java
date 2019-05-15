package com.phkj.service.impl.relate;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoUnits;
import com.phkj.model.relate.StBaseinfoUnitsModel;
import com.phkj.service.relate.IStBaseinfoUnitsService;

@Service(value = "stBaseinfoUnitsService")
public class StBaseinfoUnitsServiceImpl implements IStBaseinfoUnitsService {
	private static Logger      log = LogManager.getLogger(StBaseinfoUnitsServiceImpl.class);
	
	@Resource
	private StBaseinfoUnitsModel stBaseinfoUnitsModel;
    
     /**
     * 根据id取得楼幢单元信息
     * @param  stBaseinfoUnitsId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoUnits> getStBaseinfoUnitsById(Long stBaseinfoUnitsId) {
        ServiceResult<StBaseinfoUnits> result = new ServiceResult<StBaseinfoUnits>();
        try {
            result.setResult(stBaseinfoUnitsModel.getStBaseinfoUnitsById(stBaseinfoUnitsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoUnitsService][getStBaseinfoUnitsById]根据id["+stBaseinfoUnitsId+"]取得楼幢单元信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoUnitsService][getStBaseinfoUnitsById]根据id["+stBaseinfoUnitsId+"]取得楼幢单元信息时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoUnitsModel.saveStBaseinfoUnits(stBaseinfoUnits));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoUnitsService][saveStBaseinfoUnits]保存楼幢单元信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoUnitsService][saveStBaseinfoUnits]保存楼幢单元信息时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     * @see com.phkj.service.StBaseinfoUnitsService#updateStBaseinfoUnits(StBaseinfoUnits)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoUnitsModel.updateStBaseinfoUnits(stBaseinfoUnits));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoUnitsService][updateStBaseinfoUnits]更新楼幢单元信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoUnitsService][updateStBaseinfoUnits]更新楼幢单元信息时出现未知异常：",
                e);
        }
        return result;
     }
}