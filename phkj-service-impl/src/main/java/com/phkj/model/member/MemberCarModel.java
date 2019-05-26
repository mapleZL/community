package com.phkj.model.member;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.member.MemberCarDao;
import com.phkj.entity.member.MemberCar;

@Component
public class MemberCarModel {

    @Resource
    private MemberCarDao memberCarDao;

    /**
     * 根据id取得member_car对象
     *
     * @param memberCarId
     * @return
     */
    public MemberCar getMemberCarById(Integer memberCarId) {
        return memberCarDao.get(memberCarId);
    }

    /**
     * 保存member_car对象
     *
     * @param memberCar
     * @return
     */
    public Integer saveMemberCar(MemberCar memberCar) {
        this.dbConstrains(memberCar);
        return memberCarDao.insert(memberCar);
    }

    /**
     * 更新member_car对象
     *
     * @param memberCar
     * @return
     */
    public Integer updateMemberCar(MemberCar memberCar) {
        this.dbConstrains(memberCar);
        return memberCarDao.update(memberCar);
    }

    private void dbConstrains(MemberCar memberCar) {
        memberCar.setVehicleType(StringUtil.dbSafeString(memberCar.getVehicleType(), true, 64));
        memberCar.setVehicleStructure(StringUtil.dbSafeString(memberCar.getVehicleStructure(), true, 64));
        memberCar.setVehicleNumber(StringUtil.dbSafeString(memberCar.getVehicleNumber(), true, 20));
    }

    public int getMemberCarCount(Map<String, String> queryMap) {
        return memberCarDao.getMemberCarCount(queryMap);
    }

    public List<MemberCar> getMemberCarList(Map<String, String> queryMap, Integer start, Integer size) {
        return memberCarDao.getMemberCarList(queryMap, start, size);
    }

    /**
     * 修改状态值
     *
     * @param id
     * @param state
     * @return
     */
    public Boolean changeStatus(Integer id, int state) {
        return memberCarDao.changeState(id, state);
    }

    /**
     * create by: zl
     * description: 我的车辆列表列表
     * create time:
     *
     * @return
     * @Param: memberId
     * @Param: pageNum
     * @Param: pageSize
     */
    public List<MemberCar> getMyMemberCarList(Integer memberId, Integer villageId, int pageNum, int pageSize) {
        return memberCarDao.getMyMemberCarList(memberId, villageId, pageNum, pageSize);
    }
}