package com.ejavashop.service.wmsinterface;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.wmsinterface.InterfaceWms;

public interface IInterfaceWmsService {

	/**
     * 根据id取得interface_wms对象
     * @param  interfaceWmsId
     * @return
     */
    ServiceResult<InterfaceWms> getInterfaceWmsById(Integer interfaceWmsId);
    
    /**
     * 保存interface_wms对象
     * @param  interfaceWms
     * @return
     */
     ServiceResult<Integer> saveInterfaceWms(InterfaceWms interfaceWms);
     
     /**
     * 更新interface_wms对象
     * @param  interfaceWms
     * @return
     */
     ServiceResult<Integer> updateInterfaceWms(InterfaceWms interfaceWms);
     
     /**
      * 根据relationId和relationTable查询interface_wms对象
      * @param relationId
      * @param relationTable
      * @return
      */
     ServiceResult<InterfaceWms>getInterfaceByRelationIdAndRelationTable(String relationId,String relationTable);
     
     
     ServiceResult<List<InterfaceWms>>page(Map<String, String> dataMap,PagerInfo pager);
     
     
     ServiceResult <Boolean>sendFailOrderMail();
}