package com.ejavashop.web.util;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.CookieHelper;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterSignService;

/**
 * 用户Session对象管理
 *                       
 * @Filename: WebFrontSession.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public class WebFrontSession {
    protected static org.apache.log4j.Logger log = org.apache.log4j.LogManager.getLogger(WebFrontSession.class);

    private static String MEMBER_SESSION_NAME = "memberSession";

    /**
     * member放入session中
     * @param request
     * @param member
     * @throws Exception
     */
    public static void putMemberSession(HttpServletRequest request, Member member) throws Exception {
        //        putMemberSession(request, member, null, null, null, null);
        HttpSession session = request.getSession();
        //        if (member != null) {
        //            session.setAttribute(MEMBER_SESSION_NAME, member);
        //        }
        MemberSession memberSession = new MemberSession();
        if (member != null) {
            memberSession.setMember(member);
        }
        session.setAttribute(MEMBER_SESSION_NAME, memberSession);
    }

    public static void removeMemberSession(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(MEMBER_SESSION_NAME);
        }
    }

    /**
     * 对象放入session中
     * @param request
     * @param member
     * @throws Exception
     */
    public static void putObjToSession(HttpServletRequest request, String key, Object value) {
        HttpSession session = request.getSession();
        session.setAttribute(key, value);
    }

    /**
     * 从session中取出对象
     * @param request
     * @param member
     * @throws Exception
     */
    public static Object getObjFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        return session.getAttribute(key);
    }

    /**
     * 从session中移除对象
     * @param request
     * @param member
     * @throws Exception
     */
    public static void delObjFromSession(HttpServletRequest request, String key) {
        HttpSession session = request.getSession();
        session.removeAttribute(key);
        ;
    }

    /**
     * 获取用户session
     * @param request
     * @return
     * @throws Exception
     */
    public static MemberSession getMemberSession(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        MemberSession memberSession = (MemberSession) session.getAttribute(MEMBER_SESSION_NAME);
        return memberSession;

        //memcache 分布式session启用
        //        Object memberSession = XMemcacheClient
        //            .get(ConstantsJM.NAMESPACE_MOBILEMALL_WEB_MEMBER_SESSION + getSessionId(request));
        //        if (memberSession == null)
        //            return null;
        //        return JSON.parseObject((String) memberSession, MemberSession.class);
    }

    public static String getSompleShop(HttpServletRequest request) {
        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getEJS_COOKIE_NAME());
        if (cookie == null) {
            return null;
        }
        return cookie.getValue();
    }

    public static String getSessionId(HttpServletRequest request) {
        String sessionId = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
//                System.out.println("cookie.getName() = " + cookie.getName());
                if (DomainUrlUtil.getEJS_COOKIE_NAME().equals(cookie.getName()) && cookie.getValue() != null) {
                    sessionId = cookie.getValue();
                    int sid = 0;
                    try {
                        sid = Integer.parseInt(cookie.getValue());
                    }
                    catch (Exception e) {
                        return null;
                    }
                    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
                    IMemberService memberService = (IMemberService) context.getBean("memberService");
                    ServiceResult<Member> serviceResult = memberService.getMemberById(sid);
                    Integer status = 1;
                    if (serviceResult.getResult() != null && !"".equals(serviceResult.getResult().getId())) {
                        Integer memberId = serviceResult.getResult().getId();
                        IOrdersService ordersService = (IOrdersService) context.getBean("ordersService");
                        ServiceResult<Integer> ordersResult = ordersService.getOrdersByMemberId(memberId);
                        status = ordersResult.getResult();
                    }

                    try {
                        Member member = serviceResult.getResult();
                        //add by tzm 查询用户进货单是否存在数据
                        if(member!=null){
                            //通过用户ID来查询用户订单数量
                            ICartService cartService = (ICartService) context.getBean("cartService");
                            ServiceResult<Integer> serviceResult2 = cartService.getCartNumberByMId(member.getId());
                            //如果用户进货单中存在数量，则前端底部导航进货单则显示一个提示图标
                            if(serviceResult2.getResult()!=null && serviceResult2.getResult()!=0){
                                request.getSession().setAttribute("memberCartCount",serviceResult2.getResult());
                            }
                        }
                        //end
                        member.setStatus(status);
                        // 登录送经验值积分
                        memberService.memberLoginSendValue(member.getId(), member.getName());
                        // 存入session
                        WebFrontSession.putMemberSession(request, member);
                        //加入cookie

                        IParterSignService parterSignService = (IParterSignService) context.getBean("parterSignService");
                        ServiceResult<List<ParterSign>> parterSignServiceResult = parterSignService.getByMemeberId(""+member.getId());
                        if(parterSignServiceResult.getSuccess()){
                            List<ParterSign>  parterSign = parterSignServiceResult.getResult();
                            if(parterSign != null && parterSign.size() > 0){
                                WebFrontSession.putObjToSession(request, "is_parter_sign", "true");
                            }else{
                                WebFrontSession.putObjToSession(request, "is_parter_sign", "false");
                            }
                        }
                        
                    } catch (Exception e) {
                        
                    }
                    break;
                }
            }
        } else {
            sessionId = request.getSession().getId();
        }
        return sessionId;
    }

    /**
     * 在公共头将sessionId写入cookie
     * @param request
     * @param response
     */
    public static void addSessionIdToCookie(HttpServletRequest request, HttpServletResponse response) {
        // 从request中取回cookie，判断存sessionId的cookie对象是否存在
//        Cookie[] cookies = request.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (DomainUrlUtil.getEJS_COOKIE_NAME().equals(cookie.getName()) && cookie.getValue() != null) {
//                    return;
//                }
//            }
//        }
        // sessionId存入cookie
//        Cookie cookie = new Cookie(DomainUrlUtil.getEJS_COOKIE_NAME(), request.getSession().getId());
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        Integer memberId = sessionMember.getId();
        Cookie cookie = new Cookie(DomainUrlUtil.getEJS_COOKIE_NAME(), memberId+"");
        cookie.setMaxAge(30 * 24 * 60 * 60); // cookie缓存一个月
//        cookie.setDomain(DomainUrlUtil.getEJS_COOKIE_DOMAIN());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static void removeSessionIdToCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (DomainUrlUtil.getEJS_COOKIE_NAME().equals(cookie.getName()) && cookie.getValue() != null) {
                    Cookie ck = new Cookie(cookie.getName(),null);
                    ck.setMaxAge(0);
                    ck.setPath("/");
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }

//    public static void putMemberSession(HttpServletRequest request, Integer provinceId,
//                                        Integer cityId, Integer regionId,
//                                        String regionName) throws Exception {
//        putMemberSession(request, null, provinceId, cityId, regionId, regionName);
//    }
//
//    /**
//     * 把会员实体、省市区信息存入MemberSession实体，并存入memcache
//     * @param request
//     * @param member
//     * @param provinceId
//     * @param cityId
//     * @param regionId
//     * @param regionName
//     * @throws Exception
//     */
//    private static void putMemberSession(HttpServletRequest request, Member member,
//                                         Integer provinceId, Integer cityId, Integer regionId,
//                                         String regionName) throws Exception {
//        String sessionId = getSessionId(request);
//        //TODO
//        MemberSession memberSession = new MemberSession();
//        if (member != null) {
//            memberSession.setMember(member);
//        }
//        if (provinceId != null && cityId != null && regionId != null) {
//            memberSession.setProvince(provinceId);
//            memberSession.setCity(cityId);
//            memberSession.setRegion(regionId);
//            memberSession.setRegionName(regionName);
//        }
//        //        //TODO
//        //        Object memberSessionObj = XMemcacheClient
//        //            .get(ConstantsJM.NAMESPACE_MOBILEMALL_WEB_MEMBER_SESSION + getSessionId(request));
//        //        MemberSession memberSession = null;
//        //        if (memberSessionObj == null) {
//        //            memberSession = new MemberSession();
//        //        } else {
//        //            memberSession = JSON.parseObject((String) memberSessionObj, MemberSession.class);
//        //        }
//        //        if (member != null) {
//        //            memberSession.setMember(member);
//        //        }
//        //        if (provinceId != null && cityId != null && regionId != null) {
//        //            memberSession.setProvince(provinceId);
//        //            memberSession.setCity(cityId);
//        //            memberSession.setRegion(regionId);
//        //            memberSession.setRegionName(regionName);
//        //        }
//        //        XMemcacheClient.set(ConstantsJM.NAMESPACE_MOBILEMALL_WEB_MEMBER_SESSION + sessionId,
//        //            7 * 24 * 60 * 60, JSON.toJSONString(memberSession));
//    }

    /**
     * 获取用户验证码
     * @param request
     * @return
     * @throws Exception
     */
    public static String getVerifyNumber(HttpServletRequest request) {
        String verify_number = (String) request.getSession().getAttribute(ConstantsEJS.VERIFY_NUMBER_NAME);
        return verify_number;
    }
    
    /**
     * 获取用户名
     * @param request
     * @return
     * @throws Exception
     */
    public static String getVerifyUser(HttpServletRequest request) throws Exception {
    	String verify_user = (String) request.getSession().getAttribute(ConstantsEJS.USER_NUMBER_NAME);
    	return verify_user;
    }

    public static Member getLoginedUser(HttpServletRequest request) {
        Member member = new Member();
        try {
            MemberSession memberSession = getMemberSession(request);
            if (memberSession == null) {
                if (getSessionId(request) != null && getMemberSession(request) != null)
                    return getMemberSession(request).getMember();
                else
                    return null;
                // throw new BusinessException("会员信息获取失败，请重试！");
            }
            member = memberSession.getMember();
            if (member == null) {
                return null;
                // throw new BusinessException("会员信息获取失败，请重试！");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return member;
    }
    
}
