package com.phkj.entity.member;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/6/14 21:24
 * @description：房屋信息缓存
 * @modified By：
 * @version: 0.0.1$
 */
public class MemberHourseParam implements Serializable {
    /**
     * ｛
     * regionId
     * region
     * streetId
     * street
     * communityId
     * community
     * villageId
     * village
     * villageCode
     * ｝
     */
    private String regionId;
    private String region;
    private Integer streetId;
    private String street;
    private Integer communityId;
    private String community;
    private Integer villageId;
    private String village;
    private String villageCode;
    private Integer memberId;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getStreetId() {
        return streetId;
    }

    public void setStreetId(Integer streetId) {
        this.streetId = streetId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public Integer getVillageId() {
        return villageId;
    }

    public void setVillageId(Integer villageId) {
        this.villageId = villageId;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String toJSONString() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("regionId", regionId);
        jsonObject.put("region", region);
        jsonObject.put("streetId", streetId);
        jsonObject.put("street", street);
        jsonObject.put("communityId", communityId);
        jsonObject.put("community", community);
        jsonObject.put("villageId", villageId);
        jsonObject.put("village", village);
        jsonObject.put("villageCode", villageCode);
        return jsonObject.toJSONString();
    }
}
