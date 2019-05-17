package com.phkj.dao.shop.write.member;

import com.phkj.entity.member.StAppletPet;
import com.phkj.entity.member.StAppletPetWithBLOBs;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StAppletPetWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletPetWithBLOBs record);

    int insertSelective(StAppletPetWithBLOBs record);

    StAppletPetWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletPetWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(StAppletPetWithBLOBs record);

    int updateByPrimaryKey(StAppletPet record);

    List<Map<String,Object>> selectMePetListByPage(@Param("userId") String userId);
}