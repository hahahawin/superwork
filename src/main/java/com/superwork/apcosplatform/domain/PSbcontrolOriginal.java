package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PSbcontrolOriginal implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String id;

    /**
     * 设备所属账号
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String account;

    /**
     * 产品类型
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String productId;

    /**
     * 设备名称
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String deviceName;

    /**
     * 产品MAC
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String serialNum;

    /**
     * 操作
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String operation;

    /**
     * 控制参数
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String controlparams;

    /**
     * 控制人
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String controlId;


    private String controlName;

    /**
     * 控制时间
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createDate;

    /**
     * 控制结果:1 成功 2.失败
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String result;

    /**
     * 异常原因
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String abnormalReason;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String attr1;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String attr2;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-06 16:36:41
     */
    private String attr3;

    private static final long serialVersionUID = 1L;


    public String getControlName() {
        return controlName;
    }

    public void setControlName(String controlName) {
        this.controlName = controlName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public String getControlparams() {
        return controlparams;
    }

    public void setControlparams(String controlparams) {
        this.controlparams = controlparams == null ? null : controlparams.trim();
    }

    public String getControlId() {
        return controlId;
    }

    public void setControlId(String controlId) {
        this.controlId = controlId == null ? null : controlId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getAbnormalReason() {
        return abnormalReason;
    }

    public void setAbnormalReason(String abnormalReason) {
        this.abnormalReason = abnormalReason == null ? null : abnormalReason.trim();
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