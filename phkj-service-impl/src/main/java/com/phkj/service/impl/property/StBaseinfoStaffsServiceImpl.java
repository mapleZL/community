package com.phkj.service.impl.property;

import com.phkj.core.AESHelper;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.property.PropertiItem;
import com.phkj.entity.property.StBaseinfoDepartment;
import com.phkj.entity.property.StBaseinfoStaffs;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.model.property.StBaseinfoDepartmentModel;
import com.phkj.model.property.StBaseinfoStaffsModel;
import com.phkj.service.property.IStBaseinfoStaffsService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "stBaseinfoStaffsService")
public class StBaseinfoStaffsServiceImpl implements IStBaseinfoStaffsService {
    private static Logger             log = LogManager.getLogger(StBaseinfoStaffsServiceImpl.class);

    @Resource
    private StBaseinfoStaffsModel     stBaseinfoStaffsModel;

    @Resource
    private StBaseinfoDepartmentModel stBaseinfoDepartmentModel;

    /**
     * 根据id取得工作人员信息管理表
     *
     * @param stBaseinfoStaffsId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoStaffs> getStBaseinfoStaffsById(Integer stBaseinfoStaffsId) {
        ServiceResult<StBaseinfoStaffs> result = new ServiceResult<StBaseinfoStaffs>();
        try {
            result.setResult(stBaseinfoStaffsModel.getStBaseinfoStaffsById(stBaseinfoStaffsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoStaffsService][getStBaseinfoStaffsById]根据id["
                      + stBaseinfoStaffsId + "]取得工作人员信息管理表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoStaffsService][getStBaseinfoStaffsById]根据id["
                      + stBaseinfoStaffsId + "]取得工作人员信息管理表时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * create by: zl
     * description: 物业人员列表
     * create time:
     *
     * @return
     * @Param: pageNum
     * @Param: pageSize
     */
    @Override
    public ServiceResult<List<PropertiItem>> getStaffsOnDutyList() {
        ServiceResult<List<PropertiItem>> result = new ServiceResult<>();
        try {
            List<PropertiItem> items = new ArrayList<>();
            List<Long> list = new ArrayList<>();
            // 获取值班人员
            List<StBaseinfoStaffs> staffsOnDutyList = stBaseinfoStaffsModel.getStaffsOnDutyList();
            for (StBaseinfoStaffs stBaseinfoStaffs : staffsOnDutyList) {
                list.add(stBaseinfoStaffs.getJobsId());
                PropertiItem propertiItem = new PropertiItem();
                propertiItem.setName(stBaseinfoStaffs.getName());
                propertiItem.setPhone(stBaseinfoStaffs.getMobile());
                propertiItem.setJobsId(stBaseinfoStaffs.getJobsId());
                items.add(propertiItem);
            }
            // 获取人员部门及领导id
            List<StBaseinfoDepartment> departmentList = stBaseinfoDepartmentModel
                .getDepartmentList(list);
            for (PropertiItem item : items) {
                for (StBaseinfoDepartment department : departmentList) {
                    if (item.getJobsId().equals(department.getId())) {
                        item.setDepartment(department.getName());
                        item.setTopId(department.getTopId());
                        break;
                    }
                }
            }
            list.clear();
            for (StBaseinfoDepartment department : departmentList) {
                list.add(department.getTopId());
            }
            //            departmentList.forEach(department -> list.add(department.getTopId()));
            // 获取领导姓名和手机号
            List<StBaseinfoStaffs> staffs = stBaseinfoStaffsModel.getStaffsByJobsId(list);
            for (PropertiItem item : items) {
                for (StBaseinfoStaffs staff : staffs) {
                    if (item.getTopId().equals(staff.getJobsId())) {
                        item.setLeaderName(staff.getName());
                        item.setLeaderPhone(staff.getMobile());
                        break;
                    }
                }
            }
            result.setResult(items);
            result.setMessage("ok");
            result.setSuccess(true);
            result.setCode("200");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IStBaseinfoStaffsService][getStaffsOnDutyList]获取StBaseinfoStaffs对象列表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStBaseinfoStaffsService][getStaffsOnDutyList]获取StBaseinfoStaffs对象列表时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoStaffsModel.saveStBaseinfoStaffs(stBaseinfoStaffs));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoStaffsService][saveStBaseinfoStaffs]保存工作人员信息管理表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoStaffsService][saveStBaseinfoStaffs]保存工作人员信息管理表时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更新工作人员信息管理表
     *
     * @param stBaseinfoStaffs
     * @return
     */
    @Override
    public ServiceResult<Integer> updateStBaseinfoStaffs(StBaseinfoStaffs stBaseinfoStaffs) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoStaffsModel.updateStBaseinfoStaffs(stBaseinfoStaffs));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoStaffsService][updateStBaseinfoStaffs]更新工作人员信息管理表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoStaffsService][updateStBaseinfoStaffs]更新工作人员信息管理表时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查看无业人员是否已经登记
     * @param admin
     * @return
     * @see com.phkj.service.property.IStBaseinfoStaffsService#checkAdminUser(com.phkj.entity.system.SystemAdmin)
     */
    @Override
    public boolean checkAdminUser(SystemAdmin admin) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", admin.getRelName());
        String idNo = AESHelper.Encrypt(admin.getIdNo());
        param.put("idNo", idNo);
        return stBaseinfoStaffsModel.getStaffsByParam(param) > 0;
    }
}