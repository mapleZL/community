package com.phkj.service.impl.property;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.property.StBaseinfoDepartment;
import com.phkj.model.property.StBaseinfoDepartmentModel;
import com.phkj.service.property.IStBaseinfoDepartmentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "stBaseinfoDepartmentService")
public class StBaseinfoDepartmentServiceImpl implements IStBaseinfoDepartmentService {
	private static Logger      log = LogManager.getLogger(StBaseinfoDepartmentServiceImpl.class);
	
	@Resource
	private StBaseinfoDepartmentModel stBaseinfoDepartmentModel;
    
     /**
     * 根据id取得部门维护表
     * @param  stBaseinfoDepartmentId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoDepartment> getStBaseinfoDepartmentById(Integer stBaseinfoDepartmentId) {
        ServiceResult<StBaseinfoDepartment> result = new ServiceResult<StBaseinfoDepartment>();
        try {
            result.setResult(stBaseinfoDepartmentModel.getStBaseinfoDepartmentById(stBaseinfoDepartmentId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoDepartmentService][getStBaseinfoDepartmentById]根据id["+stBaseinfoDepartmentId+"]取得部门维护表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoDepartmentService][getStBaseinfoDepartmentById]根据id["+stBaseinfoDepartmentId+"]取得部门维护表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存部门维护表
     * @param  stBaseinfoDepartment
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoDepartmentModel.saveStBaseinfoDepartment(stBaseinfoDepartment));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoDepartmentService][saveStBaseinfoDepartment]保存部门维护表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoDepartmentService][saveStBaseinfoDepartment]保存部门维护表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新部门维护表
     * @param  stBaseinfoDepartment
     * @return
     * @see com.ejavashop.service.StBaseinfoDepartmentService#updateStBaseinfoDepartment(StBaseinfoDepartment)
     */
     @Override
     public ServiceResult<Integer> updateStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoDepartmentModel.updateStBaseinfoDepartment(stBaseinfoDepartment));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoDepartmentService][updateStBaseinfoDepartment]更新部门维护表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoDepartmentService][updateStBaseinfoDepartment]更新部门维护表时出现未知异常：",
                e);
        }
        return result;
     }
}