package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PSbgl implements Serializable {
    /**
     * 创建人
     *
     * @mbg.generated 2019-10-28 11:24:25
     */

   private String  userAccount;

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 设备ID
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String id;

    /**
     * 设备类型
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String productCode;

    /**
     * 设备账户
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String account;

    /**
     * 设备序列号
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String serialNum;

    /**
     * 设备Mac
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String deviceMac;

    /**
     * 设备名称
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String deviceName;

    /**
     * DEVICE_TYPE
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String deviceType;

    /**
     * CREATE_TIME
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String createTime;

    private String updateTime;

    /**
     * WHETHER_REGISTER
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String whetherRegister;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String creatorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 编辑
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String editorId;

    /**
     * 编辑时间
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String belongOrgId;

    /**
     * 备用字段1
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String attr1;

    /**
     * 备用字段2
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String attr2;

    /**
     * 备用字段3
     *
     * @mbg.generated 2019-10-28 11:24:25
     */
    private String attr3;

    private List<String> macList;

    public List<String> getMacList() {
        return macList;
    }

    public void setMacList(List<String> macList) {
        this.macList = macList;
    }

    private boolean bong=false;//是否绑定3D元件

    private boolean mark = false;//标记，是否查询时是否绑

    public boolean isMark() {
        return mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
    }

    public boolean isBong() {
        return bong;
    }

    public void setBong(boolean bong) {
        this.bong = bong;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac == null ? null : deviceMac.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType == null ? null : deviceType.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getWhetherRegister() {
        return whetherRegister;
    }

    public void setWhetherRegister(String whetherRegister) {
        this.whetherRegister = whetherRegister == null ? null : whetherRegister.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId == null ? null : editorId.trim();
    }

    public Date getEditedTime() {
        return editedTime;
    }

    public void setEditedTime(Date editedTime) {
        this.editedTime = editedTime;
    }

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId == null ? null : belongOrgId.trim();
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1 == null ? null : attr1.trim();
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2 == null ? null : attr2.trim();
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3 == null ? null : attr3.trim();
    }
}