package com.phkj.service.impl.event;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.event.StAppletHotEvents;
import com.phkj.model.event.StAppletHotEventsModel;
import com.phkj.service.event.IStAppletHotEventsService;

@Service(value = "stAppletHotEventsService")
public class StAppletHotEventsServiceImpl implements IStAppletHotEventsService {
	private static Logger      log = LogManager.getLogger(StAppletHotEventsServiceImpl.class);
	
	@Resource
	private StAppletHotEventsModel stAppletHotEventsModel;
    
     /**
     * 根据id取得st_applet_hot_events对象
     * @param  stAppletHotEventsId
     * @return
     */
    @Override
    public ServiceResult<StAppletHotEvents> getStAppletHotEventsById(Integer stAppletHotEventsId) {
        ServiceResult<StAppletHotEvents> result = new ServiceResult<StAppletHotEvents>();
        try {
            result.setResult(stAppletHotEventsModel.getStAppletHotEventsById(stAppletHotEventsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletHotEventsService][getStAppletHotEventsById]根据id["+stAppletHotEventsId+"]取得st_applet_hot_events对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletHotEventsService][getStAppletHotEventsById]根据id["+stAppletHotEventsId+"]取得st_applet_hot_events对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_applet_hot_events对象
     * @param  stAppletHotEvents
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletHotEvents(StAppletHotEvents stAppletHotEvents) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            stAppletHotEvents.setCreateTime(new Date());
            result.setResult(stAppletHotEventsModel.saveStAppletHotEvents(stAppletHotEvents));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletHotEventsService][saveStAppletHotEvents]保存st_applet_hot_events对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletHotEventsService][saveStAppletHotEvents]保存st_applet_hot_events对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_applet_hot_events对象
     * @param  stAppletHotEvents
     * @return
     * @see com.phkj.service.StAppletHotEventsService#updateStAppletHotEvents(StAppletHotEvents)
     */
     @Override
     public ServiceResult<Integer> updateStAppletHotEvents(StAppletHotEvents stAppletHotEvents) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletHotEventsModel.updateStAppletHotEvents(stAppletHotEvents));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletHotEventsService][updateStAppletHotEvents]更新st_applet_hot_events对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletHotEventsService][updateStAppletHotEvents]更新st_applet_hot_events对象时出现未知异常：",
                e);
        }
        return result;
     }

    @Override
    public ServiceResult<List<StAppletHotEvents>> getPageList(PagerInfo pager,
                                                              Map<String, Object> dataMap) {
        ServiceResult<List<StAppletHotEvents>> result = new ServiceResult<>();
        try {
            result.setResult(stAppletHotEventsModel.pageList(pager, dataMap));
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletHotEventsService][getPageList]查询st_applet_hot_events列表时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletHotEvents>> wxPage(Integer start, Integer size,
                                                         String villageCode) {
        ServiceResult<List<StAppletHotEvents>> result = new ServiceResult<>();
        try {
            start = (start - 1) * size;
            result.setResult(stAppletHotEventsModel.wxPage(start, size, villageCode));
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletHotEventsService][getPageList]查询st_applet_hot_events列表时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public Integer wxCount(String villageCode) {
        return stAppletHotEventsModel.wxCount(villageCode);
    }
}