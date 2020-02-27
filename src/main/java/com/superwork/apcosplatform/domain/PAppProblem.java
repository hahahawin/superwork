package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class PAppProblem implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String id;

    /**
     * 问题描述
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String problemDesc;

    /**
     * 发生时间
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String occurrenceTime;

    /**
     * 附件
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String fielPath;

    /**
     * 创建者
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
     * 回复内容
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String replyContent;

    /**
     * 回复者
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String replyId;

    /**
     * 回复时间
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date replyTime;

    /**
     * 处理状态：1：已解决；2：未解决
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String status;

    /**
     * 备注
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String remark;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String arrt1;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String arrt2;

    /**
     * 备用
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String arrt3;

    /**
     * 电话号码
     *
     * @mbg.generated 2019-12-18 15:29:24
     */
    private String phone;

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getProblemDesc() {
        return problemDesc;
    }

    public void setProblemDesc(String problemDesc) {
        this.problemDesc = problemDesc == null ? null : problemDesc.trim();
    }

    public String getOccurrenceTime() {
        return occurrenceTime;
    }

    public void setOccurrenceTime(String occurrenceTime) {
        this.occurrenceTime = occurrenceTime == null ? null : occurrenceTime.trim();
    }

    public String getFielPath() {
        return fielPath;
    }

    public void setFielPath(String fielPath) {
        this.fielPath = fielPath == null ? null : fielPath.trim();
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

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent == null ? null : replyContent.trim();
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId == null ? null : replyId.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getArrt1() {
        return arrt1;
    }

    public void setArrt1(String arrt1) {
        this.arrt1 = arrt1 == null ? null : arrt1.trim();
    }

    public String getArrt2() {
        return arrt2;
    }

    public void setArrt2(String arrt2) {
        this.arrt2 = arrt2 == null ? null : arrt2.trim();
    }

    public String getArrt3() {
        return arrt3;
    }

    public void setArrt3(String arrt3) {
        this.arrt3 = arrt3 == null ? null : arrt3.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}