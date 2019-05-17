package com.phkj.service.impl.member;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.flow.StAppletRecordDao;
import com.phkj.dao.shop.read.member.StAppletPetReadDao;
import com.phkj.dao.shop.write.member.StAppletPetWriteDao;
import com.phkj.entity.flow.StAppletRecord;
import com.phkj.entity.member.StAppletPet;
import com.phkj.entity.member.StAppletPetWithBLOBs;
import com.phkj.entity.repair.StAppletComment;
import com.phkj.service.member.IMemberPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MemberPetServiceImpl implements IMemberPetService {

    @Autowired
    StAppletPetReadDao stAppletPetReadDao;
    @Autowired
    StAppletPetWriteDao stAppletPetWriteDao;
    @Autowired
    StAppletRecordDao stAppletRecordDao;


    /*(
     * del
     */
    @Override
    public boolean delPet(String id, String userId, String userName) {
        StAppletPetWithBLOBs pet = stAppletPetWriteDao.selectByPrimaryKey(Long.valueOf(id));
        pet.setSts("3");
        pet.setModifyUserId(userId);
        pet.setModifyTime(new Date());
        pet.setModifyUserId(userId);
        int i = stAppletPetWriteDao.updateByPrimaryKeyWithBLOBs(pet);
        if (i > 0) {
            // 生成流水表
            StAppletRecord record = new StAppletRecord();
            record.setRId(pet.getId().toString());
            record.setCreateTime(new Date());
            record.setCreateUserId(pet.getCreateUserId());
            record.setType("pet");
            record.setCreateUserName(pet.getPetRegisNum());
            record.setSts(1);
            record.setRemark("申请删除宠物登记");
            stAppletRecordDao.insert(record);
            return true;
        }
        return false;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public boolean updatePet(StAppletPetWithBLOBs pet) {

        pet.setModifyUserId(pet.getCreateUserId());
        pet.setModifyTime(new Date());
        int i = stAppletPetWriteDao.updateByPrimaryKeySelective(pet);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param pet
     * @return
     */
    @Override
    public boolean addPet(StAppletPetWithBLOBs pet) {
        pet.setSts("0");  // 申请中状态
        int i = stAppletPetWriteDao.insert(pet);
        if (i > 0) {
            // 生成流水表
            StAppletRecord record = new StAppletRecord();
            record.setRId(pet.getId().toString());
            record.setCreateTime(new Date());
            record.setCreateUserId(pet.getCreateUserId());
            record.setType("pet");
            record.setCreateUserName(pet.getPetName());
            record.setSts(1);
            record.setRemark("申请登记宠物");
            stAppletRecordDao.insert(record);
            return true;
        }
        return false;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public StAppletPet getPetDetail(String id) {
        StAppletPetWithBLOBs stAppletPetWithBLOBs = stAppletPetReadDao.selectByPrimaryKey(Long.valueOf(id));
        return stAppletPetWithBLOBs;
    }


    /**
     * @param userId
     * @param sts
     * @return
     */
    @Override
    public PageInfo<Map<String, Object>> getMePetListByPage(String userId, String sts, Integer pageNum,
                                                            Integer pageSize) {
        int num = (pageNum == 0) ? 1 : pageNum;
        int size = (pageSize == 0) ? 20 : pageSize;
        // 分页工具
        PageInfo<Map<String, Object>> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                List<Map<String, Object>> list = stAppletPetReadDao.selectMePetListByPage(userId);
            }
        });
        return pageInfo;
    }


}
