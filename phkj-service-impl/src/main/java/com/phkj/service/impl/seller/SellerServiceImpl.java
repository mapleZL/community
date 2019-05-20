package com.phkj.service.impl.seller;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phkj.model.seller.SellerModel;
import com.phkj.service.seller.ISellerService;

/**
 *                       
 * @Filename: SellerServiceImpl.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "sellerService")
public class SellerServiceImpl implements ISellerService {

    @Resource
    private SellerModel   sellerModel;

    
}