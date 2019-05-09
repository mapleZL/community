package com.phkj.model.repair;


import com.phkj.core.StringUtil;
import com.phkj.dao.index.read.repair.StAppletRepairDao;
import com.phkj.entity.repair.StAppletRepair;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class StAppletRepairModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(StAppletRepairModel.class);
    
    @Resource
    private StAppletRepairDao stAppletRepairDao;
    
    /**
     * 根据id取得st_applet_repair对象
     * @param  stAppletRepairId
     * @return
     */
    public StAppletRepair getStAppletRepairById(Integer stAppletRepairId) {
    	return stAppletRepairDao.get(stAppletRepairId);
    }
    
    /**
     * 保存st_applet_repair对象
     * @param  stAppletRepair
     * @return
     */
     public Integer saveStAppletRepair(StAppletRepair stAppletRepair) {
     	this.dbConstrains(stAppletRepair);
     	return stAppletRepairDao.insert(stAppletRepair);
     }
     
     /**
     * 更新st_applet_repair对象
     * @param  stAppletRepair
     * @return
     */
     public Integer updateStAppletRepair(StAppletRepair stAppletRepair) {
     	this.dbConstrains(stAppletRepair);
     	return stAppletRepairDao.update(stAppletRepair);
     }
     
     private void dbConstrains(StAppletRepair stAppletRepair) {
		stAppletRepair.setCommunityName(StringUtil.dbSafeString(stAppletRepair.getCommunityName(), true, 255));
		stAppletRepair.setHouseName(StringUtil.dbSafeString(stAppletRepair.getHouseName(), true, 50));
		stAppletRepair.setCreateUserId(StringUtil.dbSafeString(stAppletRepair.getCreateUserId(), true, 30));
		stAppletRepair.setUserName(StringUtil.dbSafeString(stAppletRepair.getUserName(), true, 30));
		stAppletRepair.setExamineId(StringUtil.dbSafeString(stAppletRepair.getExamineId(), true, 255));
		stAppletRepair.setRepairId(StringUtil.dbSafeString(stAppletRepair.getRepairId(), true, 30));
		stAppletRepair.setType(StringUtil.dbSafeString(stAppletRepair.getType(), true, 20));
		stAppletRepair.setTitle(StringUtil.dbSafeString(stAppletRepair.getTitle(), true, 50));
		stAppletRepair.setTelPhone(StringUtil.dbSafeString(stAppletRepair.getTelPhone(), true, 11));
		stAppletRepair.setDetail(StringUtil.dbSafeString(stAppletRepair.getDetail(), true, 255));
		stAppletRepair.setImg(StringUtil.dbSafeString(stAppletRepair.getImg(), true, 255));
     }
}