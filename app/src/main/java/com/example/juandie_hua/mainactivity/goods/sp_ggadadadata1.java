package com.example.juandie_hua.mainactivity.goods;

public class sp_ggadadadata1 {

	boolean t_sele;
	String id, text;

	public sp_ggadadadata1(boolean t_sele, String id, String text) {
		this.t_sele = t_sele;
		this.id = id;
		this.text = text;
	}

	public boolean isT_sele() {
		return t_sele;
	}

	public void setT_sele(boolean t_sele) {
		this.t_sele = t_sele;
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

}
