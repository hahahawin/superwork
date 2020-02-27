package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PTypeProduct implements Serializable {
    /**
     * 主键ID
     *
     * @mbg.generated 2020-02-11 16:54:16
     */
    private String id;

    /**
     * 设备ID
     *
     * @mbg.generated 2020-02-11 16:54:16
     */
    private String proId;

    /**
     * 分类ID
     *
     * @mbg.generated 2020-02-11 16:54:16
     */
    private String typeId;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId == null ? null : proId.trim();
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }
}