package com.ejavashop.service.member;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberCar;

public interface IMemberCarService {

    /**
     * 根据id取得member_car对象
     * @param  memberCarId
     * @return
     */
    ServiceResult<MemberCar> getMemberCarById(Integer memberCarId);

    /**
     * 保存member_car对象
     * @param  memberCar
     * @return
     */
     ServiceResult<Integer> saveMemberCar(MemberCar memberCar);

    /**
     * 更新member_car对象
     * @param  memberCar
     * @return
     */
     ServiceResult<Integer> updateMemberCar(MemberCar memberCar);
}