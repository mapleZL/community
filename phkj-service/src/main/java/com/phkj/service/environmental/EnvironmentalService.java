package com.phkj.service.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.environmental.StAppletEnviron;
import com.phkj.entity.environmental.StAppletEnvironment;

import java.util.Map;

public interface EnvironmentalService {

    boolean add(StAppletEnvironment stAppletEnviron);

    Map<String, Object> getAll(String id, Integer pageNum, Integer pageSize);

    StAppletEnvironment getEnvironDetail(String id);

    boolean addComment(Comment comment);

    PageInfo<StAppletEnvironment> getSystemAllEnviron(String status, String sts, String type, String page, String rows, String villageCode);
}
