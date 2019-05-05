package com.ejavashop.service.member;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.MemberCredit;

public interface IMemberCreditService {

    /**
     * 根据id取得赊账管理
     * @param  memberCreditId
     * @return
     */
    ServiceResult<MemberCredit> getMemberCreditById(Integer memberCreditId);

    /**
     * 保存赊账管理
     * @param  memberCredit
     * @return
     */
    ServiceResult<Integer> saveMemberCredit(MemberCredit memberCredit);

    /**
    * 更新赊账管理
    * @param  memberCredit
    * @return
    */
    ServiceResult<Integer> updateMemberCredit(MemberCredit memberCredit);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberCredit>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 以会员id查找赊账记录
     * @param memberId
     * @return
     */
    ServiceResult<MemberCredit> getMemberCreditByMemberId(Integer memberId);

    /**
     * 赊账调整
     * @param creditinfo
     * @param name 
     * @param id 
     * @return
     */
    ServiceResult<Boolean> doEdit(String creditinfo, Integer id, String name);

    /**
     * 会员赊账设置到期定时任务
     * @return
     */
    ServiceResult<Boolean> jobCredit();

    /**
     * 获得所有的memberCerdit对象
     * @param queryMap
     * @return
     */
    ServiceResult<List<MemberCredit>> getAllMemberCredit(Map<String, String> queryMap);
}