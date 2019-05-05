package com.ejavashop.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductSortReadDao;
import com.ejavashop.dao.shop.write.product.ProductSortWriteDao;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorProduct;

@Service(value="productSortModel")
public class ProductSortModel {
	
	@Resource
	private ProductSortReadDao productSortReadDao;
	@Resource
	private ProductSortWriteDao productSortWriteDao;
    @Resource
    private ProductReadDao productReadDao;
	
	public int getPcIndexFloorsCount(Map<String, String> queryMap) {
		return productSortReadDao.getPcIndexFloorsCount(queryMap);
	}
	public List<PcIndexFloorProduct> pageProduct(Map<String, String> queryMap, Integer start, Integer size) {
		List<PcIndexFloorProduct> pc = new ArrayList<>();
		pc = productSortReadDao.getPcIndexFloors(queryMap, start, size);
		for (PcIndexFloorProduct pcIndexFloorProduct : pc) {
			pcIndexFloorProduct.setProduct(productReadDao.get(pcIndexFloorProduct.getProductId()));
		}
		return pc;
	}
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		productSortWriteDao.delete(id);
	}

	

}
