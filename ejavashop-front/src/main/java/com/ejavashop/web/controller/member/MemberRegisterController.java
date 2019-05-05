package com.ejavashop.web.controller.member;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.sms.bean.VerifyCodeResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 会员注册controller
 * 
 * @Filename: MemberRegisterController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MemberRegisterController extends BaseController {

    @Resource
    private IMemberService memberService;
    @Resource
    private ISenderService senderService;
    @Resource
    private ICouponService couponService;

    /**
     * 获取手机验证码
     * @param request
     * @param response
     * @param dataMap
     * @param mob
     * @param verifycode
     * @return
     */
    @RequestMapping(value = "/mobVerify.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> mobVerify(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap, String mob) {
        HttpSession session = request.getSession();
        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
        try {
            ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(mob);
            if (!serviceResult.getSuccess()) {
                return new HttpJsonResult<Integer>("手机号校验出错，请重试");
            }

            //当天只能获取5次 
            if (session.getAttribute("exp_time") != null
                && isCur((long) session.getAttribute("exp_time"))
                && ((Integer) session.getAttribute("vc_count")) >= ConstantsEJS.SMS_MAX_GIVEN_NUM) {
                return new HttpJsonResult<Integer>("今日获取验证码次数过多,请明日再试");
            }

            ServiceResult<Object> result = senderService.sendVerifyCode(mob);
            if (result.getSuccess()) {
                VerifyCodeResult vcr = (VerifyCodeResult) result.getResult();

                //将信息放入当前会话
                //                System.out.println("############################" + vcr.getVerifyCode());
                session.setAttribute("smsCode", vcr.getVerifyCode());
                session.setAttribute("exp_time", new Date().getTime());
                session.setAttribute("vc_count", session.getAttribute("vc_count") == null ? 0
                    : ((Integer) session.getAttribute("vc_count")) + 1);

                log.debug("短信发送完毕：验证码：" + vcr.getVerifyCode() + "|" + vcr.toString());
                jsonResult.setData(Integer.valueOf(vcr.getVerifyCode()));
            } else {
                jsonResult.setMessage(result.getMessage());
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                jsonResult.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                jsonResult.setMessage("获取失败,请稍后重试");
            }
        }
        return jsonResult;
    }

    /**
     * 获取手机验证码
     * @param request
     * @param response
     * @param dataMap
     * @param mob 手机号码
     * @param verifycode 验证码
     * @return
     */
    @RequestMapping(value = "/sendVerifySMS.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> sendVerifySMS(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap,
                                                               String mob, String verifycode) {
        HttpSession session = request.getSession();
        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
        try {
            //获得session中的验证码
            String verify_number = WebFrontSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifycode)) {
                return new HttpJsonResult<Integer>("验证码不正确");
            }
            //判断用户名是否已存在
            ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(mob);
            if (!serviceResult.getSuccess()) {
                return new HttpJsonResult<Integer>("手机号校验出错，请重试");
            }
            if (serviceResult.getResult() != null && serviceResult.getResult().size() > 0) {
                return new HttpJsonResult<Integer>("手机号重复，请重新输入");
            }

            ServiceResult<Object> result = senderService.sendVerifyCode(mob);
            ////////////////test start///////////////
            //            ServiceResult<Object> result = new ServiceResult<Object>();
            //            VerifyCodeResult test = new VerifyCodeResult();
            //            test.setMessage("成功");
            //            test.setVerifyCode(RandomUtil.randomNumber(6));
            //            result.setResult(test);
            ////////////////test end///////////////
            if (result.getSuccess()) {
                VerifyCodeResult vcr = (VerifyCodeResult) result.getResult();

                //将信息放入当前会话
                //System.out.println("############################" + vcr.getVerifyCode());
                session.setAttribute("smsCode", vcr.getVerifyCode());
                session.setAttribute(ConstantsEJS.USER_NUMBER_NAME, mob);
                session.setAttribute("exp_time", new Date().getTime());
                session.setAttribute("vc_count", session.getAttribute("vc_count") == null ? 0
                    : ((Integer) session.getAttribute("vc_count")) + 1);

                //当天只能获取5次 	
                if (isCur((long) session.getAttribute("exp_time"))
                    && ((Integer) session.getAttribute("vc_count")) >= ConstantsEJS.SMS_MAX_GIVEN_NUM) {
                    return new HttpJsonResult<Integer>("今日获取验证码次数过多,请明日再试");
                }
                log.debug("短信发送完毕：验证码：" + vcr.getVerifyCode() + "|" + vcr.toString());
                
                
                // System.out.println("短信发送完毕：验证码："+vcr.getVerifyCode());
                jsonResult.setData(Integer.valueOf(vcr.getVerifyCode()));
            } else {
                jsonResult.setMessage(result.getMessage());
            }
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                jsonResult.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                jsonResult.setMessage("获取失败,请稍后重试");
            }
        }
        return jsonResult;
    }

    /**
     * 是否当天
     * @param attribute
     * @return
     */
    private boolean isCur(long time) {
        long now = new Date().getTime();
        long diff = (now - time) / (1000 * 60 * 60 * 24);
        return diff == 0;
    }

    /**
     * 查看用户协议
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/service_protocol.html", method = { RequestMethod.GET })
    public String protocol(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> stack) {
        return "front/member/serviceprotocol";
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/register.html", method = { RequestMethod.GET })
    public String signup(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> stack) {
        String saleCode = request.getParameter("saleCode") == null ? "" : (String) request
            .getParameter("saleCode");
        String inviterId = request.getParameter("inviterId") == null ? "" : (String) request
            .getParameter("inviterId");
        stack.put("saleCode", saleCode);
        stack.put("inviterId", inviterId);
//        return "front/member/membersignup_bak";
        return "front/member/fwc_register";
    }

    /**
     * 判断用户名是否已存在
     * @param request
     * @param response
     * @param member
     * @throws Exception 
     */
    @RequestMapping(value = "/nameIsExist.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> verify_membername(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam(value = "name", required = true) String name)
                                                                                                                              throws Exception {
        //判断用户名是否已存在
        ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(name);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>("用户名校验出错，请重试！");
        }

        if (serviceResult.getResult() != null && serviceResult.getResult().size() > 0) {
            return new HttpJsonResult<Boolean>("用户名重复，请重新输入！");
        }

        return new HttpJsonResult<Boolean>();
    }

    /**
     * 注册信息提交
     * @param request
     * @param response
     * @param stack
     * @param membervo
     * @throws Exception
     */
    @RequestMapping(value = "/doregister.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> signupSubmit(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             Member member, String verifyCode)
                                                                                              throws Exception {
        String userName = request.getParameter("name");
        String smsCode = request.getParameter("smsCode");
        String memberType = request.getParameter("memberType");

        String saleCode = request.getParameter("saleCode");
        String inviterId = request.getParameter("inviterId");

        HttpSession session = request.getSession();

        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Member>("验证码不正确！");
        }
        
        //获得session中的用户名
        String verify_user = WebFrontSession.getVerifyUser(request);
        if (verify_user == null || !verify_user.equals(userName)) {
        	return new HttpJsonResult<Member>("用户名称与发送短信验证码的手机号不一致！");
        }

        //校验验证码是否过期
        long time = (long) session.getAttribute("exp_time");
        long now = new Date().getTime();
        long diff = (now - time) / (1000 * 60);
        if (diff > 10) {
            return new HttpJsonResult<Member>("验证码已过期,请重新获取");
        }

        if (!smsCode.equals(session.getAttribute("smsCode"))) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }

        //判断用户名是否已存在
        ServiceResult<List<Member>> sr = memberService.getMemberByName(userName);
        if (!sr.getSuccess()) {
            return new HttpJsonResult<Member>("用户名校验出错，请重试");
        }
        if (sr.getResult() != null && sr.getResult().size() > 0) {
            return new HttpJsonResult<Member>("用户名重复，请重新输入");
        }

        // 初始化会员信息
        member.setPassword(Md5.getMd5String(member.getPassword()));
        member.setGrade(Member.GRADE_1);
        member.setGradeValue(0);
        member.setIntegral(0);
        member.setLastLoginIp(WebUtil.getIpAddr(request));
        member.setLoginNumber(0);
        member.setPwdErrCount(0);
        member.setSource(ConstantsEJS.SOURCE_1_PC);
        member.setBalance(new BigDecimal(0.00));
        member.setBalancePwd("");
        member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
        member.setIsSmsVerify(ConstantsEJS.YES_NO_0);
        member.setSmsVerifyCode("");
        member.setEmailVerifyCode("");
        member.setCanReceiveSms(1);
        member.setCanReceiveEmail(1);
        member.setStatus(Member.STATUS_1);
        member.setEmail("");
        member.setMemberType(Integer.parseInt(memberType));
        member.setInviterId(inviterId);
        member.setSaleCode(saleCode);
        member.setIsSyncOms("0");
        member.setIsTransferBussiness("0");
        try {
            long phone = Integer.parseInt(userName);
            member.setPhone("" + phone);
        } catch (NumberFormatException e) {
        }
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        String backUrl = "";
        if (!memberType.equals("1")) {
            ServiceResult<Member> serviceResult = memberService.memberRegister(member);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                }
            }
            WebFrontSession.putMemberSession(request, serviceResult.getResult());

            //注册时赠送经验值及积分
            memberService.memberRegistSendValue(member.getId(), member.getName());
            //注册成功后默认登录
            WebFrontSession.putMemberSession(request, member);
            /* 20161001起没有红包了
            //赠送8888大红包即七个红包  【仝照美】
            String currentTime = MemberRegisterController.getCurrentTime();
            int result = MemberRegisterController.timeCompare("2016-09-30 13:13:42", currentTime);
            //判断当前时间小于2016-09-30 13:13:42  才可以进行红包赠送
            if (result == 1) {
                Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

                PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

                ServiceResult<List<Coupon>> serviceResult2 = couponService.getCoupons(queryMap,
                    pager);
                List<Coupon> coupons = serviceResult2.getResult();
                Collections.reverse(coupons);

                List<Coupon> coupons2 = new ArrayList<Coupon>();
                for (int i = 0; i <= 6; i++) {
                    Coupon coupon2 = new Coupon();
                    coupon2 = coupons.get(i);
                    coupons2.add(coupon2);
                }
                Member sessionMember = WebFrontSession.getLoginedUser(request);
                for (Coupon coupon : coupons2) {
                    ServiceResult<Boolean> receiveCoupon = couponService.receiveCoupon(
                        coupon.getId(), sessionMember.getId());

                    if (!receiveCoupon.getSuccess()) {
                        // return new HttpJsonResult<Boolean>(receiveCoupon.getMessage());
                        jsonResult.setMessage(receiveCoupon.getMessage());
                    }
                }
            }
            */
        } else {
            //如果是供应商
            WebFrontSession.putObjToSession(request, "frontmember", member);
            backUrl = "/store/step2.html";
            request.getSession().setAttribute("safekey", new Date());
        }
        jsonResult.setBackUrl(backUrl);
        return jsonResult;
    }

    //获得当前时间
    public static String getCurrentTime() {
        Date currentTime = new Date();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateString = formatter.format(currentTime);

        return dateString;

    }

    //时间比较大小

    public static int timeCompare(String t1, String t2) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(formatter.parse(t1));
            c2.setTime(formatter.parse(t2));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = c1.compareTo(c2);

        return result;
    }

}
