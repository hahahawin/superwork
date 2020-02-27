package com.superwork.apcosplatform.domain;

public class QueryRequest<T> extends JsonRequest {


	private static final long serialVersionUID = -1097782155053261620L;
	/**
	 * 是否排序 0 不排 1 排序
	 */
	private int isSort;

	/**
	 * 正序 反序
	 */
	private String direction;

	/**
	 * 查询DO
	 */
	private T data;

	public int getIsSort() {
		return isSort;
	}

	public void setIsSort(int isSort) {
		this.isSort = isSort;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

}
