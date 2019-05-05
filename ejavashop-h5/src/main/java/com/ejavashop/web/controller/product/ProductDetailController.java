package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductComments;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.promotion.coupon.Coupon;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.operate.IProductLabelService;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.service.product.IProductAttrService;
import com.ejavashop.service.product.IProductBuyStockService;
import com.ejavashop.service.product.IProductCommentsService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductNormAttrOptService;
import com.ejavashop.service.product.IProductPriceService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActFlashSaleService;
import com.ejavashop.service.promotion.IActFullService;
import com.ejavashop.service.promotion.IActSingleService;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.util.FrontProductPictureUtil;
import com.ejavashop.vo.member.FrontMemberProductBehaveStatisticsVO;
import com.ejavashop.vo.product.ProductActVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 单品页controller
 * 
 * @Filename: ProductDetailController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class ProductDetailController extends BaseController {

    @Resource
    private IMemberService             memberService;
    @Resource
    private IProductService            productService;
    @Resource
    private ISellerService             sellerService;
    @Resource
    private IProductGoodsService       productGoodsService;
    @Resource
    private FrontProductPictureUtil    frontProductPictureUtil;
    @Resource
    private IProductAttrService        productAttrService;
    @Resource
    private IProductCommentsService    productCommentsService;
    @Resource
    private IProductAskService         productAskService;
    @Resource
    private ICartService               cartService;
    @Resource
    private IActSingleService          actSingleService;
    @Resource
    private IActFullService            actFullService;
    @Resource
    private ICouponService             couponService;
    @Resource
    private IActFlashSaleService       actFlashSaleService;
    @Resource
    private IProductNormAttrOptService productNormAttrOptService;
    @Resource
    private IProductPackageService     productPackageService;
    @Resource
    private IProductBuyStockService    productBuyStockService;
    @Resource
    private IProductPriceService       productPriceService;
    @Resource
    private IOrdersService             ordersService;
    @Resource
    private IProductLabelService       productLabelService;
    /**
     * 跳转到单品页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/{productId}.html", method = { RequestMethod.GET })
    public String productDetail(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);
        String goodIdStr = request.getParameter("goodId");
        Integer goodId = ConvertUtil.toInt(goodIdStr, 0);

        Member member = WebFrontSession.getLoginedUser(request);
        Integer status = 0;
        if(member!=null){
            ServiceResult<Integer> sResult = ordersService.getOrdersByMemberId(member.getId());
            if(sResult.getSuccess()){
                status = sResult.getResult();
            }
        }
        dataMap.put("status", status);
        //获得商品信息
        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        ProductPrice p_price = new ProductPrice();
        if (product != null && product.getPriceStatus() == 2) {//阶梯价需要查询另一张表
            p_price = productPriceService.getProductPriceById(product.getPricePid()).getResult();
            if (p_price != null) {
                dataMap.put("productPrice", p_price);
            }
        }
        //将product中descript值_src替换为data-orginal  实现图片懒加载 
	    String descriptstr =  product.getDescription().replaceAll("_src", "lazyLoadSrc").replaceAll("src", "lazyLoadSrc");
	    product.setDescription(descriptstr);
        dataMap.put("product", product);
        //获取打标价信息 add by tongzhaomei 
        ProductPrice p_price2 = new ProductPrice();
        if (product != null) {//获取打标价
        	p_price2 = productPriceService.getProductPriceById(product.getPrice2Pid()).getResult();
            if (p_price2 != null) {
                dataMap.put("productPrice2", p_price2);
            }
        }
      //add by Ls 2016/08/06 新增属性查询，规格包装的双数
        ProductAttr productAttr = new ProductAttr();
        try {
            ServiceResult<ProductAttr> attrResult = productAttrService.getByProductIdAndName(product.getId(),"包装规格"); 
            productAttr = attrResult.getResult();
            String[] attr_n = productAttr.getValue().split("双");
            productAttr.setValue(attr_n[0]);
        } catch (Exception e) {
            productAttr.setName("包装规格");
            productAttr.setValue("10");
        }
        dataMap.put("pAttr", productAttr);
        
        // 取是否预览标志，1表示是预览
        String preview = request.getParameter("preview");
        if (!"1".equals(preview)) {
            // 如果不是预览，则根据属性修改商品的状态
            // 上架时间在现在之后
            if (product.getUpTime().after(new Date())) {
                product.setState(Product.STATE_7);
            }
            // 店铺被冻结
            if (product.getSellerState().intValue() != Product.SELLER_STATE_1) {
                product.setState(Product.STATE_7);
            }
        } else {
            product.setState(Product.STATE_6);
        }

        //获得产品对应的大图
        List<String> productLeadPicList = frontProductPictureUtil.getByProductIds(productId);
        dataMap.put("productLeadPicList", productLeadPicList);

        //获得用户评价数及占比
        ServiceResult<FrontMemberProductBehaveStatisticsVO> serviceResult = new ServiceResult<FrontMemberProductBehaveStatisticsVO>();
        serviceResult = memberService.getBehaveStatisticsByProductId(productId, member);
        dataMap.put("statisticsVO", serviceResult.getResult());

        //获得货品信息,默认取第一个 包含某规格商品的库存及价格信息 
        ServiceResult<List<ProductGoods>> goodsServiceResult = productGoodsService
            .getGoodSByProductId(productId);
        List<ProductGoods> goods = goodsServiceResult.getResult();
        if (goods == null || goods.size() == 0) {
            dataMap.put("info", "货品信息为空。");
            return "h5v1/commons/error";
        } else {
            ProductGoods good = goods.get(0);
            if (goodId > 0) {
                for (ProductGoods goodFor : goods) {
                    if (goodId.equals(goodFor.getId())) {
                        good = goodFor;
                        break;
                    }
                }
            }
            good.setProductStockWarning(product.getStockWarning());
            dataMap.put("goods", good);

            //最大购买数
            BigDecimal maxStock = new BigDecimal(good.getProductStock());
            //最大购买数限制
            if (!isNull(member)) {
                //如果货品库存到达阀值，则根据会员等级限制最大购买数
                Integer prostock = good.getProductStock();

                //当前货品对应的库存限制
                Map<String, String> map = new HashMap<String, String>();
                map.put("productId", product.getId() + "");
                map.put("goodsId", good.getId() + "");
                ServiceResult<List<ProductBuyStock>> pbssr = productBuyStockService.page(map, null);
                List<ProductBuyStock> pbslist = pbssr.getResult();
                if (pbslist != null && pbslist.size() > 0) {
                    if (pbslist.size() > 1) {
                        throw new BusinessException("该sku对应多个库存设置");
                    }
                    ProductBuyStock pbs = pbslist.get(0);
                    if (prostock.intValue() <= pbs.getStock().intValue()) {
                        //库存值小于等于阀值，则根据等级做限制
                        Integer grade = member.getGrade();

                        switch (grade) {
                            case 1:
                                //普通会员
                                maxStock = new BigDecimal(prostock).multiply(pbs.getGrade1());
                                break;
                            case 2:
                                //铜牌会员
                                maxStock = new BigDecimal(prostock).multiply(pbs.getGrade2());
                                break;
                            case 3:
                                //银牌会员
                                maxStock = new BigDecimal(prostock).multiply(pbs.getGrade3());
                                break;
                            case 4:
                                //金牌会员
                                maxStock = new BigDecimal(prostock).multiply(pbs.getGrade4());
                                break;
                            case 5:
                                //钻石会员
                                maxStock = new BigDecimal(prostock).multiply(pbs.getGrade5());
                                break;

                            default:
                                break;
                        }
                        maxStock = maxStock.setScale(0, BigDecimal.ROUND_HALF_UP);
                    }
                }
            }
            good.setMaxStock(maxStock.intValue());

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

        //取得属性值
        ServiceResult<List<ProductAttr>> attrResult = productAttrService.getProductAttrByProductId(productId);
        if (!attrResult.getSuccess()) {
            throw new BusinessException("获得商品属性信息失败！");
        }
        if (attrResult.getResult() == null) {
            throw new BusinessException("获得商品属性信息为空！");
        }
        dataMap.put("productAttr", attrResult.getResult());
        //end
        //选择的颜色属性
        if (normList.size() > 0) {
            Iterator<ProductNorm> it = normList.iterator();
            while (it.hasNext()) {
                ProductNorm norm = it.next();
                //移除颜色规格，和添加商品逻辑一致
                if (norm.getName().indexOf("颜色") != -1) {
                    //移除颜色属性
                    it.remove();
                }
            }
            //选定的规格
            Map<String, String> skumap = new HashMap<String, String>();
            skumap.put("sellerId", product.getSellerId() + "");
            skumap.put("productId", product.getId() + "");
            skumap.put("flag", "2");
            ServiceResult<List<ProductNormAttrOpt>> optser = productNormAttrOptService.page(skumap,
                null);
            String noomids = "";
            if (optser.getResult().size() > 0) {
            	
                ProductNorm norm = new ProductNorm();
                List<ProductNormAttr> attrlist = new ArrayList<ProductNormAttr>();
                for (ProductNormAttrOpt opt : optser.getResult()) {
                    if (isNull(norm.getName())) {
                        norm.setIsCustom(true);
                        norm.setName(opt.getProductNormName());
                    }
                ProductNormAttr attr = new ProductNormAttr();
                Integer productGoodsId = null;
                Integer productStock = null;
                BigDecimal pgMallMobilePrice = BigDecimal.ZERO;
                attr.setSort(2);
                    for (ProductGoods good : goods) {
                        /*if(good.getNormName().substring(4).equals(opt.getName())){
                        	productGoodsId = good.getId();
                        	productStock = good.getProductStock();
                        	pgMallMobilePrice = good.getMallMobilePrice();
                        	//猫头鹰seller_id = 1 or 13商品库存小于一百 下架
                        	 if(good.getProductStock()<100 && product != null && (product.getSellerId() == 1 || product.getSellerId() == 13)){
                                 attr.setSort(1);
                                 //非猫头鹰商品。sku库存小于一手 下架
                             }else if(productAttr != null && good.getProductStock()< Integer.parseInt(productAttr.getValue()) && product.getSellerId() != 1 && product.getSellerId() != 13){
                             	attr.setSort(1);
                             }
                            if(good.getProductStock()<100){
                                attr.setSort(1);
                            }
                        }*/
                        //替换上面判定条件   add by tzm 2016-11-22
                        if(good.getNormAttrId().equals(opt.getAttrId().toString())){
                        	productGoodsId = good.getId();
                        	productStock = good.getProductStock();
                        	pgMallMobilePrice = good.getMallMobilePrice();
                        	//猫头鹰seller_id = 1 or 13商品库存小于一百 下架
                        	 if(good.getProductStock()<100 && product != null && (product.getSellerId() == 1 || product.getSellerId() == 13)){
                                 attr.setSort(1);
                                 //非猫头鹰商品。sku库存小于一手 下架
                             }else if(productAttr != null && good.getProductStock()< Integer.parseInt(productAttr.getValue()) && product.getSellerId() != 1 && product.getSellerId() != 13){
                             	attr.setSort(1);
                             }
                           /* if(good.getProductStock()<100){
                                attr.setSort(1);
                            }*/
                        }
                        
                        
                    }
                    attr.setProductGoodsId(productGoodsId);
                    attr.setProductStock(productStock);
                    attr.setPgMallPcPrice(pgMallMobilePrice);
                    
                    noomids+=opt.getAttrId()+",";
                    attr.setId(opt.getAttrId());
                    attr.setName(opt.getName());
                    if (!isNull(opt.getImage()))
                        attr.setUrl(opt.getImage());
                    attrlist.add(attr);
                }

                //添加至商品规格
                norm.setAttrList(attrlist);
                normList.add(0, norm);
            }
            dataMap.put("noomids", noomids);
        }
        
        dataMap.put("norms", normList);

        //套餐
        Map<String, String> packMap = new HashMap<String, String>();
        packMap.put("state", "1");
        ServiceResult<List<ProductPackage>> packsr = productPackageService.page(packMap, null);
        dataMap.put("productPackage", packsr.getResult());
        //获得商标信息  通过关联的ID add by tongzhaomei
        String shangbiaoInfo = "";
        if(product.getSkuOther() != null && !"".equals(product.getSkuOther())){
        	String[] skuothers = product.getSkuOther().split(",");
        	ServiceResult<List<ProductLabel>>  skuothers2 = productLabelService.getProductLabelNameByskuother(skuothers);
        	List<ProductLabel> productLabels = skuothers2.getResult();
        	if(productLabels.size()>0){
        		for (ProductLabel productLabel : productLabels) {
        			shangbiaoInfo += "	" + productLabel.getName();
				}
        		dataMap.put("shangbiaoInfo", shangbiaoInfo);
        	}
        }
        //打标袜 打标信息获取 add by tongzhaomei
        if(product.getTaocanId()!=null && product.getTaocanId() != 0){
        	ServiceResult<ProductPackage> ppfordabiao =  productPackageService.getProductPackageById(product.getTaocanId());
        	ProductPackage pp = ppfordabiao.getResult();
        	//封装辅料
        	String fuliaoStr = "";
        	if(pp!=null){
        		if(pp.getIsHook()==1){//钩子
        			fuliaoStr += "钩子";
        		}else if(pp.getIsGlue()==1){//不干胶
        			fuliaoStr += ",不干胶";
        		}else if(pp.getIsLiner()==1){//衬板
        			fuliaoStr += ",衬板";
        		}else if(pp.getIsBag()==1){//中包袋
        			fuliaoStr += ",中包袋";
        		}else if(pp.getIsLabel()==1){//防伪标
        			fuliaoStr += ",防伪标";
        		}else if(pp.getIsGirdle()==1){//腰封
        			fuliaoStr += ",腰封";
        		}
        		pp.setFuliaoStr(fuliaoStr);
        		//封装包装单位packageUnitStr
        		if(pp.getUnitType()==5){
        			pp.setPackageUnitStr("五双装");
        		}else if(pp.getUnitType()==4){
        			pp.setPackageUnitStr("四双装");
        		}else if(pp.getUnitType()==3){
        			pp.setPackageUnitStr("三双装");
        		}else if(pp.getUnitType()==2){
        			pp.setPackageUnitStr("二双装");
        		}else if(pp.getUnitType()==1){
        			pp.setPackageUnitStr("一双装");
        		}
        	}
        	dataMap.put("productPackage2", ppfordabiao.getResult());
        }
        
        // 获得商家信息、商家详细信息
        ServiceResult<Seller> sellerServiceResult = sellerService.getSellerById(productResult
            .getResult().getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 购物车数量
        if (member != null && member.getId() != null) {
            ServiceResult<Integer> cartResult = cartService.getCartNumberByMId(member.getId());
            dataMap.put("cartNumber", cartResult.getResult());
        }

        //已选列表
        if (member != null) {
            Map<String, Object> cartMap = new HashMap<String, Object>();
            cartMap.put("q_memberId", member.getId());
            cartMap.put("q_productId", productId);
            ServiceResult<List<Cart>> cartserviceResult = cartService.queryList(cartMap);
            if (cartserviceResult.getResult().size() > 0) {
                int count = 0;
                //商品价格
                double amount = 0d;
                for (Cart cart : cartserviceResult.getResult()) {
                    count += cart.getCount();
                    ProductGoods pg = productGoodsService.getProductGoodsById(
                        cart.getProductGoodsId()).getResult();
                    if (pg != null) {
                        //add by lushuai 2016/07/13
                        //此处对价格进行处理，根据一口价1，阶梯价2，整箱价3的不同价格进行计算
                        Product pro = new Product();
                        pro = productService.getProductById(pg.getProductId()).getResult();
                        if (pro != null && pro.getPriceStatus() == 1) {//一口价
                            amount += pg.getMallPcPrice().doubleValue() * cart.getCount();
                        } else if (pro != null && pro.getPriceStatus() == 2) {//阶梯价
                            ProductPrice pro_price = productPriceService.getProductPriceById(pro.getPricePid()).getResult();
                            int num = cart.getCount();
                            if (num >= pro_price.getPrice1S() && num <= pro_price.getPrice1E()) {
                                amount += pro_price.getPrice1().doubleValue() * cart.getCount();
                            }
                            if (num >= pro_price.getPrice2S() && num <= pro_price.getPrice2E()) {
                                amount += pro_price.getPrice2().doubleValue() * cart.getCount();
                            }
                            if (num >= pro_price.getPrice3S()) {
                                amount += pro_price.getPrice3().doubleValue() * cart.getCount();
                            }
                        } else if (pro != null && pro.getPriceStatus() == 3) {//整箱价
                            int num = cart.getCount();
                            int quantity = pro.getFullContainerQuantity();
                            amount += (num / quantity) * quantity
                                      * pro.getMallPcPrice().doubleValue();//先计算整箱部分
                            amount += (num % quantity) * pro.getScatteredPrice().doubleValue();
                        }
                    }

                    //套餐价格
                    if (cart.getProductPackageId() != null) {
                        ProductPackage pp = productPackageService.getProductPackageById(
                            cart.getProductPackageId()).getResult();
                        if (pp != null)
                            amount += (pp.getPrice() == null ? BigDecimal.ZERO : pp.getPrice())
                                .doubleValue();
                    }

                    StringBuffer sb = new StringBuffer(isNull(cart.getSpecInfo()) ? "无规格"
                        : cart.getSpecInfo());
                    if (!StringUtil.isNullOrZero(cart.getProductPackageId())) {
                        ProductPackage pp = productPackageService.getProductPackageById(
                            cart.getProductPackageId()).getResult();
                        sb.append("(");
                        sb.append("套餐：").append(pp.getName()).append(";");
                        if (StringUtil.isNullOrZero(cart.getIsSelfLabel())) {
                            sb.append("标签：").append("平台标");
                        } else {
                            sb.append("标签：").append("自供标");
                        }
                        sb.append(")");
                    }
                    cart.setSpecInfo(sb.toString());
                }

                dataMap.put("count", count);
                dataMap.put("amount", amount);
                dataMap.put("selectedlist", cartserviceResult.getResult());
            }
        }

        // 获取类型参数：为1时表示打开限时抢购页面
        String type = request.getParameter("type");
        if (type != null && type.equals("1")) {
            // 限时抢购时获取活动信息
            // 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
            ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
                .getTodayEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);

            // 抢购活动信息，不为空则显示，为空则提示当前时间无抢购活动
            ActFlashSale actFlashSale = flashSaleResult.getResult();
            if (actFlashSale != null) {
                dataMap.put("actFlashSale", actFlashSale);

                // 记录正在进行的阶段，用于计算结束时间
                ActFlashSaleStage underWayStage = null;
                // 记录即将开始的阶段（商品有多个阶段即将开始时显示最近的）
                ActFlashSaleStage comingStage = null;
                // 记录阶段类型
                int stageType = 0;

                // 如果活动对象不为空，则返回结果中必定有阶段和活动商品
                int hour = TimeUtil.getHour();
                int minute = TimeUtil.getMinute();
                int second = TimeUtil.getSecond();
                for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                    int startTime = stage.getStartTime();
                    int endTime = stage.getEndTime();
                    if (hour >= startTime && hour < endTime) {
                        // 如果有阶段正在进行  优先显示正在进行的
                        dataMap.put("actFlashSaleStage", stage);
                        dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                        dataMap.put("stageType", ProductActVO.STAGE_TYPE_2);
                        underWayStage = stage;
                        stageType = ProductActVO.STAGE_TYPE_2;
                        break;
                    } else if (hour < startTime) {
                        // 如果有阶段即将开始，显示最近的，如果有正在进行的则会覆盖
                        if (comingStage == null
                            || stage.getStartTime() < comingStage.getStartTime()) {
                            // 如果循环当前阶段比上次记录的阶段要早，则显示当前阶段
                            dataMap.put("actFlashSaleStage", stage);
                            dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                            dataMap.put("stageType", ProductActVO.STAGE_TYPE_3);
                            comingStage = stage;
                            stageType = ProductActVO.STAGE_TYPE_3;
                        }
                    } else if (hour >= endTime) {
                        // 如果有阶段已经结束，则判断是否有正在进行或即将开始，
                        // 如果都没有，则保存结束的阶段，后续循环如果有即将开始或者正在进行的将会覆盖
                        if (stageType == 0) {
                            dataMap.put("actFlashSaleStage", stage);
                            dataMap.put("actFlashSaleProduct", stage.getProductList().get(0));
                            dataMap.put("stageType", ProductActVO.STAGE_TYPE_1);
                            stageType = ProductActVO.STAGE_TYPE_1;
                        }
                    }
                }

                if (stageType == ProductActVO.STAGE_TYPE_2) {
                    //计算结束倒计时
                    int endTime = underWayStage.getEndTime();
                    int hour1 = endTime - hour - 1;
                    int minute1 = 60 - minute;
                    int second1 = 60 - second;
                    int countTime = hour1 * 3600 + minute1 * 60 + second1;
                    dataMap.put("countTime", countTime);
                } else if (stageType == ProductActVO.STAGE_TYPE_3) {
                    // 计算开始倒计时
                    int startTime = comingStage.getStartTime();
                    int hour1 = startTime - hour;
                    int countTime = hour1 * 3600 - minute * 60 - second;
                    dataMap.put("countTime", countTime);
                }
            }
            return "h5v1/product/productdetailflash";
        } else {
            // 类型参数为空则打开普通单品页
            return "h5v1/product/productdetail";
        }

    }

    /**
     * 跳转到商品详细介绍页 
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/info/{productId}.html", method = { RequestMethod.GET })
    public String productDetailInfo(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);
        //获得商品信息
        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        dataMap.put("product", product);
        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5v1/product/detailinfo";
    }

    /**
     * 跳转到单品规格页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/spec/{productId}.html", method = { RequestMethod.GET })
    public String productDetailSpec(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        //获得商品信息
        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess()) {
            throw new BusinessException("获得商品信息失败！");
        }
        if (productResult.getResult() == null) {
            throw new BusinessException("获得商品信息为空！");
        }
        Product product = productResult.getResult();
        dataMap.put("product", product);

        //取得属性值
        ServiceResult<List<ProductAttr>> attrResult = productAttrService
            .getProductAttrByProductId(productId);
        if (!attrResult.getSuccess()) {
            throw new BusinessException("获得商品属性信息失败！");
        }
        if (attrResult.getResult() == null) {
            throw new BusinessException("获得商品属性信息为空！");
        }
        dataMap.put("productAttr", attrResult.getResult());

        // 获得商家信息、商家详细信息及 商家qq信息  以及活动信息 
        ServiceResult<Seller> sellerServiceResult = sellerService.getSellerById(productResult
            .getResult().getSellerId());
        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
            // 商家基本信息
            Seller seller = sellerServiceResult.getResult();
            dataMap.put("seller", seller);
        }

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5v1/product/detailspec";
    }

    /**
     * 跳转到单品评论页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/comment/{productId}.html", method = { RequestMethod.GET })
    public String productDetailComment(HttpServletRequest request, HttpServletResponse response,
                                       Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        Member member = WebFrontSession.getLoginedUser(request);

        //获得用户评价数及占比
        ServiceResult<FrontMemberProductBehaveStatisticsVO> serviceResult = new ServiceResult<FrontMemberProductBehaveStatisticsVO>();
        serviceResult = memberService.getBehaveStatisticsByProductId(productId, member);
        dataMap.put("statisticsVO", serviceResult.getResult());

        // 好评 grade = 4或5，中评：3，差评：1或2
        // 全部评论
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        //查找所有的评价和咨询   状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();

        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_state", String.valueOf(ProductComments.STATE_2));
        queryMap.put("q_grades", "all");
        ServiceResult<List<ProductComments>> allCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 全部评论数量和数据
        dataMap.put("allNumber", pager.getRowsCount());
        dataMap.put("allCommentList", allCmtResult.getResult());

        // 好评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "high");
        ServiceResult<List<ProductComments>> highCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("highNumber", pager.getRowsCount());
        dataMap.put("highCommentList", highCmtResult.getResult());

        // 中评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "middle");
        ServiceResult<List<ProductComments>> middleCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("middleNumber", pager.getRowsCount());
        dataMap.put("middleCommentList", middleCmtResult.getResult());

        // 差评
        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        queryMap.put("q_grades", "low");
        ServiceResult<List<ProductComments>> lowCmtResult = productCommentsService
            .getProductComments(queryMap, pager);
        // 数量和数据
        dataMap.put("lowNumber", pager.getRowsCount());
        dataMap.put("lowCommentList", lowCmtResult.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5v1/product/detailcomment";
    }

    /**
     * 单品页 评价列表，异步调用加载更多评论
     * @param request
     * @param response
     * @param dataMap
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/morecomment.html", method = { RequestMethod.GET })
    public String commentsListForProcutPage(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Map<String, Object> dataMap) {

        // 商品ID
        String productIdStr = request.getParameter("productId");

        String type = request.getParameter("type");

        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = ConvertUtil.toInt(pageIndexStr, 1);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        //查找所有的评价和咨询   状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();

        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productIdStr);
        queryMap.put("q_state", String.valueOf(ProductComments.STATE_2));
        //好评  grade = 4或5 ,中评：3，差评：1或2
        queryMap.put("q_grades", type);

        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductComments(queryMap, pager);

        dataMap.put("commentList", serviceResult.getResult());
        dataMap.put("type", type);

        return "h5v1/product/commentlist";
    }

    /**
     * 跳转到单品咨询页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/product/ask/{productId}.html", method = { RequestMethod.GET })
    public String productDetailAsk(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap, @PathVariable Integer productId) {

        dataMap.put("productId", productId);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Map<String, String> queryMap = new HashMap<String, String>();
        //查找某个产品的 状态为前台显示的
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_state", String.valueOf(ProductAsk.STATE_3));
        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsks(queryMap,
            pager);

        // 数量和数据
        dataMap.put("askNumber", pager.getRowsCount());
        dataMap.put("askList", serviceResult.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        // 跳转类型（type=1跳转到秒杀单品页）
        String type = request.getParameter("type");
        dataMap.put("type", type);

        return "h5v1/product/detailask";
    }

    /**
     * 单品页 咨询列表，异步调用
     * @param request
     * @param response
     * @param dataMap
     * @param productId
     * @return
     */
    @RequestMapping(value = "/product/moreask.html", method = { RequestMethod.GET })
    public String productAskListForProcutPage(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Map<String, Object> dataMap) {

        // 商品ID
        String productIdStr = request.getParameter("productId");
        String pageIndexStr = request.getParameter("pageIndex");
        Integer pageIndex = ConvertUtil.toInt(pageIndexStr, 1);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        //查找所有的评价和咨询，状态为审核通过
        Map<String, String> queryMap = new HashMap<String, String>();
        //查找某个产品的咨询，状态为前台显示的
        queryMap.put("q_productId", productIdStr);
        queryMap.put("q_state", String.valueOf(ProductAsk.STATE_3));

        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsks(queryMap,
            pager);

        dataMap.put("askList", serviceResult.getResult());

        return "h5v1/product/asklist";
    }

    /**
     * 根据产品id及规格id查询货品价格及库存
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/getGoodsInfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductGoods> collectionShop(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestParam(value = "productId", required = true) Integer productId,
                                                                     String normAttrId) {

        Map<String, String> queryMap = new HashMap<String, String>();
        //获得货品信息 包含某规格商品的库存及价格信息
        queryMap.put("q_productId", productId + "");
        queryMap.put("q_normAttrId", normAttrId);
        ServiceResult<ProductGoods> serviceResult = productGoodsService
            .getProductGoodsByCondition(queryMap);

        Member member = WebFrontSession.getLoginedUser(request);
        ProductGoods pg = serviceResult.getResult();
        pg.setProductStockWarning(productService.getProductById(productId).getResult()
            .getStockWarning());
        //最大购买数
        BigDecimal maxStock = new BigDecimal(pg.getProductStock());
        //最大购买数限制
        if (!isNull(member)) {
            //如果货品库存到达阀值，则根据会员等级限制最大购买数
            Integer prostock = pg.getProductStock();

            //当前货品对应的库存限制
            Map<String, String> map = new HashMap<String, String>();
            map.put("productId", productId + "");
            map.put("goodsId", pg.getId() + "");
            ServiceResult<List<ProductBuyStock>> pbssr = productBuyStockService.page(map, null);
            List<ProductBuyStock> pbslist = pbssr.getResult();
            if (pbslist != null && pbslist.size() > 0) {
                if (pbslist.size() > 1) {
                    throw new BusinessException("该sku对应多个库存设置");
                }
                ProductBuyStock pbs = pbslist.get(0);
                if (prostock.intValue() <= pbs.getStock().intValue()) {
                    //库存值小于等于阀值，则根据等级做限制
                    Integer grade = member.getGrade();

                    switch (grade) {
                        case 1:
                            //普通会员
                            maxStock = new BigDecimal(prostock).multiply(pbs.getGrade1());
                            break;
                        case 2:
                            //铜牌会员
                            maxStock = new BigDecimal(prostock).multiply(pbs.getGrade2());
                            break;
                        case 3:
                            //银牌会员
                            maxStock = new BigDecimal(prostock).multiply(pbs.getGrade3());
                            break;
                        case 4:
                            //金牌会员
                            maxStock = new BigDecimal(prostock).multiply(pbs.getGrade4());
                            break;
                        case 5:
                            //钻石会员
                            maxStock = new BigDecimal(prostock).multiply(pbs.getGrade5());
                            break;

                        default:
                            break;
                    }
                    maxStock = maxStock.setScale(0, BigDecimal.ROUND_HALF_UP);
                }
            }
        }
        pg.setMaxStock(maxStock.intValue());

        HttpJsonResult<ProductGoods> jsonResult = new HttpJsonResult<ProductGoods>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductGoods>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 商品页异步加载立减、满减、优惠券信息
     * @param request
     * @param response
     * @param productId
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/getproductactinfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductActVO> getProductActInfo(HttpServletRequest request,
                                                                        HttpServletResponse response,
                                                                        @RequestParam(value = "productId", required = true) Integer productId,
                                                                        @RequestParam(value = "sellerId", required = true) Integer sellerId) {

        ProductActVO productActVO = new ProductActVO();
        // 如果获取限时抢购的结果为空，则加载商品的单品立减、订单满减、优惠券信息
        ServiceResult<ActSingle> singleResult = actSingleService.getEffectiveActSingle(sellerId,
            ConstantsEJS.CHANNEL_3, productId);
        // 满减
        ServiceResult<ActFull> fullResult = actFullService.getEffectiveActFull(sellerId,
            ConstantsEJS.CHANNEL_3);
        // 优惠券
        ServiceResult<List<Coupon>> couponResult = couponService.getEffectiveCoupon(sellerId,
            ConstantsEJS.CHANNEL_3);
        productActVO.setActSingle(singleResult.getResult());
        productActVO.setActFull(fullResult.getResult());
        productActVO.setCouponList(couponResult.getResult());

        HttpJsonResult<ProductActVO> jsonResult = new HttpJsonResult<ProductActVO>();
        jsonResult.setData(productActVO);
        return jsonResult;
    }

    /**
     * 商品页异步加载限时抢购信息
     * @param request
     * @param response
     * @param productId
     * @return
     */
    @RequestMapping(value = "/getproductflashinfo.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductActVO> getProductFlashInfo(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          @RequestParam(value = "productId", required = true) Integer productId) {

        ProductActVO productActVO = new ProductActVO();
        // 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
        ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
            .getTodayEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);

        // 抢购活动
        ActFlashSale actFlashSale = flashSaleResult.getResult();
        if (actFlashSale != null) {
            productActVO.setActFlashSale(actFlashSale);
            // 如果活动对象不为空，则返回结果中必定有阶段和活动商品
            int hour = TimeUtil.getHour();
            for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
                int startTime = stage.getStartTime();
                int endTime = stage.getEndTime();
                if (hour >= startTime && hour < endTime) {
                    // 如果有阶段正在进行  优先显示正在进行的
                    productActVO.setActFlashSaleStage(stage);
                    productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                    productActVO.setStageType(ProductActVO.STAGE_TYPE_2);
                    break;
                } else if (hour < startTime) {
                    // 如果有阶段即将开始，保存到productActVO，如果有正在进行的则会覆盖
                    productActVO.setActFlashSaleStage(stage);
                    productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                    productActVO.setStageType(ProductActVO.STAGE_TYPE_3);
                } else if (hour >= endTime) {
                    // 如果有阶段已经结束，则判断是否有正在进行或即将开始，
                    // 如果都没有，则保存结束的阶段，后续循环如果有即将开始或者正在进行的将会覆盖
                    if (productActVO.getStageType() == null || productActVO.getStageType() == 0) {
                        productActVO.setActFlashSaleStage(stage);
                        productActVO.setActFlashSaleProduct(stage.getProductList().get(0));
                        productActVO.setStageType(ProductActVO.STAGE_TYPE_1);
                    }
                }
            }
        }

        HttpJsonResult<ProductActVO> jsonResult = new HttpJsonResult<ProductActVO>();
        jsonResult.setData(productActVO);
        return jsonResult;
    }
}
