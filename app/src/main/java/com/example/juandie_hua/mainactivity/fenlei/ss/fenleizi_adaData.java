package com.example.juandie_hua.mainactivity.fenlei.ss;

public class fenleizi_adaData {
	String id, name;
	boolean selset;

	public fenleizi_adaData(String id, String name, boolean selset) {
		this.id = id;
		this.name = name;
		this.selset = selset;
	}

	public boolean isSelset() {
		return selset;
	}

	public void setSelset(boolean selset) {
		this.selset = selset;
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
