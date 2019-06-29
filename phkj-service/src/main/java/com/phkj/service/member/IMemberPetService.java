package com.phkj.service.member;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.member.StAppletPet;
import com.phkj.entity.member.StAppletPetWithBLOBs;

import java.util.Map;

public interface IMemberPetService {

    PageInfo<Map<String, Object>> getMePetListByPage(String userId, String sts, Integer pageNum, Integer pageSize, String villageCode);

    StAppletPet getPetDetail(String id);

    boolean addPet(StAppletPetWithBLOBs pet);

    boolean delPet(String id,String userId, String userName);

    boolean updatePet(StAppletPetWithBLOBs pet);

    PageInfo<StAppletPetWithBLOBs> getAllPetList(Integer page, Integer rows, String sts, String villageCode);

    boolean systemUpdatePet(String id, String type, Integer userId, String name, String msg);
}
