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
import com.ejavashop.entity.member.MemberCreditLog;
import com.ejavashop.model.member.MemberCreditLogModel;
import com.ejavashop.service.member.IMemberCreditLogService;

@Service(value = "memberCreditLogService")
public class MemberCreditLogServiceImpl implements IMemberCreditLogService {
    private static Logger        log = LogManager.getLogger(MemberCreditLogServiceImpl.class);

    @Resource
    private MemberCreditLogModel memberCreditLogModel;

    /**
    * 根据id取得赊账记录
    * @param  memberCreditLogId
    * @return
    */
    @Override
    public ServiceResult<MemberCreditLog> getMemberCreditLogById(Integer memberCreditLogId) {
        ServiceResult<MemberCreditLog> result = new ServiceResult<MemberCreditLog>();
        try {
            result.setResult(memberCreditLogModel.getMemberCreditLogById(memberCreditLogId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditLogService][getMemberCreditLogById]根据id[" + memberCreditLogId
                      + "]取得赊账记录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditLogService][getMemberCreditLogById]根据id[" + memberCreditLogId
                      + "]取得赊账记录时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存赊账记录
     * @param  memberCreditLog
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberCreditLog(MemberCreditLog memberCreditLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCreditLogModel.saveMemberCreditLog(memberCreditLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditLogService][saveMemberCreditLog]保存赊账记录时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditLogService][saveMemberCreditLog]保存赊账记录时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新赊账记录
    * @param  memberCreditLog
    * @return
    * @see com.ejavashop.service.MemberCreditLogService#updateMemberCreditLog(MemberCreditLog)
    */
    @Override
    public ServiceResult<Integer> updateMemberCreditLog(MemberCreditLog memberCreditLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCreditLogModel.updateMemberCreditLog(memberCreditLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCreditLogService][updateMemberCreditLog]更新赊账记录时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCreditLogService][updateMemberCreditLog]更新赊账记录时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberCreditLog>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<MemberCreditLog>> serviceResult = new ServiceResult<List<MemberCreditLog>>();
        try {
            serviceResult.setResult(memberCreditLogModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[MemberCreditLogServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberCreditLogModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[MemberCreditLogServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MemberCreditLog>> getAllMemberCreditLog(Map<String, String> queryMap) {
        ServiceResult<List<MemberCreditLog>> serviceResult = new ServiceResult<List<MemberCreditLog>>();
        try {
            serviceResult.setResult(memberCreditLogModel.getAllMemberCreditLog(queryMap));
        } catch (Exception e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        }
        return serviceResult;
    }
}