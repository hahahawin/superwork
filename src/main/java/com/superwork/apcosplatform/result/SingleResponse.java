package com.superwork.apcosplatform.result;

public class SingleResponse<T> extends JsonResponse {

	private T data;

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}


}
