package com.ejavashop.service.member;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.PersonalTailorPicture;

public interface IPersonalTailorPictureService {

	/**
     * 根据id取得personal_tailor_picture对象
     * @param  personalTailorPictureId
     * @return
     */
    ServiceResult<PersonalTailorPicture> getPersonalTailorPictureById(Integer personalTailorPictureId);
    
    /**
     * 保存personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     */
     ServiceResult<Integer> savePersonalTailorPicture(PersonalTailorPicture personalTailorPicture);
     
     /**
     * 更新personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     */
     ServiceResult<Integer> updatePersonalTailorPicture(PersonalTailorPicture personalTailorPicture);

     public void delete(Integer tailor_id);
}