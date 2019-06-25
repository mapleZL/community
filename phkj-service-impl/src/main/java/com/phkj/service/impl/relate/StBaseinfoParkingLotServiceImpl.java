package com.phkj.service.impl.relate;

import java.util.*;

import javax.annotation.Resource;

import com.phkj.core.redis.RedisComponent;
import com.phkj.dao.shop.read.member.MemberHouseReadDao;
import com.phkj.dao.shop.read.visit.StAppletReadVisitDao;
import com.phkj.dao.shopm.read.relate.StBaseinfoParkingLotOrderDao;
import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import com.phkj.entity.visit.StAppletVisitor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.model.relate.StBaseinfoParkingLotModel;
import com.phkj.service.relate.IStBaseinfoParkingLotService;

@Service(value = "stBaseinfoParkingLotService")
public class StBaseinfoParkingLotServiceImpl implements IStBaseinfoParkingLotService {
    private static Logger log = LogManager.getLogger(StBaseinfoParkingLotServiceImpl.class);

    @Resource
    private StBaseinfoParkingLotModel stBaseinfoParkingLotModel;

    @Autowired
    RedisComponent redisComponent;

    @Autowired
    MemberHouseReadDao memberHouseReadDao;

    @Autowired
    StBaseinfoParkingLotOrderDao parkingLotOrderDao;

    @Autowired
    StAppletReadVisitDao stAppletReadVisitDao;

    /**
     * 根据id取得车位信息
     *
     * @param stBaseinfoParkingLotId
     * @return
     */
    @Override
    public ServiceResult<StBaseinfoParkingLot> getStBaseinfoParkingLotById(Long stBaseinfoParkingLotId) {
        ServiceResult<StBaseinfoParkingLot> result = new ServiceResult<StBaseinfoParkingLot>();
        try {
            result.setResult(stBaseinfoParkingLotModel.getStBaseinfoParkingLotById(stBaseinfoParkingLotId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getStBaseinfoParkingLotById]根据id[" + stBaseinfoParkingLotId + "]取得车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getStBaseinfoParkingLotById]根据id[" + stBaseinfoParkingLotId + "]取得车位信息时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoParkingLotModel.saveStBaseinfoParkingLot(stBaseinfoParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][saveStBaseinfoParkingLot]保存车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][saveStBaseinfoParkingLot]保存车位信息时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 更新车位信息
     *
     * @param stBaseinfoParkingLot
     * @return
     * @see //com.phkj.service.StBaseinfoParkingLotService#updateStBaseinfoParkingLot(StBaseinfoParkingLot)
     */
    @Override
    public ServiceResult<Integer> updateStBaseinfoParkingLot(StBaseinfoParkingLot stBaseinfoParkingLot) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBaseinfoParkingLotModel.updateStBaseinfoParkingLot(stBaseinfoParkingLot));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][updateStBaseinfoParkingLot]更新车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][updateStBaseinfoParkingLot]更新车位信息时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StBaseinfoParkingLot>> getRelatedParkingLot(Long residentinfoId) {
        ServiceResult<List<StBaseinfoParkingLot>> result = new ServiceResult<List<StBaseinfoParkingLot>>();
        try {
            result.setResult(stBaseinfoParkingLotModel.getRelatedParkingLot(residentinfoId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getRelatedParkingLot]根据id[" + residentinfoId + "]取得车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getRelatedParkingLot]根据id[" + residentinfoId + "]取得车位信息时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * create by: zl
     * description: 获取剩余车位
     * create time:
     *
     * @return
     * @Param: orgCode
     */
    @Override
    public ServiceResult<List<StBaseinfoParkingLot>> getSurplusParkingLot(String orgCode, String userId) {
        ServiceResult<List<StBaseinfoParkingLot>> result = new ServiceResult<>();
        try {

            //   ====测试逻辑
         /*   String key = RedisKeyCommon.JS_PARKING_KEY + userId;
            String redisString = redisComponent.getRedisString(key);
            Map<String, Object> map = null;
            if (StringUtils.isNotBlank(redisString)) {
                map = JSON.parseObject(redisString, Map.class);
            }
            List<StBaseinfoParkingLot> list = new ArrayList<>();
            List<StBaseinfoParkingLot> surplusParkingLot = stBaseinfoParkingLotModel.getSurplusParkingLot(orgCode);
            if (surplusParkingLot != null && surplusParkingLot.size() > 0) {
                for (StBaseinfoParkingLot parking : surplusParkingLot) {
                    parking.setSts("0");
                    if (null != map) {
                        Object id = map.get(parking.getId().toString());
                        if (id != null) {
                            parking.setSts("1");
                        }
                    }
                    list.add(parking);
                }
            }
*/
            // 最终版本
            List<StBaseinfoParkingLot> parkingLot = stBaseinfoParkingLotModel.getSurplusParkingLotAndMeParking(
                    orgCode, userId);
            // 结束 ======
            result.setResult(parkingLot);
            result.setSuccess(true);
            result.setMessage("ok");
            result.setCode("200");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * @param orgCode
     * @return
     */
    @Override
    public ServiceResult<List<Map<String, Object>>> getNearbyParkingLot(String orgCode) {
        ServiceResult<List<Map<String, Object>>> result = new ServiceResult<>();
        try {
            List<Map<String, Object>> nearbyParkingLot = stBaseinfoParkingLotModel.getNearbyParkingLot(orgCode);
            result.setResult(nearbyParkingLot);
            result.setSuccess(true);
            result.setMessage("ok");
            result.setCode("200");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：",
                    e);
        }
        return result;
    }


    /**
     * @param orgCode
     * @param
     * @return
     */
    @Override
    public ServiceResult<Integer> getSurplusParkingLotNum(String orgCode) {
        ServiceResult<Integer> result = new ServiceResult<>();
        try {
            result.setResult(stBaseinfoParkingLotModel.getSurplusParkingLotNum(orgCode));
            result.setSuccess(true);
            result.setMessage("ok");
            result.setCode("200");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoParkingLotService][getSurplusParkingLot]根据[" + orgCode + "]取得剩余车位信息时出现未知异常：",
                    e);
        }
        return result;
    }


    /**
     * @param parkingLotOrder
     * @return
     */
    @Override
    public boolean applyParkingLot(StBaseinfoParkingLotOrder parkingLotOrder) {
        // 拼接Key
//        String key = RedisKeyCommon.JS_PARKING_KEY + userId;
//        long time = startTime.getTime();
//        long nowTime = new Date().getTime();
//        long l = time - nowTime;
//        if (l < 1) {
//            l = 600000l;
//        }
//        String redisString = redisComponent.getRedisString(key);
//        Map<String, Object> returnMap = null;
//        if (StringUtils.isNotBlank(redisString)) {
//            returnMap = JSON.parseObject(redisString, Map.class);
//            String value = userId + "_" + userName;
//            returnMap.put(pkId, value);
//            String json = JSON.toJSONString(returnMap);
//            redisComponent.setStringExpire(key, json, l);
//        } else {
//            returnMap = new HashMap<String, Object>();
//            String value = userId + "_" + userName;
//            returnMap.put(pkId, value);
//            String json = JSON.toJSONString(returnMap);
//            redisComponent.setStringExpire(key, json, l);
//        }
        parkingLotOrder.setSts("1");
        parkingLotOrder.setCreateTime(new Date());
        return true;
    }


    /**
     * @param parkingLot
     * @return
     */
    @Override
    public String applyParking(StBaseinfoParkingLotOrder parkingLot) {
        Long houseId = parkingLot.getHouseId();
        // 判断用户信息
        String type = parkingLot.getType();

        /**
         *  访客车位 判断是否有申请预约访问
         */
        if ("B".equals(type)) {
            List<StAppletVisitor> list = stAppletReadVisitDao.selectByUserId(parkingLot.getWechatUserId().toString());
            if (null == list || list.size() == 0) {
                return "暂无访客申请!";
            }
            StAppletVisitor visitor = list.get(0);
            long time = visitor.getOverTime().getTime();
            long nowDate = new Date().getTime();
            if (time < nowDate) {
                return "访客申请过期!";
            }
        }
        // 如果没问题申请成功
        parkingLot.setSts("Y");
        parkingLot.setStatus("Y");
        parkingLot.setCreateTime(new Date());
        int i = parkingLotOrderDao.insert(parkingLot);
        if (i < 0) {
            return "预约失败!";
        }
        return "";
    }
}