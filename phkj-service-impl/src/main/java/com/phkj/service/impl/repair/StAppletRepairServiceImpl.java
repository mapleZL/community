package com.phkj.service.impl.repair;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.model.repair.StAppletRepairModel;
import com.phkj.service.repair.IStAppletRepairService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "stAppletRepairService")
public class StAppletRepairServiceImpl implements IStAppletRepairService {
    private static Logger log = LogManager.getLogger(StAppletRepairServiceImpl.class);

    @Resource
    private StAppletRepairModel stAppletRepairModel;

    /**
     * 根据id取得st_applet_repair对象
     *
     * @param stAppletRepairId
     * @return
     */
    @Override
    public ServiceResult<StAppletRepair> getStAppletRepairById(Integer stAppletRepairId) {
        ServiceResult<StAppletRepair> result = new ServiceResult<StAppletRepair>();
        try {
            result.setResult(stAppletRepairModel.getStAppletRepairById(stAppletRepairId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairService][getStAppletRepairById]根据id[" + stAppletRepairId + "]取得st_applet_repair对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairService][getStAppletRepairById]根据id[" + stAppletRepairId + "]取得st_applet_repair对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletRepair(StAppletRepair stAppletRepair) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            Date date = new Date();
            stAppletRepair.setModifyTime(date);
            stAppletRepair.setCreateTime(date);
            stAppletRepair.setSts(1);
            result.setResult(stAppletRepairModel.saveStAppletRepair(stAppletRepair));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairService][saveStAppletRepair]保存st_applet_repair对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairService][saveStAppletRepair]保存st_applet_repair对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 更新st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    @Override
    public ServiceResult<Integer> updateStAppletRepair(StAppletRepair stAppletRepair) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            stAppletRepair.setModifyTime(new Date());
            result.setResult(stAppletRepairModel.updateStAppletRepair(stAppletRepair));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairService][updateStAppletRepair]更新st_applet_repair对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairService][updateStAppletRepair]更新st_applet_repair对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * create by: zl
     * description: 查询报修记录列表
     * create time:
     *
     * @return
     * @Param: createUserId
     * @Param: pageNum
     * @Param: pageSize
     */
    @Override
    public ServiceResult<List<StAppletRepair>> getStAppletRepairList(String createUserId, int pageNum, int pageSize) {
        ServiceResult<List<StAppletRepair>> result = new ServiceResult<>();
        try {
            pageNum = (pageNum - 1) * pageSize;
            result.setResult(stAppletRepairModel.getStAppletRepairList(createUserId, pageNum, pageSize));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRepairService][getStAppletRepairList]获取st_applet_repair对象列表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRepairService][getStAppletRepairList]获取st_applet_repair对象列表时出现未知异常：",
                    e);
        }
        return result;
    }
}