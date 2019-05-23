package com.phkj.model.property;


import com.phkj.core.StringUtil;
import com.phkj.dao.shopm.read.relate.StBaseinfoDepartmentDao;
import com.phkj.entity.property.StBaseinfoDepartment;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class StBaseinfoDepartmentModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(StBaseinfoDepartmentModel.class);
    
    @Resource
    private StBaseinfoDepartmentDao stBaseinfoDepartmentDao;
    
    /**
     * 根据id取得部门维护表
     * @param  stBaseinfoDepartmentId
     * @return
     */
    public StBaseinfoDepartment getStBaseinfoDepartmentById(Integer stBaseinfoDepartmentId) {
    	return stBaseinfoDepartmentDao.get(stBaseinfoDepartmentId);
    }
    
    /**
     * 保存部门维护表
     * @param  stBaseinfoDepartment
     * @return
     */
     public Integer saveStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment) {
     	this.dbConstrains(stBaseinfoDepartment);
     	return stBaseinfoDepartmentDao.insert(stBaseinfoDepartment);
     }
     
     /**
     * 更新部门维护表
     * @param  stBaseinfoDepartment
     * @return
     */
     public Integer updateStBaseinfoDepartment(StBaseinfoDepartment stBaseinfoDepartment) {
     	this.dbConstrains(stBaseinfoDepartment);
     	return stBaseinfoDepartmentDao.update(stBaseinfoDepartment);
     }
     
     private void dbConstrains(StBaseinfoDepartment stBaseinfoDepartment) {
		stBaseinfoDepartment.setName(StringUtil.dbSafeString(stBaseinfoDepartment.getName(), true, 50));
		stBaseinfoDepartment.setCode(StringUtil.dbSafeString(stBaseinfoDepartment.getCode(), true, 50));
		stBaseinfoDepartment.setOrgCode(StringUtil.dbSafeString(stBaseinfoDepartment.getOrgCode(), true, 50));
		stBaseinfoDepartment.setRegion(StringUtil.dbSafeString(stBaseinfoDepartment.getRegion(), true, 20));
		stBaseinfoDepartment.setTopOrgCode(StringUtil.dbSafeString(stBaseinfoDepartment.getTopOrgCode(), true, 50));
		stBaseinfoDepartment.setSts(StringUtil.dbSafeString(stBaseinfoDepartment.getSts(), true, 1));
     }

    /**
     * 根据jobs_id获取部门数据
     * @param list
     * @return
     */
    public List<StBaseinfoDepartment> getDepartmentList(List<Long> list) {
        return stBaseinfoDepartmentDao.getDepartmentList(list);
    }
}