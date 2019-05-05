package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.PersonalTailor;


public interface IPersonalTailorService {

	/**
     * 根据id取得personal_tailor对象
     * @param  personalTailorId
     * @return
     */
    ServiceResult<PersonalTailor> getPersonalTailorById(Integer personalTailorId);
    
    /**
     * 保存personal_tailor对象
     * @param  personalTailor
     * @return
     */
     ServiceResult<Integer> savePersonalTailor(PersonalTailor personalTailor);
     
     /**
     * 更新personal_tailor对象
     * @param  personalTailor
     * @return
     */
     ServiceResult<Integer> updatePersonalTailor(PersonalTailor personalTailor);


     ServiceResult<List<PersonalTailor>> page(Map<String, String> queryMap, PagerInfo pager);

     public void deleteTailor(Integer id);
}