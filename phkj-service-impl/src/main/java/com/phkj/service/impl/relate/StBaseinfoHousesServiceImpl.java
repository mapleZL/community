package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoHouses;
import com.phkj.model.relate.StBaseinfoHousesModel;
import com.phkj.service.relate.IStBaseinfoHousesService;

@Service(value = "stBaseinfoHousesService")
public class StBaseinfoHousesServiceImpl implements IStBaseinfoHousesService {
	private static Logger      log = LogManager.getLogger(StBaseinfoHousesServiceImpl.class);
	
	@Resource
	private StBaseinfoHousesModel stBaseinfoHousesModel;
    
     /**
     * 根据id取得房屋信息
     * @param  stBaseinfoHousesId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoHouses> getStBaseinfoHousesById(Long stBaseinfoHousesId) {
        ServiceResult<StBaseinfoHouses> result = new ServiceResult<StBaseinfoHouses>();
        try {
            result.setResult(stBaseinfoHousesModel.getStBaseinfoHousesById(stBaseinfoHousesId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoHousesService][getStBaseinfoHousesById]根据id["+stBaseinfoHousesId+"]取得房屋信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoHousesService][getStBaseinfoHousesById]根据id["+stBaseinfoHousesId+"]取得房屋信息时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存房屋信息
     * @param  stBaseinfoHouses
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoHousesModel.saveStBaseinfoHouses(stBaseinfoHouses));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoHousesService][saveStBaseinfoHouses]保存房屋信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoHousesService][saveStBaseinfoHouses]保存房屋信息时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新房屋信息
     * @param  stBaseinfoHouses
     * @return
     * @see com.phkj.service.StBaseinfoHousesService#updateStBaseinfoHouses(StBaseinfoHouses)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoHouses(StBaseinfoHouses stBaseinfoHouses) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoHousesModel.updateStBaseinfoHouses(stBaseinfoHouses));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoHousesService][updateStBaseinfoHouses]更新房屋信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoHousesService][updateStBaseinfoHouses]更新房屋信息时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public List<StBaseinfoHouses> getHouseSpriner(Integer buildingId, String unitId) {
        return stBaseinfoHousesModel.getHouseSpriner(buildingId, unitId);
    }
}