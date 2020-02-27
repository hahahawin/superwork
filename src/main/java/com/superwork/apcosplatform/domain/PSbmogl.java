package com.superwork.apcosplatform.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ApiModel(value = "模式")
public class PSbmogl implements Serializable {

    private String sbmoglId;//模式id:本地模式id

    private String sbmoglYmsid;//云服务模式id

    private String sbmoglName;//模式名称

    private String sbmoglZt;//模式状态  0禁用 1启用

    private String sbmoglLx;//模式类型

    private String sbmoglTb;//模式图标

    private String sbmoglQdfy;//启动发音控制

    private String sbmoglJsfy;//结束发音控制

    private String sbmoglQylx;//区域类型 所属类型 1楼宇 2楼层 3房间 9其他(账户)

    private String sbmoglKzqy;//对应所属类型的ID 如1存楼宇ID 2楼层ID 3房间ID 9空

    private String creatorId;//创建人id

    private String createName;
    public String getCreateName() {
        return createName;
    }
    public void setCreateName(String createName) {
        this.createName = createName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date createdTime;//创建时间

    private String editorId;//修改人id

    private String editName;

    public String getEditName() {
        return editName;
    }

    public void setEditName(String editName) {
        this.editName = editName;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") //入参
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //出参
    private Date editedTime;//修改时间

    private String belongOrgId;//单位组织节点id

    private String attr1;//模式账户ID

    private String attr2;//设备账户

    private String attr3;//备注

    private String attr4;//语音命令

    private String outputwhid;//输出设备

    private String outputvoicer;//播音人

    private String outputspeed;//语速

    private String outputvoicelevel;//音量

    private String outputhasmusic;//背景音乐

    private String mac;//MAC地址

    private List<PYktMsgngx> listMl;

    public List<PYktMsgngx> getListMl() {
        return listMl;
    }

    public void setListMl(List<PYktMsgngx> listMl) {
        this.listMl = listMl;
    }

    private static final long serialVersionUID = 1L;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getSbmoglId() {
        return sbmoglId;
    }

    public void setSbmoglId(String sbmoglId) {
        this.sbmoglId = sbmoglId == null ? null : sbmoglId.trim();
    }

    public String getSbmoglYmsid() {
        return sbmoglYmsid;
    }

    public void setSbmoglYmsid(String sbmoglYmsid) {
        this.sbmoglYmsid = sbmoglYmsid == null ? null : sbmoglYmsid.trim();
    }

    public String getSbmoglName() {
        return sbmoglName;
    }

    public void setSbmoglName(String sbmoglName) {
        this.sbmoglName = sbmoglName == null ? null : sbmoglName.trim();
    }

    public String getSbmoglZt() {
        return sbmoglZt;
    }

    public void setSbmoglZt(String sbmoglZt) {
        this.sbmoglZt = sbmoglZt == null ? null : sbmoglZt.trim();
    }

    public String getSbmoglLx() {
        return sbmoglLx;
    }

    public void setSbmoglLx(String sbmoglLx) {
        this.sbmoglLx = sbmoglLx == null ? null : sbmoglLx.trim();
    }

    public String getSbmoglTb() {
        return sbmoglTb;
    }

    public void setSbmoglTb(String sbmoglTb) {
        this.sbmoglTb = sbmoglTb == null ? null : sbmoglTb.trim();
    }

    public String getSbmoglQdfy() {
        return sbmoglQdfy;
    }

    public void setSbmoglQdfy(String sbmoglQdfy) {
        this.sbmoglQdfy = sbmoglQdfy == null ? null : sbmoglQdfy.trim();
    }

    public String getSbmoglJsfy() {
        return sbmoglJsfy;
    }

    public void setSbmoglJsfy(String sbmoglJsfy) {
        this.sbmoglJsfy = sbmoglJsfy == null ? null : sbmoglJsfy.trim();
    }

    public String getSbmoglQylx() {
        return sbmoglQylx;
    }

    public void setSbmoglQylx(String sbmoglQylx) {
        this.sbmoglQylx = sbmoglQylx == null ? null : sbmoglQylx.trim();
    }

    public String getSbmoglKzqy() {
        return sbmoglKzqy;
    }

    public void setSbmoglKzqy(String sbmoglKzqy) {
        this.sbmoglKzqy = sbmoglKzqy == null ? null : sbmoglKzqy.trim();
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

    public String getOutputwhid() {
        return outputwhid;
    }

    public void setOutputwhid(String outputwhid) {
        this.outputwhid = outputwhid == null ? null : outputwhid.trim();
    }

    public String getOutputvoicer() {
        return outputvoicer;
    }

    public void setOutputvoicer(String outputvoicer) {
        this.outputvoicer = outputvoicer == null ? null : outputvoicer.trim();
    }

    public String getOutputspeed() {
        return outputspeed;
    }

    public void setOutputspeed(String outputspeed) {
        this.outputspeed = outputspeed == null ? null : outputspeed.trim();
    }

    public String getOutputvoicelevel() {
        return outputvoicelevel;
    }

    public void setOutputvoicelevel(String outputvoicelevel) {
        this.outputvoicelevel = outputvoicelevel == null ? null : outputvoicelevel.trim();
    }

    public String getOutputhasmusic() {
        return outputhasmusic;
    }

    public void setOutputhasmusic(String outputhasmusic) {
        this.outputhasmusic = outputhasmusic == null ? null : outputhasmusic.trim();
    }


}