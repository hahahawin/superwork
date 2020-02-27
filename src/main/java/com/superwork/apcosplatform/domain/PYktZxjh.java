package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PYktZxjh implements Serializable {
    /**
     * ZXJH_ID
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String zxjhId;

    /**
     * 云平台执行计划ID
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String yptjhId;

    /**
     * 执行计划名称
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String zxjhMc;

    /**
     * 云平台账户
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String zxjhYptzh;

    /**
     * 计划状态 1停用 2启用
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String zxjhZt;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String attr1;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String attr2;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String attr3;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String attr4;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-30 10:53:13
     */
    private String belongOrgId;

    private String createName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    private static final long serialVersionUID = 1L;

    public String getZxjhId() {
        return zxjhId;
    }

    public void setZxjhId(String zxjhId) {
        this.zxjhId = zxjhId == null ? null : zxjhId.trim();
    }

    public String getYptjhId() {
        return yptjhId;
    }

    public void setYptjhId(String yptjhId) {
        this.yptjhId = yptjhId == null ? null : yptjhId.trim();
    }

    public String getZxjhMc() {
        return zxjhMc;
    }

    public void setZxjhMc(String zxjhMc) {
        this.zxjhMc = zxjhMc == null ? null : zxjhMc.trim();
    }

    public String getZxjhYptzh() {
        return zxjhYptzh;
    }

    public void setZxjhYptzh(String zxjhYptzh) {
        this.zxjhYptzh = zxjhYptzh == null ? null : zxjhYptzh.trim();
    }

    public String getZxjhZt() {
        return zxjhZt;
    }

    public void setZxjhZt(String zxjhZt) {
        this.zxjhZt = zxjhZt == null ? null : zxjhZt.trim();
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

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4 == null ? null : attr4.trim();
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
}