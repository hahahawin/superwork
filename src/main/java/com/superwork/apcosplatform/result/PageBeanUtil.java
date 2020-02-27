package com.superwork.apcosplatform.result;


/**
 * <p>Description: 分页类</p>
 * @date 2013年9月23日
 * @version 1.0
 * <p>Company:reagan</p>
 * <p>Copyright:Copyright(c)2013</p>
 */
public class PageBeanUtil extends JsonResponse {


	/**
	 * 当前页
	 */
	private int pageindex = 1;

	/**
	 * 每页显示的结果数
	 */
	private int pagesize = 10;

	/**
	 * 总记录数
	 */
	private long total;


	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

}

