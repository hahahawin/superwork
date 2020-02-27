package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PRole implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String id;

    /**
     * 角色名
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String roleName;

    /**
     * 创建者ID
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String createId;

    private String createName;



    /**
     * 创建时间
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createDate;

    /**
     * 编辑者
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String editId;

    /**
     * 编辑时间
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editDate;

    /**
     * 组织ID
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String orgId;

    /**
     * 备注
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String remark;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String arrt1;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String arrt2;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String arrt3;

    private static final long serialVersionUID = 1L;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId == null ? null : editId.trim();
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getArrt1() {
        return arrt1;
    }

    public void setArrt1(String arrt1) {
        this.arrt1 = arrt1 == null ? null : arrt1.trim();
    }

    public String getArrt2() {
        return arrt2;
    }

    public void setArrt2(String arrt2) {
        this.arrt2 = arrt2 == null ? null : arrt2.trim();
    }

    public String getArrt3() {
        return arrt3;
    }

    public void setArrt3(String arrt3) {
        this.arrt3 = arrt3 == null ? null : arrt3.trim();
    }
}