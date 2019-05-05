package com.ejavashop.web.controller.promotion;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActGroupService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

/**
 * 团购详情页controller
 * 
 * @Filename: ActGroupDetailController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class ActGroupDetailController extends BaseController {

    @Resource
    private IProductService      productService;
    @Resource
    private ISellerService       sellerService;
    @Resource
    private IProductGoodsService productGoodsService;
    @Resource
    private IActGroupService     actGroupService;

    /**
     * 跳转到单品页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/tuan/{actGroupId}.html", method = { RequestMethod.GET })
    public String productDetail(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap, @PathVariable Integer actGroupId) {

        dataMap.put("actGroupId", actGroupId);

        ServiceResult<ActGroup> groupResult = actGroupService.getActGroupById(actGroupId);
        if (!groupResult.getSuccess() || groupResult.getResult() == null) {
            dataMap.put("info", "团购信息为空。");
            return "h5v1/commons/error";
        }

        ActGroup actGroup = groupResult.getResult();
        //获得商品信息
        ServiceResult<Product> productResult = productService.getProductById(actGroup
            .getProductId());
        if (!productResult.getSuccess()) {
            dataMap.put("info", productResult.getMessage());
            return "h5v1/commons/error";
        }
        if (productResult.getResult() == null) {
            dataMap.put("info", "获得商品信息为空！");
            return "h5v1/commons/error";
        }
        Product product = productResult.getResult();
        dataMap.put("product", product);
        actGroup.setProductName(productResult.getResult().getName1());

        if (actGroup.getTypeState() == null || actGroup.getTypeState() != ActGroup.TYPE_STATE_1) {
            log.error("团购活动" + actGroup.getName() + "的分类状态为不显示，下单失败。");
            dataMap.put("info", "对不起，该团购活动已下线！");
            return "h5v1/commons/error";
        }
        if (actGroup.getState() == null || actGroup.getState() != ActGroup.STATE_3) {
            dataMap.put("info", "对不起，团购活动不存在！");
            return "h5v1/commons/error";
        }
        if (actGroup.getActivityState() == null
            || actGroup.getActivityState() != ActGroup.ACTIVITY_STATE_2) {
            dataMap.put("info", "对不起，该团购活动还没有发布！");
            return "h5v1/commons/error";
        }
        if (actGroup.getChannel() != ConstantsEJS.CHANNEL_1
            && actGroup.getChannel() != ConstantsEJS.CHANNEL_3) {
            dataMap.put("info", "对不起，该团购活动不在移动端进行！");
            return "h5v1/commons/error";
        }
        dataMap.put("actGroup", actGroup);

        //获得货品信息,默认取第一个 包含某规格商品的库存及价格信息 
        ServiceResult<List<ProductGoods>> goodsServiceResult = productGoodsService
            .getGoodSByProductId(product.getId());
        List<ProductGoods> goods = goodsServiceResult.getResult();
        if (goods == null || goods.size() == 0) {
            dataMap.put("info", "货品信息为空。");
            return "h5v1/commons/error";
        } else {
            ProductGoods good = goods.get(0);
            dataMap.put("goods", good);
        }

        // 组装商品规格信息
        List<ProductNorm> normList = new ArrayList<ProductNorm>();
        Map<String, ProductNorm> normMap = new HashMap<String, ProductNorm>();
        Map<String, ProductNormAttr> attrMap = new HashMap<String, ProductNormAttr>();
        for (ProductGoods good : goods) {
            String normName = good.getNormName(); // 如：颜色,红色;内存,4G
            String normAttrId = good.getNormAttrId(); // 如：1,17

            if (StringUtil.isEmpty(normName, true)) {
                // 规则属性为空则表示该商品没有启用规格
                continue;
            }

            String[] normNameSplit = normName.split(";");
            String[] normAttrIdSplit = normAttrId.split(",");

            if (normNameSplit.length > 0) {
                // 循环
                for (int i = 0; i < normNameSplit.length; i++) {
                    String name = normNameSplit[i];

                    // 得到类似：颜色,红色的值，颜色为规格名称，红色为规格的值
                    String[] cellName = name.split(",");

                    if (normMap.get(cellName[0]) == null) {
                        // 如果规格map中没有当前规格，则添加规格和对应的规格值
                        ProductNorm norm = new ProductNorm();
                        norm.setName(cellName[0]);
                        // 保存规则名称
                        normList.add(norm);
                        normMap.put(cellName[0], norm);
                        // 保存规则值
                        List<ProductNormAttr> attrList = new ArrayList<ProductNormAttr>();
                        ProductNormAttr attr = new ProductNormAttr();
                        attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
                        attr.setName(cellName[1]);
                        attrList.add(attr);
                        norm.setAttrList(attrList);

                        // 记录到map中防止重复添加
                        attrMap.put(cellName[1], attr);
                    } else {
                        // 如果规格map中有当前规格，则不添加规格，对应的规格值再判断是否已经存在决定是否添加
                        ProductNorm norm = normMap.get(cellName[0]);

                        // 判断是否已有规则值，如果没有则添加，有则不再添加
                        if (attrMap.get(cellName[1]) == null) {
                            // 规则值
                            List<ProductNormAttr> attrList = norm.getAttrList();
                            ProductNormAttr attr = new ProductNormAttr();
                            attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
                            attr.setName(cellName[1]);
                            attrList.add(attr);
                            norm.setAttrList(attrList);
                            // 记录到map中防止重复添加
                            attrMap.put(cellName[1], attr);
                        }
                    }
                }
            }
        }
        dataMap.put("norms", normList);

        // 获得商家信息、商家详细信息
        ServiceResult<Seller> sellerServiceResult = sellerService.getSellerById(actGroup
            .getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 记录活动进行状态
        int stageType = 0;
        Date date = new Date();
        if (date.before(actGroup.getStartTime())) {
            // 还未开始
            // 计算开始倒计时
            long countTime = actGroup.getStartTime().getTime() - date.getTime();
            dataMap.put("countTime", countTime / 1000);
            stageType = 1;
        } else if (date.after(actGroup.getEndTime())) {
            // 已结束不计算时间
            stageType = 3;
        } else {
            // 计算结束倒计时
            long countTime = actGroup.getEndTime().getTime() - date.getTime();
            dataMap.put("countTime", countTime / 1000);
            stageType = 2;
        }
        dataMap.put("stageType", stageType);
        return "h5v1/promotion/actgroupdetail";
    }
}
