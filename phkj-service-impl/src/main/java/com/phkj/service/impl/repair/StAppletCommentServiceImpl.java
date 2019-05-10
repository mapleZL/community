package com.phkj.service.impl.repair;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.repair.StAppletComment;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.model.repair.StAppletCommentModel;
import com.phkj.service.repair.IStAppletCommentService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service(value = "stAppletCommentService")
public class StAppletCommentServiceImpl implements IStAppletCommentService {
	private static Logger      log = LogManager.getLogger(StAppletCommentServiceImpl.class);
	
	@Resource
	private StAppletCommentModel stAppletCommentModel;
    
     /**
     * 根据id取得st_applet_comment对象
     * @param  stAppletCommentId
     * @return
     */
    @Override
    public ServiceResult<StAppletComment> getStAppletCommentById(Long stAppletCommentId) {
        ServiceResult<StAppletComment> result = new ServiceResult<StAppletComment>();
        try {
            result.setResult(stAppletCommentModel.getStAppletCommentById(stAppletCommentId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCommentService][getStAppletCommentById]根据id["+stAppletCommentId+"]取得st_applet_comment对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCommentService][getStAppletCommentById]根据id["+stAppletCommentId+"]取得st_applet_comment对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_applet_comment对象
     * @param  stAppletComment
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletComment(StAppletComment stAppletComment) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            stAppletComment.setCreateTime(new Date());
            stAppletComment.setSts(1);
            result.setResult(stAppletCommentModel.saveStAppletComment(stAppletComment));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCommentService][saveStAppletComment]保存st_applet_comment对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCommentService][saveStAppletComment]保存st_applet_comment对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_applet_comment对象
     * @param  stAppletComment
     * @return
     */
     @Override
     public ServiceResult<Integer> updateStAppletComment(StAppletComment stAppletComment) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCommentModel.updateStAppletComment(stAppletComment));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCommentService][updateStAppletComment]更新st_applet_comment对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCommentService][updateStAppletComment]更新st_applet_comment对象时出现未知异常：",
                e);
        }
        return result;
     }


    /**
     * create by: zl
     * description: 评论记录列表
     * create time:
     *
     * @return
     * @Param: createUserId
     * @Param: pageNum
     * @Param: pageSize
     */
    @Override
    public ServiceResult<List<StAppletComment>> getStAppletRepairList(Long rId, int pageNum, int pageSize) {
        ServiceResult<List<StAppletComment>> result = new ServiceResult<>();
        try {
            pageNum = (pageNum - 1) * pageSize;
            result.setResult(stAppletCommentModel.getStAppletCommentList(rId, pageNum, pageSize));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCommentService][getStAppletCommentList]获取st_applet_comment对象列表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCommentService][getStAppletCommentList]获取st_applet_comment对象列表时出现未知异常：",
                    e);
        }
        return result;
    }
}