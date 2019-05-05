package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorProduct;

public interface IProductSortService {

	  /**
	    * 分页
	    * @param queryMap
	    * @param pager
	    * @return
	    */
	ServiceResult<List<PcIndexFloorProduct>> pageProduct(
			Map<String, String> queryMap, PagerInfo pager);

	void delete(Integer id);

}
