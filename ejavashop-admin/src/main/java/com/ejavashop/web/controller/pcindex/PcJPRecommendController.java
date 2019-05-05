package com.ejavashop.web.controller.pcindex;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.shopm.pcindex.PcRecommend;
import com.ejavashop.service.product.IProductService;

/**
 * 精品推荐后台设置 
 * 
 * 前台 商品详情页左侧 进行渲染显示的部分
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "admin/pcindex/jprecommend")
public class PcJPRecommendController {

	 @Resource
    private IProductService        productService;
	/**
     * PC端推荐商品列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/pcindex/jprecommend/jprecommendlist";
    }
    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {
    	  Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
          queryMap.put("q_isTop", "2");
          queryMap.put("q_state", "6");
          PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
          ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, pager);
          if (!serviceResult.getSuccess()) {
              if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                  throw new RuntimeException(serviceResult.getMessage());
              } else {
                  throw new BusinessException(serviceResult.getMessage());
              }
          }

          HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
          jsonResult.setRows(serviceResult.getResult());
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
                            Integer id, Integer isTop) {
    	response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        if (id == null || id == 0)
            throw new BusinessException("请选择要操作的商品");
        if (isTop == null)
            throw new BusinessException("未知操作");
        try {
			pw = response.getWriter();
			productService.updateProductRecommend(id, isTop);
		} catch (IOException e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
        pw.print(msg);
    }
    
    
}
