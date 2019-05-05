package com.ejavashop.web.controller.pcindex;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorProduct;
import com.ejavashop.service.pcindex.IPcIndexFloorService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.product.IProductSortService;

/**
 * 精品推荐后台设置 
 * 
 * 前台 商品详情页左侧 进行渲染显示的部分
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/jprecommend1")
public class PcIndexFloorProductSortController {

	 @Resource
    private IProductService   productService;
	 
	 @Resource
	 private IProductSortService productSortService;
	 
	 @Resource
	 private	IPcIndexFloorService	pcIndexFloorService;
	/**
     * PC端楼层商品排序显示
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<PcIndexFloorProduct>> serviceResult = productSortService.pageProduct(queryMap, null);
        List<PcIndexFloorProduct> result = serviceResult.getResult();
        for  ( int  i  =   0 ; i  <  result.size()  -   1 ; i ++ )   {   
            for  ( int  j  =  result.size()  -   1 ; j  >  i; j -- )   {   
              if  (result.get(j).getSortType().equals(result.get(i).getSortType()))   {   
            	  result.remove(result.get(j));   
              }   
            }   
          } 
          dataMap.put("floors", result);
        
        return "admin/pcindex/jprecommend1/jprecommendlist";
    }
    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexFloorProduct>> list(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {
    	  Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
          PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
          ServiceResult<List<PcIndexFloorProduct>> serviceResult = productSortService.pageProduct(queryMap, pager);
          pager =serviceResult.getPager();
          if (!serviceResult.getSuccess()) {
              if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                  throw new RuntimeException(serviceResult.getMessage());
              } else {
                  throw new BusinessException(serviceResult.getMessage());
              }
          }
          List<PcIndexFloorProduct> list = serviceResult.getResult();
          HttpJsonResult<List<PcIndexFloorProduct>> jsonResult = new HttpJsonResult<List<PcIndexFloorProduct>>();
          //jsonResult.setRows(serviceResult.getResult());
          jsonResult.setRows(list);
          jsonResult.setTotal(pager.getRowsCount());
          return jsonResult;
    }
    /**
     * 把对应的商品is_top 修改为1 即不推荐
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    public void delete(HttpServletRequest request,  HttpServletResponse response,
                            Integer id) {
    	response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        if (id == null || id == 0)
            throw new BusinessException("请选择要操作的商品");
        try {
			pw = response.getWriter();
			productSortService.delete(id);
		} catch (IOException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
        pw.print(msg);
    	
    }
    
    
}
