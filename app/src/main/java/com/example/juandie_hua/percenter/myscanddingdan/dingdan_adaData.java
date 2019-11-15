package com.example.juandie_hua.percenter.myscanddingdan;

import java.util.List;

import com.example.juandie_hua.percenter.myorder.myorder_adaDatatwo;

public class dingdan_adaData {
	String order_bh, order_status, order_price;
	List<myorder_adaDatatwo> data;
	String order_id;

	public dingdan_adaData(String order_bh, String order_status,
			String order_price, List<myorder_adaDatatwo> data, String order_id) {
		this.order_bh = order_bh;
		this.order_status = order_status;
		this.order_price = order_price;
		this.data = data;
		this.order_id = order_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public List<myorder_adaDatatwo> getData() {
		return data;
	}

	public void setData(List<myorder_adaDatatwo> data) {
		this.data = data;
	}

	public String getOrder_bh() {
		return order_bh;
	}

	public void setOrder_bh(String order_bh) {
		this.order_bh = order_bh;
	}

	public String getOrder_status() {
		return order_status;
	}

	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getOrder_price() {
		return order_price;
	}

	public void setOrder_price(String order_price) {
		this.order_price = order_price;
	}

}
