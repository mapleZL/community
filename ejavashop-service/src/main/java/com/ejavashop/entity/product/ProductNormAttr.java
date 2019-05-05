package com.ejavashop.entity.product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 规格属性
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public class ProductNormAttr implements Serializable {
    private static final long serialVersionUID = -4733220964574900474L;

    private Integer           id;                                      //id
    private String            name;                                    //属性名称
    private Integer           productNormId;                           //规格ID
    private Integer           sort;                                    //排序
    private Integer           createId;                                //创建人
    private Date              createTime;                              //创建时间
    private String            image;                                   //默认图片
    private String            productNormName;                         //规格名称
    private Integer              productStock;                           //库存
    private Integer 			productGoodsId;					//商品ID(sku)
    private BigDecimal 			pgMallPcPrice;					//商品PC价格
    //颜色规格图片
    private String            url;
    //1、商城属性 2、自定义属性
    private Integer           typeAttr;

    private Boolean           selected;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProductNormId() {
        return productNormId;
    }

    public void setProductNormId(Integer productNormId) {
        this.productNormId = productNormId;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getProductNormName() {
        return productNormName;
    }

    public void setProductNormName(String productNormName) {
        this.productNormName = productNormName;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTypeAttr() {
        return typeAttr;
    }

    public void setTypeAttr(Integer typeAttr) {
        this.typeAttr = typeAttr;
    }

	public Integer getProductStock() {
		return productStock;
	}

	public void setProductStock(Integer productStock) {
		this.productStock = productStock;
	}

	public Integer getProductGoodsId() {
		return productGoodsId;
	}

	public void setProductGoodsId(Integer productGoodsId) {
		this.productGoodsId = productGoodsId;
	}

	public BigDecimal getPgMallPcPrice() {
		return pgMallPcPrice;
	}

	public void setPgMallPcPrice(BigDecimal pgMallPcPrice) {
		this.pgMallPcPrice = pgMallPcPrice;
	}

    
}
