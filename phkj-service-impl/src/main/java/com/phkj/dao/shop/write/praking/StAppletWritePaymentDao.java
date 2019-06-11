package com.phkj.dao.shop.write.praking;

import com.phkj.entity.praking.StAppletPayment;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletWritePaymentDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletPayment record);

    int insertSelective(StAppletPayment record);

    StAppletPayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletPayment record);

    int updateByPrimaryKey(StAppletPayment record);
}