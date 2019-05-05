package com.ejavashop.web.controller.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.cart.Cart;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.service.product.IProductBuyStockService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductPriceService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.cart.CartListVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 购物流程-购物车<br>
 * 本controller中得请求都需要登录才能访问
 * 
 * @Filename: CartController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "cart")
public class CartController extends BaseController {

    @Resource
    private ICartService            cartService;
    @Resource
    private IProductGoodsService    productGoodsService;
    @Resource
    private IProductService         productService;
    @Resource
    private IProductBuyStockService productBuyStockService;
    @Resource
    private IProductPackageService productPackageService;
    @Resource
    private IProductPriceService productPriceService;
    /**
     * 添加物品到购物车
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/addtocart.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> addCart(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Map<String, Object> dataMap, Integer productId, Integer productGoodId,
                                                         Integer number) {

        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess() || productResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("添加购物车失败，获取商品信息失败！");
        }
        Product product = productResult.getResult();
        
        ProductGoods goods = null;
        //有问题  说不出来的感觉 怪怪的
       /* if (productGoodId == null || productGoodId == 0) {
            // 如果是货品是空，则可能是从列表页等不能选择货品的页面请求过来，随机取一个货品放入购物车
            ServiceResult<List<ProductGoods>> goodsResult = productGoodsService
                .getGoodSByProductId(productId);
            if (goodsResult.getSuccess() && goodsResult.getResult() != null
                && goodsResult.getResult().size() > 0) {
                goods = goodsResult.getResult().get(0);
            }
        } else {
            ServiceResult<ProductGoods> goodResult = productGoodsService
                .getProductGoodsById(productGoodId);
            goods = goodResult.getResult();
        }*/
        
        if(productGoodId == null){
        	return new HttpJsonResult<Boolean>("添加购物车失败，获取货品信息失败！");
        }
        
        ServiceResult<ProductGoods> goodResult = productGoodsService
                .getProductGoodsById(productGoodId);
        if(goodResult.getSuccess()){
        	goods = goodResult.getResult();
        }
        if (goods == null) {
        	return new HttpJsonResult<Boolean>("添加购物车失败，获取货品信息失败！");
        }
        Cart cart = new Cart();
        cart.setMemberId(member.getId());
        cart.setProductGoodsId(productGoodId);
        cart.setSpecId(goods.getNormAttrId());
        cart.setSpecInfo(goods.getNormName());
        cart.setCount(number);
        cart.setProductId(productId);
        cart.setSellerId(product.getSellerId());
        String dabiaowaFlag = request.getParameter("dabiaowaFlag");
        if(dabiaowaFlag != null && !"".equals(dabiaowaFlag) && Integer.valueOf(dabiaowaFlag) == 1){
        	//打标袜处理流程 
        	if(product.getTaocanId()!=null){
        		cart.setProductPackageId(product.getTaocanId());//关联套餐
        		cart.setIsSelfLabel(0);//设置为平台标
        		cart.setIsDabiaowa(1);//设置为打标袜
        	}
        }else{
        	cart.setProductPackageId(isNull(request.getParameter("productPackageId")) ? null : Integer
        			.valueOf(request.getParameter("productPackageId")));
        	cart.setIsSelfLabel(request.getParameter("isSelfLabel") == null ? 0 : Integer
        			.valueOf(request.getParameter("isSelfLabel")));
        	cart.setIsDabiaowa(0);//设置非打标袜
        }
        
        ServiceResult<Boolean> serviceResult = cartService.addToCart(cart);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到 我的购物车 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/detail.html", method = { RequestMethod.GET })
    public String toMyCart(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {

    	//套餐
        Map<String, String> packMap = new HashMap<String, String>();
        packMap.put("state", "1");
        ServiceResult<List<ProductPackage>> packsr = productPackageService.page(packMap, null);
        dataMap.put("productPackage", packsr.getResult());
    	
        Member member = WebFrontSession.getLoginedUser(request);

        //取购物车信息  产品价格 按照商家来区分
        //查询购物车
        ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(member.getId(),
            null, ConstantsEJS.SOURCE_1_PC, 1,1);
        CartInfoVO cartinfoVO = serviceResult.getResult();

        List<CartListVO> cartlistvo = cartinfoVO.getCartListVOs();
        for (CartListVO listvo : cartlistvo) {
            List<Cart> cartlist = listvo.getCartList();
            for (Cart cart : cartlist) {
                Product pro = cart.getProduct();
                ProductGoods pg = cart.getProductGoods();
                //----------------------最大购买数限制 bg----------------------//
                //最大购买数
                BigDecimal maxStock = new BigDecimal(pg.getProductStock());
                //如果货品库存到达阀值，则根据会员等级限制最大购买数
                Integer prostock = pg.getProductStock();
                //当前货品对应的库存限制
                Map<String, String> map = new HashMap<String, String>();
                map.put("productId", pro.getId() + "");
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
                pg.setMaxStock(maxStock.intValue());
                //----------------------最大购买数限制 ed----------------------//
            }
        }

        dataMap.put("cartInfoVO", cartinfoVO);

        return "front/cart/cart";
    }

    /**
     * 购物车页面ajax更新购物车列表
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/getcartinfo.html", method = { RequestMethod.POST })
    public String getCartInfo(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
    	//套餐
        Map<String, String> packMap = new HashMap<String, String>();
        packMap.put("state", "1");
        ServiceResult<List<ProductPackage>> packsr = productPackageService.page(packMap, null);
        dataMap.put("productPackage", packsr.getResult());
        
        Member member = WebFrontSession.getLoginedUser(request);
        //取购物车信息  产品价格 按照商家来区分
        //查询购物车
        ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(member.getId(),
            null, ConstantsEJS.SOURCE_1_PC, 1,1);

        CartInfoVO cartinfoVO = serviceResult.getResult();

        List<CartListVO> cartlistvo = cartinfoVO.getCartListVOs();
        for (CartListVO listvo : cartlistvo) {
            List<Cart> cartlist = listvo.getCartList();
            for (Cart cart : cartlist) {
                Product pro = cart.getProduct();
                ProductGoods pg = cart.getProductGoods();

                //----------------------最大购买数限制 bg----------------------//
                //最大购买数
                BigDecimal maxStock = new BigDecimal(pg.getProductStock());
                //如果货品库存到达阀值，则根据会员等级限制最大购买数
                Integer prostock = pg.getProductStock();

                //当前货品对应的库存限制
                Map<String, String> map = new HashMap<String, String>();
                map.put("productId", pro.getId() + "");
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
                pg.setMaxStock(maxStock.intValue());
                //----------------------最大购买数限制 ed----------------------//
            }
        }

        dataMap.put("cartInfoVO", serviceResult.getResult());

        return "front/cart/cart_list";
    }

    /**
     * 跳转到添加购物车成功页面
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/add.html", method = { RequestMethod.GET })
    public String toCartSuccess(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {
        return "front/cart/cartsuccess";
    }

    /**
     * 更新单条购物车购买数量
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/updateCartById.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> updateCartById(HttpServletRequest request,
                                                                HttpServletResponse response) {

        String cartIdStr = request.getParameter("id");
        Integer cartId = ConvertUtil.toInt(cartIdStr, 0);
        String countStr = request.getParameter("count");
        Integer count = ConvertUtil.toInt(countStr, 0);

        ServiceResult<Boolean> serviceResult = cartService.updateCartNumber(cartId, count);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 删除购物车数据
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteCartById.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteCartById(HttpServletRequest request,
                                                                HttpServletResponse response) {
        String[] idsStr = request.getParameterValues("id");
        if (idsStr == null || idsStr.length == 0) {
            return new HttpJsonResult<Boolean>("请选择商品后再点击删除！");
        }
        List<Integer> ids = new ArrayList<Integer>();
        for (String idStr : idsStr) {
            ids.add(ConvertUtil.toInt(idStr, 0));
        }
        ServiceResult<Boolean> serviceResult = cartService.deleteCartByIds(ids);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 单条购物车数据选中或不选中
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cartchecked.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cartChecked(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             @RequestParam(value = "id", required = true) Integer id,
                                                             @RequestParam(value = "checked", required = true) Integer checked) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = cartService.updateChecked(member.getId(), id,
            checked);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 购物车数据全选或不全选
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/cartcheckedall.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cartCheckedAll(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                @RequestParam(value = "checked", required = true) Integer checked) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = cartService
            .updateChecked(member.getId(), 0, checked);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "/checklimitations.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<String> checklimitations(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Map<String, Object> dataMap, Integer productId,Integer productGoodsId, Integer number) {
        Member member = WebFrontSession.getLoginedUser(request);
        HttpJsonResult<String> jsonResult = new HttpJsonResult<String>();
        ServiceResult<String> serviceResult = cartService.checklimitations(member.getId(),productId,productGoodsId,number);
        String eMessage = serviceResult.getResult();
        if(!eMessage.equals("")){
            jsonResult.setMessage(eMessage);
        }
        return jsonResult;
    }
    
   /**
    * 查询购物车选中的商品中是否有二次加工的
    * @param request
    * @param response
    * @return
    */
   @RequestMapping(value = "/selectpackage.html", method = { RequestMethod.GET })
   public @ResponseBody HttpJsonResult<Boolean> selectpackage(HttpServletRequest request,
                                                               HttpServletResponse response) {
       Member member = WebFrontSession.getLoginedUser(request);
       ServiceResult<Boolean> serviceResult = cartService.selectPackage(member.getId());

       HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
       if (!serviceResult.getSuccess()) {
           if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
               throw new RuntimeException(serviceResult.getMessage());
           } else {
               jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
               jsonResult.setData(false);
           }
       }
       if(serviceResult.getResult()){
    	   jsonResult.setData(true);
       }else{
    	   jsonResult.setData(false);
       }
       
       return jsonResult;
   }
}
