package com.phkj.echarts.component;

/**
 * redis相关key
 *                       
 * @Filename: RedisSychroKeyConfig.java
 * @Version: 1.0
 * @date: 2019年5月15日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class RedisSychroKeyConfig {

    public static final String CODE_VALUE_KEY           = "codeValuesKey";
    // 省、市、区
    public static final String CODE_PROVINCE_SYN        = "provinceSynchroKey";
    public static final String CODE_CITY_SYN            = "citySynchroKey";
    public static final String CODE_AREA_SYN            = "areaSynchroKey";

    // 街道、社区、小区
    public static final String CODE_STREET_SYN          = "streetSynchroKey";
    public static final String CODE_COMMUNITY_SYN       = "communitySynchroKey";
    public static final String CODE_RESIDENTIAN_SYN     = "residentiaSynchroKey";
    // 楼幢
    public static final String CODE_BUILD_SYN           = "buildingSynchroKey";
    // 单元
    public static final String CODE_UNIT_SYN            = "unitSynchroKey";
    // 室
    public static final String CODE_HOUSE_SYN           = "houseSynchroKey";

    public static final String REDIS_CODE_BROWSE_PREFIX = "browseHeadlinesIdFlow_";
}
