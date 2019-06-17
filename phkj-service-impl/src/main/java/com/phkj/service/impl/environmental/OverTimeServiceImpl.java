package com.phkj.service.impl.environmental;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.environmental.StAppletOverTimeReadMapper;
import com.phkj.dao.shop.write.environmental.StAppletOverTimeWriteMapper;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.environmental.OverTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OverTimeServiceImpl implements OverTimeService {

    @Autowired
    StAppletOverTimeReadMapper stAppletOverTimeReadMapper;

    @Autowired
    StAppletOverTimeWriteMapper stAppletOverTimeWriteMapper;

    /**
     * @param page
     * @param rows
     * @return
     */
    @Override
    public PageInfo<StAppletOverTime> getSystemAllOverTime(Integer page, Integer rows, String type) {

        int pageNum = (page == 0) ? 1 : page;
        int pageSize = (rows == 0) ? 20 : rows;
        PageInfo<StAppletOverTime> pageInfo = PageHelper.startPage(page, rows).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletOverTimeReadMapper.selectAllOverTime(type);
            }
        });
        return pageInfo;
    }

    /**
     * @param stAppletOverTime
     * @param adminUser
     * @return
     */
    @Override
    public boolean add(StAppletOverTime stAppletOverTime, SystemAdmin adminUser) {
        stAppletOverTime.setCreateName(adminUser.getName());
        stAppletOverTime.setCreateId(adminUser.getId().toString());
        stAppletOverTime.setSts("0");
        stAppletOverTime.setType("2");
        int insert = stAppletOverTimeWriteMapper.insert(stAppletOverTime);
        if (insert > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StAppletOverTime getSystemDetail(Integer id) {

        StAppletOverTime stAppletOverTime = stAppletOverTimeReadMapper.selectByPrimaryKey(Long.valueOf(id));
        return stAppletOverTime;
    }

    /**
     * @param id
     * @param time
     * @return
     */
    @Override
    public boolean update(String id, String time) {
        int i = stAppletOverTimeWriteMapper.updateTimeById(id, time);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id) {

        StAppletOverTime stAppletOverTime = stAppletOverTimeReadMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null == stAppletOverTime) {
            return false;
        }

        stAppletOverTime.setSts("3");
        int i = stAppletOverTimeWriteMapper.updateByPrimaryKeySelective(stAppletOverTime);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
