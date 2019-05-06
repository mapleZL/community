package com.ejavashop.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberHouse;
import com.ejavashop.model.member.MemberHouseModel;
import com.ejavashop.service.member.IMemberHouseService;


@Service(value = "memberHouseService")
public class MemberHouseServiceImpl implements IMemberHouseService {
	private static Logger      log = LogManager.getLogger(MemberHouseServiceImpl.class);
	
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
            log.error("[IMemberHouseService][getMemberHouseById]根据id["+memberHouseId+"]取得member_house对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][getMemberHouseById]根据id["+memberHouseId+"]取得member_house对象时出现未知异常：",
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
            result.setResult(memberHouseModel.saveMemberHouse(memberHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberHouseService][saveMemberHouse]保存member_house对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][saveMemberHouse]保存member_house对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新member_house对象
     * @param  memberHouse
     * @return
     * @see com.ejavashop.service.MemberHouseService#updateMemberHouse(MemberHouse)
     */
     @Override
     public ServiceResult<Integer> updateMemberHouse(MemberHouse memberHouse) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberHouseModel.updateMemberHouse(memberHouse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberHouseService][updateMemberHouse]更新member_house对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberHouseService][updateMemberHouse]更新member_house对象时出现未知异常：",
                e);
        }
        return result;
     }

    /**
     * 
     * @param queryMap
     * @param pager
     * @return
     * @see com.ejavashop.service.member.IMemberHouseService#page(java.util.Map, com.ejavashop.core.PagerInfo)
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
}