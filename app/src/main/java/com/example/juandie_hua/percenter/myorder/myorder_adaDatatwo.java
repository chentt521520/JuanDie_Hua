package com.example.juandie_hua.percenter.myorder;

public class myorder_adaDatatwo {
	String goods_id, number, url, goodsName, price;

	public myorder_adaDatatwo(String goods_id, String number, String url,
			String goodsName, String price) {
		this.goods_id = goods_id;// goods_id表示订单状态
		this.number = number;
		this.url = url;
		this.goodsName = goodsName;
		this.price = price;
	}

	public String getgoods_id() {
		return goods_id;
	}

	public void setgoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public String getnumber() {
		return number;
	}

	public void setnumber(String number) {
		this.number = number;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
}
