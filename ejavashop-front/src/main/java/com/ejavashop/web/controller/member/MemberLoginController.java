package com.ejavashop.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.sms.MobileMessage;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 会员登录controller
 * 
 * @Filename: MemberLoginController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MemberLoginController extends BaseController {

    @Resource
    private IMemberService memberService;

    @Resource
    private IOrdersService ordersService;

    @Resource
    private ISenderService senderService;
    
    @Resource
    private IParterSignService parterSignService ;

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/login.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> stack) {
    	return "front/member/fwc_login";
//        return "front/member/tzm_login";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "dologin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> loginSumbit(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Member member, String verifyCode) {
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        try {
            String ip = WebUtil.getIpAddr(request);

            String verify_number = WebFrontSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
                return new HttpJsonResult<Member>("验证码不正确！");
            }

            // 登录验证
            ServiceResult<Member> serviceResult = memberService.memberLogin(member.getName(),
                member.getPassword(), ip, ConstantsEJS.SOURCE_1_PC);
            Integer status = 1;
            if (serviceResult.getResult() != null && !"".equals(serviceResult.getResult().getId())) {
                Integer memberId = serviceResult.getResult().getId();
                ServiceResult<Integer> ordersResult = ordersService.getOrdersByMemberId(memberId);
                status = ordersResult.getResult();
            }
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                    return jsonResult;
                }
            }

            Member member2 = serviceResult.getResult();
            
            ServiceResult<List<ParterSign>> parterSignServiceResult = parterSignService.getByMemeberId(""+member2.getId());
            if(parterSignServiceResult.getSuccess()){
            	List<ParterSign>  parterSign = parterSignServiceResult.getResult();
            	if(parterSign != null && parterSign.size() > 0){
            		WebFrontSession.putObjToSession(request, "is_parter_sign", "true");
            	}else{
            		WebFrontSession.putObjToSession(request, "is_parter_sign", "false");
            	}
            }
            //add by tongzhaomei
            jsonResult.setData(member2);
            //end
            member.setStatus(status);
            //借用字段存储登录用户的购买定订单的数量
            jsonResult.setTotal(status);
            // 登录送经验值积分
            memberService.memberLoginSendValue(member2.getId(), member2.getName());
            // 存入session
            WebFrontSession.putMemberSession(request, member2);
        } catch (Exception e) {
            jsonResult = new HttpJsonResult<Member>("登录失败，请重试");
        }

        return jsonResult;
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "dodialoglogin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> doDialogLogin(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              String verifyCode) {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        try {

            String verify_number = WebFrontSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
                return new HttpJsonResult<Member>("验证码不正确！");
            }

            String ip = WebUtil.getIpAddr(request);
            // 登录验证
            ServiceResult<Member> serviceResult = memberService.memberLogin(name, password, ip,
                ConstantsEJS.SOURCE_1_PC);

            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                    return jsonResult;
                }
            }

            Member member2 = serviceResult.getResult();
            // 登录送经验值积分
            memberService.memberLoginSendValue(member2.getId(), member2.getName());
            // 存入session
            WebFrontSession.putMemberSession(request, member2);
        } catch (Exception e) {
            jsonResult = new HttpJsonResult<Member>("登录失败，请重试");
        }

        return jsonResult;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout.html", method = { RequestMethod.GET })
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("memberSession");
        }
        //        return "front/member/tzm_login";
        return "forward:/";
    }

    /**
     * 跳转到用户中心页面
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/member/index.html", method = { RequestMethod.GET })
    public String toUserIndex(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        Integer memberId = sessionMember.getId();
        // 获取member对象
        ServiceResult<Member> result = memberService.getMemberById(memberId);
        dataMap.put("member", result.getResult());

        //待支付订单数
        ServiceResult<Integer> numResult = ordersService.getOrderNumByMIdAndState(memberId,
            Orders.ORDER_STATE_1);
        dataMap.put("toBepaidOrders", numResult.getResult());
        //待收货订单数
        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_4);
        dataMap.put("toBeReceivedOrders", numResult.getResult());
        //待评价订单数
        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_5);
        dataMap.put("toBeCommentedOrders", numResult.getResult());

        return "front/member/userindex";
    }

    /**
     * 跳转到忘记密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/forgetpassword.html", method = { RequestMethod.GET })
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> stack) {
//        return "front/member/forgetpassword";
        return "front/member/fwc_forgetpassword";
    }

    /**
     * 找回密码
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = "/doforgetpassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<String> doForgetpassword(HttpServletRequest request,
                                                                 HttpServletResponse response)
                                                                                              throws Exception {

        HttpJsonResult<String> result = new HttpJsonResult<String>();
        String name = request.getParameter("name");
        String mobVerfiy = request.getParameter("mobVerfiy");
        HttpSession session = request.getSession();

        if (StringUtil.isEmpty(name)) {
            return new HttpJsonResult("手机号不能为空！");
        }
        if (StringUtil.isEmpty(mobVerfiy)) {
            return new HttpJsonResult("手机验证码不能为空！");
        }
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtil.isEmpty(verifyCode)) {
            return new HttpJsonResult("验证码不能为空！");
        }
        if (!mobVerfiy.equals(session.getAttribute("smsCode"))) {
            return new HttpJsonResult("手机验证码错误");
        }
        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult("验证码输入错误");
        }

        // 根据name取会员信息
        ServiceResult<List<Member>> memberResult = memberService.getMemberByName(name);
        if (!memberResult.getSuccess()) {
            return new HttpJsonResult(memberResult.getMessage());
        }
        if (memberResult.getResult() == null || memberResult.getResult().size() == 0) {
            return new HttpJsonResult("您输入的手机号不存在");
        }
        Member member = memberResult.getResult().get(0);

        //新密码
        String newPwd = RandomUtil.randomNumber(6);

        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(newPwd));

        ServiceResult updateMember = memberService.updateMember(memberNew);
        if (!updateMember.getSuccess()) {
            return new HttpJsonResult(updateMember.getMessage());
        }

        //发送短信
        Map<String, Object> smsparam = new HashMap<String, Object>();
        smsparam.put("mobile", member.getName());
      //短信模板统一管理
        MobileMessage mMessage = new MobileMessage();
        smsparam.put("content", mMessage.getForgetMessage(newPwd));
        senderService.sendSMSWidthCallable(smsparam);

        result.setData("密码重置成功，请注意查收短信。");
        return result;
    }

    /**
     * 判断登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "isuserlogin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> isUserLogin(HttpServletRequest request,
                                                             HttpServletResponse response) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(false);
        } else {
            jsonResult.setData(true);
        }
        return jsonResult;
    }

    /**
     * 获取登录用户（如果未登录返回null对象）
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "getloginuser.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> getLoginUser(HttpServletRequest request,
                                                             HttpServletResponse response) {
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(null);
        } else {
            jsonResult.setData(loginedUser);
        }
        return jsonResult;
    }
}
