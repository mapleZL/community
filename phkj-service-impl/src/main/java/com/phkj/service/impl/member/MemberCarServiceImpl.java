package com.phkj.service.impl.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.member.MemberCar;
import com.phkj.model.member.MemberCarModel;
import com.phkj.service.member.IMemberCarService;

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
        ServiceResult<MemberCar> result = new ServiceResult<>();
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
            memberCar.setCreateDate(new Date());
            memberCar.setStatus(1);
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

    /**
     * create by: zl
     * description: 获取我的车辆列表
     * create time:
     *
     * @return
     * @Param: queryMap
     * @Param: pager
     */
    @Override
    public ServiceResult<List<MemberCar>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<MemberCar>> serviceResult = new ServiceResult<List<MemberCar>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberCarModel, "Property 'memberCarModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberCarModel.getMemberCarCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberCarModel.getMemberCarList(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IMemberCarService][page]查询车辆表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMemberCarService][page]param1:" + JSON.toJSONString(queryMap)
                    + " &param2:" + JSON.toJSONString(pager));
            log.error("[IMemberCarService][page]查询车辆信息发生异常:", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<Boolean> changeStatus(Integer id, int state) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberCarModel.changeStatus(id, state));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[memberCarService][changeStatus]根据id[" + id + "]审核车辆状态时出现异常："
                    + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[memberCarService][changeStatus]审核车辆状态时发生异常:", e);
        }
        return result;
    }

    /**
     * create by: zl
     * description: 我的车辆列表列表
     * create time:
     *
     * @return
     * @Param: memberId
     * @Param: pageNum
     * @Param: pageSize
     */
    public ServiceResult<List<MemberCar>> getMyMemberCarList(Integer memberId, String villageCode, int pageNum, int pageSize) {
        ServiceResult<List<MemberCar>> result = new ServiceResult<>();
        try {
            pageNum = (pageNum - 1) * pageSize;
            result.setResult(memberCarModel.getMyMemberCarList(memberId, villageCode, pageNum, pageSize));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                    "[memberCarService][getMyMemberCarList]获取MemberCar对象列表时出现未知异常："
                            + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                    "[memberCarService][getMyMemberCarList]获取MemberCar对象列表时出现未知异常：",
                    e);
        }
        return result;
    }
}