package com.phkj.entity.product;

import java.io.Serializable;
import java.util.List;

/**
 * 商品表
 * <p>Table: <strong>st_applet_product</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>productCateId</td><td>{@link java.lang.Integer}</td><td>product_cate_id</td><td>int</td><td>分类ID</td></tr>
 *   <tr><td>name1</td><td>{@link java.lang.String}</td><td>name1</td><td>varchar</td><td>商品名称建议50个字符</td></tr>
 *   <tr><td>name2</td><td>{@link java.lang.String}</td><td>name2</td><td>varchar</td><td>商品促销信息（建议100个字符）</td></tr>
 *   <tr><td>keyword</td><td>{@link java.lang.String}</td><td>keyword</td><td>varchar</td><td>商品关键字，用于检索商品，用逗号分隔</td></tr>
 *   <tr><td>productBrandId</td><td>{@link java.lang.Integer}</td><td>product_brand_id</td><td>int</td><td>品牌ID</td></tr>
 *   <tr><td>isSelf</td><td>{@link java.lang.Integer}</td><td>is_self</td><td>tinyint</td><td>1、自营；2、商家</td></tr>
 *   <tr><td>costPrice</td><td>{@link java.math.BigDecimal}</td><td>cost_price</td><td>decimal</td><td>成本价</td></tr>
 *   <tr><td>protectedPrice</td><td>{@link java.math.BigDecimal}</td><td>protected_price</td><td>decimal</td><td>保护价，最低价格不能低于</td></tr>
 *   <tr><td>marketPrice</td><td>{@link java.math.BigDecimal}</td><td>market_price</td><td>decimal</td><td>市场价</td></tr>
 *   <tr><td>mallPcPrice</td><td>{@link java.math.BigDecimal}</td><td>mall_pc_price</td><td>decimal</td><td>商城价</td></tr>
 *   <tr><td>malMobilePrice</td><td>{@link java.math.BigDecimal}</td><td>mal_mobile_price</td><td>decimal</td><td>手机端销售价格</td></tr>
 *   <tr><td>virtualSales</td><td>{@link java.lang.Integer}</td><td>virtual_sales</td><td>int</td><td>虚拟销量</td></tr>
 *   <tr><td>actualSales</td><td>{@link java.lang.Integer}</td><td>actual_sales</td><td>int</td><td>实际销量</td></tr>
 *   <tr><td>productStock</td><td>{@link java.lang.Integer}</td><td>product_stock</td><td>int</td><td>商品库存</td></tr>
 *   <tr><td>isNorm</td><td>{@link java.lang.Integer}</td><td>is_norm</td><td>tinyint</td><td>1、没有启用规格；2、启用规格</td></tr>
 *   <tr><td>normIds</td><td>{@link java.lang.String}</td><td>norm_ids</td><td>varchar</td><td>规格ID集合</td></tr>
 *   <tr><td>normName</td><td>{@link java.lang.String}</td><td>norm_name</td><td>varchar</td><td>规格属性值集合 空</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>tinyint</td><td>1、刚创建；2、提交审核；3、审核通过；4、申请驳回；5、商品删除；6、上架；7、下架</td></tr>
 *   <tr><td>isTop</td><td>{@link java.lang.Integer}</td><td>is_top</td><td>tinyint</td><td>1、不推荐；2、推荐</td></tr>
 *   <tr><td>upTime</td><td>{@link java.util.Date}</td><td>up_time</td><td>datetime</td><td>商品上架时间</td></tr>
 *   <tr><td>description</td><td>{@link java.lang.String}</td><td>description</td><td>text</td><td>商品描述信息</td></tr>
 *   <tr><td>packing</td><td>{@link java.lang.String}</td><td>packing</td><td>text</td><td>包装清单</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家ID</td></tr>
 *   <tr><td>createId</td><td>{@link java.lang.Integer}</td><td>create_id</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>timestamp</td><td>更新时间</td></tr>
 *   <tr><td>sellerCateId</td><td>{@link java.lang.Integer}</td><td>seller_cate_id</td><td>int</td><td>商家分类ID</td></tr>
 *   <tr><td>sellerIsTop</td><td>{@link java.lang.Integer}</td><td>seller_is_top</td><td>tinyint</td><td>商品推荐，1、不推荐；2、推荐  推荐的商品会显示到店铺的首页</td></tr>
 *   <tr><td>sellerState</td><td>{@link java.lang.Integer}</td><td>seller_state</td><td>tinyint</td><td>店铺状态：1、店铺正常；2、店铺关闭 默认1</td></tr>
 *   <tr><td>commentsNumber</td><td>{@link java.lang.Integer}</td><td>comments_number</td><td>int</td><td>评价总数</td></tr>
 *   <tr><td>productCateState</td><td>{@link java.lang.Integer}</td><td>product_cate_state</td><td>tinyint</td><td>平台商品分类状态：1、分类正常；2、分类关闭 默认1</td></tr>
 *   <tr><td>isInventedProduct</td><td>{@link java.lang.Integer}</td><td>is_invented_product</td><td>tinyint</td><td>是否是虚拟商品：1、实物商品；2、虚拟商品</td></tr>
 *   <tr><td>transportId</td><td>{@link java.lang.Integer}</td><td>transport_id</td><td>int</td><td>运费模板id</td></tr>
 *   <tr><td>masterImg</td><td>{@link java.lang.String}</td><td>master_img</td><td>varchar</td><td>主图</td></tr>
 *   <tr><td>productCode</td><td>{@link java.lang.String}</td><td>product_code</td><td>varchar</td><td>商品编码</td></tr>
 *   <tr><td>sellerCode</td><td>{@link java.lang.String}</td><td>seller_code</td><td>varchar</td><td>sellerCode</td></tr>
 *   <tr><td>priceStatus</td><td>{@link java.lang.Integer}</td><td>price_status</td><td>tinyint</td><td>价格类型 1：一口价，2：阶梯价，3:整箱价</td></tr>
 *   <tr><td>percentageScale</td><td>{@link java.math.BigDecimal}</td><td>percentage_scale</td><td>decimal</td><td>分佣比例</td></tr>
 *   <tr><td>stockWarning</td><td>{@link java.lang.Integer}</td><td>stock_warning</td><td>int</td><td>库存预警值，低于此值后，显示‘库存紧张’</td></tr>
 *   <tr><td>productUrl</td><td>{@link java.lang.String}</td><td>product_url</td><td>varchar</td><td>productUrl</td></tr>
 *   <tr><td>inStockWarning</td><td>{@link java.lang.Integer}</td><td>in_stock_warning</td><td>int</td><td>inStockWarning</td></tr>
 *   <tr><td>refIds</td><td>{@link java.lang.String}</td><td>ref_ids</td><td>varchar</td><td>关联推荐搭配商品</td></tr>
 *   <tr><td>unit</td><td>{@link java.lang.String}</td><td>unit</td><td>varchar</td><td>销售单位</td></tr>
 *   <tr><td>otherCategory</td><td>{@link java.lang.Integer}</td><td>other_category</td><td>int</td><td>其他分类</td></tr>
 * </table>
 *
 */
public class StAppletProduct implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 1L;
    private java.lang.Integer    id;
    private java.lang.Integer    productCateId;
    private java.lang.String     name1;
    private java.lang.String     name2;
    private java.lang.String     keyword;
    private java.lang.Integer    productBrandId;
    private java.lang.Integer    isSelf;
    private java.math.BigDecimal costPrice;
    private java.math.BigDecimal protectedPrice;
    private java.math.BigDecimal marketPrice;
    private java.math.BigDecimal mallPcPrice;
    private java.math.BigDecimal malMobilePrice;
    private java.lang.Integer    virtualSales;
    private java.lang.Integer    actualSales;
    private java.lang.Integer    productStock;
    private java.lang.Integer    isNorm;
    private java.lang.String     normIds;
    private java.lang.String     normName;
    private java.lang.Integer    state;
    private java.lang.Integer    isTop;
    private java.util.Date       upTime;
    private java.lang.String     description;
    private java.lang.String     packing;
    private java.lang.Integer    sellerId;
    private java.lang.Integer    createId;
    private java.util.Date       createTime;
    private java.util.Date       updateTime;
    private java.lang.Integer    sellerCateId;
    private java.lang.Integer    sellerIsTop;
    private java.lang.Integer    sellerState;
    private java.lang.Integer    commentsNumber;
    private java.lang.Integer    productCateState;
    private java.lang.Integer    isInventedProduct;
    private java.lang.Integer    transportId;
    private java.lang.String     masterImg;
    private java.lang.String     productCode;
    private java.lang.String     sellerCode;
    private java.lang.Integer    priceStatus;
    private java.math.BigDecimal percentageScale;
    private java.lang.Integer    stockWarning;
    private java.lang.String     productUrl;
    private java.lang.Integer    inStockWarning;
    private java.lang.String     refIds;
    private java.lang.String     unit;
    private java.lang.Integer    otherCategory;
    private String               villageCode;

    private List<ProductPicture> pictures;

    /**
     * 产品表 state  状态：1、刚创建；
     */
    public final static int      STATE_1          = 1;
    /**
     * 产品表 state  状态：2、提交审核；
     */
    public final static int      STATE_2          = 2;
    /**
     * 产品表 state  状态：3、审核通过；
     */
    public final static int      STATE_3          = 3;
    /**
     * 产品表 state  状态：4、申请驳回；
     */
    public final static int      STATE_4          = 4;
    /**
     * 产品表 state  状态：5、商品删除
     */
    public final static int      STATE_5          = 5;
    /**
     * 产品表 state  状态：6、上架
     */
    public final static int      STATE_6          = 6;
    /**
     * 产品表 state  状态：7、下架
     */
    public final static int      STATE_7          = 7;

    /**
     * 产品表 seller_state  店铺状态：1、店铺正常；
     */
    public final static int      SELLER_STATE_1   = 1;

    /**
     * 产品表 seller_state  店铺状态：2、店铺关闭
     */
    public final static int      SELLER_STATE_2   = 2;

    public enum IsSelfEnum {
        //1:自营，2:商家
        SELF("默认", 1), SELLER("提交审核", 2);

        private String name;  //显示的名字
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

    /**
     * 获取id
     */
    public java.lang.Integer getId() {
        return this.id;
    }

    /**
     * 设置id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取分类ID
     */
    public java.lang.Integer getProductCateId() {
        return this.productCateId;
    }

    /**
     * 设置分类ID
     */
    public void setProductCateId(java.lang.Integer productCateId) {
        this.productCateId = productCateId;
    }

    /**
     * 获取商品名称建议50个字符
     */
    public java.lang.String getName1() {
        return this.name1;
    }

    /**
     * 设置商品名称建议50个字符
     */
    public void setName1(java.lang.String name1) {
        this.name1 = name1;
    }

    /**
     * 获取商品促销信息（建议100个字符）
     */
    public java.lang.String getName2() {
        return this.name2;
    }

    /**
     * 设置商品促销信息（建议100个字符）
     */
    public void setName2(java.lang.String name2) {
        this.name2 = name2;
    }

    /**
     * 获取商品关键字，用于检索商品，用逗号分隔
     */
    public java.lang.String getKeyword() {
        return this.keyword;
    }

    /**
     * 设置商品关键字，用于检索商品，用逗号分隔
     */
    public void setKeyword(java.lang.String keyword) {
        this.keyword = keyword;
    }

    /**
     * 获取品牌ID
     */
    public java.lang.Integer getProductBrandId() {
        return this.productBrandId;
    }

    /**
     * 设置品牌ID
     */
    public void setProductBrandId(java.lang.Integer productBrandId) {
        this.productBrandId = productBrandId;
    }

    /**
     * 获取1、自营；2、商家
     */
    public java.lang.Integer getIsSelf() {
        return this.isSelf;
    }

    /**
     * 设置1、自营；2、商家
     */
    public void setIsSelf(java.lang.Integer isSelf) {
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
     * 获取手机端销售价格
     */
    public java.math.BigDecimal getMalMobilePrice() {
        return this.malMobilePrice;
    }

    /**
     * 设置手机端销售价格
     */
    public void setMalMobilePrice(java.math.BigDecimal malMobilePrice) {
        this.malMobilePrice = malMobilePrice;
    }

    /**
     * 获取虚拟销量
     */
    public java.lang.Integer getVirtualSales() {
        return this.virtualSales;
    }

    /**
     * 设置虚拟销量
     */
    public void setVirtualSales(java.lang.Integer virtualSales) {
        this.virtualSales = virtualSales;
    }

    /**
     * 获取实际销量
     */
    public java.lang.Integer getActualSales() {
        return this.actualSales;
    }

    /**
     * 设置实际销量
     */
    public void setActualSales(java.lang.Integer actualSales) {
        this.actualSales = actualSales;
    }

    /**
     * 获取商品库存
     */
    public java.lang.Integer getProductStock() {
        return this.productStock;
    }

    /**
     * 设置商品库存
     */
    public void setProductStock(java.lang.Integer productStock) {
        this.productStock = productStock;
    }

    /**
     * 获取1、没有启用规格；2、启用规格
     */
    public java.lang.Integer getIsNorm() {
        return this.isNorm;
    }

    /**
     * 设置1、没有启用规格；2、启用规格
     */
    public void setIsNorm(java.lang.Integer isNorm) {
        this.isNorm = isNorm;
    }

    /**
     * 获取规格ID集合
     */
    public java.lang.String getNormIds() {
        return this.normIds;
    }

    /**
     * 设置规格ID集合
     */
    public void setNormIds(java.lang.String normIds) {
        this.normIds = normIds;
    }

    /**
     * 获取规格属性值集合 空
     */
    public java.lang.String getNormName() {
        return this.normName;
    }

    /**
     * 设置规格属性值集合 空
     */
    public void setNormName(java.lang.String normName) {
        this.normName = normName;
    }

    /**
     * 获取1、刚创建；2、提交审核；3、审核通过；4、申请驳回；5、商品删除；6、上架；7、下架
     */
    public java.lang.Integer getState() {
        return this.state;
    }

    /**
     * 设置1、刚创建；2、提交审核；3、审核通过；4、申请驳回；5、商品删除；6、上架；7、下架
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取1、不推荐；2、推荐
     */
    public java.lang.Integer getIsTop() {
        return this.isTop;
    }

    /**
     * 设置1、不推荐；2、推荐
     */
    public void setIsTop(java.lang.Integer isTop) {
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
    public java.lang.String getDescription() {
        return this.description;
    }

    /**
     * 设置商品描述信息
     */
    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    /**
     * 获取包装清单
     */
    public java.lang.String getPacking() {
        return this.packing;
    }

    /**
     * 设置包装清单
     */
    public void setPacking(java.lang.String packing) {
        this.packing = packing;
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
     * 获取创建人
     */
    public java.lang.Integer getCreateId() {
        return this.createId;
    }

    /**
     * 设置创建人
     */
    public void setCreateId(java.lang.Integer createId) {
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
    public java.lang.Integer getSellerCateId() {
        return this.sellerCateId;
    }

    /**
     * 设置商家分类ID
     */
    public void setSellerCateId(java.lang.Integer sellerCateId) {
        this.sellerCateId = sellerCateId;
    }

    /**
     * 获取商品推荐，1、不推荐；2、推荐  推荐的商品会显示到店铺的首页
     */
    public java.lang.Integer getSellerIsTop() {
        return this.sellerIsTop;
    }

    /**
     * 设置商品推荐，1、不推荐；2、推荐  推荐的商品会显示到店铺的首页
     */
    public void setSellerIsTop(java.lang.Integer sellerIsTop) {
        this.sellerIsTop = sellerIsTop;
    }

    /**
     * 获取店铺状态：1、店铺正常；2、店铺关闭 默认1
     */
    public java.lang.Integer getSellerState() {
        return this.sellerState;
    }

    /**
     * 设置店铺状态：1、店铺正常；2、店铺关闭 默认1
     */
    public void setSellerState(java.lang.Integer sellerState) {
        this.sellerState = sellerState;
    }

    /**
     * 获取评价总数
     */
    public java.lang.Integer getCommentsNumber() {
        return this.commentsNumber;
    }

    /**
     * 设置评价总数
     */
    public void setCommentsNumber(java.lang.Integer commentsNumber) {
        this.commentsNumber = commentsNumber;
    }

    /**
     * 获取平台商品分类状态：1、分类正常；2、分类关闭 默认1
     */
    public java.lang.Integer getProductCateState() {
        return this.productCateState;
    }

    /**
     * 设置平台商品分类状态：1、分类正常；2、分类关闭 默认1
     */
    public void setProductCateState(java.lang.Integer productCateState) {
        this.productCateState = productCateState;
    }

    /**
     * 获取是否是虚拟商品：1、实物商品；2、虚拟商品
     */
    public java.lang.Integer getIsInventedProduct() {
        return this.isInventedProduct;
    }

    /**
     * 设置是否是虚拟商品：1、实物商品；2、虚拟商品
     */
    public void setIsInventedProduct(java.lang.Integer isInventedProduct) {
        this.isInventedProduct = isInventedProduct;
    }

    /**
     * 获取运费模板id
     */
    public java.lang.Integer getTransportId() {
        return this.transportId;
    }

    /**
     * 设置运费模板id
     */
    public void setTransportId(java.lang.Integer transportId) {
        this.transportId = transportId;
    }

    /**
     * 获取主图
     */
    public java.lang.String getMasterImg() {
        return this.masterImg;
    }

    /**
     * 设置主图
     */
    public void setMasterImg(java.lang.String masterImg) {
        this.masterImg = masterImg;
    }

    /**
     * 获取商品编码
     */
    public java.lang.String getProductCode() {
        return this.productCode;
    }

    /**
     * 设置商品编码
     */
    public void setProductCode(java.lang.String productCode) {
        this.productCode = productCode;
    }

    /**
     * 获取sellerCode
     */
    public java.lang.String getSellerCode() {
        return this.sellerCode;
    }

    /**
     * 设置sellerCode
     */
    public void setSellerCode(java.lang.String sellerCode) {
        this.sellerCode = sellerCode;
    }

    /**
     * 获取价格类型 1：一口价，2：阶梯价，3:整箱价
     */
    public java.lang.Integer getPriceStatus() {
        return this.priceStatus;
    }

    /**
     * 设置价格类型 1：一口价，2：阶梯价，3:整箱价
     */
    public void setPriceStatus(java.lang.Integer priceStatus) {
        this.priceStatus = priceStatus;
    }

    /**
     * 获取分佣比例
     */
    public java.math.BigDecimal getPercentageScale() {
        return this.percentageScale;
    }

    /**
     * 设置分佣比例
     */
    public void setPercentageScale(java.math.BigDecimal percentageScale) {
        this.percentageScale = percentageScale;
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

    /**
     * 获取productUrl
     */
    public java.lang.String getProductUrl() {
        return this.productUrl;
    }

    /**
     * 设置productUrl
     */
    public void setProductUrl(java.lang.String productUrl) {
        this.productUrl = productUrl;
    }

    /**
     * 获取inStockWarning
     */
    public java.lang.Integer getInStockWarning() {
        return this.inStockWarning;
    }

    /**
     * 设置inStockWarning
     */
    public void setInStockWarning(java.lang.Integer inStockWarning) {
        this.inStockWarning = inStockWarning;
    }

    /**
     * 获取关联推荐搭配商品
     */
    public java.lang.String getRefIds() {
        return this.refIds;
    }

    /**
     * 设置关联推荐搭配商品
     */
    public void setRefIds(java.lang.String refIds) {
        this.refIds = refIds;
    }

    /**
     * 获取销售单位
     */
    public java.lang.String getUnit() {
        return this.unit;
    }

    /**
     * 设置销售单位
     */
    public void setUnit(java.lang.String unit) {
        this.unit = unit;
    }

    /**
     * 获取其他分类
     */
    public java.lang.Integer getOtherCategory() {
        return this.otherCategory;
    }

    /**
     * 设置其他分类
     */
    public void setOtherCategory(java.lang.Integer otherCategory) {
        this.otherCategory = otherCategory;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }

    public List<ProductPicture> getPictures() {
        return pictures;
    }

    public void setPictures(List<ProductPicture> pictures) {
        this.pictures = pictures;
    }

    @Override
    public String toString() {
        return "StAppletProduct [id=" + id + ", productCateId=" + productCateId + ", name1=" + name1
               + ", name2=" + name2 + ", keyword=" + keyword + ", productBrandId=" + productBrandId
               + ", isSelf=" + isSelf + ", costPrice=" + costPrice + ", protectedPrice="
               + protectedPrice + ", marketPrice=" + marketPrice + ", mallPcPrice=" + mallPcPrice
               + ", malMobilePrice=" + malMobilePrice + ", virtualSales=" + virtualSales
               + ", actualSales=" + actualSales + ", productStock=" + productStock + ", isNorm="
               + isNorm + ", normIds=" + normIds + ", normName=" + normName + ", state=" + state
               + ", isTop=" + isTop + ", upTime=" + upTime + ", description=" + description
               + ", packing=" + packing + ", sellerId=" + sellerId + ", createId=" + createId
               + ", createTime=" + createTime + ", updateTime=" + updateTime + ", sellerCateId="
               + sellerCateId + ", sellerIsTop=" + sellerIsTop + ", sellerState=" + sellerState
               + ", commentsNumber=" + commentsNumber + ", productCateState=" + productCateState
               + ", isInventedProduct=" + isInventedProduct + ", transportId=" + transportId
               + ", masterImg=" + masterImg + ", productCode=" + productCode + ", sellerCode="
               + sellerCode + ", priceStatus=" + priceStatus + ", percentageScale="
               + percentageScale + ", stockWarning=" + stockWarning + ", productUrl=" + productUrl
               + ", inStockWarning=" + inStockWarning + ", refIds=" + refIds + ", unit=" + unit
               + ", otherCategory=" + otherCategory + ", villageCode=" + villageCode + "]";
    }

}