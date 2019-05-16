package com.phkj.service.impl.relate;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoResidentHouse;
import com.phkj.model.relate.StBaseinfoResidentHouseModel;
import com.phkj.service.relate.IStBaseinfoResidentHouseService;

@Service(value = "stBaseinfoResidentHouseService")
public class StBaseinfoResidentHouseServiceImpl implements IStBaseinfoResidentHouseService {
	private static Logger      log = LogManager.getLogger(StBaseinfoResidentHouseServiceImpl.class);
	
	@Resource
	private StBaseinfoResidentHouseModel stBaseinfoResidentHouseModel;
    
     /**
     * 根据id取得居民房屋关系表
     * @param  stBaseinfoResidentHouseId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoResidentHouse> getStBaseinfoResidentHouseById(Long stBaseinfoResidentHouseId) {
        ServiceResult<StBaseinfoResidentHouse> result = new ServiceResult<StBaseinfoResidentHouse>();
        try {
            result.setResult(stBaseinfoResidentHouseModel.getStBaseinfoResidentHouseById(stBaseinfoResidentHouseId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentHouseService][getStBaseinfoResidentHouseById]根据id["+stBaseinfoResidentHouseId+"]取得居民房屋关系表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentHouseService][getStBaseinfoResidentHouseById]根据id["+stBaseinfoResidentHouseId+"]取得居民房屋关系表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存居民房屋关系表
     * @param  stBaseinfoResidentHouse
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentHouseModel.saveStBaseinfoResidentHouse(stBaseinfoResidentHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentHouseService][saveStBaseinfoResidentHouse]保存居民房屋关系表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentHouseService][saveStBaseinfoResidentHouse]保存居民房屋关系表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新居民房屋关系表
     * @param  stBaseinfoResidentHouse
     * @return
     * @see com.phkj.service.StBaseinfoResidentHouseService#updateStBaseinfoResidentHouse(StBaseinfoResidentHouse)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoResidentHouse(StBaseinfoResidentHouse stBaseinfoResidentHouse) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentHouseModel.updateStBaseinfoResidentHouse(stBaseinfoResidentHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentHouseService][updateStBaseinfoResidentHouse]更新居民房屋关系表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentHouseService][updateStBaseinfoResidentHouse]更新居民房屋关系表时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public StBaseinfoResidentHouse getResidentBouseByParam(Map<String, Object> queryMap) {
        return stBaseinfoResidentHouseModel.getResidentBouseByParam(queryMap);
    }
}