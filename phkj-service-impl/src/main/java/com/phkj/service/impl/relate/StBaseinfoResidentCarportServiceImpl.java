package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoResidentCarport;
import com.phkj.model.relate.StBaseinfoResidentCarportModel;
import com.phkj.service.relate.IStBaseinfoResidentCarportService;

@Service(value = "stBaseinfoResidentCarportService")
public class StBaseinfoResidentCarportServiceImpl implements IStBaseinfoResidentCarportService {
	private static Logger      log = LogManager.getLogger(StBaseinfoResidentCarportServiceImpl.class);
	
	@Resource
	private StBaseinfoResidentCarportModel stBaseinfoResidentCarportModel;
    
     /**
     * 根据id取得居民车位表
     * @param  stBaseinfoResidentCarportId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoResidentCarport> getStBaseinfoResidentCarportById(Long stBaseinfoResidentCarportId) {
        ServiceResult<StBaseinfoResidentCarport> result = new ServiceResult<StBaseinfoResidentCarport>();
        try {
            result.setResult(stBaseinfoResidentCarportModel.getStBaseinfoResidentCarportById(stBaseinfoResidentCarportId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarportService][getStBaseinfoResidentCarportById]根据id["+stBaseinfoResidentCarportId+"]取得居民车位表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarportService][getStBaseinfoResidentCarportById]根据id["+stBaseinfoResidentCarportId+"]取得居民车位表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存居民车位表
     * @param  stBaseinfoResidentCarport
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentCarportModel.saveStBaseinfoResidentCarport(stBaseinfoResidentCarport));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarportService][saveStBaseinfoResidentCarport]保存居民车位表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarportService][saveStBaseinfoResidentCarport]保存居民车位表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新居民车位表
     * @param  stBaseinfoResidentCarport
     * @return
     * @see com.phkj.service.StBaseinfoResidentCarportService#updateStBaseinfoResidentCarport(StBaseinfoResidentCarport)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentCarportModel.updateStBaseinfoResidentCarport(stBaseinfoResidentCarport));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarportService][updateStBaseinfoResidentCarport]更新居民车位表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarportService][updateStBaseinfoResidentCarport]更新居民车位表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<StBaseinfoResidentCarport>> getCarportList(Long residentinfoId) {
        ServiceResult<List<StBaseinfoResidentCarport>> result = new ServiceResult<List<StBaseinfoResidentCarport>>();
        try {
            result.setResult(stBaseinfoResidentCarportModel.getCarportList(residentinfoId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarportService][getCarportList]根据residentinfoId["+residentinfoId+"]取得居民车位表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarportService][getCarportList]根据residentinfoId["+residentinfoId+"]取得居民车位表时出现未知异常：",
                e);
        }
        return result;
    }
}