package com.phkj.service.impl.share;

import java.util.*;

import com.github.pagehelper.StringUtil;
import com.phkj.entity.system.SystemAdmin;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.share.StAppletShareApplyMapper;
import com.phkj.dao.shop.read.share.StAppletShareInfoMapper;
import com.phkj.entity.share.StAppletShareApply;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;

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
     * @param taskType
     * @param pageNum
     * @param pageSize
     * @param villageCode
     * @return
     */
    @Override
    public Map<String, Object> getMeShareInfo(String userId, String taskType, Integer pageNum, Integer pageSize, String villageCode) {

        int pageNumber = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 5 : pageSize;
        // 根据当前倒叙查询列表
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareInfoMapper.selectByUserId(userId, taskType,villageCode);
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
    public boolean deleteShareInfo(String id, String type) {

        boolean flag = false;
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null != shareInfo) {
            //  停止共享
            if ("2".equals(type)) {
                shareInfo.setSts("1");
                shareInfo.setShareStatus("2");  //共享完成
            } else {
                shareInfo.setSts("0");
            }
            int i = stAppletShareInfoMapper.updateByPrimaryKeySelective(shareInfo);

            flag = (i == 0) ? false : true;
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
        shareInfo.setShareStatus("0"); //
        int i = stAppletShareInfoMapper.insert(shareInfo);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }


    /**
     * @param
     * @param id
     * @param userId
     * @param userName
     * @param telePhone
     * @param address
     * @return
     */
    @Override
    public String applyShareInfo(String id, String userId, String userName, String telePhone, String address, String IDCard) {

        String msg = "";
        // 查询当前任务信息
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));

        // 申请成功不可再次预约
        if ("1".equals(shareInfo.getShareStatus())) {
            msg = "共享信息已被预约,不可再申请!";
            return msg;
        }

        //
        if ("2".equals(shareInfo.getShareStatus())) {
            msg = "共享已被关闭,不可再申请!";
            return msg;
        }

        StAppletShareApply apply = stAppletShareApplyMapper.selectApplyByUserId(userId, shareInfo.getId().toString());
        if (null != apply){
            return "你已经申请过本条共享信息!";
        }
        // 查询申请任务id
        String applyNum = shareInfo.getApplyNum();
        if (StringUtil.isNotEmpty(applyNum)) {
            Integer num = Integer.valueOf(applyNum);
            /**
             *  获取所有申请数量,如果申请数量+1(当前要发布数量,则修改任务)
             */
            List<StAppletShareApply> applyList = stAppletShareApplyMapper.selectApplyByInfoId(shareInfo.getId());
            if (num == applyList.size()) {
                msg = "共享申请已满,不在再申请!";
                return msg;
            }
        }
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
        if (i <= 0) {
            msg = "申请失败!";
        }

        return msg;
    }


    /**
     * 后台管理列表页面
     *
     * @param taskType
     * @param status
     * @param pageNum
     * @param pageSize
     * @param villageCode
     * @return
     */
    @Override
    public Map<String, Object> getShareInfoList(String taskType, String status, Integer pageNum, Integer pageSize, String villageCode) {

        int pageNumber = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 20 : pageSize;
        // 根据当前倒叙查询列表
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareInfoMapper.selectByTaskType(taskType, status,villageCode);
            }
        });
        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", String.valueOf(pageInfo.getTotal()));
        returnMap.put("list", pageInfo.getList());

        return returnMap;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getShareDetail(String id) {
        Map<String, Object> returnMap = new HashMap<>();
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));
        if (shareInfo != null) {
            // 查询所有的申请 用于列表页展示
            List<StAppletShareApply> list = stAppletShareApplyMapper.selectApplyByInfoId(shareInfo.getId());
            returnMap.put("applyList", list);
        }
        returnMap.put("shareInfo", shareInfo);
        return returnMap;
    }

    /**
     * @param
     * @param taskType
     * @param status
     * @param page
     * @param rows
     * @param villageCode
     * @return
     */
    @Override
    public Map<String, Object> getComShareInfoList(Integer id, String taskType, String status, Integer page,
                                                   Integer rows, String villageCode) {
        int pageNumber = page == 0 ? 1 : page;
        int size = rows == 0 ? 20 : rows;
        // 根据当前倒叙查询列表
        String userId = (String.valueOf(id)).equals("null") ? "" : String.valueOf(id);
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareInfoMapper.selectComShareInfoList(userId, taskType, status,villageCode);
            }
        });

        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", String.valueOf(pageInfo.getTotal()));
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }

    /**
     * @param shareInfo
     * @return
     */
    @Override
    public boolean createShareInfo(StAppletShareInfo shareInfo) {

        boolean flag = false;
        shareInfo.setSts("1"); //任务状态 0删除 1.正常
        if (StringUtils.isBlank(shareInfo.getImgUrl())){
            List<String> list = new ArrayList<>();
            shareInfo.setImgUrl(list.toString());
        }
        int i = stAppletShareInfoMapper.insert(shareInfo);
        if (i > 0) {
            flag = true;
        }
        return flag;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public boolean stopShareInfo(String id) {

        boolean flag = false;
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null != shareInfo) {
            shareInfo.setShareStatus("2");
            int i = stAppletShareInfoMapper.updateByPrimaryKeySelective(shareInfo);
            List<StAppletShareApply> applyList =
                    stAppletShareApplyMapper.selectSUCCESSApplyByInfoId(shareInfo.getId());
            // 如果有申请中的任务全部拒绝
            if (applyList != null) {
                for (StAppletShareApply shareApply : applyList) {
                    shareApply.setSts("4");
                    stAppletShareApplyMapper.updateByPrimaryKeySelective(shareApply);
                }
            }
            flag = i == 0 ? false : true;
        }
        return flag;
    }

    /**
     * @param status
     * @param userId
     * @param villageCode
     * @return
     */
    @Override
    public Map<String, Object> getMeApplyInfoList(String status, String userId, Integer pageNum, Integer pageSize, String villageCode) {

        int pageNumber = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 20 : pageSize;
        // 根据当前倒叙查询列表
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareApplyMapper.selectMeApplyInfoList(status, userId,villageCode);
            }
        });

        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", String.valueOf(pageInfo.getTotal()));
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }

    /**
     * 查询所有申请
     *
     * @param page
     * @param rows
     * @param infoId
     * @return
     */
    @Override
    public Map<String, Object> getAllApplyByPage(Integer page, Integer rows, String infoId) {

        int pageNumber = page == 0 ? 1 : page;
        int size = rows == 0 ? 20 : rows;
        // 根据当前倒叙查询列表
        PageInfo<Object> pageInfo = PageHelper.startPage(pageNumber, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletShareApplyMapper.selectSystemApplyByInfoId(Long.valueOf(infoId));
            }
        });

        // 处理数据
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("total", String.valueOf(pageInfo.getTotal()));
        returnMap.put("list", pageInfo.getList());
        return returnMap;
    }

    /**
     * @param id
     * @param type
     * @param adminUser
     * @return
     */
    @Override
    public boolean examineApplyInfo(String id, String type, SystemAdmin adminUser) {

        /**
         * 判断拒绝 还是通过 --type
         * 1.申请中
         * 2.通过
         * 3.拒绝
         * 4.关闭申请
         */
        boolean flag = false;
        StAppletShareApply shareApply = stAppletShareApplyMapper.selectByPrimaryKey(Long.valueOf(id));
        if ("del".equals(type)) {
            shareApply.setSts("3");
        } else {
            shareApply.setSts("2");
        }

        /**\
         * 设置发布不可再申请
         */
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectShareByInfo(shareApply.getInfoId());
        shareInfo.setModifyTime(new Date());
        shareInfo.setModifyUserId(Long.valueOf(adminUser.getId()));
        shareInfo.setShareStatus("2");
        stAppletShareInfoMapper.updateByPrimaryKeySelective(shareInfo);
        /**
         *  修改状态
         */
        shareApply.setUpdateTime(new Date());
        shareApply.setExamineId(String.valueOf(adminUser.getId()));
        shareApply.setImgUrl(adminUser.getName());
        shareApply.setModifyUserId(Long.valueOf(adminUser.getId()));
        int i = stAppletShareApplyMapper.updateByPrimaryKeySelective(shareApply);
        if (i > 0) {
            flag = true;
        }

        List<StAppletShareApply> list = stAppletShareApplyMapper.selectNOTINApplyById(shareApply.getInfoId(), shareApply.getId());
        if (list != null && list.size() > 0) {
            for (StAppletShareApply apply : list) {
                apply.setSts("3");
                apply.setImgUrl(adminUser.getName());
                apply.setExamineId(adminUser.getId().toString());
                apply.setModifyTime(new Date());
                apply.setModifyUserId(Long.valueOf(adminUser.getId()));
                stAppletShareApplyMapper.updateByPrimaryKeySelective(apply);
            }
        }
        return flag;
    }

    @Override
    public Map<String, Object> getMeApplyDetail(String id, String userId) {

        Map<String, Object> returnMap = new HashMap<>();
        StAppletShareInfo shareInfo = stAppletShareInfoMapper.selectByPrimaryKey(Long.valueOf(id));

        StAppletShareApply apply = stAppletShareApplyMapper.selectApplyByUserId(userId, shareInfo.getId().toString());
        returnMap.put("shareInfo", shareInfo);
        returnMap.put("apply", apply);
        return returnMap;
    }

}
