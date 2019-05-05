package com.ejavashop.entity.operate;

import java.io.Serializable;

/**
 * 二次加工套餐设定
 * <p>Table: <strong>product_package</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>套餐名称</td></tr>
 *   <tr><td>price</td><td>{@link java.math.BigDecimal}</td><td>price</td><td>decimal</td><td>套餐价格</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>状态：0，不显示；1、显示</td></tr>
 *   <tr><td>packingType</td><td>{@link java.lang.Integer}</td><td>packing_type</td><td>tinyint</td><td>包装方式</td></tr>
 *   <tr><td>unitType</td><td>{@link java.lang.Integer}</td><td>unit_type</td><td>tinyint</td><td>包装单位</td></tr>
 *   <tr><td>isHook</td><td>{@link java.lang.Integer}</td><td>is_hook</td><td>tinyint</td><td>钩子：0，否；1、是</td></tr>
 *   <tr><td>isGlue</td><td>{@link java.lang.Integer}</td><td>is_glue</td><td>tinyint</td><td>不干胶：0，否；1、是</td></tr>
 *   <tr><td>isLiner</td><td>{@link java.lang.Integer}</td><td>is_liner</td><td>tinyint</td><td>衬板：0，否；1、是</td></tr>
 *   <tr><td>isBag</td><td>{@link java.lang.Integer}</td><td>is_bag</td><td>tinyint</td><td>中包袋：0，否；1、是</td></tr>
 *   <tr><td>isLabel</td><td>{@link java.lang.Integer}</td><td>is_label</td><td>tinyint</td><td>防伪标：0，否；1、是</td></tr>
 *   <tr><td>isGirdle</td><td>{@link java.lang.Integer}</td><td>is_girdle</td><td>tinyint</td><td>腰封：0，否；1、是</td></tr>
 *   <tr><td>describe</td><td>{@link java.lang.String}</td><td>describe</td><td>text</td><td>描述</td></tr>
 *   <tr><td>image</td><td>{@link java.lang.String}</td><td>image</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>createId</td></tr>
 *   <tr><td>createName</td><td>{@link java.lang.String}</td><td>create_name</td><td>varchar</td><td>createName</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class ProductPackage implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 129845641223558604L;
    private java.lang.Integer    id;                                    //id
    private java.lang.String     name;                                  //套餐名称
    private java.math.BigDecimal price;                                 //套餐价格
    private java.lang.Integer    state;                                 //状态：0，不显示；1、显示
    private java.lang.Integer    packingType;                           //包装方式
    private java.lang.Integer    unitType;                              //包装单位
    private java.lang.Integer    isHook           = 0;                  //钩子：0，否；1、是
    private java.lang.Integer    isGlue           = 0;                  //不干胶：0，否；1、是
    private java.lang.Integer    isLiner          = 0;                  //衬板：0，否；1、是
    private java.lang.Integer    isBag            = 0;                  //中包袋：0，否；1、是
    private java.lang.Integer    isLabel          = 0;                  //防伪标：0，否；1、是
    private java.lang.Integer    isGirdle         = 0;                  //腰封：0，否；1、是
    private java.lang.String     describe;                              //描述
    private java.lang.String     image;                                 //图片
    private java.lang.Integer    createId;                              //createId
    private java.lang.String     createName;                            //createName
    private java.util.Date       createTime;                            //createTime
    private java.util.Date       updateTime;                            //updateTime

    //----------------------------------------------------//
    private String               packingTypeName;                       //包装类型
    private String               unitTypeName;                          //包装单位
    private String               packOther;                             //其它辅料
    private String 				 fuliaoStr;								//显示辅料内容
    private String 				 packageUnitStr;						//显示中文包装单位
    //----------------------------------------------------//
    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    public String getPackingTypeName() {
        return packingTypeName;
    }

    public void setPackingTypeName(String packingTypeName) {
        this.packingTypeName = packingTypeName;
    }

    public String getUnitTypeName() {
        return unitTypeName;
    }

    public void setUnitTypeName(String unitTypeName) {
        this.unitTypeName = unitTypeName;
    }

    public String getPackOther() {
        return packOther;
    }

    public void setPackOther(String packOther) {
        this.packOther = packOther;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取套餐名称
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * 设置套餐名称
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取套餐价格
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 设置套餐价格
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    /**
     * 获取状态：0，不显示；1、显示
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置状态：0，不显示；1、显示
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取包装方式
     */
    public java.lang.Integer getPackingType() {
        return this.packingType;
    }

    /**
     * 设置包装方式
     */
    public void setPackingType(java.lang.Integer packingType) {
        this.packingType = packingType;
    }

    /**
     * 获取包装单位
     */
    public java.lang.Integer getUnitType() {
        return this.unitType;
    }

    /**
     * 设置包装单位
     */
    public void setUnitType(java.lang.Integer unitType) {
        this.unitType = unitType;
    }

    /**
     * 获取钩子：0，否；1、是
     */
    public java.lang.Integer getIsHook() {
        return this.isHook;
    }

    /**
     * 设置钩子：0，否；1、是
     */
    public void setIsHook(java.lang.Integer isHook) {
        this.isHook = isHook;
    }

    /**
     * 获取不干胶：0，否；1、是
     */
    public java.lang.Integer getIsGlue() {
        return this.isGlue;
    }

    /**
     * 设置不干胶：0，否；1、是
     */
    public void setIsGlue(java.lang.Integer isGlue) {
        this.isGlue = isGlue;
    }

    /**
     * 获取衬板：0，否；1、是
     */
    public java.lang.Integer getIsLiner() {
        return this.isLiner;
    }

    /**
     * 设置衬板：0，否；1、是
     */
    public void setIsLiner(java.lang.Integer isLiner) {
        this.isLiner = isLiner;
    }

    /**
     * 获取中包袋：0，否；1、是
     */
    public java.lang.Integer getIsBag() {
        return this.isBag;
    }

    /**
     * 设置中包袋：0，否；1、是
     */
    public void setIsBag(java.lang.Integer isBag) {
        this.isBag = isBag;
    }

    /**
     * 获取防伪标：0，否；1、是
     */
    public java.lang.Integer getIsLabel() {
        return this.isLabel;
    }

    /**
     * 设置防伪标：0，否；1、是
     */
    public void setIsLabel(java.lang.Integer isLabel) {
        this.isLabel = isLabel;
    }

    /**
     * 获取腰封：0，否；1、是
     */
    public java.lang.Integer getIsGirdle() {
        return this.isGirdle;
    }

    /**
     * 设置腰封：0，否；1、是
     */
    public void setIsGirdle(java.lang.Integer isGirdle) {
        this.isGirdle = isGirdle;
    }

    /**
     * 获取描述
     */
    public java.lang.String getDescribe() {
        return this.describe;
    }

    /**
     * 设置描述
     */
    public void setDescribe(java.lang.String describe) {
        this.describe = describe;
    }

    /**
     * 获取图片
     */
    public java.lang.String getImage() {
        return this.image;
    }

    /**
     * 设置图片
     */
    public void setImage(java.lang.String image) {
        this.image = image;
    }

    /**
     * 获取createId
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置createId
     */
    public void setCreateId(java.lang.Integer createId) {
        this.createId = createId;
    }

    /**
     * 获取createName
     */
    public java.lang.String getCreateName() {
        return this.createName;
    }

    /**
     * 设置createName
     */
    public void setCreateName(java.lang.String createName) {
        this.createName = createName;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getFuliaoStr() {
		return fuliaoStr;
	}

	public void setFuliaoStr(String fuliaoStr) {
		this.fuliaoStr = fuliaoStr;
	}

	public String getPackageUnitStr() {
		return packageUnitStr;
	}

	public void setPackageUnitStr(String packageUnitStr) {
		this.packageUnitStr = packageUnitStr;
	}
    
    
}