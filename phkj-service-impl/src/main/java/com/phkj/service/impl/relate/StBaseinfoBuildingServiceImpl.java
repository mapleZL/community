package com.phkj.service.impl.relate;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoBuilding;
import com.phkj.model.relate.StBaseinfoBuildingModel;
import com.phkj.service.relate.IStBaseinfoBuildingService;

@Service(value = "stBaseinfoBuildingService")
public class StBaseinfoBuildingServiceImpl implements IStBaseinfoBuildingService {
	private static Logger      log = LogManager.getLogger(StBaseinfoBuildingServiceImpl.class);
	
	@Resource
	private StBaseinfoBuildingModel stBaseinfoBuildingModel;
    
     /**
     * 根据id取得楼幢信息
     * @param  stBaseinfoBuildingId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoBuilding> getStBaseinfoBuildingById(Long stBaseinfoBuildingId) {
        ServiceResult<StBaseinfoBuilding> result = new ServiceResult<StBaseinfoBuilding>();
        try {
            result.setResult(stBaseinfoBuildingModel.getStBaseinfoBuildingById(stBaseinfoBuildingId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoBuildingService][getStBaseinfoBuildingById]根据id["+stBaseinfoBuildingId+"]取得楼幢信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoBuildingService][getStBaseinfoBuildingById]根据id["+stBaseinfoBuildingId+"]取得楼幢信息时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存楼幢信息
     * @param  stBaseinfoBuilding
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoBuildingModel.saveStBaseinfoBuilding(stBaseinfoBuilding));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoBuildingService][saveStBaseinfoBuilding]保存楼幢信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoBuildingService][saveStBaseinfoBuilding]保存楼幢信息时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新楼幢信息
     * @param  stBaseinfoBuilding
     * @return
     * @see com.phkj.service.StBaseinfoBuildingService#updateStBaseinfoBuilding(StBaseinfoBuilding)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoBuilding(StBaseinfoBuilding stBaseinfoBuilding) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoBuildingModel.updateStBaseinfoBuilding(stBaseinfoBuilding));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoBuildingService][updateStBaseinfoBuilding]更新楼幢信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoBuildingService][updateStBaseinfoBuilding]更新楼幢信息时出现未知异常：",
                e);
        }
        return result;
     }
}