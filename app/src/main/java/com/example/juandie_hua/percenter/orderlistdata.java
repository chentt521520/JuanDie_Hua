/** 
 * @Author ldlin
 * @Time 2019-1-9 上午11:16:31 
 * @Description 
 */

package com.example.juandie_hua.percenter;

/**
 * @Author ldlin
 * @Time 2019-1-9 上午11:16:31
 * @Description 未登录支付成功订单javabean
 * 
 */

public class orderlistdata {
	String time, ordernumber;

	public orderlistdata(String time, String ordernumber) {
		this.time = time;
		this.ordernumber = ordernumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getOrdernumber() {
		return ordernumber;
	}

	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}

}
