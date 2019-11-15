package com.example.juandie_hua.mainactivity.fenlei.ss;

public class gdlist_adaData {
    private String id, url, price, name, sale_number, marketPrice;

    public gdlist_adaData(String id, String url, String name, String price, String sale_number, String marketPrice) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.price = price;
        this.sale_number = sale_number;
        this.marketPrice = marketPrice;
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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }
}
