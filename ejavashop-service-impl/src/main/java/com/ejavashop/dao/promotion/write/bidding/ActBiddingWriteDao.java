package com.ejavashop.dao.promotion.write.bidding;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBidding;

@Repository
public interface ActBiddingWriteDao {

    Integer insert(ActBidding actBidding);

    Integer update(ActBidding actBidding);

    int updateState(@Param("id") Integer id, @Param("state") int state);

    int updateActState(@Param("id") Integer id, @Param("actState") int actState);

    //    /**
    //     * 修改活动库存<br>
    //     * set `stock`= `stock` - #{number}
    //     * @param id 活动ID
    //     * @param number 修改的数量（如果是负数，则表示还原库存）
    //     * @return
    //     */
    //    Integer updateStock(@Param("id") Integer id, @Param("number") Integer number);
    //
    //    /**
    //     * 修改活动销量<br>
    //     * set `sale_num`= `sale_num` + #{number}
    //     * @param id 活动ID
    //     * @param number 修改的数量
    //     * @return
    //     */
    //    Integer updateSaleNum(@Param("id") Integer id, @Param("number") Integer number);

    /**
     * 修改活动商品的库存和销量，库存减saleNum，销量加saleNum，订单支付时调用<br>
     * `stock`= `stock` - saleNum<br>
     * `sale_num` = `sale_num` + saleNum<br>
     * @param id
     * @param saleNum
     * @return
     */
    Integer updateStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);

    /**
     * 修改活动商品的库存和销量，库存加saleNum，销量减saleNum，订单取消时调用<br>
     * `stock`= `stock` + saleNum<br>
     * `sale_num` = `sale_num` - saleNum<br>
     * @param id
     * @param saleNum
     * @return
     */
    Integer backStockAndActualSales(@Param("id") Integer id, @Param("saleNum") Integer saleNum);

    /**
     * 查询所有过期、没有修改状态的集合竞价 <br/>
     * `state` = 3
        and `act_state` = 2
        and `end_time` &lt; now()
     * @return
     */
    //List<ActBidding> getAllEnd();
    /**
     * 查询所有需要生成尾款订单的集合竞价 <br/>
     * `state` = 3
        and `act_state` = 3
        and `execute_state` = 0
        and `first_end_time` &lt; now()
     * @return
     */
   // List<ActBidding> getExecuteStateNo();

   

    /**
     * 更新尾款的状态
     * @param id
     * @param executeState1
     * @return
     */
    int updateExecuteState(@Param("id") Integer id, @Param("executeState") int executeState);

    /**
     * 集合竞价分类停用或者启用的时候把团购分类下面的团购商品设置为停用或者启用
     * @param type 团购分类ID
     * @param typeState 团购分类的状态
     * @return
     */
    int updateByTypeState(@Param("type") Integer type, @Param("typeState") int typeState);

    /**
     * 根据集合竞价商品分类统计数量
     * @param id
     * @return
     */
    int countByType(Integer id);

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    Boolean updateStateAndAuditOpinion(@Param("id") Integer id, @Param("state") int state,
                                       @Param("auditOpinion") String auditOpinion);
}