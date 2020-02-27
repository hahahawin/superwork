package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.xml.ws.Service;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PAppColumn implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String id;

    /**
     * 栏目
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String title;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createTime;

    /**
     * 创建人ID
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String createId;

    private String createName;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     * 编辑人ID
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

    private List<PAppCommonpro> listCommonPro;

    public List<PAppCommonpro> getListCommonPro() {
        return listCommonPro;
    }

    public void setListCommonPro(List<PAppCommonpro> listCommonPro) {
        this.listCommonPro = listCommonPro;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId == null ? null : createId.trim();
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