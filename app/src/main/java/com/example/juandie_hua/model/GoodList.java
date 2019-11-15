package com.example.juandie_hua.model;

public class GoodList {


    /**
     * goods_id : 1247
     * goods_name : 方形奶油鲜奶蛋糕
     * goods_thumb : https://juandie.oss-cn-shanghai.aliyuncs.com/images/201907/thumb_img/1564365746_48412.jpg
     * shop_price : 299.00
     * virtual_sales : 19525
     * goods_sale_number : 19525
     * market_price : 388
     */

    private String goods_id;
    private String goods_name;
    private String goods_thumb;
    private String shop_price;
    private String virtual_sales;
    private String goods_sale_number;
    private Object market_price;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getVirtual_sales() {
        return virtual_sales;
    }

    public void setVirtual_sales(String virtual_sales) {
        this.virtual_sales = virtual_sales;
    }

    public String getGoods_sale_number() {
        return goods_sale_number;
    }

    public void setGoods_sale_number(String goods_sale_number) {
        this.goods_sale_number = goods_sale_number;
    }

    public Object getMarket_price() {
        return market_price;
    }

    public void setMarket_price(Object market_price) {
        this.market_price = market_price;
    }
}
