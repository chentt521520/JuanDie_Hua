package com.example.juandie_hua.percenter.myorder;

import java.util.List;

public class myorder_adaData {
	String type, order_id, bhao,position;//position表示哪里来的数据0,1,2,3
	List<myorder_adaDatatwo> list;

	public myorder_adaData(String type, String order_id, String bhao,
			List<myorder_adaDatatwo> list,String position) {
		this.type = type;// type表示订单状态
		this.order_id = order_id;
		this.bhao = bhao;
		this.list = list;
		this.position=position;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getorder_id() {
		return order_id;
	}

	public List<myorder_adaDatatwo> getList() {
		return list;
	}

	public void setList(List<myorder_adaDatatwo> list) {
		this.list = list;
	}

	public void setorder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getBhao() {
		return bhao;
	}

	public void setBhao(String bhao) {
		this.bhao = bhao;
	}

}
