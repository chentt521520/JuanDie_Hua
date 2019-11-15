package com.example.juandie_hua.mainactivity.fenlei.ss;

public class fenlei_sslistdata {
	String by, text, order, cid, filter_attr;

	public fenlei_sslistdata(String by, String text, String order, String cid,
			String filter_attr) {
		this.by = by;
		this.text = text;
		this.order = order;
		this.cid = cid;
		this.filter_attr = filter_attr;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getFilter_attr() {
		return filter_attr;
	}

	public void setFilter_attr(String filter_attr) {
		this.filter_attr = filter_attr;
	}
}
