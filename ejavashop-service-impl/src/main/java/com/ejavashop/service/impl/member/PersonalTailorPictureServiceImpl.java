package com.ejavashop.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.PersonalTailorPicture;
import com.ejavashop.model.member.PersonalTailorPictureModel;
import com.ejavashop.service.member.IPersonalTailorPictureService;


@Service(value = "personalTailorPictureService")
public class PersonalTailorPictureServiceImpl implements IPersonalTailorPictureService {
	private static Logger      log = LogManager.getLogger(PersonalTailorPictureServiceImpl.class);
	
	@Resource
	private PersonalTailorPictureModel personalTailorPictureModel;
    
     /**
     * 根据id取得personal_tailor_picture对象
     * @param  personalTailorPictureId
     * @return
     */
    @Override
    public ServiceResult<PersonalTailorPicture> getPersonalTailorPictureById(Integer personalTailorPictureId) {
        ServiceResult<PersonalTailorPicture> result = new ServiceResult<PersonalTailorPicture>();
        try {
            result.setResult(personalTailorPictureModel.getPersonalTailorPictureById(personalTailorPictureId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorPictureService][getPersonalTailorPictureById]根据id["+personalTailorPictureId+"]取得personal_tailor_picture对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorPictureService][getPersonalTailorPictureById]根据id["+personalTailorPictureId+"]取得personal_tailor_picture对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     */
     @Override
     public ServiceResult<Integer> savePersonalTailorPicture(PersonalTailorPicture personalTailorPicture) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(personalTailorPictureModel.savePersonalTailorPicture(personalTailorPicture));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorPictureService][savePersonalTailorPicture]保存personal_tailor_picture对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorPictureService][savePersonalTailorPicture]保存personal_tailor_picture对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     * @see com.ejavashop.service.PersonalTailorPictureService#updatePersonalTailorPicture(PersonalTailorPicture)
     */
     @Override
     public ServiceResult<Integer> updatePersonalTailorPicture(PersonalTailorPicture personalTailorPicture) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(personalTailorPictureModel.updatePersonalTailorPicture(personalTailorPicture));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPersonalTailorPictureService][updatePersonalTailorPicture]更新personal_tailor_picture对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPersonalTailorPictureService][updatePersonalTailorPicture]更新personal_tailor_picture对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public void delete(Integer tailor_id) {
        personalTailorPictureModel.delete(tailor_id);
    }


}