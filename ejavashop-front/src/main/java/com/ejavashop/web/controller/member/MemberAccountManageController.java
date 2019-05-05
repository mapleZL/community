package com.ejavashop.web.controller.member;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.entity.parter.CategorySalesVO;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.entity.parter.ParterTuijian;
import com.ejavashop.entity.parter.SalesDetailsVO;
import com.ejavashop.entity.parter.SalesPersonVO;
import com.ejavashop.entity.parter.SumParterSaleVO;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.service.member.IMemberBalanceLogsService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 会员账户管理 ：提现、收支明细                      
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberAccountManageController extends BaseController {

    @Resource
    private IMemberBalanceLogsService memberBalanceLogsService;

    @Resource
    private IMemberService            memberService;
    
    @Resource
    private IOrdersService ordersService;
    
    @Resource
    private IParterSignService parterSignService;
    
    @Resource
    private IRegionsService regionsService;

    private static String             baseUrl = "front/member/usercenter/deposit";

    /**
     * 跳转到余额列表页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/balance.html", method = { RequestMethod.GET })
    public String balancelist(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("memberId", sessionMember.getId().toString());
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService.page(
            queryMap, pager);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("info", serviceResult.getMessage());
                return "front/commons/error";
            }
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), request.getRequestURI() + "");

        dataMap.put("infoList", serviceResult.getResult());
        dataMap.put("page", pm);
        dataMap.put("member", memberResult.getResult());

        return baseUrl + "/balancelist";
    }

    /**
     * 跳转到设置支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/setbalancepassword.html", method = { RequestMethod.GET })
    public String balancepwdadd(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return baseUrl + "/balancepwdadd";
    }

    /**
     * 支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/savebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> addBalancePwdSumbit(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    @RequestParam(value = "password", required = true) String password)
                                                                                                                                       throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.addBalancePassword(password, sessionMember);
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
     * 跳转到 修改支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/resetbalancepassword.html", method = { RequestMethod.GET })
    public String resetbalancepassword(HttpServletRequest request, HttpServletResponse response,
                                       Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return baseUrl + "/resetbalancepassword";
    }

    /**
     * 跳转到 修改支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/editbalancepassword.html", method = { RequestMethod.GET })
    public String toEditBalancePwd(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return baseUrl + "/balancepwdedit";
    }

    /**
     * 修改支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> editBalancePasswordSumbit(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                                          @RequestParam(value = "newPwd", required = true) String newPwd)
                                                                                                                                         throws Exception {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.editBalancePassword(oldPwd, newPwd, sessionMember.getId());
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
     * 重置支付密码
     * @param request
     * @param response
     * @param newPwd
     * @param confirmPwd
     * @param mobVerify
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resetbalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> resetbalancepassword(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestParam(value = "newPwd", required = true) String newPwd,
                                                                     @RequestParam(value = "confirmPwd", required = true) String confirmPwd,
                                                                     @RequestParam(value = "mobVerify", required = true) String mobVerify)
                                                                                                                                          throws Exception {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.getMemberById(sessionMember.getId());
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        HttpSession session = request.getSession();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        Member member = serviceResult.getResult();
        if (isNull(mobVerify)) {
            return new HttpJsonResult<Member>("请输入手机验证码");
        }

        if (isNull(newPwd)) {
            return new HttpJsonResult<Member>("请输入密码");
        }

        if (isNull(confirmPwd)) {
            return new HttpJsonResult<Member>("请输入确认密码");
        }

        if (!mobVerify.equals(session.getAttribute("smsCode"))) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }

        if (!newPwd.equals(confirmPwd)) {
            return new HttpJsonResult<Member>("两次密码输入不一致");  
        }

        member.setBalancePwd(Md5.getMd5String(newPwd));
        //重置密码输入错误次数
        member.setPwdErrCount(0);
        ServiceResult<Boolean> servlet = memberService.updateMember(member);
        if(!servlet.getSuccess()){
            return new HttpJsonResult<Member>("更新密码失败");  
        }
        return jsonResult;
    }

    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotal.html", method = { RequestMethod.GET , RequestMethod.POST})
    public String panerTotal(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getSumParterSaleVO(sessionMember.getId(),null,signNo);
        
        dataMap.put("sumParterSale", memberResult.getResult());
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("signNo", signNo);
        return "front/member/usercenter/panter/panterTotal";
    }
    
    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalMonth.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String panerTotalMonth(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        String year = request.getParameter("year");
        int years;
        if(StringUtil.isEmpty(year)){
        	SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Date date = new Date();
            years = Integer.parseInt(sdf.format(date));
        }else{
        	years = Integer.parseInt(year);
        }
        //查询用户信息
        ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getSumParterSaleVO(sessionMember.getId(),years,signNo);
        dataMap.put("sumParterSale", memberResult.getResult());
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("year", year);
        dataMap.put("signNo", signNo);
        return "front/member/usercenter/panter/panterTotalMonth";
    }
    
    
    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalYears.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String panerTotalYears(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getNewSumParterSalesYears(sessionMember.getId(),2,null,signNo);
        dataMap.put("sumParterSale", memberResult.getResult());
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("signNo", signNo);
        return "front/member/usercenter/panter/panterTotalYears";
    }
    
    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalDays.html", method = { RequestMethod.GET , RequestMethod.POST})
    public String panerTotalDays(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        pager.setPageSize(ConstantsEJS.DEFAULT_LIST_PAGE_SIZE);
        
        String url = request.getRequestURI();
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getNewSumParterSalesYears(sessionMember.getId(),1,pager,signNo);
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
                .getPageIndex()), pager.getRowsCount(), url);
        dataMap.put("sumParterSale", memberResult.getResult());
        dataMap.put("type", 1);
        dataMap.put("page", pm);
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("signNo", signNo);
        return "front/member/usercenter/panter/panterTotalYears";
    }
    
    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalPerson.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String panerTotalPerson(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	PaginationUtil page = WebUtil.handlerPaginationUtil(request);
    	int start = (page.getNum() - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE_10;
    	int size = ConstantsEJS.DEFAULT_PAGE_SIZE_10;
        
        String url = request.getRequestURI();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("startTime") ;
        String endTime =  request.getParameter("endTime");
        
        String areaId = request.getParameter("areaId") == null ? "" : request.getParameter("areaId");
        
        if(areaId != null && areaId.equals("--请选择--")){
        	areaId = "";
        }
        
        if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }
        
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<SalesPersonVO>> memberResult = new ServiceResult<List<SalesPersonVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
        	signNo = parterSignList.get(0).getSignNo();
        }
        
       List<Regions> regionsAreaList = regionsService.getParterSignArea(sessionMember.getId(),signNo).getResult();
       
        memberResult =  ordersService.panerTotalPersonFront(sessionMember.getId(),startTime,endTime,start,size,signNo,areaId);
        /*PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
                .getPageIndex()), pager.getRowsCount(), url);*/
         int pageCount = ordersService.panerTotalPersonFrontCount(sessionMember.getId(),startTime,endTime,signNo,areaId).getResult();
        List<SalesPersonVO> list = memberResult.getResult();
        Integer sort = 1 ;
        for (SalesPersonVO salesPersonVO : list) {
        	salesPersonVO.setSort(sort);
        	sort++;
		}
        page.createPagination(pageCount);
        dataMap.put("salesPersonVO", list);
        dataMap.put("page", page);
        dataMap.put("startTime", startTime);
        if ("".equals(areaId)) areaId = "0";
        dataMap.put("areaId", areaId);
        dataMap.put("signNo", signNo);
        dataMap.put("endTime", endTime);
        dataMap.put("url4page", "member/panerTotalPerson.html?startTime="+startTime+"&endTime="+endTime);
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("regionsAreaList", regionsAreaList);
        return "front/member/usercenter/panter/panerTotalPerson";
    }
    //裸袜     product_brand_id =8  categorySales.html'
    //品牌袜 product_brand_id !=8
    
    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/categorySales.html", method = { RequestMethod.GET , RequestMethod.POST})
    public String categorySales(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("startTime") ;
        String endTime =  request.getParameter("endTime");
        
        if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }
        
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<CategorySalesVO>> memberResult = new ServiceResult<List<CategorySalesVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getCategorySales(sessionMember.getId(),startTime,endTime,signNo);
        List<CategorySalesVO> list = memberResult.getResult();
        dataMap.put("salesPersonVO", list);
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        dataMap.put("signNo", signNo);
        dataMap.put("parterSignList", parterSignList);
        return "front/member/usercenter/panter/categorySales";
    }
    
    @RequestMapping(value = "/getSalesDetails.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String getSalesDetails(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	PaginationUtil page = WebUtil.handlerPaginationUtil(request);
    	int start = (page.getNum() - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE_10;
    	int size = ConstantsEJS.DEFAULT_PAGE_SIZE_10;
        
        String url = request.getRequestURI();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("startTime") ;
        String endTime =  request.getParameter("endTime");
        
        if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }
        
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<SalesDetailsVO>> memberResult = new ServiceResult<List<SalesDetailsVO>>();
        String signNo = request.getParameter("signNo");
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        memberResult =  ordersService.getSalesDetailsFront(sessionMember.getId(),startTime,endTime,start,size,signNo,null);
        Integer pageCount = ordersService.getSalesDetailsFrontCount(sessionMember.getId(),startTime,endTime,signNo,null).getResult();
       /* PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
                .getPageIndex()), pager.getRowsCount(), url);*/
        List<SalesDetailsVO> list = memberResult.getResult();
        page.createPagination(pageCount);
        dataMap.put("salesDetailsVO", list);
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        dataMap.put("page", page);
        dataMap.put("signNo", signNo);
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("url4page", "member/getSalesDetails.html?startTime="+startTime+"&endTime="+endTime);
        return "front/member/usercenter/panter/salesDetails";
    }
    
    
    @RequestMapping(value = "/getSalesDetailsByOrdersId.html", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<SalesDetailsVO>> getSalesDetailsByOrdersId(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String ordersId =request.getParameter("ordersId");
    	ServiceResult<List<SalesDetailsVO>> serviceResult = new ServiceResult<List<SalesDetailsVO>>();
    	serviceResult = ordersService.getSalesDetailsByOrdersId(Integer.parseInt(ordersId));
        HttpJsonResult<List<SalesDetailsVO>> jsonResult = new HttpJsonResult<List<SalesDetailsVO>>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<List<SalesDetailsVO>>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
    
    /**
     * 合伙人推荐合伙人
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/parterTuijian.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String parterTuijian(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        
        String url = request.getRequestURI();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("startTime") ;
        String endTime =  request.getParameter("endTime");
        String memberTuijianId =  request.getParameter("memberTuijian") == null ? "" : request.getParameter("memberTuijian");
        String memberAreaId =  request.getParameter("memberArea") == null ? "" : request.getParameter("memberArea");
        if(memberTuijianId != null && memberTuijianId.equals("--请选择--")){
        	memberTuijianId = "";
        }
        if(memberAreaId != null &&memberAreaId.equals("--请选择--")){
        	memberAreaId = "";
        }
        
        
        /*if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }*/
        
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<List<Member>> tuijianMemberList = memberService.getTuijianMemberByMemberId(sessionMember.getId());
        
        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
        parterTuijianResult =  ordersService.getParterTuijian(""+sessionMember.getId(),startTime,endTime,memberTuijianId,memberAreaId,null);
        List<ParterTuijian> list = parterTuijianResult.getResult();
        dataMap.put("parterTuijian", list);
        dataMap.put("memberList", tuijianMemberList.getResult());
        dataMap.put("startTime", startTime);
        dataMap.put("endTime", endTime);
        if ("".equals(memberTuijianId)) memberTuijianId = "0";
        if ("".equals(memberAreaId)) memberAreaId = "0";
        dataMap.put("memberTuijianId", Integer.parseInt(memberTuijianId));
        dataMap.put("memberAreaId", Integer.parseInt(memberAreaId));
        return "front/member/usercenter/panter/parterTuijian";
    }
    
    @RequestMapping(value = "/getParterSignArea.html", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<Regions>> getParterSignArea(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String memberId =request.getParameter("memberId");
    	ServiceResult<List<Regions>> serviceResult = new ServiceResult<List<Regions>>();
    	serviceResult = regionsService.getParterSignArea(Integer.parseInt(memberId),null);
        HttpJsonResult<List<Regions>> jsonResult = new HttpJsonResult<List<Regions>>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<List<Regions>>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
    
    /**
     * 合伙人提点统计
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/parterSalesSum.html", method = { RequestMethod.GET, RequestMethod.POST })
    public String parterSalesSumPercent(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	Member sessionMember = WebFrontSession.getLoginedUser(request);
    	String signNo = request.getParameter("signNo");
    	String startTime = request.getParameter("startTime") ;
        String endTime =  request.getParameter("endTime");
        
        String areaId = request.getParameter("areaId") == null ? "" : request.getParameter("areaId");
        
        if(areaId != null && areaId.equals("--请选择--")){
        	areaId = "";
        }
    	List<Regions> regionsAreaList = regionsService.getParterSignArea(sessionMember.getId(),signNo).getResult();
        ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        if("".equals(signNo) || StringUtil.isEmpty(signNo)){
            signNo = parterSignList.get(0).getSignNo();
        }
        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
        parterTuijianResult =  ordersService.getParterTuijian(""+sessionMember.getId(),startTime,endTime,null,areaId,signNo);
        List<ParterTuijian> list = parterTuijianResult.getResult();
        dataMap.put("parterTuijian", list);
        dataMap.put("startTime", startTime);
        if ("".equals(areaId)) areaId = "0";
        dataMap.put("areaId", areaId);
        dataMap.put("signNo", signNo);
        dataMap.put("endTime", endTime);
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("parterSignList", parterSignList);
        dataMap.put("regionsAreaList", regionsAreaList);
    	return "front/member/usercenter/panter/parterSalesSumPercent";
    }
    
    
}
