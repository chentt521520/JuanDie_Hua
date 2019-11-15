package com.example.juandie_hua.percenter.myscanddingdan;

public class mysc_adaData {
	String id, url, title, price, num, goods_id, type;

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

	public mysc_adaData(String id, String url, String title, String price,
			String num, String goods_id, String type) {
		this.id = id;
		this.url = url;
		this.title = title;
		this.price = price;
		this.num = num;
		this.goods_id = goods_id;
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

}
