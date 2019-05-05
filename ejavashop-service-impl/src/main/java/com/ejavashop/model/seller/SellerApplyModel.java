package com.ejavashop.model.seller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.Md5;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.member.MemberReadDao;
import com.ejavashop.dao.shop.read.seller.SellerApplyReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.read.seller.SellerRolesReadDao;
import com.ejavashop.dao.shop.read.seller.SellerUserReadDao;
import com.ejavashop.dao.shop.read.system.RegionsReadDao;
import com.ejavashop.dao.shop.read.system.SystemResourcesReadDao;
import com.ejavashop.dao.shop.write.member.MemberWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerApplyWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerResourcesRolesWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerRolesWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerUserWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dao.shop.write.system.RegionsWriteDao;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerApply;
import com.ejavashop.entity.seller.SellerResourcesRoles;
import com.ejavashop.entity.seller.SellerRoles;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.SystemResources;

@Component(value = "sellerApplyModel")
public class SellerApplyModel {
    @Resource
    private SellerApplyWriteDao          sellerApplyWriteDao;
    @Resource
    private SellerApplyReadDao          sellerApplyReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private SellerReadDao                sellerReadDao;
    @Resource
    private SellerRolesReadDao           sellerRolesReadDao;
    @Resource
    private SellerRolesWriteDao          sellerRolesWriteDao;
    @Resource
    private SellerResourcesRolesWriteDao sellerResourcesRolesWriteDao;
    @Resource
    private SystemResourcesReadDao       systemResourcesReadDao;
    @Resource
    private MemberReadDao                memberReadDao;
    @Resource
    private MemberWriteDao               memberWriteDao;
    @Resource
    private SellerUserReadDao            sellerUserReadDao;
    @Resource
    private SellerUserWriteDao           sellerUserWriteDao;
    @Resource
    private RegionsWriteDao regionsWriteDao;
    @Resource
    private RegionsReadDao regionsReadDao;
    
    private static final Logger  ILog = LogManager.getLogger("oms_interface");

    public boolean updateSellerApply(SellerApply sellerApply) {
        return sellerApplyWriteDao.update(sellerApply) > 0;
    }

    public Integer getSellerApplysCount(Map<String, String> queryMap) {
        return sellerApplyReadDao.getSellerApplysCount(queryMap);
    }

    public List<SellerApply> getSellerApplys(Map<String, String> queryMap, Integer start,
                                             Integer size) {
        return sellerApplyReadDao.getSellerApplys(queryMap, start, size);
    }

    public boolean delete(Integer id, Integer memberId) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 数据库中的申请
            SellerApply applyDb = sellerApplyReadDao.get(id);
            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
                throw new BusinessException("只能删除提交申请和审核失败状态的商家申请！");
            }

            // 删除商家入驻申请
            boolean applyDel = sellerApplyWriteDao.delete(id) > 0;
            if (!applyDel) {
                throw new BusinessException("删除商家入驻申请时失败！");
            }

            Seller sellerDb = sellerReadDao.getSellerByMemberId(memberId);
            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
                throw new BusinessException("只能删除提交申请状态的商家申请！");
            }

            //删除商家账号
            boolean sellerDel = sellerWriteDao.deleteByMemberId(memberId) > 0;
            if (!sellerDel) {
                throw new BusinessException("删除商家入驻申请时失败（删除关联账号时）！");
            }

            transactionManager.commit(status);

            return applyDel && sellerDel;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 根据id取得商家申请表
     * @param  sellerApplyId
     * @return
     */
    public SellerApply getSellerApplyById(Integer sellerApplyId) {
        return sellerApplyReadDao.get(sellerApplyId);
    }

    /**
     * 保存商家申请表
     * @param  sellerApply
     * @return
     */
    public Integer saveSellerApply(SellerApply sellerApply) {
        return sellerApplyWriteDao.insert(sellerApply);
    }

    /**
     * 根据用户ID获取其商家入驻申请
     * @param userid
     * @return
     */
    public SellerApply getSellerApplyByUser(Integer userid) {
        return sellerApplyReadDao.getSellerApplyByUserId(userid);
    }

    public boolean auditSellerApply(Integer sellerApplyId, Integer state, Integer optUserId,String sellerCode) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            // 数据库中的申请
            SellerApply applyDb = sellerApplyReadDao.get(sellerApplyId);

            // 如果审核通过，同步修改商家表
            if (SellerApply.STATE_2_DONE == state.intValue()) {
                // 修改商家申请
                SellerApply sellerApply = new SellerApply();
                sellerApply.setId(sellerApplyId);
                sellerApply.setState(SellerApply.STATE_2_DONE);
                sellerApply.setOptId(optUserId);
                Integer row = sellerApplyWriteDao.update(sellerApply);
                if (row == 0) {
                    throw new BusinessException("修改商家申请状态时失败！");
                }
/*
                // 修改商家状态
                row = sellerWriteDao.auditSeller(applyDb.getUserId(), Seller.AUDIT_STATE_2_DONE);
                if (row == 0) {
                    throw new BusinessException("修改商家状态时失败！");
                }
*/
                // 初始化商家管理的数据
                // 1、建立超级管理员的角色
                // 2、给超级管理员角色赋权限
                // 3、建立一个用户初始化为超级管理员
                Seller seller = sellerReadDao.getByMemberId(applyDb.getUserId());
                Member member = memberReadDao.get(applyDb.getUserId());
                // 1
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("q_sellerId", seller.getId() + "");
                Integer sellerRolesCount = sellerRolesReadDao.getSellerRolesCount(queryMap);
                Integer count = sellerUserReadDao.getCount(queryMap);
                if (sellerRolesCount == 0 && count == 0) {
                    SellerRoles roles = new SellerRoles();
                    roles.setSellerId(seller.getId());
                    roles.setRoleCode("seller_admin");
                    roles.setRolesName("店铺超级管理员");
                    roles.setStatus(SellerRoles.STATUS_1);
                    roles.setContent("店铺超级管理员");
                    roles.setUserId(0);
                    Integer insert = sellerRolesWriteDao.insert(roles);
                    if (insert == 0) {
                        throw new BusinessException("初始化商家角色数据时失败！");
                    }
                    // 2
                    initRolesResource(roles.getId(), ConstantsEJS.SELLER_RESOURCE_ROOT);

                    // 3
                    SellerUser sellerUser = new SellerUser();
                    sellerUser.setName(seller.getName());
                    sellerUser.setPassword(member.getPassword());
                    sellerUser.setCode("");
                    sellerUser.setRealName("");
                    sellerUser.setPhone("");
                    sellerUser.setJob("");
                    sellerUser.setSellerId(seller.getId());
                    sellerUser.setRoleId(roles.getId());
                    sellerUser.setState(SellerUser.STATE_NORM);
                    sellerUser.setCreateId(0);
                    sellerUser.setUpdateId(0);
                    insert = sellerUserWriteDao.insert(sellerUser);
                    if (insert == 0) {
                        throw new BusinessException("初始化商家管理员数据时失败！");
                    }
                    //给商家设置商家编码
                    seller.setSellerCode(sellerCode);
                    seller.setAuditStatus(Seller.AUDIT_STATE_2_DONE);
                    sellerWriteDao.update(seller);
                }

            } else if (SellerApply.STATE_4_FAIL == state.intValue()) {
                // 商家申请的状态不可逆，审核通过后不能再修改成审核失败状态
                if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
                    && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
                    throw new BusinessException("商家申请已经审核通过，不能修改状态！");
                }
                // 如果商家申请被驳回，直接修改商家申请状态，不修改商家状态
                // 修改商家申请
                SellerApply sellerApply = new SellerApply();
                sellerApply.setId(sellerApplyId);
                sellerApply.setState(SellerApply.STATE_4_FAIL);
                sellerApply.setOptId(optUserId);
                Integer row = sellerApplyWriteDao.update(sellerApply);
                if (row == 0) {
                    throw new BusinessException("修改商家申请状态时失败！");
                }
            }
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            throw e;
        }
    }
    
    /**
     * 推送货主信息给oms
     * @param member
     * @param applyDb
     * @param seller
     */
//    public void syncOmsMember(Member member,SellerApply applyDb,Seller seller){
//    	try {
//    		//推送货主信息给oms
//    		Map<String, Object> omsMap = new HashMap<String, Object>();
//    		omsMap.put(dawa_ttx_config.RELATION_ID, seller.getSellerCode()); 
//    		//名称需要从dawa_ttx_config拿 避免输入错误影响查询结果
//    		omsMap.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.SELLER_USER);
//    		//用户名臣
//    		omsMap.put("userName", member.getName());
//    		//盐值 = MD5(用户名)
//    		//String saltValue = Md5.getMd5String(member.getName());
//    		//盐值
//    		//omsMap.put("saltValue",saltValue);
//    		//用户密码 = MD5(盐值 +MD5(用户明文密码))
//    		omsMap.put("password", member.getPassword());
//    		
//    		Map<String, Object> corporationMap = new HashMap<String, Object>();
//    		//公司编码
//    		corporationMap.put("code", seller.getSellerCode());
//    		//公司名称
//    		corporationMap.put("name", applyDb.getCompany());
//    		//公司logo地址
//    		corporationMap.put("logoUrl", "");
//    		//全名
//    		corporationMap.put("fullName", applyDb.getCompany());
//    		//联系人
//    		corporationMap.put("attentionTo", applyDb.getLegalPerson());
//    		//电话1
//    		corporationMap.put("phoneNum1", applyDb.getPersonPhone());
//    		//电话2
//    		corporationMap.put("phoneNum2", applyDb.getPersonPhone());
//    		//手机
//    		corporationMap.put("mobileNum", applyDb.getPersonPhone());
//    		//邮箱
//    		corporationMap.put("email", applyDb.getEmail());
//    		//邮编
//    		corporationMap.put("zip", "");
//    	    //传真
//    		corporationMap.put("faxNum", applyDb.getFax());
//    		//国家  默认值，目前用户信息没有区域属性，但是传入OMS需要
//    		corporationMap.put("country", "中国");
//    		//省 默认值，目前用户信息没有区域属性，但是传入OMS需要
//    		//corporationMap.put("state","浙江省");
//    		//市 默认值，目前用户信息没有区域属性，但是传入OMS需要
//    		//corporationMap.put("city", "绍兴市");
//    		//区 默认值，目前用户信息没有区域属性，但是传入OMS需要
//    		//省
//    		if (regionsReadDao.get(Integer.parseInt(applyDb.getCompanyProvince())) != null) {
//    			corporationMap.put("state", regionsReadDao.get(Integer.parseInt(applyDb.getCompanyProvince())).getRegionName());
//            }else{
//            	corporationMap.put("state","浙江省");
//            }
//    		//市
//    		if (regionsReadDao.get(Integer.parseInt(applyDb.getCompanyCity())) != null) {
//    			corporationMap.put("city", regionsReadDao.get(Integer.parseInt(applyDb.getCompanyCity())).getRegionName());
//            }else{
//            	corporationMap.put("city", "绍兴市");
//            }
//    		//区
//    		corporationMap.put("district", "诸暨市");
//    		//地址1
//    		corporationMap.put("address1",applyDb.getCompanyAdd());
//    		//地址2
//    		corporationMap.put("address2",applyDb.getCompanyAdd());
//    		omsMap.put("corporation", corporationMap);
//    		
//			EnteringWarehouse.commonSyncOMS(omsMap, dawa_ttx_config.OMS_CORAPORATION_CREATE_COMPANY);
//			
//			member.setIsSyncOms("1");
//			
//			memberWriteDao.update(member);
//		} catch (Exception e) {
//			ILog.error(e.getMessage());
//			e.printStackTrace();
//			InterfaceWms interfaceWms = new InterfaceWms();
//			interfaceWms.setRalationTable(dawa_ttx_config.SELLER_USER);
//			interfaceWms.setRelationId(""+seller.getId());
//			interfaceWms.setSendNo(1);
//			interfaceWms.setSendResult("0");
//			interfaceWms.setErrorMsg(e.getMessage());
//			interfaceWms.setSyncTime(new Date());
//			interfaceWms.setSyncType(dawa_ttx_config.OMS_CORAPORATION_CREATE_COMPANY);
//			interfaceWms.setErrorMsg(e.getMessage());
//			interfaceWmsWriteDao.insert(interfaceWms);
//		}
//    }

    /**
     * 递归为商家角色赋权限
     * @param roleId 角色ID
     * @param resourcePId 资源父ID
     */
    private void initRolesResource(Integer roleId, Integer resourcePId) {
        // 赋自己的权限
        SellerResourcesRoles resourcesRoles = new SellerResourcesRoles();
        resourcesRoles.setResourcesId(resourcePId);
        resourcesRoles.setSellerRolesId(roleId);
        sellerResourcesRolesWriteDao.insert(resourcesRoles);
        List<SystemResources> children = systemResourcesReadDao.getByPId(resourcePId);
        if (children != null && children.size() > 0) {
            for (SystemResources child : children) {
                initRolesResource(roleId, child.getId());
            }
        }
    }

    /**
     * 保存商家申请表(平台添加商家用)<br>
     * <li>新增一个普通用户表（兼容用户端申请）
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param  sellerApply
     * @return
     */
    public boolean saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                           String sellerName, String ip) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            // 初始化会员信息
            Member member = new Member();
//            member.setName("admin-seller-apply-" + userName);
//            member.setPassword(Md5.getMd5String("123456"));
            member.setName(userName);
            member.setPassword(Md5.getMd5String("abc123"));
            member.setGrade(Member.GRADE_1);
            member.setGradeValue(0);
            member.setIntegral(0);
            member.setLastLoginIp(ip);
            member.setLoginNumber(0);
            member.setPwdErrCount(0);
            member.setSource(ConstantsEJS.SOURCE_1_PC);
            member.setBalance(new BigDecimal(0.00));
            member.setBalancePwd("");
            member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
            member.setIsSmsVerify(ConstantsEJS.YES_NO_0);
            member.setSmsVerifyCode("");
            member.setEmailVerifyCode("");
            member.setCanReceiveSms(1);
            member.setCanReceiveEmail(1);
            member.setStatus(Member.STATUS_1);
            member.setEmail("");
            member.setMemberType(1);
            member.setIsSyncOms("0");
            member.setIsTransferBussiness("0");
            // 判断用户名是否已存在
            /*List<Member> byNameList = memberWriteDao.getByName(member.getName());
            if (byNameList != null && byNameList.size() > 0) {
                throw new BusinessException("商家账号已存在，请重试！");
            }*/

            // 申请信息校验
            this.checkForInsert(sellerApply, userName, sellerName);

            // 保存会员注册信息（因为需要加密的字段都是空所以不需要再加密）
            int count = memberWriteDao.save(member);
            if (count == 0) {
                throw new BusinessException("信息保存失败，请重试！");
            }

            // 保存商家申请
            sellerApply.setUserId(member.getId());
            sellerApply.setName(member.getName());
            sellerApply.setPassword(member.getPassword());
            Integer insertRow = sellerApplyWriteDao.insert(sellerApply);
            if (insertRow == 0) {
                throw new BusinessException("商家申请保存失败，请重试！");
            }

            // 保存商家表
            this.saveSellerForApply(member, userName, sellerName);

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 新增商家入驻申请时的校验
     * @param sellerApply
     * @param userName
     * @param sellerName
     */
    private void checkForInsert(SellerApply sellerApply, String userName, String sellerName) {
        // 判断公司名称是否已经存在
        List<SellerApply> sellerApplys = sellerApplyReadDao
            .getSellerApplyByCompany(sellerApply.getCompany());
        if (sellerApplys != null && sellerApplys.size() > 0) {
            throw new BusinessException("该公司已经注册过商家了！");
        }
        // 判断商家登录账号名是否已经存在
        List<Seller> sellers = sellerReadDao.getSellerByName(userName);
        if (sellers != null && sellers.size() > 0) {
            throw new BusinessException("商家账号已存在，请重试！");
        }
        // 判断店铺名称是否已经存在
        sellers = sellerReadDao.getSellerBySellerName(sellerName);
        if (sellers != null && sellers.size() > 0) {
            throw new BusinessException("店铺名称已存在，请重试！");
        }
    }

    /**
     * 商家申请入驻时保存商家信息表
     * @param member
     * @param userName
     * @param sellerName
     */
    private void saveSellerForApply(Member member, String userName, String sellerName) {
        Seller seller = new Seller();
        seller.setMemberId(member.getId());
        //商家账号
        seller.setName(userName);
        //店铺名称
        seller.setSellerName(sellerName);
        seller.setSellerGrade(1);
        seller.setScoreService("0");
        seller.setScoreDeliverGoods("0");
        seller.setScoreDescription("0");
        seller.setProductNumber(0);
        seller.setCollectionNumber(0);
        seller.setCreateTime(new Date());
        seller.setSaleMoney(new BigDecimal(0));
        seller.setOrderCount(0);
        seller.setOrderCountOver(0);
        seller.setSellerKeyword("");
        seller.setSellerDes("");
        seller.setAuditStatus(Seller.AUDIT_STATE_1_SEND);

        //保存商家
        int insertRow = sellerWriteDao.save(seller);
        if (insertRow == 0) {
            throw new BusinessException("商家信息保存失败，请重试！");
        }
        //生成商户编码
        //seller.setSellerCode((seller.getId().intValue()+SellerUser.APPLY_GOODS_CODE)+"");
//        int sellerid = sellerWriteDao.update(seller);
//        if (sellerid == 0) {
//            throw new BusinessException("商家信息更新失败，请重试！");
//        }
    }

    /**
     * 修改商家申请(平台修改商家申请用)
     * 
     * @param sellerApply
     * @param userName
     * @param sellerName
     * @return
     */
    public boolean updateSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                             String sellerName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            // 数据库中的申请
            SellerApply applyDb = sellerApplyReadDao.get(sellerApply.getId());
            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND
                && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
                throw new BusinessException("只能修改提交申请和审核失败状态的商家申请！");
            }

            Seller sellerDb = sellerReadDao.getSellerByMemberId(applyDb.getUserId());
            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
                throw new BusinessException("只能修改提交申请状态的商家申请！");
            }

            // 商家申请修改时校验
            this.checkForUpdate(sellerApply, sellerDb, userName, sellerName);

            // 保存商家申请
            Integer updateRow = sellerApplyWriteDao.update(sellerApply);
            if (updateRow == 0) {
                throw new BusinessException("商家申请修改失败，请重试！");
            }

            Seller seller = new Seller();
            seller.setId(sellerDb.getId());
            //商家账号
            seller.setName(userName);
            //店铺名称
            seller.setSellerName(sellerName);

            //保存商家
            updateRow = sellerWriteDao.update(seller);
            if (updateRow == 0) {
                throw new BusinessException("商家信息修改失败，请重试！");
            }

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 商家申请修改时校验
     * @param sellerApply
     * @param sellerDb
     * @param userName
     * @param sellerName
     */
    private void checkForUpdate(SellerApply sellerApply, Seller sellerDb, String userName,
                                String sellerName) {
        // 判断公司名称是否已经存在
        List<SellerApply> sellerApplys = sellerApplyReadDao.getSellerApplyByCompany(sellerApply.getCompany());
        if (sellerApplys != null) {
            if (sellerApplys.size() > 1) {
                throw new BusinessException("该公司已经注册过商家了！");
            } else if (sellerApplys.size() == 1
                       && !sellerApplys.get(0).getId().equals(sellerApply.getId())) {
                throw new BusinessException("该公司已经注册过商家了！");
            }
        }
        // 判断商家登录账号名是否已经存在
        List<Seller> sellers = sellerReadDao.getSellerByName(userName);
        if (sellers != null) {
            if (sellers.size() > 1) {
                throw new BusinessException("商家账号已存在，请重试！");
            } else if (sellers.size() == 1 && !sellers.get(0).getId().equals(sellerDb.getId())) {
                throw new BusinessException("商家账号已存在，请重试！");
            }
        }
        // 判断店铺名称是否已经存在
        sellers = sellerReadDao.getSellerBySellerName(sellerName);
        if (sellers != null) {
            if (sellers.size() > 1) {
                throw new BusinessException("店铺名称已存在，请重试！");
            } else if (sellers.size() == 1 && !sellers.get(0).getId().equals(sellerDb.getId())) {
                throw new BusinessException("店铺名称已存在，请重试！");
            }
        }
    }

    /**
     * 保存商家申请表(用户端申请入驻时用)<br>
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param  sellerApply
     * @return
     */
    public boolean saveSellerApplyForFront(SellerApply sellerApply, String userName,
                                           String sellerName, Integer memberId) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);

        try {

            SellerApply sellerApplyByUserId = sellerApplyReadDao.getSellerApplyByUserId(memberId);
            if (sellerApplyByUserId != null) {
                throw new BusinessException("您已经申请过了，请不要重复申请！");
            }

            // 申请信息校验
            //this.checkForInsert(sellerApply, userName, sellerName);
             
            // 判断公司名称是否已经存在 
            List<SellerApply> sellerApplys = sellerApplyReadDao.getSellerApplyByCompany(sellerApply.getCompany());
            if (sellerApplys != null && sellerApplys.size() > 0) {
                throw new BusinessException("该公司已经注册过商家了！");
            }
            
            // 获取用户
            Member member = memberReadDao.get(memberId);

            // 保存商家申请
            sellerApply.setUserId(member.getId());
            sellerApply.setName(member.getName());
            sellerApply.setPassword(member.getPassword());
            Integer insertRow = sellerApplyWriteDao.insert(sellerApply);
            if (insertRow == 0) {
                throw new BusinessException("商家申请保存失败，请重试！");
            }

            // 保存商家表
            this.saveSellerForApply(member, userName, sellerName);

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 修改商家申请(用户端商家申请时用)
     * 
     * @param sellerApply
     * @param userName
     * @param sellerName
     * @return
     */
    public boolean updateSellerApplyForFront(SellerApply sellerApply, String userName,
                                             String sellerName) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            // 数据库中的申请
            SellerApply applyDb = sellerApplyReadDao.get(sellerApply.getId());
            if (applyDb.getState().intValue() != SellerApply.STATE_1_SEND && applyDb.getState().intValue() != SellerApply.STATE_4_FAIL) {
                throw new BusinessException("只能修改提交申请和审核失败状态的商家申请！");
            }

            Seller sellerDb = sellerReadDao.getSellerByMemberId(applyDb.getUserId());
            if (sellerDb.getAuditStatus().intValue() != Seller.AUDIT_STATE_1_SEND) {
                throw new BusinessException("只能修改提交申请状态的商家申请！");
            }

            // 商家申请修改时校验
            this.checkForUpdate(sellerApply, sellerDb, userName, sellerName);

            // 保存商家申请
            Integer updateRow = sellerApplyWriteDao.update(sellerApply);
            if (updateRow == 0) {
                throw new BusinessException("商家申请修改失败，请重试！");
            }

            Seller seller = new Seller();
            seller.setId(sellerDb.getId());
            //商家账号
            seller.setName(userName);
            //店铺名称
            seller.setSellerName(sellerName);

            //保存商家
            updateRow = sellerWriteDao.update(seller);
            if (updateRow == 0) {
                throw new BusinessException("商家信息修改失败，请重试！");
            }

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 根据公司名称查询入驻申请
     * @param company
     * @return
     */
    public List<SellerApply> getSellerApplyByCompany(String company) {
        return sellerApplyReadDao.getSellerApplyByCompany(company);
    }
}
