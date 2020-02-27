package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PAppCommonpro implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String id;

    /**
     * 问题标题
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String proTitle;

    /**
     * 解决方案
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String solution;

    /**
     * 栏目ID
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String columuId;

    private String title;

    /**
     * 赞成数
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String favorNum;

    /**
     * 反对数
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String oppositionNum;

    /**
     * 创建者ID
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String createId;

    private String createName;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createTime;

    /**
     * 编辑者ID
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String editId;

    /**
     * 编辑时间
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editTime;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String attr1;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String attr2;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String attr3;

    private String evaluateType;

    /**
     * 备用 评价类型
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    public String getEvaluateType() {
        return evaluateType;
    }

    public void setEvaluateType(String evaluateType) {
        this.evaluateType = evaluateType;
    }

    private static final long serialVersionUID = 1L;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProTitle() {
        return proTitle;
    }

    public void setProTitle(String proTitle) {
        this.proTitle = proTitle == null ? null : proTitle.trim();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }

    public String getColumuId() {
        return columuId;
    }

    public void setColumuId(String columuId) {
        this.columuId = columuId == null ? null : columuId.trim();
    }

    public String getFavorNum() {
        return favorNum;
    }

    public void setFavorNum(String favorNum) {
        this.favorNum = favorNum == null ? null : favorNum.trim();
    }

    public String getOppositionNum() {
        return oppositionNum;
    }

    public void setOppositionNum(String oppositionNum) {
        this.oppositionNum = oppositionNum == null ? null : oppositionNum.trim();
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getEditId() {
        return editId;
    }

    public void setEditId(String editId) {
        this.editId = editId == null ? null : editId.trim();
    }

    public Date getEditTime() {
        return editTime;
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
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