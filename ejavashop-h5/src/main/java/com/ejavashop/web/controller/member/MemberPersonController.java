package com.ejavashop.web.controller.member;

import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.email.SendMail;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.core.sms.bean.VerifyCodeResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;
import com.google.gson.Gson;

/**
 * 用户中心：资料管理
 *                       
 */
@Controller
@RequestMapping(value = "member")
public class MemberPersonController extends BaseController {

    @Resource
    private IMemberService memberService;
    @Resource
    private ISenderService senderService;

    /**
     * 跳转到 个人资料界面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/info.html", method = { RequestMethod.GET })
    public String toPersonalfile(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap map) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.getMemberById(sessionMember.getId());

        Member member = null;
        if (serviceResult.getSuccess()) {
            member = serviceResult.getResult();
        }
        map.put("member", member);

          return "h5v1/member/person/personfile";
    }

    /**
     * 个人资料提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/saveinfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> personalfileSumbit(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   Member membervo)
                                                                                   throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> memberResult = memberService.getMemberById(sessionMember.getId());
        if (!memberResult.getSuccess()) {
            return new HttpJsonResult<Member>(memberResult.getMessage());
        }
        Member memberDb = memberResult.getResult();
        // 资料保存
        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setBirthday(membervo.getBirthday());
        memberNew.setGender(membervo.getGender());
        memberNew.setPhone(membervo.getPhone());
//        if (memberDb.getIsSmsVerify() == 0) {
            memberNew.setMobile(membervo.getMobile());
//        }
//        if (memberDb.getIsEmailVerify() == 0) {
            memberNew.setEmail(membervo.getEmail());
//        }
        memberNew.setQq(membervo.getQq());
      //add by tongzhaomei 新增 
        memberNew.setRealName(membervo.getRealName());
        memberNew.setQuhaoNum(membervo.getQuhaoNum());
        memberNew.setTeleNum(membervo.getTeleNum());
        memberNew.setFenjiNum(membervo.getFenjiNum());
        memberNew.setWechatNum(membervo.getWechatNum());
        memberNew.setCertificateNum(membervo.getCertificateNum());
        memberNew.setUserCertify(membervo.getUserCertify());
        memberNew.setWdName(membervo.getWdName());
        memberNew.setWdwebAddress(membervo.getWdwebAddress());
        memberNew.setUserAddress(membervo.getUserAddress());
        memberNew.setUserAddinfo(membervo.getUserAddinfo());
        memberNew.setDpAddress(membervo.getDpAddress());
        memberNew.setDpAddinfo(membervo.getDpAddinfo());
        //end
        ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 获取手机验证码
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/sendsmsverif.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> sendSmsVerif(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              String mobile) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            if (StringUtil.isEmpty(mobile)) {
                return new HttpJsonResult<Boolean>("请输入手机号码！");
            }
            Member sessionMember = WebFrontSession.getLoginedUser(request);

            Member memberNew = new Member();
            memberNew.setId(sessionMember.getId());
            memberNew.setMobile(mobile);

            try {
                this.isCanVerifySms(mobile);
            } catch (BusinessException e) {
                return new HttpJsonResult<Boolean>(e.getMessage());
            }

            ServletContext context = request.getSession().getServletContext();

            Object exttime = isNull(context.getAttribute("exp_time")) ? 0 : context
                .getAttribute("exp_time");
            Object count = isNull(context.getAttribute("vc_count")) ? 0 : context
                .getAttribute("vc_count");
            //当天只能获取5次 
            if (isCur((long) exttime) && ((Integer) count) >= EjavashopConfig.SMS_MAX_GIVEN_NUM) {
                return new HttpJsonResult<Boolean>("今日获取验证码次数过多,请明日再试");
            }

            ServiceResult<Object> result = senderService.sendVerifyCode(mobile);
            if (result.getSuccess()) {
                Gson gson = new Gson();
                VerifyCodeResult vcr = gson.fromJson((String) result.getResult(),
                    VerifyCodeResult.class);

                //将信息放入servletcontext
                context.setAttribute("vc_count", context.getAttribute("vc_count") == null ? 0
                    : ((Integer) context.getAttribute("vc_count")) + 1);

                memberNew.setSmsVerifyCode(vcr.getVerifyCode());
                ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
                if (!serviceResult.getSuccess()) {
                    jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            if (e instanceof BusinessException)
                return new HttpJsonResult<Boolean>(e.getMessage());
            else
                e.printStackTrace();
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
     * 解除手机绑定
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/unsmsverif.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> unSmsVerif(HttpServletRequest request,
                                                            HttpServletResponse response)
                                                                                         throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setIsSmsVerify(0);

        ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 手机绑定
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/smsverif.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> smsVerif(HttpServletRequest request,
                                                          HttpServletResponse response, String verif)
                                                                                                     throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = memberService.getMemberById(sessionMember.getId());
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        Member member = serviceResult.getResult();
        if (member == null) {
            return new HttpJsonResult<Boolean>("会员信息获取失败，请重试！");
        }

        if (StringUtil.isEmpty(verif)) {
            return new HttpJsonResult<Boolean>("验证码不能为空！");
        }

        if (!verif.equals(member.getSmsVerifyCode())) {
            return new HttpJsonResult<Boolean>("验证码输入错误，请重试！");
        }

        try {
            this.isCanVerifySms(member.getMobile());
        } catch (BusinessException e) {
            return new HttpJsonResult<Boolean>(e.getMessage());
        }

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setIsSmsVerify(1);

        ServiceResult<Boolean> updateServiceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!updateServiceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(updateServiceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 手机号是否已经被绑定
     * @param mobile
     */
    private void isCanVerifySms(String mobile) {

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_mobile", mobile);
        queryMap.put("q_isSmsVerify", Member.IS_SMS_VERIFY_1 + "");
        ServiceResult<List<Member>> membersResult = memberService.getMembers(queryMap, null);
        if (!membersResult.getSuccess()) {
            throw new BusinessException(membersResult.getMessage());
        }
        if (membersResult.getResult() != null && membersResult.getResult().size() > 0) {
            throw new BusinessException("对不起，该手机号码已经被绑定过了！");
        }
    }

    /**
     * 邮箱是否已经被绑定
     * @param mobile
     */
    private void isCanVerifyEmail(String email) {

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_email", email);
        queryMap.put("q_isEmailVerify", Member.IS_EMAIL_VERIFY_1 + "");
        ServiceResult<List<Member>> membersResult = memberService.getMembers(queryMap, null);
        if (!membersResult.getSuccess()) {
            throw new BusinessException(membersResult.getMessage());
        }
        if (membersResult.getResult() != null && membersResult.getResult().size() > 0) {
            throw new BusinessException("对不起，该邮箱已经被绑定过了！");
        }
    }

    /**
     * 邮箱绑定
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/sendemailverif.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> sendEmailVerif(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                String email) throws Exception {
        if (StringUtil.isEmpty(email)) {
            return new HttpJsonResult<Boolean>("邮箱地址不能为空！");
        }

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        String verif = RandomUtil.randomNumber(4);

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        // 邮箱地址加密
        memberNew.setEmail(email);
        memberNew.setEmailVerifyCode(verif);

        try {
            this.isCanVerifyEmail(email);
        } catch (BusinessException e) {
            return new HttpJsonResult<Boolean>(e.getMessage());
        }

        ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        // 邮件发送服务，需要客户提供邮箱地址及密码
        String encode = URLEncoder.encode(verif, "GBK");
        SendMail sendMail = new SendMail();
        sendMail.send163Email(email, "邮箱验证", DomainUrlUtil.EJS_URL_RESOURCES
                                             + "/member/emailverif.html?verif=" + encode,null);

        return jsonResult;
    }

    /**
     * 邮件绑定
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/emailverif.html", method = { RequestMethod.GET })
    public String emailVerif(HttpServletRequest request, HttpServletResponse response,
                             ModelMap map, String verif) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = memberService.getMemberById(sessionMember.getId());
        if (!serviceResult.getSuccess()) {
            map.put("sucess", "false");
            map.put("message", serviceResult.getMessage());
            return "h5v1/member/person/emailverif";
        }
        Member member = serviceResult.getResult();
        if (member == null) {
            map.put("sucess", "false");
            map.put("message", "用户信息获取失败，请重试！");
            return "h5v1/member/person/emailverif";
        }

        if (StringUtil.isEmpty(verif)) {
            map.put("sucess", "false");
            map.put("message", "验证码不能为空！");
            return "h5v1/member/person/emailverif";
        }

        if (!verif.equals(member.getEmailVerifyCode())) {
            map.put("sucess", "false");
            map.put("message", "验证码输入错误，请重试！");
            return "h5v1/member/person/emailverif";
        }

        try {
            this.isCanVerifyEmail(member.getEmail());
        } catch (BusinessException e) {
            map.put("sucess", "false");
            map.put("message", e.getMessage());
            return "h5v1/member/person/emailverif";
        }

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setIsEmailVerify(1);

        ServiceResult<Boolean> updateServiceResult = memberService.updateMember(memberNew);
        if (!updateServiceResult.getSuccess()) {
            map.put("sucess", "false");
            map.put("message", updateServiceResult.getMessage());
            return "h5v1/member/person/emailverif";
        }
        map.put("sucess", "true");
        return "h5v1/member/person/emailverif";
    }

    /**
     * 解除邮箱绑定
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/unemailverif.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> unEmailVerif(HttpServletRequest request,
                                                              HttpServletResponse response)
                                                                                           throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setIsEmailVerify(0);

        ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
