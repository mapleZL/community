package com.phkj.service.repair;


import com.phkj.core.ServiceResult;
import com.phkj.entity.repair.PublishInformationInfo;

public interface IPublishInformationInfoService {

	/**
     * 根据id取得publish_information_info对象
     * @param  publishInformationInfoId
     * @return
     */
    ServiceResult<PublishInformationInfo> getPublishInformationInfoById(Long publishInformationInfoId);
    
    /**
     * 保存publish_information_info对象
     * @param  publishInformationInfo
     * @return
     */
     ServiceResult<Integer> savePublishInformationInfo(PublishInformationInfo publishInformationInfo);
     
     /**
     * 更新publish_information_info对象
     * @param  publishInformationInfo
     * @return
     */
     ServiceResult<Integer> updatePublishInformationInfo(PublishInformationInfo publishInformationInfo);
}