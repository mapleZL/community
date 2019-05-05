package com.ejavashop.web.controller.product;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IOrderReceiptDetailService;
import com.ejavashop.service.product.IOrderReceiptService;
import com.ejavashop.service.product.IProductAttrService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

@Controller
@RequestMapping(value="admin/product")
public class OrderReceiptController extends BaseController{
    
    @Resource
    private IProductAttrService         productAttrService;
    @Resource
    private IProductTypeAttrService     sellerProductTypeAttrService;
    @Resource
    private IOrderReceiptService        orderReceiptService;
    @Resource
    private IOrderReceiptDetailService  orderReceiptDetailService;
    @Resource
    private IProductService             productService;
    @Resource
    private IProductGoodsService        productGoodsService;
    @Resource
    private ISellerService              sellerService;
    
    
    @RequestMapping(value = "create")
    public String add(HttpServletRequest request,Map<String, Object> dataMap){
        Map<String, String> sellerMap = new HashMap<String, String>();
        sellerMap.put("q_auditStatus", "2");
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
        dataMap.put("sellers", sellers.getResult());
        return "seller/product/probaby/orderreceipt";
    }

    
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductGoods>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
//        System.out.println("list表格开始查询");
        //product表查询
        String productId = request.getParameter("productId");
//        ServiceResult<List<Product>>  serviceResult = productService.getProductsByKeyWord(name1,product_code,seller_id);
        ServiceResult<Product>  serviceResult = productService.getProductById(Integer.parseInt(productId));
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Integer product_id = serviceResult.getResult().getId();
        ServiceResult<List<ProductGoods>> serviceResult_goods = productGoodsService.getGoodSByProductId(product_id);
        HttpJsonResult<List<ProductGoods>> jsonResult = new HttpJsonResult<List<ProductGoods>>();
        jsonResult.setRows(serviceResult_goods.getResult());
        jsonResult.setTotal(serviceResult_goods.getResult().size());
        return jsonResult;
    }
    
    
    @RequestMapping(value = "save", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductCate cate, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
//        SystemAdmin systemAdmin = WebAdminSession.getAdminUser(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        System.out.println("create");
        Map<String,String[]> getMap = request.getParameterMap();
        String[] q_sku = getMap.get("q_sku");
        String[] prop_stock = getMap.get("prop_stock");
        String seller_id = request.getParameter("seller_id");
        String date_probaby = request.getParameter("date_probaby").substring(0, 13);
        OrderReceipt orderReceipt = new OrderReceipt();
        OrderReceiptDetail orderReceiptDetail = new OrderReceiptDetail();
        String order_nums = this.getProductCode();
        String sku = "";
        int sum = 0;
        
        for(int i=0;i<q_sku.length;i++){
            if(i==0){
                sku = q_sku[i].substring(0,9);
            }
            if(q_sku[i].contains(sku)){
                sum+=Integer.valueOf(prop_stock[i]);    
            }else{
                sku = q_sku[i].substring(0,9);
                orderReceipt.setTotalAmount(sum);
                orderReceipt.setOrdersId(order_nums);
                orderReceiptService.saveOrderReceipt(orderReceipt);
                order_nums = this.getProductCode();
                sku = q_sku[i].substring(0,9);
                sum = Integer.valueOf(prop_stock[i]);
            }
            orderReceiptDetail.setNumberProbaby(Integer.valueOf(prop_stock[i]));
            orderReceiptDetail.setOrdersId(order_nums);
            orderReceiptDetail.setSKU(q_sku[i]);
            orderReceiptDetailService.saveOrderReceiptDetail(orderReceiptDetail);
            
            orderReceipt.setTotalAmount(sum);
            orderReceipt.setDateProbaby(date_probaby);
            orderReceipt.setSystemId(sellerUser.getRoleId());
            orderReceipt.setSellerId(Integer.valueOf(seller_id));
            orderReceipt.setSPU(sku);
            orderReceipt.setStatus(0);
            
            //为最后一次更新总表
            if(i==q_sku.length-1){
                orderReceipt.setOrdersId(order_nums);
                orderReceipt.setSystemId(sellerUser.getRoleId());
                orderReceiptService.saveOrderReceipt(orderReceipt);
            }
        }
        jsonResult.setMessage("保存成功！");
        return jsonResult;
    }
    
    /**
     * 按特定格式生成预约入库单号共计14位
     * 生成规则 年月日+6位随机数（例如20160622000000）
     * @return
     */
    public String getProductCode(){
        Date dt=new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String num = sdf.format(dt);
        Random random = new Random();
        int a = 0;
        for(int i=0;i<6;i++){
            a = random.nextInt(10);
            num+=a;
        }
        return num;
    }
    
}
