package com.superwork.apcosplatform.domain;

import java.io.Serializable;

public class PSbalias implements Serializable {
    /**
     * 主键
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String id;

    /**
     * MAC地址
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String serialnum;

    /**
     * 设备别名
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String deviceName;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String arrt1;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String arrt2;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String arrt3;

    /**
     * 别名
     *
     * @mbg.generated 2020-01-07 14:25:10
     */
    private String remark;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSerialnum() {
        return serialnum;
    }

    public void setSerialnum(String serialnum) {
        this.serialnum = serialnum == null ? null : serialnum.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}