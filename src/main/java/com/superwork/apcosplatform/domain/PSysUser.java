package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PSysUser implements Serializable {
    /**
     * 用户ID
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String userId;

    /**
     * 登录账户
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String userAccount;

    /**
     * 密码
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String userPassword;

    /**
     * 姓名
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String userName;

    /**
     * 用户类型：1系统用户；2普通用户
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private BigDecimal userType;

    /**
     * 用户状态 1停用 2 启用
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private BigDecimal enable;

    /**
     * 性别 1女 2 男
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private BigDecimal gender;

    /**
     * 电话
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String cellphoneNo;

    /**
     * 邮箱
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String email;

    /**
     * 地址
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String address;

    /**
     * 备注
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String remark;

    /**
     * 创建者
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String creatorId;

    /**
     * 创建时间
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;

    /**
     * 修改者
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String editorId;

    /**
     * 修改时间
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;

    /**
     * 身份证
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String cardId;

    /**
     * 生日
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String birthday;

    /**
     * 1:删除2：没有删除
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String isdel;

    /**
     * 账号等级 1 主账号 2 子账号
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String userLevel;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String attr1;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String attr2;

    /**
     * 备用
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String attr3;

    /**
     * 头像
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String headportrarit;

    /**
     * 组织名称
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String orgName;

    /**
     * 公司名称
     *
     * @mbg.generated 2020-01-11 11:22:08
     */
    private String companyName;

    private boolean checked;

    private static final long serialVersionUID = 1L;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount == null ? null : userAccount.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public BigDecimal getUserType() {
        return userType;
    }

    public void setUserType(BigDecimal userType) {
        this.userType = userType;
    }

    public BigDecimal getEnable() {
        return enable;
    }

    public void setEnable(BigDecimal enable) {
        this.enable = enable;
    }

    public BigDecimal getGender() {
        return gender;
    }

    public void setGender(BigDecimal gender) {
        this.gender = gender;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public void setCellphoneNo(String cellphoneNo) {
        this.cellphoneNo = cellphoneNo == null ? null : cellphoneNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel == null ? null : isdel.trim();
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
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

    public String getHeadportrarit() {
        return headportrarit;
    }

    public void setHeadportrarit(String headportrarit) {
        this.headportrarit = headportrarit == null ? null : headportrarit.trim();
    }

    @Override
    public String toString() {
        return "PSysUser{" +
                "userId='" + userId + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                ", enable=" + enable +
                ", gender=" + gender +
                ", cellphoneNo='" + cellphoneNo + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", createdTime=" + createdTime +
                ", editorId='" + editorId + '\'' +
                ", editedTime=" + editedTime +
                ", cardId='" + cardId + '\'' +
                ", birthday='" + birthday + '\'' +
                ", isdel='" + isdel + '\'' +
                ", userLevel='" + userLevel + '\'' +
                ", attr1='" + attr1 + '\'' +
                ", attr2='" + attr2 + '\'' +
                ", attr3='" + attr3 + '\'' +
                ", headportrarit='" + headportrarit + '\'' +
                ", orgName='" + orgName + '\'' +
                ", checked=" + checked +
                '}';
    }
}