package com.superwork.apcosplatform.domain;

public class ExcutionPlanProcess{

    private String id;
    private String process_name;
    private String is_valid;
    private String ser_order;
    private String trigger_conditon_desc;
    private String plan_id;
    private String has_condition;
    private String trigger_conditon_params;
    private String trigger_times_params;
    private String mode_ids;
    private String mode_desc;
    private String pre_trigger_time;
    private String delay_trigger_time;
    private String create_time;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcess_name() {
        return process_name;
    }

    public void setProcess_name(String process_name) {
        this.process_name = process_name;
    }

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    public String getSer_order() {
        return ser_order;
    }

    public void setSer_order(String ser_order) {
        this.ser_order = ser_order;
    }

    public String getTrigger_conditon_desc() {
        return trigger_conditon_desc;
    }

    public void setTrigger_conditon_desc(String trigger_conditon_desc) {
        this.trigger_conditon_desc = trigger_conditon_desc;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getHas_condition() {
        return has_condition;
    }

    public void setHas_condition(String has_condition) {
        this.has_condition = has_condition;
    }

    public String getTrigger_conditon_params() {
        return trigger_conditon_params;
    }

    public void setTrigger_conditon_params(String trigger_conditon_params) {
        this.trigger_conditon_params = trigger_conditon_params;
    }

    public String getTrigger_times_params() {
        return trigger_times_params;
    }

    public void setTrigger_times_params(String trigger_times_params) {
        this.trigger_times_params = trigger_times_params;
    }

    public String getMode_ids() {
        return mode_ids;
    }

    public void setMode_ids(String mode_ids) {
        this.mode_ids = mode_ids;
    }

    public String getMode_desc() {
        return mode_desc;
    }

    public void setMode_desc(String mode_desc) {
        this.mode_desc = mode_desc;
    }

    public String getPre_trigger_time() {
        return pre_trigger_time;
    }

    public void setPre_trigger_time(String pre_trigger_time) {
        this.pre_trigger_time = pre_trigger_time;
    }

    public String getDelay_trigger_time() {
        return delay_trigger_time;
    }

    public void setDelay_trigger_time(String delay_trigger_time) {
        this.delay_trigger_time = delay_trigger_time;
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