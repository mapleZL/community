package com.ejavashop.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.model.member.MemberCreditModel;
import com.ejavashop.service.member.IMemberCreditService;

@Service(value = "memberCreditService")
public class MemberCreditServiceImpl implements IMemberCreditService {
    private static Logger     log = LogManager.getLogger(MemberCreditServiceImpl.class);

    @Resource
    private MemberCreditModel memberCreditModel;

    /**
    * 根据id取得赊账管理 
    * @param  memberCreditId
    * @return
    */
    @Override
    public ServiceResult<MemberCredit> getMemberCreditById(Integer memberCreditId) {
        ServiceResult<MemberCredit> result = new ServiceResult<MemberCredit>();
        try {
            result.setResult(memberCreditModel.getMemberCreditById(memberCreditId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditService][getMemberCreditById]根据id[" + memberCreditId
                      + "]取得赊账管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditService][getMemberCreditById]根据id[" + memberCreditId
                      + "]取得赊账管理时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存赊账管理
     * @param  memberCredit
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberCredit(MemberCredit memberCredit) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCreditModel.saveMemberCredit(memberCredit));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditService][saveMemberCredit]保存赊账管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditService][saveMemberCredit]保存赊账管理时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新赊账管理
    * @param  memberCredit
    * @return
    * @see com.ejavashop.service.MemberCreditService#updateMemberCredit(MemberCredit)
    */
    @Override
    public ServiceResult<Integer> updateMemberCredit(MemberCredit memberCredit) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCreditModel.updateMemberCredit(memberCredit));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditService][updateMemberCredit]更新赊账管理时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditService][updateMemberCredit]更新赊账管理时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberCredit>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<MemberCredit>> serviceResult = new ServiceResult<List<MemberCredit>>();
        try {
            serviceResult.setResult(memberCreditModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberCreditModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<MemberCredit> getMemberCreditByMemberId(Integer memberId) {
        ServiceResult<MemberCredit> serviceResult = new ServiceResult<MemberCredit>();
        try {
            serviceResult.setResult(memberCreditModel.getMemberCreditByMemberId(memberId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][getMemberCreditByMemberId] exception:"
                      + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> doEdit(String creditinfo, Integer id, String name) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            Boolean result = memberCreditModel.doEdit(creditinfo, id, name);
            serviceResult.setResult(result);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][doEdit] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobCredit() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            boolean result = memberCreditModel.jobCredit();
            serviceResult.setResult(result);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][jobCredit] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberCredit>> getAllMemberCredit(Map<String, String> queryMap) {
        ServiceResult<List<MemberCredit>> serviceResult = new ServiceResult<List<MemberCredit>>();
        try {
            serviceResult.setResult(memberCreditModel.getAllMemberCredit(queryMap));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberCreditServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}