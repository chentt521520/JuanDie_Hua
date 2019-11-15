package com.example.juandie_hua.mainactivity.goods;

public class gdlist_adaData {
	String id, url, price, name, sale_number;

	public gdlist_adaData(String id, String url, String price, String name,
			String sale_number) {
		this.id = id;
		this.url = url;
		this.price = price;
		this.name = name;
		this.sale_number = sale_number;
	}

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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSale_number() {
		return sale_number;
	}

	public void setSale_number(String sale_number) {
		this.sale_number = sale_number;
	}
}
