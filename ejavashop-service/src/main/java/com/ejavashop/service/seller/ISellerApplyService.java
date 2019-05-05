package com.ejavashop.service.seller;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.seller.SellerApply;

/**
 * 商家申请
 *                       
 * @Filename: ISellerApplyService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerApplyService {

    /**
     * 根据id取得商家申请表
     * @param  sellerApplyId
     * @return
     */
    ServiceResult<SellerApply> getSellerApplyById(Integer sellerApplyId);

    /**
     * 保存商家申请表
     * @param  sellerApply
     * @return
     */
    ServiceResult<Integer> saveSellerApply(SellerApply sellerApply);

    /**
     * 修改商家申请
     * @param sellerApply
     * @return
     */
    ServiceResult<Boolean> updateSellerApply(SellerApply sellerApply);

    /**
     * 删除该商家申请,同时删除该商家账号
     * @param id
     * @return
     */
    ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId);

    /**
     * 根据条件分页查询商家申请，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerApply>> getSellerApplys(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据用户ID获取其商家入驻申请
     * @param userid
     * @return
     */
    ServiceResult<SellerApply> getSellerApplyByUser(Integer memberId);

    /**
     * 审核商家申请，审核通过同步更新商家状态为审核通过
     * @param sellerApplyId 商家申请ID
     * @param state 申请状态
     * @param optUserId 操作人ID
     * @return
     */
    ServiceResult<Boolean> auditSellerApply(Integer sellerApplyId, Integer state,
                                            Integer optUserId,String sellerCode);

    /**
     * 
     * 保存商家申请(平台添加商家申请用)<br>
     * <li>新增一个普通用户表（兼容用户端申请）
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @param ip 操作IP
     * @return
     */
    ServiceResult<Boolean> saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                   String sellerName, String ip);

    /**
     * 
     * 修改商家申请(平台修改商家申请用)
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @return
     */
    ServiceResult<Boolean> updateSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                     String sellerName);

    /**
     * 
     * 保存商家申请(用户端商家申请用)<br>
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @param memberId
     * @return
     */
    ServiceResult<Boolean> saveSellerApplyForFront(SellerApply sellerApply, String userName,
                                                   String sellerName, Integer memberId);

    /**
     * 
     * 修改商家申请(用户端修改商家申请用)
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @return
     */
    ServiceResult<Boolean> updateSellerApplyForFront(SellerApply sellerApply, String userName,
                                                     String sellerName);

    /**
     * 根据公司名称查询入驻申请
     * @param company
     * @return
     */
    ServiceResult<List<SellerApply>> getSellerApplyByCompany(String company);
}