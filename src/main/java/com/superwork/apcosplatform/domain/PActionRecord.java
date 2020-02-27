package com.superwork.apcosplatform.domain;

import java.io.Serializable;
import java.util.Date;

public class PActionRecord implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String id;

    /**
     * 用户ID
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String userId;

    /**
     * 常见问题ID
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String comonproId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private Date createTime;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String attr1;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String attr2;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:23
     */
    private String attr3;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getComonproId() {
        return comonproId;
    }

    public void setComonproId(String comonproId) {
        this.comonproId = comonproId == null ? null : comonproId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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