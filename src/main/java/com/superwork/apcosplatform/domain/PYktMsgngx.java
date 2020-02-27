package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PYktMsgngx implements Serializable {
    private String msgngxId; //msgngx_id

    private String msid;//模式id

    private String whid;//设备whid

    private String productId;//功能id

    private String code;//命令

    private String params;//命令参数

    private String memo;//命令名称

    private String memo1;//设备名称

    private String memo2;//备注

    private String memo3;//备注

    private String creatorId;//创建人id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;//创建时间

    private String editorId;//修改人id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;//修改时间

    private String belongOrgId;

    private String productCode;//设备类型

    private String account;//设备账户

    private String ymsid;//云模式ID

    public String getYmsid() {
        return ymsid;
    }

    public void setYmsid(String ymsid) {
        this.ymsid = ymsid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private static final long serialVersionUID = 1L;

    public String getMsgngxId() {
        return msgngxId;
    }

    public void setMsgngxId(String msgngxId) {
        this.msgngxId = msgngxId == null ? null : msgngxId.trim();
    }

    public String getMsid() {
        return msid;
    }

    public void setMsid(String msid) {
        this.msid = msid == null ? null : msid.trim();
    }

    public String getWhid() {
        return whid;
    }

    public void setWhid(String whid) {
        this.whid = whid == null ? null : whid.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public String getMemo1() {
        return memo1;
    }

    public void setMemo1(String memo1) {
        this.memo1 = memo1 == null ? null : memo1.trim();
    }

    public String getMemo2() {
        return memo2;
    }

    public void setMemo2(String memo2) {
        this.memo2 = memo2 == null ? null : memo2.trim();
    }

    public String getMemo3() {
        return memo3;
    }

    public void setMemo3(String memo3) {
        this.memo3 = memo3 == null ? null : memo3.trim();
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

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode == null ? null : productCode.trim();
    }
}