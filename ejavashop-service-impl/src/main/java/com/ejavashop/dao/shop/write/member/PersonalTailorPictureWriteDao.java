package com.ejavashop.dao.shop.write.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.PersonalTailorPicture;


@Repository
public interface PersonalTailorPictureWriteDao {
 
	//PersonalTailorPicture get(Integer id);
	
	Integer insert(PersonalTailorPicture personalTailorPicture);
	
	Integer update(PersonalTailorPicture personalTailorPicture);

    void delete(Integer personalTailorId);
}