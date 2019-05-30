package com.phkj.service.member;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.member.MemberCar;
import com.phkj.entity.repair.StAppletComment;

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

    /**
     * 修改状态
     *
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> changeStatus(Integer id, int state);

    /**
     * 获取车辆列表
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResult<List<MemberCar>> getMyMemberCarList(Integer memberId, Integer villageCode, int pageNum, int pageSize);
}