package com.ejavashop.dao.shop.write.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.PersonalTailor;

@Repository
public interface PersonalTailorWriteDao {
 
	//PersonalTailor get(Integer id);
	
	Integer insert(PersonalTailor personalTailor);
	
	Integer update(PersonalTailor personalTailor);

   // PersonalTailor getPersonalTailor();

    //Integer getCount(Map<String, Object> queryMap);

  //  List<PersonalTailor> page(Map<String, Object> queryMap);

    Integer deleteTailor(Integer id);
}