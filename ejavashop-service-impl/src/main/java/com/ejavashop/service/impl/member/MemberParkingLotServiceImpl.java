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
import com.ejavashop.entity.member.MemberParkingLot;
import com.ejavashop.model.member.MemberParkingLotModel;
import com.ejavashop.service.member.IMemberParkingLotService;

/**
 *                       
 * @Filename: MemberParkingLotServiceImpl.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "memberParkingLotService")
public class MemberParkingLotServiceImpl implements IMemberParkingLotService {
	private static Logger      log = LogManager.getLogger(MemberParkingLotServiceImpl.class);
	
	@Resource
	private MemberParkingLotModel memberParkingLotModel;
    
     /**
     * 根据id取得member_parking_lot对象
     * @param  memberParkingLotId
     * @return
     */
    @Override
    public ServiceResult<MemberParkingLot> getMemberParkingLotById(Integer memberParkingLotId) {
        ServiceResult<MemberParkingLot> result = new ServiceResult<MemberParkingLot>();
        try {
            result.setResult(memberParkingLotModel.getMemberParkingLotById(memberParkingLotId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberParkingLotService][getMemberParkingLotById]根据id["+memberParkingLotId+"]取得member_parking_lot对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberParkingLotService][getMemberParkingLotById]根据id["+memberParkingLotId+"]取得member_parking_lot对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存member_parking_lot对象
     * @param  memberParkingLot
     * @return
     */
     @Override
     public ServiceResult<Integer> saveMemberParkingLot(MemberParkingLot memberParkingLot) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberParkingLotModel.saveMemberParkingLot(memberParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberParkingLotService][saveMemberParkingLot]保存member_parking_lot对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberParkingLotService][saveMemberParkingLot]保存member_parking_lot对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新member_parking_lot对象
     * @param  memberParkingLot
     * @return
     * @see com.ejavashop.service.MemberParkingLotService#updateMemberParkingLot(MemberParkingLot)
     */
     @Override
     public ServiceResult<Integer> updateMemberParkingLot(MemberParkingLot memberParkingLot) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberParkingLotModel.updateMemberParkingLot(memberParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberParkingLotService][updateMemberParkingLot]更新member_parking_lot对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberParkingLotService][updateMemberParkingLot]更新member_parking_lot对象时出现未知异常：",
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
     public ServiceResult<List<MemberParkingLot>> page(Map<String, String> queryMap, PagerInfo pager) {
         ServiceResult<List<MemberParkingLot>> serviceResult = new ServiceResult<List<MemberParkingLot>>();
         serviceResult.setPager(pager);
         try {
             Integer start = 0, size = 0;
             if (pager != null) {
                 pager.setRowsCount(memberParkingLotModel.getMemberParkingCount(queryMap));
                 start = pager.getStart();
                 size = pager.getPageSize();
             }
             serviceResult.setResult(memberParkingLotModel.getMemberParkingList(queryMap, start, size));
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
             result.setResult(memberParkingLotModel.changeStatus(id, state));
         } catch (BusinessException e) {
             result.setSuccess(false);
             result.setMessage(e.getMessage());
             log.error("[memberHouseService][changeStatus]根据id[" + id + "]审核房屋状态时出现异常："
                       + e.getMessage());
         } catch (Exception e) {
             result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
             log.error("[memberHouseService][changeStatus]审核房屋状态时发生异常:", e);
         }
         return result;
     }
}