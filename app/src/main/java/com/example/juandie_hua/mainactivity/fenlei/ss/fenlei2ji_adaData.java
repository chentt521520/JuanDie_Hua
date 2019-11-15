package com.example.juandie_hua.mainactivity.fenlei.ss;

import java.util.List;

public class fenlei2ji_adaData {
	String id, name;
	List<fenleizi_adaData> data;

	public fenlei2ji_adaData(String id, String name, List<fenleizi_adaData> data) {
		this.id = id;
		this.name = name;
		this.data = data;
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

	public List<fenleizi_adaData> getData() {
		return data;
	}

	public void setData(List<fenleizi_adaData> data) {
		this.data = data;
	}
}
