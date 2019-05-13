package com.phkj.web.controller.product;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ConvertUtil;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.StringUtil;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.freemarkerutil.DomainUrlUtil;
import com.phkj.entity.product.Product;
import com.phkj.entity.product.ProductAttr;
import com.phkj.entity.product.ProductBrand;
import com.phkj.entity.product.ProductCate;
import com.phkj.entity.product.ProductGoods;
import com.phkj.entity.product.ProductNorm;
import com.phkj.entity.product.ProductNormAttr;
import com.phkj.entity.product.ProductNormAttrOpt;
import com.phkj.entity.product.ProductPicture;
import com.phkj.entity.product.ProductType;
import com.phkj.entity.product.ProductTypeAttr;
import com.phkj.entity.product.WmsClassify;
import com.phkj.entity.seller.Seller;
import com.phkj.entity.shopm.MIndexFloorData;
import com.phkj.entity.shopm.pcindex.PcIndexFloorData;
import com.phkj.service.member.IMemberService;
import com.phkj.service.mindex.IMIndexService;
import com.phkj.service.product.IProductService;
import com.phkj.service.seller.ISellerService;
import com.phkj.service.system.ISystemLogsService;
import com.phkj.web.controller.BaseController;
/**
 * 商品品牌
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {
    @Resource
    private IProductService        productService;
    @Resource
    private IMemberService         memberService;
    @Resource
    private ISellerService         sellerService;
    @Resource
    private IMIndexService          mIndexService;
    @Resource
    private ISystemLogsService         systemLogsService;
    
    private String                 baseUrl = "admin/product/manager/";
    private Logger                 log     = Logger.getLogger(this.getClass());
    private static final Logger  ILog = LogManager.getLogger("oms_interface");

    @RequestMapping(value = "waitSale", method = { RequestMethod.GET })
    public String waitSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        Map<String, String> sellerMap = new HashMap<String, String>();
        sellerMap.put("q_auditStatus", "2");
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
        dataMap.put("sellers", sellers.getResult());
        return baseUrl + "listwaitsale";
    }
    
    @RequestMapping(value = "waitSale2", method = { RequestMethod.GET })
    public String waitSale2(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        Map<String, String> sellerMap = new HashMap<String, String>();
        sellerMap.put("q_auditStatus", "2");
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
        dataMap.put("sellers", sellers.getResult());
        return baseUrl + "listwaitsale2";
    }

    @RequestMapping(value = "onSale", method = { RequestMethod.GET })
    public String onSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "6");//6、上架；
        Map<String, String> sellerMap = new HashMap<String, String>();
        sellerMap.put("q_auditStatus", "2");
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
        dataMap.put("sellers", sellers.getResult());
        return baseUrl + "listonsale";
    }

    @RequestMapping(value = "delSale", method = { RequestMethod.GET })
    public String delSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "5");//5、商品删除；
        return baseUrl + "listdelsale";
    }

    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (!StringUtil.isEmpty(request.getParameter("q_state1"))) {
            queryMap.put("q_state", request.getParameter("q_state1"));
        }
        if (!StringUtil.isEmpty(request.getParameter("q_sellerId"))) {
            queryMap.put("q_sellerId", request.getParameter("q_sellerId"));
        }
        queryMap.put("q_priceStatus", "1");
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
        log.debug("jsonResult size=" + jsonResult.getTotal());
        return jsonResult;
    }
    
    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list2", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list2(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (!StringUtil.isEmpty(request.getParameter("q_state1"))) {
            queryMap.put("q_state", request.getParameter("q_state1"));
        }
        if (!StringUtil.isEmpty(request.getParameter("q_sellerId"))) {
            queryMap.put("q_sellerId", request.getParameter("q_sellerId"));
        }
        queryMap.put("q_priceStatus", "2");
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
        log.debug("jsonResult size=" + jsonResult.getTotal());
        return jsonResult;
    }

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
        	ServiceResult<List<PcIndexFloorData>> serviceResultFloorData = new ServiceResult<>();
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

    /**
     * 根据商品ID查询货品
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list_goods", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductGoods>> listGoods(Integer productId,
                                                                      HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        ServiceResult<List<ProductGoods>> serviceResult = new ServiceResult<>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductGoods>> jsonResult = new HttpJsonResult<List<ProductGoods>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }


    /**
     * 跳转到单品页
     * @param productId
     * @return
     */
    @RequestMapping(value = "/toDetail.html", method = { RequestMethod.GET })
    public String toDetail(@RequestParam(value = "productId", required = true) Integer productId) {
        String url = DomainUrlUtil.getEJS_FRONT_URL() + "/front/toDetail.html?productId="
                     + productId;
        return "redirect:" + url;
    }

    @RequestMapping(value = "recommond", method = { RequestMethod.GET })
    public void recommond(HttpServletRequest request, HttpServletResponse response, Integer id,
                          Boolean isRec) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        try {
            if (isRec == null)
                throw new BusinessException("未知操作");

            Integer isTop = ConstantsEJS.PRODUCT_IS_TOP_1;
            if (isRec) {
                isTop = ConstantsEJS.PRODUCT_IS_TOP_2;
                msg = "推荐成功";
            } else {
                isTop = ConstantsEJS.PRODUCT_IS_TOP_1;
                msg = "取消推荐成功";
            }
            ServiceResult<Boolean> sr = productService.updateProductRecommend(id, isTop);
            if (!sr.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
                    throw new RuntimeException(sr.getMessage());
                } else {
                    throw new BusinessException(sr.getMessage());
                }
            }
            pw = response.getWriter();
        } catch (IOException e) {
            log.error("[admin][ProductController] recommond exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "删除成功";
        PrintWriter pw = null;

        try {
            ServiceResult<Boolean> sr = productService.updateProductState(id, Product.STATE_5);
            if (!sr.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
                    throw new RuntimeException(sr.getMessage());
                } else {
                    throw new BusinessException(sr.getMessage());
                }
            }
            pw = response.getWriter();
        } catch (IOException e) {
            log.error("[admin][ProductController] del exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    
    /** 
     * 设置正文单元样式 
     * @param workbook 
     * @return 
     */  
    public static HSSFCellStyle createBodyCellStyle(HSSFWorkbook workbook){   
        HSSFCellStyle cellStyle = workbook.createCellStyle();  
        HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short) 8);    
        font.setFontName(HSSFFont.FONT_ARIAL);//设置标题字体  
        cellStyle.setFont(font);  
        cellStyle = workbook.createCellStyle();  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中         
        return cellStyle;  
    }  
    /** 
     * 设置正文单元时间样式 
     * @param workbook 
     * @return 
     */  
    public static HSSFCellStyle createDateBodyCellStyle(HSSFWorkbook workbook){   
        HSSFCellStyle cellStyle = workbook.createCellStyle();  
        HSSFFont font = workbook.createFont();  
        font.setFontHeightInPoints((short) 8);    
        font.setFontName(HSSFFont.FONT_ARIAL);//设置标题字体  
        cellStyle.setFont(font);  
        cellStyle = workbook.createCellStyle();  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中     
        HSSFDataFormat format= workbook.createDataFormat();  
        cellStyle.setDataFormat(format.getFormat("yyyy-mm-dd"));          
        return cellStyle;  
    }     
  
    /** 
     * 设置标题单元样式 
     * @param workbook 
     * @return 
     */  
    public static HSSFCellStyle createTitleCellStyle(HSSFWorkbook workbook){   
        HSSFCellStyle cellStyle = workbook.createCellStyle();  
        HSSFFont font = workbook.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontHeightInPoints((short) 8);    
        font.setFontName(HSSFFont.FONT_ARIAL);//设置标题字体  
        cellStyle.setFont(font);  
        cellStyle = workbook.createCellStyle();  
        cellStyle.setFont(font);//设置列标题样式  
        cellStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);// 设置背景色  
        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 居中         
        return cellStyle;  
    }     
    /** 
     * 写入标题行 
     * @param workbook 
     * @return 
     */  
    public static void writeTitleContent (HSSFSheet sheet,HSSFCellStyle cellStyle,String[] title){   
        HSSFRow row = null;  
        HSSFCell cell = null;         
        //标题  
        row = sheet.createRow(0);          
        //第一行写入标题行  
        cell = row.createCell((short)0);//序号  
        cell.setCellStyle(cellStyle);  
        cell.setCellValue("序号"); 
        if (title != null && title.length > 0) {
        	for (int i = 0; i < title.length ; i++) {
    		   cell = row.createCell((short)i+1);  
	           cell.setCellStyle(cellStyle);  
	           cell.setCellValue(title[i]);  
    		}
        }
    }  
    public static void setSheetColumn(HSSFSheet sheet){  
         sheet.setColumnWidth((short) 2, (short) 6250);//设置货主列宽  
         sheet.setColumnWidth((short) 3, (short) 5250);//设置货主列宽  
    }
    
    /**
     * 编辑商品
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id, Map<String, Object> dataMap) {
        String rtnPath = baseUrl + "productedit";
        /**查询商品信息**/
        ServiceResult<Product> productServiceResult = productService.getProductById(id);
        if (!productServiceResult.getSuccess() || null == productServiceResult.getResult()) {
            dataMap.put("message", "编辑商品失败，商品不存在");
            return rtnPath;//
        }
        Product product = productServiceResult.getResult();
        dataMap.put("product", product);

        //商品二级分类
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<>();
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            dataMap.put("cate", serviceResult.getResult());
        }
        /**品牌名称**/
        ServiceResult<ProductCate> cateResult = new ServiceResult<>();
        if (!cateResult.getSuccess() || cateResult.getResult() == null) {
            dataMap.put("message", "该分类下无可以经营的品牌，请重新选择分类，或者联系商城管理员");
            return rtnPath;
        }
        ServiceResult<List<ProductBrand>> brandResult = new ServiceResult<>();
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
              dataMap.put("brand", brandResult.getResult());
        }
//        ServiceResult<ProductBrand> brandResult = productBrandService.getById(product
//            .getProductBrandId());
//        if (brandResult.getSuccess() && brandResult.getResult() != null) {
//            dataMap.put("brand", brandResult.getResult().getNameFirst() + " "
//                                 + brandResult.getResult().getName());
//        }
        /**商品类型*/
        assembleProp(Integer.valueOf(product.getProductCateId()), dataMap, product.getId());

        /**商品图片**/
        ServiceResult<List<ProductPicture>> pictureServiceResult = new ServiceResult<>();
        if (pictureServiceResult.getSuccess() && pictureServiceResult.getResult() != null) {
            for (ProductPicture pic : pictureServiceResult.getResult()) {
                String path = pic.getImagePath();
                path = DomainUrlUtil.getEJS_IMAGE_RESOURCES() + path;
                pic.setImagePath(path);
            }
            dataMap.put("pic", pictureServiceResult.getResult());
        }

        //选中的颜色规格 
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("sellerId", product.getSellerId() + "");
        queryMap.put("productId", id + "");
        queryMap.put("flag", "2");
        ServiceResult<List<ProductNormAttrOpt>> optservice = new ServiceResult<>();
        dataMap.put("customAttr", optservice.getResult());

        dataMap.put("edit", "edit");
        
        ServiceResult<List<Seller>> seller = sellerService.getSellerListAll();
        dataMap.put("seller", seller.getResult());
        
        //查询wms分类 使用中的
        Integer state = 1;
        ServiceResult<List<WmsClassify>> serviceResult2 = new ServiceResult<>();
        if (serviceResult2.getSuccess() == true && serviceResult2.getResult() != null) {
            dataMap.put("wmsClassify", serviceResult2.getResult());
        }
        
        return "admin/product/pdt/productedit";
    }  
    
    @RequestMapping(value = "validatesku", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Object> validatesku(HttpServletRequest request,
                                                            @RequestParam("sku") String sku ,@RequestParam("sellerId") String sellerId) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        jsonResult.setMessage("");
        if (!"".equals(sku)) {//sku校验
            Map<String, String> queryMap = new HashMap<String, String>();
            if(sellerId == null || "".equals(sellerId)){
                jsonResult.setMessage("获取货主时发生异常，请检查！");
                return jsonResult;
            }
            queryMap.put("sellerId", sellerId.toString());
            queryMap.put("sku", sku);
            ServiceResult<Boolean> serviceResult = new ServiceResult<>();
            if (serviceResult.getResult()) {
                jsonResult.setMessage("该商品对应SKU已存在，请重新输入！");
                return jsonResult;
            }
        } else {
            String barCode = StringUtil.nullSafeString(request.getParameter("barCode"));
            if (!"".equals(barCode)) {//barCode校验
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("barCode", barCode);
                String id = StringUtil.nullSafeString(request.getParameter("id"));
                if (!StringUtil.isEmpty(id)) {
                    queryMap.put("id", id);
                }
                ServiceResult<Boolean> serviceResult = new ServiceResult<>();
                if (serviceResult.getResult()) {
                    jsonResult.setMessage("商品编码已存在，请重新输入！");
                    return jsonResult;
                }
            }
        }
        return jsonResult;
    }
    
    
    /**
     * 根据商品分类id组装商品类型属性、商品规格属性
     *
     * @param productCateId
     * @param dataMap
     */
    private void assembleProp(Integer productCateId, Map<String, Object> dataMap, Integer productId) {
        ServiceResult<ProductCate> cate = new ServiceResult<>();
        if (cate.getSuccess() == true && cate.getResult() != null) {

            Integer typeId = cate.getResult().getProductTypeId();
            //初始化商品属性信息
            typeId = 1;
            ServiceResult<List<ProductTypeAttr>> typeAttr = new ServiceResult<>();

            Map<String, String> sellerMap = new HashMap<String, String>();
            sellerMap.put("q_auditStatus", "2");
            ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
            dataMap.put("sellers", sellers.getResult());

            /**组装商品类型属性*/
            if (typeAttr.getSuccess() == true && typeAttr.getResult() != null
                && typeAttr.getResult().size() > 0) {
                List<ProductTypeAttr> attrList = typeAttr.getResult();
                List<Map<String, Object>> queryTypeAttr = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> custTypeAttr = new ArrayList<Map<String, Object>>();

                List<ProductAttr> productAttrList = null;
                if (null != productId) {
                    //编辑商品时候使用
                    ServiceResult<List<ProductAttr>> attrServiceResult = new ServiceResult<>();
                    if (attrServiceResult.getSuccess() && attrServiceResult.getResult() != null) {
                        productAttrList = attrServiceResult.getResult();
                    }
                }
                for (ProductTypeAttr attr1 : attrList) {
                    Map<String, Object> attrMap = new HashMap<String, Object>();
                    if (attr1.getType() == 1) {
                        processAttr(attr1, attrMap, productAttrList);
                        queryTypeAttr.add(attrMap);//查询属性
                    } else {
                        processAttr(attr1, attrMap, productAttrList);
                        custTypeAttr.add(attrMap);//自定义属性
                    }
                }
                dataMap.put("queryTypeAttr", queryTypeAttr);
                dataMap.put("custTypeAttr", custTypeAttr);
            }

            /**组装商品属性信息**/
            ServiceResult<ProductType> serviceResult = new ServiceResult<>();
            if (serviceResult.getSuccess() && serviceResult.getResult() != null) {
                ProductType productType = serviceResult.getResult();
                String productNormIds = productType.getProductNormIds();
                if (!StringUtil.isEmpty(productNormIds)) {
                    String[] normIds = productNormIds.split(",");
                    List<Map<String, Object>> normList = new ArrayList<Map<String, Object>>(
                        normIds.length);
                    for (String normId : normIds) {
                        Map<String, Object> attrMap = null;
                        Integer id = Integer.valueOf(normId);
                        ServiceResult<ProductNorm> normResult = new ServiceResult<>();

                        if (normResult.getSuccess() && normResult.getResult() != null) {
                            attrMap = new HashMap<String, Object>(2);
                            ProductNorm result = normResult.getResult();
                            List<ProductNormAttr> list = result.getAttrList();
                            List<Map<String, Object>> attrList = new ArrayList<Map<String, Object>>(
                                result.getAttrList().size());

                            /**构造货品属性**/
                            List<String> normAttrIdList = new ArrayList<String>();
                            if (null != productId) {
                                Map<String, String> queryMap = new HashMap<String, String>(1);
                                queryMap.put("q_productId", String.valueOf(productId));
                                ServiceResult<List<ProductGoods>> listServiceResult = new ServiceResult<>();
                                if (listServiceResult.getSuccess()
                                    && listServiceResult.getResult() != null
                                    && listServiceResult.getResult().size() > 0) {
                                    List<ProductGoods> goodsList = listServiceResult.getResult();

                                    processGoods(goodsList, dataMap);//货品信息

                                    for (ProductGoods goods : goodsList) {
                                        String normAttrId = goods.getNormAttrId();
                                        if (!StringUtil.isEmpty(normAttrId)) {
                                            String[] split = normAttrId.split(",");
                                            for (String str : split) {
                                                if (!StringUtil.isEmpty(str)) {
                                                    normAttrIdList.add(str);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (normAttrIdList.size() > 0) {
                                Set<String> set = new HashSet<String>();
                                set.addAll(normAttrIdList);
                                normAttrIdList.clear();
                                normAttrIdList.addAll(set);
                            }

                            attrMap.put("id", result.getId());
                            attrMap.put("name", result.getName());
                            attrMap.put("attrList", attrList);
                            if (null != list && list.size() > 0) {
                                Map<String, Object> map = null;
                                for (ProductNormAttr attr : list) {
                                    map = new HashMap<String, Object>(3);
                                    map.put("id", attr.getId());
                                    map.put("name", attr.getName());
                                    if (normAttrIdList.size() > 0) {
                                        for (String str : normAttrIdList) {
                                            if (String.valueOf(attr.getId()).equals(str)) {
                                                map.put("checked", "true");
                                            }
                                        }
                                    }
                                    attrList.add(map);
                                }
                            }
                            normList.add(attrMap);
                        }
                    }
                    dataMap.put("normList", normList);
                }
            }
        }
    }
    
    /**
     * 构造货品信息
     *
     * @param goodsList
     * @param dataMap
     */
    private void processGoods(List<ProductGoods> goodsList, Map<String, Object> dataMap) {
        if (null != goodsList && goodsList.size() > 0) {
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>(goodsList.size());
            Map<String, Object> goodMap = null;
            List<String> columnList = new ArrayList<String>();

            for (ProductGoods good : goodsList) {
                goodMap = new HashMap<String, Object>();
                String normAttrId = good.getNormAttrId();
                if (!StringUtil.isEmpty(normAttrId)) {
                    String[] split = normAttrId.split(",");

                    if (null != split && split.length == 1) {//一列规格
                        goodMap.put("normId1", normAttrId);
                        String normName = good.getNormName();
                        String[] arr = normName.split(",");
                        goodMap.put("normName1", arr.length > 1 ? arr[1] : arr[0]);
                        columnList.add(normName.split(",")[0]);
                    } else if (null != split && split.length == 2) {//两列规格
                        goodMap.put("normId1", split[0]);
                        goodMap.put("normId2", split[1]);
                        String normName = good.getNormName();
                        String column1 = normName.split(";")[0];
                        column1 = column1.substring(0, column1.indexOf(","));
                        String column2 = normName.split(";")[1];
                        column2 = column2.substring(0, column2.indexOf(","));
                        String normName1 = normName.split(";")[0];
                        normName1 = normName1.substring(normName1.indexOf(",") + 1,
                            normName1.length());
                        String normName2 = normName.split(";")[1];
                        normName2 = normName2.substring(normName2.indexOf(",") + 1,
                            normName2.length());

                        goodMap.put("normName1", normName1);
                        goodMap.put("normName2", normName2);
                        columnList.add(column1);
                        columnList.add(column2);
                    }

                    goodMap.put("sku", good.getSku());
                    goodMap.put("stock", good.getProductStock());
                    goodMap.put("mobilePrice", good.getMallMobilePrice());
                    goodMap.put("pcPrice", good.getMallPcPrice());
                    goodMap.put("barCode", good.getBarCode());
                    goodMap.put("barCodeCS",good.getBarCodeCS());
                    goodMap.put("barCodePL",good.getBarCodePL());
                    goodMap.put("isVirtualBom",good.getIsVirtualBom());

                    goods.add(goodMap);

                    Set<String> set = new HashSet<String>();
                    set.addAll(columnList);
                    columnList.clear();
                    columnList.addAll(set);
                    dataMap.put("goods", goods);
                    dataMap.put("column", columnList);
                }
            }
        }
    }
    
    private void processAttr(ProductTypeAttr attr1, Map<String, Object> attrMap,
                             List<ProductAttr> productAttrList) {
        List<String> attrValList = new ArrayList<String>();
        String attrVal = attr1.getValue();
        if (!StringUtil.isEmpty(attrVal)) {
            String[] split = attrVal.split(",");
            for (String str : split) {
                if (StringUtil.isEmpty(str))
                    continue;
                attrValList.add(str);
            }
        }

        attrMap.put("attrId", attr1.getId());
        attrMap.put("attrName", attr1.getName());
        attrMap.put("attrValList", attrValList);

        //编辑商品时候使用
        if (null != productAttrList && productAttrList.size() > 0) {
            for (ProductAttr attr : productAttrList) {
                if (attr.getProductTypeAttrId().equals(attr1.getId())) {
                    attrMap.put("dbAttr", attr.getValue());
                }
            }
        }
    }
    
    /**
     * 组装货品信息
     *
     * @param productGoodStr
     * @param product
     */
    private void processGoods(String productGoodStr, Product product) throws BusinessException {
        if (!StringUtil.isEmpty(productGoodStr)) {
            String[] goods = productGoodStr.split(";");
            ArrayList<ProductGoods> goodList = new ArrayList<ProductGoods>();
            product.setGoodsList(goodList);
            if (null != goods && goods.length > 0 && goods.length == 1) {
                //一个货品.例如:67,12,21
                goodList.add(processProductGoods(product.getSellerId(), new ProductGoods(),
                    goods[0]));
            } else if (null != goods && goods.length > 0 && goods.length > 1) {
                //多个货品.例如:67,72,12,21;67,73,23,32......
                for (String str : goods) {
                    goodList
                        .add(processProductGoods(product.getSellerId(), new ProductGoods(), str));
                }
            }
        }
    }
    
    
    @SuppressWarnings("deprecation")
    private String buildImgPath(HttpServletRequest request) {
        String path = "upload";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        path = request.getRealPath(path);
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        return path;
    }
    
    private ProductGoods processProductGoods(Integer sellerId, ProductGoods productGoods, String str)
            throws BusinessException {
            String[] split = str.split(",");
            if (split.length == 9) {
            String normattr = split[0].trim();
            String[] norminfo = normattr.split("!@#");
            productGoods.setNormAttrId(norminfo[0]);//规格属性值ID
            productGoods.setNormName(norminfo[1] + "," + norminfo[2]);
            
            productGoods.setSku(split[1].trim());
            Integer stock = ConvertUtil.toInt(split[2].trim(), -1);
            if (-1 == stock) {
            throw new BusinessException("库存输入有误,请重新输入");
            }
                productGoods.setProductStock(stock);//库存
            try {
                BigDecimal bigDecimal = new BigDecimal(split[3].trim());
                productGoods.setMallPcPrice(bigDecimal);//PC价格
            } catch (Exception e) {
                throw new BusinessException("PC价格输入有误,请重新输入");
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(split[4].trim());
                productGoods.setMallMobilePrice(bigDecimal);//mobile价格
            } catch (Exception e) {
                throw new BusinessException("mobile价格输入有误,请重新输入");
            }
            try {
                productGoods.setBarCode(split[5].trim());//商品条码（EA）
            } catch (Exception e) {
                throw new BusinessException("商品条码（EA）输入有误,请重新输入");
            }
            try {
                productGoods.setBarCodePL(split[6].trim());//商品条码（PL）
            } catch (Exception e) {
                throw new BusinessException("商品条码（PL）输入有误,请重新输入");
            }
            try {
                productGoods.setBarCodeCS(split[7].trim());//商品条码（CS）
            } catch (Exception e) {
                throw new BusinessException("商品条码（CS）输入有误,请重新输入");
            }
            try {
                productGoods.setIsVirtualBom(Integer.parseInt(split[8].trim()));
            } catch (Exception e) {
            productGoods.setIsVirtualBom(0);
            }
            } else if (split.length == 7) {
                String normattr1 = split[0].trim();
                String[] norminfo1 = normattr1.split("!@#");
                String normattr2 = split[1].trim();
                String[] norminfo2 = normattr2.split("!@#");
                
                productGoods.setNormAttrId(norminfo1[0] + "," + norminfo2[0]);//规格属性值ID
                productGoods.setNormName(norminfo1[1] + "," + norminfo1[2] + ";" + norminfo2[1] + ","
                + norminfo2[2]);
            
                productGoods.setSku(split[2].trim());
                Integer stock = ConvertUtil.toInt(split[3].trim(), -1);
            if (-1 == stock) {
                throw new BusinessException("库存输入有误,请重新输入");
            }
                productGoods.setProductStock(stock);//库存
            try {
                BigDecimal bigDecimal = new BigDecimal(split[4].trim());
                productGoods.setMallPcPrice(bigDecimal);//PC价格
            } catch (Exception e) {
                throw new BusinessException("PC价格输入有误,请重新输入");
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(split[5].trim());
                productGoods.setMallMobilePrice(bigDecimal);//mobile价格
            } catch (Exception e) {
                throw new BusinessException("mobile价格输入有误,请重新输入");
            }
            try {
                productGoods.setBarCode(split[6].trim());//商品编码
            } catch (Exception e) {
                throw new BusinessException("商品编码输入有误,请重新输入");
            }
            }
            productGoods.setProductStockWarning(0);
            productGoods.setActualSales(0);
            return productGoods;
            }
}
