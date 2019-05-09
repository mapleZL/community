package com.phkj.core;

public class SyncWayUtil {
    
    public final static String SYNC_WMS = "WMS";
    
    public final static String SYNC_OMS = "OMS"; 
    
    public static boolean SEND_MESSAGE_SELLER = true;

    public static String SYNC_WAY = SYNC_OMS;
    
    public static StringBuffer changeSyncWay(String syncWay) {
//    	if (true) return null;
        
        StringBuffer bf = new StringBuffer();
        bf.append("<center>");
        if (("YES").equals(syncWay)) {
            bf.append("<br/><br/><br/><br/><font color='red' size='6'>操作成功，发送订单消息给供应商为[" + syncWay + "]</font>");
            SyncWayUtil.SEND_MESSAGE_SELLER = true;
        }
        else if (("NO").equals(syncWay)) {
            bf.append("<br/><br/><br/><br/><font color='red' size='6'>操作成功，发送订单消息给供应商为[" + syncWay + "]</font>");
            SyncWayUtil.SEND_MESSAGE_SELLER = false;
        }
        bf.append("<br/><br/>商城当前发送消息给供应商为：");
        bf.append(SyncWayUtil.SEND_MESSAGE_SELLER);
        bf.append("<br/><br/>可切换是否发送订单：<br/><br/>");
        if (SEND_MESSAGE_SELLER)
            bf.append("<input type='button' name='' value='不发送消息' style='width: 150px;height: 40px;' onclick='syncNO()'>");
        else
            bf.append("<input type='button' name='' value='发送消息' style='width: 150px;height: 40px;' onclick='syncYES()'>");
        bf.append("</center><script>");
        bf.append("function syncNO(){if (confirm('确定不发送订单消息给供应商?')) {location.href = '/sync/way/sync_way_set/sync_way_set?syncWay=NO';}}");
        bf.append("function syncYES() {if (confirm('确定需要发送订单消息给供应商吗?')) {location.href = '/sync/way/sync_way_set/sync_way_set?syncWay=YES';}}");
        bf.append("</script>");
        return bf;
        
       /* StringBuffer bf = new StringBuffer();
        bf.append("<center>");
        if (!"".equals(syncWay) && (syncWay.equals(SyncWayUtil.SYNC_OMS) || syncWay.equals(SyncWayUtil.SYNC_WMS))) {
            bf.append("<br/><br/><br/><br/><font color='red' size='6'>操作成功，指定同步至[" + syncWay + "]</font>");
            SyncWayUtil.SYNC_WAY = syncWay;
        }
        PrintWriter pw = null;
        bf.append("<br/><br/>商城当前同步系统：");
        bf.append(SyncWayUtil.SYNC_WAY);
        bf.append("<br/><br/>可切换同步至：<br/><br/>");
        if (SyncWayUtil.SYNC_WAY.equals(SyncWayUtil.SYNC_WMS))
            bf.append("<input type='button' name='' value='同步至OMS' style='width: 150px;height: 40px;' onclick='syncOms()'>");
        else
            bf.append("<input type='button' name='' value='同步至WMS' style='width: 150px;height: 40px;' onclick='syncWms()'>");
        bf.append("</center><script>");
        bf.append("function syncOms(){if (confirm('确定调整为同步至OMS(订单管理系统)吗?')) {location.href = '/sync/way/sync_way_set/sync_way_set?syncWay=OMS';}}");
        bf.append("function syncWms() {if (confirm('确定调整为同步至WMS(仓库管理系统)吗?')) {location.href = '/sync/way/sync_way_set/sync_way_set?syncWay=WMS';}}");
        bf.append("</script>");
        return bf;*/
    }
}
