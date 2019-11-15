package com.example.juandie_hua.mainactivity.fenlei;

public class fenleizi_adaData {
	String id, name, order, by;

	public fenleizi_adaData(String id, String name, String order, String by) {
		this.id = id;
		this.name = name;
		this.order=order;
		this.by=by;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
