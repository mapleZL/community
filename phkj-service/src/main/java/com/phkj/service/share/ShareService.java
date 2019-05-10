package com.phkj.service.share;

import com.phkj.entity.share.StAppletShareInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ShareService {

    Map<String, Object> getMeShareInfo(String userId, Integer pageNum, Integer pageSize);

    boolean deleteShareInfo(String id);

    Map<String, Object> getAllShareInfo(String taskType, Integer pageNum, Integer pageSize);


    StAppletShareInfo getShareInfoDetail(String id);
}
