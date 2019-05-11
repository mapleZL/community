package com.phkj.model.repair;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.repaire.StAppletRepairMemberReadDao;
import com.phkj.dao.shop.write.repaire.StAppletRepairMemberWriteDao;
import com.phkj.entity.repair.StAppletRepairMember;
/**
 * 
 *                       
 * @Filename: StAppletRepairMemberModel.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Component
public class StAppletRepairMemberModel {

    
    @Resource
    private StAppletRepairMemberReadDao stAppletRepairMemberReadDao;
    @Resource
    private StAppletRepairMemberWriteDao stAppletRepairMemberWriteDao;
    
    /**
     * 根据id取得st_applet_repair_member对象
     * @param  stAppletRepairMemberId
     * @return
     */
    public StAppletRepairMember getStAppletRepairMemberById(Integer stAppletRepairMemberId) {
    	return stAppletRepairMemberReadDao.get(stAppletRepairMemberId);
    }
    
    /**
     * 保存st_applet_repair_member对象
     * @param  stAppletRepairMember
     * @return
     */
     public Integer saveStAppletRepairMember(StAppletRepairMember stAppletRepairMember) {
     	return stAppletRepairMemberWriteDao.insert(stAppletRepairMember);
     }
     
     /**
     * 更新st_applet_repair_member对象
     * @param  stAppletRepairMember
     * @return
     */
     public Integer updateStAppletRepairMember(StAppletRepairMember stAppletRepairMember) {
     	return stAppletRepairMemberWriteDao.update(stAppletRepairMember);
     }

    public int getRepaitMemberCount(Map<String, String> queryMap) {
        return stAppletRepairMemberReadDao.getRepaitMemberCount(queryMap);
    }

    public List<StAppletRepairMember> getRepaitMemberList(Map<String, String> queryMap,
                                                         Integer start, Integer size) {
        return stAppletRepairMemberReadDao.getRepaitMemberList(start, size, queryMap);
    }
}