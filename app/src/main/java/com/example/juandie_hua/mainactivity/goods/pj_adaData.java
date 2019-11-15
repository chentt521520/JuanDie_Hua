package com.example.juandie_hua.mainactivity.goods;

public class pj_adaData {
	String url_head, number_xin, name, Str_pj;
	String[] url_tp;

	public pj_adaData(String url_head, String number_xin, String name,
			String Str_pj, String[] url_tp) {
		this.url_head = url_head;
		this.number_xin = number_xin;
		this.name = name;
		this.Str_pj = Str_pj;
		this.url_tp = url_tp;
	}

	public String getUrl_head() {
		return url_head;
	}

	public void setUrl_head(String url_head) {
		this.url_head = url_head;
	}

	public String getNumber_xin() {
		return number_xin;
	}

	public void setNumber_xin(String number_xin) {
		this.number_xin = number_xin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStr_pj() {
		return Str_pj;
	}

	public void setStr_pj(String str_pj) {
		Str_pj = str_pj;
	}

	public String[] getUrl_tp() {
		return url_tp;
	}

	public void setUrl_tp(String[] url_tp) {
		this.url_tp = url_tp;
	}
}
