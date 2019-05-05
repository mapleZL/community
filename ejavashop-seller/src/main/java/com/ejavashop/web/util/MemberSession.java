package com.ejavashop.web.util;

import com.ejavashop.entity.member.Member;

public class MemberSession {

    private Member  member;
    private Integer Province;
    private Integer City;
    private Integer Region;
    private String  regionName;
    private String  memberRank;

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Integer getProvince() {
        return Province;
    }

    public void setProvince(Integer province) {
        Province = province;
    }

    public Integer getCity() {
        return City;
    }

    public void setCity(Integer city) {
        City = city;
    }

    public Integer getRegion() {
        return Region;
    }

    public void setRegion(Integer region) {
        Region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(String memberRank) {
        this.memberRank = memberRank;
    }

}