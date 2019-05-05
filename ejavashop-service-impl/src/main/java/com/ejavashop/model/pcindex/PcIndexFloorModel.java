package com.ejavashop.model.pcindex;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorClassReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorDataReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorPatchReadDao;
import com.ejavashop.dao.shopm.read.pcindex.PcIndexFloorReadDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorPatchWriteDao;
import com.ejavashop.dao.shopm.write.pcindex.PcIndexFloorWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorClass;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorData;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorPatch;

@Component(value = "pcIndexFloorModel")
public class PcIndexFloorModel {

    @Resource
    private PcIndexFloorReadDao      pcIndexFloorReadDao;
    @Resource
    private PcIndexFloorWriteDao     pcIndexFloorWriteDao;
    @Resource
    private PcIndexFloorClassReadDao pcIndexFloorClassReadDao;
    @Resource
    private PcIndexFloorDataReadDao  pcIndexFloorDataReadDao;
    @Resource
    private PcIndexFloorPatchReadDao pcIndexFloorPatchReadDao;
    @Resource
    private ProductReadDao           productReadDao;
    @Resource
    private ActSingleReadDao         actSingleReadDao;
    
    @Resource
    private PcIndexFloorPatchWriteDao pcIndexFloorPatchWriteDao;

    /**
     * 根据id取得PC端首页楼层
     * @param  pcIndexFloorId
     * @return
     */
    public PcIndexFloor getPcIndexFloorById(Integer pcIndexFloorId) {
        return pcIndexFloorReadDao.get(pcIndexFloorId);
    }

    /**
     * 保存PC端首页楼层
     * @param  pcIndexFloor
     * @return
     */
    public boolean savePcIndexFloor(PcIndexFloor pcIndexFloor) {
        return pcIndexFloorWriteDao.insert(pcIndexFloor) > 0;
    }

    /**
     * 更新PC端首页楼层
     * @param pcIndexFloor
     * @return
     */
    public boolean updatePcIndexFloor(PcIndexFloor pcIndexFloor) {
        return pcIndexFloorWriteDao.update(pcIndexFloor) > 0;
    }

    /**
     * 删除PC端首页楼层
     * @param pcIndexFloor
     * @return
     */
    public boolean deletePcIndexFloor(Integer pcIndexFloorId) {
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_floorId", pcIndexFloorId + "");
        Integer count = pcIndexFloorClassReadDao.getPcIndexFloorClasssCount(queryMap);
        if (count != null && count > 0) {
            throw new BusinessException("楼层下还有楼层分类，不能直接删除！");
        }

        Integer patchCount = pcIndexFloorPatchReadDao.getPcIndexFloorPatchsCount(queryMap);
        if (patchCount != null && patchCount > 0) {
            throw new BusinessException("楼层下还有楼层碎屑数据，不能直接删除！");
        }

        return pcIndexFloorWriteDao.delete(pcIndexFloorId) > 0;
    }

    public Integer getPcIndexFloorsCount(Map<String, String> queryMap) {
        return pcIndexFloorReadDao.getPcIndexFloorsCount(queryMap);
    }

    public List<PcIndexFloor> getPcIndexFloors(Map<String, String> queryMap, Integer start,
                                               Integer size) {
        return pcIndexFloorReadDao.getPcIndexFloors(queryMap, start, size);
    }

    /**
     * 取得PC端首页楼层<br>
     * <li>如果isPreview=true取使用和预使用状态的楼层
     * <li>如果isPreview=false取使用状态的楼层
     * 
     * @param isPreview
     * @return
     */
    public List<PcIndexFloor> getPcIndexFloorForView(Boolean isPreview) {
        Integer isPreviewInt = 0;
        if (isPreview != null && isPreview) {
            isPreviewInt = 1;
        }
        List<ActSingle> actSingles = actSingleReadDao.getSingleByState(5);
        List<PcIndexFloor> floorList = pcIndexFloorReadDao.getPcIndexFloorsForView(isPreviewInt);
        if (floorList != null && floorList.size() > 0) {
            for (PcIndexFloor floor : floorList) {
                // 封装楼层分类
                List<PcIndexFloorClass> classList = pcIndexFloorClassReadDao
                    .getPcIndexFloorClasssForView(floor.getId(), isPreviewInt);
                floor.setClassList(classList);
                if (classList != null && classList.size() > 0) {
                    // 封装楼层分类数据
                    for (PcIndexFloorClass floorClass : classList) {
                        List<PcIndexFloorData> dataList = pcIndexFloorDataReadDao
                            .getPcIndexFloorDatasForView(floorClass.getId());
                        if (dataList != null) {
                            for (PcIndexFloorData data : dataList) {
                                if (data.getDataType().intValue() == PcIndexFloorData.DATA_TYPE_1) {
                                    Product product = productReadDao.get(data.getRefId());
                                    productSetMarket(actSingles,product);
                                    data.setProduct(product);
                                }
                            }
                        }
                        floorClass.setDataList(dataList);
                    }
                }
                // 封装楼层碎屑
                List<PcIndexFloorPatch> patchList = pcIndexFloorPatchReadDao
                    .getPcIndexFloorPatchsForView(floor.getId(), isPreviewInt);
                floor.setPatchList(patchList);
            }
        }
        return floorList;
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
//                System.out.println(">>>>>>>> " + pd.getId() +">>>>>>>> " + currentPrice +">>>>>>>> " + currentMobilePrice + "  --------- " + as.getProductIds() + "  ========= " + discount + " xx = " + (discount.equals(BigDecimal.ZERO)));
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