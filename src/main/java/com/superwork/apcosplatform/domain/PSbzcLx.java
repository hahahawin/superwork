package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PSbzcLx implements Serializable {
    /**
     * 设备资产类型ID
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzcLxId;

    /**
     * 上级目录ID
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String pLxId;

    /**
     * 用途类型：1-易耗品目录，2-资产目录
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String ytlx;

    /**
     * 目录名称：
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String mlmc;

    /**
     * 目录代码
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String mldm;

    /**
     * 是否停用：SFTY,考虑到用品的推陈出新以及旧的物品不会再改目录需要个正常使用，设置此字段
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private BigDecimal sfty;

    /**
     * 备注：
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String bz;

    /**
     * 备用字段1
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String attr1;

    /**
     * 备用字段2
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String attr2;

    /**
     * 备用字段3
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String attr3;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String belongOrgId;

    /**
     * 图标
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String iconFileJson;

    /**
     * 图标(关)
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String iconFileJsonClose;

    /**
     * 是否可组网  SFBZ 1否2是
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxSfzw;

    /**
     * 是否创建模式 SFBZ 1否2是
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxSfcjms;

    /**
     * 是否绑模式 SFBZ 1否2是
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxSfbms;

    /**
     * 是否无线 SFBZ 1否2是（1有线2无线）
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxSfwx;

    /**
     * 选择方式 1单选2多选
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxXzfs;

    /**
     * 结构参数 1独立 2单条成对 3多条成对 9其他
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sbzclxJgcs;

    /**
     * 是否易消耗物品  1是 2否
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String sfyxhwp;

    /**
     * 网页模板 pc 端
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String htmlmodle;

    /**
     * 网页模板 手机端
     *
     * @mbg.generated 2019-10-28 09:11:49
     */
    private String htmlmobile;


    private String account;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    private static final long serialVersionUID = 1L;

    public String getSbzcLxId() {
        return sbzcLxId;
    }

    public void setSbzcLxId(String sbzcLxId) {
        this.sbzcLxId = sbzcLxId == null ? null : sbzcLxId.trim();
    }

    public String getpLxId() {
        return pLxId;
    }

    public void setpLxId(String pLxId) {
        this.pLxId = pLxId == null ? null : pLxId.trim();
    }

    public String getYtlx() {
        return ytlx;
    }

    public void setYtlx(String ytlx) {
        this.ytlx = ytlx == null ? null : ytlx.trim();
    }

    public String getMlmc() {
        return mlmc;
    }

    public void setMlmc(String mlmc) {
        this.mlmc = mlmc == null ? null : mlmc.trim();
    }

    public String getMldm() {
        return mldm;
    }

    public void setMldm(String mldm) {
        this.mldm = mldm == null ? null : mldm.trim();
    }

    public BigDecimal getSfty() {
        return sfty;
    }

    public void setSfty(BigDecimal sfty) {
        this.sfty = sfty;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
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

    public String getIconFileJson() {
        return iconFileJson;
    }

    public void setIconFileJson(String iconFileJson) {
        this.iconFileJson = iconFileJson == null ? null : iconFileJson.trim();
    }

    public String getIconFileJsonClose() {
        return iconFileJsonClose;
    }

    public void setIconFileJsonClose(String iconFileJsonClose) {
        this.iconFileJsonClose = iconFileJsonClose == null ? null : iconFileJsonClose.trim();
    }

    public String getSbzclxSfzw() {
        return sbzclxSfzw;
    }

    public void setSbzclxSfzw(String sbzclxSfzw) {
        this.sbzclxSfzw = sbzclxSfzw == null ? null : sbzclxSfzw.trim();
    }

    public String getSbzclxSfcjms() {
        return sbzclxSfcjms;
    }

    public void setSbzclxSfcjms(String sbzclxSfcjms) {
        this.sbzclxSfcjms = sbzclxSfcjms == null ? null : sbzclxSfcjms.trim();
    }

    public String getSbzclxSfbms() {
        return sbzclxSfbms;
    }

    public void setSbzclxSfbms(String sbzclxSfbms) {
        this.sbzclxSfbms = sbzclxSfbms == null ? null : sbzclxSfbms.trim();
    }

    public String getSbzclxSfwx() {
        return sbzclxSfwx;
    }

    public void setSbzclxSfwx(String sbzclxSfwx) {
        this.sbzclxSfwx = sbzclxSfwx == null ? null : sbzclxSfwx.trim();
    }

    public String getSbzclxXzfs() {
        return sbzclxXzfs;
    }

    public void setSbzclxXzfs(String sbzclxXzfs) {
        this.sbzclxXzfs = sbzclxXzfs == null ? null : sbzclxXzfs.trim();
    }

    public String getSbzclxJgcs() {
        return sbzclxJgcs;
    }

    public void setSbzclxJgcs(String sbzclxJgcs) {
        this.sbzclxJgcs = sbzclxJgcs == null ? null : sbzclxJgcs.trim();
    }

    public String getSfyxhwp() {
        return sfyxhwp;
    }

    public void setSfyxhwp(String sfyxhwp) {
        this.sfyxhwp = sfyxhwp == null ? null : sfyxhwp.trim();
    }

    public String getHtmlmodle() {
        return htmlmodle;
    }

    public void setHtmlmodle(String htmlmodle) {
        this.htmlmodle = htmlmodle == null ? null : htmlmodle.trim();
    }

    public String getHtmlmobile() {
        return htmlmobile;
    }

    public void setHtmlmobile(String htmlmobile) {
        this.htmlmobile = htmlmobile == null ? null : htmlmobile.trim();
    }
}