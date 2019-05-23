package com.phkj.model.seller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.phkj.core.StringUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.member.MemberReadDao;
import com.phkj.dao.shop.read.order.OrdersReadDao;
import com.phkj.dao.shop.read.seller.SellerReadDao;
import com.phkj.dao.shop.read.system.RegionsReadDao;
import com.phkj.dao.shop.write.member.MemberWriteDao;
import com.phkj.dao.shop.write.seller.SellerWriteDao;
import com.phkj.dao.shop.write.system.RegionsWriteDao;
import com.phkj.dto.CommentsDto;
import com.phkj.dto.OrderDayDto;
import com.phkj.entity.seller.Seller;
import com.phkj.entity.seller.SellerApply;
import com.phkj.entity.system.Regions;

@Component(value = "sellerModel")
public class SellerModel {

    private static Logger                 log = LogManager.getLogger(SellerModel.class);

    @Resource
    private SellerWriteDao                sellerWriteDao;
    @Resource
    private SellerReadDao                 sellerReadDao;
    @Resource
    private DataSourceTransactionManager  transactionManager;
    @Resource
    private MemberWriteDao                memberWriteDao;
    @Resource
    private MemberReadDao                memberReadDao;
    @Resource
    private RegionsWriteDao               regionsDao;
    @Resource
    private RegionsReadDao               regionsReadDao;
    @Resource
    private OrdersReadDao                 ordersReadDao;

    public Seller getSellerById(Integer sellerId) {

        return sellerReadDao.get(sellerId);
    }

    public Integer saveSeller(Seller seller) {
        return sellerWriteDao.save(seller);
    }

    public Integer updateSeller(Seller seller) {
        return sellerWriteDao.update(seller);
    }

    public Integer getSellersCount(Map<String, String> queryMap) {
        return sellerReadDao.getSellersCount(queryMap);
    }

    public List<Seller> getSellers(Map<String, String> queryMap, Integer start, Integer size) {
        List<Seller> list = sellerReadDao.getSellers(queryMap, start, size);
        for (Seller seller : list) {
            seller.setMemberName(memberReadDao.get(seller.getMemberId()).getName());
        }
        return list;
    }

    public Seller getSellerByMemberId(Integer memberId) {
        return sellerReadDao.getSellerByMemberId(memberId);
    }

    /**
     * 冻结商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    public Boolean freezeSeller(Integer sellerId) throws Exception {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 修改商家状态
            Integer row = sellerWriteDao.freezeSeller(sellerId, Seller.AUDIT_STATE_3_FREEZE);
            if (row == 0) {
                throw new BusinessException("冻结商家时失败！");
            }

            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 解冻商家，修改商家状态，修改商家下所有商品状态
     * @param sellerId
     * @return
     */
    public Boolean unFreezeSeller(Integer sellerId) throws Exception {

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 修改商家状态
            Integer row = sellerWriteDao.freezeSeller(sellerId, Seller.AUDIT_STATE_2_DONE);
            if (row == 0) {
                throw new BusinessException("解冻商家时失败！");
            }


            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 根据商家的用户ID，获取商家所在的地址（省市级）
     * @param memberId
     * @return
     */
    public String getSellerLocationByMId(Integer memberId) {
        //获得商家申请信息
        SellerApply sellerApply = new SellerApply();
        String location = "";
        if (sellerApply != null && !StringUtil.isEmpty(sellerApply.getCompanyProvince())
            && !StringUtil.isEmpty(sellerApply.getCompanyCity())) {
            Regions province = regionsReadDao.get(Integer.valueOf(sellerApply.getCompanyProvince()));

            Regions city = regionsReadDao.get(Integer.valueOf(sellerApply.getCompanyCity()));
            location = province.getRegionName() + city.getRegionName();
        }
        return location;
    }

    /**
     * 定时任务设定商家的评分，用户评论各项求平均值设置为商家各项的综合评分
     * @return
     */
    public boolean jobSetSellerScore() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_auditStatus", Seller.AUDIT_STATE_2_DONE + "");
        List<Seller> sellers = sellerReadDao.getSellers(queryMap, 0, 0);
        if (sellers != null && sellers.size() > 0) {
            for (Seller seller : sellers) {
                try {
                    CommentsDto commentsDto = new CommentsDto();
                    if (commentsDto != null && commentsDto.getNumber() != null
                        && commentsDto.getNumber() > 0) {
                        Seller sellerNew = new Seller();
                        sellerNew.setId(seller.getId());
                        BigDecimal scoreDescription = (new BigDecimal(commentsDto.getDescription()))
                            .divide((new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreDescription(scoreDescription.toString());

                        BigDecimal scoreService = (new BigDecimal(commentsDto.getServiceAttitude()))
                            .divide((new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreService(scoreService.toString());

                        BigDecimal scoreDeliverGoods = (new BigDecimal(
                            commentsDto.getProductSpeed())).divide(
                                (new BigDecimal(commentsDto.getNumber())), 1,
                                BigDecimal.ROUND_HALF_UP);
                        sellerNew.setScoreDeliverGoods(scoreDeliverGoods.toString());

                        Integer update = sellerWriteDao.update(sellerNew);
                        if (update == 0) {
                            throw new BusinessException("修改商家评分时失败：sellerId=" + seller.getId());
                        }
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }

            }
        }

        return true;
    }

    /**
     * 定时任务设定商家各项统计数据
     * @return
     */
    public boolean jobSellerStatistics() {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_auditStatus", Seller.AUDIT_STATE_2_DONE + "");
        List<Seller> sellers = sellerReadDao.getSellers(queryMap, 0, 0);
        if (sellers != null && sellers.size() > 0) {
            for (Seller seller : sellers) {
                try {
                    Seller sellerNew = new Seller();
                    sellerNew.setId(seller.getId());
                    // 商品数量
//                    Integer prdCount = productReadDao.getUpProductCountBySellerId(seller.getId());
//                    sellerNew.setProductNumber(prdCount);

                    // 店铺收藏
                    Integer countBySellerId = 1;
                    sellerNew.setCollectionNumber(countBySellerId);

                    // 店铺总销售金额、店铺完成订单量
                    OrderDayDto dto = ordersReadDao.getSumMoneyOrderBySellerId(seller.getId());
                    if (dto != null) {
                        BigDecimal moneyOrder = dto.getMoneyOrder() == null ? BigDecimal.ZERO
                            : dto.getMoneyOrder();
                        BigDecimal moneyBack = dto.getMoneyBack() == null ? BigDecimal.ZERO
                            : dto.getMoneyBack();
                        sellerNew.setSaleMoney(moneyOrder.subtract(moneyBack));
                        sellerNew.setOrderCountOver(dto.getCount());
                    }

                    // 店铺总订单量
                    Integer count = ordersReadDao.getCountBySellerId(seller.getId());
                    sellerNew.setOrderCount(count);

                    Integer update = sellerWriteDao.update(sellerNew);
                    if (update == 0) {
                        throw new BusinessException("统计商家数据时失败：sellerId=" + seller.getId());
                    }
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            }
        }
        return true;
    }

    /**
     * 根据名称取商家
     * @param name
     * @return
     */
    public List<Seller> getSellerByName(String name) {
        return sellerReadDao.getSellerByName(name);
    }
    
    public Boolean checkSellerCode(String sellerCode){
    	Seller seller = sellerReadDao.checkSellerCode(sellerCode);
    	if(seller == null){
    		return true;
    	}
    	return false;
    }

    /**
     * 根据店铺名称取商家
     * @param name
     * @return
     */
    public List<Seller> getSellerBySellerName(String sellerName) {
        return sellerReadDao.getSellerBySellerName(sellerName);
    }

    public List<Seller> getSellerListAll() {
        return sellerReadDao.getSellerListAll();
    }

}
