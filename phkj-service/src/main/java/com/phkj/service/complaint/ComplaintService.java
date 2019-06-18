package com.phkj.service.complaint;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.complaint.StAppletComSugges;
import com.phkj.entity.system.SystemAdmin;

import java.util.Map;

public interface ComplaintService {

    Map<String,Object> getAllMeComplaint(Integer pageNum, Integer pageSize, String type, String userId, String villageCode);

    boolean addComorSuggess(StAppletComSugges stAppletComSugges);

    PageInfo<StAppletComSugges> getAllComAndSugg(Integer page, Integer rows, String type, String sts, String villageCode);

    boolean updateComAndSuggess(String id, String type, SystemAdmin adminUser);

    StAppletComSugges getDetail(String id);
}
