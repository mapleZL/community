package com.ejavashop.model.operate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.member.MemberReadDao;
import com.ejavashop.dao.shop.read.operate.ProductLabelReadDao;
import com.ejavashop.dao.shop.write.operate.ProductLabelWriteDao;
import com.ejavashop.entity.operate.ProductLabel;

@Component
public class ProductLabelModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductLabelModel.class);

    @Resource
    private ProductLabelWriteDao           productLabelWriteDao;
    @Resource
    private ProductLabelReadDao           productLabelReadDao;
    @Resource
    private MemberReadDao                  memberReadDao;

    /**
     * 根据id取得标签管理
     * @param  productLabelId
     * @return
     */
    public ProductLabel getProductLabelById(Integer productLabelId) {
        ProductLabel label = productLabelReadDao.get(productLabelId);
        if (label.getMemberId().intValue() != 0) {
            label.setMemberName(memberReadDao.get(label.getMemberId()).getName());
        }
        return label;
    }

    /**
     * 保存标签管理
     * @param  productLabel
     * @return
     */
    public Integer saveProductLabel(ProductLabel productLabel) {
        this.dbConstrains(productLabel);
        return productLabelWriteDao.save(productLabel);
    }

    /**
    * 更新标签管理
    * @param  productLabel
    * @return
    */
    public Integer updateProductLabel(ProductLabel productLabel) {
        this.dbConstrains(productLabel);
        return productLabelWriteDao.update(productLabel);
    }

    private void dbConstrains(ProductLabel productLabel) {
        productLabel.setName(StringUtil.dbSafeString(productLabel.getName(), false, 200));
        productLabel.setSku(StringUtil.dbSafeString(productLabel.getSku(), false, 20));
        productLabel.setNorms(StringUtil.dbSafeString(productLabel.getNorms(), false, 50));
        productLabel.setNetWeight(StringUtil.dbSafeString(productLabel.getNetWeight(), false, 20));
        productLabel.setGrossWeight(StringUtil.dbSafeString(productLabel.getGrossWeight(), false,
            20));
        productLabel.setBrand(StringUtil.dbSafeString(productLabel.getBrand(), false, 50));
        productLabel.setDescribe(StringUtil.dbSafeString(productLabel.getDescribe(), false, 65535));
        productLabel.setImage(StringUtil.dbSafeString(productLabel.getImage(), true, 200));
        productLabel
            .setCreateName(StringUtil.dbSafeString(productLabel.getCreateName(), false, 50));
    }

    public List<ProductLabel> page(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        if (pager != null) {
            pager.setRowsCount(productLabelReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<ProductLabel> list = productLabelReadDao.page(param);
        for (ProductLabel label : list) {
            if (label.getMemberId().intValue() != 0) {
                label.setMemberName(memberReadDao.get(label.getMemberId()).getName());
            }
        }
        return list;
    }

    /**
     * 获取所有标签名称即辅料
     * @author 仝照美
     */
	public List<ProductLabel> getProductLabelName() {
		List<ProductLabel> list = productLabelReadDao.getProductLabelName();
		return list;
	}

	/**
     * 根据sku_other值，来动态获得对应的辅料name
     * @param queryMap
     * @param pager
     * @return
     */
	public List<ProductLabel> getProductLabelNameByskuother(String[] skuothers) {
		List<ProductLabel> list = productLabelReadDao.getProductLabelNameByskuother(skuothers);
		return list;
	}
}