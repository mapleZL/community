package com.ejavashop.dao.shop.read.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberGradeConfig;

/**
 * 会员等级配置表
 * 
 * @Filename: MemberGradeConfigWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberGradeConfigReadDao {

    MemberGradeConfig get(java.lang.Integer id);

}
