package com.phkj.service.share;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ShareService {

    Map<String, Object> getMeShareInfo(String userId, Integer pageNum, Integer pageSize);

    boolean deleteShareInfo(String id);
}
