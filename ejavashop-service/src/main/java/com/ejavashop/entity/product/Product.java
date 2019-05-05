package com.ejavashop.entity.product;

import java.io.Serializable;
import java.util.List;

/**
 * 商品表
 * <p>Table: <strong>product</strong>
 * <p/>
 * <table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 * <p/>
 * <tr style="background-color:#ddd;Text-align:Left;">
 * <p/>
 * <th nowrap>属性名</th>
 * <th nowrap>属性类型</th>
 * <th nowrap>字段名</th>
 * <th nowrap>字段类型</th>
 * <th nowrap>说明</th>
 * <p/>
 * </tr>
 * <p/>
 * <tr>
 * <td>id</td>
 * <td>{@link Integer}</td>
 * <td>id</td>
 * <td>int</td>
 * <td>id</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>productCateId</td>
 * <td>{@link Integer}</td>
 * <td>product_cate_id</td>
 * <td>int</td>
 * <td>分类ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>name1</td>
 * <td>{@link String}</td>
 * <td>name1</td>
 * <td>varchar</td>
 * <td>商品名称最多50个字符</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>name2</td>
 * <td>{@link String}</td>
 * <td>name2</td>
 * <td>varchar</td>
 * <td>商品促销信息（最多100个字符）</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>keyword</td>
 * <td>{@link String}</td>
 * <td>keyword</td>
 * <td>varchar</td>
 * <td>商品关键字，用于检索商品，用逗号分隔</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>productBrandId</td>
 * <td>{@link Integer}</td>
 * <td>product_brand_id</td>
 * <td>int</td>
 * <td>品牌ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>isSelf</td>
 * <td>{@link Integer}</td>
 * <td>is_self</td>
 * <td>tinyint</td>
 * <td>1、自营；2、商家</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>costPrice</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>cost_price</td>
 * <td>decimal</td>
 * <td>成本价</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>protectedPrice</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>protected_price</td>
 * <td>decimal</td>
 * <td>保护价，最低价格不能低于</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>marketPrice</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>market_price</td>
 * <td>decimal</td>
 * <td>市场价</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>mallPcPrice</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>mall_pc_price</td>
 * <td>decimal</td>
 * <td>商城价</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>malMobilePrice</td>
 * <td>{@link java.math.BigDecimal}</td>
 * <td>mal_mobile_price</td>
 * <td>decimal</td>
 * <td>商城价Mobile</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>virtualSales</td>
 * <td>{@link Integer}</td>
 * <td>virtual_sales</td>
 * <td>int</td>
 * <td>虚拟销量</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>actualSales</td>
 * <td>{@link Integer}</td>
 * <td>actual_sales</td>
 * <td>int</td>
 * <td>实际销量</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>productStock</td>
 * <td>{@link Integer}</td>
 * <td>product_stock</td>
 * <td>int</td>
 * <td>商品库存</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>isNorm</td>
 * <td>{@link Integer}</td>
 * <td>is_norm</td>
 * <td>tinyint</td>
 * <td>1、没有启用规格；2、启用规格</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>normIds</td>
 * <td>{@link String}</td>
 * <td>norm_ids</td>
 * <td>varchar</td>
 * <td>规格ID集合</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>normName</td>
 * <td>{@link String}</td>
 * <td>norm_name</td>
 * <td>varchar</td>
 * <td>规格属性值集合 空</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>state</td>
 * <td>{@link Integer}</td>
 * <td>state</td>
 * <td>tinyint</td>
 * <td>1、刚创建；2、提交审核；3、商城显示；4、申请驳回；5、商品删除</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>isTop</td>
 * <td>{@link Integer}</td>
 * <td>is_top</td>
 * <td>tinyint</td>
 * <td>1、不推荐；2、推荐</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>upTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>up_time</td>
 * <td>datetime</td>
 * <td>商品上架时间</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>description</td>
 * <td>{@link String}</td>
 * <td>description</td>
 * <td>text</td>
 * <td>商品描述信息</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>packing</td>
 * <td>{@link String}</td>
 * <td>packing</td>
 * <td>text</td>
 * <td>包装清单</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>sellerId</td>
 * <td>{@link Integer}</td>
 * <td>seller_id</td>
 * <td>int</td>
 * <td>商家ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>createId</td>
 * <td>{@link Integer}</td>
 * <td>create_id</td>
 * <td>int</td>
 * <td>创建人</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>createTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>create_time</td>
 * <td>datetime</td>
 * <td>创建时间</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>updateTime</td>
 * <td>{@link java.util.Date}</td>
 * <td>update_time</td>
 * <td>datetime</td>
 * <td>更新时间</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>sellerCateId</td>
 * <td>{@link Integer}</td>
 * <td>seller_cate_id</td>
 * <td>int</td>
 * <td>商家分类ID</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>sellerIsTop</td>
 * <td>{@link Integer}</td>
 * <td>seller_is_top</td>
 * <td>tinyint</td>
 * <td>商品推荐，推荐的商品会显示到店铺的首页</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>sellerState</td>
 * <td>{@link Integer}</td>
 * <td>seller_state</td>
 * <td>tinyint</td>
 * <td>店铺状态：1、店铺正常；2、店铺关闭 默认1</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>commentsNumber</td>
 * <td>{@link Integer}</td>
 * <td>comments_number</td>
 * <td>int</td>
 * <td>评价总数 默认0</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>productCateState</td>
 * <td>{@link Integer}</td>
 * <td>product_cate_state</td>
 * <td>tinyint</td>
 * <td>平台商品分类状态：1、分类正常；2、分类关闭 默认1</td>
 * </tr>
 * <p/>
 * <tr>
 * <td>isInventedProduct</td>
 * <td>{@link Integer}</td>
 * <td>is_invented_product</td>
 * <td>tinyint</td>
 * <td>是否是虚拟商品：1、实物商品；2、虚拟商品</td>
 * </tr>
 * <p/>
 * </table>
 */
public class Product implements Serializable {

    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID     = -264584636750741280L;

    /**
     * 产品表 state  状态：1、刚创建；
     */
    public final static int      STATE_1              = 1;
    /**
     * 产品表 state  状态：2、提交审核；
     */
    public final static int      STATE_2              = 2;
    /**
     * 产品表 state  状态：3、审核通过；
     */
    public final static int      STATE_3              = 3;
    /**
     * 产品表 state  状态：4、申请驳回；
     */
    public final static int      STATE_4              = 4;
    /**
     * 产品表 state  状态：5、商品删除
     */
    public final static int      STATE_5              = 5;
    /**
     * 产品表 state  状态：6、上架
     */
    public final static int      STATE_6              = 6;
    /**
     * 产品表 state  状态：7、下架
     */
    public final static int      STATE_7              = 7;

    /**
     * 产品表 seller_state  店铺状态：1、店铺正常；
     */
    public final static int      SELLER_STATE_1       = 1;

    /**
     * 产品表 seller_state  店铺状态：2、店铺关闭
     */
    public final static int      SELLER_STATE_2       = 2;

    /**
     * 自营 1
     */
    public final static int      IS_SELF_1            = 1;

    /**
     * 入住商家 2
     */
    public final static int      IS_SELF_2            = 2;

    /** 平台商品分类状态：1、分类正常 */
    public final static int      PRODUCT_CATE_STATE_1 = 1;

    /** 平台商品分类状态：2、分类关闭 */
    public final static int      PRODUCT_CATE_STATE_2 = 2;
    
    /**
     * 平台娃子分类 8：裸娃
     */
    public final static int PRODUCT_BRAND_ID = 8;
    
    private Integer              id;
    private Integer              productCateId;                              // 分类ID
    private String               name1;                                      // 商品名称最多50个字符
    private String               name2;                                      // 商品促销信息（最多100个字符）
    private String               keyword;                                    // 商品关键字，用于检索商品，用逗号分隔
    private Integer              productBrandId;                             // 品牌ID
    private Integer              isSelf;                                     // 1、自营；2、商家
    private java.math.BigDecimal costPrice;                                  // 成本价
    private java.math.BigDecimal protectedPrice;                             // 保护价，最低价格不能低于
    private java.math.BigDecimal marketPrice;                                // 市场价
    private java.math.BigDecimal mallPcPrice;                                // 商城价
    private java.math.BigDecimal malMobilePrice;                             // 商城价Mobile
    private Integer              virtualSales;                               // 虚拟销量
    private Integer              actualSales;                                // 实际销量
    private Integer              productStock;                               // 商品库存
    private Integer              isNorm;                                     // 1、没有启用规格；2、启用规格
    private String               normIds;                                    // 规格ID集合
    private String               normName;                                   // 规格属性值集合 空
    private Integer              state;                                      // 1、刚创建；2、提交审核；3、商城显示；4、申请驳回；5、商品删除
    private Integer              isTop;                                      // 1、不推荐；2、推荐
    private java.util.Date       upTime;                                     // 商品上架时间
    private String               description;                                // 商品描述信息
    private String               packing;                                    // 包装清单
    private Integer              sellerId;                                   // 商家ID
    private Integer              createId;                                   // 创建人
    private java.util.Date       createTime;                                 // 创建时间
    private java.util.Date       updateTime;                                 // 更新时间
    private Integer              sellerCateId;                               // 商家分类ID
    private Integer              sellerIsTop;                                // 商品推荐，推荐的商品会显示到店铺的首页
    private Integer              sellerState;                                // 店铺状态：1、店铺正常；2、店铺关闭 默认1
    private Integer              commentsNumber;                             // 评价总数
    private Integer              productCateState;                           // 平台商品分类状态：1、分类正常；2、分类关闭 默认1
    private Integer              isInventedProduct;                          // 是否是虚拟商品：1、实物商品；2、虚拟商品
    private String               masterImg;                                  // 主图
    private String               productCode;                                //商品编码
    private java.lang.Integer    stockWarning;                               //库存预警值，低于此值后，显示‘库存紧张’
    private java.lang.String	 unit;										 //销售单位  add by tongzhaomei
    // --------额外属性（entity对应表结构之外的属性） start------------------------------

    private String               productCateName;                            // 商品分类名称
    private String               productBrandName;                           // 品牌名称
    private String               sellerCateName;                             // 商家分类名称

    private List<ProductPicture> pic;
    private List<ProductAttr>    attr;
    private List<ProductGoods>   goodsList;

    private String               imagePath;                                  // 图片路径（根据需要存大中小图片）

    private String               seller;                                     //商家名称
    private String               sku;

    //活动时间
    private String               actTime;

    //辅料sku terry 20160707
    private String               skuOther;

    private String               productUrl;                                 //商品跳转路径
    private Integer              inStockWarning;                             //内部使用预警数量
    private String 				 wmsCategory;                      //wms 要用的分类
    private Integer				 isCxbh;							//【仝照美】新增字段  补货提示
    private String 				 refIds;							//搭配推荐商品
    private Integer 			 taocanId;							//关联一个套餐，即商品有一个默认的推荐加工方式
    private Integer				 otherCategory;						//其他分类
    private String 				 memberIds;							//购买权限
    private Integer 				dabiaowaFlag;						//值为1则说明为打标袜商品 
    public String getMemberIds() {
		return memberIds;
	}

	public void setMemberIds(String memberIds) {
		this.memberIds = memberIds;
	}

	public Integer getOtherCategory() {
		return otherCategory;
	}

	public void setOtherCategory(Integer otherCategory) {
		this.otherCategory = otherCategory;
	}

	public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public Integer getInStockWarning() {
        return inStockWarning;
    }

    public void setInStockWarning(Integer inStockWarning) {
        this.inStockWarning = inStockWarning;
    }


    public String getSkuOther() {
		return skuOther;
	}

	public void setSkuOther(String skuOther) {
		this.skuOther = skuOther;
	}

	public Integer getFullContainerQuantity() {
        return fullContainerQuantity;
    }

    public void setFullContainerQuantity(Integer fullContainerQuantity) {
        this.fullContainerQuantity = fullContainerQuantity;
    }

    private java.math.BigDecimal percentageScale;      //分佣比例
    private Integer              fullContainerQuantity; //整箱价对应的整箱数量

    public Integer getPricePid() {
        return pricePid;
    }

    public void setPricePid(Integer pricePid) {
        this.pricePid = pricePid;
    }

    public Integer getPriceStatus() {
        return priceStatus;
    }

    public void setPriceStatus(Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    private Integer              pricePid;      //阶梯价表Product_price对应的id
    private Integer              priceStatus;   //价格类型 1：一口价，2：阶梯价，3:整箱价
    private java.math.BigDecimal scatteredPrice; //散价，该字段只针对整箱价时进行存储
    private Integer              price2Pid;		//关联打标价ID
    private Integer              price2Status;   //打标价格类型 1：一口价，2：阶梯价
    private java.math.BigDecimal price1;        //阶梯价第一价位
    private java.lang.Integer    price1S;       //第一梯段数量起始
    private java.lang.Integer    price1E;       //第一梯段末
    private java.math.BigDecimal price2;        //阶梯第二价格
    private java.lang.Integer    price2S;       //第二阶梯数量起始
    private java.lang.Integer    price2E;       //第二梯段末
    private java.math.BigDecimal price3;        //阶梯第三价格
    private java.lang.Integer    price3S;       //第三阶梯数量起始
    private String               price1se;      
    private String               price2se;
    private String               price3se;
    private java.math.BigDecimal percentageScale1; //阶梯价1分佣比例
    private java.math.BigDecimal percentageScale2; //阶梯价2分佣比例
    private java.math.BigDecimal percentageScale3; //阶梯价3分佣比例
    
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------

    public String getPrice1se() {
        return price1se;
    }

    public java.math.BigDecimal getPercentageScale1() {
        return percentageScale1;
    }

    public void setPercentageScale1(java.math.BigDecimal percentageScale1) {
        this.percentageScale1 = percentageScale1;
    }

    public java.math.BigDecimal getPercentageScale2() {
        return percentageScale2;
    }

    public void setPercentageScale2(java.math.BigDecimal percentageScale2) {
        this.percentageScale2 = percentageScale2;
    }

    public java.math.BigDecimal getPercentageScale3() {
        return percentageScale3;
    }

    public void setPercentageScale3(java.math.BigDecimal percentageScale3) {
        this.percentageScale3 = percentageScale3;
    }

    public void setPrice1se(String price1se) {
        this.price1se = price1se;
    }

    public String getPrice2se() {
        return price2se;
    }

    public void setPrice2se(String price2se) {
        this.price2se = price2se;
    }

    public String getPrice3se() {
        return price3se;
    }

    public void setPrice3se(String price3se) {
        this.price3se = price3se;
    }

    public java.math.BigDecimal getPrice1() {
        return price1;
    }

    public void setPrice1(java.math.BigDecimal price1) {
        this.price1 = price1;
    }

    public java.lang.Integer getPrice1S() {
        return price1S;
    }

    public void setPrice1S(java.lang.Integer price1s) {
        price1S = price1s;
    }

    public java.lang.Integer getPrice1E() {
        return price1E;
    }

    public void setPrice1E(java.lang.Integer price1e) {
        price1E = price1e;
    }

    public java.math.BigDecimal getPrice2() {
        return price2;
    }

    public void setPrice2(java.math.BigDecimal price2) {
        this.price2 = price2;
    }

    public java.lang.Integer getPrice2S() {
        return price2S;
    }

    public void setPrice2S(java.lang.Integer price2s) {
        price2S = price2s;
    }

    public java.lang.Integer getPrice2E() {
        return price2E;
    }

    public void setPrice2E(java.lang.Integer price2e) {
        price2E = price2e;
    }

    public java.math.BigDecimal getPrice3() {
        return price3;
    }

    public void setPrice3(java.math.BigDecimal price3) {
        this.price3 = price3;
    }

    public java.lang.Integer getPrice3S() {
        return price3S;
    }

    public void setPrice3S(java.lang.Integer price3s) {
        price3S = price3s;
    }

    public java.math.BigDecimal getScatteredPrice() {
        return scatteredPrice;
    }

    public void setScatteredPrice(java.math.BigDecimal scatteredPrice) {
        this.scatteredPrice = scatteredPrice;
    }

    public java.math.BigDecimal getPercentageScale() {
        return percentageScale;
    }

    public void setPercentageScale(java.math.BigDecimal percentageScale) {
        this.percentageScale = percentageScale;
    }

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
     * 获取分类ID
     */
    public Integer getProductCateId() {
        return this.productCateId;
    }

    /**
     * 设置分类ID
     */
    public void setProductCateId(Integer productCateId) {
        this.productCateId = productCateId;
    }

    /**
     * 获取商品名称最多50个字符
     */
    public String getName1() {
        return this.name1;
    }

    /**
     * 设置商品名称最多50个字符
     */
    public void setName1(String name1) {
        this.name1 = name1;
    }

    /**
     * 获取商品促销信息（最多100个字符）
     */
    public String getName2() {
        return this.name2;
    }

    /**
     * 设置商品促销信息（最多100个字符）
     */
    public void setName2(String name2) {
        this.name2 = name2;
    }

    /**
     * 获取商品关键字，用于检索商品，用逗号分隔
     */
    public String getKeyword() {
        return this.keyword;
    }

    /**
     * 设置商品关键字，用于检索商品，用逗号分隔
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取品牌ID
     */
    public Integer getProductBrandId() {
        return this.productBrandId;
    }

    /**
     * 设置品牌ID
     */
    public void setProductBrandId(Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    /**
     * 获取1、自营；2、商家
     */
    public Integer getIsSelf() {
        return this.isSelf;
    }

    /**
     * 设置1、自营；2、商家
     */
    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }

    /**
     * 获取成本价
     */
    public java.math.BigDecimal getCostPrice() {
        return this.costPrice;
    }

    /**
     * 设置成本价
     */
    public void setCostPrice(java.math.BigDecimal costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * 获取保护价，最低价格不能低于
     */
    public java.math.BigDecimal getProtectedPrice() {
        return this.protectedPrice;
    }

    /**
     * 设置保护价，最低价格不能低于
     */
    public void setProtectedPrice(java.math.BigDecimal protectedPrice) {
        this.protectedPrice = protectedPrice;
    }

    /**
     * 获取市场价
     */
    public java.math.BigDecimal getMarketPrice() {
        return this.marketPrice;
    }

    /**
     * 设置市场价
     */
    public void setMarketPrice(java.math.BigDecimal marketPrice) {
        this.marketPrice = marketPrice;
    }

    /**
     * 获取商城价
     */
    public java.math.BigDecimal getMallPcPrice() {
        return this.mallPcPrice;
    }

    /**
     * 设置商城价
     */
    public void setMallPcPrice(java.math.BigDecimal mallPcPrice) {
        this.mallPcPrice = mallPcPrice;
    }

    /**
     * 获取商城价Mobile
     */
    public java.math.BigDecimal getMalMobilePrice() {
        return this.malMobilePrice;
    }

    /**
     * 设置商城价Mobile
     */
    public void setMalMobilePrice(java.math.BigDecimal malMobilePrice) {
        this.malMobilePrice = malMobilePrice;
    }

    /**
     * 获取虚拟销量
     */
    public Integer getVirtualSales() {
        return this.virtualSales;
    }

    /**
     * 设置虚拟销量
     */
    public void setVirtualSales(Integer virtualSales) {
        this.virtualSales = virtualSales;
    }

    /**
     * 获取实际销量
     */
    public Integer getActualSales() {
        return this.actualSales;
    }

    /**
     * 设置实际销量
     */
    public void setActualSales(Integer actualSales) {
        this.actualSales = actualSales;
    }

    /**
     * 获取商品库存
     */
    public Integer getProductStock() {
        return this.productStock;
    }

    /**
     * 设置商品库存
     */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
     * 获取1、没有启用规格；2、启用规格
     */
    public Integer getIsNorm() {
        return this.isNorm;
    }

    /**
     * 设置1、没有启用规格；2、启用规格
     */
    public void setIsNorm(Integer isNorm) {
        this.isNorm = isNorm;
    }

    /**
     * 获取规格ID集合
     */
    public String getNormIds() {
        return this.normIds;
    }

    /**
     * 设置规格ID集合
     */
    public void setNormIds(String normIds) {
        this.normIds = normIds;
    }

    /**
     * 获取规格属性值集合 空
     */
    public String getNormName() {
        return this.normName;
    }

    /**
     * 设置规格属性值集合 空
     */
    public void setNormName(String normName) {
        this.normName = normName;
    }

    /**
     * 获取1、刚创建；2、提交审核；3、商城显示；4、申请驳回；5、商品删除
     */
    public Integer getState() {
        return this.state;
    }

    /**
     * 设置1、刚创建；2、提交审核；3、商城显示；4、申请驳回；5、商品删除
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取1、不推荐；2、推荐
     */
    public Integer getIsTop() {
        return this.isTop;
    }

    /**
     * 设置1、不推荐；2、推荐
     */
    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    /**
     * 获取商品上架时间
     */
    public java.util.Date getUpTime() {
        return this.upTime;
    }

    /**
     * 设置商品上架时间
     */
    public void setUpTime(java.util.Date upTime) {
        this.upTime = upTime;
    }

    /**
     * 获取商品描述信息
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * 设置商品描述信息
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取包装清单
     */
    public String getPacking() {
        return this.packing;
    }

    /**
     * 设置包装清单
     */
    public void setPacking(String packing) {
        this.packing = packing;
    }

    /**
     * 获取商家ID
     */
    public Integer getSellerId() {
        return this.sellerId;
    }

    /**
     * 设置商家ID
     */
    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 获取创建人
     */
    public Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人
     */
    public void setCreateId(Integer createId) {
        this.createId = createId;
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
     * 获取更新时间
     */
    public java.util.Date getUpdateTime() {
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取商家分类ID
     */
    public Integer getSellerCateId() {
        return this.sellerCateId;
    }

    /**
     * 设置商家分类ID
     */
    public void setSellerCateId(Integer sellerCateId) {
        this.sellerCateId = sellerCateId;
    }

    public Integer getCommentsNumber() {
        return commentsNumber;
    }

    public void setCommentsNumber(Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    /**
     * 获取商品推荐，推荐的商品会显示到店铺的首页
     */
    public Integer getSellerIsTop() {
        return this.sellerIsTop;
    }

    /**
     * 设置商品推荐，推荐的商品会显示到店铺的首页
     */
    public void setSellerIsTop(Integer sellerIsTop) {
        this.sellerIsTop = sellerIsTop;
    }

    /**
     * 获取店铺状态：1、店铺正常；2、店铺关闭 默认1
     */
    public Integer getSellerState() {
        return this.sellerState;
    }

    /**
     * 设置店铺状态：1、店铺正常；2、店铺关闭 默认1
     */
    public void setSellerState(Integer sellerState) {
        this.sellerState = sellerState;
    }

    /**
     * 获取平台商品分类状态：1、分类正常；2、分类关闭 默认1
     */
    public Integer getProductCateState() {
        return this.productCateState;
    }

    /**
     * 设置平台商品分类状态：1、分类正常；2、分类关闭 默认1
     */
    public void setProductCateState(Integer productCateState) {
        this.productCateState = productCateState;
    }

    /**
     * 获取是否是虚拟商品：1、实物商品；2、虚拟商品
     */
    public Integer getIsInventedProduct() {
        return this.isInventedProduct;
    }

    /**
     * 设置是否是虚拟商品：1、实物商品；2、虚拟商品
     */
    public void setIsInventedProduct(Integer isInventedProduct) {
        this.isInventedProduct = isInventedProduct;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public enum IsSelfEnum {
        //1:自营，2:商家
        SELF("默认", 1), SELLER("提交审核", 2);

        private String name; //显示的名字
        private int    value; //实际存储的值

        //构造方法
        private IsSelfEnum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 检查value是否合法
         *
         * @param value
         * @return
         */
        public static Boolean chkIsSelfEnum(int value) {
            for (IsSelfEnum status : IsSelfEnum.values()) {
                if (status.getValue() == value)
                    return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public enum StateEnum {
        //1、刚创建；2、提交审核；3、商城显示；4、申请驳回；5、商品删除
        CREATE("默认", 1), COMIT("提交审核", 2), SHOW("商城显示", 3), ERROR("申请驳回", 4), DEL("商品删除", 5);

        private String name; //显示的名字
        private int    value; //实际存储的值

        //构造方法
        private StateEnum(String name, int value) {
            this.name = name;
            this.value = value;
        }

        /**
         * 检查value是否合法
         *
         * @param value
         * @return
         */
        public static Boolean chkIsSelfEnum(int value) {
            for (IsSelfEnum status : IsSelfEnum.values()) {
                if (status.getValue() == value)
                    return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }

        public int getValue() {
            return value;
        }

        public String getName() {
            return name;
        }
    }

    public List<ProductPicture> getPic() {
        return pic;
    }

    public void setPic(List<ProductPicture> pic) {
        this.pic = pic;
    }

    public List<ProductAttr> getAttr() {
        return attr;
    }

    public void setAttr(List<ProductAttr> attr) {
        this.attr = attr;
    }

    public List<ProductGoods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<ProductGoods> goodsList) {
        this.goodsList = goodsList;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getMasterImg() {
        return masterImg;
    }

    public void setMasterImg(String masterImg) {
        this.masterImg = masterImg;
    }

    public String getProductCateName() {
        return productCateName;
    }

    public void setProductCateName(String productCateName) {
        this.productCateName = productCateName;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public String getSellerCateName() {
        return sellerCateName;
    }

    public void setSellerCateName(String sellerCateName) {
        this.sellerCateName = sellerCateName;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getActTime() {
        return actTime;
    }

    public void setActTime(String actTime) {
        this.actTime = actTime;
    }

    /**
     * 获取库存预警值，低于此值后，显示‘库存紧张’
     */
    public java.lang.Integer getStockWarning() {
        return this.stockWarning;
    }

    /**
     * 设置库存预警值，低于此值后，显示‘库存紧张’
     */
    public void setStockWarning(java.lang.Integer stockWarning) {
        this.stockWarning = stockWarning;
    }

	public String getWmsCategory() {
		return wmsCategory;
	}

	public void setWmsCategory(String wmsCategory) {
		this.wmsCategory = wmsCategory;
	}

	public Integer getIsCxbh() {
		return isCxbh;
	}

	public void setIsCxbh(Integer isCxbh) {
		this.isCxbh = isCxbh;
	}

	public String getRefIds() {
		return refIds;
	}

	public void setRefIds(String refIds) {
		this.refIds = refIds;
	}

	public java.lang.String getUnit() {
		return unit;
	}

	public void setUnit(java.lang.String unit) {
		this.unit = unit;
	}

	public Integer getTaocanId() {
		return taocanId;
	}

	public void setTaocanId(Integer taocanId) {
		this.taocanId = taocanId;
	}

	public Integer getPrice2Pid() {
		return price2Pid;
	}

	public void setPrice2Pid(Integer price2Pid) {
		this.price2Pid = price2Pid;
	}

	public Integer getPrice2Status() {
		return price2Status;
	}

	public void setPrice2Status(Integer price2Status) {
		this.price2Status = price2Status;
	}

	public Integer getDabiaowaFlag() {
		return dabiaowaFlag;
	}

	public void setDabiaowaFlag(Integer dabiaowaFlag) {
		this.dabiaowaFlag = dabiaowaFlag;
	}
    
}
