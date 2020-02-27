package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PRoleUser implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String id;

    /**
     * 角色ID
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String roleId;

    /**
     * 用户ID
     *
     * @mbg.generated 2019-12-26 15:03:09
     */
    private String userId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}