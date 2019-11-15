package com.example.juandie_hua.mycar.orderpay;

public class order_spadadata {
	String url, number, price, name,jieri,guige;

	public order_spadadata(String url, String number, String price, String name,String jieri,String guige) {
		this.url = url;
		this.number = number;
		this.price = price;
		this.name = name;
		this.jieri=jieri;
		this.guige=guige;
	}

	public String getGuige() {
		return guige;
	}

	public void setGuige(String guige) {
		this.guige = guige;
	}

	public String getJieri() {
		return jieri;
	}

	public void setJieri(String jieri) {
		this.jieri = jieri;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
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
}
