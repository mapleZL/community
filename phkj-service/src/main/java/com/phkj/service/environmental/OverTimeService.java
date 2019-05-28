package com.phkj.service.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.environmental.StAppletOverTime;

public interface OverTimeService {

    PageInfo<StAppletOverTime> getSystemAllOverTime(Integer page, Integer rows);

    boolean add(StAppletOverTime stAppletOverTime);

    StAppletOverTime getSystemDetail(Integer id);

    boolean update(String id, String time);
}
