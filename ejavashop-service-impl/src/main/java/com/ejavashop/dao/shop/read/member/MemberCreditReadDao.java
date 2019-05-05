package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCredit;

@Repository
public interface MemberCreditReadDao {

    MemberCredit get(java.lang.Integer id);

    Integer getCount(Map<String, Object> queryMap);

    List<MemberCredit> page(Map<String, Object> queryMap);

    MemberCredit getByMemberId(Integer memberId);

    List<MemberCredit> getAllMemberCredit(Map<String, Object> param);

}