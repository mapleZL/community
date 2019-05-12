package com.phkj.dao.shop.write.repaire;


import org.springframework.stereotype.Repository;

import com.phkj.entity.repair.StAppletRepair;

/**
 *                       
 * @Filename: StAppletRepairWriteDao.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Repository
public interface StAppletRepairWriteDao {

	Integer insert(StAppletRepair stAppletRepair);
	
	Integer update(StAppletRepair stAppletRepair);

}