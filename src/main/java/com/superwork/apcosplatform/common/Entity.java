package com.superwork.apcosplatform.common;

import java.io.Serializable;

/**
 * 实体抽象类
 * 
 * @company Jinxin Computer Corp.
 * @author Chenbing
 * @email wwwchenbing@gmail.com
 * @description
 * @date 2012-7-20
 */
public abstract class Entity implements Serializable {

	private static final long serialVersionUID = -3618055865507347329L;

	protected Integer pageNo = 1;
	protected Integer pageSize = 10;
	protected String sort;	//排序字段
	protected String sortOrder;	//排序方式
	protected Integer totalRecords; //查询记录数



	public Integer getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(Integer totalRecords) {
		this.totalRecords = totalRecords;
	}

	public Entity() {}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	public Integer getPageStart(){//兼容mysql的分页
		return (this.pageNo - 1) * this.pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
