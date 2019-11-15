/** 
 * @Author ldlin
 * @Time 2019-1-21 下午3:32:51 
 * @Description 
 */

package com.example.juandie_hua.mainactivity.goods.recommend;

/**
 * @Author ldlin
 * @Time 2019-1-21 下午3:32:51
 * @Description
 * 
 */

public class recommendDatatwo {
	String id,url, name, price;

	public recommendDatatwo(String id,String url, String name, String price) {
		this.url = url;
		this.name = name;
		this.price = price;
		this.id=id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
