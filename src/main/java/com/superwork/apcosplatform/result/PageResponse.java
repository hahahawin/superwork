package com.superwork.apcosplatform.result;


import java.math.BigDecimal;
import java.util.List;

public class PageResponse<T> extends PageBeanUtil {

	private Long cashCount;

	private BigDecimal totalAmount;

	public List<T> data;

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Long getCashCount() {
		return cashCount;
	}

	public void setCashCount(Long cashCount) {
		this.cashCount = cashCount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

}
