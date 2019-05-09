package com.phkj.service.impl.system;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.system.SystemLogs;
import com.phkj.model.system.SystemLogsModel;
import com.phkj.service.system.ISystemLogsService;

@Service(value = "systemLogsService")
public class SystemLogsServiceImpl implements ISystemLogsService {
	private static Logger      log = LogManager.getLogger(SystemLogsServiceImpl.class);
	
	@Resource
	private SystemLogsModel systemLogsModel;
    
     /**
     * 根据id取得系统操作日志表
     * @param  systemLogsId
     * @return
     */
    @Override
    public ServiceResult<SystemLogs> getSystemLogsById(Integer systemLogsId) {
        ServiceResult<SystemLogs> result = new ServiceResult<SystemLogs>();
        try {
            result.setResult(systemLogsModel.getSystemLogsById(systemLogsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemLogsService][getSystemLogsById]根据id["+systemLogsId+"]取得系统操作日志表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemLogsService][getSystemLogsById]根据id["+systemLogsId+"]取得系统操作日志表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存系统操作日志表
     * @param  systemLogs
     * @return
     */
     @Override
     public ServiceResult<Integer> saveSystemLogs(SystemLogs systemLogs) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemLogsModel.saveSystemLogs(systemLogs));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemLogsService][saveSystemLogs]保存系统操作日志表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemLogsService][saveSystemLogs]保存系统操作日志表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
      * 保存系统操作日志表
      * @param  systemLogs
      * @return
      */
      @Override
      public ServiceResult<Integer> saveSystemLogs(int optId, String optName, String optContent, int optObjectId, String optObject, String optType, String optOther) {
         ServiceResult<Integer> result = new ServiceResult<Integer>();
         try {
             SystemLogs systemLogs = new SystemLogs();
             systemLogs.setOptId(optId);
             systemLogs.setOptName(optName);
             systemLogs.setOptObjectId(optObjectId);
             systemLogs.setOptObject(optObject);
             systemLogs.setOptContent(optContent);
             systemLogs.setOptType(optType);
             systemLogs.setOptOther(optOther);
             systemLogs.setCreateTime(new Date());
             result.setResult(systemLogsModel.saveSystemLogs(systemLogs));
         } catch (BusinessException e) {
             result.setSuccess(false);
             result.setMessage(e.getMessage());
             log.error("[ISystemLogsService][saveSystemLogs]保存系统操作日志表时出现未知异常：" + e.getMessage());
         } catch (Exception e) {
             result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
             log.error("[ISystemLogsService][saveSystemLogs]保存系统操作日志表时出现未知异常：",
                 e);
         }
         return result;
      }
     
     /**
     * 更新系统操作日志表
     * @param  systemLogs
     * @return
     * @see com.phkj.service.SystemLogsService#updateSystemLogs(SystemLogs)
     */
     @Override
     public ServiceResult<Integer> updateSystemLogs(SystemLogs systemLogs) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemLogsModel.updateSystemLogs(systemLogs));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISystemLogsService][updateSystemLogs]更新系统操作日志表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISystemLogsService][updateSystemLogs]更新系统操作日志表时出现未知异常：",
                e);
        }
        return result;
     }
}