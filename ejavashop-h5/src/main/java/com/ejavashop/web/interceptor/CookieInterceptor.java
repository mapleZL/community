package com.ejavashop.web.interceptor;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ejavashop.core.freemarkerutil.DomainUrlUtil;

/**
 * <p>
 *      cookie拦截器，如果cookie存在，不处理，如果不存在创建一个时间为一个月的cookie<br/>
 *      存储主域名的cookie，子域名都可以读取到cookie的值
 * </p>
 *                       
 * @Filename: CookieInterceptor.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class CookieInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (DomainUrlUtil.getEJS_COOKIE_NAME().equals(cookie.getName())
                    && cookie.getValue() != null) {
                    return true;
                }
            }
        }
        // 存入cookie
        Cookie cookie = new Cookie(DomainUrlUtil.getEJS_COOKIE_NAME(), UUID.randomUUID().toString());
        cookie.setMaxAge(30 * 24 * 60 * 60); // cookie缓存一个月
        cookie.setDomain(DomainUrlUtil.getEJS_COOKIE_DOMAIN());
        cookie.setPath("/");
        response.addCookie(cookie);
        return true;
    }

}
