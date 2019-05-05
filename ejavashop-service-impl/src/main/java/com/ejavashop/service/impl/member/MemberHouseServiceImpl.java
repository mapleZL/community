package com.ejavashop.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
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
}