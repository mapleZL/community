package com.ejavashop.dao.shop.write.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCredit;

@Repository
public interface MemberCreditWriteDao {

    //MemberCredit get(java.lang.Integer id);

    Integer save(MemberCredit memberCredit);

    Integer update(MemberCredit memberCredit);

    //Integer getCount(Map<String, Object> queryMap);

    //List<MemberCredit> page(Map<String, Object> queryMap);

    //MemberCredit getByMemberId(Integer memberId);

    Integer del(Integer id);

}