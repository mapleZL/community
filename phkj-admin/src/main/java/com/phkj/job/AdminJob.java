package com.phkj.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.relate.StBaseinfoBuilding;
import com.phkj.entity.relate.StBaseinfoHouses;
import com.phkj.entity.relate.StBaseinfoOrganization;
import com.phkj.entity.relate.StBaseinfoUnits;
import com.phkj.entity.system.Code;
import com.phkj.model.relate.StBaseinfoBuildingModel;
import com.phkj.model.relate.StBaseinfoHousesModel;
import com.phkj.model.relate.StBaseinfoOrganizationModel;
import com.phkj.model.relate.StBaseinfoUnitsModel;
import com.phkj.model.system.CodeModel;

/**
 * 
 *                       
 * @Filename: AdminJob.java
 * @Version: 1.0
 * @date: 2019年5月14日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class AdminJob {

    @Resource
    private CodeModel                   codeModel;
    @Resource
    private StBaseinfoBuildingModel     stBaseinfoBuildingModel;
    @Resource
    private StBaseinfoHousesModel       stBaseinfoHousesModel;
    @Resource
    private StBaseinfoOrganizationModel stBaseinfoOrganizationModel;
    @Resource
    private StBaseinfoUnitsModel        stBaseinfoUnitsModel;
    @Resource
    private RedisComponent              redisComponent;

    private static final Logger         log = LogManager.getLogger(AdminJob.class);

    /**
     * 字典定时更新
     */
    public void jobUpdateCode() {
        List<Code> codes = codeModel.getAllCodes();
        Map<String, List<Code>> codeMap = new HashMap<>();
        if (codes != null) {
            List<Code> tempList = null;
            for (Code code : codes) {
                if (codeMap.containsKey(code.getCodeDiv())) {
                    tempList = codeMap.get(code.getCodeDiv());
                } else {
                    tempList = new ArrayList<>();
                    codeMap.put(code.getCodeDiv(), tempList);
                }
                tempList.add(code);
            }
            redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_VALUE_KEY,
                JSONObject.toJSONString(tempList));
            log.info("字典表更新成功");
        } else {
            log.error("定时更新字典表发生错误，未查询到数据");
        }
    }

    /**
     * 同步房屋信息中的下拉列表
     */
    public void jobSynchroSpinner() {
        log.info("同步社区下拉列表开始...");
        List<StBaseinfoOrganization> organizations = null;
        try {
            organizations = stBaseinfoOrganizationModel.getOranizations();

            if (organizations != null) {
                // 解析省市区
                Map<StBaseinfoOrganization, Map<StBaseinfoOrganization, List<StBaseinfoOrganization>>> treeMap = new HashMap<>();
                Map<StBaseinfoOrganization, List<StBaseinfoOrganization>> cityMap = null;
                List<StBaseinfoOrganization> areaList = null;
                // 确定省级
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("province")) {
                        cityMap = new HashMap<>();
                        treeMap.put(organization, cityMap);
                    }
                }
                // 确定市级
                for (StBaseinfoOrganization cityKey : treeMap.keySet()) {
                    for (StBaseinfoOrganization organization : organizations) {
                        if (organization.getRegion().equals("city")
                            && organization.getTopId() == cityKey.getId()) {
                            cityMap = treeMap.get(cityKey);
                            areaList = new ArrayList<>();
                            cityMap.put(organization, areaList);
                        }
                    }
                }
                // 确定区县
                for (StBaseinfoOrganization cityKey : treeMap.keySet()) {
                    cityMap = treeMap.get(cityKey);
                    for (StBaseinfoOrganization areaKey : cityMap.keySet()) {
                        for (StBaseinfoOrganization organization : organizations) {
                            if (organization.getRegion().equals("county")
                                && areaKey.getId() == organization.getTopId()) {
                                areaList = cityMap.get(areaKey);
                                areaList.add(organization);
                            }
                        }
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_PROVINCE_SYN,
                    JSONObject.toJSONString(treeMap));
                log.info("省市区同步成功！" + JSONObject.toJSONString(treeMap));
                
                // 解析街道，社区，小区
                treeMap = new HashMap<>();
                Map<StBaseinfoOrganization, List<StBaseinfoOrganization>> communityMap = null;
                List<StBaseinfoOrganization> residentiaList = null;
                // 街道
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("street")) {
                        communityMap = new HashMap<>();
                        treeMap.put(organization, communityMap);
                    }
                }
                // 确定社区
                for (StBaseinfoOrganization streetKey : treeMap.keySet()) {
                    for (StBaseinfoOrganization organization : organizations) {
                        if (organization.getRegion().equals("community")
                            && organization.getTopId() == streetKey.getId()) {
                            cityMap = treeMap.get(streetKey);
                            residentiaList = new ArrayList<>();
                            cityMap.put(organization, residentiaList);
                        }
                    }
                }
                // 确定区县
                for (StBaseinfoOrganization streetKey : treeMap.keySet()) {
                    communityMap = treeMap.get(streetKey);
                    for (StBaseinfoOrganization residentiaKey : communityMap.keySet()) {
                        for (StBaseinfoOrganization organization : organizations) {
                            if (organization.getRegion().equals("residentia")
                                && residentiaKey.getId() == organization.getTopId()) {
                                residentiaList = cityMap.get(residentiaKey);
                                residentiaList.add(organization);
                            }
                        }
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_STREET_SYN,
                    JSONObject.toJSONString(treeMap));
                log.info("街道社区小区下拉列表信息同步成功！" + JSONObject.toJSONString(treeMap));
            }
            // 获取楼幢
            List<StBaseinfoBuilding> buildings = stBaseinfoBuildingModel.getBuildings();
            // 获取单元
            List<StBaseinfoUnits> units = stBaseinfoUnitsModel.getUnits();
            // 获取室
            List<StBaseinfoHouses> houses = stBaseinfoHousesModel.getHouse();
            Map<Long, List<StBaseinfoBuilding>> buildingsMap = new HashMap<>();
            List<StBaseinfoBuilding> buildingList = null;
            for (StBaseinfoBuilding building : buildings) {
                if (buildingsMap.containsKey(building.getResidentiaId())) {
                    buildingList = buildingsMap.get(building.getResidentiaId());
                } else {
                    buildingList = new ArrayList<>();
                    buildingsMap.put(building.getResidentiaId(), buildingList);
                }
                buildingList.add(building);
            }
            redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_BUILD_SYN,
                JSONObject.toJSONString(buildingsMap));
            log.info("楼幢信息同步成功！");
            
            Map<Long, List<StBaseinfoUnits>> unitMap = new HashMap<>();
            List<StBaseinfoUnits> unitList = null;
            for (StBaseinfoUnits unit : units) {
                if (unitMap.keySet().contains(unit.getBuildingId())) {
                    unitList = unitMap.get(unit.getBuildingId());
                } else {
                    unitList = new ArrayList<>();
                    unitMap.put(unit.getBuildingId(), unitList);
                }
                unitList.add(unit);
            }
            redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_UNIT_SYN,
                JSONObject.toJSONString(unitMap));
            log.info("单元信息同步成功！");
            
            Map<Long, Map<Long, List<StBaseinfoHouses>>> houseMap = new HashMap<>();
            Map<Long, List<StBaseinfoHouses>> unitsMap = null;
            List<StBaseinfoHouses> houseList = null;
            for (StBaseinfoHouses house : houses) {
                if (!houseMap.keySet().contains(house.getBuildingId())) {
                    unitsMap = new HashMap<>();
                    houseMap.put(house.getBuildingId(), unitsMap);
                }
            }
            for (Long buildingId : houseMap.keySet()) {
                Map<Long, List<StBaseinfoHouses>> temHouse = houseMap.get(buildingId);
                for (StBaseinfoHouses house : houses) {
                    if (temHouse.keySet().contains(house.getUnitId())) {
                        houseList = temHouse.get(house.getUnitId());
                    } else {
                        houseList = new ArrayList<>();
                        temHouse.put(house.getUnitId(), houseList);
                    }
                    houseList.add(house);
                }
            }
            redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_HOUSE_SYN,
                JSONObject.toJSONString(houseMap));
            log.info("室同步成功！");
        } catch (Exception e) {
            log.error("房屋认证页面同步数据失败", e);
        }
    }
}
