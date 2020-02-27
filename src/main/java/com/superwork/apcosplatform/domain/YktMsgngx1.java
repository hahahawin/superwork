package com.superwork.apcosplatform.domain;

import java.util.Date;

public class YktMsgngx1{
    
	private String msgngx_id;//msgngx_id
	private String msid;//模式id
	private String product_id;//功能id
	private String whid;//设备whid
	private String product_code;//设备类型
	private String code;//命令
	private String params;//命令参数
	private String memo;//命令名称
	private String memo1;//设备名称
	private String memo2;//备注
	private String memo3;//备注
	private String creator_id;//创建人id
	private Date created_time;//创建时间
	private String editor_id;//修改人id
	private Date edited_time;//修改时间
	private String belong_org_id;//单位组织节点id

	private String account; //设备账户
	private String ymsid; //云模式ID

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}


   
	/**
	 * msgngx_id
	 */	 
	public String getMsgngx_id(){
		return msgngx_id;
	}
	
	/**
	 * msgngx_id
	 */	
	public void setMsgngx_id(String msgngx_id){
		this.msgngx_id = msgngx_id;
	}
	
   
	/**
	 * 模式id
	 */	 
	public String getMsid(){
		return msid;
	}
	
	/**
	 * 模式id
	 */	
	public void setMsid(String msid){
		this.msid = msid;
	}
	
   
	/**
	 * 设备whid
	 */	 
	public String getWhid(){
		return whid;
	}
	
	/**
	 * 设备whid
	 */	
	public void setWhid(String whid){
		this.whid = whid;
	}
	
   
	/**
	 * 功能id
	 */	 
	public String getProduct_id(){
		return product_id;
	}
	
	/**
	 * 功能id
	 */	
	public void setProduct_id(String product_id){
		this.product_id = product_id;
	}
	
   
	/**
	 * 参数code
	 */	 
	public String getCode(){
		return code;
	}
	
	/**
	 * 参数code
	 */	
	public void setCode(String code){
		this.code = code;
	}
	
   
	/**
	 * 参数
	 */	 
	public String getParams(){
		return params;
	}
	
	/**
	 * 参数
	 */	
	public void setParams(String params){
		this.params = params;
	}
	
   
	/**
	 * 备注
	 */	 
	public String getMemo(){
		return memo;
	}
	
	/**
	 * 备注
	 */	
	public void setMemo(String memo){
		this.memo = memo;
	}
	
   
	/**
	 * 备注
	 */	 
	public String getMemo1(){
		return memo1;
	}
	
	/**
	 * 备注
	 */	
	public void setMemo1(String memo1){
		this.memo1 = memo1;
	}
	
   
	/**
	 * 备注
	 */	 
	public String getMemo2(){
		return memo2;
	}
	
	/**
	 * 备注
	 */	
	public void setMemo2(String memo2){
		this.memo2 = memo2;
	}
	
   
	/**
	 * 备注
	 */	 
	public String getMemo3(){
		return memo3;
	}
	
	/**
	 * 备注
	 */	
	public void setMemo3(String memo3){
		this.memo3 = memo3;
	}
	
   
	/**
	 * 创建人id
	 */	 
	public String getCreator_id(){
		return creator_id;
	}
	
	/**
	 * 创建人id
	 */	
	public void setCreator_id(String creator_id){
		this.creator_id = creator_id;
	}
	
   
	/**
	 * 创建时间
	 */	 
	public Date getCreated_time(){
		return created_time;
	}
	
	/**
	 * 创建时间
	 */	
	public void setCreated_time(Date created_time){
		this.created_time = created_time;
	}

	/**
	 * 修改人id
	 */	 
	public String getEditor_id(){
		return editor_id;
	}
	
	/**
	 * 修改人id
	 */	
	public void setEditor_id(String editor_id){
		this.editor_id = editor_id;
	}
	
   
	/**
	 * 修改时间
	 */	 
	public Date getEdited_time(){
		return edited_time;
	}
	
	/**
	 * 修改时间
	 */	
	public void setEdited_time(Date edited_time){
		this.edited_time = edited_time;
	}

	/**
	 * 单位组织节点id
	 */	 
	public String getBelong_org_id(){
		return belong_org_id;
	}
	
	/**
	 * 单位组织节点id
	 */	
	public void setBelong_org_id(String belong_org_id){
		this.belong_org_id = belong_org_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getYmsid() {
		return ymsid;
	}

	public void setYmsid(String ymsid) {
		this.ymsid = ymsid;
	}
}
