package com.ejavashop.dao.promotion.read.bidding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;

@Repository
public interface ActBiddingTypeReadDao {

    ActBiddingType get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<ActBiddingType> getActBiddingTypes(@Param("param1") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 查询所有可用的集合竞价分类
     * @return
     */
    List<ActBiddingType> getAll();
    
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