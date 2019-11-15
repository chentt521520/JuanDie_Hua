package com.example.juandie_hua.mainactivity.goods;

public class sphb_data {
	String price, context, name, string;

	public sphb_data(String price, String context, String name, String string) {
		this.price = price;
		this.context = context;
		this.name = name;
		this.string = string;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getString() {
		return string;
	}

	public void setString(String string) {
		this.string = string;
	}
}
