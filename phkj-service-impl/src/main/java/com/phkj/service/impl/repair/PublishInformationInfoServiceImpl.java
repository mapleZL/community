package com.phkj.service.impl.repair;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.repair.PublishInformationInfo;
import com.phkj.model.repair.PublishInformationInfoModel;
import com.phkj.service.repair.IPublishInformationInfoService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "publishInformationInfoService")
public class PublishInformationInfoServiceImpl implements IPublishInformationInfoService {
	private static Logger      log = LogManager.getLogger(PublishInformationInfoServiceImpl.class);
	
	@Resource
	private PublishInformationInfoModel publishInformationInfoModel;
    
     /**
     * 根据id取得publish_information_info对象
     * @param  publishInformationInfoId
     * @return
     */
    @Override
    public ServiceResult<PublishInformationInfo> getPublishInformationInfoById(Long publishInformationInfoId) {
        ServiceResult<PublishInformationInfo> result = new ServiceResult<PublishInformationInfo>();
        try {
            result.setResult(publishInformationInfoModel.getPublishInformationInfoById(publishInformationInfoId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPublishInformationInfoService][getPublishInformationInfoById]根据id["+publishInformationInfoId+"]取得publish_information_info对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPublishInformationInfoService][getPublishInformationInfoById]根据id["+publishInformationInfoId+"]取得publish_information_info对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存publish_information_info对象
     * @param  publishInformationInfo
     * @return
     */
     @Override
     public ServiceResult<Integer> savePublishInformationInfo(PublishInformationInfo publishInformationInfo) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(publishInformationInfoModel.savePublishInformationInfo(publishInformationInfo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPublishInformationInfoService][savePublishInformationInfo]保存publish_information_info对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPublishInformationInfoService][savePublishInformationInfo]保存publish_information_info对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新publish_information_info对象
     * @param  publishInformationInfo
     * @return
     * @see com.phkj.service.PublishInformationInfoService#updatePublishInformationInfo(PublishInformationInfo)
     */
     @Override
     public ServiceResult<Integer> updatePublishInformationInfo(PublishInformationInfo publishInformationInfo) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(publishInformationInfoModel.updatePublishInformationInfo(publishInformationInfo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPublishInformationInfoService][updatePublishInformationInfo]更新publish_information_info对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPublishInformationInfoService][updatePublishInformationInfo]更新publish_information_info对象时出现未知异常：",
                e);
        }
        return result;
     }
}