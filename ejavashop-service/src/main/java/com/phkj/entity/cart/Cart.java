package com.phkj.entity.cart;

import java.io.Serializable;
import java.math.BigDecimal;

import com.phkj.entity.product.Product;
import com.phkj.entity.product.ProductGoods;
import com.phkj.entity.promotion.single.ActSingle;

/**
 * 商城购物车
 * <p>Table: <strong>cart</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>会员ID</td></tr>
 *   <tr><td>count</td><td>{@link java.lang.Integer}</td><td>count</td><td>int</td><td>数量</td></tr>
 *   <tr><td>specId</td><td>{@link java.lang.String}</td><td>spec_id</td><td>varchar</td><td>规格ID，多个规格用逗号分隔</td></tr>
 *   <tr><td>specInfo</td><td>{@link java.lang.String}</td><td>spec_info</td><td>varchar</td><td>规格详情</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>产品ID</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>productGoodsId</td><td>{@link java.lang.Integer}</td><td>product_goods_id</td><td>int</td><td>货品ID</td></tr>
 * </table>
 *
 */
public class Cart implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 7035997412527086155L;

    /** 是否选中：0未选中、1选中 */
    public static final int   CHECKED_0        = 0;
    /** 是否选中：0未选中、1选中 */
    public static final int   CHECKED_1        = 1;

    private java.lang.Integer id;                                     //id
    private java.util.Date    createTime;                             //创建时间
    private java.lang.Integer memberId;                               //会员ID
    private java.lang.Integer count;                                  //数量
    private java.lang.String  specId;                                 //规格ID，多个规格用逗号分隔
    private java.lang.String  specInfo;                               //规格详情
    private java.lang.Integer productId;                              //产品ID
    private java.lang.Integer sellerId;                               //商家ID
    private java.lang.Integer productGoodsId;                         //货品ID
    private java.lang.Integer checked;                                //是否选中0未选中、1选中
    private java.lang.Integer productPackageId;                       //套餐id
    private java.lang.Integer isSelfLabel;                            //是否自供标 1-是 0-否
    private java.lang.Integer fullContainerCount;                     //整箱价对应的数量
    private java.lang.Integer isDabiaowa;							 //是否打标袜 1-是 0-否
    // --------额外属性（entity对应表结构之外的属性） start------------------------------

    private Product           product;                                // 当前购物车的商品
    private ProductGoods      productGoods;                           // 当前购物车的货品
    private BigDecimal        currAmount;                             // 当前购物车商品的价格和（单价*数量）

    private ActSingle         actSingle;                              // 当前购物车商品参加的单品立减活动
    private BigDecimal        currDiscounted;                         // 当前购物车商品优惠的金额和（立减金额*数量）
    private BigDecimal        currDiscountedAmount;                   // 当前购物车商品优惠后的价格和（（单价-立减金额）*数量）
    private BigDecimal        packageUnitFee;                         //当前套餐价格(单价)
    private BigDecimal        packageFee;                             //当前套餐价格(总价)
    private String            productPackageName;                     //套餐名称
    private String            proudctLabelName;                       //标签名称
    private BigDecimal        labelUnitFee;							  //当前辅料价格(单价)
    private BigDecimal  	  labelFee;							      //当前辅料价格(总价)
    private String            packOther;                              //其它辅料
    private String            packDescribe;                           //套餐描述
    // add by Ls 
    private Integer           stockNums;                               // 增加页面字段，显示商品规格，用来处理页面数量
    private Integer           actSingleType;                           //单品立减类别（1：比例，2：金额扣减）

    private BigDecimal 	      tempdabiaoPriceOnly;							//在进货单显示打标价格

    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

    public Integer getStockNums() {
        return stockNums;
    }

    public void setStockNums(Integer stockNums) {
        this.stockNums = stockNums;
    }
    
    public Integer getActSingleType() {
        return actSingleType;
    }

    public void setActSingleType(Integer actSingleType) {
        this.actSingleType = actSingleType;
    }

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    public String getPackOther() {
        return packOther;
    }

    public void setPackOther(String packOther) {
        this.packOther = packOther;
    }

    public String getPackDescribe() {
        return packDescribe;
    }

    public void setPackDescribe(String packDescribe) {
        this.packDescribe = packDescribe;
    }

    public java.lang.Integer getFullContainerCount() {
        return fullContainerCount;
    }

    public void setFullContainerCount(java.lang.Integer fullContainerCount) {
        this.fullContainerCount = fullContainerCount;
    }
    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取会员ID
     */
    public java.lang.Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置会员ID
     */
    public void setMemberId(java.lang.Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取数量
     */
    public java.lang.Integer getCount() {
        return this.count;
    }

    /**
     * 设置数量
     */
    public void setCount(java.lang.Integer count) {
        this.count = count;
    }

    /**
     * 获取规格ID，多个规格用逗号分隔
     */
    public java.lang.String getSpecId() {
        return this.specId;
    }

    /**
     * 设置规格ID，多个规格用逗号分隔
     */
    public void setSpecId(java.lang.String specId) {
        this.specId = specId;
    }

    /**
     * 获取规格详情
     */
    public java.lang.String getSpecInfo() {
        return this.specInfo;
    }

    /**
     * 设置规格详情
     */
    public void setSpecInfo(java.lang.String specInfo) {
        this.specInfo = specInfo;
    }

    /**
     * 获取产品ID
     */
    public java.lang.Integer getProductId() {
        return this.productId;
    }

    /**
     * 设置产品ID
     */
    public void setProductId(java.lang.Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取商家ID
     */
    public java.lang.Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取货品ID
     */
    public java.lang.Integer getProductGoodsId() {
        return this.productGoodsId;
    }

    /**
     * 设置货品ID
     */
    public void setProductGoodsId(java.lang.Integer productGoodsId) {
        this.productGoodsId = productGoodsId;
    }

    public java.lang.Integer getChecked() {
        return checked;
    }

    public void setChecked(java.lang.Integer checked) {
        this.checked = checked;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductGoods getProductGoods() {
        return productGoods;
    }

    public void setProductGoods(ProductGoods productGoods) {
        this.productGoods = productGoods;
    }

    public BigDecimal getCurrAmount() {
        return currAmount;
    }

    public void setCurrAmount(BigDecimal currAmount) {
        this.currAmount = currAmount;
    }

    public ActSingle getActSingle() {
        return actSingle;
    }

    public void setActSingle(ActSingle actSingle) {
        this.actSingle = actSingle;
    }

    public BigDecimal getCurrDiscounted() {
        return currDiscounted;
    }

    public void setCurrDiscounted(BigDecimal currDiscounted) {
        this.currDiscounted = currDiscounted;
    }

    public BigDecimal getCurrDiscountedAmount() {
        return currDiscountedAmount;
    }

    public void setCurrDiscountedAmount(BigDecimal currDiscountedAmount) {
        this.currDiscountedAmount = currDiscountedAmount;
    }

    public java.lang.Integer getProductPackageId() {
        return productPackageId;
    }

    public void setProductPackageId(java.lang.Integer productPackageId) {
        this.productPackageId = productPackageId;
    }

    public java.lang.Integer getIsSelfLabel() {
        return isSelfLabel;
    }

    public void setIsSelfLabel(java.lang.Integer isSelfLabel) {
        this.isSelfLabel = isSelfLabel;
    }

    public String getProductPackageName() {
        return productPackageName;
    }

    public void setProductPackageName(String productPackageName) {
        this.productPackageName = productPackageName;
    }

    public BigDecimal getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(BigDecimal packageFee) {
        this.packageFee = packageFee;
    }

    public String getProudctLabelName() {
        return proudctLabelName;
    }

    public void setProudctLabelName(String proudctLabelName) {
        this.proudctLabelName = proudctLabelName;
    }

    public BigDecimal getPackageUnitFee() {
        return packageUnitFee;
    }

    public void setPackageUnitFee(BigDecimal packageUnitFee) {
        this.packageUnitFee = packageUnitFee;
    }

	public java.lang.Integer getIsDabiaowa() {
		return isDabiaowa;
	}

	public void setIsDabiaowa(java.lang.Integer isDabiaowa) {
		this.isDabiaowa = isDabiaowa;
	}

	public BigDecimal getLabelUnitFee() {
		return labelUnitFee;
	}

	public void setLabelUnitFee(BigDecimal labelUnitFee) {
		this.labelUnitFee = labelUnitFee;
	}

	public BigDecimal getLabelFee() {
		return labelFee;
	}

	public void setLabelFee(BigDecimal labelFee) {
		this.labelFee = labelFee;
	}

	public BigDecimal getTempdabiaoPriceOnly() {
		return tempdabiaoPriceOnly;
	}

	public void setTempdabiaoPriceOnly(BigDecimal tempdabiaoPriceOnly) {
		this.tempdabiaoPriceOnly = tempdabiaoPriceOnly;
	}

}