package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class PSysRight implements Serializable {
    /**
     * 权限ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String rightId;

    /**
     * 权限名称
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String rightName;

    /**
     * 权限类型：1:系统权限2：普通权限
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private BigDecimal rightType;

    /**
     * 权限路径
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String url;

    /**
     * 父级ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String parentId;

    /**
     * 1:菜单2按钮
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private BigDecimal isMeua;

    /**
     * 创建者
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String createId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createDate;

    /**
     * 修改者
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String editedId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 备注
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String remark;

    private List<PSysRight> chlidren;

    public List<PSysRight> getChlidren() {
        return chlidren;
    }

    public void setChlidren(List<PSysRight> chlidren) {
        this.chlidren = chlidren;
    }

    private static final long serialVersionUID = 1L;

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId == null ? null : rightId.trim();
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(String rightName) {
        this.rightName = rightName == null ? null : rightName.trim();
    }

    public BigDecimal getRightType() {
        return rightType;
    }

    public void setRightType(BigDecimal rightType) {
        this.rightType = rightType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public BigDecimal getIsMeua() {
        return isMeua;
    }

    public void setIsMeua(BigDecimal isMeua) {
        this.isMeua = isMeua;
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

    public String getEditedId() {
        return editedId;
    }

    public void setEditedId(String editedId) {
        this.editedId = editedId == null ? null : editedId.trim();
    }

    public Date getEditedTime() {
        return editedTime;
    }

    public void setEditedTime(Date editedTime) {
        this.editedTime = editedTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}