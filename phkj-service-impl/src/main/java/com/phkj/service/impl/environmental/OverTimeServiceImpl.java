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

import java.util.List;

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
     * @param adminUser
     * @return
     */
    @Override
    public PageInfo<StAppletOverTime> getSystemAllOverTime(Integer page, Integer rows, String type, SystemAdmin adminUser) {

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
     * @param adminUser
     * @return
     */
    @Override
    public boolean update(String id, String time, SystemAdmin adminUser) {
        int i = stAppletOverTimeWriteMapper.updateTimeById(id, time);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(String id, String type) {

        StAppletOverTime stAppletOverTime = stAppletOverTimeReadMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null == stAppletOverTime) {
            return false;
        }
        if ("0".equals(type)) {
            stAppletOverTime.setSts("0");
        } else if ("1".equals(type)) {
            stAppletOverTime.setSts("1");
            // 查询所有启用数据
            List<StAppletOverTime> list = stAppletOverTimeReadMapper.getAllEnableTimeByType(id);
            if (null != list && list.size() > 0) {
                for (StAppletOverTime appletOverTime : list) {
                    appletOverTime.setSts("0");
                    stAppletOverTimeWriteMapper.updateByPrimaryKeySelective(appletOverTime);
                }
            }
        } else {
            stAppletOverTime.setSts("3");
        }
        int i = stAppletOverTimeWriteMapper.updateByPrimaryKeySelective(stAppletOverTime);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
