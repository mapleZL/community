package com.phkj.service.impl.share;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.share.StAppletShareInfoMapper;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShareServiceImpl implements ShareService {

    /**
     *
     */
    @Autowired
    private StAppletShareInfoMapper stAppletShareInfoMapper;


    @Override
    public Map<String, Object> getMeShareInfo(String userId, Integer pageNum, Integer pageSize) {

        int pageNumber = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 5 : pageSize;
        // 根据当前倒叙查询列表
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareInfoMapper.selectByUserId(userId);
            }
        });
        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", pageInfo.getTotal());
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }

    /**
     * 删除共享信息 - 假的删除 0.删除 1.正常
     *
     * @param id
     * @return
     */
    @Override
    public boolean deleteShareInfo(String id) {

        boolean flag = false;
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null != shareInfo) {
            shareInfo.setSts("0");
            int i = stAppletShareInfoMapper.updateByPrimaryKeySelective(shareInfo);

            flag = i == 0 ? false : true;
        }

        return flag;
    }
}
