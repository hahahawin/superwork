package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class POrgUser implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String id;

    /**
     * 部门ID
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String orgId;

    /**
     * 用户ID
     *
     * @mbg.generated 2020-01-08 14:01:20
     */
    private String userId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}