package com.ejavashop.dao.shop.read.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.PersonalTailorPicture;


@Repository
public interface PersonalTailorPictureReadDao {
 
	PersonalTailorPicture get(Integer id);
	
}