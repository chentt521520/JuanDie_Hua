package com.example.juandie_hua.model;

public class ScanHistory {

    /**
     * goods_id : 1285
     * goods_name : 恋恋情深-11支精品粉玫瑰
     * goods_thumb : https://juandie.oss-cn-shanghai.aliyuncs.com/images/201909/thumb_img/1569376918_74212.jpg
     * shop_price : 168.00
     * goods_sale_number : 45800
     * market_price : 218
     */

    private String goods_id;
    private String goods_name;
    private String goods_thumb;
    private String shop_price;
    private String goods_sale_number;
    private String market_price;
//    private int goods_sale_number;
//    private int market_price;

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

    public String getGoods_sale_number() {
        return goods_sale_number;
    }

    public void setGoods_sale_number(String goods_sale_number) {
        this.goods_sale_number = goods_sale_number;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }
}
