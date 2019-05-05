package com.ejavashop.model.member;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.member.PersonalTailorPictureReadDao;
import com.ejavashop.dao.shop.write.member.PersonalTailorPictureWriteDao;
import com.ejavashop.entity.member.PersonalTailorPicture;

@Component
public class PersonalTailorPictureModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(PersonalTailorPictureModel.class);
    
    @Resource
    private PersonalTailorPictureWriteDao personalTailorPictureWriteDao;
    @Resource
    private PersonalTailorPictureReadDao personalTailorPictureReadDao;
    
    /**
     * 根据id取得personal_tailor_picture对象
     * @param  personalTailorPictureId
     * @return
     */
    public PersonalTailorPicture getPersonalTailorPictureById(Integer personalTailorPictureId) {
    	return personalTailorPictureReadDao.get(personalTailorPictureId);
    }
    
    /**
     * 保存personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     */
     public Integer savePersonalTailorPicture(PersonalTailorPicture personalTailorPicture) {
     	this.dbConstrains(personalTailorPicture);
     	return personalTailorPictureWriteDao.insert(personalTailorPicture);
     }
     
     /**
     * 更新personal_tailor_picture对象
     * @param  personalTailorPicture
     * @return
     */
     public Integer updatePersonalTailorPicture(PersonalTailorPicture personalTailorPicture) {
     	this.dbConstrains(personalTailorPicture);
     	return personalTailorPictureWriteDao.update(personalTailorPicture);
     }
     
     private void dbConstrains(PersonalTailorPicture personalTailorPicture) {
		personalTailorPicture.setTailorDescription(StringUtil.dbSafeString(personalTailorPicture.getTailorDescription(), true, 300));
     }

    public void delete(Integer tailor_id) {
        personalTailorPictureWriteDao.delete(tailor_id);
    }
}