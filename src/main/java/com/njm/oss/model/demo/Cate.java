package com.njm.oss.model.demo;

import java.util.List;

public class Cate {
	private int purchase_id;
	private int parent_id;
	private String purchase_nm;
	private int catalog;

	private List<Cate> children;

	public int getPurchase_id() {
		return purchase_id;
	}

	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}

	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getPurchase_nm() {
		return purchase_nm;
	}

	public void setPurchase_nm(String purchase_nm) {
		this.purchase_nm = purchase_nm;
	}

	public int getCatalog() {
		return catalog;
	}

	public void setCatalog(int catalog) {
		this.catalog = catalog;
	}

	public List<Cate> getChildren() {
		return children;
	}

	public void setChildren(List<Cate> children) {
		this.children = children;
	}

}
