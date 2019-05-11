package com.phkj.service.repair;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.repair.StAppletRepairMember;

/**
 * 2019-05-11
 *                       
 * @Filename: IStAppletRepairMemberService.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public interface IStAppletRepairMemberService {

    /**
     * 根据id取得st_applet_repair_member对象
     * @param  stAppletRepairMemberId
     * @return
     */
    ServiceResult<StAppletRepairMember> getStAppletRepairMemberById(Integer stAppletRepairMemberId);

    /**
     * 保存st_applet_repair_member对象
     * @param  stAppletRepairMember
     * @return
     */
    ServiceResult<Integer> saveStAppletRepairMember(StAppletRepairMember stAppletRepairMember);

    /**
    * 更新st_applet_repair_member对象
    * @param  stAppletRepairMember
    * @return
    */
    ServiceResult<Integer> updateStAppletRepairMember(StAppletRepairMember stAppletRepairMember);

    /**
     * 列表查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<StAppletRepairMember>> page(Map<String, String> queryMap, PagerInfo pager);
}