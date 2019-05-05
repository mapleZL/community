package com.ejavashop.web.controller.parter;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.parter.ParterArea;
import com.ejavashop.entity.parter.ParterPercent;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.entity.parter.ParterTuijian;
import com.ejavashop.entity.parter.SalesDetailsVO;
import com.ejavashop.entity.parter.SumParterSaleVO;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.ResourceTree;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterAreaService;
import com.ejavashop.service.parter.IParterPercentService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;


@Controller
@RequestMapping(value = "admin/member/parter")
public class AdminParterController extends BaseController {
	
	@Resource
    private    IParterSignService   parterSignService;
	
	@Resource
    private IRegionsService     regionsService;
	
	@Resource
    private IParterAreaService	 parterAreaService;
	
	@Resource
    private  IParterPercentService   parterPercentService;
	
	@Resource
    private IMemberService memberService;
	
	@Resource
	private IOrdersService ordersService;
	
	private List<String>                parterRegionsIds = new ArrayList<String>();
    private List<Regions>           regionsList ;
    private byte[]                       token      = new byte[0];
	
	@RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/parter/parterlist";
    }
	
	
	@RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ParterSign>> list(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ParterSign>> serviceResult = parterSignService.getParterSign(queryMap,pager,"");
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ParterSign>> jsonResult = new HttpJsonResult<List<ParterSign>>();
        List<ParterSign>parterSignList = serviceResult.getResult();
        for (ParterSign parterSign : parterSignList) {
			String memberId = parterSign.getMemberId();
			Member member = memberService.getMemberById(Integer.parseInt(memberId)).getResult();
			parterSign.setMemberRealName(member.getRealName());
		}
        jsonResult.setRows(parterSignList);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
	
	@RequestMapping(value = "selectAddress", method = { RequestMethod.GET, RequestMethod.POST})
    public String selectAddress(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap,String parterSignId) {
        dataMap.put("parterSignId", parterSignId);
        ParterSign parterSign = parterSignService.getParterSignById(Integer.parseInt(parterSignId)).getResult();
        dataMap.put("memberName",parterSign.getMemberName());
        List<Regions> regionsList = regionsService.getParterSignArea(Integer.parseInt(parterSign.getMemberId()), parterSign.getSignNo()).getResult();
        StringBuffer sb = new StringBuffer();
        for (Regions regions : regionsList) {
        	sb.append(regions.getRegionName()+" ");
		}
        dataMap.put("regionsName",sb.toString());
		return "admin/member/parter/selectAddress";
	}
	
	
	@RequestMapping(value = "parterAddressTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> roleResTree(HttpServletRequest request,
                                                        Map<String, Object> dataMap,
                                                        String parterSignId) {
		List<ResourceTree> tree = new ArrayList<ResourceTree>();
		ServiceResult<List<Regions>> serviceResult = regionsService.getRegionsByParentId(0);
		if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
		Map<String, String> map = new HashMap<String, String>();
		map.put("parterSignId", parterSignId);//TODO
		
		ServiceResult<List<ParterArea>> parterAreaServiceResult = parterAreaService.getParterAreaByParterSignId(Integer.parseInt(parterSignId),null);
		List<ParterArea> parterAreaList = parterAreaServiceResult.getResult();
		parterRegionsIds.clear();
		for (ParterArea parterArea : parterAreaList) {
			parterRegionsIds.add(parterArea.getProvinceId());
		}
		
		synchronized (token) {
        this.regionsList = regionsService.page(new HashMap<String, String>(), null).getResult();
        generateTree(tree, serviceResult.getResult());
		}
		return tree;
	}

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<ResourceTree> generateTree(List<ResourceTree> treelist,
                                            List<Regions> data) {
        for (Regions sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getRegionName());
            if (this.parterRegionsIds != null && this.parterRegionsIds.size() > 0){
            	for (String str : this.parterRegionsIds) {
            		Integer parterRegionsId = Integer.parseInt(str);
            		if(parterRegionsId.intValue() == sr.getId().intValue()){
            			tree.setChecked(true);	
            		}
				}
            }
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(), getByPid(sr.getId())));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }

    private List<Regions> getByPid(Integer pid) {
        if (this.regionsList == null || this.regionsList.size() < 1)
            return null;
        List<Regions> reslist = new ArrayList<Regions>();
        for (Regions res : this.regionsList) {
            if (res.getParentId().intValue() == pid.intValue())
                reslist.add(res);
        }
        return reslist;
    }
    
    @RequestMapping(value = "saveParterRegions", method = { RequestMethod.POST })
    public void saveRoleRes(HttpServletRequest request, HttpServletResponse response, String parterSignId,
                            String resIds,String memberName) {
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;
       SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        String[] resArr = resIds.split(",");
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            pw = response.getWriter();
            
            serviceResult = parterAreaService.save(parterSignId, resArr,adminUser,memberName);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }
    
    @RequestMapping(value = "percent", method = { RequestMethod.GET, RequestMethod.POST})
    public String percent(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap,String parterSignId) {
        dataMap.put("ParterSign",parterSignService.getParterSignById(Integer.parseInt(parterSignId)).getResult());
		return "admin/member/parter/percent";
	}
    
    @RequestMapping(value = "saveParterPercent", method = { RequestMethod.POST })
    public void saveParterPercent(HttpServletRequest request, HttpServletResponse response) {
    	PrintWriter pw = null;
    	String msg = "保存成功";
        try {
        	   ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
	           response.setContentType("text/html;charset=utf-8");
	           SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
	           String parterSignId = request.getParameter("parterSignId");
	           String waType = request.getParameter("waType");
	           String percentType = request.getParameter("percentType");
	           SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	           for(int i = 0;i < 4 ; i++) {
	        	   String startTime = request.getParameter("startTime"+i);
	        	   String endTime = request.getParameter("endTime"+i);
	        	   String percent = request.getParameter("percent"+i);
	        	   if(!StringUtil.isEmpty(startTime) && !StringUtil.isEmpty(endTime) && !StringUtil.isEmpty(percent)){
	        		   ParterPercent parterPercent = new ParterPercent();
	        		   parterPercent.setEndTime(dateFormat.parse(endTime+ " 23:59:59"));
	        		   parterPercent.setParterSignId(parterSignId);
	        		   parterPercent.setPercent(new BigDecimal(percent));
	        		   parterPercent.setStartTime(dateFormat.parse(startTime+ " 00:00:00"));
	        		   parterPercent.setType(waType);
	        		   parterPercent.setPercentType(percentType);
	        		   serviceResult = parterPercentService.saveParterPercent(parterPercent);
	        	   }
	           }
	            pw = response.getWriter();
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }
    
    @RequestMapping(value = "percentlist", method = { RequestMethod.GET, RequestMethod.POST})
    public String percentlist(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/member/parter/percentlist";
	}
    
    @RequestMapping(value = "percentlistall", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody HttpJsonResult<List<ParterPercent>> percentlistall(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
    	Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ParterPercent>> serviceResult = parterPercentService.getParterPercent(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        for (ParterPercent parterPercent : serviceResult.getResult()) {
        	ParterSign prterSign = parterSignService.getParterSignById(Integer.parseInt(parterPercent.getParterSignId())).getResult();
        	parterPercent.setSignNo(prterSign.getSignNo());
        	parterPercent.setMemberRealName(memberService.getMemberById(Integer.parseInt(prterSign.getMemberId())).getResult().getRealName());
		}
        
        HttpJsonResult<List<ParterPercent>> jsonResult = new HttpJsonResult<List<ParterPercent>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
	}
    
    @RequestMapping(value = "percentDelete", method = { RequestMethod.GET, RequestMethod.POST})
    public @ResponseBody HttpJsonResult<Boolean> percentDelete(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap,Integer parterPercentId) {
    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = parterPercentService.percentDelete(parterPercentId);
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
    
    @RequestMapping(value = "percentEdit", method = { RequestMethod.GET, RequestMethod.POST})
    public String percentEdit(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap,@RequestParam(value = "id", required = true)Integer  id) {
    	ServiceResult<ParterPercent>serviceResult = parterPercentService.getParterPercentById(id);
    	ParterPercent   parterPercent  = serviceResult.getResult();
    	ParterSign parterSign = parterSignService.getParterSignById(Integer.parseInt(parterPercent.getParterSignId())).getResult() ;
    	dataMap.put("ParterPercent", parterPercent);
    	dataMap.put("parterSign", parterSign);
		return "admin/member/parter/percentEdit";
	}
    
    @RequestMapping(value = "updateParterPercent", method = { RequestMethod.GET, RequestMethod.POST})
    public void updateParterPercent(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap) {
    	PrintWriter pw = null;
    	String msg = "";
    	try {
    		response.setContentType("text/html;charset=utf-8");
    		String parterPercentId = request.getParameter("parterPercentId");
        	String percent = request.getParameter("percent");
        	ServiceResult<ParterPercent>serviceResult = parterPercentService.getParterPercentById(Integer.parseInt(parterPercentId));
        	ParterPercent   parterPercent  = serviceResult.getResult();
        	parterPercent.setPercent(new BigDecimal(percent));
        	ServiceResult<Integer>serviceResult1 = parterPercentService.updateParterPercent(parterPercent);
        	pw = response.getWriter();
        	msg = serviceResult1.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
    	pw.print(msg);
	}
    
    @RequestMapping(value = "listParterPercent", method = { RequestMethod.GET, RequestMethod.POST})
    public String listParterPercent(HttpServletRequest request, HttpServletResponse response,
                                                           Map<String, Object> dataMap,@RequestParam(value = "parterId", required = true)Integer  parterId) {
    	ServiceResult<List<ParterPercent>>serviceResult = parterPercentService.getParterPercentByParterSignId(parterId);
    	List<ParterPercent>   parterPercent  = serviceResult.getResult();
    	ParterSign parterSign = parterSignService.getParterSignById(parterId).getResult() ;
    	dataMap.put("ParterPercent", parterPercent);
    	dataMap.put("parterSign", parterSign);
		return "admin/member/parter/listParterPercent";
	}
    
    @RequestMapping(value = "/panerTotalMonth", method = { RequestMethod.GET })
    public String panerTotalMonth(Map<String, Object> dataMap) throws Exception {
    	Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
		}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	dataMap.put("parterSignList", parterSignList);
        return "admin/parter/panterTotalMonth";
    }
    
    @RequestMapping(value = "/panerTotalDays", method = { RequestMethod.GET })
    public String panerTotalDays(Map<String, Object> dataMap) throws Exception {
        Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
		}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	dataMap.put("parterSignList", parterSignList);
        return "admin/parter/panerTotalDays";
    }
    
    @RequestMapping(value = "/getSalesDetails", method = { RequestMethod.GET })
    public String getSalesDetails(Map<String, Object> dataMap) throws Exception {
    	Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
		}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	dataMap.put("parterSignList", parterSignList);
        return "admin/parter/salesDetails";
    }
    
    @RequestMapping(value = "/parterTuijian", method = { RequestMethod.GET })
    public String parterTuijian(Map<String, Object> dataMap) throws Exception {
    	Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
    	}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	dataMap.put("parterSignList", parterSignList);
    	return "admin/parter/parterTuijian";
    }
    
    /**
     * 每月销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalMonthList", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<SumParterSaleVO>> panerTotalMonthList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	HttpJsonResult<List<SumParterSaleVO>> jsonResult = new HttpJsonResult<List<SumParterSaleVO>>();
    	PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
    	String memberId = request.getParameter("q_parterSignMemberId");
    	String signNo = request.getParameter("q_signNo");
    	 String year = request.getParameter("q_year");
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
        memberResult =  ordersService.getSumParterSaleVO(Integer.parseInt(memberId),years,signNo);
        jsonResult.setRows(memberResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    /**
     * 每日销售汇总
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/panerTotalDaysList", method = { RequestMethod.GET , RequestMethod.POST})
    public @ResponseBody HttpJsonResult<List<SumParterSaleVO>> panerTotalDaysList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
    	HttpJsonResult<List<SumParterSaleVO>> jsonResult = new HttpJsonResult<List<SumParterSaleVO>>();
        String memberId = request.getParameter("q_parterSignMemberId");
        //查询用户信息
        ServiceResult<List<SumParterSaleVO>> memberResult = new ServiceResult<List<SumParterSaleVO>>();
        String signNo = request.getParameter("q_signNo");
        memberResult =  ordersService.getNewSumParterSalesYears(Integer.parseInt(memberId),1,pager,signNo);
        jsonResult.setRows(memberResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    
    @RequestMapping(value = "/getParterSignNo", method = { RequestMethod.GET , RequestMethod.POST})
    public @ResponseBody HttpJsonResult<List<ParterSign>> getParterSignNo(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String memberId = request.getParameter("memberId");
    	HttpJsonResult<List<ParterSign>> jsonResult = new HttpJsonResult<List<ParterSign>>();
    	ServiceResult<List<ParterSign>> parterSignListServiceResult = parterSignService.getByMemeberId(memberId);
        List<ParterSign> parterSignList = parterSignListServiceResult.getResult();
        jsonResult.setData(parterSignList);
    	return jsonResult;
    }
    
    @RequestMapping(value = "/getParterSignArea", method = { RequestMethod.GET , RequestMethod.POST})
    public @ResponseBody HttpJsonResult<List<Regions>> getParterSignArea(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String signNo = request.getParameter("signNo");
    	HttpJsonResult<List<Regions>> jsonResult = new HttpJsonResult<List<Regions>>();
    	ServiceResult<List<Regions>> parterSignListServiceResult = regionsService.getParterSignArea(null,signNo);
    	List<Regions> parterSignList = parterSignListServiceResult.getResult();
    	jsonResult.setData(parterSignList);
    	return jsonResult;
    }
    
    @RequestMapping(value = "/getParterTuiJian", method = { RequestMethod.GET , RequestMethod.POST})
    public @ResponseBody HttpJsonResult<List<Member>> getParterTuiJian(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String memberId = request.getParameter("memberId");
    	HttpJsonResult<List<Member>> jsonResult = new HttpJsonResult<List<Member>>();
    	ServiceResult<List<Member>> parterSignListServiceResult = memberService.getParterTuijianByMemberId1(Integer.parseInt(memberId),null);
    	List<Member> parterSignList = parterSignListServiceResult.getResult();
    	jsonResult.setData(parterSignList);
    	return jsonResult;
    }
    
    
    
    /**
     * 销售明细汇总
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/getSalesDetailsList", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<SalesDetailsVO>> getSalesDetailsList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
    	HttpJsonResult<List<SalesDetailsVO>> jsonResult = new HttpJsonResult<List<SalesDetailsVO>>();
        String memberId = request.getParameter("q_parterSignMemberId");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("q_startTime") ;
        String endTime =  request.getParameter("q_endTime");
        
        String areaId =  request.getParameter("q_areaId");
        
        if(memberId != null && memberId.equals("000")){
        	memberId = "";
        }
        
        if(areaId != null && areaId.equals("000")){
        	areaId = "";
        }
        
        if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        startTime = startTime + " 00:00:00";
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }
        endTime = endTime + " 23:59:59";
        //查询用户信息
        ServiceResult<List<SalesDetailsVO>> memberResult = new ServiceResult<List<SalesDetailsVO>>();
        String signNo = request.getParameter("q_signNo");
        if(signNo != null && signNo.equals("000")){
        	signNo = "";
        }
        memberResult =  ordersService.getSalesDetails(Integer.parseInt(memberId),startTime,endTime,pager,signNo,areaId);
        List<SalesDetailsVO> list = memberResult.getResult();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }
    
    
    /**
     * 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/getSalesDetailsByOrdersId", method = { RequestMethod.GET,RequestMethod.POST })
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
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return jsonResult;
    }
    
    /**
     * 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/getParterTuijianList", method = { RequestMethod.GET,RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<ParterTuijian>> getParterTuijianList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String url = request.getRequestURI();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        String startTime = request.getParameter("q_startTime") ;
        String endTime =  request.getParameter("q_endTime");
        
        String areaId =  request.getParameter("q_areaId");
        String memberId =  request.getParameter("q_parterSignMemberId");
        
        String q_tuiJIanMemberId =  request.getParameter("q_tuiJIanMemberId");
        
        if(memberId != null && memberId.equals("000")){
        	memberId = "";
        }
        if(areaId != null && areaId.equals("000")){
        	areaId = "";
        }
        
        
       /* if(StringUtil.isEmpty(startTime)){
        	startTime = "2016-07-31";
        }
        
        if(StringUtil.isEmpty(endTime)){
        	Date date = new Date();
        	endTime = sdf.format(date);
        }*/
        
        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
        parterTuijianResult =  ordersService.getParterTuijian(memberId,startTime,endTime,q_tuiJIanMemberId,areaId,null);
        List<ParterTuijian> list = parterTuijianResult.getResult();
    	HttpJsonResult<List<ParterTuijian>> jsonResult = new HttpJsonResult<List<ParterTuijian>>();
    	
    	if (!parterTuijianResult.getSuccess()) {
    		if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(parterTuijianResult.getCode())) {
    			throw new RuntimeException(parterTuijianResult.getMessage());
    		} else {
    			jsonResult = new HttpJsonResult<List<ParterTuijian>>(parterTuijianResult.getMessage());
    		}
    	}
    	jsonResult.setRows(list);
    	jsonResult.setTotal(list.size());
    	return jsonResult;
    }
    
    
    @RequestMapping(value = "/parterSalesSum", method = { RequestMethod.GET })
    public String parterSalesSum(Map<String, Object> dataMap) throws Exception {
    	Map<String, String>queryMap = new HashMap<String, String>();
    	ServiceResult<List<ParterSign>> parterSignListResult = parterSignService.getParterSign(queryMap, null,"1");
    	List<ParterSign> parterSignList = parterSignListResult.getResult();
    	for (ParterSign parterSign : parterSignList) {
    		ServiceResult<Member> memberServiceResult = memberService.getMemberById(Integer.parseInt(parterSign.getMemberId()));
    		Member member = memberServiceResult.getResult();
    		parterSign.setMemberRealName(member.getRealName());
    	}
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
    	dataMap.put("parterSignList", parterSignList);
    	return "admin/parter/parterSalesSum";
    }
    
    
    
    /**
     * 合伙人提点统计
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/parterSalesSumList", method = { RequestMethod.GET, RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<ParterTuijian>> parterSalesSumPercent(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
    	String memberId =  request.getParameter("q_parterSignMemberId");
    	String signNo = request.getParameter("q_signNo");
    	String startTime = request.getParameter("q_startTime") ;
        String endTime =  request.getParameter("q_endTime");
        
        String areaId = request.getParameter("q_areaId") == null ? "" : request.getParameter("q_areaId");
        
        if(memberId != null && memberId.equals("000")){
        	memberId = "";
        }
        
        if(areaId != null && areaId.equals("000")){
        	areaId = "";
        }
        ServiceResult<List<ParterTuijian>> parterTuijianResult = new ServiceResult<List<ParterTuijian>>();
        parterTuijianResult =  ordersService.getParterTuijian(memberId,startTime,endTime,null,areaId,signNo);
        List<ParterTuijian> list = parterTuijianResult.getResult();
        
        HttpJsonResult<List<ParterTuijian>> jsonResult = new HttpJsonResult<List<ParterTuijian>>();
    	
    	if (!parterTuijianResult.getSuccess()) {
    		if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(parterTuijianResult.getCode())) {
    			throw new RuntimeException(parterTuijianResult.getMessage());
    		} else {
    			jsonResult = new HttpJsonResult<List<ParterTuijian>>(parterTuijianResult.getMessage());
    		}
    	}
    	jsonResult.setRows(list);
    	jsonResult.setTotal(list.size());
    	return jsonResult;
    }
    
}
