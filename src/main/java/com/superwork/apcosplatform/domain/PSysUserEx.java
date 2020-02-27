package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PSysUserEx implements Serializable {
    /**
     * 主键自增
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String id;

    /**
     * 用户ID
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String userId;

    /**
     * 备用1
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String attr1;

    /**
     * 备用2
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String attr2;

    /**
     * 备用3
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String attr3;

    /**
     * 用户头像
     *
     * @mbg.generated 2019-12-10 14:51:56
     */
    private String headportrarit;

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

    public String getHeadportrarit() {
        return headportrarit;
    }

    public void setHeadportrarit(String headportrarit) {
        this.headportrarit = headportrarit == null ? null : headportrarit.trim();
    }
}