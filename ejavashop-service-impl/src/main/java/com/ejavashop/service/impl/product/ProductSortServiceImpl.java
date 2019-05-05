package com.ejavashop.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloorProduct;
import com.ejavashop.model.product.ProductSortModel;
import com.ejavashop.service.product.IProductSortService;

@Service(value="productSortService")
public class ProductSortServiceImpl implements IProductSortService {

	private static Logger log = LogManager.getLogger(ProductServiceImpl.class);
	
	@Resource
	private ProductSortModel productSortModel;
	
	@Override
	public ServiceResult<List<PcIndexFloorProduct>> pageProduct(
			Map<String, String> queryMap, PagerInfo pager) {
		 ServiceResult<List<PcIndexFloorProduct>> serviceResult = new ServiceResult<List<PcIndexFloorProduct>>();
		 serviceResult.setPager(pager);  
		 try {
	        	 Integer start = 0, size = 0;
	             if (pager != null) {
	                 pager.setRowsCount(productSortModel.getPcIndexFloorsCount(queryMap));
	                 start = pager.getStart();
	                 size = pager.getPageSize();
	             }
	            serviceResult.setResult(productSortModel.pageProduct(queryMap, start,size));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
	                      + " pager:" + JSON.toJSONString(pager));
	            log.error("ProductServiceImpl pageProduct exception:", e);
	        }
	        return serviceResult;
	}

	@Override
	public void delete(Integer id) {
		
		productSortModel.delete(id);
		
	}

}
