package com.example.juandie_hua.http;

import java.util.HashMap;
import java.util.Map;

public class HttpUrl {

    private static String baseUrl = "https://app.juandie.com/api_mobile";

    /**
     * 用户
     */
    public static String user = baseUrl + "/user.php?";
    /*登录时获取验证码*/
    public static String login_send_msg = "app_send_sms_login";
    public static String phone_login = "app_ajax_quick_login";
    public static String user_index = "index";
    public static String bonus = "bonus";


    /**
     * 首页
     */
    public static String index = baseUrl + "/index.php?";
    /*首页*/
    public static String index_page = "app_index20191016";
    //    public static String index_page = "app_index";
    /*检查APP版本*/
    public static String app_version = "app_version";
    /*领取优惠券*/
    public static String receive_bonus = "receive_bonus";

    /**
     * 分类
     */
    public static String category = baseUrl + "/category.php?";
    //    public static String category_page = "app_index";
    /*分类*/
    public static String category_page = "index20191016";
    public static String search_hot_words = "search_hot_words";

    /**
     * 商品
     */
    public static String good = baseUrl + "/goods.php?";
    public static String good_detail = "index";

    /**
     * 列表
     */
    public static String flow = baseUrl + "/flow.php?";
    /*获取购物车列表*/
    public static String shop_cart = "cart";
    /*修改购物车商品属性*/
    public static String update_shop_cart = "ajax_update_goods_attr_cart";
    /*单个商品加入购物车*/
    public static String add_shop_cart = "add_to_cart";
    /*组合商品加入购物车*/
    public static String add_group_shop_cart = "add_group_goods_to_cart";
    /*删除购物车商品*/
    public static String drop_goods = "drop_goods";
    /*获取商品配送时间*/
    public static String get_delivery_date_change = "get_delivery_date_change";
    public static String get_delivery_timer_range_change = "get_delivery_timer_range_change";

    /**
     * 地址
     */
    public static String address = baseUrl + "/address.php?";


    public static String getUrl(String baseUrl, HashMap<String, String> map) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            builder.append("&").append(entry.getKey()).append("=").append(entry.getValue());
        }
        return baseUrl.concat(builder.toString().substring(1));
    }

}
