package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PDepartment implements Serializable {
    /**
     * 菜单ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String depId;

    /**
     * 菜单名称
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String name;

    /**
     * 类型：1：系统内置2：自定义
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private BigDecimal sysType;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createTime;

    /**
     * 创建者
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String createUserid;

    private String createName;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editTime;

    /**
     * 修改者
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String editUserid;

    private String editName;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String remark;


    private String companyId;

    private static final long serialVersionUID = 1L;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId == null ? null : depId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getSysType() {
        return sysType;
    }

    public void setSysType(BigDecimal sysType) {
        this.sysType = sysType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(String createUserid) {
        this.createUserid = createUserid == null ? null : createUserid.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getEditUserid() {
        return editUserid;
    }

    public void setEditUserid(String editUserid) {
        this.editUserid = editUserid == null ? null : editUserid.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}