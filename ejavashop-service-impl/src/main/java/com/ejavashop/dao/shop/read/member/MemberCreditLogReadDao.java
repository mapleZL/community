package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCreditLog;

@Repository
public interface MemberCreditLogReadDao {

    MemberCreditLog get(java.lang.Integer id);

    Integer getCount(Map<String, Object> queryMap);

    List<MemberCreditLog> page(Map<String, Object> queryMap);

    List<MemberCreditLog> getAllMemberCreditLog(Map<String, String> queryMap);

}