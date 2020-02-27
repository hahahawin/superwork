package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description //设备账户表
 * @author xjj
 * @date 2019/10/24
 */
public class PSmhSetting implements Serializable {
    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 登录者账号
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
     private String   userAccount ;

    /**
     * 主键ID
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private BigDecimal settingId;

    /**
     * 账户
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String smarthomeAccount;

    /**
     * 密码
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String smarthomePwd;

    /**
     * 是否开通：SFBZ 1.停用 2 .启用
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String sfkt;

    /**
     * 2 主账户
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr1;

    /**
     * 订阅结果：1已订阅 2 为通过 3未订阅
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr2;

    /**
     * 第三方账户ID
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr3;

    /**
     * 姓名
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr4;

    /**
     * 身份证号码
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr6;

    /**
     * 手机号码
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr7;

    /**
     * 注册邮箱
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr8;

    /**
     * 城市代码
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr9;

    /**
     * 性别：1男2女
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String attr5;

    /**
     * 创建人id
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改人id
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 单位组织节点ID
     *
     * @mbg.generated 2019-10-24 15:27:01
     */
    private String belongOrgId;

    private static final long serialVersionUID = 1L;

    public BigDecimal getSettingId() {
        return settingId;
    }

    public void setSettingId(BigDecimal settingId) {
        this.settingId = settingId;
    }

    public String getSmarthomeAccount() {
        return smarthomeAccount;
    }

    public void setSmarthomeAccount(String smarthomeAccount) {
        this.smarthomeAccount = smarthomeAccount == null ? null : smarthomeAccount.trim();
    }

    public String getSmarthomePwd() {
        return smarthomePwd;
    }

    public void setSmarthomePwd(String smarthomePwd) {
        this.smarthomePwd = smarthomePwd == null ? null : smarthomePwd.trim();
    }

    public String getSfkt() {
        return sfkt;
    }

    public void setSfkt(String sfkt) {
        this.sfkt = sfkt == null ? null : sfkt.trim();
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

    public String getAttr4() {
        return attr4;
    }

    public void setAttr4(String attr4) {
        this.attr4 = attr4 == null ? null : attr4.trim();
    }

    public String getAttr6() {
        return attr6;
    }

    public void setAttr6(String attr6) {
        this.attr6 = attr6 == null ? null : attr6.trim();
    }

    public String getAttr7() {
        return attr7;
    }

    public void setAttr7(String attr7) {
        this.attr7 = attr7 == null ? null : attr7.trim();
    }

    public String getAttr8() {
        return attr8;
    }

    public void setAttr8(String attr8) {
        this.attr8 = attr8 == null ? null : attr8.trim();
    }

    public String getAttr9() {
        return attr9;
    }

    public void setAttr9(String attr9) {
        this.attr9 = attr9 == null ? null : attr9.trim();
    }

    public String getAttr5() {
        return attr5;
    }

    public void setAttr5(String attr5) {
        this.attr5 = attr5 == null ? null : attr5.trim();
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getEditorId() {
        return editorId;
    }

    public void setEditorId(String editorId) {
        this.editorId = editorId == null ? null : editorId.trim();
    }

    public Date getEditedTime() {
        return editedTime;
    }

    public void setEditedTime(Date editedTime) {
        this.editedTime = editedTime;
    }

    public String getBelongOrgId() {
        return belongOrgId;
    }

    public void setBelongOrgId(String belongOrgId) {
        this.belongOrgId = belongOrgId == null ? null : belongOrgId.trim();
    }
}