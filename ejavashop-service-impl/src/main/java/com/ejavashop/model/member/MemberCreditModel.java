package com.ejavashop.model.member;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.ejavashop.dao.shop.read.member.MemberBalanceLogsReadDao;
import com.ejavashop.dao.shop.read.member.MemberCreditLogReadDao;
import com.ejavashop.dao.shop.read.member.MemberCreditReadDao;
import com.ejavashop.dao.shop.read.member.MemberReadDao;
import com.ejavashop.dao.shop.write.member.MemberBalanceLogsWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCreditLogWriteDao;
import com.ejavashop.dao.shop.write.member.MemberCreditWriteDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.entity.member.MemberCreditLog;

@Component
public class MemberCreditModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(MemberCreditModel.class);

    @Resource
    private MemberCreditWriteDao           memberCreditWriteDao;
    @Resource
    private MemberCreditReadDao           memberCreditReadDao;
    @Resource
    private DataSourceTransactionManager   transactionManager;
    @Resource
    private MemberCreditLogWriteDao        memberCreditLogWriteDao;
    @Resource
    private MemberCreditLogReadDao        memberCreditLogReadDao;
    @Resource
    private MemberWriteDao                 memberWriteDao;
    @Resource
    private MemberReadDao                 memberReadDao;
    @Resource
    private MemberBalanceLogsWriteDao      memberBalanceLogsWriteDao;
    @Resource
    private MemberBalanceLogsReadDao      memberBalanceLogsReadDao;

    /**
     * 根据id取得赊账管理
     * @param  memberCreditId
     * @return
     */
    public MemberCredit getMemberCreditById(Integer memberCreditId) {
        return memberCreditReadDao.get(memberCreditId);
    }

    /**
     * 保存赊账管理
     * @param  memberCredit
     * @return
     */
    public Integer saveMemberCredit(MemberCredit memberCredit) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        Integer count = 0;
        try {
            //保存赊账
            count = memberCreditWriteDao.save(memberCredit);

            //赊账日志
            MemberCreditLog log = new MemberCreditLog(memberCredit.getId(),
                memberCredit.getMemberId(), memberCredit.getQuota(), memberCredit.getQuota(),
                memberCredit.getCycle(), memberCredit.getExpireDate(), memberCredit.getCreateId(),
                memberCredit.getCreateName(), new Date());
            memberCreditLogWriteDao.save(log);

            transactionManager.commit(status);
            this.dbConstrains(memberCredit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;

    }

    /**
    * 更新赊账管理
    * @param  memberCredit
    * @return
    */
    public Integer updateMemberCredit(MemberCredit memberCredit) {
        this.dbConstrains(memberCredit);
        return memberCreditWriteDao.update(memberCredit);
    }

    private void dbConstrains(MemberCredit memberCredit) {
    }

    public List<MemberCredit> page(Map<String, String> queryMap, PagerInfo pager) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        List<Member> members = null;
        if (param.get("q_memberName") != null) {
            String name = (String) param.get("q_memberName");
            Map<String, String> memberMap = new HashMap<String, String>();
            memberMap.put("q_name", name);
            members = memberReadDao.getMembers(memberMap, -1, -1);
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
            pager.setRowsCount(memberCreditReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<MemberCredit> list = memberCreditReadDao.page(param);
        for (MemberCredit mc : list) {
            for (Member mr: members) {
                if (mc.getMemberId().equals(mr.getId())) {
                    mc.setRealName(mr.getRealName());
                    break;
                }
            }
        }
        return list;
    }

    public Integer del(Integer id) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id == null)
                throw new BusinessException("删除赊账管理[" + id + "]时出错");
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("[MemberCreditModel][del] exception:" + e.getMessage());
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
        return memberCreditWriteDao.del(id);
    }

    /**
     * 赊账调整
     * @param creditinfo
     * @param createName 
     * @param createId 
     * @return
     */
    public Boolean doEdit(String creditinfo, Integer createId, String createName) {
        boolean success = false;
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            String[] rowsdata = creditinfo.split(";");
            if (rowsdata.length < 1) {
                throw new BusinessException("没有要调整的数据");
            }
            for (String row : rowsdata) {
                String[] data = row.split(",");
                if (data.length != 5) {
                    throw new BusinessException("数据格式错误");
                }
                Integer id = Integer.valueOf(data[0]);
                BigDecimal quotaChange = new BigDecimal(data[3]);
                Integer cycle = Integer.valueOf(data[4]);

                MemberCredit mc = memberCreditReadDao.get(id);
                if (mc == null) {
                    throw new BusinessException("数据错误");
                }
                BigDecimal used = mc.getUsed();
                Member member = memberReadDao.get(mc.getMemberId());
                BigDecimal balanceBefore = member.getBalance();
                
                /*
                log.debug("********************用户：" + member.getName() + "bg*****************");
                log.debug("用户账户余额：" + balanceBefore);
                //如果用户余额为负，即使用过赊账，则用户余额= 已使用赊账额度+原余额
                //如果已使用赊账额度+原余额>0，则可能是用户充过值，此时余额应为两者差值（即用户充值的金额）
                //其它情况置0（财务已结清）
                BigDecimal changeAmount = BigDecimal.ZERO;
                if (balanceBefore.doubleValue() < 0) {
                    changeAmount = used.add(balanceBefore);
                    log.debug("用户已使用赊账：" + used);
                    log.debug("设置后余额：" + changeAmount);
                }
                member.setBalance(changeAmount);
                memberWriteDao.update(member);
                log.debug("更新用户余额完成，当前余额：" + member.getBalance());
                log.debug("记录余额日志");
                // 记录余额日志
                MemberBalanceLogs logs = new MemberBalanceLogs();
                logs.setMemberId(member.getId());
                logs.setMemberName(member.getName());
                logs.setMoneyBefore(balanceBefore);
                logs.setMoneyAfter(member.getBalance());
                logs.setMoney(quotaChange);
                logs.setCreateTime(new Date());
                logs.setState(MemberBalanceLogs.STATE_5);
                logs.setRemark("用户赊账调整余额：" + quotaChange + "元，其中，原余额：" + balanceBefore + "，调整后用户余额："
                               + changeAmount);
                logs.setOptId(createId);
                logs.setOptName(createName);
                memberBalanceLogsWriteDao.save(logs);
                log.debug("********************用户：" + member.getName() + "ed*****************");
                */
                //Terry 20161020 finacel not need reset
//                mc.setHasSettled(1);//已结清

                BigDecimal quotaBefore = mc.getQuota();
                //额度
                mc.setQuota(quotaChange);
                mc.setState(1);
                //剩余额度
                mc.setSurplus(quotaChange.subtract(mc.getUsed()));
                /* terry 20161013 已使用额度不停累加，不清零
                //已使用额度置0
                mc.setUsed(BigDecimal.ZERO);
                //额度
                mc.setQuota(quotaChange);
                //剩余额度
                mc.setSurplus(quotaChange);
                */

                //周期
                mc.setCycle(cycle);

                Date expireDateBefore = mc.getExpireDate();
                //到期日
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, cycle);
                mc.setExpireDate(cal.getTime());

                //赊账日志
//                MemberCreditLog log = new MemberCreditLog(mc.getId(), mc.getMemberId(),
//                    balanceBefore, member.getBalance(), quotaChange, quotaBefore, mc.getQuota(),
//                    expireDateBefore, cycle, mc.getExpireDate(), createId, createName, new Date());
                MemberCreditLog log = new MemberCreditLog(mc.getId(), mc.getMemberId(),
                  balanceBefore, member.getBalance(), BigDecimal.ZERO, mc.getSurplus(), mc.getSurplus(),
                  expireDateBefore, cycle, mc.getExpireDate(), createId, createName, new Date());
                memberCreditLogWriteDao.save(log);

                memberCreditWriteDao.update(mc);
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            success = false;
            throw e;
        }
        return success;
    }

    public MemberCredit getMemberCreditByMemberId(Integer memberId) {
        return memberCreditReadDao.getByMemberId(memberId);
    }

    public boolean jobCredit() {
        boolean result = false;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("state", 1);
            List<MemberCredit> mclist = memberCreditReadDao.page(map);
            for (MemberCredit mc : mclist) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(mc.getExpireDate());

                Calendar now = Calendar.getInstance();
                //如果到期日期在此时之前，则到期
                if (cal.before(now)) {
                    mc.setState(2);
                    mc.setUpdateId(0);
                    mc.setUpdateName("系统自动调整");
                    memberCreditWriteDao.update(mc);
                }
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }

    public List<MemberCredit> getAllMemberCredit(Map<String, String> queryMap) {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        List<Member> members = null;
        if (param.get("q_memberName") != null) {
            String name = (String) param.get("q_memberName");
            Map<String, String> memberMap = new HashMap<String, String>();
            memberMap.put("q_name", name);
            members = memberReadDao.getMembers(memberMap, -1, -1);
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
        List<MemberCredit> list = memberCreditReadDao.getAllMemberCredit(param);
        return list;
    }
}