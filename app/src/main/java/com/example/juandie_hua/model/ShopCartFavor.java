package com.example.juandie_hua.model;

public class ShopCartFavor {

    /**
     * promote_price :
     * i : 2
     * id : 804
     * name : 心若向阳-3支精品向日葵
     * goods_sn : Jd000804
     * comment_rank : null
     * sell_number : null
     * goods_sale_number : 47217
     * seller_note :
     * is_new : null
     * is_shipping : 0
     * brief : null
     * brand_name : null
     * brand_id : null
     * brand_logo : null
     * short_name : 心若向阳-3支精品向日葵
     * market_price : ￥287
     * shop_price : ￥239
     * shop_price_ori : 239.00
     * market_price_ori : 286.80
     * sale_price : 48
     * discount : 8.3
     * thumb : https://juandie.oss-cn-shanghai.aliyuncs.com/images/201907/thumb_img/1564393497_81886.jpg
     * goods_img : images/201905/source_img/1557213961_67768.jpg
     * url : /goods-804.html
     * promote_end_date : 2019-10-29 09:55:38
     * promote_end_date2 : null
     * short_style_name : 心若向阳-3支精品向日葵
     */

//    private String promote_price;
//    private int i;
    private String id;
    private String name;
//    private String goods_sn;
//    private Object comment_rank;
//    private Object sell_number;
//    private String goods_sale_number;
//    private String seller_note;
//    private Object is_new;
//    private String is_shipping;
//    private Object brief;
//    private Object brand_name;
//    private Object brand_id;
//    private Object brand_logo;
//    private String short_name;
    private String market_price;
    private String shop_price;
//    private String shop_price_ori;
//    private String market_price_ori;
//    private int sale_price;
//    private double discount;
    private String thumb;
//    private String goods_img;
//    private String url;
//    private String promote_end_date;
//    private Object promote_end_date2;
//    private String short_style_name;
//"https://juandie.oss-cn-shanghai.aliyuncs.com/images/201907/thumb_img/1564472149_63258.jpg",
private String goods_thumb;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }
}
