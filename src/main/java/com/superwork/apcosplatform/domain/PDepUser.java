package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PDepUser implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String id;

    /**
     * 用户ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String userId;

    /**
     * 部门ID
     *
     * @mbg.generated 2019-10-23 14:29:29
     */
    private String depId;

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

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId == null ? null : depId.trim();
    }
}