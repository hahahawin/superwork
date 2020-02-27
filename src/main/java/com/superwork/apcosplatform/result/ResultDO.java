package com.superwork.apcosplatform.result;

import java.io.Serializable;

public class ResultDO<T> implements Serializable{

	private boolean isSuccess = true;

	private Integer errorCode;

	private String errorMsg;

	private Long count;

	private T data;


	public static String ORDER_QUERY_MSG_ERROR = "查询失败";

	public static String ORDER_GET_MSG_ERROR = "通过id获取失败";

	public static String ORDER_MODIFY_MSG_ERROR = "修改失败";

	public static String ORDER_DELETE_MSG_ERROR = "删除失败";

	public static String ORDER_ADD_MSG_ERROR = "添加失败";

	public static String ORDER_ID_MSG_ERROR = "ID异常";

	public static Integer ORDER_QUERY_ERROR = 8001;

	public static Integer ORDER_GET_ERROR = 8002;

	public static Integer ORDER_MODIFY_ERROR = 8003;

	public static Integer ORDER_DELETE_ERROR = 8004;

	public static Integer ORDER_ADD_ERROR = 8005;

	public static Integer ORDER_ID_ERROR = 8006;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
