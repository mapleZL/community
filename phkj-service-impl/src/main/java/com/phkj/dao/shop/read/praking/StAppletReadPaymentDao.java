package com.phkj.dao.shop.read.praking;

import com.phkj.entity.praking.StAppletParking;
import com.phkj.entity.praking.StAppletPayment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletReadPaymentDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletPayment record);

    int insertSelective(StAppletPayment record);

    StAppletPayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletPayment record);

    int updateByPrimaryKey(StAppletPayment record);

    List<StAppletPayment> selectAllPayment(@Param("villageCode") String villageCode, @Param("type") String type,
                                           @Param("sts") String sts);

    List<StAppletPayment> selectByType(@Param("type") String paymentType, @Param("id") String id);

    StAppletPayment selectByPaymentType(@Param("villageCode") String villageCode, @Param("type") String type);
}