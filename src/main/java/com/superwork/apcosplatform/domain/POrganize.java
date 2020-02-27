package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;


import java.io.Serializable;
import java.util.Date;

public class POrganize implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String id;

    /**
     * 组织名称
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String orgName;

    /**
     * 父级ID
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String pid;

    /**
     * 创建时间
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createDate;

    /**
     * 创建人
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String createId;

    private String createName;

    /**
     * 编辑时间
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editDate;

    /**
     * 编辑人
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String editId;

    /**
     * 所属公司ID
     *
     * @mbg.generated 2020-02-08 13:52:42
     */
    private String companyId;


    /**
     * 层级
     *
     * @mbg.generated 2020-02-09 10:27:32
     */
    private String levels;

    /**
     * 备注
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String remark;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String attr1;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String attr2;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String attr3;

    private String num;

    private static final long serialVersionUID = 1L;


    public String getLevels() {
        return levels;
    }

    public void setLevels(String levels) {
        this.levels = levels;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName == null ? null : orgName.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId == null ? null : editId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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