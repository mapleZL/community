package com.ejavashop.web.controller.member;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.parter.CategorySalesVO;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.entity.parter.ParterTuijian;
import com.ejavashop.entity.parter.SumParterSaleVO;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

@Controller
@RequestMapping(value = "member")
public class MemberAccountManageController extends BaseController {
    
    @Resource
    private IMemberService            memberService;
    
    @Resource
    private IOrdersService ordersService;
    
    @Resource
    private IParterSignService parterSignService;
    
    @Resource
    private IRegionsService regionsService;

    /**
     * panterIndex.html
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panterIndex.html", method = { RequestMethod.GET })
    public String indexRedirect(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        if(sessionMember == null ){
            return "h5v1/member/login";
        }
        return "h5v1/panter/panterIndex";
    }

    /**
     * 累计销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotal.html", method = { RequestMethod.GET })
    public String panerTotal(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	Member sessionMember = WebFrontSession.getLoginedUser(request);
    	if(sessionMember == null ){
    		return "h5v1/member/login";
        }
    	String type  = request.getParameter("type");
    	//合伙人推荐合伙人
    	if("5".equals(type)){
    		List<Member> tuijianMemberList = memberService.getTuijianMemberByMemberId(sessionMember.getId()).getResult();
    		List<ParterSign> parterSignList = new ArrayList<ParterSign>();
    		List<Regions> regionsList = new ArrayList<Regions>();
    		for (Member member : tuijianMemberList) {
    			ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(member.getId()+"");
    			parterSignList.addAll(parterSignListServiceResult.getResult());
    			ServiceResult<List<Regions>> serviceResult = regionsService.getParterSignArea(member.getId(),null);
    			regionsList.addAll(serviceResult.getResult());
			}
    		dataMap.put("parterSignList", parterSignList);
    		dataMap.put("regionsList", regionsList);
    	}else{
    		ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
    		List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
    		dataMap.put("parterSignList", parterSignList);
    		ServiceResult<List<Regions>> serviceResult = regionsService.getParterSignArea(sessionMember.getId(),null);
    		dataMap.put("regionsList", serviceResult.getResult());
    	}
    	dataMap.put("type", type);
    	dataMap.put("first", "first");
        return "h5v1/panter/panterTotal";
    }

    @RequestMapping(value = "/panerTotalMore.html", method = { RequestMethod.GET,  RequestMethod.POST})
    public  String panerTotalMore(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
    	String type = request.getParameter("type");
    	dataMap.put("first", "second");
    	dataMap.put("type", type);
    	String signNo = request.getParameter("signNo");
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	String startTime = request.getParameter("startDate");
    	dataMap.put("startTime", startTime);
    	String endTime = request.getParameter("endDate");
    	dataMap.put("endDate", endTime);
    	if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }
        
        String year = request.getParameter("year");
        if(StringUtil.isEmpty(year)){
        	SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
        	year = sdfYear.format(new Date());
        }
        dataMap.put("year", year);
        
    	//合伙人推荐合伙人
    	if("5".equals(type)){
    		List<Member> tuijianMemberList = memberService.getTuijianMemberByMemberId(sessionMember.getId()).getResult();
    		List<ParterSign> parterSignList = new ArrayList<ParterSign>();
    		List<Regions> regionsList = new ArrayList<Regions>();
    		for (Member member : tuijianMemberList) {
    			ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(member.getId()+"");
    			parterSignList.addAll(parterSignListServiceResult.getResult());
    			ServiceResult<List<Regions>> serviceResult = regionsService.getParterSignArea(member.getId(),null);
    			regionsList.addAll(serviceResult.getResult());
			}
    	    if("".equals(signNo) || StringUtil.isEmpty(signNo)){
    	    	if (!parterSignList.isEmpty())				//Terry 20170308
    	    		signNo = parterSignList.get(0).getMemberId();
    	    }
    		dataMap.put("parterSignList", parterSignList);
    		dataMap.put("regionsList", regionsList);
    	}else{
    		ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(sessionMember.getId()+"");
    		List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
    		dataMap.put("parterSignList", parterSignList);
    		ServiceResult<List<Regions>> serviceResult = regionsService.getParterSignArea(sessionMember.getId(),null);
    		dataMap.put("regionsList", serviceResult.getResult());
    	    if("".equals(signNo) || StringUtil.isEmpty(signNo)){
    	            signNo = parterSignList.get(0).getSignNo();
    	    }
    	}
    	
    	//累计销售汇总
    	if("1".equals(type)){
    		//查询用户信息
            ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
            memberResult =  ordersService.getSumParterSaleVO(sessionMember.getId(),Integer.parseInt(year),signNo);
            dataMap.put("sumParterSale", memberResult.getResult());
    	}
    	//年度汇总表
    	else if("2".equals(type)){
            //查询用户信息
            ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
            memberResult =  ordersService.getNewSumParterSalesYears(sessionMember.getId(),2,null,signNo);
            dataMap.put("sumParterSale", memberResult.getResult());
    	}
    	//分类销售汇总表
    	else if("3".equals(type)){
		        //查询用户信息
		        ServiceResult<List<CategorySalesVO>> memberResult = new ServiceResult<List<CategorySalesVO>>();
		        memberResult =  ordersService.getCategorySales(sessionMember.getId(),startTime,endTime,signNo);
		        List<CategorySalesVO> list = memberResult.getResult();
		        dataMap.put("salesPersonVO", list);
		}
		//合伙人提点统计表
		else if("4".equals(type)){
	        String areaId = request.getParameter("areaId") == null ? "" : request.getParameter("areaId");
	        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
	        parterTuijianResult =  ordersService.getParterTuijian(""+sessionMember.getId(),startTime,endTime,null,areaId,signNo);
	        List<ParterTuijian> list = parterTuijianResult.getResult();
	        dataMap.put("parterTuijian", list);
	        dataMap.put("areaId", areaId);
		}
		//合伙人推荐合伙人
		else if("5".equals(type)){
			xxx(request, sessionMember, dataMap,startTime,endTime,signNo);
		}
    	dataMap.put("signNo", signNo);
        return "h5v1/panter/panter_total_morel";
    }

    /**
     * 
     * @param request
     * @param sessionMember
     * @param dataMap
     */
    public void xxx(HttpServletRequest request, Member sessionMember, Map<String, Object> dataMap,String startTime,String endTime,String memberTuijianId){
        String memberAreaId =  request.getParameter("areaId") == null ? "" : request.getParameter("areaId");
        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
        parterTuijianResult =  ordersService.getParterTuijian(""+sessionMember.getId(),startTime,endTime,memberTuijianId,memberAreaId,null);
        List<ParterTuijian> list = parterTuijianResult.getResult();
        dataMap.put("parterTuijian", list);
        if ("".equals(memberAreaId)) memberAreaId = "0";
        dataMap.put("areaId", memberAreaId);
    }
}
