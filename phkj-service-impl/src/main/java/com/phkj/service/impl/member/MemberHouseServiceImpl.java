package com.phkj.service.impl.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.member.MemberHouse;
import com.phkj.model.member.MemberHouseModel;
import com.phkj.service.member.IMemberHouseService;

/**
 * 
 *                       
 * @Filename: MemberHouseServiceImpl.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "memberHouseService")
public class MemberHouseServiceImpl implements IMemberHouseService {
    private static Logger    log = LogManager.getLogger(MemberHouseServiceImpl.class);

    @Resource
    private MemberHouseModel memberHouseModel;

    /**
    * 根据id取得member_house对象
    * @param  memberHouseId
    * @return
    */
    @Override
    public ServiceResult<MemberHouse> getMemberHouseById(Integer memberHouseId) {
        ServiceResult<MemberHouse> result = new ServiceResult<MemberHouse>();
        try {
            result.setResult(memberHouseModel.getMemberHouseById(memberHouseId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberHouseService][getMemberHouseById]根据id[" + memberHouseId
                      + "]取得member_house对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][getMemberHouseById]根据id[" + memberHouseId
                      + "]取得member_house对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存member_house对象
     * @param  memberHouse
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberHouse(MemberHouse memberHouse) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            memberHouse.setStatus(1);
            memberHouse.setDeleted("1");
            memberHouse.setCreateDate(new Date());
            result.setResult(memberHouseModel.saveMemberHouse(memberHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IMemberHouseService][saveMemberHouse]保存member_house对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][saveMemberHouse]保存member_house对象时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新member_house对象
    * @param  memberHouse
    * @return
    */
    @Override
    public ServiceResult<Integer> updateMemberHouse(MemberHouse memberHouse) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberHouseModel.updateMemberHouse(memberHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberHouseService][updateMemberHouse]更新member_house对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][updateMemberHouse]更新member_house对象时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 
     * @param queryMap
     * @param pager
     * @return
     * @see com.phkj.service.member.IMemberHouseService#page(java.util.Map, com.phkj.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<MemberHouse>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<MemberHouse>> serviceResult = new ServiceResult<List<MemberHouse>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberHouseModel.getMemberHouseCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberHouseModel.getMemberHouseList(queryMap, start, size));
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberHouseService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[memberHouseService][page]查询会员信息发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> changeStatus(Integer id, int state) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberHouseModel.changeStatus(id, state));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[memberHouseService][changeStatus]根据id[" + id + "]审核房屋状态时出现异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberHouseService][changeStatus]审核房屋状态时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberHouse>> getAllHouse(String memberId) {
        ServiceResult<List<MemberHouse>> serviceResult = new ServiceResult<List<MemberHouse>>();
        try {
            serviceResult.setResult(memberHouseModel.getAllHouse(memberId));
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberHouseService][page]param1:" + memberId );
            log.error("[memberHouseService][page]查询会员房屋信息发生异常:", e);
        }
        return serviceResult;
    }
}