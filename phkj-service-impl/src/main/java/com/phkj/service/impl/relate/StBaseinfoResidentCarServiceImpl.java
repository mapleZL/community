package com.phkj.service.impl.relate;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoResidentCar;
import com.phkj.model.relate.StBaseinfoResidentCarModel;
import com.phkj.service.relate.IStBaseinfoResidentCarService;

@Service(value = "stBaseinfoResidentCarService")
public class StBaseinfoResidentCarServiceImpl implements IStBaseinfoResidentCarService {
	private static Logger      log = LogManager.getLogger(StBaseinfoResidentCarServiceImpl.class);
	
	@Resource
	private StBaseinfoResidentCarModel stBaseinfoResidentCarModel;
    
     /**
     * 根据id取得人员车辆登记表
     * @param  stBaseinfoResidentCarId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoResidentCar> getStBaseinfoResidentCarById(Long stBaseinfoResidentCarId) {
        ServiceResult<StBaseinfoResidentCar> result = new ServiceResult<StBaseinfoResidentCar>();
        try {
            result.setResult(stBaseinfoResidentCarModel.getStBaseinfoResidentCarById(stBaseinfoResidentCarId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarService][getStBaseinfoResidentCarById]根据id["+stBaseinfoResidentCarId+"]取得人员车辆登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarService][getStBaseinfoResidentCarById]根据id["+stBaseinfoResidentCarId+"]取得人员车辆登记表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentCarModel.saveStBaseinfoResidentCar(stBaseinfoResidentCar));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarService][saveStBaseinfoResidentCar]保存人员车辆登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarService][saveStBaseinfoResidentCar]保存人员车辆登记表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新人员车辆登记表
     * @param  stBaseinfoResidentCar
     * @return
     * @see com.phkj.service.StBaseinfoResidentCarService#updateStBaseinfoResidentCar(StBaseinfoResidentCar)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoResidentCar(StBaseinfoResidentCar stBaseinfoResidentCar) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoResidentCarModel.updateStBaseinfoResidentCar(stBaseinfoResidentCar));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentCarService][updateStBaseinfoResidentCar]更新人员车辆登记表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentCarService][updateStBaseinfoResidentCar]更新人员车辆登记表时出现未知异常：",
                e);
        }
        return result;
     }
}