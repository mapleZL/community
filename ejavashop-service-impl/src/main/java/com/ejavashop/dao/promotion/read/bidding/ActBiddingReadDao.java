package com.ejavashop.dao.promotion.read.bidding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBidding;

@Repository
public interface ActBiddingReadDao {

    ActBidding get(java.lang.Integer id);

    int getActBiddingsCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActBidding> getActBiddings(@Param("queryMap") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    int getActBiddingsFrontCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActBidding> getActBiddingsFront(@Param("queryMap") Map<String, String> queryMap,
                                         @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 取得该团购所属分类下的前5个商品
     * @param type
     * @param topNum
     * @return
     */
    List<ActBidding> getActBiddingsByType(@Param("type") int type, @Param("topNum") int topNum);
    /**
     * 查询所有过期、没有修改状态的集合竞价 <br/>
     * `state` = 3
        and `act_state` = 2
        and `end_time` &lt; now()
     * @return
     */
    List<ActBidding> getAllEnd();
    /**
     * 查询所有需要生成尾款订单的集合竞价 <br/>
     * `state` = 3
        and `act_state` = 3
        and `execute_state` = 0
        and `first_end_time` &lt; now()
     * @return
     */
    List<ActBidding> getExecuteStateNo();
    

}