package com.phkj.core.ip;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ：zl
 * @date ：Created in 2019/5/15 13:54
 * @description：获取IP地址
 * @modified By：
 * @version: 0.0.1$
 */
public class IPUtil {
    static Logger logger = LoggerFactory.getLogger(IPUtil.class);

    public static String getIpAddr(HttpServletRequest request) {
        try {
            String ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            return ip;
        } catch (Exception e) {
            logger.error("获取IP地址异常,exception:{}", e);
            return null;
        }
    }
}
