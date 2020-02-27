package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PScene implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String id;

    /**
     * 场景名称
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String name;

    /**
     * 场景X轴刻度
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String xlen;

    /**
     * 场景Y轴刻度
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String ylen;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String creator;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createtime;

    /**
     * 编辑者ID
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String editor;

    /**
     * 编辑时间
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private String editime;

    /**
     * 备注
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String remarks;

    private String attr1;

    private String attr2;

    private String attr3;

    /**
     * 场景图片
     *
     * @mbg.generated 2019-12-05 17:19:06
     */
    private String img;

    private String creataName;

    public String getCreataName() {
        return creataName;
    }

    public void setCreataName(String creataName) {
        this.creataName = creataName;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getXlen() {
        return xlen;
    }

    public void setXlen(String xlen) {
        this.xlen = xlen == null ? null : xlen.trim();
    }

    public String getYlen() {
        return ylen;
    }

    public void setYlen(String ylen) {
        this.ylen = ylen == null ? null : ylen.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor == null ? null : editor.trim();
    }

    public String getEditime() {
        return editime;
    }

    public void setEditime(String editime) {
        this.editime = editime == null ? null : editime.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}