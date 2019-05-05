package com.ejavashop.service.member;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberHouse;

public interface IMemberHouseService {

	/**
     * 根据id取得member_house对象
     * @param  memberHouseId
     * @return
     */
    ServiceResult<MemberHouse> getMemberHouseById(Integer memberHouseId);
    
    /**
     * 保存member_house对象
     * @param  memberHouse
     * @return
     */
     ServiceResult<Integer> saveMemberHouse(MemberHouse memberHouse);
     
     /**
     * 更新member_house对象
     * @param  memberHouse
     * @return
     */
     ServiceResult<Integer> updateMemberHouse(MemberHouse memberHouse);
}