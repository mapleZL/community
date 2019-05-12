package com.phkj.dao.shop.read.repaire;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.repair.StAppletRepairMember;
/**
 * 
 *                       
 * @Filename: StAppletRepairMemberReadDao.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Repository
public interface StAppletRepairMemberReadDao {
 
	StAppletRepairMember get(Integer id);

    int getRepaitMemberCount(@Param("queryMap") Map<String, String> queryMap);

    List<StAppletRepairMember> getRepaitMemberList(@Param("start") Integer start, @Param("size") Integer size,
                                                  @Param("queryMap") Map<String, String> queryMap);

    List<StAppletRepairMember> getUseRepairMemberList();
	
}