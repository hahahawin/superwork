package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PZzUser implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-26 09:13:08
     */
    private String id;

    /**
     * 用户ID
     *
     * @mbg.generated 2019-12-26 09:13:08
     */
    private String userId;

    /**
     * 组织ID
     *
     * @mbg.generated 2019-12-26 09:13:08
     */
    private String zzId;

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

    public String getZzId() {
        return zzId;
    }

    public void setZzId(String zzId) {
        this.zzId = zzId == null ? null : zzId.trim();
    }
}