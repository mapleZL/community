package com.ejavashop.model.mindex;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.mindex.MIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.read.mindex.MIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.mindex.MIndexFloorDataWriteDao;
import com.ejavashop.dao.shopm.write.mindex.MIndexFloorWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.shopm.MIndexFloor;
import com.ejavashop.entity.shopm.MIndexFloorData;

@Component(value = "mIndexFloorModel")
public class MIndexFloorModel {

    @Resource
    private MIndexFloorReadDao           mIndexFloorReadDao;
    @Resource
    private MIndexFloorWriteDao          mIndexFloorWriteDao;
    @Resource
    private MIndexFloorDataReadDao       mIndexFloorDataReadDao;
    @Resource
    private MIndexFloorDataWriteDao      mIndexFloorDataWriteDao;
    @Resource
    private ProductReadDao               productReadDao;
    @Resource
    private DataSourceTransactionManager mobileTransactionManager;
    @Resource
    private ActSingleReadDao         actSingleReadDao;
    
    /**
     * 根据id取得移动端首页楼层表
     * @param  mIndexFloorId
     * @return
     */
    public MIndexFloor getMIndexFloorById(Integer mIndexFloorId) {
        return mIndexFloorReadDao.get(mIndexFloorId);
    }

    /**
     * 保存移动端首页楼层表
     * @param  mIndexFloor
     * @return
     */
    public boolean saveMIndexFloor(MIndexFloor mIndexFloor) {
        return mIndexFloorWriteDao.insert(mIndexFloor) > 0;
    }

    /**
     * 更新移动端首页楼层表
     * @param mIndexFloor
     * @return
     */
    public boolean updateMIndexFloor(MIndexFloor mIndexFloor) {
        return mIndexFloorWriteDao.update(mIndexFloor) > 0;
    }

    /**
     * 删除移动端首页楼层表
     * @param mIndexFloor
     * @return
     */
    public boolean deleteMIndexFloor(Integer mIndexFloorId) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = mobileTransactionManager.getTransaction(def);
        try {
            // 删除楼层
            Integer delete = mIndexFloorWriteDao.delete(mIndexFloorId);
            // 删除楼层数据
            mIndexFloorDataWriteDao.deleteByFloorId(mIndexFloorId);
            mobileTransactionManager.commit(status);
            return delete > 0;
        } catch (Exception e) {
            mobileTransactionManager.rollback(status);
            throw e;
        }

    }

    public Integer getMIndexFloorsCount(Map<String, String> queryMap) {
        return mIndexFloorReadDao.getMIndexFloorsCount(queryMap);
    }

    public List<MIndexFloor> getMIndexFloors(Map<String, String> queryMap, Integer start,
                                             Integer size) {
        return mIndexFloorReadDao.getMIndexFloors(queryMap, start, size);
    }

    /**
     * 取得移动端首页楼层表<br>
     * <li>如果isPreview=true取所有楼层
     * <li>如果isPreview=false只取显示状态的楼层
     * <li>封装楼层对应的数据（MIndexFloorData）
     * 
     * @param isPreview
     * @return
     */
    public List<MIndexFloor> getMIndexFloorsWithData(Boolean isPreview) {
        String status = "";
        if (!isPreview) {
            status = MIndexFloor.STATUS_1 + "";
        }
        List<MIndexFloor> list = mIndexFloorReadDao.getMIndexFloorsForView(status);
        List<ActSingle> actSingles = actSingleReadDao.getSingleByState(5);
        for (MIndexFloor floor : list) {
            List<MIndexFloorData> dataList = mIndexFloorDataReadDao
                .getMIndexFloorDatasByFloorId(floor.getId());
            if (dataList != null && dataList.size() > 0) {
                for (MIndexFloorData data : dataList) {
                    if (data.getDataType() != null
                        && data.getDataType().equals(MIndexFloorData.DATA_TYPE_1)) {
                        Product product = productReadDao.get(data.getRefId());
                        productSetMarket(actSingles,product);
                        data.setProduct(productReadDao.get(data.getRefId()));
                    }
                }
            }
            floor.setDatas(dataList);
        }

        return list;
    }
    
    /**
     * 判断是否在单品立减中，赋值markrtPrice字段
     * @param actSingles
     * @param product
     */
    private void productSetMarket(List<ActSingle> actSingles, Product product) {
        for (ActSingle as : actSingles) {
            if (as.getProductIds().contains("," + product.getId() + ",")) {
                BigDecimal discount = as.getDiscount();
                if (discount != null && discount.compareTo(BigDecimal.ZERO) == 0) continue;
                BigDecimal currentPrice = product.getMallPcPrice();
                BigDecimal currentMobilePrice = product.getMalMobilePrice();
                product.setMarketPrice(currentPrice);
                if (as.getType() == ActSingle.TYPE_1) {
                    product.setMallPcPrice(currentPrice.subtract(discount));
                    product.setMalMobilePrice(currentMobilePrice.subtract(discount));
                }
                else if (as.getType() == ActSingle.TYPE_2) {
                    product.setMallPcPrice(currentPrice.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
                    product.setMalMobilePrice(currentMobilePrice.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                continue;
            }
        }
    }
}