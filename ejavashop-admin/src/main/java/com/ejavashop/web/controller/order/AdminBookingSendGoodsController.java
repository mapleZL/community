package com.ejavashop.web.controller.order;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.system.Code;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/*
 * 商品预约发货
 */
@Controller
@RequestMapping(value = "admin/product/sendgoods")
public class AdminBookingSendGoodsController extends BaseController{
	
	 @Resource(name = "bookingSendGoodsService")
	 IBookingSendGoodsService bookingSendGoodsService;
	 
	  @Resource
	  private IRegionsService     regionsService;
	  
	  @Resource(name = "ordersProductService")
	  IOrdersProductService ordersProductService;
	  
	 
	   /**
	     * 默认页面
	     * @param dataMap
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "", method = { RequestMethod.GET })
	    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
	        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
	        return "admin/product/sendgoods/booklist";
	    }
	 
	 
	 
	 
    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<BookingSendGoods>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
    	 Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<BookingSendGoods>> serviceResult = bookingSendGoodsService.page(queryMap,
             pager);
         List<BookingSendGoods> list = serviceResult.getResult();

         HttpJsonResult<List<BookingSendGoods>> jsonResult = new HttpJsonResult<List<BookingSendGoods>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap,Integer id) {
        Code code = new Code();
        dataMap.put("code", code);
        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        dataMap.put("provinceList", provinceResult.getResult());
        	
        	
        return  "admin/product/sendgoods/goodsedit";
    }
    
    
    
    /**
     * 预约发货审核
     * @param request
     * @param response
     * @param id
     * @param type
     */
    @RequestMapping(value = "audit")
    public void auditGoods(HttpServletRequest request, HttpServletResponse response, String type,
    		String checkNote,Integer id ) {
    	SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
    	String checkMan = "";
    	if(systemAdmin != null){
    		checkMan = systemAdmin.getName();
    	}
        response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            if (id == null || id == 0)
                throw new BusinessException("请选择要操作的商品");
            if (type == null)
                throw new BusinessException("未知操作");
            pw = response.getWriter();
            bookingSendGoodsService.auditSendGoods(id, type,checkMan,checkNote);
        } catch (Exception e) {
            log.error("[admin][AdminBookingSendGoodsController] auditGoods exception", e);
            msg = e.getMessage();
            e.printStackTrace();
        }
        pw.print(msg);
    }
    
    @RequestMapping(value = "del")
    public void delGoods(HttpServletRequest request, HttpServletResponse response, Integer id ) {
    	response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
        	if (id == null || id == 0)
        		throw new BusinessException("请选择要操作的订单");
			pw = response.getWriter();
			bookingSendGoodsService.delGoods(id);
		} catch (IOException e) {
			log.error("[admin][AdminBookingSendGoodsController] delGoods exception", e);
            msg = e.getMessage();
			e.printStackTrace();
		}
        pw.print(msg);
    }
    
    @RequestMapping(value="queryOrdersId",method = { RequestMethod.GET })
    public void queryOrdersId(HttpServletRequest request, HttpServletResponse response, Integer ordersId ){
    	response.setContentType("text/plain;charset=utf-8");
        try {
			PrintWriter pw = response.getWriter();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
    
    @RequestMapping(value="returngoods",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void returngoods(HttpServletRequest request, HttpServletResponse response,Integer id,String type,String checkNote) throws UnsupportedEncodingException{
        response.setContentType("text/plain;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            if (id == null || id == 0)
                throw new BusinessException("请选择要操作的订单");
            if(type == null || type.equals(""))
            	throw new BusinessException("未知操作");
            int status = 0;
            if(type.equals("return")){
            	status = 7;
            }
            if(type.equals("uncheck")){
            	status = 9;
            }
            pw = response.getWriter();
            bookingSendGoodsService.returngoods(id,status,checkNote);
        } catch (Exception e) {
            log.error("[admin][AdminBookingSendGoodsController] auditGoods exception", e);
            msg = e.getMessage();
            e.printStackTrace();
        }
        pw.print(msg);
    }
    
    @RequestMapping(value="passreturn",method={RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public void passReturnGoods(HttpServletRequest request, HttpServletResponse response,Integer id){
    	response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
	        if (id == null || id == 0)
	            throw new BusinessException("请选择要操作的订单");
	        pw = response.getWriter();
	        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
	        String checkMan = "";
	        if(systemAdmin != null){
	        	checkMan = systemAdmin.getName();
	        }
	        bookingSendGoodsService.passReturnGoods(id,checkMan);
        } catch (Exception e) {
            log.error("[admin][AdminBookingSendGoodsController] passReturnGoods exception", e);
            msg = e.getMessage();
            e.printStackTrace();
        }
        pw.print(msg);
    }
}
