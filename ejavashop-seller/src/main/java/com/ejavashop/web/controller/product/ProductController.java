package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.shopm.MIndexFloorData;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.mindex.IMIndexService;
import com.ejavashop.service.pcindex.IPcIndexFloorDataService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductPictureService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

/**
 * 商品品牌
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {
    @Resource
    private IProductService        productService;
    @Resource
    private IProductPictureService productPicService;
    @Resource
    private IMemberService         memberService;
    @Resource
    private IProductGoodsService   productGoodsService;
    @Resource
    private ISellerService         sellerService;
    @Resource
    private IMIndexService  mIndexService;
    @Resource
    private IPcIndexFloorDataService  pcIndexFloorDataService;
    
    private String                 baseUrl = "admin/product/manager/";
    private Logger                 log     = Logger.getLogger(this.getClass());

    /**
     * 商品列表无分页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listnopage", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> listNoPage(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {
    	String channel = request.getParameter("channel");
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);//{ pageIndex:1, pageSize:20, rowsCount:0 }
        ServiceResult<List<Product>> serviceResult = null;
        int exceptNumber = 0;
        if(channel!=null && "h5floordata".equals(channel)){//移动端楼层数据，则商品列表中不应该存在已经设置过的楼层数据
        	queryMap.put("q_dataType", "1");
        	ServiceResult<List<MIndexFloorData>> serviceResultFloorData = mIndexService.getMIndexFloorDatas(
                    queryMap, null);
        	List<MIndexFloorData> floorDatas = serviceResultFloorData.getResult();
        	List<Integer> productIds = new ArrayList<Integer>();
        	if(floorDatas.size()>0){//存在楼层数据
        		exceptNumber = floorDatas.size();
        		for (MIndexFloorData mIndexFloorData : floorDatas) {
        			productIds.add(mIndexFloorData.getRefId());
				}
        	}
        	serviceResult = productService.pageProductByh5fllordata(queryMap, pager,productIds);
        }else if(channel!=null && "pcfloordata".equals(channel)){//PC端楼层数据，则商品列表不应该是设置过的PC端楼层数据
        	queryMap.put("q_dataType", "1");
        	ServiceResult<List<PcIndexFloorData>> serviceResultFloorData = pcIndexFloorDataService.getPcIndexFloorDatas(
        			queryMap, null);
        	List<PcIndexFloorData> floorDatas = serviceResultFloorData.getResult();
        	List<Integer> productIds = new ArrayList<Integer>();
        	if(floorDatas.size()>0){//存在楼层数据
        		exceptNumber = floorDatas.size();
        		for (PcIndexFloorData pcIndexFloorData : floorDatas) {
        			productIds.add(pcIndexFloorData.getRefId());
        		}
        	}
        	serviceResult = productService.pageproductBypcfloordata(queryMap, pager,productIds);
        }else{
        	serviceResult = productService.pageProduct(queryMap, pager);
        }
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
        log.debug("jsonResult size=" + jsonResult.getTotal());
        return jsonResult;
    }

}
