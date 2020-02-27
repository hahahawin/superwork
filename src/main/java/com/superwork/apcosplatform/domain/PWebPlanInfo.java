package com.superwork.apcosplatform.domain;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


public class PWebPlanInfo implements Serializable {
    /**
     * id
     *
     * @mbg.generated 2019-10-30 16:37:49
     */

    private BigDecimal id;

    /**
     * 模式ID
     *
     * @mbg.generated 2019-10-30 16:37:49
     */

    private BigDecimal propertyId;

    /**
     * 指令ID
     *
     * @mbg.generated 2019-10-30 16:37:49
     */

    private BigDecimal cmdId;

    /**
     * 参数key
     *
     * @mbg.generated 2019-10-30 16:37:49
     */

    private String paramKey;

    private Date createTime;

    private Date updateTime;

    private BigDecimal syncStatus;

    private BigDecimal syncStatus3;

    private PWebProductCmdUp webProductCmdUp;

    private PWebProductCmdUpParam webProductCmdUpParam;

    public PWebProductCmdUp getWebProductCmdUp() {
        return webProductCmdUp;
    }

    public void setWebProductCmdUp(PWebProductCmdUp webProductCmdUp) {
        this.webProductCmdUp = webProductCmdUp;
    }

    public PWebProductCmdUpParam getWebProductCmdUpParam() {
        return webProductCmdUpParam;
    }

    public void setWebProductCmdUpParam(PWebProductCmdUpParam webProductCmdUpParam) {
        this.webProductCmdUpParam = webProductCmdUpParam;
    }

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(BigDecimal propertyId) {
        this.propertyId = propertyId;
    }

    public BigDecimal getCmdId() {
        return cmdId;
    }

    public void setCmdId(BigDecimal cmdId) {
        this.cmdId = cmdId;
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