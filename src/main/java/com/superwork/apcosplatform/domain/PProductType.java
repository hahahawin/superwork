package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PProductType implements Serializable {
    /**
     * 自增ID
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String id;

    /**
     * 类型名称
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String typeName;

    /**
     * 所属公司
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String companyId;

    /**
     * 创建者ID
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String createId;

    private String createName;

    /**
     * 创建时间
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createTime;

    /**
     * 编辑者
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String editId;

    /**
     * 编辑时间
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editTime;

    /**
     * 备注
     *
     * @mbg.generated 2020-02-11 16:18:31
     */
    private String remarks;

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId == null ? null : editId.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}