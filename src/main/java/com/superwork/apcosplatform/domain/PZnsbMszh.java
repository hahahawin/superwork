package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.Date;

public class PZnsbMszh implements Serializable {
    /**
     * MSZH
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String mszhId;

    /**
     * 组合名称
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String mszhMc;

    /**
     * 组合语音关键字
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String mszhGjz;

    /**
     * 组合模式状态 1停用 2启用 不与平台同步
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String mszhZt;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String attr1;

    /**
     * 备注2
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String attr2;

    /**
     * 备注3
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String attr3;

    /**
     * 备注4
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String attr4;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private Date createdTime;

    /**
     * 修改人ID
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-28 17:37:50
     */
    private String belongOrgId;


    private String msmls;//模式命令式字符串

    public String getMsmls() {
        return msmls;
    }

    public void setMsmls(String msmls) {
        this.msmls = msmls;
    }

    private static final long serialVersionUID = 1L;

    public String getMszhId() {
        return mszhId;
    }

    public void setMszhId(String mszhId) {
        this.mszhId = mszhId == null ? null : mszhId.trim();
    }

    public String getMszhMc() {
        return mszhMc;
    }

    public void setMszhMc(String mszhMc) {
        this.mszhMc = mszhMc == null ? null : mszhMc.trim();
    }

    public String getMszhGjz() {
        return mszhGjz;
    }

    public void setMszhGjz(String mszhGjz) {
        this.mszhGjz = mszhGjz == null ? null : mszhGjz.trim();
    }

    public String getMszhZt() {
        return mszhZt;
    }

    public void setMszhZt(String mszhZt) {
        this.mszhZt = mszhZt == null ? null : mszhZt.trim();
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