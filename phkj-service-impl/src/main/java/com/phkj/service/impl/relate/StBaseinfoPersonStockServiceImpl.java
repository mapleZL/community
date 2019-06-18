package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoPersonStock;
import com.phkj.model.relate.StBaseinfoPersonStockModel;
import com.phkj.service.relate.IStBaseinfoPersonStockService;

@Service(value = "stBaseinfoPersonStockService")
public class StBaseinfoPersonStockServiceImpl implements IStBaseinfoPersonStockService {
	private static Logger      log = LogManager.getLogger(StBaseinfoPersonStockServiceImpl.class);
	
	@Resource
	private StBaseinfoPersonStockModel stBaseinfoPersonStockModel;
    
     /**
     * 根据id取得人员库
     * @param  stBaseinfoPersonStockId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoPersonStock> getStBaseinfoPersonStockById(Long stBaseinfoPersonStockId) {
        ServiceResult<StBaseinfoPersonStock> result = new ServiceResult<StBaseinfoPersonStock>();
        try {
            result.setResult(stBaseinfoPersonStockModel.getStBaseinfoPersonStockById(stBaseinfoPersonStockId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoPersonStockService][getStBaseinfoPersonStockById]根据id["+stBaseinfoPersonStockId+"]取得人员库时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoPersonStockService][getStBaseinfoPersonStockById]根据id["+stBaseinfoPersonStockId+"]取得人员库时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存人员库
     * @param  stBaseinfoPersonStock
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoPersonStockModel.saveStBaseinfoPersonStock(stBaseinfoPersonStock));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoPersonStockService][saveStBaseinfoPersonStock]保存人员库时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoPersonStockService][saveStBaseinfoPersonStock]保存人员库时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新人员库
     * @param  stBaseinfoPersonStock
     * @return
     * @see com.phkj.service.StBaseinfoPersonStockService#updateStBaseinfoPersonStock(StBaseinfoPersonStock)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoPersonStockModel.updateStBaseinfoPersonStock(stBaseinfoPersonStock));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoPersonStockService][updateStBaseinfoPersonStock]更新人员库时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoPersonStockService][updateStBaseinfoPersonStock]更新人员库时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public StBaseinfoPersonStock getStBaseinfoPersonStock(String phone) {
        return stBaseinfoPersonStockModel.getStBaseinfoPersonStock(phone);
    }

    @Override
    public StBaseinfoPersonStock getStBaseinfoPerson(List<Long> personStockIds, String name) {
        return stBaseinfoPersonStockModel.getStBaseinfoPerson(personStockIds, name);
    }
}