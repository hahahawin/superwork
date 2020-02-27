package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PWebPropertyInfo implements Serializable {
    /**
     * ID
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "id")
    private BigDecimal id;

    /**
     * 产品编号
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "product_code")
    private String productCode;

    /**
     * 属性名称
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "property_name")
    private String propertyName;

    /**
     * 属性模式<0-指令本身,1-指令参数
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "property_model")
    private String propertyModel;

    /**
     * 属性模式说明
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "property_readme")
    private String propertyReadme;

    /**
     * 指令ID
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "cmd_id")
    private String cmdId;

    /**
     * 参数
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "param_key")
    private String paramKey;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     *
     * @mbg.generated 2019-10-30 16:42:01
     */
//    @JSONField(name = "update_time")
    private Date updateTime;

//    @JSONField(name = "sync_status")
    private BigDecimal syncStatus;
//    @JSONField(name = "sync_status3")
    private BigDecimal syncStatus3;
//    @JSONField(name = "web_plan_info")
    private List<PWebPlanInfo> webPlanInfo;

    public List<PWebPlanInfo> getWebPlanInfo() {
        return webPlanInfo;
    }

    public void setWebPlanInfo(List<PWebPlanInfo> webPlanInfo) {
        this.webPlanInfo = webPlanInfo;
    }

    @Override
    public String toString() {
        return "PWebPropertyInfo{" +
                "id=" + id +
                ", productCode='" + productCode + '\'' +
                ", propertyName='" + propertyName + '\'' +
                ", propertyModel='" + propertyModel + '\'' +
                ", propertyReadme='" + propertyReadme + '\'' +
                ", cmdId='" + cmdId + '\'' +
                ", paramKey='" + paramKey + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", syncStatus=" + syncStatus +
                ", syncStatus3=" + syncStatus3 +
                ", webPlanInfo=" + webPlanInfo +
                '}';
    }

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName == null ? null : propertyName.trim();
    }

    public String getPropertyModel() {
        return propertyModel;
    }

    public void setPropertyModel(String propertyModel) {
        this.propertyModel = propertyModel == null ? null : propertyModel.trim();
    }

    public String getPropertyReadme() {
        return propertyReadme;
    }

    public void setPropertyReadme(String propertyReadme) {
        this.propertyReadme = propertyReadme == null ? null : propertyReadme.trim();
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId == null ? null : cmdId.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public BigDecimal getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(BigDecimal syncStatus) {
        this.syncStatus = syncStatus;
    }

    public BigDecimal getSyncStatus3() {
        return syncStatus3;
    }

    public void setSyncStatus3(BigDecimal syncStatus3) {
        this.syncStatus3 = syncStatus3;
    }
}