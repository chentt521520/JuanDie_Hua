package com.example.juandie_hua.mainactivity.goods;

import java.util.List;

public class sp_ggadadadata {
	String id, text;
	List<sp_ggadadadata1> list;

	public sp_ggadadadata(String id, String text, List<sp_ggadadadata1> list) {
		this.id = id;
		this.text = text;
		this.list = list;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<sp_ggadadadata1> getList() {
		return list;
	}

	public void setList(List<sp_ggadadadata1> list) {
		this.list = list;
	}

}
