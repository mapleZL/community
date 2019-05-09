package com.phkj.model.system;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.system.SystemLogsReadDao;
import com.phkj.dao.shop.write.system.SystemLogsWriteDao;
import com.phkj.entity.system.SystemLogs;

@Component
public class SystemLogsModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SystemLogsModel.class);
    
    @Resource
    private SystemLogsReadDao systemLogsReadDao;
    @Resource
    private SystemLogsWriteDao systemLogsWriteDao;
    
    /**
     * 根据id取得系统操作日志表
     * @param  systemLogsId
     * @return
     */
    public SystemLogs getSystemLogsById(Integer systemLogsId) {
    	return systemLogsReadDao.get(systemLogsId);
    }
    
    /**
     * 保存系统操作日志表
     * @param  systemLogs
     * @return
     */
     public Integer saveSystemLogs(SystemLogs systemLogs) {
     	this.dbConstrains(systemLogs);
     	return systemLogsWriteDao.insert(systemLogs);
     }
     
     /**
     * 更新系统操作日志表
     * @param  systemLogs
     * @return
     */
     public Integer updateSystemLogs(SystemLogs systemLogs) {
     	this.dbConstrains(systemLogs);
     	return systemLogsWriteDao.update(systemLogs);
     }
     
     private void dbConstrains(SystemLogs systemLogs) {
		systemLogs.setOptName(StringUtil.dbSafeString(systemLogs.getOptName(), false, 50));
		systemLogs.setOptContent(StringUtil.dbSafeString(systemLogs.getOptContent(), false, 255));
		systemLogs.setOptObject(StringUtil.dbSafeString(systemLogs.getOptObject(), false, 255));
     }
}