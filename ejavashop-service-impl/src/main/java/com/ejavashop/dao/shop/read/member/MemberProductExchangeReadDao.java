package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberProductExchange;

/**
 * 换货申请
 *                       
 * @Filename: MemberProductExchangeReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberProductExchangeReadDao {

    MemberProductExchange get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberProductExchange> queryList(Map<String, Object> map);
    
    Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<MemberProductExchange> page(@Param("param1") Map<String, String> queryMap,
                                     @Param("start") Integer start, @Param("size") Integer size);

    List<MemberProductExchange> list();

}
