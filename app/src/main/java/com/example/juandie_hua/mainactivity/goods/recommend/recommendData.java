/** 
 * @Author ldlin
 * @Time 2019-1-21 下午2:36:17 
 * @Description 
 */

package com.example.juandie_hua.mainactivity.goods.recommend;

import org.json.JSONArray;

/**
 * @Author ldlin
 * @Time 2019-1-21 下午2:36:17
 * @Description
 * 
 */

public class recommendData {
	String allPrice, favourablePreice, buyer;
	JSONArray jsaArray;

	public recommendData(String allPrice, String favourablePreice,
			String buyer, JSONArray jsaArray) {
		this.allPrice = allPrice;
		this.favourablePreice = favourablePreice;
		this.buyer = buyer;
		this.jsaArray = jsaArray;
	}

	public JSONArray getJsaArray() {
		return jsaArray;
	}

	public void setJsaArray(JSONArray jsaArray) {
		this.jsaArray = jsaArray;
	}

	public String getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(String allPrice) {
		this.allPrice = allPrice;
	}

	public String getFavourablePreice() {
		return favourablePreice;
	}

	public void setFavourablePreice(String favourablePreice) {
		this.favourablePreice = favourablePreice;
	}

	public String getBuyer() {
		return buyer;
	}

	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
}
