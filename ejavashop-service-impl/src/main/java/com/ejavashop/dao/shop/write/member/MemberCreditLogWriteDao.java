package com.ejavashop.dao.shop.write.member;


import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberCreditLog;

@Repository
public interface MemberCreditLogWriteDao {

   // MemberCreditLog get(java.lang.Integer id);

    Integer save(MemberCreditLog memberCreditLog);

    Integer update(MemberCreditLog memberCreditLog);

   // Integer getCount(Map<String, Object> queryMap);

    //List<MemberCreditLog> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}