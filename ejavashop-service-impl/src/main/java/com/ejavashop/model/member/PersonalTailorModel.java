package com.ejavashop.model.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.member.PersonalTailorReadDao;
import com.ejavashop.dao.shop.write.member.PersonalTailorWriteDao;
import com.ejavashop.entity.member.PersonalTailor;

@Component
public class PersonalTailorModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(PersonalTailorModel.class);
    
    @Resource
    private PersonalTailorWriteDao personalTailorWriteDao;
    @Resource
    private PersonalTailorReadDao personalTailorReadDao;
    
    /**
     * 根据id取得personal_tailor对象
     * @param  personalTailorId
     * @return
     */
    public PersonalTailor getPersonalTailorById(Integer personalTailorId) {
    	return personalTailorReadDao.get(personalTailorId);
    }
    
    /**
     * 保存personal_tailor对象
     * @param  personalTailor
     * @return
     */
     public Integer savePersonalTailor(PersonalTailor personalTailor) {
     	this.dbConstrains(personalTailor);
     	return personalTailorWriteDao.insert(personalTailor);
     }
     
     /**
     * 更新personal_tailor对象
     * @param  personalTailor
     * @return
     */
     public Integer updatePersonalTailor(PersonalTailor personalTailor) {
     	this.dbConstrains(personalTailor);
     	return personalTailorWriteDao.update(personalTailor);
     }
     
     private void dbConstrains(PersonalTailor personalTailor) {
		personalTailor.setName(StringUtil.dbSafeString(personalTailor.getName(), false, 20));
		personalTailor.setMobile(StringUtil.dbSafeString(personalTailor.getMobile(), false, 20));
		personalTailor.setQq(StringUtil.dbSafeString(personalTailor.getQq(), true, 20));
		personalTailor.setWeixin(StringUtil.dbSafeString(personalTailor.getWeixin(), true, 50));
     }

    public int pageCount(Map<String, Object> param) {
        return personalTailorReadDao.getCount(param);
    }

    public List<PersonalTailor> page(Map<String, Object> param) {
        return personalTailorReadDao.page(param);
    }

    public void deleteTailor(Integer id) {
        personalTailorWriteDao.deleteTailor(id);
    }
}