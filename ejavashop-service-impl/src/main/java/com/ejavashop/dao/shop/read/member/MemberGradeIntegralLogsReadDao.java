package com.ejavashop.dao.shop.read.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberGradeIntegralLogs;

/**
 * 会员经验积分日志表
 * 
 * @Filename: MemberGradeIntegralLogsWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberGradeIntegralLogsReadDao {

    MemberGradeIntegralLogs get(java.lang.Integer id);


    /**
     * 根据会员ID和类型获取会员经验值积分值变更日志
     * @param memberId
     * @param type
     * @param start
     * @param size
     * @return
     */
    List<MemberGradeIntegralLogs> getMemberGradeIntegralLogs(@Param("memberId") Integer memberId,
                                                             @Param("type") Integer type,
                                                             @Param("start") Integer start,
                                                             @Param("size") Integer size);

    /**
     * 根据会员ID和类型获取会员经验值积分值变更日志数量
     * @param memberId
     * @param type
     * @return
     */
    Integer getMemberGradeIntegralLogsCount(@Param("memberId") Integer memberId,
                                            @Param("type") Integer type);
}
