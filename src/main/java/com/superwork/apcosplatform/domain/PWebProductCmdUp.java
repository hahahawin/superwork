package com.superwork.apcosplatform.domain;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PWebProductCmdUp implements Serializable {
//    @JSONField(name = "id")
    private BigDecimal id;

    /**
     * 商品ID
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "product_id")
    private BigDecimal productId;

    /**
     * 支持注册：yes[支持]，no[不支持]
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "enable_register")
    private String enableRegister;

    /**
     * 激活指令：yes[激活]，no[不激活]
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "enable_cmd")
    private String enableCmd;

    /**
     * 指令类型
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_type")
    private String cmdType;

    /**
     * 指令编号
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_code")
    private String cmdCode;

    /**
     * 指令名称
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_name")
    private String cmdName;

    /**
     * 指令正则解析：正则表达式
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_regex")
    private String cmdRegex;

    /**
     * 指令说明
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_readme")
    private String cmdReadme;

    /**
     * 指令成功响应ID
     *
     * @mbg.generated 2019-10-30 16:37:49
     */

    private String cmdSuccessId;

    /**
     * 指令失败响应ID
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
    private String cmdErrorId;

    /**
     * 参数个数：-1[未知]
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "param_num")
    private BigDecimal paramNum;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "update_time")
    private Date updateTime;

    /**
     * 下行指令id
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
    private String downCmdId;
//    @JSONField(name = "sync_status")
    private String syncStatus;
//    @JSONField(name = "sync_status3")
    private BigDecimal syncStatus3;

    /**
     * 指令原文
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
//    @JSONField(name = "cmd_text")
    private byte[] cmdText;

    /**
     * 指令JS解析：js代码
     *
     * @mbg.generated 2019-10-30 16:37:49
     */
    private String cmdJs;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public BigDecimal getProductId() {
        return productId;
    }

    public void setProductId(BigDecimal productId) {
        this.productId = productId;
    }

    public String getEnableRegister() {
        return enableRegister;
    }

    public void setEnableRegister(String enableRegister) {
        this.enableRegister = enableRegister == null ? null : enableRegister.trim();
    }

    public String getEnableCmd() {
        return enableCmd;
    }

    public void setEnableCmd(String enableCmd) {
        this.enableCmd = enableCmd == null ? null : enableCmd.trim();
    }

    public String getCmdType() {
        return cmdType;
    }

    public void setCmdType(String cmdType) {
        this.cmdType = cmdType == null ? null : cmdType.trim();
    }

    public String getCmdCode() {
        return cmdCode;
    }

    public void setCmdCode(String cmdCode) {
        this.cmdCode = cmdCode == null ? null : cmdCode.trim();
    }

    public String getCmdName() {
        return cmdName;
    }

    public void setCmdName(String cmdName) {
        this.cmdName = cmdName == null ? null : cmdName.trim();
    }

    public String getCmdRegex() {
        return cmdRegex;
    }

    public void setCmdRegex(String cmdRegex) {
        this.cmdRegex = cmdRegex == null ? null : cmdRegex.trim();
    }

    public String getCmdReadme() {
        return cmdReadme;
    }

    public void setCmdReadme(String cmdReadme) {
        this.cmdReadme = cmdReadme == null ? null : cmdReadme.trim();
    }

    public String getCmdSuccessId() {
        return cmdSuccessId;
    }

    public void setCmdSuccessId(String cmdSuccessId) {
        this.cmdSuccessId = cmdSuccessId == null ? null : cmdSuccessId.trim();
    }

    public String getCmdErrorId() {
        return cmdErrorId;
    }

    public void setCmdErrorId(String cmdErrorId) {
        this.cmdErrorId = cmdErrorId == null ? null : cmdErrorId.trim();
    }

    public BigDecimal getParamNum() {
        return paramNum;
    }

    public void setParamNum(BigDecimal paramNum) {
        this.paramNum = paramNum;
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

    public String getDownCmdId() {
        return downCmdId;
    }

    public void setDownCmdId(String downCmdId) {
        this.downCmdId = downCmdId == null ? null : downCmdId.trim();
    }

    public String getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(String syncStatus) {
        this.syncStatus = syncStatus == null ? null : syncStatus.trim();
    }

    public BigDecimal getSyncStatus3() {
        return syncStatus3;
    }

    public void setSyncStatus3(BigDecimal syncStatus3) {
        this.syncStatus3 = syncStatus3;
    }

    public byte[] getCmdText() {
        return cmdText;
    }

    public void setCmdText(byte[] cmdText) {
        this.cmdText = cmdText;
    }

    public String getCmdJs() {
        return cmdJs;
    }

    public void setCmdJs(String cmdJs) {
        this.cmdJs = cmdJs == null ? null : cmdJs.trim();
    }
}