package com.ejavashop.service.impl.member;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberCar;
import com.ejavashop.model.member.MemberCarModel;
import com.ejavashop.service.member.IMemberCarService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "memberCarService")
public class MemberCarServiceImpl implements IMemberCarService {
    private static Logger log = LogManager.getLogger(MemberCarServiceImpl.class);

    @Resource
    private MemberCarModel memberCarModel;

    /**
     * 根据id取得member_car对象
     *
     * @param memberCarId
     * @return
     */
    @Override
    public ServiceResult<MemberCar> getMemberCarById(Integer memberCarId) {
        ServiceResult<MemberCar> result = new ServiceResult<MemberCar>();
        try {
            result.setResult(memberCarModel.getMemberCarById(memberCarId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCarService][getMemberCarById]根据id[" + memberCarId + "]取得member_car对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCarService][getMemberCarById]根据id[" + memberCarId + "]取得member_car对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存member_car对象
     *
     * @param memberCar
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberCar(MemberCar memberCar) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCarModel.saveMemberCar(memberCar));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCarService][saveMemberCar]保存member_car对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCarService][saveMemberCar]保存member_car对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 更新member_car对象
     *
     * @param memberCar
     * @return
     * @see
     */
    @Override
    public ServiceResult<Integer> updateMemberCar(MemberCar memberCar) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberCarModel.updateMemberCar(memberCar));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberCarService][updateMemberCar]更新member_car对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberCarService][updateMemberCar]更新member_car对象时出现未知异常：",
                    e);
        }
        return result;
    }
}