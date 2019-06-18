package com.phkj.service.impl.complaint;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.complaint.StAppletComSuggesReadDao;
import com.phkj.dao.shop.read.flow.StAppletRecordDao;
import com.phkj.dao.shop.write.complaint.StAppletComSuggesWriteDao;
import com.phkj.entity.complaint.StAppletComSugges;
import com.phkj.entity.flow.StAppletRecord;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.complaint.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ComplaintServiceImpl implements ComplaintService {

    @Autowired
    StAppletComSuggesReadDao stAppletComSuggesReadDao;

    @Autowired
    StAppletComSuggesWriteDao stAppletComSuggesWriteDao;

    @Autowired
    StAppletRecordDao stAppletRecordDao;

    @Override
    public boolean addComorSuggess(StAppletComSugges stAppletComSugges) {
        stAppletComSugges.setSts("0"); // 未审核
        stAppletComSugges.setCreateTime(new Date());
        int i = stAppletComSuggesWriteDao.insert(stAppletComSugges);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<StAppletComSugges> getAllComAndSugg(Integer page, Integer rows, String type, String sts, String villageCode) {
        PageInfo<StAppletComSugges> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletComSuggesReadDao.selectAllComAndSugg(type,sts,villageCode);
            }
        });
        return pageInfo;
    }


    @Override
    public boolean updateComAndSuggess(String id, String type, SystemAdmin adminUser) {

        StAppletComSugges comSugges = stAppletComSuggesReadDao.selectByPrimaryKey(Long.valueOf(id));
        if ("0".equals(type)) {
            comSugges.setSts("2");
        } else {
            comSugges.setSts("1");
        }
        comSugges.setModifyTime(new Date());
        comSugges.setModifyUserId(String.valueOf(adminUser.getId()));
        comSugges.setModifyUserName(adminUser.getName());

        int i = stAppletComSuggesWriteDao.updateByPrimaryKey(comSugges);
        if (i > 0) {
            // 生成流水表
            StAppletRecord record = new StAppletRecord();
            record.setRId(comSugges.getId().toString());
            record.setCreateTime(new Date());
            record.setCreateUserId(comSugges.getCreateUserId());
            record.setType("complaint");
            record.setCreateUserName(adminUser.getName());
            record.setSts(1);
            record.setRemark("审核意见反馈 , 审核结果" + comSugges.getSts());
            stAppletRecordDao.insert(record);
            return true;
        }
        return false;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public StAppletComSugges getDetail(String id) {
        StAppletComSugges stAppletComSugges = stAppletComSuggesReadDao.selectByPrimaryKey(Long.valueOf(id));
        return stAppletComSugges;
    }


    @Override
    public Map<String, Object> getAllMeComplaint(Integer pageNum, Integer pageSize, String type, String userId, String villageCode) {

        int num = (pageNum == 0) ? 1 : pageNum;
        int size = (pageSize == 0) ? 20 : pageSize;
        PageInfo<StAppletComSugges> pageInfo = PageHelper.startPage(num, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletComSuggesReadDao.selectAllMeComplaint(type, userId,villageCode);
            }
        });
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("total", pageInfo.getTotal());
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }


}
