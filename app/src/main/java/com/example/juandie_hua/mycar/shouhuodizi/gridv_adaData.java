package com.example.juandie_hua.mycar.shouhuodizi;

public class gridv_adaData {
	String id, title;
	boolean isselect;

	public gridv_adaData(String id, String title, boolean isselect) {
		this.id = id;
		this.title = title;
		this.isselect = isselect;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isIsselect() {
		return isselect;
	}

	public void setIsselect(boolean isselect) {
		this.isselect = isselect;
	}
}
