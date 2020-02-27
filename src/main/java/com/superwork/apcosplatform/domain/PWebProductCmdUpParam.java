package com.superwork.apcosplatform.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PWebProductCmdUpParam implements Serializable {
//    @JSONField(name = "id")
    private BigDecimal id;

    /**
     * 产品上行指令ID
     *
     * @mbg.generated 2019-11-05 09:27:13
     */

    private BigDecimal cmdId;

    /**
     * 参数顺序
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_order")
    private BigDecimal paramOrder;

    /**
     * 参数类型：init[内置参数]，string[字符串]，binary[二进制]，int[十进制]，hex[十六进制]，boolean[布尔]，js[js代码]
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_type")
    private String paramType;

    /**
     * 参数位置（单位byte）：-1[未知]
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_pos")
    private BigDecimal paramPos;

    /**
     * 参数长度（单位byte）：-1[未知]
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_len")
    private BigDecimal paramLen;

    /**
     * 参数名称：init[设备类型,指令地址]
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_name")
    private String paramName;

    /**
     * 参数键值：init[DEVICE_TYPE,CMD_ADDR]
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
//    @JSONField(name = "param_key")
    private String paramKey;

    /**
     * 参数说明
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
    private String paramReadme;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
    private Date updateTime;

    /**
     * 1.未通知 2.已通知
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
    private String syncStatus;

    /**
     * 参数值：解析值，正则表达式，js代码
     *
     * @mbg.generated 2019-11-05 09:27:13
     */
    private String paramVal;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getCmdId() {
        return cmdId;
    }

    public void setCmdId(BigDecimal cmdId) {
        this.cmdId = cmdId;
    }

    public BigDecimal getParamOrder() {
        return paramOrder;
    }

    public void setParamOrder(BigDecimal paramOrder) {
        this.paramOrder = paramOrder;
    }

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType == null ? null : paramType.trim();
    }

    public BigDecimal getParamPos() {
        return paramPos;
    }

    public void setParamPos(BigDecimal paramPos) {
        this.paramPos = paramPos;
    }

    public BigDecimal getParamLen() {
        return paramLen;
    }

    public void setParamLen(BigDecimal paramLen) {
        this.paramLen = paramLen;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName == null ? null : paramName.trim();
    }

    public String getParamKey() {
        return paramKey;
    }

    public void setParamKey(String paramKey) {
        this.paramKey = paramKey == null ? null : paramKey.trim();
    }

    public String getParamReadme() {
        return paramReadme;
    }

    public void setParamReadme(String paramReadme) {
        this.paramReadme = paramReadme == null ? null : paramReadme.trim();
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

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public String getParamVal() {
        return paramVal;
    }

    public void setParamVal(String paramVal) {
        this.paramVal = paramVal == null ? null : paramVal.trim();
    }
}