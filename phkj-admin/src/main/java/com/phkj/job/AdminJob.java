package com.phkj.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.relate.StBaseinfoBuilding;
import com.phkj.entity.relate.StBaseinfoHouses;
import com.phkj.entity.relate.StBaseinfoOrganization;
import com.phkj.entity.relate.StBaseinfoUnits;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.entity.system.Code;
import com.phkj.model.notice.StBrowseModel;
import com.phkj.model.relate.StBaseinfoBuildingModel;
import com.phkj.model.relate.StBaseinfoHousesModel;
import com.phkj.model.relate.StBaseinfoOrganizationModel;
import com.phkj.model.relate.StBaseinfoUnitsModel;
import com.phkj.model.system.CodeModel;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;

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

    @Autowired
    private CodeModel                             codeModel;
    @Autowired
    private StBaseinfoBuildingModel               stBaseinfoBuildingModel;
    @Autowired
    private StBaseinfoHousesModel                 stBaseinfoHousesModel;
    @Autowired
    private StBaseinfoOrganizationModel           stBaseinfoOrganizationModel;
    @Autowired
    private StBaseinfoUnitsModel                  stBaseinfoUnitsModel;
    @Autowired
    private RedisComponent                        redisComponent;
    @Autowired
    private StBrowseModel                         stBrowseModel;
    @Autowired
    private IStNoticeBulletinReleaseManageService noticeBulletinReleaseManageService;

    private static final Logger                   log = LogManager.getLogger(AdminJob.class);

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
                Map<Integer, List<StBaseinfoOrganization>> tempMap = null;
                List<StBaseinfoOrganization> areaList = null;
                // 确定省级
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("province")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_PROVINCE_SYN,
                    JSONObject.toJSONString(tempMap));
                // 确定市级
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("city")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_CITY_SYN,
                    JSONObject.toJSONString(tempMap));
                // 确定区县
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("county")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_AREA_SYN,
                    JSONObject.toJSONString(tempMap));
                log.info("省市区同步成功！");

                // 解析街道，社区，小区
                // 街道
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("street")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_STREET_SYN,
                    JSONObject.toJSONString(tempMap));
                // 确定社区
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("community")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_COMMUNITY_SYN,
                    JSONObject.toJSONString(tempMap));
                // 确定小区
                //TODO 此处先不处理分区的情况
                tempMap = new HashMap<>();
                for (StBaseinfoOrganization organization : organizations) {
                    if (organization.getRegion().equals("residentia")) {
                        if (tempMap.get(organization.getTopId()) == null) {
                            areaList = new ArrayList<>();
                            tempMap.put(organization.getTopId(), areaList);
                        } else {
                            areaList = tempMap.get(organization.getTopId());
                        }
                        areaList.add(organization);
                    }
                }
                redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_RESIDENTIAN_SYN,
                    JSONObject.toJSONString(tempMap));
                log.info("街道社区小区下拉列表信息同步成功！" + JSONObject.toJSONString(tempMap));
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
                    if (house.getBuildingId().longValue() == buildingId.longValue()) {
                        if (temHouse.keySet().contains(house.getUnitId())) {
                            houseList = temHouse.get(house.getUnitId());
                        } else {
                            houseList = new ArrayList<>();
                            temHouse.put(house.getUnitId(), houseList);
                        }
                        houseList.add(house);
                    }
                }
            }
            redisComponent.setStringPersistence(RedisSychroKeyConfig.CODE_HOUSE_SYN,
                JSONObject.toJSONString(houseMap));
            log.info("室同步成功！");
        } catch (Exception e) {
            log.error("房屋认证页面同步数据失败", e);
        }
    }

    /**
     * 更新redis头条流量进数据库
     */
    public void BrowseUpdateRedisJob() {
        StBrowse stBrowse = null;
        String redisKey  = null;
        Long count = 0L;
        try {
            List<StNoticeBulletinReleaseManage> list = noticeBulletinReleaseManageService.pageList(0, 1000, "1").getResult();
            if (list != null) {
                for (StNoticeBulletinReleaseManage notice : list) {
                    stBrowse = stBrowseModel.getBrowseByNoticeId(notice.getId());
                    redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + stBrowse.getId();
                    // 判断redis是否已经存在该条流量信息
                    if (stBrowse != null && stBrowse.getId() > 0) {
                        count = redisComponent.increment(redisKey, 0L);
                        // 可能是redis服务异常无数据，将数据库原有的信息更新进去
                        if (count < 1) {
                            redisComponent.increment(redisKey, stBrowse.getBrowseVolume());
                        } else {
                            stBrowse.setBrowseVolume(count);
                            stBrowseModel.updateStBrowse(stBrowse);
                        }
                    } else {
                        count = redisComponent.increment(redisKey, 0L);
                        stBrowse = new StBrowse();
                        stBrowse.setNoticeId(notice.getId());
                        stBrowse.setBrowseVolume(count);
                        stBrowse.setCreateTime(new Date());
                        stBrowseModel.saveStBrowse(stBrowse);
                    }
                }
                log.info("更新小区头条流量成功 ，共 ：" + list.size() + "条");
            } else {
                log.info("暂无需要更新小区头条记录");
            }
        } catch (Exception e) {
            log.error("更细头条流量信息失败", e);
        }
    }
}
