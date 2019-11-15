package com.example.juandie_hua.mainactivity.fenlei;

public class fenlei_adaData {
	String id, name,url;
	boolean select;

	public fenlei_adaData(String id, String name, boolean select,String url) {
		this.id = id;
		this.name = name;
		this.select = select;
		this.url=url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
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
