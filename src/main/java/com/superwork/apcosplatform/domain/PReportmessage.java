package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PReportmessage implements Serializable {
    /**
     * ID
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String id;

    /**
     * 设备类型
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String productCode;

    /**
     * 设备序列号
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String serialNum;

    /**
     * 所属账号
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String account;

    /**
     * 上报指令ID
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String cmdId;

    /**
     * 上报指令名称
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String cmdName;

    /**
     * 备注1
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String attr1;

    /**
     * 备注2
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String attr2;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 单位组织ID
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String belongOrgId;

    /**
     * 上报信息
     *
     * @mbg.generated 2019-10-25 11:30:32
     */
    private String reportmessage;

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

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getCmdId() {
        return cmdId;
    }

    public void setCmdId(String cmdId) {
        this.cmdId = cmdId == null ? null : cmdId.trim();
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName == null ? null : cmdName.trim();
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

    public String getReportmessage() {
        return reportmessage;
    }

    public void setReportmessage(String reportmessage) {
        this.reportmessage = reportmessage == null ? null : reportmessage.trim();
    }
}