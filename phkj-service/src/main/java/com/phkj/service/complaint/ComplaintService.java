package com.phkj.service.complaint;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.complaint.StAppletComSugges;

import java.util.Map;

public interface ComplaintService {

    Map<String,Object> getAllMeComplaint(Integer pageNum, Integer pageSize, String type,String userId);

    boolean addComorSuggess(StAppletComSugges stAppletComSugges);

    PageInfo<StAppletComSugges> getAllComAndSugg(Integer page, Integer rows,String type);
}
