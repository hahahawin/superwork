package com.superwork.apcosplatform.result;

import java.util.List;

public class ListResponse<T> extends JsonResponse {

	public Long total;

	public List<T> data;

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

}
