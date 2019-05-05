package com.ejavashop.entity.shopm.pcindex;

import java.io.Serializable;

import com.ejavashop.entity.product.Product;

/**
 * 
 * <th field="id" width="100" align="left" halign="center">ID</th>
 *	<th field="name1" width="500" align="left" halign="center">商品名称</th>
 * <th field="sortType" width="100" align="center" halign="center">楼层编号</th>
 *	<th field="sort" width="200" align="center">排序号</th>
 *
 */
public class PcIndexFloorProduct implements Serializable {
	/**
	 *Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -3019654876096885607L;
	
    private java.lang.Integer id;
    private Product product; 
    private java.lang.String sortType;
    private java.lang.Integer sort;
    private java.lang.Integer productId;
    
	public Product getProduct() {
        return product;
    }
	
    public void setProduct(Product product) {
        this.product = product;
    }
    
    public java.lang.Integer getProductId() {
		return productId;
	}
	public void setProductId(java.lang.Integer productId) {
		this.productId = productId;
	}
	
	public java.lang.Integer getId() {
		return id;
	}
	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getSortType() {
		return sortType;
	}
	
	public void setSortType(java.lang.String sortType) {
		this.sortType = sortType;
	}
	
	public java.lang.Integer getSort() {
		return sort;
	}
	
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}
    
}
