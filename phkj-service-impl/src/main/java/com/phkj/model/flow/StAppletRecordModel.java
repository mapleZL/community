package com.phkj.model.flow;


import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.flow.StAppletRecordDao;
import com.phkj.entity.flow.StAppletRecord;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

@Component
public class StAppletRecordModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(StAppletRecordModel.class);
    
    @Resource
    private StAppletRecordDao stAppletRecordDao;
    
    /**
     * 根据id取得st_applet_record对象
     * @param  stAppletRecordId
     * @return
     */
    public StAppletRecord getStAppletRecordById(Long stAppletRecordId) {
    	return stAppletRecordDao.get(stAppletRecordId);
    }
    
    /**
     * 保存st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     public Integer saveStAppletRecord(StAppletRecord stAppletRecord) {
     	this.dbConstrains(stAppletRecord);
         stAppletRecord.setCreateTime(new Date());
         stAppletRecord.setSts(1);
     	return stAppletRecordDao.insert(stAppletRecord);
     }
     
     /**
     * 更新st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     public Integer updateStAppletRecord(StAppletRecord stAppletRecord) {
     	this.dbConstrains(stAppletRecord);
     	return stAppletRecordDao.update(stAppletRecord);
     }
     
     private void dbConstrains(StAppletRecord stAppletRecord) {
		stAppletRecord.setRId(StringUtil.dbSafeString(stAppletRecord.getRId(), true, 50));
		stAppletRecord.setType(StringUtil.dbSafeString(stAppletRecord.getType(), true, 20));
		stAppletRecord.setCreateUserName(StringUtil.dbSafeString(stAppletRecord.getCreateUserName(), true, 50));
		stAppletRecord.setCreateUserId(StringUtil.dbSafeString(stAppletRecord.getCreateUserId(), true, 50));
		stAppletRecord.setRemark(StringUtil.dbSafeString(stAppletRecord.getRemark(), true, 255));
     }
}