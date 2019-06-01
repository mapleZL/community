package com.phkj.entity.member;

import java.io.Serializable;
/**
 * 
 * <p>Table: <strong>st_applet_member_house</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>memberId</td></tr>
 *   <tr><td>regionId</td><td>{@link java.lang.String}</td><td>region_id</td><td>varchar</td><td>regionId</td></tr>
 *   <tr><td>region</td><td>{@link java.lang.String}</td><td>region</td><td>varchar</td><td>区域</td></tr>
 *   <tr><td>streetId</td><td>{@link java.lang.Long}</td><td>street_id</td><td>bigint</td><td>streetId</td></tr>
 *   <tr><td>street</td><td>{@link java.lang.String}</td><td>street</td><td>varchar</td><td>街道</td></tr>
 *   <tr><td>communityId</td><td>{@link java.lang.Long}</td><td>community_id</td><td>bigint</td><td>communityId</td></tr>
 *   <tr><td>community</td><td>{@link java.lang.String}</td><td>community</td><td>varchar</td><td>社区</td></tr>
 *   <tr><td>villageId</td><td>{@link java.lang.Long}</td><td>village_id</td><td>bigint</td><td>villageId</td></tr>
 *   <tr><td>village</td><td>{@link java.lang.String}</td><td>village</td><td>varchar</td><td>小区</td></tr>
 *   <tr><td>buildingId</td><td>{@link java.lang.Long}</td><td>building_id</td><td>bigint</td><td>buildingId</td></tr>
 *   <tr><td>building</td><td>{@link java.lang.String}</td><td>building</td><td>varchar</td><td>楼幢</td></tr>
 *   <tr><td>unitId</td><td>{@link java.lang.Long}</td><td>unit_id</td><td>bigint</td><td>unitId</td></tr>
 *   <tr><td>unit</td><td>{@link java.lang.String}</td><td>unit</td><td>varchar</td><td>单元</td></tr>
 *   <tr><td>roomId</td><td>{@link java.lang.Long}</td><td>room_id</td><td>bigint</td><td>roomId</td></tr>
 *   <tr><td>room</td><td>{@link java.lang.String}</td><td>room</td><td>varchar</td><td>室</td></tr>
 *   <tr><td>identityInformation</td><td>{@link java.lang.String}</td><td>identity_information</td><td>varchar</td><td>身份信息</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>name</td></tr>
 *   <tr><td>idNumber</td><td>{@link java.lang.String}</td><td>id_number</td><td>varchar</td><td>身份证号</td></tr>
 *   <tr><td>phone</td><td>{@link java.lang.String}</td><td>phone</td><td>varchar</td><td>联系电话</td></tr>
 *   <tr><td>createDate</td><td>{@link java.util.Date}</td><td>create_date</td><td>datetime</td><td>createDate</td></tr>
 *   <tr><td>examineDate</td><td>{@link java.util.Date}</td><td>examine_date</td><td>datetime</td><td>examineDate</td></tr>
 *   <tr><td>examineUserId</td><td>{@link java.lang.Integer}</td><td>examine_user_id</td><td>int</td><td>examineUserId</td></tr>
 *   <tr><td>modifyId</td><td>{@link java.lang.Integer}</td><td>modify_id</td><td>int</td><td>修改人id</td></tr>
 *   <tr><td>modifyName</td><td>{@link java.lang.String}</td><td>modify_name</td><td>varchar</td><td>modifyName</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.Integer}</td><td>status</td><td>tinyint</td><td>status</td></tr>
 *   <tr><td>deleted</td><td>{@link java.lang.String}</td><td>deleted</td><td>varchar</td><td>deleted</td></tr>
 *   <tr><td>searchWord</td><td>{@link java.lang.String}</td><td>search_word</td><td>varchar</td><td>将房屋信息拼装起来的检索字段，用来综合搜索</td></tr>
 *   <tr><td>img</td><td>{@link java.lang.String}</td><td>img</td><td>varchar</td><td>房屋保存图片路径</td></tr>
 * </table>
 *
 */
public class MemberHouse implements Serializable {
 
    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    private java.lang.Integer id;
    private java.lang.Integer memberId;
    private java.lang.String regionId;
    private java.lang.String region;
    private java.lang.Long streetId;
    private java.lang.String street;
    private java.lang.Long communityId;
    private java.lang.String community;
    private java.lang.Long villageId;
    private java.lang.String villageCode;
    private java.lang.String village;
    private java.lang.Long buildingId;
    private java.lang.String building;
    private java.lang.Long unitId;
    private java.lang.String unit;
    private java.lang.Long roomId;
    private java.lang.String room;
    private java.lang.String identityInformation;
    private java.lang.String name;
    private java.lang.String idNumber;
    private java.lang.String phone;
    private java.util.Date createDate;
    private java.util.Date examineDate;
    private java.lang.Integer examineUserId;
    private java.lang.Integer modifyId;
    private java.lang.String modifyName;
    private java.lang.Integer status;
    private java.lang.String deleted;
    private java.lang.String searchWord;
    private java.lang.String img;
    
        
    /**
     * 获取id
     */
    public java.lang.Integer getId(){
        return this.id;
    }
        
    /**
     * 设置id
     */
    public void setId(java.lang.Integer id){
        this.id = id;
    }
        
    /**
     * 获取memberId
     */
    public java.lang.Integer getMemberId(){
        return this.memberId;
    }
        
    /**
     * 设置memberId
     */
    public void setMemberId(java.lang.Integer memberId){
        this.memberId = memberId;
    }
        
    /**
     * 获取regionId
     */
    public java.lang.String getRegionId(){
        return this.regionId;
    }
        
    /**
     * 设置regionId
     */
    public void setRegionId(java.lang.String regionId){
        this.regionId = regionId;
    }
        
    /**
     * 获取区域
     */
    public java.lang.String getRegion(){
        return this.region;
    }
        
    /**
     * 设置区域
     */
    public void setRegion(java.lang.String region){
        this.region = region;
    }
        
    /**
     * 获取streetId
     */
    public java.lang.Long getStreetId(){
        return this.streetId;
    }
        
    /**
     * 设置streetId
     */
    public void setStreetId(java.lang.Long streetId){
        this.streetId = streetId;
    }
        
    /**
     * 获取街道
     */
    public java.lang.String getStreet(){
        return this.street;
    }
        
    /**
     * 设置街道
     */
    public void setStreet(java.lang.String street){
        this.street = street;
    }
        
    /**
     * 获取communityId
     */
    public java.lang.Long getCommunityId(){
        return this.communityId;
    }
        
    /**
     * 设置communityId
     */
    public void setCommunityId(java.lang.Long communityId){
        this.communityId = communityId;
    }
        
    /**
     * 获取社区
     */
    public java.lang.String getCommunity(){
        return this.community;
    }
        
    /**
     * 设置社区
     */
    public void setCommunity(java.lang.String community){
        this.community = community;
    }
        
    /**
     * 获取villageId
     */
    public java.lang.Long getVillageId(){
        return this.villageId;
    }
        
    /**
     * 设置villageId
     */
    public void setVillageId(java.lang.Long villageId){
        this.villageId = villageId;
    }
        
    /**
     * 获取小区
     */
    public java.lang.String getVillage(){
        return this.village;
    }
        
    /**
     * 设置小区
     */
    public void setVillage(java.lang.String village){
        this.village = village;
    }
        
    /**
     * 获取buildingId
     */
    public java.lang.Long getBuildingId(){
        return this.buildingId;
    }
        
    /**
     * 设置buildingId
     */
    public void setBuildingId(java.lang.Long buildingId){
        this.buildingId = buildingId;
    }
        
    /**
     * 获取楼幢
     */
    public java.lang.String getBuilding(){
        return this.building;
    }
        
    /**
     * 设置楼幢
     */
    public void setBuilding(java.lang.String building){
        this.building = building;
    }
        
    /**
     * 获取unitId
     */
    public java.lang.Long getUnitId(){
        return this.unitId;
    }
        
    /**
     * 设置unitId
     */
    public void setUnitId(java.lang.Long unitId){
        this.unitId = unitId;
    }
        
    /**
     * 获取单元
     */
    public java.lang.String getUnit(){
        return this.unit;
    }
        
    /**
     * 设置单元
     */
    public void setUnit(java.lang.String unit){
        this.unit = unit;
    }
        
    /**
     * 获取roomId
     */
    public java.lang.Long getRoomId(){
        return this.roomId;
    }
        
    /**
     * 设置roomId
     */
    public void setRoomId(java.lang.Long roomId){
        this.roomId = roomId;
    }
        
    /**
     * 获取室
     */
    public java.lang.String getRoom(){
        return this.room;
    }
        
    /**
     * 设置室
     */
    public void setRoom(java.lang.String room){
        this.room = room;
    }
        
    /**
     * 获取身份信息
     */
    public java.lang.String getIdentityInformation(){
        return this.identityInformation;
    }
        
    /**
     * 设置身份信息
     */
    public void setIdentityInformation(java.lang.String identityInformation){
        this.identityInformation = identityInformation;
    }
        
    /**
     * 获取name
     */
    public java.lang.String getName(){
        return this.name;
    }
        
    /**
     * 设置name
     */
    public void setName(java.lang.String name){
        this.name = name;
    }
        
    /**
     * 获取身份证号
     */
    public java.lang.String getIdNumber(){
        return this.idNumber;
    }
        
    /**
     * 设置身份证号
     */
    public void setIdNumber(java.lang.String idNumber){
        this.idNumber = idNumber;
    }
        
    /**
     * 获取联系电话
     */
    public java.lang.String getPhone(){
        return this.phone;
    }
        
    /**
     * 设置联系电话
     */
    public void setPhone(java.lang.String phone){
        this.phone = phone;
    }
        
    /**
     * 获取createDate
     */
    public java.util.Date getCreateDate(){
        return this.createDate;
    }
        
    /**
     * 设置createDate
     */
    public void setCreateDate(java.util.Date createDate){
        this.createDate = createDate;
    }
        
    /**
     * 获取examineDate
     */
    public java.util.Date getExamineDate(){
        return this.examineDate;
    }
        
    /**
     * 设置examineDate
     */
    public void setExamineDate(java.util.Date examineDate){
        this.examineDate = examineDate;
    }
        
    /**
     * 获取examineUserId
     */
    public java.lang.Integer getExamineUserId(){
        return this.examineUserId;
    }
        
    /**
     * 设置examineUserId
     */
    public void setExamineUserId(java.lang.Integer examineUserId){
        this.examineUserId = examineUserId;
    }
        
    /**
     * 获取修改人id
     */
    public java.lang.Integer getModifyId(){
        return this.modifyId;
    }
        
    /**
     * 设置修改人id
     */
    public void setModifyId(java.lang.Integer modifyId){
        this.modifyId = modifyId;
    }
        
    /**
     * 获取modifyName
     */
    public java.lang.String getModifyName(){
        return this.modifyName;
    }
        
    /**
     * 设置modifyName
     */
    public void setModifyName(java.lang.String modifyName){
        this.modifyName = modifyName;
    }
        
    /**
     * 获取status
     */
    public java.lang.Integer getStatus(){
        return this.status;
    }
        
    /**
     * 设置status
     */
    public void setStatus(java.lang.Integer status){
        this.status = status;
    }
        
    /**
     * 获取deleted
     */
    public java.lang.String getDeleted(){
        return this.deleted;
    }
        
    /**
     * 设置deleted
     */
    public void setDeleted(java.lang.String deleted){
        this.deleted = deleted;
    }
        
    /**
     * 获取将房屋信息拼装起来的检索字段，用来综合搜索
     */
    public java.lang.String getSearchWord(){
        return this.searchWord;
    }
        
    /**
     * 设置将房屋信息拼装起来的检索字段，用来综合搜索
     */
    public void setSearchWord(java.lang.String searchWord){
        this.searchWord = searchWord;
    }
        
    /**
     * 获取房屋保存图片路径
     */
    public java.lang.String getImg(){
        return this.img;
    }
        
    /**
     * 设置房屋保存图片路径
     */
    public void setImg(java.lang.String img){
        this.img = img;
    }

    public String getVillageCode() {
        return villageCode;
    }

    public void setVillageCode(String villageCode) {
        this.villageCode = villageCode;
    }
}