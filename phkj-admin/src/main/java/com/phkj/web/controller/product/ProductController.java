package com.phkj.web.controller.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ConvertUtil;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.HttpJsonResultForAjax;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.StringUtil;
import com.phkj.core.SyncWayUtil;
import com.phkj.core.TimeUtil;
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
import com.phkj.entity.system.SystemAdmin;
import com.phkj.entity.system.SystemLogsConstants;
import com.phkj.service.member.IMemberService;
import com.phkj.service.mindex.IMIndexService;
import com.phkj.service.product.IProductService;
import com.phkj.service.seller.ISellerService;
import com.phkj.service.system.ISystemLogsService;
import com.phkj.vo.product.ListProductPriceVO1;
import com.phkj.vo.product.ListProductPriceVO2;
import com.phkj.vo.product.ListProductPriceVO3;
import com.phkj.web.controller.BaseController;
import com.phkj.web.controller.CsrfTokenManager;
import com.phkj.web.util.UploadUtil;
import com.phkj.web.util.WebAdminSession;

import net.sf.json.JSONObject;
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

    @RequestMapping(value = "auditProduct", method = { RequestMethod.GET })
    public void auditProduct(HttpServletRequest request, HttpServletResponse response, Integer id, Integer type) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            if (id == null || id == 0)
                throw new BusinessException("请选择要操作的商品");
            if (type == null)
                throw new BusinessException("未知操作");
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            switch (type) {
                case 3:
                    /*
                     * 推送商品给oms
                     */
                	boolean flag = true;
                    try {
                    	if (SyncWayUtil.SYNC_WAY.equals(SyncWayUtil.SYNC_OMS)) {
                    		Product product =  productService.getProductById(id).getResult();
                    		if(product != null ){
                    			List<ProductGoods> goodsList = new ArrayList<>();
                    			for (ProductGoods goods : goodsList) {
                    				ServiceResult<String> strResult = productService.omsProductCreate(product,goods);
                    				//如果返回成功并且错误信息为空更新商品状态为审核通过
                    				if (strResult.getSuccess() && ("Success".equals(strResult.getResult().trim()) || "SKU商品已经存在".equals(strResult.getResult().trim()))) {
                    				} else {
                    					//依旧设置审核状态为申请驳回
                    					type = 4;
                    					flag = false;
                    					msg += "推送oms出现如下错误："+strResult.getResult();
                    					break;
                    				}
								}
                    		}else{
                    			flag = false;
                    			 msg += "推送oms出现如下错误：查询商品失败";
                    			 ILog.error("推送oms出现如下错误：查询商品失败");
                    		}
                    	}
        			} catch (Exception e) {
        				flag = false;
        				ILog.error(e.getMessage());
        				msg += "推送oms出现系统错误";
        			}
                    if (flag) {
                    	msg += "该商品将允许在商城上架";
                    	systemLogsService.saveSystemLogs(adminUser.getId(), adminUser.getName(), "", id, "product", SystemLogsConstants.AUDIT_SUCCESS, "system_admin");
                    } else {
                    	systemLogsService.saveSystemLogs(adminUser.getId(), adminUser.getName(), "", id, "product", SystemLogsConstants.OMS_CREATE_FAIL, "system_admin");
                    }
                    break;
                case 4:
                    msg += "该商品将不允许再次上架";
                    systemLogsService.saveSystemLogs(adminUser.getId(), adminUser.getName(), "", id, "product", SystemLogsConstants.AUDIT_RETURN, "system_admin");
                    break;
                case 5:
                    msg += "该商品将被冻结,并强制下架";
                    systemLogsService.saveSystemLogs(adminUser.getId(), adminUser.getName(), "", id, "product", SystemLogsConstants.FROZEN, "system_admin");
                    break;
                case 7:
                    msg += "该商品将强制下架";
                    systemLogsService.saveSystemLogs(adminUser.getId(), adminUser.getName(), "", id, "product", SystemLogsConstants.DOWN, "system_admin");
                    break;
                default:
                    msg = "操作失败,请稍后重试";
                    throw new BusinessException("操作失败,请稍后重试");
            }
            productService.updateProductState(id, type);
            
            pw = response.getWriter();
        } catch (Exception e) {
            log.error("[admin][ProductController] auditProduct exception", e);
            msg = e.getMessage();
        }
        pw.print(msg);
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
    

    @RequestMapping(value = "exportOnsalesProductPrice", method = { RequestMethod.GET })
    public void exportOnsalesProductPrice(HttpServletRequest request, HttpServletResponse response) {
    	String busiErrMsg = "";
    	ServiceResult<List<ListProductPriceVO1>> ordersResult1  = productService.listProductPrice1();
    	 if (!ordersResult1.getSuccess()) {
             busiErrMsg = ordersResult1.getMessage();
         }
     	ServiceResult<List<ListProductPriceVO2>> ordersResult2  = productService.listProductPrice2();
    	 if (!ordersResult2.getSuccess()) {
    		 busiErrMsg = ordersResult2.getMessage();
    	 }
     	ServiceResult<List<ListProductPriceVO3>> ordersResult3  = productService.listProductPrice3();
    	 if (!ordersResult3.getSuccess()) {
    		 busiErrMsg = ordersResult3.getMessage();
    	 }
    	 
    	 if (!StringUtil.isEmpty(busiErrMsg, true)) {
             try {
                 Cookie msgCookie = new Cookie("busiErrMsg", URLEncoder.encode(busiErrMsg, "utf-8"));
                 msgCookie.setPath("/");
                 response.addCookie(msgCookie);
                 Cookie fileDownloadCookie = new Cookie("fileDownload", "false");
                 fileDownloadCookie.setPath("/");
                 response.addCookie(fileDownloadCookie);
             } catch (UnsupportedEncodingException e) {
                 e.printStackTrace();
             }
    	 } else {
    		 List<ListProductPriceVO1> list1 = ordersResult1.getResult();
    		 List<ListProductPriceVO2> list2 = ordersResult2.getResult();
    		 List<ListProductPriceVO3> list3 = ordersResult3.getResult();
    		 OutputStream os=null;  
    		 try {
    		    	String fileName = "在售商品价格" + "_" + TimeUtil.getNoFormatToday()+ ".xls";
    		    	response.reset();// 清空response     
    		        response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes(), "ISO8859_1"));
//    		        response.setContentType("application/octet-stream");
    		        response.setContentType("application/vnd.ms-excel");
    			 os = new BufferedOutputStream(response.getOutputStream());//输出流  
    		     HSSFWorkbook workbook = new HSSFWorkbook();//创建excel
    		     HSSFSheet sheet1 = workbook.createSheet("一口价");//创建一个工作薄 
    		     HSSFSheet sheet2 = workbook.createSheet("整箱价");//创建一个工作薄 
    		     HSSFSheet sheet3 = workbook.createSheet("阶梯价");//创建一个工作薄 
    		     setSheetColumn(sheet1);//设置工作薄列宽
    		     setSheetColumn(sheet2);//设置工作薄列宽
    		     setSheetColumn(sheet3);//设置工作薄列宽
    		     String title1[] = {"id", "货主", "spu", "价格"};
    		     String title2[] = {"id", "货主", "spu", "装箱数", "整箱价", "正常价"};
    		     String title3[] = {"id", "货主", "spu", "阶梯1", "价格1", "阶梯2", "价格2", "阶梯3", "价格3"};
    		     HSSFCellStyle titleCellStyle = createTitleCellStyle(workbook);  
    		     this.makeCell(workbook, sheet1, titleCellStyle, list1, title1, "1");
    		     this.makeCell(workbook, sheet2, titleCellStyle, list2, title2, "2");
    		     this.makeCell(workbook, sheet3, titleCellStyle, list3, title3, "3");
    		     workbook.write(os);  
    		     os.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {  
		             os.close();  
		             response.setHeader("Connetion", "close");
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        } 
			}
    	 }
    }
    
    public static void makeCell (HSSFWorkbook workbook,HSSFSheet sheet,HSSFCellStyle titleCellStyle,List list,String[] title,String type) {
    	int bodyRowCount=1;//正文内容行号  
        int currentRowCount=1;//当前的行号
    	HSSFRow row = null;//创建一行  
        HSSFCell cell = null;//每个单元格
        //写入标题
        writeTitleContent(sheet,titleCellStyle,title);
        HSSFCellStyle bodyCellStyle = createBodyCellStyle(workbook);   
        if ("1".equals(type)) {
        	exportDataPriceVO1(list, sheet, row, cell, bodyCellStyle, bodyRowCount, currentRowCount) ;
        } else  if ("2".equals(type)) {
        	exportDataPriceVO2(list, sheet, row, cell, bodyCellStyle, bodyRowCount, currentRowCount) ;
        } else  if ("3".equals(type)) {
        	exportDataPriceVO3(list, sheet, row, cell, bodyCellStyle, bodyRowCount, currentRowCount) ;
        }
    }
    
    /**
     * 
     * @param list
     * @param sheet
     * @param row
     * @param cell
     * @param bodyCellStyle
     * @param bodyRowCount
     * @param currentRowCount
     */
    private static void exportDataPriceVO1(List<ListProductPriceVO1> list, HSSFSheet sheet, HSSFRow row, HSSFCell cell, 
			HSSFCellStyle bodyCellStyle, int bodyRowCount, int currentRowCount) {
        //第二行开始写入数据
        for (int i = 0 ; i < list.size() ; i++) { 
        	//正文内容  
            row = sheet.createRow(bodyRowCount);          
            //第二行写开始写入正文内容  
            cell = row.createCell((short)0);  
            cell.setCellStyle(bodyCellStyle);  
            cell.setCellValue(currentRowCount);//序号 
        	ListProductPriceVO1 listProductPriceVO1 = (ListProductPriceVO1)list.get(i);
        	
        	cell = row.createCell((short)1);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO1.getId());
        	
        	cell = row.createCell((short)2);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO1.getSellerName());
        	
        	cell = row.createCell((short)3);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO1.getProductCode());
        	
        	cell = row.createCell((short)4);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO1.getPrice());
        	
            bodyRowCount++;//正文内容行号递增1  
            currentRowCount++;//当前行号递增1
        }
    }
    
    /**
     * 
     * @param list
     * @param sheet
     * @param row
     * @param cell
     * @param bodyCellStyle
     * @param bodyRowCount
     * @param currentRowCount
     */
    private static void exportDataPriceVO2(List<ListProductPriceVO2> list, HSSFSheet sheet, HSSFRow row, HSSFCell cell, 
			HSSFCellStyle bodyCellStyle, int bodyRowCount, int currentRowCount) {
        //第二行开始写入数据
        for (int i = 0 ; i < list.size() ; i++) { 
        	//正文内容  
            row = sheet.createRow(bodyRowCount);          
            //第二行写开始写入正文内容  
            cell = row.createCell((short)0);  
            cell.setCellStyle(bodyCellStyle);  
            cell.setCellValue(currentRowCount);//序号 

        	ListProductPriceVO2 listProductPriceVO2 = (ListProductPriceVO2)list.get(i);
        	
        	cell = row.createCell((short)1);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getId());
        	
        	cell = row.createCell((short)2);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getSellerName());
        	
        	cell = row.createCell((short)3);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getProductCode());
        	
        	cell = row.createCell((short)4);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getNumber());
        	
        	cell = row.createCell((short)5);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getPrice1());
        	
        	cell = row.createCell((short)6);  
        	cell.setCellStyle(bodyCellStyle);  
        	cell.setCellValue(listProductPriceVO2.getPrice2());
        	
            bodyRowCount++;//正文内容行号递增1  
            currentRowCount++;//当前行号递增1
        }
    }
    
    /**
     * 
     * @param list
     * @param sheet
     * @param row
     * @param cell
     * @param bodyCellStyle
     * @param bodyRowCount
     * @param currentRowCount
     */
    private static void exportDataPriceVO3(List<ListProductPriceVO3> list, HSSFSheet sheet, HSSFRow row, HSSFCell cell, 
    														HSSFCellStyle bodyCellStyle, int bodyRowCount, int currentRowCount) {
        //第二行开始写入数据
        for (int i = 0 ; i < list.size() ; i++) { 
        	//正文内容  
            row = sheet.createRow(bodyRowCount);          
            //第二行写开始写入正文内容  
            cell = row.createCell((short)0);  
            cell.setCellStyle(bodyCellStyle);  
            cell.setCellValue(currentRowCount);//序号 
	    	ListProductPriceVO3 listProductPriceVO3 = (ListProductPriceVO3)list.get(i);
	    	
	    	cell = row.createCell((short)1);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getId());
	    	
	    	cell = row.createCell((short)2);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getSellerName());
	    	
	    	cell = row.createCell((short)3);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getProductCode());
	    	
	    	cell = row.createCell((short)4);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getState1());
	    	
	    	cell = row.createCell((short)5);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getPrice1());
	    	
	    	cell = row.createCell((short)6);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getState2());
	    	
	    	cell = row.createCell((short)7);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getPrice2());
	    	
	    	cell = row.createCell((short)8);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getState3());
	    	
	    	cell = row.createCell((short)9);  
	    	cell.setCellStyle(bodyCellStyle);  
	    	cell.setCellValue(listProductPriceVO3.getPrice3());
            bodyRowCount++;//正文内容行号递增1  
            currentRowCount++;//当前行号递增1
        }
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
    
    /**
     * 更新商品
     *
     * @param product
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(Product product, HttpServletRequest request) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true, CsrfTokenManager.createTokenForSession(request.getSession()));
        
        SystemAdmin admin = WebAdminSession.getAdminUser(request);
        if (null == admin) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login.html");
            return jsonResult;
        }
        Seller seller = new Seller();
        Integer sellerId = 1;
        if(product.getSellerId() != null && !"".equals(product.getSellerId()) && product.getSellerId() != 0){
            sellerId = product.getSellerId();
        }else{
            Product oldProduct = productService.getProductById(product.getId()).getResult();
            sellerId = oldProduct.getSellerId();
        }
        
        seller = sellerService.getSellerById(sellerId).getResult();
        ServiceResult<Boolean> serviceResult = createOrUpdateProduct(product, request, seller, "U", admin);
        systemLogsService.saveSystemLogs(admin.getId(), admin.getName(), JSONObject.fromObject(product).toString(), product.getId(), "product", SystemLogsConstants.UPDATE, "seller_user");
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    /**
     * 保存或者更新商品
     *
     * @param product
     * @param request
     * @param seller
     * @param type    C:保存 U:更新
     * @return
     */
    private ServiceResult<Boolean> createOrUpdateProduct(Product product,
                                                         HttpServletRequest request, Seller seller,
                                                         String type, SystemAdmin admin) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            if (seller.getId() == 1) {
                product.setIsSelf(Product.IsSelfEnum.SELF.getValue());//自营
                product.setVirtualSales(Integer.valueOf(request.getParameter("virtualSales")));
            } else {
                product.setIsSelf(Product.IsSelfEnum.SELLER.getValue());//商家
            }
            Integer productBrandId = ConvertUtil.toInt(request.getParameter("productBrandId"), 0);
            if (0 == productBrandId) {
                //请选择品牌
                //return;
            }
            Integer sellerCateId = ConvertUtil.toInt(request.getParameter("sellerCateId"), 0);
            if (0 == sellerCateId) {
                //请选择商家分类
                //return;
            }
            String isNormStr = request.getParameter("isNorm");
            if (isNormStr == null) {
                // 编辑商品时表单为disabled，取不到值
                isNormStr = request.getParameter("isNormHidden");
            }
            product.setSellerId(seller.getId());
            Integer isNorm = ConvertUtil.toInt(isNormStr, 1);
            if (2 == isNorm) {
                processGoods(request.getParameter("productGoods"), product);
            } else {
                product.setSku(request.getParameter("sku"));
            }
            product.setProductUrl(request.getParameter("productUrl"));
            String inStockWarning = request.getParameter("inStockWarning");
            int sw = 0;
            if(inStockWarning!=null && !"".equals(inStockWarning)){
                sw = Integer.valueOf(inStockWarning);
                product.setInStockWarning(sw);
            }
            product.setMalMobilePrice(product.getMallPcPrice());
            product.setProductBrandId(productBrandId);
            product.setKeyword(request.getParameter("keyword1"));
            Integer auditStatus = seller.getAuditStatus();
            if (auditStatus.intValue() == Seller.AUDIT_STATE_2_DONE) {
                auditStatus = Product.SELLER_STATE_1;
            } else {
                auditStatus = Product.SELLER_STATE_2;
            }
            String productStock1 =  request.getParameter("productStockHidden");
            if(productStock1!=null && !"".equals(productStock1)){
                product.setProductStock(Integer.valueOf(productStock1));
            }
            product.setSellerState(auditStatus);// 店铺状态
            product.setCommentsNumber(ConvertUtil.toInt(request.getParameter("commentsNumber"), 0));
            product.setSellerCateId(sellerCateId);
            product.setVirtualSales(ConvertUtil.toInt(request.getParameter("virtualSales"), 0));
            product.setActualSales(ConvertUtil.toInt(request.getParameter("actualSales"), 0));
            product.setCreateId(seller.getId());
            product.setKeyword(request.getParameter("keyword1"));
            BigDecimal percentageScale = ConvertUtil.toDecimal(
                request.getParameter("percentageScale"), new BigDecimal(0.00));
            product.setPercentageScale(percentageScale);
            product.setProductCateState(1);//分类正常
            product.setIsTop(1);//不推荐
            String isTop = request.getParameter("is_top") == null ?"1":request.getParameter("is_top");
            if(!"".equals(isTop)&&!"1".equals(isTop)){
                product.setIsTop(Integer.valueOf(isTop));
            }
            if (!StringUtil.isEmpty(product.getDescription())) {
                String description = product.getDescription();
                description = description.replaceAll(System.getProperty("line.separator"), "");
                product.setDescription(description);
            }
            // 是否是虚拟商品
            product.setIsInventedProduct(ConvertUtil.toInt(
                request.getParameter("isInventedProduct"), 1));

            List<ProductPicture> picList = new ArrayList<ProductPicture>();
            List<ProductAttr> attrList = new ArrayList<ProductAttr>();
            //product picture
            String pics = request.getParameter("imageSrc");
            if (!StringUtil.isEmpty(pics)) {
                String[] split = pics.split(";");
                for (int i = 0; i < split.length; i++) {
                    ProductPicture picture = new ProductPicture();
                    String img = split[i];
                    img = img.replace(DomainUrlUtil.getEJS_IMAGE_RESOURCES(), "");
                    picture.setImagePath(img);
                    picture.setSort(i);
                    picture.setCreateId(seller.getId());
                    picture.setState(1);
                    picture.setSellerId(seller.getId());
                    if (i == 0) {
                        picture.setProductLead(1);
                        product.setMasterImg(img);
                    } else {
                        picture.setProductLead(2);
                    }
                    picList.add(picture);
                }
            }
            //商品查询属性
            String queryType = request.getParameter("queryType");
            if (!StringUtil.isEmpty(queryType)) {
                String[] split = queryType.split(";");//productTypeAttrId,name,value
                for (String str : split) {
                    String[] split1 = str.split(",");
                    if (split1.length != 3)
                        continue;
                    ProductAttr productAttr = new ProductAttr();
                    productAttr.setProductTypeAttrId(Integer.valueOf(split1[0]));
                    productAttr.setName(split1[1]);
                    if ("-1".equals(split1[2])) {
                        productAttr.setValue("");
                    } else {
                        productAttr.setValue(split1[2]);
                    }
                    productAttr.setState(1);//检索属性
                    attrList.add(productAttr);
                }
            }
            //商品自定义属性
            String custAttr = request.getParameter("custAttr");
            if (!StringUtil.isEmpty(custAttr)) {
                String[] split = custAttr.split(";");//productTypeAttrId,name,value
                for (String str : split) {
                    String[] split1 = str.split(",");
                    ProductAttr productAttr = new ProductAttr();
                    productAttr.setProductTypeAttrId(Integer.valueOf(split1[0]));
                    productAttr.setName(split1[1]);
                    if (split1.length == 2) {
                        if(split1[1].equals("克重（净重）")){
                            productAttr.setValue("45.00");
                        }else{
                            productAttr.setValue("");
                        }
                    } else {
                        productAttr.setValue(split1[2]);
                    }
                    if(split1[1].equals("包装规格")&&"-1".equals(split1[2])){
                        productAttr.setValue("10双");
                    }
                    productAttr.setState(3);//商品自定义属性
                    attrList.add(productAttr);
                }
            }

            String sku_others = request.getParameter("sku_other");
            if ("C".equals(type)) {
                //新建商品默认价格是一口价
                product.setPriceStatus(1);
                int state = ConvertUtil.toInt(request.getParameter("state"),
                    Product.StateEnum.CREATE.getValue());
                product.setState(state);
                product.setSkuOther(sku_others);
                result = productService.saveProduct(product, picList, attrList);
            }
            if ("U".equals(type)) {
                product.setSkuOther(sku_others);
                //审核通过后重置商品状态
                Product p = productService.getProductById(product.getId()).getResult();
                int state = p.getState();
                if(state == 3){
                    state = 2;
                }
                product.setState(state);
                result = productService.updateProduct(product, picList, attrList);
                //更新货主时需同步更新product_nnorm_attr_opt表数据
            }

            //保存sku图片信息
            if (isNorm == 2) {
                if (result.getSuccess() == true){
                    //更新货主时需同步更新product_nnorm_attr_opt表数据
                    processSKUPic(request, product);
                }
            }
        } catch (BusinessException e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作商品发生错误,请联系管理员");
            result.setSuccess(false);
        }
        return result;
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
    
    private void processSKUPic(HttpServletRequest request, Product product) throws Exception {
        String skupics = request.getParameter("skupics");
        if (isNull(skupics)) {
            return;
        }

        Map<String, String> queryMap = new HashMap<String, String>();
//        queryMap.put("sellerId", product.getSellerId() + "");
        queryMap.put("flag", "2");
        queryMap.put("productId", product.getId() + "");
        ServiceResult<List<ProductNormAttrOpt>> optservice = new ServiceResult<>();
        List<ProductNormAttrOpt> optlist = optservice.getResult();

        if (skupics.length() < 1) {
            throw new BusinessException("sku规格错误");
        }
        for (String info : skupics.split(",")) {
            //normid_@_normname_@_attrid_@_name_@_url
            String[] optarr = info.split("_@_");
            String normid = optarr[0];
            String normname = optarr[1];
            String id = optarr[2];
            String name = optarr[3];
            String url = optarr[4];
            String colortype = optarr[5];

            if (optlist.size() > 0) {
                //编辑
                for (ProductNormAttrOpt opt1 : optlist) {
                    if (opt1.getAttrId().intValue() == Integer.valueOf(id).intValue()) {
                        opt1.setImage(url);
                        opt1.setSellerId(product.getSellerId());
                    }
                }
            } else {
                //新增
//                ProductNormAttrOpt opt = new ProductNormAttrOpt();
//                Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();
//                opt.setSellerId(sellerId);
//                opt.setCreateId(sellerId);
//                opt.setTypeAttr(!isNull(colortype) && "custom".equals(colortype) ? 2 : 1);
//                opt.setCreateTime(new Date());
//                opt.setType(2);//图片
//                opt.setAttrId(Integer.valueOf(id));//属性值
//
//                opt.setProductNormId(Integer.valueOf(normid));
//                opt.setProductNormName(normname);
//                opt.setImage(url);
//                opt.setName(name);
//                opt.setProductId(product.getId());
//                opt.setFlag(2);//已创建
//                productNormAttrOptService.saveProductNormAttrOpt(opt);
            }
            product.getGoodsList();
            //货品图片
            for (ProductGoods pg : product.getGoodsList()) {
                if (Integer.valueOf(pg.getNormAttrId()).intValue() == Integer.valueOf(id)
                    .intValue()) {
                    pg.setImages(url);
                }
            }

        }
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
    
    /**
     * ajax获取二级、三级分类
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "getCateById", method = { RequestMethod.GET })
    @ResponseBody
    public Object getCateById(HttpServletRequest request, @RequestParam("id") Integer id) {
        HttpJsonResult<List<ProductCate>> jsonResult = new HttpJsonResult<List<ProductCate>>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {

        }
        //所有商家初始化列表一致user.getSellerId()
        ServiceResult<List<ProductCate>> serviceResult = new ServiceResult<>();
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            jsonResult.setRows(serviceResult.getResult());
        }

        return jsonResult;
    }

    /**
     * ajax商品图片上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "uploadFiles", method = { RequestMethod.POST })
    public @ResponseBody Object uploadImage(MultipartHttpServletRequest request,
                                            HttpServletResponse response, String fileIndex) {
        log.info("UploadImageController uploadImage start");
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
            if (map != null) {
                Iterator<String> iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String str = (String) iter.next();
                    List<MultipartFile> fileList = map.get(str);
                    for (MultipartFile mpf : fileList) {
                        String originalFilename = mpf.getOriginalFilename();
                        File tmpFile = new File(buildImgPath(request)
                                                + "/"
                                                + UUID.randomUUID()
                                                + originalFilename.substring(
                                                    originalFilename.lastIndexOf("."),
                                                    originalFilename.length()));
                        if (!mpf.isEmpty()) {
                            byte[] bytes = mpf.getBytes();
                            BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(tmpFile));
                            stream.write(bytes);
                            stream.close();
                        }

                        String url = UploadUtil.getInstance().productUploaderImage(tmpFile, request);

                        tmpFile.deleteOnExit();

                        //规范路径,以避免浏览器兼容问题
                        url = url.replaceAll("\\\\", "/");
                        result.put("url", DomainUrlUtil.getEJS_IMAGE_RESOURCES() + url);
                        result.put("fileIndex", fileIndex);
                        jsonResult.setData(result);

                        log.debug("url==================" + url);
                        log.debug("fileIndex==================" + fileIndex);

                        return jsonResult;
                    }
                }
            }
        } catch (Exception e) {
            log.error("[shoppingmall-memer-web][UploadImageController][uploadImage] exception:", e);
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        return null;
    }
    
    /**
     * SKU图片上传
     * @param request
     * @param response
     * @param attrid 属性id
     * @param colortype 颜色类型 custom-自定义
     * @param uploadtype 上传类型 1-创建 2-更新
     * @param productId 商品id（仅更新时有效）
     * @return
     */
    @RequestMapping(value = "uploadSKUImage", method = { RequestMethod.POST })
    public @ResponseBody Object uploadSKUImage(MultipartHttpServletRequest request,
                                               HttpServletResponse response, Integer attrid,
                                               String colortype, Integer uploadtype,
                                               Integer productId) {
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
        try {
            if (map != null) {
                Iterator<String> iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String str = (String) iter.next();
                    List<MultipartFile> fileList = map.get(str);
                    for (MultipartFile mpf : fileList) {
                        String originalFilename = mpf.getOriginalFilename();
                        File tmpFile = new File(buildImgPath(request)
                                                + "/"
                                                + UUID.randomUUID()
                                                + originalFilename.substring(
                                                    originalFilename.lastIndexOf("."),
                                                    originalFilename.length()));
                        String url = null;
                        try {
                            if (!mpf.isEmpty()) {
                                byte[] bytes = mpf.getBytes();
                                BufferedOutputStream stream = new BufferedOutputStream(
                                    new FileOutputStream(tmpFile));
                                stream.write(bytes);
                                stream.close();
                            }

                            url = UploadUtil.getInstance().productUploaderImage(tmpFile, request);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        tmpFile.deleteOnExit();
                        //删除目录
                        UploadUtil.deleteDir(tmpFile.getParentFile().getParentFile());

                        //返回值
                        result.put("attrid", attrid);
                        result.put("colortype", colortype);
                        jsonResult.setData(result);

                        //上传失败
                        if (isNull(url))
                            throw new BusinessException("上传失败");
                        //规范路径,以避免浏览器兼容问题
                        url = url.replaceAll("\\\\", "/");
                        result.put("url", url);

                    }
                }
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
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
