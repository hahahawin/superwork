package com.superwork.apcosplatform.domain;

public class SbzclxTemp {

    private String id;
    private String product_code;
    private String template_name;
    private String template_author;
    private String template_type;
    private String template_price;
    private String template_thumbnail;
    private String create_time;
    private String update_time;
    private String template_html;
    private String status ;// 0 代表未购买 1代表购买
    private String is_default ;//null以前的数据当做非默认  1非默认需购买后使用  2默认模板可以直接使用


    public String getTemplate_html() {
        return template_html;
    }

    public void setTemplate_html(String template_html) {
        this.template_html = template_html;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIs_default() {
        return is_default;
    }

    public void setIs_default(String is_default) {
        this.is_default = is_default;
    }

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

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getTemplate_author() {
        return template_author;
    }

    public void setTemplate_author(String template_author) {
        this.template_author = template_author;
    }

    public String getTemplate_type() {
        return template_type;
    }

    public void setTemplate_type(String template_type) {
        this.template_type = template_type;
    }

    public String getTemplate_price() {
        return template_price;
    }

    public void setTemplate_price(String template_price) {
        this.template_price = template_price;
    }

    public String getTemplate_thumbnail() {
        return template_thumbnail;
    }

    public void setTemplate_thumbnail(String template_thumbnail) {
        this.template_thumbnail = template_thumbnail;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}