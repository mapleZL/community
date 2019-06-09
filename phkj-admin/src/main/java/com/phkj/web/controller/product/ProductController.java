package com.phkj.web.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.StringUtil;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.freemarkerutil.DomainUrlUtil;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.product.ProductPicture;
import com.phkj.entity.product.StAppletProduct;
import com.phkj.entity.system.Code;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.product.IProductPictureService;
import com.phkj.service.product.IStAppletProductService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * 
 *                       
 * @Filename: ProductController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {

    @Autowired
    private IStAppletProductService productService;
    @Autowired
    private RedisComponent          redisComponent;
    @Autowired
    private IProductPictureService  productPictureService;

    /**
     * 登录用户角色
     */
    private static final String     USER_TYPE_1 = "seller";

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        List<Code> codeList = new ArrayList<>();
        // 商品分类
        String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
        if (StringUtils.isNotBlank(jsonString)) {
            Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
            codeList = codeMap.get("PRODUCT_CATEGORY");
        }
        dataMap.put("productCategory", codeList);
        return "admin/product/pdt/productadd";
    }

    /**
     * 待售商品
     * @param dataMap
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/waitSale", method = { RequestMethod.GET })
    public String getWaitSale(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state1", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        // 商品分类
        String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
        List<Code> codeList = new ArrayList<>();
        if (StringUtils.isNotBlank(jsonString)) {
            Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
            codeList = codeMap.get("PRODUCT_CATEGORY");
        }
        dataMap.put("productCategory", codeList);
        return "admin/product/pdt/listwaitsale";
    }

    /**
     * 在售商品
     * @param dataMap
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/onSale", method = { RequestMethod.GET })
    public String getOnSale(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_state", "6");//6、上架；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        // 商品分类
        String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
        List<Code> codeList = new ArrayList<>();
        if (StringUtils.isNotBlank(jsonString)) {
            Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
            codeList = codeMap.get("PRODUCT_CATEGORY");
        }
        dataMap.put("productCategory", codeList);
        return "admin/product/pdt/listonsale";
    }

    //已经删除商品
    @RequestMapping(value = "delSale", method = { RequestMethod.GET })
    public String delSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_state", "5");//5、商品删除；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/pdt/listdelsale";
    }

    //待审核商品列表
    @RequestMapping(value = "examine", method = { RequestMethod.GET })
    public String examineSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_state", "2");//5、待审核；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/pdt/listexamine";
    }

    /**
     * 保存商品
     *
     * @param product
     * @param request
     * @param dataMap
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(StAppletProduct product, HttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login.html");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = createOrUpdateProduct(product, request, user);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    // 构造需要保存的商品
    private ServiceResult<Boolean> createOrUpdateProduct(StAppletProduct product,
                                                         HttpServletRequest request,
                                                         SystemAdmin user) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        if (product.getId() == null || product.getId() == 0) {
            product.setSellerId(user.getId());
            product.setCreateId(user.getId());
            product.setCreateTime(new Date());
            // 商品和小区唯一绑定
            product.setVillageCode(user.getVillageCode());
            product.setIsSelf(StAppletProduct.IsSelfEnum.SELF.getValue());//自营
            product.setProductCateState(1);//分类正常
            product.setIsTop(1);//不推荐
            product.setActualSales(0);
            product.setMalMobilePrice(product.getMallPcPrice());
        }
        Integer state = product.getState();
        if (state == 3) {
            state = 2;
        }
        // 审核通过后修改状态重置
        product.setState(state);
        String pics = request.getParameter("imageSrc");
        List<ProductPicture> picList = new ArrayList<ProductPicture>();
        if (!StringUtil.isEmpty(pics)) {
            String[] split = pics.split(";");
            for (int i = 0; i < split.length; i++) {
                String img = split[i];
                ProductPicture picture = new ProductPicture();
                picture.setImagePath(img);
                picture.setSort(i);
                picture.setCreateId(user.getId());
                picture.setState(1);
                picture.setSellerId(user.getId());
                if (i == 0) {
                    picture.setProductLead(1);
                    product.setMasterImg(img);
                } else {
                    picture.setProductLead(2);
                }
                picList.add(picture);
            }
        }
        result = productService.updateOrCreate(product, picList);
        return result;
    }

    /**
     * 编辑商品
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request,
                       @RequestParam(value = "id", required = true) Integer id,
                       Map<String, Object> dataMap) {
        String rtnPath = "admin/product/pdt/productedit";
        /**1.判断是否登录*/
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            return DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login.html";
        }

        /**查询商品信息**/
        ServiceResult<StAppletProduct> productServiceResult = productService
            .getStAppletProductById(id);
        if (!productServiceResult.getSuccess() || null == productServiceResult.getResult()) {
            dataMap.put("message", "编辑商品失败，商品不存在");
            return rtnPath;//
        }

        List<Code> codeList = new ArrayList<>();
        // 商品分类
        String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
        if (StringUtils.isNotBlank(jsonString)) {

            Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
            codeList = codeMap.get("PRODUCT_CATEGORY");
        }
        dataMap.put("productCategory", codeList);
        StAppletProduct product = productServiceResult.getResult();
        dataMap.put("product", product);

        // 商品图片
        ServiceResult<List<ProductPicture>> pictures = productPictureService
            .getProductPictureByProductId(product.getId());
        dataMap.put("pic", pictures.getResult());
        return rtnPath;
    }

    // 提交审核和下架操作
    @ResponseBody
    @RequestMapping(value = "changeStatus", method = { RequestMethod.GET })
    public HttpJsonResult<Boolean> changeStatus(Integer id, Integer state,
                                                HttpServletRequest request,
                                                Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        StAppletProduct stAppletProduct = new StAppletProduct();
        stAppletProduct.setId(id);
        stAppletProduct.setState(state);
        ServiceResult<Integer> serviceResult = productService.update(stAppletProduct);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        String msg = "";
        if (state == 2) {
            msg = "提交审核成功";
        } else if (state == 6) {
            msg = "商品上架成功";
        } else if (state == 1) {
            msg = "恢复商品成功";
        }
        jsonResult.setMessage(msg);
        jsonResult.setData(true);
        return jsonResult;
    }

    /**
     * 删除商品
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productService.delProduct(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 提交审核，上下架操作
     *
     * @param request
     * @param response
     * @param ids
     */
    @RequestMapping(value = "handler", method = { RequestMethod.POST })
    public void handler(HttpServletRequest request, HttpServletResponse response, String ids,
                        Integer type) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("state", StAppletProduct.STATE_2);
            if (type == StAppletProduct.STATE_6) {
                map.put("state", StAppletProduct.STATE_6);
                msg = "上架成功";
            } else if (type == StAppletProduct.STATE_7) {
                map.put("state", StAppletProduct.STATE_7);
                msg = "下架成功";
            } else if (type == StAppletProduct.STATE_2) {
                map.put("state", StAppletProduct.STATE_2);
                msg = "提交审核成功";
            } else if (type == StAppletProduct.STATE_3) {
                map.put("state", StAppletProduct.STATE_3);
                msg = "审核通过成功";
            } else if (type == StAppletProduct.STATE_4) {
                map.put("state", StAppletProduct.STATE_4);
                msg = "驳回成功";
            } else
                throw new BusinessException("未知操作");
            productService.updateByIds(map, StringUtil.string2IntegerList(ids));
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 校验spu重复性
     * @param request
     * @param spu
     * @return
     */
    @RequestMapping(value = "validate", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Object> validate(HttpServletRequest request,
                                                         @RequestParam("spu") String spu) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        jsonResult.setMessage("");
        Map<String, String> queryMap = new HashMap<String, String>();
        Integer sellerId = WebAdminSession.getAdminUser(request).getId();
        queryMap.put("sellerId", sellerId.toString());
        if (!"".equals(spu)) {//spu校验
            queryMap.put("spu", spu);
            ServiceResult<Boolean> serviceResult = productService
                .checkProductBySPUAndSellerId(queryMap);
            if (serviceResult.getResult()) {
                jsonResult.setMessage("该商品对应SPU已存在，请重新输入！");
                return jsonResult;
            }
        }
        return jsonResult;
    }

    /**
     * 后台商品列表通用查询接口
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletProduct>> list(HttpServletRequest request,
                                                                    Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        String state = request.getParameter("q_state1");
        if (!StringUtil.isEmpty(state)) {
            queryMap.put("q_state", state);
        }
        String product_code = request.getParameter("q_product_code");
        if (!StringUtil.isEmpty(product_code)) {
            queryMap.put("q_productCode", product_code);
        }
        String name = request.getParameter("q_name");
        if (!StringUtil.isEmpty(name)) {
            queryMap.put("q_name1", name);
        }
        String cate_id = request.getParameter("q_cateId");
        if (!StringUtil.isEmpty(cate_id)) {
            queryMap.put("q_productCateId", cate_id);
        }
        String name2 = request.getParameter("q_name2");
        if (!StringUtil.isEmpty(name2)) {
            if (name2.equals("1")) {
                queryMap.put("q_name2", "促销中");
            } else {
                queryMap.put("q_name2", "未促销");
            }

        }
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String userType = request.getParameter("q_userType");
        // 登陆者为商户的时候才加上商品查询条件
        if (USER_TYPE_1.equals(userType)) {
            queryMap.put("q_sellerId", WebAdminSession.getAdminUser(request).getId() + "");
        }

        ServiceResult<List<StAppletProduct>> serviceResult = productService.pageProduct(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletProduct>> jsonResult = new HttpJsonResult<List<StAppletProduct>>();
        jsonResult.setRows((List<StAppletProduct>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 微信端查询楼层数据
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/floor", method = RequestMethod.GET)
    @ResponseBody
    HttpJsonResult<List<Code>> floorList(HttpServletRequest request, HttpServletResponse response) {
        HttpJsonResult<List<Code>> result = new HttpJsonResult<>();
        try {
            // 商品分类
            String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
            List<Code> codeList = new ArrayList<>();
            if (StringUtils.isNotBlank(jsonString)) {
                Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
                codeList = codeMap.get("PRODUCT_CATEGORY");
            }
            result.setData(codeList);
        } catch (Exception e) {
            result.setMessage("楼层数据查询失败");
        }
        return result;
    }

    /**
     * 前端查询商品列表
     * @param start
     * @param pageSize
     * @param productCateId
     * @param villageCode
     * @return
     */
    @RequestMapping(value = "/pageList", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletProduct>> list(Integer start, Integer pageSize,
                                                                    Integer productCateId,
                                                                    String villageCode) {
        HttpJsonResult<List<StAppletProduct>> result = new HttpJsonResult<>();
        try {
            List<StAppletProduct> list = productService.list(start, pageSize, productCateId, villageCode);
            result.setData(list);
            Integer count = productService.count(productCateId, villageCode);
            result.setTotal(count);
        } catch (Exception e) {
            result.setMessage("查询商品列表失败");
        }
        return result;
    }
    
    /**
     * 获取商品详情
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<StAppletProduct> detail(Integer id, HttpServletResponse response){
        HttpJsonResult<StAppletProduct> result = new HttpJsonResult<>();
        try {
            StAppletProduct product = productService.getStAppletProductById(id).getResult();
            
            // 组装图片数据
            List<ProductPicture> pictures = productPictureService.getProductPictureByProductId(product.getId()).getResult();
            if (pictures != null) {
                product.setPictures(pictures);
            }
            result.setData(product);
        } catch (Exception e) {
            result.setMessage("查询商品详情失败");
        }
        return result;
    }
}
