package com.phkj.service.member;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.member.MemberParkingLot;

/**
 * @Filename: IMemberParkingLotService.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
public interface IMemberParkingLotService {

    /**
     * 根据id取得member_parking_lot对象
     *
     * @param memberParkingLotId
     * @return
     */
    ServiceResult<MemberParkingLot> getMemberParkingLotById(Integer memberParkingLotId);

    /**
     * 保存member_parking_lot对象
     *
     * @param memberParkingLot
     * @return
     */
    ServiceResult<Integer> saveMemberParkingLot(MemberParkingLot memberParkingLot);

    /**
     * 更新member_parking_lot对象
     *
     * @param memberParkingLot
     * @return
     */
    ServiceResult<Integer> updateMemberParkingLot(MemberParkingLot memberParkingLot);

    /**
     * 分页查询
     *
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MemberParkingLot>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 审核车位状态
     *
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> changeStatus(Integer id, int state);

    /**
     * 获取我的车位
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    ServiceResult<List<MemberParkingLot>> getMyMemberLotList(Integer memberId, Integer villageId, int pageNum, int pageSize);
}