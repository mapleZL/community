package com.phkj.service.impl.repair;

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
import com.phkj.entity.repair.StAppletRepairMember;
import com.phkj.model.repair.StAppletRepairMemberModel;
import com.phkj.service.repair.IStAppletRepairMemberService;
/**
 *                       
 * @Filename: StAppletRepairMemberServiceImpl.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "stAppletRepairMemberService")
public class StAppletRepairMemberServiceImpl implements IStAppletRepairMemberService {
	private static Logger      log = LogManager.getLogger(StAppletRepairMemberServiceImpl.class);
	
	@Resource
	private StAppletRepairMemberModel stAppletRepairMemberModel;
    
     /**
     * 根据id取得st_applet_repair_member对象
     * @param  stAppletRepairMemberId
     * @return
     */
    @Override
    public ServiceResult<StAppletRepairMember> getStAppletRepairMemberById(Integer stAppletRepairMemberId) {
        ServiceResult<StAppletRepairMember> result = new ServiceResult<StAppletRepairMember>();
        try {
            result.setResult(stAppletRepairMemberModel.getStAppletRepairMemberById(stAppletRepairMemberId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairMemberService][getStAppletRepairMemberById]根据id["+stAppletRepairMemberId+"]取得st_applet_repair_member对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairMemberService][getStAppletRepairMemberById]根据id["+stAppletRepairMemberId+"]取得st_applet_repair_member对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_applet_repair_member对象
     * @param  stAppletRepairMember
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletRepairMember(StAppletRepairMember stAppletRepairMember) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletRepairMemberModel.saveStAppletRepairMember(stAppletRepairMember));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairMemberService][saveStAppletRepairMember]保存st_applet_repair_member对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairMemberService][saveStAppletRepairMember]保存st_applet_repair_member对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_applet_repair_member对象
     * @param  stAppletRepairMember
     * @return
     * @see com.ejavashop.service.StAppletRepairMemberService#updateStAppletRepairMember(StAppletRepairMember)
     */
     @Override
     public ServiceResult<Integer> updateStAppletRepairMember(StAppletRepairMember stAppletRepairMember) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletRepairMemberModel.updateStAppletRepairMember(stAppletRepairMember));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairMemberService][updateStAppletRepairMember]更新st_applet_repair_member对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairMemberService][updateStAppletRepairMember]更新st_applet_repair_member对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<StAppletRepairMember>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<StAppletRepairMember>> serviceResult = new ServiceResult<List<StAppletRepairMember>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(stAppletRepairMemberModel.getRepaitMemberCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(stAppletRepairMemberModel.getRepaitMemberList(queryMap, start, size));
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IStAppletRepairMemberService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IStAppletRepairMemberService][page]查询会员信息发生异常:", e);
        }
        return serviceResult;
    }
}