package com.njm.oss.common.util;

public class Pager {

	private long total;
	private int pageCount;
	private int pageNum;
	private int pageSize;

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int calculatePageCount(long total, int pageSize) {
		this.total = total;
		this.pageSize = pageSize;
		this.pageCount = (int) (total % pageSize > 0 ? ((total / pageSize) + 1) : (total / pageSize));
		return this.pageCount;
	}
}
