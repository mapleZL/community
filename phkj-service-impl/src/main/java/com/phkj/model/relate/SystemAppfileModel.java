package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.SystemAppfileDao;
import com.phkj.entity.relate.SystemAppfile;

@Component
public class SystemAppfileModel {

    @Resource
    private SystemAppfileDao systemAppfileDao;

    /**
     * 根据id取得system_appfile对象
     * @param  systemAppfileId
     * @return
     */
    public SystemAppfile getSystemAppfileById(Long systemAppfileId) {
        return systemAppfileDao.get(systemAppfileId);
    }

    /**
     * 保存system_appfile对象
     * @param  systemAppfile
     * @return
     */
    public Integer saveSystemAppfile(SystemAppfile systemAppfile) {
        return systemAppfileDao.insert(systemAppfile);
    }

    /**
    * 更新system_appfile对象
    * @param  systemAppfile
    * @return
    */
    public Integer updateSystemAppfile(SystemAppfile systemAppfile) {
        return systemAppfileDao.update(systemAppfile);
    }

    /**
     * 根据模块编码，id，类型获取图片列表
     * @param module
     * @param id
     * @param type
     * @return
     */
    public List<SystemAppfile> getPicList(String module, Long id, String type) {
        return systemAppfileDao.getPicList(module, id, type);
    }

}