package com.superwork.apcosplatform.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class JsonRequest implements Serializable {

	private static final long serialVersionUID = -2992906514391243537L;

	/**
	 * 版本号根据版本号来区别旧版本以后兼容性上使用
	 */
	private int version;


	private String token;

	private Integer page;

	private Integer limit;

	private BigDecimal id;

	private BigDecimal rightType;

	private String cmdId;

	private String productName;

	private List<BigDecimal> ids;


	public List<BigDecimal> getIds() {
		return ids;
	}

	public void setIds(List<BigDecimal> ids) {
		this.ids = ids;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getRightType() {
		return rightType;
	}

	public void setRightType(BigDecimal rightType) {
		this.rightType = rightType;
	}

	public String getCmdId() {
		return cmdId;
	}

	public void setCmdId(String cmdId) {
		this.cmdId = cmdId;
	}

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
