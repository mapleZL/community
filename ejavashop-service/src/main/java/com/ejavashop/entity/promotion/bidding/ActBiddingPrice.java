package com.ejavashop.entity.promotion.bidding;

import java.io.Serializable;

/**
 * 阶梯价格表
 * <p>Table: <strong>act_bidding_price</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>actBiddingId</td><td>{@link java.lang.Integer}</td><td>act_bidding_id</td><td>int</td><td>竞价ID</td></tr>
 *   <tr><td>saleNum</td><td>{@link java.lang.Integer}</td><td>sale_num</td><td>int</td><td>销量（虚拟销量+实际销量）</td></tr>
 *   <tr><td>price</td><td>{@link java.math.BigDecimal}</td><td>price</td><td>decimal</td><td>价格</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class ActBiddingPrice implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long    serialVersionUID = 6034041491807568642L;
    private java.lang.Integer    id;
    private java.lang.Integer    actBiddingId;
    private java.lang.Integer    saleNum;
    private java.math.BigDecimal price;
    private java.util.Date       createTime;

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
     * 获取竞价ID
     */
    public java.lang.Integer getActBiddingId() {
        return this.actBiddingId;
    }

    /**
     * 设置竞价ID
     */
    public void setActBiddingId(java.lang.Integer actBiddingId) {
        this.actBiddingId = actBiddingId;
    }

    /**
     * 获取销量（虚拟销量+实际销量）
     */
    public java.lang.Integer getSaleNum() {
        return this.saleNum;
    }

    /**
     * 设置销量（虚拟销量+实际销量）
     */
    public void setSaleNum(java.lang.Integer saleNum) {
        this.saleNum = saleNum;
    }

    /**
     * 获取价格
     */
    public java.math.BigDecimal getPrice() {
        return this.price;
    }

    /**
     * 设置价格
     */
    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
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
}