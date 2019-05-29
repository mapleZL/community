package com.phkj.service.environmental;

import com.phkj.entity.environmental.StAppletEnviron;

import java.util.Map;

public interface EnvironmentalService {

    boolean add(StAppletEnviron stAppletEnviron);

    Map<String, Object> getAll(String id, Integer pageNum, Integer pageSize);
}
