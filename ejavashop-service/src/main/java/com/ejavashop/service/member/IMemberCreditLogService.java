package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberCreditLog;

public interface IMemberCreditLogService {

    /**
     * 根据id取得赊账记录
     * @param  memberCreditLogId
     * @return
     */
    ServiceResult<MemberCreditLog> getMemberCreditLogById(Integer memberCreditLogId);

    /**
     * 保存赊账记录
     * @param  memberCreditLog
     * @return
     */
    ServiceResult<Integer> saveMemberCreditLog(MemberCreditLog memberCreditLog);

    /**
    * 更新赊账记录
    * @param  memberCreditLog
    * @return
    */
    ServiceResult<Integer> updateMemberCreditLog(MemberCreditLog memberCreditLog);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberCreditLog>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    
    ServiceResult<List<MemberCreditLog>> getAllMemberCreditLog(Map<String, String> queryMap);
}