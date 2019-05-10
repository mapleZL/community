package com.phkj.service.impl.share;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.share.StAppletShareApplyMapper;
import com.phkj.dao.share.StAppletShareInfoMapper;
import com.phkj.entity.share.StAppletShareApply;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class ShareServiceImpl implements ShareService {

    /**
     *
     */
    @Autowired
    private StAppletShareInfoMapper stAppletShareInfoMapper;

    @Autowired
    private StAppletShareApplyMapper stAppletShareApplyMapper;

    /**
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
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


    /**
     * @param taskType
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Map<String, Object> getAllShareInfo(String taskType, Integer pageNum, Integer pageSize) {

        //
        int pageNumber = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 5 : pageSize;
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareInfoMapper.selectAllShareInfo(taskType);
            }
        });

        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", pageInfo.getTotal());
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public StAppletShareInfo getShareInfoDetail(String id) {

        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));

        return shareInfo;
    }

    /**
     * @param
     * @return
     */
    @Override
    public boolean addShareInfo(StAppletShareInfo shareInfo) {
        boolean flag = false;
        // app发布更新时间
        shareInfo.setCreateTime(new Date());
        shareInfo.setShareType("1"); //1居民 2 物业社区
        shareInfo.setSts("1"); //任务状态 0删除 1.正常
        int i = stAppletShareInfoMapper.insertSelective(shareInfo);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }


    /**
     * @param id1
     * @param id
     * @param userId
     * @param userName
     * @param telePhone
     * @param address
     * @return
     */
    @Override
    public boolean applyShareInfo(String id, String userId, String userName, String telePhone, String address, String IDCard) {

        boolean flag = false;
        // 查询当前任务信息
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));

        // 创建一条申请信息
        StAppletShareApply shareApply = new StAppletShareApply();
        shareApply.setInfoId(shareInfo.getId());
        shareApply.setCreateUserId(Long.valueOf(userId));
        shareApply.setUserName(userName);
        shareApply.setTelephone(telePhone);
        shareApply.setAddress(address);
        shareApply.setIdCard(IDCard);
        shareApply.setSts("1"); // 申请状态 1.申请中 2.申请通过 3.拒绝申请
        shareApply.setCreateTime(new Date());
        int i = stAppletShareApplyMapper.insert(shareApply);
        if (i > 0) {
            flag = true;
        }

        return flag;
    }


}
