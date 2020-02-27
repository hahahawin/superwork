package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PDepRight implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-25 08:58:39
     */
    private String id;

    /**
     * 部门ID
     *
     * @mbg.generated 2019-12-25 08:58:39
     */
    private String depId;

    /**
     * 菜单ID
     *
     * @mbg.generated 2019-12-25 08:58:39
     */
    private String rightId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId == null ? null : depId.trim();
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId == null ? null : rightId.trim();
    }
}