package com.phkj.web.controller.cart;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.ServiceResult;
import com.phkj.entity.cart.StAppletCart;
import com.phkj.entity.product.StAppletProduct;
import com.phkj.service.cart.IStAppletCartService;
import com.phkj.service.product.IStAppletProductService;

/**
 * 购物车操作类
 *                       
 * @Filename: CartController.java
 * @Version: 1.0
 * @date: 2019年6月3日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping("/admin/cart")
public class CartController {

    @Autowired
    private IStAppletCartService    cartService;
    @Autowired
    private IStAppletProductService productService;

    private static final Logger     log = LogManager.getLogger(CartController.class);

    /**
     * 将商品加入购物车
     * @param cart
     * @param response
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Integer> addCart(@RequestBody StAppletCart cart,
                                                         HttpServletResponse response) {
        HttpJsonResult<Integer> result = new HttpJsonResult<>();
        try {
            cart.setCreateTime(new Date());
            ServiceResult<Integer> serviceResult = cartService.saveStAppletCart(cart);
            result.setData(serviceResult.getResult());
        } catch (Exception e) {
            log.error("加入购物车失败", e);
            result.setMessage("加入购物车失败");
        }
        return result;
    }

    /**
     * 修改购物车
     * @param id
     * @param num
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeCart", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> changeCart(Integer id, Integer num,
                                                            HttpServletResponse response) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<>();
        try {
            result.setData(cartService.changeCart(id, num).getResult());
        } catch (Exception e) {
            log.error("修改购物车数量失败，id=" + id + "数量：" + num, e);
            result.setMessage("修改数量失败");
        }
        return result;
    }

    /**
     * 删除购物车中的商品
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delCart(Integer id, HttpServletResponse response) {
        HttpJsonResult<Boolean> result = new HttpJsonResult<>();
        try {
            result.setData(cartService.delCartById(id));
        } catch (Exception e) {
            log.error("删除商品失败，id=" + id, e);
            result.setMessage("删除商品失败");
        }
        return result;
    }

    /**
     * 用户查询购物车列表失败
     * @param memberId
     * @param villageCode
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<StAppletCart>> list(Integer memberId,
                                                                 String villageCode, Integer start,
                                                                 Integer pageSize) {
        HttpJsonResult<List<StAppletCart>> result = new HttpJsonResult<>();
        try {
            ServiceResult<List<StAppletCart>> serviceResult = cartService.list(memberId,
                villageCode, start, pageSize);
            if (serviceResult.getSuccess()) {
                StAppletProduct product = null;
                // 展示时丰富字段
                for (StAppletCart stAppletCart : serviceResult.getResult()) {
                    product = productService.getStAppletProductById(stAppletCart.getProductId()).getResult();
                    stAppletCart.setProduct(product);
                }
                result.setData(serviceResult.getResult());
                result.setTotal(cartService.getCount(memberId, villageCode));
            }
        } catch (Exception e) {
            log.error("查询购物车失败", e);
            result.setMessage("查询购物车失败");
        }
        return result;
    }

}
