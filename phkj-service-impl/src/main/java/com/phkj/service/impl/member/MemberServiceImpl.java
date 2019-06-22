package com.phkj.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.phkj.core.ResponseStateEnum;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.member.Member;
import com.phkj.entity.member.MemberCollectionProduct;
import com.phkj.entity.member.MemberCollectionSeller;
import com.phkj.entity.member.MemberLoginLogs;
import com.phkj.entity.member.MemberRule;
import com.phkj.model.member.MemberModel;
import com.phkj.service.member.IMemberService;

@Service(value = "memberService")
public class MemberServiceImpl implements IMemberService {
    private static Logger log = LogManager.getLogger(MemberServiceImpl.class);

    @Resource
    private MemberModel memberModel;

    @Override
    public ServiceResult<Member> getMemberById(Integer id) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            serviceResult.setResult(memberModel.getMemberById(id));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                    "[MemberService][getMemberById]根据id[" + id + "]取得会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberById]根据id[" + id + "]取得会员表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Member> getByMemberId(Integer memberId) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            serviceResult.setResult(memberModel.getByMemberId(memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                    "[MemberService][getMemberById]根据id[" + memberId + "]取得会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberById]根据id[" + memberId + "]取得会员表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveMember(Member member) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(memberModel.saveMember(member));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[MemberService][saveMember]保存会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][saveMember] param:" + JSON.toJSONString(member));
            log.error("[MemberService][saveMember]保存会员表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateMember(Member member) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberModel.updateMember(member));
            serviceResult.setMessage("ok");
            serviceResult.setSuccess(true);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[MemberService][updateMember]更新会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][updateMember] param:" + JSON.toJSONString(member));
            log.error("[MemberService][updateMember]更新会员表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Member>> getMembers(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Member>> serviceResult = new ServiceResult<List<Member>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberModel, "Property 'memberModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberModel.getMembersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberModel.getMembers(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[MemberService][getMembers]查询会员表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMembers]param1:" + JSON.toJSONString(queryMap)
                    + " &param2:" + JSON.toJSONString(pager));
            log.error("[MemberService][getMembers]查询会员信息发生异常:", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<List<MemberLoginLogs>> getMemberLoginLogs(Integer memberId,
                                                                   PagerInfo pager) {
        ServiceResult<List<MemberLoginLogs>> serviceResult = new ServiceResult<List<MemberLoginLogs>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberModel, "Property 'memberModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][getMemberLoginLogs]根据会员ID获取会员登录日志发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberLoginLogs]根据会员ID获取会员登录日志发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberCollectionSeller>> getMemberCollectionSellers(Integer memberId,
                                                                                  PagerInfo pager) {
        ServiceResult<List<MemberCollectionSeller>> serviceResult = new ServiceResult<List<MemberCollectionSeller>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberModel, "Property 'memberModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                    "[MemberService][getMemberCollectionSellers]根据会员ID获取会员收藏商铺发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberCollectionSellers]根据会员ID获取会员收藏商铺发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberCollectionProduct>> getMemberCollectionProducts(Integer memberId,
                                                                                    PagerInfo pager) {
        ServiceResult<List<MemberCollectionProduct>> serviceResult = new ServiceResult<List<MemberCollectionProduct>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberModel, "Property 'memberModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                start = pager.getStart();
                size = pager.getPageSize();
            }
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][getMemberCollectionProducts]根据会员ID获取会员收藏商品发生异常:"
                    + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberCollectionProducts]根据会员ID获取会员收藏商品发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Member> memberLogin(String memberName, String password, String ip,
                                             Integer source) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            serviceResult.setResult(memberModel.memberLogin(memberName, password, ip, source));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][memberLogin]会员登录时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][memberLogin]会员登录时发生异常:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<Member> memberRegister(Member member) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            log.info("memberRegister, 注册账号参数：" + member);
            serviceResult.setResult(memberModel.memberRegister(member));
            serviceResult.setCode("200");
            serviceResult.setSuccess(true);
            serviceResult.setMessage("ok");
//        } catch (BusinessException be) {
//            serviceResult.setSuccess(false);
//            serviceResult.setMessage(be.getMessage());
//            log.error("[MemberService][memberRegister]register exception:" + be);
        } catch (Exception e) {
            serviceResult.setError(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "服务异常，请联系系统管理员。");
            log.error("[MemberService][memberRegister]member:" + member);
            log.error("[MemberService][memberRegister]member register exception:{}", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<List<Member>> getMemberByName(String name) {
        ServiceResult<List<Member>> serviceResult = new ServiceResult<List<Member>>();
        try {
            serviceResult.setResult(memberModel.getMemberByName(name));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][getMemberByName]根据会员名称取会员时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberByName]name:" + name);
            log.error("[MemberService][getMemberByName]根据会员名称取会员时发生异常:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<Member> getMemberByPhone(String phone) {
        ServiceResult<Member> serviceResult = new ServiceResult<>();
        try {
            serviceResult.setResult(memberModel.getMemberByPhone(phone));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberService][getMemberByPhone]根据会员名称取会员时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][getMemberByPhone]phone:" + phone);
            log.error("[MemberService][getMemberByPhone]根据会员名称取会员时发生异常:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<MemberRule> getMemberRule(Integer memberRuleId, Integer state) {
        ServiceResult<MemberRule> serviceResult = new ServiceResult<MemberRule>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberService][getMemberRule]根据ID获取会员经验值积分规则发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> memberRegistSendValue(Integer memberId, String memberName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberService][memberRegistSendValue]会员注册时送经验值与积分发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> memberLoginSendValue(Integer memberId, String memberName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberService][memberLoginSendValue]会员登录时送经验值与积分发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> memberOrderSendValue(Integer memberId, String memberName,
                                                       Integer orderId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberService][memberOrderSendValue]会员下单时送经验值与积分发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> memberEvaluateSendValue(Integer memberId, String memberName,
                                                          Integer productId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberService][memberEvaluateSendValue]会员评论时送经验值与积分发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 修改密码提交
     *
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param member
     * @return
     */
    @Override
    public ServiceResult<Member> editPassword(String oldPwd, String newPwd, Member member) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            serviceResult.setResult(memberModel.editPassword(oldPwd, newPwd, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberExtendService][editPassword]修改密码时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 支付密码修改
     *
     * @param oldPwd   旧密码
     * @param newPwd   新密码
     * @param memberId
     * @return
     */
    @Override
    public ServiceResult<Member> editBalancePassword(String oldPwd, String newPwd,
                                                     Integer memberId) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();

        try {
            serviceResult.setResult(memberModel.editBalancePassword(oldPwd, newPwd, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberExtendService][editBalancePassword]修改支付密码时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 设置支付密码
     *
     * @param password 支付密码
     * @param member
     * @return
     */
    @Override
    public ServiceResult<Member> addBalancePassword(String password, Member member) {
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        try {
            serviceResult.setResult(memberModel.addBalancePassword(password, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[FrontMemberExtendService][addBalancePassword]设置支付密码时发生异常:", e);
        }
        return serviceResult;
    }

}