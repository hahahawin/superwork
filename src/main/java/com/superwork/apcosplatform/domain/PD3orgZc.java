package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PD3orgZc implements Serializable {
    /**
     * 设备ID
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private BigDecimal id;

    /**
     * 授权账户
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String account;

    /**
     * 服务授权_ID
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String serviceId;

    /**
     * 服务授权_KEY
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String serviceKey;

    /**
     * 公司名称
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String orgName;

    /**
     * 公司地址
     *
     * @mbg.generated 2020-02-17 13:05:27
     */
    private String address;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 3D URL
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String attr1;

    /**
     * 备用字段2
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String attr2;

    /**
     * 备用字段3 //存放token
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String attr3;

    private String count;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-11-12 11:28:08
     */
    private String belongOrgId;


    private String telePhone;

    private String mainAccount;

    /**
     * 主题
     *
     * @mbg.generated 2020-01-10 17:41:03
     */
    private String theme;

    /**
     * 图标
     *
     * @mbg.generated 2020-01-10 17:41:03
     */
    private String logo;

    /**
     * 是否警用 1 启用 2 禁用
     *
     * @mbg.generated 2020-01-10 17:41:03
     */
    private String effective;

    /**
     * 备案信息
     *
     * @mbg.generated 2020-02-17 13:29:20
     */
    private String companyInfo;

    /**
     * 公司电话
     *
     * @mbg.generated 2020-02-17 13:29:20
     */
    private String companyPhone;


    private static final long serialVersionUID = 1L;


    public String getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(String companyInfo) {
        this.companyInfo = companyInfo;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getMainAccount() {
        return mainAccount;
    }

    public void setMainAccount(String mainAccount) {
        this.mainAccount = mainAccount;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getEffective() {
        return effective;
    }

    public void setEffective(String effective) {
        this.effective = effective;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    public String getServiceKey() {
        return serviceKey;
    }

    public void setServiceKey(String serviceKey) {
        this.serviceKey = serviceKey == null ? null : serviceKey.trim();
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId == null ? null : belongOrgId.trim();
    }
}