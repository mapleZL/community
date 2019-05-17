package com.phkj.model.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.member.MemberHouseReadDao;
import com.phkj.dao.shop.write.member.MemberHouseWriteDao;
import com.phkj.entity.member.MemberHouse;

@Component
public class MemberHouseModel {

    @Resource
    private MemberHouseReadDao  memberHouseReadDao;

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

    /**
     * 获取数量
     * @param queryMap
     * @return
     */
    public int getMemberHouseCount(Map<String, String> queryMap) {
        return memberHouseReadDao.getMemberHouseCount(queryMap);
    }

    /**
     * 获取列表查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<MemberHouse> getMemberHouseList(Map<String, String> queryMap, Integer start,
                                                Integer size) {

        return memberHouseReadDao.getMemberHouseList(queryMap, start, size);
    }

    /**
     * 修改状态值
     * @param id
     * @param state
     * @return
     */
    public Boolean changeStatus(Integer id, int state) {
        return memberHouseWriteDao.changeState(id, state);
    }

    public List<MemberHouse> getAllHouse(String memberId) {
        return memberHouseReadDao.getAllHouse(memberId);
    }

}