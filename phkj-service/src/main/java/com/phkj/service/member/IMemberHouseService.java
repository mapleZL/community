package com.phkj.service.member;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.member.MemberHouse;

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

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MemberHouse>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 修改状态
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> changeStatus(Integer id, int state);

    /**
     * 根据手机号查询
     * @param createUserId
     * @return
     */
    ServiceResult<List<MemberHouse>> getAllHouse(String createUserId, String villageCode);
}