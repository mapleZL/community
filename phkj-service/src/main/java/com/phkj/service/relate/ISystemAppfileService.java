package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.SystemAppfile;

public interface ISystemAppfileService {

    /**
     * 根据id取得system_appfile对象
     * @param  systemAppfileId
     * @return
     */
    ServiceResult<SystemAppfile> getSystemAppfileById(Long systemAppfileId);

    /**
     * 保存system_appfile对象
     * @param  systemAppfile
     * @return
     */
    ServiceResult<Integer> saveSystemAppfile(SystemAppfile systemAppfile);

    /**
    * 更新system_appfile对象
    * @param  systemAppfile
    * @return
    */
    ServiceResult<Integer> updateSystemAppfile(SystemAppfile systemAppfile);

    /**
     * 根据类型获取图片列表
     * @param string
     * @param id
     * @param type
     * @return
     */
    ServiceResult<List<SystemAppfile>> getPicList(String string, Long id, String type);
}