package com.phkj.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.member.Member;

/**
 * 会员表
 *
 * @Filename: MemberReadDao.java
 * @Version: 1.0
 * @Author: 王方
 */
@Repository
public interface MemberReadDao {

    Member get(java.lang.Integer id);


    Member getByMemberId(@Param("memberId") Integer memberId);

    /**
     * 根据条件获取用户信息
     *
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<Member> getMembers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据条件获取用户数量
     *
     * @param queryMap
     * @return
     */
    Integer getMembersCount(@Param("queryMap") Map<String, String> queryMap);


    List<Member> getParterTuijianByMemberId1(@Param("memberId") Integer memberId, @Param("memberTuijianId") String memberTuijianId);

    List<Member> getParterTuijianByMemberId(@Param("memberId") Integer memberId);

    /**
     * 根据用户名和密码获取用户
     *
     * @param name
     * @param password
     * @return
     */
    List<Member> getByNameAndPwd(@Param("name") String name, @Param("password") String password);

    /**
     * 根据手机号和密码获取用户
     *
     * @param phone
     * @param password
     * @return
     */
    List<Member> getByPhoneAndPwd(@Param("phone") String phone, @Param("password") String password);

    /**
     * 根据会员name获取会员
     *
     * @param name
     * @return
     */
    List<Member> getByName(@Param("name") String name);

    /**
     * 根据会员phone获取会员
     *
     * @param phone
     * @return
     */
    Member getByPhone(@Param("phone") String phone);

    Member getMemberBySellerId(@Param("sellerId") Integer sellerId);

}
