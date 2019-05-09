package com.phkj.service.impl.share;

import com.baomidou.mybatisplus.plugins.pagination.PageHelper;
import com.phkj.dao.share.StAppletShareInfoMapper;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareServiceImpl implements ShareService {

    /**
     *
     */
    @Autowired
    private StAppletShareInfoMapper stAppletShareInfoMapper;


    @Override
    public List<Object> getMeShareInfo(String userId, Integer pageNum, Integer pageSize) {

        // 根据当前倒叙查询列表
        PageHelper.startPage(pageNum,pageSize);
        List<StAppletShareInfo> list = stAppletShareInfoMapper.selectByUserId(userId);
        return null;
    }
}
