package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.model.relate.StBaseinfoParkingLotModel;
import com.phkj.service.relate.IStBaseinfoParkingLotService;

@Service(value = "stBaseinfoParkingLotService")
public class StBaseinfoParkingLotServiceImpl implements IStBaseinfoParkingLotService {
	private static Logger      log = LogManager.getLogger(StBaseinfoParkingLotServiceImpl.class);
	
	@Resource
	private StBaseinfoParkingLotModel stBaseinfoParkingLotModel;
    
     /**
     * 根据id取得车位信息
     * @param  stBaseinfoParkingLotId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoParkingLot> getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId) {
        ServiceResult<StBaseinfoParkingLot> result = new ServiceResult<StBaseinfoParkingLot>();
        try {
            result.setResult(stBaseinfoParkingLotModel.getStBaseinfoParkingLotById(stBaseinfoParkingLotId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getStBaseinfoParkingLotById]根据id["+stBaseinfoParkingLotId+"]取得车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getStBaseinfoParkingLotById]根据id["+stBaseinfoParkingLotId+"]取得车位信息时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存车位信息
     * @param  stBaseinfoParkingLot
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoParkingLotModel.saveStBaseinfoParkingLot(stBaseinfoParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][saveStBaseinfoParkingLot]保存车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][saveStBaseinfoParkingLot]保存车位信息时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新车位信息
     * @param  stBaseinfoParkingLot
     * @return
     * @see com.phkj.service.StBaseinfoParkingLotService#updateStBaseinfoParkingLot(StBaseinfoParkingLot)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoParkingLotModel.updateStBaseinfoParkingLot(stBaseinfoParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][updateStBaseinfoParkingLot]更新车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][updateStBaseinfoParkingLot]更新车位信息时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<StBaseinfoParkingLot>> getRelatedParkingLot(Long residentinfoId) {
        ServiceResult<List<StBaseinfoParkingLot>> result = new ServiceResult<List<StBaseinfoParkingLot>>();
        try {
            result.setResult(stBaseinfoParkingLotModel.getRelatedParkingLot(residentinfoId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getRelatedParkingLot]根据id["+residentinfoId+"]取得车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getRelatedParkingLot]根据id["+residentinfoId+"]取得车位信息时出现未知异常：",
                e);
        }
        return result;
    }
}