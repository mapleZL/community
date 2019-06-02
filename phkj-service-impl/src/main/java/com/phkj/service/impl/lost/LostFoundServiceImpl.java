package com.phkj.service.impl.lost;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.lost.StAppletLostFoundReadMapper;
import com.phkj.dao.shop.write.lost.StAppletLostFoundWriteMapper;
import com.phkj.entity.lost.StAppletLostFound;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.lost.LostFoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 *
 */
@Service
public class LostFoundServiceImpl implements LostFoundService {

    @Autowired
    StAppletLostFoundReadMapper stAppletLostFoundReadMapper;

    @Autowired
    StAppletLostFoundWriteMapper stAppletLostFoundWriteMapper;

    /**
     * @param pageNum
     * @param pageSize
     * @param type
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletLostFound> getAllLostFoundList(Integer pageNum, Integer pageSize, String type, String villageCode) {
        int page = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 30 : pageSize;
        PageInfo<StAppletLostFound> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletLostFoundReadMapper.getAllLostFound(type, villageCode);
            }
        });
        return pageInfo;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param type
     * @param villageCode
     * @param userId
     * @return
     */
    @Override
    public PageInfo<StAppletLostFound> getMeLostFound(Integer pageNum, Integer pageSize, String type,
                                                      String villageCode, String userId) {
        int page = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 30 : pageSize;
        PageInfo<StAppletLostFound> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletLostFoundReadMapper.getMeLostFound(type, villageCode, userId);
            }
        });
        return pageInfo;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public StAppletLostFound getDetail(String id) {

        StAppletLostFound stAppletLostFound = stAppletLostFoundReadMapper.selectByPrimaryKey(Long.valueOf(id));
        return stAppletLostFound;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public boolean delete(String id, String userId, String userName) {
        StAppletLostFound stAppletLostFound = stAppletLostFoundReadMapper.selectByPrimaryKey(Long.valueOf(id));
        if (null == stAppletLostFound) {
            return false;
        }
        stAppletLostFound.setSts("0");
        stAppletLostFound.setModifyTime(new Date());
        stAppletLostFound.setModifyUserName(userName);
        stAppletLostFound.setModifyUserId(userId);
        int i = stAppletLostFoundWriteMapper.updateByPrimaryKeySelective(stAppletLostFound);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param stAppletLostFound
     * @return
     */
    @Override
    public boolean addLostFound(StAppletLostFound stAppletLostFound) {
        stAppletLostFound.setCreateTime(new Date());
        stAppletLostFound.setSts("1");
        stAppletLostFound.setStatus("1");
        int i = stAppletLostFoundWriteMapper.insert(stAppletLostFound);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param type
     * @param sts
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletLostFound> getSystemAllLostFound(Integer pageNum, Integer pageSize, String type, String sts, String villageCode) {
        int page = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 30 : pageSize;
        PageInfo<StAppletLostFound> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletLostFoundReadMapper.getSystemAllLostFound(type, sts, villageCode);
            }
        });
        return pageInfo;
    }


    /**
     * @param id
     * @param adminUser
     * @return
     */
    @Override
    public boolean systemDelete(String id, SystemAdmin adminUser) {
        StAppletLostFound stAppletLostFound = stAppletLostFoundReadMapper.selectByPrimaryKey(Long.valueOf(id));
        stAppletLostFound.setModifyUserId(adminUser.getId().toString());
        stAppletLostFound.setModifyUserName(adminUser.getName());
        stAppletLostFound.setModifyTime(new Date());
        stAppletLostFound.setSts("0");
        int i = stAppletLostFoundWriteMapper.updateByPrimaryKeySelective(stAppletLostFound);
        if (i > 0) {
            return true;
        }
        return false;
    }

    @Override
    public PageInfo<StAppletLostFound> getSystemLostFoundList(Integer pageNum, Integer pageSize, String type, String sts,
                                                              String villageCode) {
        int page = pageNum == 0 ? 1 : pageNum;
        int size = pageSize == 0 ? 30 : pageSize;
        PageInfo<StAppletLostFound> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletLostFoundReadMapper.getSystemLostFoundList(type, sts, villageCode);
            }
        });
        return pageInfo;
    }


    @Override
    public boolean addSystemLostFound(StAppletLostFound stAppletLostFound, SystemAdmin adminUser) {

        stAppletLostFound.setCreateUserId(adminUser.getId().toString());
        stAppletLostFound.setModifyUserName(adminUser.getName());
        stAppletLostFound.setModifyUserId(adminUser.getId().toString());
        stAppletLostFound.setVillageCode(adminUser.getVillageCode());
        stAppletLostFound.setStatus("2");
        stAppletLostFound.setSts("1");
        stAppletLostFound.setCreateTime(new Date());
        int i = stAppletLostFoundWriteMapper.insert(stAppletLostFound);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
