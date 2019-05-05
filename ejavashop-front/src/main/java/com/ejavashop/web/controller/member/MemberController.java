package com.ejavashop.web.controller.member;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.email.MailSender;
import com.ejavashop.core.email.SendMail;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.core.sms.MobileMessage;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.PersonalTailor;
import com.ejavashop.entity.member.PersonalTailorPicture;
import com.ejavashop.service.member.IMemberAddressService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.member.IPersonalTailorPictureService;
import com.ejavashop.service.member.IPersonalTailorService;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：资料管理
 *                       
 */
@Controller
@RequestMapping(value = "member")
public class MemberController extends BaseController {

    @Resource
    private IMemberAddressService memberAddressService;
    @Resource
    private IMemberService        memberService;
    @Resource
    private IPersonalTailorService personalTailorService;
    @Resource
    private IPersonalTailorPictureService personalTailorPictureService;
    @Resource
    private ISenderService senderService;
    /**
     * 跳转到 收货地址管理界面  取所有收货地址
     * @param request
     * @param response
     * @param map
     * @param id  收货地址id
     * @return
     */
    @RequestMapping(value = "/address.html", method = { RequestMethod.GET })
    public String toReciptAddress(HttpServletRequest request, HttpServletResponse response,
                                  ModelMap map) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddressByMId(member.getId());
        Object obj = null;
        if (serviceResult.getSuccess()) {
            obj = serviceResult.getResult();
        }

        map.put("addressList", obj);

        return "front/member/usercenter/datamanager/myreciptaddress";
    }

    /**
     * 收货地址添加或者编辑界面
     * @param request
     * @param response
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/editaddress.html", method = { RequestMethod.GET })
    public String toAddOrEditAddress(HttpServletRequest request, HttpServletResponse response,
                                     ModelMap map, Integer id) {
        ServiceResult<MemberAddress> serviceResult = new ServiceResult<MemberAddress>();
        if (id != null && !id.equals(0)) {
            serviceResult = memberAddressService.getMemberAddressById(id);
        }

        MemberAddress memberAddress = new MemberAddress();
        if (serviceResult.getSuccess()) {
            memberAddress = serviceResult.getResult();
        }
        map.put("address", memberAddress);
        return "front/member/usercenter/datamanager/addressedit";
    }
    /**
     * 新增一个  为个人资料完善  所在地区
     * @param request
     * @param response
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/editaddressbbb.html", method = { RequestMethod.GET })
    public String toAddOrEditAddressbbb(HttpServletRequest request, HttpServletResponse response,
                                     ModelMap map, Integer id) {
        ServiceResult<MemberAddress> serviceResult = new ServiceResult<MemberAddress>();
        if (id != null && !id.equals(0)) {
            serviceResult = memberAddressService.getMemberAddressById(id);
        }

        MemberAddress memberAddress = new MemberAddress();
        if (serviceResult.getSuccess()) {
            memberAddress = serviceResult.getResult();
        }
        map.put("address", memberAddress);
        return "front/member/usercenter/datamanager/newareaselectbbb";
    }
    /**
     * 新增一个  为个人资料完善  所在地区
     * @param request
     * @param response
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "/editaddressaaa.html", method = { RequestMethod.GET })
    public String toAddOrEditAddressaaa(HttpServletRequest request, HttpServletResponse response,
                                     ModelMap map, Integer id) {
        ServiceResult<MemberAddress> serviceResult = new ServiceResult<MemberAddress>();
        if (id != null && !id.equals(0)) {
            serviceResult = memberAddressService.getMemberAddressById(id);
        }

        MemberAddress memberAddress = new MemberAddress();
        if (serviceResult.getSuccess()) {
            memberAddress = serviceResult.getResult();
        }
        map.put("address", memberAddress);
        return "front/member/usercenter/datamanager/newareaselectaaa";
    }
    /**
     * 收货地址信息提交或编辑
     * @param request
     * @param response
     * @param stack
     * @param membervo
     * @throws Exception
     */
    @RequestMapping(value = "/saveaddress.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> reciptAddressSumbit(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     Map<String, Object> stack,
                                                                     MemberAddress memberAddress) throws Exception {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();

        //如果id值不为空，则更新，否则添加。前台如果为空，转成Integer时为变为0
        if (memberAddress != null && memberAddress.getId() != null && memberAddress.getId() != 0) {
            serviceResult = memberAddressService.updateMemberAddress(memberAddress);
        } else {
            memberAddress.setMemberId(member.getId());
            memberAddress.setState(MemberAddress.STATE_2);
            serviceResult = memberAddressService.saveMemberAddress(memberAddress);
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;

    }
    
    /**
     * 私人订制
     * @throws Exception 
     */
    @RequestMapping(value = "/savepersonaltailor.html", method = { RequestMethod.POST })
    public String savePersonalTailor(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);
        String description = request.getParameter("description");
        String picture = null;
        String contacts = request.getParameter("contacts");
        String mobile = request.getParameter("mobile");
        String qq = request.getParameter("qq");
        String weixin = request.getParameter("weixin");
        try {
            picture = UploadUtil.getInstance().uploadFile2ImageServer("up_picture",request);
        } catch (BusinessException e) {
            e.printStackTrace();
            log.error("图片上传异常>>" + e.getMessage());
        }
        PersonalTailor personalTailor = new PersonalTailor();
        personalTailor.setMobile(mobile);
        personalTailor.setName(contacts);
        personalTailor.setQq(qq);
        personalTailor.setWeixin(weixin);
        personalTailor.setDescription(description);
        personalTailor.setMemberId(member.getId());
        PersonalTailorPicture personalTailorPicture = new PersonalTailorPicture();
        personalTailorPicture.setTailorDescription(picture);
        //先保存信息再保存图片
        ServiceResult<Integer> pTailorResult = null;
        ServiceResult<Integer> serviceResult = personalTailorService.savePersonalTailor(personalTailor);
        if(serviceResult.getSuccess()){
            personalTailorPicture.setPersonalTailorId(personalTailor.getId());
            pTailorResult = personalTailorPictureService.savePersonalTailorPicture(personalTailorPicture);
        }
        if(!pTailorResult.getSuccess()){
            throw new RuntimeException(serviceResult.getMessage());
        }
//        信息保存成功后发送成功后将信息发至对应责任人
//        SendMail sendMail = new SendMail();
//        String mail_to = "wangyuzhu@dawawang.com";
//        String mail_to = "1632278229@qq.com";
//        String mail_title = "私人订制";
//        String mail_body = "您有新的采购需求";
//        sendMail.send163Email(mail_to, mail_title, mail_body);
        //短信提示
        Map<String, Object> smsparam = new HashMap<String, Object>();
        smsparam.put("mobile", "13735215529");
        
        //短信模板统一管理
        MobileMessage mMessage = new MobileMessage();
//        smsparam.put("content", "有新的订制需求，请注意到后台查收，并即时通知业务部门与客户联系，客户姓名："+ contacts +"，联系电话：" + mobile);
        smsparam.put("content", mMessage.getPersonalTailorMessage(contacts,mobile));
        senderService.sendSMSWidthCallable(smsparam);
        return "front/custom-made/fwc_succeeed";
    }

    /**
     * 收获地址设为默认
     * @param request
     * @param response
     * @param membervo
     * @throws Exception
     */
    @RequestMapping(value = "/setdefaultaddress.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> defaultAddress(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                @RequestParam(value = "id", required = true) Integer id) throws Exception {

        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = memberAddressService.defaultMemberAddress(id,
            member.getId());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 删除收货地址
     * @param request
     * @param response
     * @param id
     * @throws Exception
     */
    @RequestMapping(value = "/deleteaddress.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> deleteAddress(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               @RequestParam(value = "id", required = true) Integer id) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = memberAddressService.deleteMemberAddress(id,
            member.getId());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

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
        //        if (member != null && !StringUtil.isEmpty(member.getMobile(), true)) {
        //            member.setMobile(DES.decryptDES(member.getMobile()));
        //        }
        //        if (member != null && !StringUtil.isEmpty(member.getEmail(), true)) {
        //            member.setEmail(DES.decryptDES(member.getEmail()));
        //        }
          map.put("member", member);
        return "front/member/usercenter/datamanager/fwc_personalfile";
//	      return "front/member/usercenter/datamanager/personalfile";
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
                                                                   Member membervo) throws Exception {
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
        if (memberDb.getIsSmsVerify() == 0) {
            memberNew.setMobile(membervo.getMobile());
        }
        if (memberDb.getIsEmailVerify() == 0) {
            memberNew.setEmail(membervo.getEmail());
        }
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
     * 跳转到 修改密码界面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/editpassword.html", method = { RequestMethod.GET })
    public String toEditPassword(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap map) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = memberService.getMemberById(sessionMember.getId());

        Member member = null;
        if (serviceResult.getSuccess()) {
            member = serviceResult.getResult();
        }
        map.put("member", member);

        return "front/member/usercenter/datamanager/editpassword";
    }

    /**
     * 修改密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> editPasswordSumbit(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                                   @RequestParam(value = "newPwd", required = true) String newPwd) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = memberService.editPassword(oldPwd, newPwd,
            sessionMember);
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
        if (StringUtil.isEmpty(mobile)) {
            return new HttpJsonResult<Boolean>("请输入手机号码！");
        }
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        String verif = RandomUtil.randomNumber(4);

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setMobile(mobile);
        memberNew.setSmsVerifyCode(verif);

        try {
            this.isCanVerifySms(mobile);
        } catch (BusinessException e) {
            return new HttpJsonResult<Boolean>(e.getMessage());
        }

        ServiceResult<Boolean> serviceResult = memberService.updateMember(memberNew);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        // 发送短信的方法，需要运营商提供请求URL，不要删除这段代码
        //        SendSms send = new SendSms();
        //        Boolean sendSms = send.sendSms(mobile, "短信验证码：" + verif);
        //        if (!sendSms) {
        //            jsonResult = new HttpJsonResult<Boolean>("短信发送异常，请稍后重试！");
        //        }

        return jsonResult;
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
                                                            HttpServletResponse response) throws Exception {
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
                                                          HttpServletResponse response,
                                                          String verif) throws Exception {
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
        sendMail.send163Email(email, "邮箱验证",
            DomainUrlUtil.EJS_URL_RESOURCES + "/member/emailverif.html?verif=" + encode,null);

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
    public String emailVerif(HttpServletRequest request, HttpServletResponse response, ModelMap map,
                             String verif) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = memberService.getMemberById(sessionMember.getId());
        if (!serviceResult.getSuccess()) {
            map.put("sucess", "false");
            map.put("message", serviceResult.getMessage());
            return "front/member/usercenter/datamanager/emailverif";
        }
        Member member = serviceResult.getResult();
        if (member == null) {
            map.put("sucess", "false");
            map.put("message", "用户信息获取失败，请重试！");
            return "front/member/usercenter/datamanager/emailverif";
        }

        if (StringUtil.isEmpty(verif)) {
            map.put("sucess", "false");
            map.put("message", "验证码不能为空！");
            return "front/member/usercenter/datamanager/emailverif";
        }

        if (!verif.equals(member.getEmailVerifyCode())) {
            map.put("sucess", "false");
            map.put("message", "验证码输入错误，请重试！");
            return "front/member/usercenter/datamanager/emailverif";
        }

        try {
            this.isCanVerifyEmail(member.getEmail());
        } catch (BusinessException e) {
            map.put("sucess", "false");
            map.put("message", e.getMessage());
            return "front/member/usercenter/datamanager/emailverif";
        }

        Member memberNew = new Member();
        memberNew.setId(sessionMember.getId());
        memberNew.setIsEmailVerify(1);

        ServiceResult<Boolean> updateServiceResult = memberService.updateMember(memberNew);
        if (!updateServiceResult.getSuccess()) {
            map.put("sucess", "false");
            map.put("message", updateServiceResult.getMessage());
            return "front/member/usercenter/datamanager/emailverif";
        }
        map.put("sucess", "true");
        return "front/member/usercenter/datamanager/emailverif";
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
                                                              HttpServletResponse response) throws Exception {
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
