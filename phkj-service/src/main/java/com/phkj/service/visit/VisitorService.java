package com.phkj.service.visit;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.entity.visit.StAppletVisitor;

import java.util.Map;

public interface VisitorService {

    Map<String,Object> addVisit(StAppletVisitor visitor);

    Map<String,Object> getVisitTime(String villageCode);

    PageInfo<StAppletVisitor> getAllVisitor(String sts, String type, String page, String rows, String villageCode);

    boolean delete(String id, SystemAdmin adminUser);
}
