package com.phkj.dao.shop.write.visit;

import com.phkj.entity.visit.StAppletVisitor;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletWriteVisitDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletVisitor record);

    int insertSelective(StAppletVisitor record);

    StAppletVisitor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletVisitor record);

    int updateByPrimaryKey(StAppletVisitor record);
}