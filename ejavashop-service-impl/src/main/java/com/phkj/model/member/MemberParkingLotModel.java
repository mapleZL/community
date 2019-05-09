package com.phkj.model.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.member.MemberParkingLotReadDao;
import com.phkj.dao.shop.write.member.MemberParkingLotWriteDao;
import com.phkj.entity.member.MemberParkingLot;
/**
 *                       
 * @Filename: MemberParkingLotModel.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Component
public class MemberParkingLotModel {

    @Resource
    private MemberParkingLotReadDao  memberParkingLotReadDao;
    @Resource
    private MemberParkingLotWriteDao memberParkingLotWriteDao;

    /**
     * 根据id取得member_parking_lot对象
     * @param  memberParkingLotId
     * @return
     */
    public MemberParkingLot getMemberParkingLotById(Integer memberParkingLotId) {
        return memberParkingLotReadDao.get(memberParkingLotId);
    }

    /**
     * 保存member_parking_lot对象
     * @param  memberParkingLot
     * @return
     */
    public Integer saveMemberParkingLot(MemberParkingLot memberParkingLot) {
        return memberParkingLotWriteDao.insert(memberParkingLot);
    }

    /**
    * 更新member_parking_lot对象
    * @param  memberParkingLot
    * @return
    */
    public Integer updateMemberParkingLot(MemberParkingLot memberParkingLot) {
        return memberParkingLotWriteDao.update(memberParkingLot);
    }

    /**
     * 获取总数量
     * @param queryMap
     * @return
     */
    public int getMemberParkingCount(Map<String, String> queryMap) {
        return memberParkingLotReadDao.getMemberParkingCount(queryMap);
    }

    /**
     * 获取查询列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<MemberParkingLot> getMemberParkingList(Map<String, String> queryMap, Integer start,
                                                       Integer size) {
        return memberParkingLotReadDao.getMemberParkingList(queryMap, start, size);
    }

    /**
     * 更新车位锁状态
     * @param id
     * @param state
     * @return
     */
    public Boolean changeStatus(Integer id, int state) {
        return memberParkingLotWriteDao.updateStatus(id, state);
    }

}