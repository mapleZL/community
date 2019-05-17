package com.phkj.service.impl.relate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phkj.core.ServiceResult;
import com.phkj.dao.shopm.read.relate.StNoticeBulletinReleaseManageDao;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;

@Service(value = "stNoticeBulletinReleaseManageService")
public class IStNoticeBulletinReleaseManageServiceImpl implements
                                                       IStNoticeBulletinReleaseManageService {
    
    @Autowired
    private StNoticeBulletinReleaseManageDao stNoticeBulletinReleaseManageDao;

    @Override
    public ServiceResult<StNoticeBulletinReleaseManage> getNoticeById(Long id) {
        ServiceResult<StNoticeBulletinReleaseManage> result = new ServiceResult<StNoticeBulletinReleaseManage>();
        result.setResult(stNoticeBulletinReleaseManageDao.getNoticeById(id));
        return result;
    }

    @Override
    public ServiceResult<List<StNoticeBulletinReleaseManage>> pageList(Integer start,
                                                                                     Integer pageSize,
                                                                                     String type) {
        ServiceResult<List<StNoticeBulletinReleaseManage>> serviceResult = new ServiceResult<List<StNoticeBulletinReleaseManage>>();
        serviceResult.setResult(stNoticeBulletinReleaseManageDao.pageList(start, pageSize, type));
        return serviceResult;
    }

    @Override
    public Integer getCount(String type) {
        return stNoticeBulletinReleaseManageDao.getCount(type);
    }

}
