package com.phkj.model.repair;


import com.phkj.core.StringUtil;
import com.phkj.dao.index.read.repair.PublishInformationInfoDao;
import com.phkj.entity.repair.PublishInformationInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PublishInformationInfoModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(PublishInformationInfoModel.class);
    
    @Resource
    private PublishInformationInfoDao publishInformationInfoDao;
    
    /**
     * 根据id取得publish_information_info对象
     * @param  publishInformationInfoId
     * @return
     */
    public PublishInformationInfo getPublishInformationInfoById(Long publishInformationInfoId) {
    	return publishInformationInfoDao.get(publishInformationInfoId);
    }
    
    /**
     * 保存publish_information_info对象
     * @param  publishInformationInfo
     * @return
     */
     public Integer savePublishInformationInfo(PublishInformationInfo publishInformationInfo) {
     	this.dbConstrains(publishInformationInfo);
     	return publishInformationInfoDao.insert(publishInformationInfo);
     }
     
     /**
     * 更新publish_information_info对象
     * @param  publishInformationInfo
     * @return
     */
     public Integer updatePublishInformationInfo(PublishInformationInfo publishInformationInfo) {
     	this.dbConstrains(publishInformationInfo);
     	return publishInformationInfoDao.update(publishInformationInfo);
     }
     
     private void dbConstrains(PublishInformationInfo publishInformationInfo) {
		publishInformationInfo.setName(StringUtil.dbSafeString(publishInformationInfo.getName(), true, 50));
		publishInformationInfo.setUserId(StringUtil.dbSafeString(publishInformationInfo.getUserId(), true, 30));
		publishInformationInfo.setUserName(StringUtil.dbSafeString(publishInformationInfo.getUserName(), true, 30));
		publishInformationInfo.setExamineId(StringUtil.dbSafeString(publishInformationInfo.getExamineId(), true, 255));
		publishInformationInfo.setRepairId(StringUtil.dbSafeString(publishInformationInfo.getRepairId(), true, 30));
		publishInformationInfo.setType(StringUtil.dbSafeString(publishInformationInfo.getType(), true, 20));
		publishInformationInfo.setTitle(StringUtil.dbSafeString(publishInformationInfo.getTitle(), true, 50));
		publishInformationInfo.setTelPhone(StringUtil.dbSafeString(publishInformationInfo.getTelPhone(), true, 11));
		publishInformationInfo.setDetail(StringUtil.dbSafeString(publishInformationInfo.getDetail(), true, 255));
		publishInformationInfo.setImg(StringUtil.dbSafeString(publishInformationInfo.getImg(), true, 255));
     }
}