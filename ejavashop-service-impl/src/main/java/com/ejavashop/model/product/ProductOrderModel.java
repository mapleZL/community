package com.ejavashop.model.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductOrderReadDao;
import com.ejavashop.dao.shop.write.product.ProductOrderWriteDao;
import com.ejavashop.entity.product.ProductOrder;

@Component
public class ProductOrderModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductOrderModel.class);
    
    @Resource
    private ProductOrderWriteDao productOrderWriteDao;
    @Resource
    private ProductOrderReadDao productOrderReadDao;
    
    /**
     * 根据id取得楼层排序表
     * @param  productOrderId
     * @return
     */
    public ProductOrder getProductOrderById(Integer productOrderId) {
    	return productOrderReadDao.get(productOrderId);
    }
    
    /**
     * 保存楼层排序表
     * @param  productOrder
     * @return
     */
     public Integer saveProductOrder(ProductOrder productOrder) {
     	this.dbConstrains(productOrder);
     	return productOrderWriteDao.insert(productOrder);
     }
     
     /**
      * 将排序好的商品更新到数据库中
      * @param  data
      * @param  floorCode
      * @return
      */
     public String insertProductOrder(String ids, String sorts, String floorCode) {
    	 String sortType = floorCode;
    	 StringBuffer sb = new StringBuffer();
    	 if (!StringUtil.isEmpty(ids) && ids.length() > 0) {
    		 String[] selectedIds = ids.split(",");
    		 String[] selectedSorts = sorts.split(",");
				//从数据库中查询已经存在的商品ID
				Integer[] exsitProductIds = productOrderReadDao.selectProductId(sortType);
				ProductOrder productOrder = null ;
 				for (int i = 0; i < selectedIds.length; i ++) {
 					int selectedId = Integer.parseInt(selectedIds[i]);
 					int selectedSort = Integer.parseInt(selectedSorts[i]);
 					// 如果添加的商品已经在数据库中存在，则删除
 					for (int epId: exsitProductIds) {
 						if (selectedId == epId) 
 							productOrderWriteDao.deleteById(epId, sortType);
 					}
 					productOrder = new ProductOrder();
 					productOrder.setProductId(selectedId);
 					productOrder.setSort(selectedSort);
 					productOrder.setSortType(sortType);
 					productOrderWriteDao.insert(productOrder);
 				}
     	}else{
     		throw new BusinessException("请输入");
     	}
    	 if (sb.length() == 0)
    		 sb.append("success");
    	 return sb.toString();
      }
     
     /**
     * 更新楼层排序表
     * @param  productOrder
     * @return
     */
     public Integer updateProductOrder(ProductOrder productOrder) {
     	this.dbConstrains(productOrder);
     	return productOrderWriteDao.update(productOrder);
     }
     
     private void dbConstrains(ProductOrder productOrder) {
		productOrder.setSortType(StringUtil.dbSafeString(productOrder.getSortType(), false, 20));
		productOrder.setSortType(StringUtil.dbSafeString(productOrder.getSortType(), false, 20));
		productOrder.setSortType(StringUtil.dbSafeString(productOrder.getSortType(), false, 20));
     }
}