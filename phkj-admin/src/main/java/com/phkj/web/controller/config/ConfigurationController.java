package com.phkj.web.controller.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.IfFunc;
import org.omg.CORBA.ULongLongSeqHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.relate.StBaseinfoBuilding;
import com.phkj.entity.relate.StBaseinfoHouses;
import com.phkj.entity.relate.StBaseinfoOrganization;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.entity.relate.StBaseinfoPersonStock;
import com.phkj.entity.relate.StBaseinfoResidentCar;
import com.phkj.entity.relate.StBaseinfoResidentinfo;
import com.phkj.entity.relate.StBaseinfoUnits;
import com.phkj.service.relate.IStBaseinfoHousesService;
import com.phkj.service.relate.IStBaseinfoOrganizationService;
import com.phkj.service.relate.IStBaseinfoParkingLotService;
import com.phkj.service.relate.IStBaseinfoPersonStockService;
import com.phkj.service.relate.IStBaseinfoResidentCarService;
import com.phkj.service.relate.IStBaseinfoResidentinfoService;

/**
 * 
 *                       
 * @Filename: ConfigurationController.java
 * @Version: 1.0
 * @date: 2019年5月16日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@RequestMapping("/obtain/config")
@Controller
public class ConfigurationController {

    private static final Logger            log = LogManager
        .getLogger(ConfigurationController.class);

    @Autowired
    private RedisComponent                 redisComponent;
    @Autowired
    private IStBaseinfoPersonStockService  personStockService;
    @Autowired
    private IStBaseinfoResidentCarService  residentCarService;
    @Autowired
    private IStBaseinfoResidentinfoService residentinfoService;
    @Autowired
    private IStBaseinfoParkingLotService   parkingLotService;
    @Autowired
    private IStBaseinfoHousesService       houseServce;
    @Autowired
    private IStBaseinfoOrganizationService organizationService;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/linkage", method = RequestMethod.GET)
    public @ResponseBody ResponseUtil getProvinceTree(@RequestParam("configCode") String configCode,
                                                      Integer orgCode) {
        // 获取json结构原始数据
        String jsonString = redisComponent.getRedisString(configCode);
        List<StBaseinfoOrganization> list = new ArrayList<>();
        if (StringUtils.isNotBlank(jsonString)) {
            try {
                Map<Integer, List<StBaseinfoOrganization>> tempMap = JSONObject
                    .parseObject(jsonString, Map.class);
                list = tempMap.get(orgCode);
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true,
                    list);
            } catch (Exception e) {
                log.error("JSON转换出现异常：", e);
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                    "JSON转换出现异常", false, list);
            }
        } else {
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), null,
                true, list);
        }
    }

    @SuppressWarnings("unchecked")
    @RequestMapping("/buildingRelation")
    public @ResponseBody ResponseUtil getBuildingTree(@RequestParam("configCode") String configCode,
                                                      Integer relateId, String unitId) {
        Object returnObj = null;
        try {
            if (configCode.equals(RedisSychroKeyConfig.CODE_BUILD_SYN)) {
                String jsonString = redisComponent.getRedisString(configCode);
                Map<Integer, List<StBaseinfoBuilding>> buildingsMap = JSONObject
                    .parseObject(jsonString, Map.class);
                returnObj = buildingsMap.get(relateId);
            } else if (configCode.equals(RedisSychroKeyConfig.CODE_UNIT_SYN)) {
                String jsonString = redisComponent.getRedisString(configCode);
                Map<Long, List<StBaseinfoUnits>> unitMap = JSONObject.parseObject(jsonString,
                    Map.class);
                returnObj = unitMap.get(relateId);
            } else if (configCode.equals(RedisSychroKeyConfig.CODE_HOUSE_SYN)) {
                //TODO 此处待优化
                //                Map<Long, Map<Long, List<StBaseinfoHouses>>> houseMap = JSONObject
                //                    .parseObject(jsonString, Map.class);
                //                Map<Long, List<StBaseinfoHouses>> secondMap = houseMap.get(relateId);
                //                returnObj = secondMap.get(unitId);
                List<StBaseinfoHouses> list = houseServce.getHouseSpriner(relateId, unitId);
                returnObj = list;
            }
        } catch (Exception e) {
            log.error("联级查询失败：", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                "获取数据发生异常", false, returnObj);
        }
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true,
            returnObj);
    }

    @RequestMapping("/carSpinner")
    public @ResponseBody ResponseUtil getStCarList(@RequestParam("phone") String phone) {
        List<StBaseinfoResidentCar> list = null;
        try {
            StBaseinfoPersonStock personStock = personStockService.getStBaseinfoPersonStock(phone);
            if (personStock != null) {
                list = residentCarService.getAllCarByOwner(personStock.getId()).getResult();
            }
        } catch (Exception e) {
            log.error("查询居民对应车辆列表失败：", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                "查询居民车辆发生错误", false, null);
        }
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, list);
    }

    @RequestMapping("/carportSpinner")
    public @ResponseBody ResponseUtil getStCarportList(@RequestParam("phone") String phone) {
        List<StBaseinfoParkingLot> list = null;
        try {
            StBaseinfoResidentinfo residentinfo = residentinfoService.getResidentinfo(phone);
            if (residentinfo != null) {
                list = parkingLotService.getRelatedParkingLot(residentinfo.getId()).getResult();
            }
        } catch (Exception e) {
            log.error("查询居民对应车位列表失败：", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                "查询居民车位发生错误", false, null);
        }
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, list);
    }

    @RequestMapping("/getVillageName")
    public @ResponseBody ResponseUtil getVillageName(@RequestParam("villageCode") String villageCode) {
        StBaseinfoOrganization stBaseinfoOrganization = null;
        try {
            stBaseinfoOrganization = organizationService.getOrganization(villageCode);
            if (stBaseinfoOrganization != null && StringUtils.isNotBlank(stBaseinfoOrganization.getName())) {
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, stBaseinfoOrganization.getName());
            } else {
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), "暂无对应数据", true, null);
            }
        } catch (Exception e) {
            log.error("查询小区名称失败：", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                "查询小区名称失败", false, null);
        }
    }
}
