package com.example.juandie_hua.mainactivity;

public class syeada_gridlistData {
	String goods_id, url, goods_name, text, price, pricesc;

	public syeada_gridlistData(String goods_id, String url, String goods_name,
			String text, String price, String pricesc) {
		this.goods_id = goods_id;
		this.url = url;
		this.goods_name = goods_name;
		this.text = text;
		this.price = price;
		this.pricesc = pricesc;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGoods_name() {
		return goods_name;
	}

	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPricesc() {
		return pricesc;
	}

	public void setPricesc(String pricesc) {
		this.pricesc = pricesc;
	}
}
