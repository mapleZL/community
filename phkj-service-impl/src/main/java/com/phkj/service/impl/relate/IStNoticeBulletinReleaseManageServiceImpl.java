package com.phkj.service.impl.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.model.relate.StNoticeBullentinReleaseManageModel;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;

@Service(value = "stNoticeBulletinReleaseManageService")
public class IStNoticeBulletinReleaseManageServiceImpl implements
                                                       IStNoticeBulletinReleaseManageService {
    
    @Resource
    private StNoticeBullentinReleaseManageModel stNoticeBullentinReleaseManageModel;

    @Override
    public ServiceResult<StNoticeBulletinReleaseManage> getNoticeById(Long id) {
        ServiceResult<StNoticeBulletinReleaseManage> result = new ServiceResult<StNoticeBulletinReleaseManage>();
        result.setResult(stNoticeBullentinReleaseManageModel.getNoticeById(id));
        return result;
    }

    @Override
    public ServiceResult<List<StNoticeBulletinReleaseManage>> pageList(Integer start,
                                                                                     Integer pageSize,
                                                                                     String type) {
        ServiceResult<List<StNoticeBulletinReleaseManage>> serviceResult = new ServiceResult<List<StNoticeBulletinReleaseManage>>();
        serviceResult.setResult(stNoticeBullentinReleaseManageModel.pageList(start, pageSize, type));
        return serviceResult;
    }

    @Override
    public Integer getCount(String type) {
        return stNoticeBullentinReleaseManageModel.getCount(type);
    }

}
