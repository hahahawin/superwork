package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PControFormwork implements Serializable {
    private BigDecimal id;

    /**
     * 类型编码
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String code;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private Date createdTime;

    /**
     * 备用字段1
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String attr1;

    /**
     * 备用字段2
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String attr2;

    /**
     * 备用字段3
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String attr3;

    /**
     * 网页模板 pc 端
     *
     * @mbg.generated 2019-11-05 17:45:08
     */
    private String htmlmodle;

    private static final long serialVersionUID = 1L;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
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

    public String getHtmlmodle() {
        return htmlmodle;
    }

    public void setHtmlmodle(String htmlmodle) {
        this.htmlmodle = htmlmodle == null ? null : htmlmodle.trim();
    }
}