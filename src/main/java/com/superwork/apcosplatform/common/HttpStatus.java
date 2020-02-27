package com.superwork.apcosplatform.common;

public enum HttpStatus {

    SYSTEM_ERROR("系统错误", 500),

    EXCEPTION_ERROR("异常", 513),

    CHECK_ERROR("数据校验出错",514),

    LOGIN_ERROR("登录错误", 519),

    LOGIN_AUTHOR_ERROR("你没有登录权限", 518),

    USER_ACTIVATION_ERROR("用户未激活", 515),

    BINDING_ERROR("绑定失败", 516),

    UNSUBSCRIBE_ERROR("未关注微信公众号", 517),

    MSMCODE_ERROR("验证码校验失败", 518),

    MSMCODETIME_ERROR("验证码失效，请再次获取", 519),

    SUCCESS("成功", 200);


    private int errorCode;

    private String msg;

    HttpStatus(String msg, int errorCode) {
        this.msg = msg;
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
