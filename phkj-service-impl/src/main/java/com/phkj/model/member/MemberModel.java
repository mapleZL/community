package com.phkj.model.member;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;

import com.phkj.core.Md5;
import com.phkj.core.StringUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.sms.MobileMessage;
import com.phkj.dao.shop.read.member.MemberReadDao;
import com.phkj.dao.shop.read.order.OrdersReadDao;
import com.phkj.dao.shop.read.product.ProductReadDao;
import com.phkj.dao.shop.read.seller.SellerReadDao;
import com.phkj.dao.shop.write.member.MemberWriteDao;
import com.phkj.dao.shop.write.order.OrdersWriteDao;
import com.phkj.dao.shop.write.seller.SellerWriteDao;
import com.phkj.entity.member.Member;
import com.phkj.vo.member.FrontCheckPwdVO;

@Component(value = "memberModel")
public class MemberModel {
    private static Logger                   log = LogManager.getLogger(MemberModel.class);
    
    @Resource
    private MemberWriteDao                  memberWriteDao;
    
    @Resource
    private MemberReadDao                  memberReadDao;

    @Resource
    private DataSourceTransactionManager    transactionManager;
    @Resource
    private OrdersWriteDao                  ordersWriteDao;
    @Resource
    private OrdersReadDao                  ordersReadDao;
    @Resource
    private ProductReadDao                  productReadDao;
    @Resource
    private SellerWriteDao            		sellerWriteDao;
    @Resource
    private SellerReadDao            		sellerReadDao;
    

    /**
    * 根据id取得会员
    * @param  memberId
    * @return
    */
    public Member getMemberById(Integer memberId) {
        return memberReadDao.get(memberId);
    }

    /**
     * 保存会员
     * @param  member
     * @return
     */
    public Integer saveMember(Member member) {
        return memberWriteDao.save(member);
    }

    /**
    * 更新会员
    * @param  member
    * @return
    */
    public boolean updateMember(Member member) {
        return memberWriteDao.update(member) > 0;
    }

    public Integer getMembersCount(Map<String, String> queryMap) {
        return memberReadDao.getMembersCount(queryMap);
    }

    public List<Member> getMembers(Map<String, String> queryMap, Integer start, Integer size) {
        return memberReadDao.getMembers(queryMap, start, size);
    }


    /**
     * 更新会员余额
     * @param logs
     * @return
     */

    /**
     * 会员登录，修改会员登录信息，记录登录日志
     * @param memberName 用户名
     * @param password 密码
     * @param ip
     * @param source 登录来源
     * @return
     * @throws Exception
     */
    public Member memberLogin(String memberName, String password, String ip,
                              Integer source) throws Exception {

        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 参数校验
            if (StringUtil.isEmpty(memberName)) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (StringUtil.isEmpty(password)) {
                throw new BusinessException("密码不能为空，请重试！");
            }

            password = Md5.getMd5String(password);

            //查询用户
            List<Member> members = memberReadDao.getByNameAndPwd(memberName, password);
            Member member = null;
            if (members.size() == 0) {
                throw new BusinessException("用户名或密码错误！");
            } else if (members.size() > 1) {
                throw new BusinessException("用户名重复，请联系系统管理员！");
            }
            member = members.get(0);

            if (member.getStatus() != Member.STATUS_1_ON) {
                throw new BusinessException("会员账户状态异常，请联系系统管理员！");
            }
            //1、更新会员表登录信息
            Member newMember = new Member();
            newMember.setId(member.getId());
            newMember.setLastLoginTime(new Date());
            newMember.setLastLoginIp(ip);
            //登录次数累加
            newMember.setLoginNumber(member.getLoginNumber() + 1);
            memberWriteDao.update(newMember);

            transactionManager.commit(status);
            return member;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 会员注册
     * @param member
     * @return
     * @throws Exception
     */
    public Member memberRegister(Member member) throws Exception {
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
            // 参数校验
            if (member == null) {
                throw new BusinessException("注册信息不能为空，请重试！");
            } else if (StringUtil.isEmpty(member.getName())) {
                throw new BusinessException("会员名称不能为空，请重试！");
            } else if (StringUtil.isEmpty(member.getPassword())) {
                throw new BusinessException("密码不能为空，请重试！");
            }
            //判断用户名是否已存在
            List<Member> byNameList = memberReadDao.getByName(member.getName());
            if (byNameList != null && byNameList.size() > 0) {
                throw new BusinessException("会员名称已存在，请重试！");
            }

            // 保存会员注册信息
            int count = memberWriteDao.save(member);
            if (count == 0) {
                throw new BusinessException("注册信息保存失败，请重试！");
            }
            this.sendMessageToMember(member);
            transactionManager.commit(status);
            return member;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    private void sendMessageToMember(Member member) {
        Map<String, Object> smsparam = new HashMap<String, Object>();
        MobileMessage mMessage = new MobileMessage();
        try {
            smsparam.put("mobile", member.getName());
            smsparam.put("content", mMessage.getRegister());
        } catch (Exception e) {
            log.debug("注册成功短信发送失败，客户：" + member.getName());
        }
        
    }

    /**
     * 根据会员名称取会员
     * @param name
     * @return
     */
    public List<Member> getMemberByName(String name) {
        return memberReadDao.getByName(name);
    }


    /**
     * 修改密码提交
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    public Member editPassword(String oldPwd, String newPwd, Member member) {
        Assert.notNull(memberWriteDao, "Property 'memberWriteDao' is required.");
        // 参数校验
        if (StringUtil.isEmpty(oldPwd)) {
            throw new BusinessException("旧密码不能为空，请重试！");
        } else if (StringUtil.isEmpty(newPwd)) {
            throw new BusinessException("新密码不能为空，请重试！");
        }

        Member memberDb = memberReadDao.get(member.getId());
        if (memberDb == null) {
            throw new BusinessException("用户信息获取失败！");
        }

        String oldPwdMd5 = Md5.getMd5String(oldPwd);

        if (!oldPwdMd5.equals(memberDb.getPassword())) {
            throw new BusinessException("原密码不正确");
        }

        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(newPwd));

        //1、更新个人资料信息
        Integer count = memberWriteDao.update(memberNew);
        if (count == 0) {
            throw new BusinessException("修改密码失败，请重试！");
        }

        return member;
    }


    /**
     * 判断支付密码是否正确
     * @param balancePwd
     * @param request
     * @return  返回错误次数
     */
    public FrontCheckPwdVO checkcheckBalancePwd(String balancePwd, Integer memberId) {

        FrontCheckPwdVO vo = new FrontCheckPwdVO();
        boolean correct = false;
        // 参数校验
        if (StringUtil.isEmpty(balancePwd)) {
            throw new BusinessException("支付密码不能为空，请重试！");
        }

        Member memberdb = memberReadDao.get(memberId);
        if (StringUtil.isEmpty(memberdb.getBalancePwd())) {
            throw new BusinessException("未设置支付密码，请在余额菜单设置");
        }

        if (!memberdb.getBalancePwd().equals(Md5.getMd5String(balancePwd))) {
            //密码不正确，记录输错次数
            int errcount = memberdb.getPwdErrCount();
            memberdb.setPwdErrCount(errcount + 1);
            int count = memberWriteDao.update(memberdb);
            if (count == 0) {
                throw new BusinessException("验证密码时失败，请重试！");
            }
        } else {
            correct = true;
            // 输入正确后，错误次数清零
            memberdb.setPwdErrCount(0);
            int count = memberWriteDao.update(memberdb);
            if (count == 0) {
                throw new BusinessException("验证密码时失败，请重试！");
            }
        }
        vo.setCorrect(correct);
        vo.setPwdErrCount(memberdb.getPwdErrCount());

        return vo;
    }

    /**
     * 支付密码修改
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    public Member editBalancePassword(String oldPwd, String newPwd, Integer memberId) {
        // 参数校验
        if (StringUtil.isEmpty(oldPwd)) {
            throw new BusinessException("旧支付密码不能为空，请重试！");
        } else if (StringUtil.isEmpty(newPwd)) {
            throw new BusinessException("新支付密码不能为空，请重试！");
        }

        //获得会员信息
        Member memberinfo = memberReadDao.get(memberId);
        if (!memberinfo.getBalancePwd().equals(Md5.getMd5String(oldPwd))) {
            throw new BusinessException("原支付密码不正确");
        } else {
            memberinfo.setBalancePwd(Md5.getMd5String(newPwd));
            //修改或者重置支付密码后将错误次数置零
            memberinfo.setPwdErrCount(0);
            //1、更新个人资料信息
            Integer count = memberWriteDao.update(memberinfo);
            if (count == 0) {
                throw new BusinessException("修改支付密码失败，请重试！");
            }
        }

        return memberinfo;
    }

    /**
     * 设置支付密码
     * @param password 支付密码
     * @param request
     * @return
     */
    public Member addBalancePassword(String password, Member member) {

        // 参数校验
        if (StringUtil.isEmpty(password)) {
            throw new BusinessException("支付密码不能为空，请重试！");
        }

        //获得会员信息
        Member memberinfo = memberReadDao.get(member.getId());
        if (!StringUtil.isEmpty(memberinfo.getBalancePwd())) {
            throw new BusinessException("已经设置过支付密码了");
        } else {
            memberinfo.setBalancePwd(Md5.getMd5String(password));
            //1、更新个人资料信息
            Integer count = memberWriteDao.update(memberinfo);
            if (count == 0) {
                throw new BusinessException("设置支付密码失败，请重试！");
            }
        }

        return memberinfo;
    }
    
    public List<Member>getTuijianMemberByMemberId(
			Integer memberId){
    	return memberReadDao.getParterTuijianByMemberId(memberId);
    }
    public List<Member>getParterTuijianByMemberId1(
    		Integer memberId,String memeberTuijianId){
    	return memberReadDao.getParterTuijianByMemberId1(memberId,memeberTuijianId);
    }

}
