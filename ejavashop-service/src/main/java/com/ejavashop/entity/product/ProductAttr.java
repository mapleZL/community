package com.ejavashop.entity.product;

import java.io.Serializable;

/**
* 商品对应属性表
* <p>Table: <strong>product_attr</strong>
    * <p>
<table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
    *
    <tr style="background-color:#ddd;Text-align:Left;">
        *
        <th nowrap>属性名</th>
        <th nowrap>属性类型</th>
        <th nowrap>字段名</th>
        <th nowrap>字段类型</th>
        <th nowrap>说明</th>
        *
    </tr>
    *
    <tr>
        <td>id</td>
        <td>{@link Integer}</td>
        <td>id</td>
        <td>int</td>
        <td>id</td>
    </tr>
    *
    <tr>
        <td>productId</td>
        <td>{@link Integer}</td>
        <td>product_id</td>
        <td>int</td>
        <td>商品ID</td>
    </tr>
    *
    <tr>
        <td>productTypeAttrId</td>
        <td>{@link Integer}</td>
        <td>product_type_attr_id</td>
        <td>int</td>
        <td>属性ID，可以根据属性ID来进行检索</td>
    </tr>
    *
    <tr>
        <td>name</td>
        <td>{@link String}</td>
        <td>name</td>
        <td>varchar</td>
        <td>属性名称</td>
    </tr>
    *
    <tr>
        <td>value</td>
        <td>{@link String}</td>
        <td>value</td>
        <td>varchar</td>
        <td>属性值</td>
    </tr>
    *
    <tr>
        <td>state</td>
        <td>{@link Integer}</td>
        <td>state</td>
        <td>tinyint</td>
        <td>1、检索属性；2、自定义属性；3、商品自定义属性（product_tyep_attr_id=0）</td>
    </tr>
    *
</table>
*
*/
public class ProductAttr implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long serialVersionUID = -264584636750741280L;

    private Integer id;

    private Integer productId; //商品ID

    private Integer productTypeAttrId; //属性ID，可以根据属性ID来进行检索

    private String name; //属性名称

    private String value; //属性值

    private Integer state; //1、检索属性；2、自定义属性；3、商品自定义属性（product_tyep_attr_id=0）
    
    /**
     *  男长筒M.L.
		男短袜M.S.
		男中筒M.M
		女长筒W.L.
		女短袜W.S.
		女中筒W.M.
		商标
     */
    
    public static final String MAN_LONG_SOCKET="男长筒M.L.";
    public static final String MAN_SHORT_SOCKET="男长筒M.S.";
    public static final String MAN_MIDDLE_SOCKET="男长筒M.M.";
    public static final String WOMAN_LONG_SOCKET="男长筒W.L.";
    public static final String WOMAN_SHORT_SOCKET="男长筒W.S.";
    public static final String WOMAN_MIDDLE_SOCKET="男长筒W.M.";
    public static final String PRODUCT_KEY="商标";
    
    //性别
    public static final Integer SOCKET_SEX = 151;
    //筒高
    public static final Integer SOCKET_TONG_HIGH = 158;
    //规格
    public static final Integer SOCKET_GUIGE = 234;
    //尺寸
    public static final Integer SOCKET_SIZE = 178 ;

    /**
    * 获取id
    */
    public Integer getId() {
        return this.id;
    }

    /**
    * 设置id
    */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
    * 获取商品ID
    */
    public Integer getProductId() {
        return this.productId;
    }

    /**
    * 设置商品ID
    */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
    * 获取属性ID，可以根据属性ID来进行检索
    */
    public Integer getProductTypeAttrId() {
        return this.productTypeAttrId;
    }

    /**
    * 设置属性ID，可以根据属性ID来进行检索
    */
    public void setProductTypeAttrId(Integer productTypeAttrId) {
        this.productTypeAttrId = productTypeAttrId;
    }

    /**
    * 获取属性名称
    */
    public String getName() {
        return this.name;
    }

    /**
    * 设置属性名称
    */
    public void setName(String name) {
        this.name = name;
    }

    /**
    * 获取属性值
    */
    public String getValue() {
        return this.value;
    }

    /**
    * 设置属性值
    */
    public void setValue(String value) {
        this.value = value;
    }

    /**
    * 获取1、检索属性；2、自定义属性；3、商品自定义属性（product_tyep_attr_id=0）
    */
    public Integer getState() {
        return this.state;
    }

    /**
    * 设置1、检索属性；2、自定义属性；3、商品自定义属性（product_tyep_attr_id=0）
    */
    public void setState(Integer state) {
        this.state = state;
    }
}