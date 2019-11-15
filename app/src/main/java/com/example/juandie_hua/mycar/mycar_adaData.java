package com.example.juandie_hua.mycar;

public class mycar_adaData {
	String id, rec_id, url, title, price, num,jieri,guige;
	int minnum;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public mycar_adaData(String id, String rec_id, String url, String title,
			String price, String num,String jieri,int minnum,String guige) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.price = price;
		this.num = num;
		this.rec_id = rec_id;
		this.jieri=jieri;
		this.minnum=minnum;
		this.guige=guige;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public int getMinnum() {
		return minnum;
	}

	public void setMinnum(int minnum) {
		this.minnum = minnum;
	}

	public String getJieri() {
		return jieri;
	}

	public void setJieri(String jieri) {
		this.jieri = jieri;
	}

	public String getRec_id() {
		return rec_id;
	}

	public void setRec_id(String rec_id) {
		this.rec_id = rec_id;
	}

}
