package com.ejavashop.service.member;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberCar;
import com.ejavashop.entity.member.MemberCreditLog;

import java.util.List;
import java.util.Map;

public interface IMemberCarService {

    /**
     * 根据id取得member_car对象
     *
     * @param memberCarId
     * @return
     */
    ServiceResult<MemberCar> getMemberCarById(Integer memberCarId);

    /**
     * 保存member_car对象
     *
     * @param memberCar
     * @return
     */
    ServiceResult<Integer> saveMemberCar(MemberCar memberCar);

    /**
     * 更新member_car对象
     *
     * @param memberCar
     * @return
     */
    ServiceResult<Integer> updateMemberCar(MemberCar memberCar);

    /**
     * create by: zl
     * description: 获取我的车辆列表
     * create time:
     *
     * @return
     * @Param: queryMap
     * @Param: pager
     */
    ServiceResult<List<MemberCar>> page(Map<String, String> queryMap, PagerInfo pager);
}