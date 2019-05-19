package com.phkj.service.impl.complaint;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.complaint.StAppletComSuggesReadDao;
import com.phkj.dao.shop.write.complaint.StAppletComSuggesWriteDao;
import com.phkj.entity.complaint.StAppletComSugges;
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
    public PageInfo<StAppletComSugges> getAllComAndSugg(Integer page, Integer rows, String type) {
        PageInfo<StAppletComSugges> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletComSuggesReadDao.selectAllComAndSugg(type);
            }
        });
        return pageInfo;
    }


    @Override
    public Map<String, Object> getAllMeComplaint(Integer pageNum, Integer pageSize, String type, String userId) {

        int num = (pageNum == 0) ? 1 : pageNum;
        int size = (pageSize == 0) ? 20 : pageSize;
        PageInfo<StAppletComSugges> pageInfo = PageHelper.startPage(num, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletComSuggesReadDao.selectAllMeComplaint(type, userId);
            }
        });
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("total", pageInfo.getTotal());
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }


}
