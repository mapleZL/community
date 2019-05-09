package com.phkj.service.system;

import com.phkj.core.ServiceResult;
import com.phkj.entity.system.SystemLogs;

public interface ISystemLogsService {

	/**
     * 根据id取得系统操作日志表
     * @param  systemLogsId
     * @return
     */
    ServiceResult<SystemLogs> getSystemLogsById(Integer systemLogsId);
    
    /**
     * 保存系统操作日志表
     * @param  systemLogs
     * @return
     */
     ServiceResult<Integer> saveSystemLogs(SystemLogs systemLogs);
     
     /**
     * 更新系统操作日志表
     * @param  systemLogs
     * @return
     */
     ServiceResult<Integer> updateSystemLogs(SystemLogs systemLogs);
     
     /**
      * 
      * @param optId        操作人ID
      * @param optName      操作人姓名
      * @param optContent   操作内容
      * @param optObjectId  操作表ID
      * @param optObject    操作表
      * @param optType      操作类型 insert、update、delete
      * @param optOther     其他内容
      * @return
      */
     ServiceResult<Integer> saveSystemLogs(int optId, String optName, String optContent, int optObjectId, String optObject, String optType, String optOther);
}