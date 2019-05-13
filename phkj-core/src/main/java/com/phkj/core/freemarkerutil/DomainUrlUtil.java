package com.phkj.core.freemarkerutil;

/**
 * 系统资源
 *                       
 * @Filename: DomainUrlUtil.java
 * @Version: 1.0
 * @Author: zl
 * @Email: wpjava@163.com
 *
 */
public class DomainUrlUtil { 
    protected static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(DomainUrlUtil.class);

    public static String                     EJS_URL_RESOURCES;               //主站的URL
    public static String                     EJS_FRONT_URL;                   //前台主域名
    public static String                     EJS_H5_URL;                      //移动端前台主域名
    public static String                     EJS_STATIC_RESOURCES;            //静态资源的URL
    public static String                     EJS_IMAGE_RESOURCES;             //图片资源的URL
    public static String                     EJS_COOKIE_DOMAIN;               // 主域名，用来存放cookie
    public static String                     EJS_COOKIE_NAME;                 //cookie名称

    public static String                     EJS_SOLR_URL;                    //solrURL
    public static String                     EJS_SOLR_SERVER;                 //solr 库
    public static String                     EJS_WMS_URL;                 
    public static String                     EJS_OMS_URL; 
    
    public static String                     OSS_ENDPOINT;
    public static String                     OSS_ACCESSKEYID;
    public static String                     OSS_ACCESSKEYSECRET;
    public static String                     OSS_BUCKETNAME;

    public static String                     SPRING_REDIS_HOST;                 // redis ip
    public static String                     SPRING_REDIS_PORT;                 // redis 端口
    public static String                     SPRING_REDIS_PASSWORD;             // redis 密码

    public static String getEJS_URL_RESOURCES() {
        return EJS_URL_RESOURCES;
    }

    public static void setEJS_URL_RESOURCES(String eJS_URL_RESOURCES) {
        EJS_URL_RESOURCES = eJS_URL_RESOURCES;
    }

    public static String getEJS_FRONT_URL() {
        return EJS_FRONT_URL;
    }

    public static void setEJS_FRONT_URL(String eJS_FRONT_URL) {
        EJS_FRONT_URL = eJS_FRONT_URL;
    }

    public static String getEJS_H5_URL() {
        return EJS_H5_URL;
    }

    public static void setEJS_H5_URL(String eJS_H5_URL) {
        EJS_H5_URL = eJS_H5_URL;
    }

    public static String getEJS_STATIC_RESOURCES() {
        return EJS_STATIC_RESOURCES;
    }

    public static void setEJS_STATIC_RESOURCES(String eJS_STATIC_RESOURCES) {
        EJS_STATIC_RESOURCES = eJS_STATIC_RESOURCES;
    }

    public static String getEJS_IMAGE_RESOURCES() {
        return EJS_IMAGE_RESOURCES;
    }

    public static void setEJS_IMAGE_RESOURCES(String eJS_IMAGE_RESOURCES) {
        EJS_IMAGE_RESOURCES = eJS_IMAGE_RESOURCES;
    }

    public static String getEJS_COOKIE_DOMAIN() {
        return EJS_COOKIE_DOMAIN;
    }

    public static void setEJS_COOKIE_DOMAIN(String eJS_COOKIE_DOMAIN) {
        EJS_COOKIE_DOMAIN = eJS_COOKIE_DOMAIN;
    }

    public static String getEJS_COOKIE_NAME() {
        return EJS_COOKIE_NAME;
    }

    public static void setEJS_COOKIE_NAME(String eJS_COOKIE_NAME) {
        EJS_COOKIE_NAME = eJS_COOKIE_NAME;
    }

    public static String getEJS_SOLR_URL() {
        return EJS_SOLR_URL;
    }

    public static void setEJS_SOLR_URL(String eJS_SOLR_URL) {
        EJS_SOLR_URL = eJS_SOLR_URL;
    }

    public static String getEJS_SOLR_SERVER() {
        return EJS_SOLR_SERVER;
    }

    public static void setEJS_SOLR_SERVER(String eJS_SOLR_SERVER) {
        EJS_SOLR_SERVER = eJS_SOLR_SERVER;
    }

    public static String getEJS_WMS_URL() {
        return EJS_WMS_URL;
    }

    public static void setEJS_WMS_URL(String eJS_WMS_URL) {
        EJS_WMS_URL = eJS_WMS_URL;
    }

	public static String getEJS_OMS_URL() {
		return EJS_OMS_URL;
	}

	public static void setEJS_OMS_URL(String eJS_OMS_URL) {
		EJS_OMS_URL = eJS_OMS_URL;
	}

    public static String getOSS_ENDPOINT() {
        return OSS_ENDPOINT;
    }

    public static void setOSS_ENDPOINT(String oSS_ENDPOINT) {
        OSS_ENDPOINT = oSS_ENDPOINT;
    }

    public static String getOSS_ACCESSKEYID() {
        return OSS_ACCESSKEYID;
    }

    public static void setOSS_ACCESSKEYID(String oSS_ACCESSKEYID) {
        OSS_ACCESSKEYID = oSS_ACCESSKEYID;
    }

    public static String getOSS_ACCESSKEYSECRET() {
        return OSS_ACCESSKEYSECRET;
    }

    public static void setOSS_ACCESSKEYSECRET(String oSS_ACCESSKEYSECRET) {
        OSS_ACCESSKEYSECRET = oSS_ACCESSKEYSECRET;
    }

    public static String getOSS_BUCKETNAME() {
        return OSS_BUCKETNAME;
    }

    public static void setOSS_BUCKETNAME(String oSS_BUCKETNAME) {
        OSS_BUCKETNAME = oSS_BUCKETNAME;
    }

    public static void setSPRING_REDIS_HOST(String sPRING_REDIS_HOST) {
        SPRING_REDIS_HOST = sPRING_REDIS_HOST;
    }

    public static String getSPRING_REDIS_HOST() {
        return SPRING_REDIS_HOST;
    }

    public static void setSPRING_REDIS_PORT(String sPRING_REDIS_PORT) {
        SPRING_REDIS_PORT = sPRING_REDIS_PORT;
    }

    public static String getSPRING_REDIS_PORT() {
        return SPRING_REDIS_PORT;
    }

    public static void setSPRING_REDIS_PASSWORD(String sPRING_REDIS_PASSWORD) {
        SPRING_REDIS_PASSWORD = sPRING_REDIS_PASSWORD;
    }

    public static String getSPRING_REDIS_PASSWORD() {
        return SPRING_REDIS_PASSWORD;
    }
}
