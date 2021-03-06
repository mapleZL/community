package com.phkj.model.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.system.SystemAdminReadDao;
import com.phkj.dao.shop.read.system.SystemRolesReadDao;
import com.phkj.dao.shop.write.system.SystemAdminWriteDao;
import com.phkj.dao.shop.write.system.SystemRolesWriteDao;
import com.phkj.entity.system.SystemAdmin;

@Component(value = "systemAdminModel")
public class SystemAdminModel {

    @Resource
    private SystemAdminReadDao  systemAdminReadDao;
    @Resource
    private SystemAdminWriteDao systemAdminWriteDao;
    @Resource
    private SystemRolesWriteDao systemRolesWriteDao;
    @Resource
    private SystemRolesReadDao systemRolesReadDao;

    /**
    * 根据id取得系统管理员表
    * @param  systemAdminId
    * @return
    */
    public SystemAdmin getSystemAdminById(Integer systemAdminId) {
        return systemAdminReadDao.get(systemAdminId);
    }

    /**
     * 保存系统管理员表
     * @param  systemAdmin
     * @return
     */
    public Integer saveSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdminWriteDao.save(systemAdmin);
    }

    /**
    * 更新系统管理员表
    * @param  systemAdmin
    * @return
    */

    public Integer updateSystemAdmin(SystemAdmin systemAdmin) {
        return systemAdminWriteDao.update(systemAdmin);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemAdminReadDao.getCount(queryMap);
    }

    public List<SystemAdmin> page(Map<String, String> queryMap, Integer start, Integer size) {
        List<SystemAdmin> list = systemAdminReadDao.page(queryMap, start, size);

        for (SystemAdmin admin : list) {
            admin.setRoleName(systemRolesReadDao.get(admin.getRoleId()).getRolesName());
        }

        return list;
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除系统管理员表[" + id + "]时出错");
        return this.systemAdminWriteDao.del(id) > 0;
    }

    public SystemAdmin getSystemAdminByNamePwd(String name, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("password", password);
        return systemAdminReadDao.getByNamePwd(map);
    }

    /**
     * 根据用户名取用户
     * @param name
     * @return
     */
    public List<SystemAdmin> getSystemAdminByName(String name) {
        return systemAdminReadDao.getByName(name);
    }

    /**
     * 根据角色类型获取账户
     * @param roleId
     * @return
     */
    public List<SystemAdmin> getSystemAdminByRoleId(String roleId) {
        return systemAdminReadDao.getSystemAdminByRoleId(roleId);
    }

    public ServiceResult<List<SystemAdmin>> getRepairsByUser(String villageCode,
                                                             List<Integer> ids) {
        ServiceResult<List<SystemAdmin>> result = new ServiceResult<>();
        List<SystemAdmin> list = systemAdminReadDao.getRepairsByUser(villageCode, ids);
        result.setResult(list);
        return result;
    }

}
