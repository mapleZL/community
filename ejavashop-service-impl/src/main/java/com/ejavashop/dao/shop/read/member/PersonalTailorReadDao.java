package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.PersonalTailor;

@Repository
public interface PersonalTailorReadDao {
 
	PersonalTailor get(Integer id);
	
    PersonalTailor getPersonalTailor();

    Integer getCount(Map<String, Object> queryMap);

    List<PersonalTailor> page(Map<String, Object> queryMap);

}