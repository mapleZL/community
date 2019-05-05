package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.seller.SellerApplyBrandReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.seller.SellerApplyBrandWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerApplyBrand;

/**
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
@Component(value = "sellerBrandModel")
public class SellerBrandModel {
    @Resource
    private SellerApplyBrandReadDao sellerApplyBrandReadDao;
    @Resource
    private SellerApplyBrandWriteDao sellerApplyBrandWriteDao;
    @Resource
    private SellerReadDao           sellerReadDao;
    @Resource
    private SellerWriteDao           sellerWriteDao;

    public Boolean save(SellerApplyBrand brand) {
        //1. business check
        if (null == brand)
            throw new BusinessException("保存商品品牌失败，品牌信息为空");
        if (StringUtil.isEmpty(brand.getName()))
            throw new BusinessException("保存商品品牌失败，品牌名称为空");
        Integer hasName = sellerApplyBrandReadDao.getByName(brand.getName());
        if (hasName > 0)
            throw new BusinessException("保存商品品牌失败，品牌名称已经处在");
        //2. dbConstrains
        this.dbConstrains(brand);
        //3. dbOper
        return sellerApplyBrandWriteDao.save(brand) > 0;
    }

    public SellerApplyBrand getById(Integer id) {

        if (null == id || 0 == id)
            throw new BusinessException("无效的id");
        return sellerApplyBrandReadDao.getById(id);
    }

    public List<SellerApplyBrand> page(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(sellerApplyBrandReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<SellerApplyBrand> list = sellerApplyBrandReadDao.page(queryMap, start, size);
        //构造sellerName、createUser、updateUser,此处会存在性能问题，如果列表页面不需要展示人名，可以去掉
        if (null != list && list.size() > 0) {
            for (SellerApplyBrand brand : list) {
                Seller seller = sellerReadDao.getSellerByMemberId(brand.getCreateId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    brand.setSellerName(seller.getSellerName());
                }
                if (null != seller && !StringUtil.isEmpty(seller.getName())) {
                    brand.setCreateUser(seller.getName());
                }
                if (null != seller && brand.getCreateId() == brand.getUpdateId()) {
                    brand.setUpdateUser(seller.getName());
                } else {
                    seller = sellerReadDao.getSellerByMemberId(brand.getUpdateId());
                    if (null != seller && !StringUtil.isEmpty(seller.getName())) {
                        brand.setUpdateUser(seller.getName());
                    }
                }

            }
        }
        return list;
    }

    public List<SellerApplyBrand> todoList(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(sellerApplyBrandReadDao.todoCount(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }

        List<SellerApplyBrand> list = sellerApplyBrandReadDao.todoList(queryMap, start, size);
        //构造createUser、updateUser,此处会存在性能问题，如果列表页面不需要展示人名，可以去掉
        if (null != list && list.size() > 0) {
            for (SellerApplyBrand brand : list) {
                Seller seller = sellerReadDao.get(brand.getCreateId());
                if (null != seller && !StringUtil.isEmpty(seller.getName())) {
                    brand.setCreateUser(seller.getName());
                }
                if (brand.getCreateId() == brand.getUpdateId()) {
                    brand.setUpdateUser(seller.getName());
                } else {
                    seller = sellerReadDao.get(brand.getUpdateId());
                    if (null != seller && !StringUtil.isEmpty(seller.getName())) {
                        brand.setUpdateUser(seller.getName());
                    }
                }

            }
        }
        return list;
    }

    public Boolean update(SellerApplyBrand brand) {

        if (null == brand)
            throw new BusinessException("更新品牌信息失败，品牌信息为空");
        if (null == brand.getId() || 0 == brand.getId())
            throw new BusinessException("更新品牌信息失败，品牌id为空");
        //平台审核通过的不可以修改
        SellerApplyBrand dbBrand = sellerApplyBrandReadDao.getById(brand.getId());
        if (null == dbBrand)
            throw new BusinessException("更新品牌信息失败，品牌信息无效");
        if (dbBrand.getState().equals(SellerApplyBrand.Status.SUCCESS.getValue()))
            throw new BusinessException("更新品牌信息失败，平台审核成功的品牌不可修改，请联系平台管理员");
        //重置状态
        brand.setState(SellerApplyBrand.Status.DEFAULT.getValue());
        return sellerApplyBrandWriteDao.update(brand) > 0;
    }

    public Boolean commit(Integer id) {

        if (null == id || 0 == id)
            throw new BusinessException("无效的id");
        SellerApplyBrand dbBrand = sellerApplyBrandReadDao.getById(id);
        if (null == dbBrand)
            throw new BusinessException("提交审核品牌失败，该品牌不存在");
        //显示中、删除的品牌不允许提交审核
        if (SellerApplyBrand.Status.SUCCESS.getValue() == dbBrand.getState())
            throw new BusinessException("提交审核品牌失败，该品牌已经审核通过");
        if (SellerApplyBrand.Status.DEL.getValue() == dbBrand.getState())
            throw new BusinessException("提交审核品牌失败，该品牌已经删除");
        SellerApplyBrand brand = new SellerApplyBrand();
        brand.setId(id);
        brand.setState(ProductBrand.Status.COMMIT.getValue());
        return sellerApplyBrandWriteDao.update(brand) > 1;
    }

    public Boolean del(Integer id) {

        if (null == id || 0 == id)
            throw new BusinessException("无效的id");
        SellerApplyBrand dbBrand = sellerApplyBrandReadDao.getById(id);
        if (null == dbBrand)
            throw new BusinessException("删除品牌失败，该品牌不存在");
        //显示中的品牌不允许删除
        if (SellerApplyBrand.Status.SUCCESS.getValue() == dbBrand.getState())
            throw new BusinessException("删除品牌失败，该品牌已经审核通过，请联系平台管理员");
        SellerApplyBrand brand = new SellerApplyBrand();
        brand.setId(id);
        brand.setState(ProductBrand.Status.DEL.getValue());
        return sellerApplyBrandWriteDao.update(brand) > 0;
    }

    private void dbConstrains(SellerApplyBrand brand) {
        brand.setImage(StringUtil.dbSafeString(brand.getImage(), false, 200));
        brand.setName(StringUtil.dbSafeString(brand.getName(), false, 50));
        brand.setNameFirst(StringUtil.dbSafeString(brand.getNameFirst(), false, 200));
        brand.setExplainInfo(StringUtil.dbSafeString(brand.getExplainInfo(), false, 255));
    }
}
