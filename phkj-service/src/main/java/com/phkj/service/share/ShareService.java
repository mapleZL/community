package com.phkj.service.share;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShareService {

    List<Object> getMeShareInfo(String userId, Integer pageNum, Integer pageSize);
}
