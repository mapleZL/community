package com.phkj.service.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.environmental.StAppletEnvironment;
import com.phkj.entity.system.SystemAdmin;

import java.util.Map;

public interface EnvironmentalService {

    boolean add(StAppletEnvironment stAppletEnviron);

    Map<String, Object> getAll(String id, Integer pageNum, Integer pageSize, String villageCode);

    StAppletEnvironment getEnvironDetail(String id);

    boolean addComment(Comment comment);

    PageInfo<StAppletEnvironment> getSystemAllEnviron(String status, String sts, String type, String page, String rows, String villageCode);

    boolean delete(String id, SystemAdmin adminUser);

    boolean update(String id, SystemAdmin adminUser, String type);

    boolean wechatExamined(String id, String userName, String userId);
}
