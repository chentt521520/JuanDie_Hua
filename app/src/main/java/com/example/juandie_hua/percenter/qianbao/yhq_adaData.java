package com.example.juandie_hua.percenter.qianbao;

public class yhq_adaData {
	String type, price, bianhao, usetime, edu, leibie;

	public yhq_adaData(String type, String price, String bianhao,
			String usetime, String edu, String leibie) {
		super();
		this.type = type;
		this.price = price;
		this.bianhao = bianhao;
		this.usetime = usetime;
		this.edu = edu;
		this.leibie = leibie;
	}

	public String getLeibie() {
		return leibie;
	}

	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	public String getEdu() {
		return edu;
	}

	public void setEdu(String edu) {
		this.edu = edu;
	}

	public String getUsetime() {
		return usetime;
	}

	public void setUsetime(String usetime) {
		this.usetime = usetime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getBianhao() {
		return bianhao;
	}

	public void setBianhao(String bianhao) {
		this.bianhao = bianhao;
	}
}
