package com.phkj.service.impl.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.phkj.entity.product.StAppletProduct;
import com.phkj.model.product.StAppletProductModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.RandomUtil;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.order.StAppletOrders;
import com.phkj.entity.order.StAppletOrdersParam;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.entity.order.StAppletOrdersVO;
import com.phkj.entity.seller.StAppletSeller;
import com.phkj.model.order.StAppletOrdersModel;
import com.phkj.model.order.StAppletOrdersProductModel;
import com.phkj.model.seller.StAppletSellerModel;
import com.phkj.service.order.IStAppletOrdersService;

@Service(value = "stAppletOrdersService")
public class StAppletOrdersServiceImpl implements IStAppletOrdersService {
    private static Logger log = LogManager.getLogger(StAppletOrdersServiceImpl.class);

    @Resource
    private StAppletOrdersModel stAppletOrdersModel;

    @Resource
    private StAppletOrdersProductModel stAppletOrdersProductModel;

    @Resource
    private StAppletSellerModel sellerModel;

    @Resource
    private StAppletProductModel stAppletProductModel;

    /**
     * 根据id取得订单
     *
     * @param stAppletOrdersId
     * @return
     */
    @Override
    public ServiceResult<StAppletOrders> getStAppletOrdersById(Integer stAppletOrdersId) {
        ServiceResult<StAppletOrders> result = new ServiceResult<StAppletOrders>();
        try {
            result.setResult(stAppletOrdersModel.getStAppletOrdersById(stAppletOrdersId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][getStAppletOrdersById]根据id[" + stAppletOrdersId + "]取得订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][getStAppletOrdersById]根据id[" + stAppletOrdersId + "]取得订单时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存订单
     *
     * @param orders
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletOrders(List<StAppletOrdersParam> orders) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            Date date = new Date();
            StAppletOrders stAppletOrders = new StAppletOrders();
            stAppletOrders.setSellerId(orders.get(0).getSellerId());
            stAppletOrders.setMemberId(orders.get(0).getMemberId());
            stAppletOrders.setMemberName(orders.get(0).getMemberName());
            stAppletOrders.setMoneyProduct(orders.get(0).getMoneyProduct());
            stAppletOrders.setOrderState(1);
            stAppletOrders.setOrderSn(RandomUtil.getOrderSn());
            stAppletOrders.setOrderType(1);
            stAppletOrders.setDeleted(1);
            stAppletOrders.setCreateTime(date);
            stAppletOrders.setUpdateTime(date);
            stAppletOrders.setRemark(orders.get(0).getRemark());
            // 向订单明细表中插入数据
            List<StAppletOrdersProduct> list = new ArrayList<>();
            for (StAppletOrdersParam ordersParam : orders) {
                StAppletOrdersProduct product = new StAppletOrdersProduct();
                product.setOrdersSn(stAppletOrders.getOrderSn());
                product.setSellerId(ordersParam.getSellerId());
                product.setProductId(ordersParam.getProductId());
                product.setProductName(ordersParam.getProductName());
                product.setMoneyPrice(ordersParam.getMoneyPrice());
                product.setNumber(ordersParam.getNumber());
                product.setSpecInfo(ordersParam.getSpecInfo());
                product.setCreateTime(date);
                product.setUpdateTime(date);
                list.add(product);
                // TODO 生成订单时,库存数量随之减少
                changeProductStock(1, ordersParam.getProductId(), ordersParam.getNumber());
            }
            stAppletOrdersProductModel.batchInsertToOrdersProduct(list);
            result.setResult(stAppletOrdersModel.saveStAppletOrders(stAppletOrders));
            result.setMessage("ok");
            result.setCode("200");
            result.setSuccess(true);
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][saveStAppletOrders]保存订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][saveStAppletOrders]保存订单时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * create by: zl
     * description: 生成或取消订单时,商品库存随之改变
     * create time:
     *
     * @return
     * @Param: i
     * @Param: productId
     * @Param: number
     */
    private synchronized void changeProductStock(int type, Integer productId, Integer number) {
        // type为1表示生成订单 2表示取消订单
        StAppletProduct product = stAppletProductModel.getStAppletProductById(productId);
        StAppletProduct stAppletProduct = new StAppletProduct();
        if (type == 1) {
            stAppletProduct.setProductStock(product.getProductStock() - number >= 0 ? product.getProductStock() - number : 0);
        } else if (type == 2) {
            stAppletProduct.setProductStock(product.getProductStock() + number);
        }
        stAppletProduct.setId(productId);
        Integer i = stAppletProductModel.update(stAppletProduct);
        if (i == 0) {
            log.info("更新商品库存量失败, productId: " + productId);
        }
    }

    /**
     * 更新订单
     *
     * @param stAppletOrders
     * @return
     */
    @Override
    public ServiceResult<Integer> updateStAppletOrders(StAppletOrders stAppletOrders) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletOrdersModel.updateStAppletOrders(stAppletOrders));
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
            // 取消订单时商品库存数量对应变化
            if (stAppletOrders.getOrderState() == 6) {
                String orderSn = stAppletOrders.getOrderSn();
                List<StAppletOrdersProduct> productList = stAppletOrdersProductModel.getStAppletOrdersProductList(orderSn);
                if (productList != null && !productList.isEmpty()) {
                    for (StAppletOrdersProduct product : productList) {
                        changeProductStock(2, product.getProductId(), product.getNumber());
                    }
                }
            }
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][updateStAppletOrders]更新订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][updateStAppletOrders]更新订单时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletOrdersVO>> getStAppletOrdersList(Integer memberId, int pageNum, int pageSize) {
        ServiceResult<List<StAppletOrdersVO>> result = new ServiceResult<>();
        List<StAppletOrdersVO> list = new ArrayList<>();
        try {
            pageNum = (pageNum - 1) * pageSize;
            List<StAppletOrders> appletOrdersList = stAppletOrdersModel.getStAppletOrdersList(memberId, pageNum, pageSize);
            for (StAppletOrders appletOrders : appletOrdersList) {
                StAppletOrdersVO stAppletOrdersVO = new StAppletOrdersVO();
                BeanUtils.copyProperties(appletOrders, stAppletOrdersVO);
                // 获取第一件商品信息
                List<StAppletOrdersProduct> productList = stAppletOrdersProductModel.getStAppletOrdersProductList(appletOrders.getOrderSn());
                if (productList != null && !productList.isEmpty()) {
                    // 商品图片
                    String productSku = productList.get(0).getProductSku();
                    stAppletOrdersVO.setProductSku(productSku);
                    stAppletOrdersVO.setNumber(productList.get(0).getNumber());
                    stAppletOrdersVO.setMoneyPrice(productList.get(0).getMoneyPrice());
                    stAppletOrdersVO.setProductNum(productList.size());
                    stAppletOrdersVO.setProductName(productList.get(0).getProductName());
                    // 商品详情
                    String specInfo = productList.get(0).getSpecInfo();
                    stAppletOrdersVO.setSpecInfo(specInfo);
                }
                // 获取商家信息
                StAppletSeller seller = sellerModel.getSellerById(appletOrders.getSellerId());
                if (seller != null) {
                    String sellerName = seller.getSellerName();
                    String sellerLogo = seller.getSellerLogo();
                    stAppletOrdersVO.setSellerName(sellerName);
                    stAppletOrdersVO.setSellerLogo(sellerLogo);
                }
                list.add(stAppletOrdersVO);
            }

            result.setResult(list);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][getStAppletOrdersList]获取订单时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][getStAppletOrdersList]获取订单时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletOrdersVO>> detail(String orderSn) {
        ServiceResult<List<StAppletOrdersVO>> result = new ServiceResult<>();
        try {
            List<StAppletOrdersVO> list = new ArrayList<>();
            List<StAppletOrdersProduct> productList = stAppletOrdersProductModel.getStAppletOrdersProductList(orderSn);
            for (StAppletOrdersProduct product : productList) {
                StAppletOrdersVO stAppletOrdersVO = new StAppletOrdersVO();
                BeanUtils.copyProperties(product, stAppletOrdersVO);
                stAppletOrdersVO.setOrderSn(product.getOrdersSn());
                list.add(stAppletOrdersVO);
            }
            result.setResult(list);
            result.setSuccess(true);
            result.setCode("200");
            result.setMessage("ok");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletOrdersService][detail]获取订单详情时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletOrdersService][detail]获取订单详情时出现未知异常：",
                    e);
        }
        return result;
    }
}