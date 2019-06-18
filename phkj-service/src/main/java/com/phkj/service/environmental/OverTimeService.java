package com.phkj.service.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.system.SystemAdmin;

public interface OverTimeService {

    PageInfo<StAppletOverTime> getSystemAllOverTime(Integer page, Integer rows,String type);

    boolean add(StAppletOverTime stAppletOverTime, SystemAdmin adminUser);

    StAppletOverTime getSystemDetail(Integer id);

    boolean update(String id, String time);

    boolean delete(String id);

}
