package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StNoticeBulletinReleaseManageDao;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;

@Component
public class StNoticeBullentinReleaseManageModel {

    @Resource
    private StNoticeBulletinReleaseManageDao stNoticeBulletinReleaseManageDao;

    public StNoticeBulletinReleaseManage getNoticeById(Long id) {
        return stNoticeBulletinReleaseManageDao.getNoticeById(id);
    }

    public List<StNoticeBulletinReleaseManage> pageList(Integer start, Integer pageSize, String type, List<String> codes) {
        return stNoticeBulletinReleaseManageDao.pageList(start, pageSize, type, codes);
    }

    public Integer getCount(String type, List<String> codes) {
        return stNoticeBulletinReleaseManageDao.getCount(type, codes);
    }

}