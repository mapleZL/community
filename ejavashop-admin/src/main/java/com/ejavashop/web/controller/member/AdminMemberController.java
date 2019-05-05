package com.ejavashop.web.controller.member;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.member.MemberGradeIntegralLogs;
import com.ejavashop.entity.member.MemberGradeUpLogs;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberAddressService;
import com.ejavashop.service.member.IMemberBalanceLogsService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 会员管理controller
 *
 * @Filename: AdminMemberController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/member/member")
public class AdminMemberController extends BaseController {

    @Resource(name = "memberService")
    private IMemberService            memberService;

    @Resource(name = "memberBalanceLogsService")
    private IMemberBalanceLogsService memberBalanceLogsService;

    @Resource(name = "memberAddressService")
    private IMemberAddressService     memberAddressService;
    
    @Resource
    private IRegionsService     regionsService;
    
    @Resource
    private IParterSignService parterSignService;

    /**
     * 会员管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberlist";
    }

    /**
     * 会员管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Member>> list(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Member>> serviceResult = memberService.getMembers(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        try {
        	 List<Member> memberList = serviceResult.getResult();
             for (Member member : memberList) {
     			if(!"".equals(member.getInviterId())  || !StringUtil.isEmpty(member.getInviterId())){
     				 ServiceResult<Member> memberResult = memberService.getMemberById(Integer.parseInt(member.getInviterId()));
     				 if(memberResult.getSuccess()){
     					Member member1 = memberResult.getResult();
     					member.setInviterId(member1.getRealName());
     				 }
     			}
     		}
		} catch (Exception e) {
		}
        HttpJsonResult<List<Member>> jsonResult = new HttpJsonResult<List<Member>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值积分制操作controller接口
     * @param memberGradeIntegralLogs
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "valueopt", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> valueOpt(MemberGradeIntegralLogs memberGradeIntegralLogs,
                                                          HttpServletRequest request) {

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //  参数校验
        if (memberGradeIntegralLogs.getMemberId() == null) {
            jsonResult.setMessage("会员ID不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getValue() == null) {
            jsonResult.setMessage("经验值或积分值不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getOptType() == null) {
            jsonResult.setMessage("动作类型不能为空，请重试！");
            return jsonResult;
        } else if (memberGradeIntegralLogs.getType() == null) {
            jsonResult.setMessage("操作类型不能为空，请重试！");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = memberService
            .updateMemberValue(memberGradeIntegralLogs);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 会员管理页面经验值积分制操作controller接口
     * @param memberGradeIntegralLogs
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "balanceopt", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> balanceOpt(MemberBalanceLogs memberBalanceLogs,
                                                            HttpServletRequest request) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        //  参数校验
        if (memberBalanceLogs.getMemberId() == null) {
            jsonResult.setMessage("会员ID不能为空，请重试！");
            return jsonResult;
        } else if (memberBalanceLogs.getMoney() == null) {
            jsonResult.setMessage("变更金额不能为空，请重试！");
            return jsonResult;
        } else if (memberBalanceLogs.getState() == null) {
            jsonResult.setMessage("动作类型不能为空，请重试！");
            return jsonResult;
        }
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        memberBalanceLogs.setOptId(adminUser.getId());
        memberBalanceLogs.setOptName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = memberService.updateMemberBalance(memberBalanceLogs,
            WebAdminSession.getAdminUser(request));
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 会员管理页面升级日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "uploglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberGradeUpLogs>> upLogList(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberGradeUpLogs>> serviceResult = memberService
            .getMemberGradeUpLogs(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberGradeUpLogs>> jsonResult = new HttpJsonResult<List<MemberGradeUpLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值日志和积分值日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "grdIntloglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberGradeIntegralLogs>> grdIntLogList(HttpServletRequest request,
                                                                                     HttpServletResponse response,
                                                                                     Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        // 类型：1、经验值；2、积分
        String typeStr = request.getParameter("type");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberGradeIntegralLogs>> serviceResult = memberService
            .getMemberGradeIntegralLogs(ConvertUtil.toInt(memberIdStr, 0),
                ConvertUtil.toInt(typeStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberGradeIntegralLogs>> jsonResult = new HttpJsonResult<List<MemberGradeIntegralLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面经验值日志和积分值日志按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "balanceloglist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberBalanceLogs>> balanceLogList(HttpServletRequest request,
                                                                                HttpServletResponse response,
                                                                                Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService
            .getMemberBalanceLogs(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberBalanceLogs>> jsonResult = new HttpJsonResult<List<MemberBalanceLogs>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 会员管理页面收货地址按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "addresslist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberAddress>> addressList(HttpServletRequest request,
                                                                         HttpServletResponse response,
                                                                         Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddresses(ConvertUtil.toInt(memberIdStr, 0), pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberAddress>> jsonResult = new HttpJsonResult<List<MemberAddress>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

	
    /**
     * oms账号同步或者开通代发业务
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "transferBussiness", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> transferBussiness(HttpServletRequest request,
                                                                         HttpServletResponse response,
                                                                         Map<String, Object> dataMap) {
        String memberIdStr = request.getParameter("memberId");
        String type = request.getParameter("type");
        ServiceResult<Boolean> serviceResult = memberService
            .transferBussiness(memberIdStr,type);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(true);
        if (!serviceResult.getSuccess()) {
        	jsonResult.setData(false);
        	jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "parter", method = { RequestMethod.POST ,RequestMethod.GET })
    public String createParter(HttpServletRequest request,String memberId,
            HttpServletResponse response, Map<String, Object> dataMap) {
    	ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(memberId));
    	if(memberServiceResult.getSuccess()){
    		Member member = memberServiceResult.getResult();
            dataMap.put("member", member);
            dataMap.put("message", "");
    	}
    	return "admin/member/member/parterCreate";
    }
    
    @RequestMapping(value = "parter/create", method = { RequestMethod.POST ,RequestMethod.GET })
    public String saveParter(HttpServletRequest request,
            HttpServletResponse response, Map<String, Object> dataMap) {
    	try {
    		String signNo = request.getParameter("signNo");
    		String memberId = request.getParameter("memberId");
    		ParterSign parterSign1 = parterSignService.getByMemeberSignNo(signNo).getResult();
    		Member member = memberService.getMemberById(Integer.parseInt(memberId)).getResult();
    		if(parterSign1 == null){
            	String startTime = request.getParameter("startTime");
            	String endTime = request.getParameter("endTime");
            	SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            	ParterSign parterSign = new ParterSign();
            	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            	parterSign.setMemberId(memberId);
            	parterSign.setStatus("0");
            	parterSign.setCreateMan(adminUser.getName());
    			parterSign.setStartTime(dateFormat.parse(startTime+ " 00:00:00"));
    			parterSign.setEndTime(dateFormat.parse(endTime+ " 23:59:59"));
    			parterSign.setMemberName(member.getName());
    			parterSign.setParterType("parter");
    			parterSign.setSignNo(signNo);
    			parterSignService.saveParterSign(parterSign);
    		}else{
    			dataMap.put("message", "该会员已是合伙人");
    			dataMap.put("member", member);
    			return "admin/member/member/parterCreate";
    		}
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	return "admin/member/parter/parterlist";
    }
    @RequestMapping(value = "makeRecord", method = { RequestMethod.POST ,RequestMethod.GET })
    public String makeRecord(HttpServletRequest request,String memberId,
            HttpServletResponse response, Map<String, Object> dataMap) {
    	Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
		}
    	dataMap.put("parterSignList", parterSignList);
    	dataMap.put("memberId", memberId);
    	return "admin/member/parter/makeRecord";
    }
    
    @RequestMapping(value = "saveMakeRecord", method = { RequestMethod.POST ,RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> saveMakeRecord(HttpServletRequest request,String memberId,
            HttpServletResponse response, Map<String, Object> dataMap) {
    	HttpJsonResult<Boolean> result = new HttpJsonResult<Boolean>();
    	String parterMemberId = request.getParameter("parterMemberId");
    	ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(memberId));
    	if(memberServiceResult.getSuccess()){
    		Member member = memberServiceResult.getResult();
    		member.setInviterId(parterMemberId);
    		memberService.updateMember(member);
    		result.setData(true);
    	}else{
    		result.setData(false);
    		result.setMessage(memberServiceResult.getMessage());
    	}
    	return result;
    }
}
