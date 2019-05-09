package com.phkj.model.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.system.SystemResourcesReadDao;
import com.phkj.dao.shop.write.system.SystemResourcesWriteDao;
import com.phkj.entity.system.SystemResources;

@Component(value = "systemResourcesModel")
public class SystemResourcesModel {

    @Resource
    private SystemResourcesWriteDao systemResourcesWriteDao;
    @Resource
    private SystemResourcesReadDao systemResourcesReadDao;

    /**
    * 根据id取得资源表
    * @param  systemResourcesId
    * @return
    */
    public SystemResources getSystemResourcesById(Integer systemResourcesId) {
        return systemResourcesReadDao.get(systemResourcesId);
    }

    /**
     * 保存资源表
     * @param  systemResources
     * @return
     */
    public Integer saveSystemResources(SystemResources systemResources) {
        return systemResourcesWriteDao.save(systemResources);
    }

    /**
    * 更新资源表
    * @param  systemResources
    * @return
    */
    public Integer updateSystemResources(SystemResources systemResources) {
        return systemResourcesWriteDao.update(systemResources);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemResourcesReadDao.getCount(queryMap);
    }

    public List<SystemResources> page(Map<String, String> queryMap) {
        return systemResourcesReadDao.page(queryMap);
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除资源表[" + id + "]时出错");
        return systemResourcesWriteDao.del(id) > 0;
    }

    public List<SystemResources> getByPid(Integer pid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pid", pid);
        return systemResourcesReadDao.list(param);
    }
}
