package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.Date;

public class PSmhDevice implements Serializable {
    /**
     * ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String id;

    /**
     * SERVICE_ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String serviceId;

    /**
     * SERVICE_KEY
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String serviceKey;

    /**
     * SERVICE_BACK_URL
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String serviceBackUrl;

    /**
     * URL地址
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String attr1;

    /**
     * 备用字段2
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String attr2;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String belongOrgId;

    /**
     * BCCA项目地址
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String bccaUrl;

    /**
     * 大管家URL
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String dgjUrl;

    /**
     * 大管家appid
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String appid;

    /**
     * 大管家appkey
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String appkey;

    /**
     * 开发者平台URL
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String apcosUrl;

    /**
     * 开发者平台ID
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String apcosServiceId;

    /**
     * 开发者平台KEY
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String apcosServiceKey;

    /**
     * 设备控制token
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String attr3;

    /**
     * 备注4
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String attr4;

    /**
     * 备注5
     *
     * @mbg.generated 2019-10-25 11:28:21
     */
    private String attr5;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
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

    public String getServiceBackUrl() {
        return serviceBackUrl;
    }

    public void setServiceBackUrl(String serviceBackUrl) {
        this.serviceBackUrl = serviceBackUrl == null ? null : serviceBackUrl.trim();
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

    public String getBccaUrl() {
        return bccaUrl;
    }

    public void setBccaUrl(String bccaUrl) {
        this.bccaUrl = bccaUrl == null ? null : bccaUrl.trim();
    }

    public String getDgjUrl() {
        return dgjUrl;
    }

    public void setDgjUrl(String dgjUrl) {
        this.dgjUrl = dgjUrl == null ? null : dgjUrl.trim();
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid == null ? null : appid.trim();
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey == null ? null : appkey.trim();
    }

    public String getApcosUrl() {
        return apcosUrl;
    }

    public void setApcosUrl(String apcosUrl) {
        this.apcosUrl = apcosUrl == null ? null : apcosUrl.trim();
    }

    public String getApcosServiceId() {
        return apcosServiceId;
    }

    public void setApcosServiceId(String apcosServiceId) {
        this.apcosServiceId = apcosServiceId == null ? null : apcosServiceId.trim();
    }

    public String getApcosServiceKey() {
        return apcosServiceKey;
    }

    public void setApcosServiceKey(String apcosServiceKey) {
        this.apcosServiceKey = apcosServiceKey == null ? null : apcosServiceKey.trim();
    }

    public String getAttr3() {
        return attr3;
    }

    public void setAttr3(String attr3) {
        this.attr3 = attr3 == null ? null : attr3.trim();
    }

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4 == null ? null : attr4.trim();
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5 == null ? null : attr5.trim();
    }
}