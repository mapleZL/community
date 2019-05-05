package com.ejavashop.dao.shop.write.operate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.operate.ProductLabel;

@Repository
public interface ProductLabelWriteDao {

    //ProductLabel get(java.lang.Integer id);

    Integer save(ProductLabel productLabel);

    Integer update(ProductLabel productLabel);

    /*Integer getCount(Map<String, Object> queryMap);

    List<ProductLabel> page(Map<String, Object> queryMap);*/

    Integer del(Integer id);
    
    /**
     * 获得所有辅料名称
     * @return
     * @author 仝照美
     */
	//List<ProductLabel> getProductLabelName();

	/**
     * 根据sku_other值，来动态获得对应的辅料name
     * @param queryMap
     * @param pager
     * @return
     */
	//List<ProductLabel> getProductLabelNameByskuother(String[] skuothers);
}