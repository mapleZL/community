package com.ejavashop.model.member;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.shop.read.member.MemberHouseReadDao;
import com.ejavashop.dao.shop.write.member.MemberHouseWriteDao;
import com.ejavashop.entity.member.MemberHouse;


@Component
public class MemberHouseModel {
    
    @Resource
    private MemberHouseReadDao memberHouseReadDao;
    
    @Resource
    private MemberHouseWriteDao memberHouseWriteDao;
    
    /**
     * 根据id取得member_house对象
     * @param  memberHouseId
     * @return
     */
    public MemberHouse getMemberHouseById(Integer memberHouseId) {
    	return memberHouseReadDao.get(memberHouseId);
    }
    
    /**
     * 保存member_house对象
     * @param  memberHouse
     * @return
     */
     public Integer saveMemberHouse(MemberHouse memberHouse) {
     	return memberHouseWriteDao.insert(memberHouse);
     }
     
     /**
     * 更新member_house对象
     * @param  memberHouse
     * @return
     */
     public Integer updateMemberHouse(MemberHouse memberHouse) {
     	return memberHouseWriteDao.update(memberHouse);
     }

    public int getMemberHouseCount(Map<String, String> queryMap) {
        return memberHouseReadDao.getMemberHouseCount(queryMap);
    }

    public List<MemberHouse> getMemberHouseList(Map<String, String> queryMap, Integer start,
                                                Integer size) {
        
        return memberHouseReadDao.getMemberHouseList(queryMap, start, size);
    }
     
}