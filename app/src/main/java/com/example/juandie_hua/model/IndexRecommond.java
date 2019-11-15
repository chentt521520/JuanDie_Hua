package com.example.juandie_hua.model;

public class IndexRecommond {

    /**
     * promote_price :
     * i : 2
     * id : 1285
     * name :  恋恋情深-11支精品粉玫瑰
     * goods_sn : Jd001285
     * comment_rank : null
     * sell_number : null
     * goods_sale_number : 45710
     * seller_note :
     * is_new : null
     * is_shipping : 0
     * brief : null
     * brand_name : null
     * brand_id : null
     * brand_logo : null
     * short_name : 恋恋情深-11支精品粉玫瑰
     * market_price : ￥202
     * shop_price : 168
     * shop_price_ori : 168.00
     * market_price_ori : 201.60
     * sale_price : 34
     * discount : 8.3
     * thumb : images/201909/thumb_img/1569376918_74212.jpg
     * goods_img : images/201909/source_img/1569376918_66918.jpg
     * url : /goods-1285.html
     * promote_end_date : 2019-11-01 11:42:35
     * promote_end_date2 : null
     * short_style_name : 恋恋情深-11支精品粉玫瑰
     * type : goods
     * goods_id : 1285
     * goods_thumb : https://juandie.oss-cn-shanghai.aliyuncs.com/images/201909/thumb_img/1569376918_74212.jpg
     * goods_name :  恋恋情深
     * goods_desc : 11支精品粉玫瑰
     * category_name : null
     */

    /**
     * private String img;
     *         private String text;
     *         private String type;
     *         private String url;
     *         private String title;
     *         private String cid;
     *         private String filter_attr;
     *         private String order;
     *         private String by;
     */

    private String name;
    private String goods_sn;
    private String goods_sale_number;
    private String market_price;
    private String shop_price;
    private String type;
    private String goods_id;
    private String goods_thumb;
    private String goods_name;
    private String goods_desc;

    private String img;
    private String cid;
    private String filter_attr;
    private String by;
    private String order;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
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

    public String getShop_price() {
        return shop_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_desc() {
        return goods_desc;
    }

    public void setGoods_desc(String goods_desc) {
        this.goods_desc = goods_desc;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getFilter_attr() {
        return filter_attr;
    }

    public void setFilter_attr(String filter_attr) {
        this.filter_attr = filter_attr;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
