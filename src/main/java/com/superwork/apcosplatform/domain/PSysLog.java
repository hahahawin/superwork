package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PSysLog implements Serializable {
    private BigDecimal id;

    /**
     * 操作用户
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    private String username;

    /**
     * 方法
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    private String method;

    /**
     * IP
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    private String ip;

    /**
     * 操作
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    private String operation;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createDate;

    private String createName;

    /**
     * 参数
     *
     * @mbg.generated 2019-10-24 09:19:01
     */
    private String param;

    private static final long serialVersionUID = 1L;


    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method == null ? null : method.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation == null ? null : operation.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param == null ? null : param.trim();
    }
}