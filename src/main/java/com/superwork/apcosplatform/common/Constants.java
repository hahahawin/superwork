package com.superwork.apcosplatform.common;

public final class Constants {

//	8B43374E4B7BACECB0752FD270212476  56C8F289C10296E83EA563A880B53E97
	/**
	 * "是/否"字段值	1:否
	 */
	public final static Integer FV_NO = 1;
	/**
	 * "是/否"字段值	2:是
	 */
	public final static Integer FV_YES = 2;
	
	/**
	 * "启用/禁用"字段值	1:禁用
	 */
	public final static Integer FV_DISABLE = 1;
	/**
	 * "启用/禁用"字段值	2:启用
	 */
	public final static Integer FV_ENABLE = 2;

	//大管家URL 验证
//	public static final String dgj_url = "http://47.100.79.34:20081/dataService/doService";
//	public final static String appid = "MbdVhy6xG199v7DZYGs";
//	public final static String appkey = "964ce1fd8d2d45bd87433dbb0ab088cd";
//	public final static String jrszh = "AP000861";
//	public final static String jrszhmm = "023738";
	//大管家URL 平台
//	public static final String dgj_url = "http://www.beoneaut.com:6888/dataService/doService";
//	public final static String appid = "school";
//	public final static String appkey = "2DA3FAE5B653EFB5DF08C263BE43F95BC3265F432BBF39CEDBE918DB007944BC";
//	public final static String jrszh = "AP010500";
	//成都设备控制
	public static final String bcca_url = "http://118.31.70.12";
	public static final String service_id = "10502";
	public static final String service_key = "2e08c9a281d04145b495e93d4faf99fc";
	public static final String account = "C280010011";

	//APCOS开发者平台
//	public static final String apcos_url = "http://developer.beonelot.com:6868";
//	public static final String apcos_service_id = "c765a9954d31483dd31141772ad535fa";
//	public static final String apcos_service_key = "620bced2792420f31b0e578971f58b7e";

//	public static final String apcos_url = "http://39.104.74.122:20003";
//	public static final String apcos_service_id = "c765a9954d31483dd31141772ad535fa";
//	public static final String apcos_service_key = "620bced2792420f31b0e578971f58b7e";

//	海克斯账号C230010247     校园  C280010011  C280010011   密码111111

	public static final String MANY_URL_SEPARATOR = "@jxpublic@";// 多个URL间的分隔符;


	/**
	 * <p>用于Ajax请求处理结果</p>
	 * <p>常量字段：结果码</p>
	 * <p>调用举例：this.jsonObject.put(RESULT_CODE, SUCCESS);</p>
	 */
	public static final String RESULT_CODE = "resultCode";
	/**
	 * <p>用于Ajax请求处理结果</p>
	 * <p>常量字段：结果消息</p>
	 * <p>调用举例：this.jsonObject.put(RESULT_MSG, "操作失败，请检查输入参数！");</p>
	 */
	public static final String RESULT_MSG = "resultMsg";


	public static final Integer OPERATE_STATUS_SUCCESS = 1; // 操作成功
	public static final Integer OPERATE_STATUS_FAILE = 2; // 操作失败
	public static final String LOGIN_ACTION="loginAction";




}
