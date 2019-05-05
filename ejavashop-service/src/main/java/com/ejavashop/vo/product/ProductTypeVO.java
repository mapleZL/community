package com.ejavashop.vo.product;

import java.util.List;

/**
 * 商品分类属性
 *                       
 * @Filename: ProductTypeAttrVO.java
 * @Version: 1.0
 *
 */
public class ProductTypeVO {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7638315549640131068L;
    private int             id;
    private String          name;
    private String          imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    private List<ProductTypeVO>      childList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ProductTypeVO> getChildList() {
        return childList;
    }

    public void setChildList(List<ProductTypeVO> childList) {
        this.childList = childList;
    }

}