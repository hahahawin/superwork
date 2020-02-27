package com.superwork.apcosplatform.domain;

import java.util.Date;

/**
 * @author Jianjian Xu
 * @create: 2019/10/28 11:03
 * @description:
 */
public class PSbgl2 {

    private String id;
    private String product_code;
    private String account;
    private String serial_num;
    private String device_mac;
    private String device_name;
    private String device_type;
    private Date create_time;
    private Date update_time;
    private String whether_register;
    private String register_gateway_mac;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getDevice_mac() {
        return device_mac;
    }

    public void setDevice_mac(String device_mac) {
        this.device_mac = device_mac;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public String getWhether_register() {
        return whether_register;
    }

    public void setWhether_register(String whether_register) {
        this.whether_register = whether_register;
    }

    public String getRegister_gateway_mac() {
        return register_gateway_mac;
    }

    public void setRegister_gateway_mac(String register_gateway_mac) {
        this.register_gateway_mac = register_gateway_mac;
    }
}
