package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.SystemAppfile;
import com.phkj.model.relate.SystemAppfileModel;
import com.phkj.service.relate.ISystemAppfileService;

@Service(value = "systemAppfileService")
public class SystemAppfileServiceImpl implements ISystemAppfileService {
    private static Logger      log = LogManager.getLogger(SystemAppfileServiceImpl.class);

    @Resource
    private SystemAppfileModel systemAppfileModel;

    /**
    * 根据id取得system_appfile对象
    * @param  systemAppfileId
    * @return
    */
    @Override
    public ServiceResult<SystemAppfile> getSystemAppfileById(Long systemAppfileId) {
        ServiceResult<SystemAppfile> result = new ServiceResult<SystemAppfile>();
        try {
            result.setResult(systemAppfileModel.getSystemAppfileById(systemAppfileId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemAppfileService][getSystemAppfileById]根据id[" + systemAppfileId
                      + "]取得system_appfile对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemAppfileService][getSystemAppfileById]根据id[" + systemAppfileId
                      + "]取得system_appfile对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存system_appfile对象
     * @param  systemAppfile
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSystemAppfile(SystemAppfile systemAppfile) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemAppfileModel.saveSystemAppfile(systemAppfile));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemAppfileService][saveSystemAppfile]保存system_appfile对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemAppfileService][saveSystemAppfile]保存system_appfile对象时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新system_appfile对象
    * @param  systemAppfile
    * @return
    * @see com.phkj.service.SystemAppfileService#updateSystemAppfile(SystemAppfile)
    */
    @Override
    public ServiceResult<Integer> updateSystemAppfile(SystemAppfile systemAppfile) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemAppfileModel.updateSystemAppfile(systemAppfile));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemAppfileService][updateSystemAppfile]更新system_appfile对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemAppfileService][updateSystemAppfile]更新system_appfile对象时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 根据类型获取图片
     * @param string
     * @param id
     * @param type
     * @return
     * @see com.phkj.service.relate.ISystemAppfileService#getPicList(java.lang.String, java.lang.Long, java.lang.String)
     */
    @Override
    public ServiceResult<List<SystemAppfile>> getPicList(String module, Long id, String type) {
        ServiceResult<List<SystemAppfile>> result = new ServiceResult<List<SystemAppfile>>();
        try {
            result.setResult(systemAppfileModel.getPicList(module, id, type));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemAppfileService][getPicList]根据module[" + module
                      + "]取得system_appfile对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemAppfileService][getPicList]根据module[" + module
                      + "]取得system_appfile对象时出现未知异常：",
                e);
        }
        return result;
    }
}