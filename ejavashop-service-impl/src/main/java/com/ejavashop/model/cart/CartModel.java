package com.ejavashop.model.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.full.ActFullReadDao;
import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.shop.read.cart.CartReadDao;
import com.ejavashop.dao.shop.read.operate.ProductLabelReadDao;
import com.ejavashop.dao.shop.read.operate.ProductPackageReadDao;
import com.ejavashop.dao.shop.read.order.OrdersReadDao;
import com.ejavashop.dao.shop.read.product.ProductAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductPriceReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.cart.CartWriteDao;
import com.ejavashop.dao.shop.write.operate.ProductLabelWriteDao;
import com.ejavashop.dao.shop.write.operate.ProductPackageWriteDao;
import com.ejavashop.dao.shop.write.product.ProductAttrWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.product.ProductPriceWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dto.OrdersReturnDto;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.promotion.full.ActFull;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.model.seller.SellerTransportModel;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.cart.CartListVO;

@Component(value = "cartModel")
public class CartModel {

    @Resource
    private CartReadDao            cartReadDao;
    @Resource
    private CartWriteDao           cartWriteDao;
    @Resource
    private SellerReadDao          sellerReadDao;
    @Resource
    private ProductReadDao         productReadDao;
    @Resource
    private ProductGoodsReadDao    productGoodsReadDao;
    @Resource
    private ProductGoodsWriteDao   productGoodsWriteDao;
    @Resource
    private SellerTransportModel   sellerTransportModel;
    @Resource
    private ActSingleReadDao       actSingleReadDao;
    @Resource
    private ActFullReadDao         actFullReadDao;
    @Resource
    private ProductPackageWriteDao productPackageWriteDao;
    @Resource
    private ProductPackageReadDao productPackageReadDao;
    @Resource
    private ProductPriceWriteDao   productPriceWriteDao;
    @Resource
    private ProductPriceReadDao productPriceReadDao;
    @Resource
    private ProductWriteDao        productWriteDao;
    @Resource
    private ProductAttrReadDao     productAttrReadDao;
    @Resource
    private ProductAttrWriteDao    productAttrWriteDao;
    @Resource
    private ProductLabelWriteDao   productLabelWriteDao;
    @Resource
    private ProductLabelReadDao productLabelReadDao;
    @Resource
    private OrdersReadDao          ordersReadDao;
    
    /**
     * 添加商品到购物车
     * @param cart
     * @return
     */
    public boolean addToCart(Cart cart) {

        int result = 0;
        // 参数校验
        if (cart == null) {
            throw new BusinessException("进货单信息不能为空，请重试！");
        } else if (cart.getProductId() == null || cart.getProductId() == 0
                   || cart.getProductGoodsId() == null || cart.getProductGoodsId() == 0) {
            throw new BusinessException("请先选择商品后重试！");
        }
        Integer stockNums = 10;
        try {
            ProductAttr p_Attr = productAttrReadDao.getByProductIdAndName(cart.getProductId(),"包装规格");
            String[] attr_n = p_Attr.getValue().split("双");
            stockNums = Integer.valueOf(attr_n[0]);
        } catch (Exception e) {
        }
        if (cart.getCount() == null || cart.getCount() < stockNums) {
            cart.setCount(stockNums);
        }

        ProductGoods productGoods = productGoodsReadDao.get(cart.getProductGoodsId());
        if (productGoods == null) {
            throw new BusinessException("对不起，该商品型号已下架！");
        }

        // 判断购物车是否已有该商品，如果存在产品个数累加
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("q_productGoodsId", cart.getProductGoodsId());
        queryMap.put("q_memberId", cart.getMemberId());
        queryMap.put("q_productPackageId", cart.getProductPackageId());
        queryMap.put("q_isSelfLabel", cart.getIsSelfLabel() == null ? 0 : cart.getIsSelfLabel());
        queryMap.put("q_isDabiaowa", cart.getIsDabiaowa());
        List<Cart> cartList = cartReadDao.queryList(queryMap);
        if (cartList.size() > 1) {
            throw new BusinessException("进货单中同一产品有多条数据！");
        } else if (cartList.size() == 1) {
            Product product = new Product();
            product = productReadDao.get(cart.getProductId());
            if (product.getUpTime().getTime() - new Date().getTime() > 0) {
                String upTime = TimeUtil.getDateTimeString(product.getUpTime());
                throw new BusinessException("该商品将予 "+ upTime +" 开售！");
            }
            int full_container_quantity = product.getFullContainerQuantity() == null ? 0 : product
                .getFullContainerQuantity();
            Cart bean = cartList.get(0);
            int count = bean.getCount() + cart.getCount();
            // 判断库存
            if (count > productGoods.getProductStock()) {
                throw new BusinessException("您的进货单已经购买了所有此商品！");
            }
            int z_total = 0;
            if (product.getPriceStatus() == 3) {//对应整箱价的数量
                z_total = (count / full_container_quantity) * full_container_quantity;
            }
            result = cartWriteDao.updateCount(bean.getId(), cart.getCount(), z_total);
        } else {
            // 判断库存
            if (cart.getCount() > productGoods.getProductStock()) {
                throw new BusinessException("对不起，库存不足！");
            }
            cart.setIsSelfLabel(cart.getIsSelfLabel() == null ? 0 : cart.getIsSelfLabel());
            Product product = new Product();
            product = productReadDao.get(cart.getProductId());
            if (product.getUpTime().getTime() - new Date().getTime() > 0) {
                String upTime = TimeUtil.getDateTimeString(product.getUpTime());
                throw new BusinessException("该商品将予 "+ upTime +" 开售！");
            }
            int full_container_quantity = product.getFullContainerQuantity() == null ? 0 : product
                .getFullContainerQuantity();
            int z_total = 0;
            if (product.getPriceStatus() == 3) {//对应整箱价的数量
                z_total = (cart.getCount() / full_container_quantity) * full_container_quantity;
            }
            cart.setFullContainerCount(z_total);
            result = cartWriteDao.save(cart);
        }

        return result > 0;
    }

    /**
     * 根据登录用户取得购物车信息，所有数据都从写库获取
     * @param memberId
     * @param memberAddress 用户选择的地址信息，用于计算运费，如果是null表示不计算运费
     * @param source 来源，1、pc；2、H5；3、Android；4、IOS；
     * @param useType 使用类型：1、购物车用（取所有购物车记录），2、订单结算用（取用户勾选的记录）
     * @param transportType 
     * @return
     */
    public CartInfoVO getCartInfoByMId(Integer memberId, MemberAddress memberAddress, Integer source, Integer useType, Integer transportType) {

        // 默认使用类型是购物车使用
        if (useType == null) {
            useType = 1;
        }
        //取用户购物车
        List<Cart> cartList = cartReadDao.getByMemberId(memberId, useType);

        // 商家Map，记录货品所属的商家
        Map<Integer, Seller> sellerMap = new HashMap<Integer, Seller>();
        // 商家满减活动Map，记录商家当前的满减活动
        Map<Integer, ActFull> actFullMap = new HashMap<Integer, ActFull>();
        // 商家货品list Map，记录商家下得所有cart对象
        Map<Integer, List<Cart>> sellerCartMap = new HashMap<Integer, List<Cart>>();
        // 商家所有货品金额小计，记录属于该商家的货品金额
        Map<Integer, BigDecimal> sellerAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 所有 商品优惠金额小计（所有立减金额）
        Map<Integer, BigDecimal> sellerDiscountedMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品优惠金额小计（满减+所有立减金额）
        Map<Integer, BigDecimal> sellerCheckedDiscountedMap = new HashMap<Integer, BigDecimal>();
        // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
        Map<Integer, BigDecimal> sellerDiscountedAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
        Map<Integer, BigDecimal> sellerCheckedAmountMap = new HashMap<Integer, BigDecimal>();
        // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
        Map<Integer, BigDecimal> sellerCheckedDiscountedAmountMap = new HashMap<Integer, BigDecimal>();

        // 商品Map，记录货品所属的商品（有多个货品属于同一个商品时可以减少数据库查询次数）
        Map<Integer, Product> productMap = new HashMap<Integer, Product>();
        // 商家商品数量Map，用于记录各个商家商品的重量，计算运费（只记录选中商品的数量）
        Map<Integer, Object> countMap = new HashMap<Integer, Object>();
        // 商家单品立减活动Map，用于记录各个商家商品的立减活动（主要是同一个商品的多个货品），key为商品ID
        Map<Integer, ActSingle> actSingleMap = new HashMap<Integer, ActSingle>();

        // 购物车总金额
        BigDecimal cartAmount = BigDecimal.ZERO;
        // 购物车总数量
        int totalNumber = 0;
        // 购物车选中总金额
        BigDecimal checkedCartAmount = BigDecimal.ZERO;
        // 购物车选中数量
        int totalCheckedNumber = 0;
        // 购物车选中商品优惠后合计
        BigDecimal checkedDiscountedCartAmount = BigDecimal.ZERO;
        // 所有商品优惠金额合计
        BigDecimal discountAmount = BigDecimal.ZERO;
        // 选中商品优惠合计
        BigDecimal checkedDiscountedAmount = BigDecimal.ZERO;
        //套餐费用
        BigDecimal packageFeeTotal = BigDecimal.ZERO;
        //辅料费用
        BigDecimal labelFeeTotal = BigDecimal.ZERO;
        //此处为1分钱支付保留商品id，用以替换邮费计算后的结果
        Integer productId = 0;
        // 根据来源判断渠道，默认渠道为PC
        int channel = ConstantsEJS.CHANNEL_2;
        if (source != null
            && (source.equals(ConstantsEJS.SOURCE_2_H5)
                || source.equals(ConstantsEJS.SOURCE_3_ANDROID) || source
                    .equals(ConstantsEJS.SOURCE_4_IOS))) {
            channel = ConstantsEJS.CHANNEL_3;
        }

        for (Cart cart : cartList) {
            BigDecimal currentPackageFee = BigDecimal.ZERO;
            BigDecimal currentLabelFee = BigDecimal.ZERO;
            // 查询货品，每一条购物车记录都要查询，购物车中货品不重复
            ProductGoods productGoods = productGoodsReadDao.get(cart.getProductGoodsId());
            
            Product p = productReadDao.get(productGoods.getProductId());
            // 本条购物车记录的金额小计
            // 根据来源确定使用PC价格或者移动端价格，默认使用PC端价格
            BigDecimal price = productGoods.getMallPcPrice();
            BigDecimal currAmount = new BigDecimal(0);
            String formula = "";//针对整箱价保存一条计算公式，供用户查看
            BigDecimal p_price = new BigDecimal(0);
            if( cart!=null && cart.getIsDabiaowa()!=null && cart.getIsDabiaowa()== 1){//p!=null && p.getDabiaowaFlag() !=null && p.getDabiaowaFlag() == 1
            	if (p != null && (p.getPrice2Status() == 1 || p.getPrice2Status() == null)) {//一口价
            		ProductPrice pc = productPriceReadDao.get(p.getPrice2Pid());
            		if (source != null
            				&& (source.equals(ConstantsEJS.SOURCE_2_H5)
            						|| source.equals(ConstantsEJS.SOURCE_1_PC)
            						|| source.equals(ConstantsEJS.SOURCE_3_ANDROID) || source
            						.equals(ConstantsEJS.SOURCE_4_IOS))) {
            			price = pc.getPriceOnly();
            			cart.setTempdabiaoPriceOnly(price);
            		}
            		currAmount = price.multiply(new BigDecimal(cart.getCount()));
            	}
            	if (p != null && p.getPrice2Status() == 2) {//阶梯价
            		int count_sku = cart.getCount();
            		ProductPrice pc = productPriceReadDao.get(p.getPrice2Pid());
            		Integer jieTiCount = cartReadDao.getCountByMemberId(memberId,p.getId(),null);
            		int count = jieTiCount;
            		int prc1 = pc.getPrice1S();
            		int prc2 = pc.getPrice1E();
            		int prc3 = pc.getPrice2S();
            		int prc4 = pc.getPrice2E();
            		int prc5 = pc.getPrice3S();
            		if (count >= prc1 && count <= prc2) {
            			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice1());
            			p_price = pc.getPrice1();
            		}
            		if (count >= prc3 && count <= prc4) {
            			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice2());
            			p_price = pc.getPrice2();
            		}
            		if (count >= prc5) {
            			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice3());
            			p_price = pc.getPrice3();
            		}
            		price = p_price;
            		cart.setTempdabiaoPriceOnly(price);
            	}
            	formula = "￥" + price + "×" + cart.getCount();
            }else{
            	if (p != null && (p.getPriceStatus() == 1 || p.getPriceStatus() == null)) {//一口价
            		if (source != null
            				&& (source.equals(ConstantsEJS.SOURCE_2_H5)
            						|| source.equals(ConstantsEJS.SOURCE_3_ANDROID) || source
            						.equals(ConstantsEJS.SOURCE_4_IOS))) {
            			price = productGoods.getMallMobilePrice();
            		}
            		currAmount = price.multiply(new BigDecimal(cart.getCount()));
            	}
            	if (p != null && p.getPriceStatus() == 2) {//阶梯价
            		int count_sku = cart.getCount();
            		ProductPrice pc = productPriceReadDao.get(p.getPricePid());
            		if (pc != null) {
                		Integer jieTiCount = cartReadDao.getCountByMemberId(memberId,p.getId(),null);
                		int count = jieTiCount;
                		int prc1 = pc.getPrice1S();
                		int prc2 = pc.getPrice1E();
                		int prc3 = pc.getPrice2S();
                		int prc4 = pc.getPrice2E();
                		int prc5 = pc.getPrice3S();
                		if (count >= prc1 && count <= prc2) {
                			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice1());
                			p_price = pc.getPrice1();
                		}
                		if (count >= prc3 && count <= prc4) {
                			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice2());
                			p_price = pc.getPrice2();
                		}
                		if (count >= prc5) {
                			currAmount = new BigDecimal(count_sku).multiply(pc.getPrice3());
                			p_price = pc.getPrice3();
                		}
                		price = p_price;
                		//赋值商品表的价格字段，避免页面上价格存在误差
                		productGoods.setMallPcPrice(price);
            		}
            	}
            	formula = "￥" + price + "×" + cart.getCount();
            	//整箱价非二次包装订单
            	if (p != null && p.getPriceStatus() == 3 && (StringUtil.isNullOrZero(cart.getProductPackageId()) || "".equals(cart.getProductPackageId()))) {//整箱价
            		int count = cart.getCount();
            		int fullContainerQuantity = p.getFullContainerQuantity();
            		double f = count / fullContainerQuantity * p.getMallPcPrice().doubleValue()
            				* fullContainerQuantity;
            		double h = count % fullContainerQuantity * p.getScatteredPrice().doubleValue();
            		formula = "￥" + p.getMallPcPrice() + "×" + (count / fullContainerQuantity)
            				* fullContainerQuantity;
            		if (h >= 0.01) {
            			formula = formula + " + ￥" + p.getScatteredPrice() + "×"
            					+ (count % fullContainerQuantity);
            		}
            		currAmount = new BigDecimal(f + h);
            	}
            	//整箱价二次加工订单，单价按整价处理
            	if (p != null && p.getPriceStatus() == 3 && !StringUtil.isNullOrZero(cart.getProductPackageId())){
            		price = p.getMallPcPrice();
            		currAmount = price.multiply(new BigDecimal(cart.getCount()));
            		formula = "￥" + p.getMallPcPrice() + "×" + cart.getCount();
            	}
            }
            //整箱价非二次包装订单
            if (p != null && p.getPriceStatus() == 3 && (StringUtil.isNullOrZero(cart.getProductPackageId()) || "".equals(cart.getProductPackageId()))) {//整箱价
                int count = cart.getCount();
                int fullContainerQuantity = p.getFullContainerQuantity();
                double f = count / fullContainerQuantity * p.getMallPcPrice().doubleValue()
                           * fullContainerQuantity;
                double h = count % fullContainerQuantity * p.getScatteredPrice().doubleValue();
                formula = "￥" + p.getMallPcPrice() + "×" + (count / fullContainerQuantity)
                          * fullContainerQuantity;
                if (h >= 0.01) {
                    formula = formula + " + ￥" + p.getScatteredPrice() + "×"
                              + (count % fullContainerQuantity);
                }
                currAmount = new BigDecimal(f + h);
            }
            //整箱价二次加工订单，单价按整价处理
            if (p != null && p.getPriceStatus() == 3 && !StringUtil.isNullOrZero(cart.getProductPackageId())){
                price = p.getMallPcPrice();
                currAmount = price.multiply(new BigDecimal(cart.getCount()));
                formula = "￥" + p.getMallPcPrice() + "×" + cart.getCount();
            }
            //借用normName字段存储对应的计算过程
            productGoods.setNormName(formula);
            cart.setProductGoods(productGoods);
            Integer stockNums = 10;//默认规格10双每包
            try {
                ProductAttr p_Attr = productAttrReadDao.getWeight(p.getId(), "包装规格");
                if (p_Attr != null && !"".equals(p_Attr.getValue())){
                    String[] attr_n = p_Attr.getValue().split("双");
                    stockNums = Integer.valueOf(attr_n[0]);
                }
            } catch (Exception e) {
            }
            cart.setStockNums(stockNums);
            // 四舍五入，保留两位小数点
            currAmount = currAmount.setScale(2, BigDecimal.ROUND_HALF_UP);

            //如果套餐不为空，则计算套餐
            ProductPackage proPackage = null;
            if (!StringUtil.isNullOrZero(cart.getProductPackageId())) {
            	proPackage = productPackageReadDao.get(cart.getProductPackageId());
                if (proPackage != null) {
                    //套餐名称
                    cart.setProductPackageName(proPackage.getName());
                    StringBuffer sb = new StringBuffer();
                    if (!StringUtil.isNullOrZero(proPackage.getIsHook())) {
                        sb.append("钩子").append("、");
                    }
                    if (!StringUtil.isNullOrZero(proPackage.getIsGlue())) {
                        sb.append("不干胶").append("、");
                    }
                    if (!StringUtil.isNullOrZero(proPackage.getIsLiner())) {
                        sb.append("衬板").append("、");
                    }
                    if (!StringUtil.isNullOrZero(proPackage.getIsBag())) {
                        sb.append("中包袋").append("、");
                    }
                    if (!StringUtil.isNullOrZero(proPackage.getIsLabel())) {
                        sb.append("防伪标").append("、");
                    }
                    if (!StringUtil.isNullOrZero(proPackage.getIsGirdle())) {
                        sb.append("腰封").append("、");
                    }

                    if (sb.length() > 0) {
                        cart.setPackOther(sb.substring(0, sb.length() - 1));
                    }
                    cart.setPackDescribe(proPackage.getDescribe());

                    //当前套餐单价
                    cart.setPackageUnitFee(proPackage.getPrice());
                    //套餐费总价 ＝ 货品套餐费*数量 
                    BigDecimal packTotal = proPackage.getPrice().multiply(
                        new BigDecimal(cart.getCount()));
                    cart.setPackageFee(packTotal);
                    //套餐费用
                    packageFeeTotal = packageFeeTotal.add(packTotal);
                    //购物车当前价格+套餐价格
                    //                    currAmount = currAmount.add(packTotal);
                    currentPackageFee = packTotal;
                }
            }
            //add by tongzhaomei
            if(!StringUtil.isNullOrZero(cart.getIsDabiaowa()) && cart.getIsDabiaowa() == 1){
            	//如果是打标裸，则要计算辅料费用
            	Integer pLabelId = Integer.valueOf(p.getSkuOther());
            	if(pLabelId != null){
            		ProductLabel productLabel = productLabelReadDao.get(pLabelId);
            		if(productLabel!=null){
            			//二次加工中的包装单位来计算打标真实数量  add by tongzhaomei
                        Integer unitType = proPackage.getUnitType();
                        Double nowNumber = Math.ceil(cart.getCount() / unitType);
            			//当前辅料单价
            			cart.setLabelUnitFee(productLabel.getMarketPrice());
            			//辅料费用总价 = 货品辅料费 * 数量 
            			BigDecimal labelTotal = productLabel.getMarketPrice().multiply(new BigDecimal(nowNumber.intValue()));
            			cart.setLabelFee(labelTotal);
            			//辅料费用
            			labelFeeTotal = labelFeeTotal.add(labelTotal);
            			//购物车当前价格 + 辅料价格
            			currentLabelFee = labelTotal;
            		}
            	}
            }
            cart.setCurrAmount(currAmount);

            // 当前购物车商品优惠的金额和（立减金额*数量）
            BigDecimal currDiscounted = BigDecimal.ZERO;
            // 当前购物车商品优惠后的价格和（（单价-立减金额）*数量）
            BigDecimal currDiscountedAmount = BigDecimal.ZERO;

            String productIdStr = "," + cart.getProductId() + ",";
            // 商品单品立减活动信息
            ActSingle actSingle = null;
            if (actSingleMap.get(cart.getProductId()) == null) {
                actSingle = actSingleReadDao.getEffectiveActSingle(cart.getSellerId(), channel,
                    productIdStr);
                actSingleMap.put(cart.getProductId(), actSingle);
            } else {
                actSingle = actSingleMap.get(cart.getProductId());
            }
            if (actSingle != null) {
                // 当前商品参加了单品立减活动，计算优惠金额
                // 如果立减活动是直接减金额
                if (actSingle.getType().intValue() == ActSingle.TYPE_1) {
                    // 优惠金额
                    currDiscounted = actSingle.getDiscount().multiply(
                        new BigDecimal(cart.getCount()));
                    currDiscounted = currDiscounted.setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(p!=null && p.getPriceStatus() == 3){
                        currDiscountedAmount = currAmount.subtract(currDiscounted);
                    }else{
                        // 优惠后价格
                        price = price.subtract(actSingle.getDiscount());
                        // 优惠后价格与0比较，如果小于0则价格设定成0
                        price = price.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : price;
                        currDiscountedAmount = price.multiply(new BigDecimal(cart.getCount()));
                        currDiscountedAmount = currDiscountedAmount.setScale(2,
                            BigDecimal.ROUND_HALF_UP);
                    }
                    cart.setActSingleType(2);
                } else if (actSingle.getType().intValue() == ActSingle.TYPE_2) {
                    // 如果活动是折扣类型
                    if(p!=null && p.getPriceStatus() == 3){
                        currDiscounted = currAmount.multiply(new BigDecimal(1).subtract(actSingle.getDiscount()));
                        currDiscountedAmount = currAmount.subtract(currDiscounted).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }else{
                        // 优惠后价格
                        price = price.multiply(actSingle.getDiscount());
                        // 优惠后价格与0比较，如果小于0则价格设定成0
                        price = price.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : price;
                        currDiscountedAmount = price.multiply(new BigDecimal(cart.getCount()));
                        currDiscountedAmount = currDiscountedAmount.setScale(2,
                            BigDecimal.ROUND_HALF_UP);
                        currDiscounted = currAmount.subtract(currDiscountedAmount);
                    }
                    cart.setActSingleType(1);
                }
                cart.setActSingle(actSingle);
            } else {
                // 当前商品没有参加单品立减活动，优惠金额设定
                // 优惠金额为0
                currDiscounted = BigDecimal.ZERO;
                // 优惠后价格和设定为原价和
                currDiscountedAmount = currAmount;
            }
            //优惠后金额和 = 优惠金额+套餐费
            currDiscountedAmount = currDiscountedAmount.add(currentPackageFee).add(currentLabelFee);
               
            cart.setCurrDiscounted(currDiscounted);
            cart.setCurrDiscountedAmount(currDiscountedAmount);

            // 购物车总金额
            cartAmount = cartAmount.add(currAmount);
            // 购物车总数量
            totalNumber += cart.getCount();
            // 所有商品优惠金额合计
            discountAmount = discountAmount.add(currDiscounted);
            if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                // 购物车选中总金额
                checkedCartAmount = checkedCartAmount.add(currAmount);
                // 购物车选中数量
                totalCheckedNumber += cart.getCount();
                // 购物车选中商品优惠后合计
                checkedDiscountedCartAmount = checkedDiscountedCartAmount.add(currDiscountedAmount);
                // 选中商品优惠合计
                checkedDiscountedAmount = checkedDiscountedAmount.add(currDiscounted);
            }

            // 商家是空，查询商家，如果不为空不查询
            if (sellerMap.get(cart.getSellerId()) == null) {
                // 查询商家
                Seller seller = sellerReadDao.get(cart.getSellerId());
                sellerMap.put(cart.getSellerId(), seller);

                // 查询商家当前的满减活动（只查询一次，记录以备用）
                ActFull actFull = actFullReadDao.getEffectiveActFull(cart.getSellerId(), channel);
                actFullMap.put(cart.getSellerId(), actFull);

                // 记录商家的购物车
                List<Cart> sellerCartList = new ArrayList<Cart>();
                sellerCartList.add(cart);
                sellerCartMap.put(cart.getSellerId(), sellerCartList);

                // 查询商品
                Product product = productReadDao.get(cart.getProductId());
                if (product != null && product.getPriceStatus() == 2) {
                    product.setMallPcPrice(p_price);
                }
                cart.setProduct(product);
                productMap.put(product.getId(), product);

                // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
                sellerAmountMap.put(cart.getSellerId(), currAmount);
                // 店铺 所有 商品优惠金额小计（所有立减金额）
                sellerDiscountedMap.put(cart.getSellerId(), currDiscounted);
                // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
                sellerDiscountedAmountMap.put(cart.getSellerId(), currDiscountedAmount);
                if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                    // 店铺 选中 商品优惠金额小计（满减+所有立减金额），此处只记录了立减金额，满减金额在最后统一计算
                    sellerCheckedDiscountedMap.put(cart.getSellerId(), currDiscounted);
                    // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
                    sellerCheckedAmountMap.put(cart.getSellerId(), currAmount);
                    // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，此处只记录了立减金额，满减金额在最后统一计算
                    sellerCheckedDiscountedAmountMap.put(cart.getSellerId(), currDiscountedAmount);
                    productId = cart.getProductId();
                    // 记录商家商品的数量，只记录选中的商品数量，用于计算运费
                    double weight = 45.00;//设定预定值，避免商品属性表中毛重的值为空类型转换异常
                    try {
                        ProductAttr p_Attr = productAttrReadDao.getWeight(productId, "克重（净重）");
                        if (p_Attr != null && !"".equals(p_Attr.getValue())
                            && !"/".equals(p_Attr.getValue())) {
                            weight = Double.valueOf(p_Attr.getValue()) * 1.2;//获得净重后多加20%算作毛重计算邮费
                        }
                    } catch (Exception e) {
                        weight = 45.00;
                    }
                    
                    countMap.put(cart.getSellerId(), weight * cart.getCount());
                    //                  countMap.put(cart.getSellerId(), cart.getCount());
                }

            } else {
                // 如果商家已经存在

                // 记录商家的购物车
                List<Cart> sellerCartList = sellerCartMap.get(cart.getSellerId());
                sellerCartList.add(cart);
                sellerCartMap.put(cart.getSellerId(), sellerCartList);

                // 查询商品
                if (productMap.get(cart.getProductId()) == null) {
                    Product product = productReadDao.get(cart.getProductId());
                    if (product != null && product.getPriceStatus() == 2) {
                        product.setMallPcPrice(p_price);
                    }
                    product.setDescription(formula);
                    cart.setProduct(product);
                    productMap.put(product.getId(), product);
                } else {
                    cart.setProduct(productMap.get(cart.getProductId()));
                }

                // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
                sellerAmountMap.put(cart.getSellerId(), sellerAmountMap.get(cart.getSellerId())
                    .add(currAmount));
                // 店铺 所有 商品优惠金额小计（所有立减金额）
                sellerDiscountedMap.put(cart.getSellerId(),
                    sellerDiscountedMap.get(cart.getSellerId()).add(currDiscounted));
                // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小
                sellerDiscountedAmountMap.put(cart.getSellerId(),
                    sellerDiscountedAmountMap.get(cart.getSellerId()).add(currDiscountedAmount));
                if (cart.getChecked().intValue() == Cart.CHECKED_1) {
                    // 店铺 选中 商品优惠金额小计（满减+所有立减金额），此处只记录了立减金额，满减金额在最后统一计算
                    if (sellerCheckedDiscountedMap.get(cart.getSellerId()) == null) {
                        sellerCheckedDiscountedMap.put(cart.getSellerId(), currDiscounted);
                    } else {
                        sellerCheckedDiscountedMap.put(cart.getSellerId(),
                            sellerCheckedDiscountedMap.get(cart.getSellerId()).add(currDiscounted));
                    }

                    // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
                    if (sellerCheckedAmountMap.get(cart.getSellerId()) == null) {
                        sellerCheckedAmountMap.put(cart.getSellerId(), currAmount);
                    } else {
                        sellerCheckedAmountMap.put(cart.getSellerId(),
                            sellerCheckedAmountMap.get(cart.getSellerId()).add(currAmount));
                    }

                    // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，此处只记录了立减金额，满减金额在最后统一计算
                    if (sellerCheckedDiscountedAmountMap.get(cart.getSellerId()) == null) {
                        sellerCheckedDiscountedAmountMap.put(cart.getSellerId(),
                            currDiscountedAmount);
                    } else {
                        sellerCheckedDiscountedAmountMap.put(
                            cart.getSellerId(),
                            sellerCheckedDiscountedAmountMap.get(cart.getSellerId()).add(
                                currDiscountedAmount));
                    }
                    productId = cart.getProductId();
                    double weight = 45.00;//给定默认值，否则重量进行数据类型转换时会抛出异常
                    try {
                        ProductAttr p_Attr = productAttrReadDao.getWeight(productId, "克重（净重）");
                        if (p_Attr != null && !"".equals(p_Attr.getValue())
                            && !"/".equals(p_Attr.getValue())) {
                            weight = Double.valueOf(p_Attr.getValue()) * 1.2; //获得净重后多加20%算作毛重计算邮费
                        }
                    } catch (Exception e) {
                        weight = 45.00;
                    }
                    
                    // 记录商家商品的数量，只记录选中的商品数量，用于计算运费
                    if (countMap.get(cart.getSellerId()) == null) {

                        countMap.put(cart.getSellerId(), weight * cart.getCount());
                        //                      countMap.put(cart.getSellerId(), cart.getCount());
                    } else {
                        countMap.put(
                            cart.getSellerId(),
                            (Double.valueOf(countMap.get(cart.getSellerId()).toString()) + cart
                                .getCount() * weight));
                    }
                }
            }
        }

        List<CartListVO> listVOs = new ArrayList<CartListVO>();

        BigDecimal logisticsFeeAmount = BigDecimal.ZERO;
        Iterator<Integer> iterator = sellerMap.keySet().iterator();
        while (iterator.hasNext()) {
            Integer sellerId = iterator.next();
            CartListVO listVO = new CartListVO();
            listVO.setSeller(sellerMap.get(sellerId));
            listVO.setCartList(sellerCartMap.get(sellerId));
            BigDecimal fee = BigDecimal.ZERO;
            //            listVO.setSellerAmount(sellerAmountMap.get(sellerId));
            if (memberAddress != null && transportType != 5) {
                // 地址不为空则计算运费
                fee = sellerTransportModel.calculateTransFee(sellerId,
                    Double.valueOf(countMap.get(sellerId).toString()), memberAddress.getCityId(),
                    transportType);
                if (productId == 10) {
                    fee = new BigDecimal(0.00);
                }
                
                logisticsFeeAmount = logisticsFeeAmount.add(fee);
            }
            listVO.setSellerLogisticsFee(fee);
            // 店铺 所有 商品小计，商品价格*数量之和，没有减去优惠的小计
            listVO.setSellerAmount(sellerAmountMap.get(sellerId));
            // 店铺 所有 商品优惠金额小计（所有立减金额）
            listVO.setSellerDiscounted(sellerDiscountedMap.get(sellerId));
            // 店铺 所有 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（所有立减金额）的小计
            listVO.setSellerDiscountedAmount(sellerDiscountedAmountMap.get(sellerId));
            // 店铺 选中 商品金额小计，商品价格*数量之和，没有减去优惠的小计
            listVO.setSellerCheckedAmount(sellerCheckedAmountMap.get(sellerId));
            // 店铺 选中 商品优惠金额小计（满减+所有立减金额），如果满减活动存在，则计算满减
            BigDecimal sellerCheckedDiscounted = sellerCheckedDiscountedMap.get(sellerId);
            // 如果没有选中的商品则该值可能是空
            sellerCheckedDiscounted = sellerCheckedDiscounted == null ? BigDecimal.ZERO
                : sellerCheckedDiscounted;
            // 店铺 选中 商品优惠后小计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计，如果满减活动存在，则计算满减
            // 此时sellerCheckedDiscountedAmount是原价减去单品立减金额后的价格和
            // 满减优先级小于立减优先级
            BigDecimal sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmountMap
                .get(sellerId);
            // 如果没有选中的商品则该值可能是空
            sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmount == null ? BigDecimal.ZERO
                : sellerCheckedDiscountedAmount;
            // 当前商家的满减活动
            if (actFullMap.get(sellerId) != null) {
                ActFull actFull = actFullMap.get(sellerId);
                listVO.setActFull(actFull);

                // 订单满减的满额，从actfull的3个档次中取满额（根据选中商品金额计算得出）
                BigDecimal orderFull = null;
                // 订单满减的减免额，从actfull的3个档次中取减免额（根据选中商品金额计算得出）
                BigDecimal orderDiscount = null;

                // 根据店铺选中商品优惠后小计与活动满减等级的比较，得出订单满足的活动等级
                if (actFull.getThirdFull() != null
                    && actFull.getThirdFull().compareTo(BigDecimal.ZERO) > 0
                    && sellerCheckedDiscountedAmount.compareTo(actFull.getThirdFull()) >= 0) {
                    orderFull = actFull.getThirdFull();
                    orderDiscount = actFull.getThirdDiscount();
                } else if (actFull.getSecondFull() != null
                           && actFull.getSecondFull().compareTo(BigDecimal.ZERO) > 0
                           && sellerCheckedDiscountedAmount.compareTo(actFull.getSecondFull()) >= 0) {
                    orderFull = actFull.getSecondFull();
                    orderDiscount = actFull.getSecondDiscount();
                } else if (actFull.getFirstFull() != null
                           && actFull.getFirstFull().compareTo(BigDecimal.ZERO) > 0
                           && sellerCheckedDiscountedAmount.compareTo(actFull.getFirstFull()) >= 0) {
                    orderFull = actFull.getFirstFull();
                    orderDiscount = actFull.getFirstDiscount();
                }

                if (orderFull != null) {
                    // 优惠金额加上满减的优惠金额
                    sellerCheckedDiscounted = sellerCheckedDiscounted.add(orderDiscount);
                    // 优惠后订单合计减去优惠金额
                    sellerCheckedDiscountedAmount = sellerCheckedDiscountedAmount
                        .subtract(orderDiscount);

                    listVO.setOrderFull(orderFull);
                    listVO.setOrderDiscount(orderDiscount);

                    // 计算全部订单相关满减金额
                    // 购物车选中商品优惠后合计
                    checkedDiscountedCartAmount = checkedDiscountedCartAmount
                        .subtract(orderDiscount);
                    // 选中商品优惠合计
                    checkedDiscountedAmount = checkedDiscountedAmount.add(orderDiscount);
                }
            }
            listVO.setSellerCheckedDiscounted(sellerCheckedDiscounted);
            listVO.setSellerCheckedDiscountedAmount(sellerCheckedDiscountedAmount);
            listVOs.add(listVO);
        }

        CartInfoVO cartInfoVO = new CartInfoVO();
        cartInfoVO.setCartListVOs(listVOs);
        // 购物车所有商品合计，所有商品价格*数量之和，没有减去优惠的合计价格
        //ls 20160724 一分钱支付
        if (productId == 10) {
            cartInfoVO.setCartAmount(new BigDecimal(0.01));
            cartInfoVO.setTotalNumber(1);
            cartInfoVO.setTotalCheckedNumber(1);
            cartInfoVO.setLogisticsFeeAmount(new BigDecimal(0));
            // 所有商品优惠金额合计，减去优惠（所有立减金额）的合计
            cartInfoVO.setDiscountAmount(new BigDecimal(0));
            // 选中商品优惠合计，减去优惠（满减+所有立减金额）的合计
            cartInfoVO.setCheckedDiscountedAmount(new BigDecimal(0));
            // 选中商品合计，选中商品价格*数量之和，没有减去优惠的合计价格
            cartInfoVO.setCheckedCartAmount(new BigDecimal(0.01));
            //服务费
            cartInfoVO.setServicePrice(new BigDecimal(0));
            // 选中商品优惠后合计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
            cartInfoVO.setCheckedDiscountedCartAmount(new BigDecimal(0.01));
        } else {
            cartInfoVO.setCartAmount(cartAmount);
            cartInfoVO.setTotalNumber(totalNumber);
            cartInfoVO.setTotalCheckedNumber(totalCheckedNumber);
            //modify by ls 订单只产生一个邮费
            if(memberAddress != null && transportType != 5){
                
                Double weight_total = cartReadDao.getWeightByMemberId(memberId,null);
                BigDecimal calculateTransFee = sellerTransportModel.calculateTransFee(1,weight_total,memberAddress.getCityId(),transportType);
                cartInfoVO.setLogisticsFeeAmount(calculateTransFee);
            }else{
                cartInfoVO.setLogisticsFeeAmount(logisticsFeeAmount);
            }
            // 所有商品优惠金额合计，减去优惠（所有立减金额）的合计
            cartInfoVO.setDiscountAmount(discountAmount);
            // 选中商品优惠合计，减去优惠（满减+所有立减金额）的合计
            cartInfoVO.setCheckedDiscountedAmount(checkedDiscountedAmount);
            // 选中商品合计，选中商品价格*数量之和，没有减去优惠的合计价格
            cartInfoVO.setCheckedCartAmount(checkedCartAmount);
            //服务费
            cartInfoVO.setServicePrice(packageFeeTotal);
            //辅料费
            cartInfoVO.setLabelPrice(labelFeeTotal);
            // 选中商品优惠后合计，商品优惠后价格*数量之和，减去优惠（满减+所有立减金额）的小计
            cartInfoVO.setCheckedDiscountedCartAmount(checkedDiscountedCartAmount);
        }
        return cartInfoVO;
    }

    /**
     * 根据购物车id更新商城购物车，只更新商品数量
     * @param cart
     * @param request
     * @return
     */
    public boolean updateCartNumber(Integer id, Integer number) {
        //参数校验
        if (id == null || id == 0) {
            throw new BusinessException("进货单ID不能为空，请重试！");
        } else if (number == null || number < 1) {
            throw new BusinessException("进货单商品数量不能为空且不能小于0，请重试！");
        }
        Cart cart = new Cart();
        cart.setId(id);
        cart.setCount(number);
        cart.setChecked(1);
        cartWriteDao.update(cart);
        return true;
    }

    /**
     * 删除购物车商品
     * @param cart
     * @param request
     * @return
     */
    public boolean deleteCartByIds(List<Integer> ids) {

        //  参数校验
        if (ids == null || ids.size() == 0) {
            return true;
        }
        //批量删除购物车数据
        cartWriteDao.deleteByIds(ids);
        return true;
    }

    /**
    * 根据id取得商城购物车
    * @param  cartId
    * @return
    */
    public Cart getCartById(Integer cartId) {
        return cartReadDao.get(cartId);
    }

    /**
     * 定时任务清除7天之前添加的购物车数据
     * @return
     */
    public boolean jobClearCart() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        String clearTime = TimeUtil.getDateTimeString(calendar.getTime());
        cartWriteDao.jobClearCart(clearTime);
        return true;
    }

    /**
     * 根据用户ID获取用户购物车数量
     * @param memberId
     * @return
     */
    public Integer getCartNumberByMId(Integer memberId) {
        return cartReadDao.getCartNumberByMId(memberId);
    }

    /**
     * 根据用户ID、购物车ID修改购物车的选中状态，cartId为0时表示全部选中或不选中
     * @param memberId
     * @param cartId
     * @param checked
     * @return
     */
    public boolean updateChecked(Integer memberId, Integer cartId, Integer checked) {
        if (cartId != null && cartId > 0) {
            return cartWriteDao.updateChecked(memberId, cartId, checked) > 0;
        } else {
            return cartWriteDao.updateCheckedAll(memberId, checked) > 0;
        }
    }
    
    public boolean selectPackage(Integer memberId) {
    	return cartReadDao.selectPackage(memberId) > 0;
    }

    public List<Cart> queryList(Map<String, Object> queryMap) {
        return cartReadDao.queryList(queryMap);
    }

    public String checklimitations(Integer memberId, Integer productId, Integer productGoodsId,
                                            Integer number) {
        String message = "";
        List<ActSingle> actList = actSingleReadDao.getActSignalLimitiom();
        String limitiomstr = "";
        Product product;
        for(int i = 0 ; i<actList.size() ; i++){
            limitiomstr = actList.get(i).getProductIds();
            Integer tFlag;
            //该商品存在限购活动
            if(limitiomstr.contains("," + productId + ",")){
                product = productReadDao.get(productId);
                tFlag = 1;
                //查询该商品是否已经被购买过如果有返回商品数量
                Integer orders_count = ordersReadDao.getCountByProductGoodsId(memberId,productGoodsId,actList.get(i).getStartTime().toString(),actList.get(i).getEndTime().toString(),tFlag);
                if(orders_count > 0){
                    message = product.getProductCode()+"为限购商品，该商品存在您的未付款订单中，请先将订单进行付款后再进行提交";
                }
                //因限购的单位是手，此处需要查询该商品对应的一手数量是多少
                String productUnit = productAttrReadDao.getByProductIdAndName(productId, "包装规格").getValue();
                String unit = productUnit.split("双")[0];
                BigDecimal limit_num = actList.get(i).getDiscount().multiply(new BigDecimal(unit));
                if(limit_num.compareTo(new BigDecimal(number))<0){
                    message = product.getProductCode()+" 为限购商品,限购上限"+ limit_num.setScale(0) +"双，您本次购买的数量超过最大允许购买数量，请检查";
                }
                tFlag = 2;
                orders_count = ordersReadDao.getCountByProductGoodsId(memberId,productGoodsId,actList.get(i).getStartTime().toString(),actList.get(i).getEndTime().toString(),tFlag);
                if(limit_num.compareTo(new BigDecimal(orders_count))<0){
                    message = product.getProductCode()+" 为限购商品,限购上限"+ limit_num.setScale(0) +"双，您的累计的数量超过最大允许购买数量，请检查";
                }
            }
        }
        return message;
    }
}
