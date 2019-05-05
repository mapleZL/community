package com.ejavashop.model.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberCreditLogReadDao;
import com.ejavashop.dao.shop.read.member.MemberReadDao;
import com.ejavashop.dao.shop.write.member.MemberCreditLogWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberCreditLog;

@Component
public class MemberCreditLogModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(MemberCreditLogModel.class);

    @Resource
    private MemberCreditLogWriteDao        memberCreditLogWriteDao;
    @Resource
    private MemberCreditLogReadDao        memberCreditLogReadDao;
    @Resource
    private DataSourceTransactionManager   transactionManager;
    @Resource
    private MemberWriteDao                 memberWriteDao;
    @Resource
    private MemberReadDao                 memberReadDao;

    /**
     * 根据id取得赊账记录
     * @param  memberCreditLogId
     * @return
     */
    public MemberCreditLog getMemberCreditLogById(Integer memberCreditLogId) {
        return memberCreditLogReadDao.get(memberCreditLogId);
    }

    /**
     * 保存赊账记录
     * @param  memberCreditLog
     * @return
     */
    public Integer saveMemberCreditLog(MemberCreditLog memberCreditLog) {
        this.dbConstrains(memberCreditLog);
        return memberCreditLogWriteDao.save(memberCreditLog);
    }

    /**
    * 更新赊账记录
    * @param  memberCreditLog
    * @return
    */
    public Integer updateMemberCreditLog(MemberCreditLog memberCreditLog) {
        this.dbConstrains(memberCreditLog);
        return memberCreditLogWriteDao.update(memberCreditLog);
    }

    private void dbConstrains(MemberCreditLog memberCreditLog) {
        memberCreditLog.setOrderSn(StringUtil.dbSafeString(memberCreditLog.getOrderSn(), true, 32));
        memberCreditLog.setCreateName(StringUtil.dbSafeString(memberCreditLog.getCreateName(),
            false, 50));
    }

    public List<MemberCreditLog> page(Map<String, String> queryMap, PagerInfo pager)
                                                                                    throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;

        if (param.get("q_memberName") != null) {
            String name = (String) param.get("q_memberName");
            Map<String, String> memberMap = new HashMap<String, String>();
            memberMap.put("q_name", name);
            List<Member> members = memberReadDao.getMembers(memberMap, -1, -1);
            List<Integer> ids = new ArrayList<Integer>();

            if (members.size() < 1) {
                ids.add(-1);
            } else {
                for (Member member : members) {
                    ids.add(member.getId());
                }
            }

            param.put("in_members", StringUtil.listToString(ids, ","));
        }
        if (pager != null) {
            pager.setRowsCount(memberCreditLogReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<MemberCreditLog> list = memberCreditLogReadDao.page(param);
        return list;
    }

    public Integer del(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id == null)
                throw new BusinessException("删除赊账记录[" + id + "]时出错");
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("[MemberCreditLogModel][del] exception:" + e.getMessage());
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
        return memberCreditLogWriteDao.del(id);
    }

    public List<MemberCreditLog> getAllMemberCreditLog(Map<String, String> queryMap) {
        return memberCreditLogReadDao.getAllMemberCreditLog(queryMap);
    }

}