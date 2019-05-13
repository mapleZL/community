package com.phkj.service.share;

import com.phkj.entity.share.StAppletShareInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ShareService {

    Map<String, Object> getMeShareInfo(String userId, String taskType, Integer pageNum, Integer pageSize);

    boolean deleteShareInfo(String id);

    Map<String, Object> getAllShareInfo(String taskType, Integer pageNum, Integer pageSize);


    StAppletShareInfo getShareInfoDetail(String id);

    boolean addShareInfo(StAppletShareInfo stAppletShareInfo);

    boolean applyShareInfo(String id, String userId, String userName, String telePhone, String address ,String IDCard);

    Map<String,Object> getShareInfoList(String taskType, String status, Integer pageNum, Integer pageSize);

    Map<String,Object> getShareDetail(String id);

    Map<String,Object> getComShareInfoList(Integer userId, String taskType, String status, Integer page, Integer rows);

    boolean createShareInfo(StAppletShareInfo shareInfo);

    boolean stopShareInfo(String id);

    Map<String,Object> getMeApplyInfoList(String status, String userId ,Integer pageNum ,Integer pageSize);

    Map<String,Object> getAllApplyByPage(Integer page, Integer rows, String infoId);
}
