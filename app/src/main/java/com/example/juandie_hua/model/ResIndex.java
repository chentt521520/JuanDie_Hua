package com.example.juandie_hua.model;

import java.util.List;

public class ResIndex {


    /**
     * banner_list:{"img":"https://m.juandie.com/themes/5lux/assets/meilele/images/topic/tiancheng19_app_banner.jpg","type":"view","url":"https://m.juandie.com/topic-926.html?is_app=1","title":"天秤座鲜花"}
     * bonus : {"is_show_bonus":true,"amount":"￥150","title":"新人专享大礼包","subtitle":"娟蝶鲜花给你发了一个红包","field":"is_receive_150_bonus"}
     * search_keys : ["红玫瑰","花篮","蓝色妖姬"]
     * count_down : {"is_show":false,"title":"距 离 七 夕 结 束","timestamp":-36842651}
     * config : {"service_tel":"400-608-0960","session_id":"fe8ab42c6cf863b5e9d5167494e87e95","server_timestamp":1571364250}
     * nav_top: [{"text":"送女友","type":"category","cid":"5","filter_attr":"0.4793.0.0.0.0","order":"goods_id","by":"desc"}]
     *
     */

    private List<BannerBean> banner_list;
    private BonusBean bonus;
    private CountDownBean count_down;
    private List<String> search_keys;
    private ConfigBean config;
    private List<NavTopBean> nav_top;
    private List<MenuListBean> menu_list;
    private List<IndexRecommond> new_goods_list;
    private List<IndexRecommond> hot_goods_list;
    private List<IndexRecommond> best_goods_list;
    private List<IndexRecommond> lifeflower_goods_list;
    private List<IndexRecommond> baseket_goods_list;
    private List<IndexRecommond> plants_goods_list;
    private List<IndexRecommond> gift_goods_list;


    public List<BannerBean> getBanner_list() {
        return banner_list;
    }

    public void setBanner_list(List<BannerBean> banner_list) {
        this.banner_list = banner_list;
    }

    public ConfigBean getConfig() {
        return config;
    }

    public void setConfig(ConfigBean config) {
        this.config = config;
    }

    public BonusBean getBonus() {
        return bonus;
    }

    public void setBonus(BonusBean bonus) {
        this.bonus = bonus;
    }

    public CountDownBean getCount_down() {
        return count_down;
    }

    public void setCount_down(CountDownBean count_down) {
        this.count_down = count_down;
    }

    public List<String> getSearch_keys() {
        return search_keys;
    }

    public void setSearch_keys(List<String> search_keys) {
        this.search_keys = search_keys;
    }

    public List<NavTopBean> getNav_top() {
        return nav_top;
    }

    public void setNav_top(List<NavTopBean> nav_top) {
        this.nav_top = nav_top;
    }

    public List<MenuListBean> getMenu_list() {
        return menu_list;
    }

    public void setMenu_list(List<MenuListBean> menu_list) {
        this.menu_list = menu_list;
    }

    public List<IndexRecommond> getNew_goods_list() {
        return new_goods_list;
    }

    public void setNew_goods_list(List<IndexRecommond> new_goods_list) {
        this.new_goods_list = new_goods_list;
    }

    public List<IndexRecommond> getHot_goods_list() {
        return hot_goods_list;
    }

    public void setHot_goods_list(List<IndexRecommond> hot_goods_list) {
        this.hot_goods_list = hot_goods_list;
    }

    public List<IndexRecommond> getBest_goods_list() {
        return best_goods_list;
    }

    public void setBest_goods_list(List<IndexRecommond> best_goods_list) {
        this.best_goods_list = best_goods_list;
    }

    public List<IndexRecommond> getLifeflower_goods_list() {
        return lifeflower_goods_list;
    }

    public void setLifeflower_goods_list(List<IndexRecommond> lifeflower_goods_list) {
        this.lifeflower_goods_list = lifeflower_goods_list;
    }

    public List<IndexRecommond> getBaseket_goods_list() {
        return baseket_goods_list;
    }

    public void setBaseket_goods_list(List<IndexRecommond> baseket_goods_list) {
        this.baseket_goods_list = baseket_goods_list;
    }

    public List<IndexRecommond> getPlants_goods_list() {
        return plants_goods_list;
    }

    public void setPlants_goods_list(List<IndexRecommond> plants_goods_list) {
        this.plants_goods_list = plants_goods_list;
    }

    public List<IndexRecommond> getGift_goods_list() {
        return gift_goods_list;
    }

    public void setGift_goods_list(List<IndexRecommond> gift_goods_list) {
        this.gift_goods_list = gift_goods_list;
    }

    public static class ConfigBean {
        /**
         * service_tel : 400-608-0960
         * session_id : fe8ab42c6cf863b5e9d5167494e87e95
         * server_timestamp : 1571364250
         */

        private String service_tel;
        private String session_id;
        private int server_timestamp;

        public String getService_tel() {
            return service_tel;
        }

        public void setService_tel(String service_tel) {
            this.service_tel = service_tel;
        }

        public String getSession_id() {
            return session_id;
        }

        public void setSession_id(String session_id) {
            this.session_id = session_id;
        }

        public int getServer_timestamp() {
            return server_timestamp;
        }

        public void setServer_timestamp(int server_timestamp) {
            this.server_timestamp = server_timestamp;
        }
    }

    public static class BonusBean {
        /**
         * is_show_bonus : true
         * amount : ￥150
         * title : 新人专享大礼包
         * subtitle : 娟蝶鲜花给你发了一个红包
         * field : is_receive_150_bonus
         */

        private boolean is_show_bonus;
        private String amount;
        private String title;
        private String subtitle;
        private String field;

        public boolean isIs_show_bonus() {
            return is_show_bonus;
        }

        public void setIs_show_bonus(boolean is_show_bonus) {
            this.is_show_bonus = is_show_bonus;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    public static class CountDownBean {
        /**
         * is_show : false
         * title : 距 离 七 夕 结 束
         * timestamp : -36842651
         */

        private boolean is_show;
        private String title;
        private int timestamp;

        public boolean isIs_show() {
            return is_show;
        }

        public void setIs_show(boolean is_show) {
            this.is_show = is_show;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(int timestamp) {
            this.timestamp = timestamp;
        }
    }

    public static class NavTopBean {
        /**
         * text : 送女友
         * type : category
         * cid : 5
         * filter_attr : 0.4793.0.0.0.0
         * order : goods_id
         * by : desc
         */

        private String text;
        private String type;
        private String cid;
        private String filter_attr;
        private String order;
        private String by;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
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

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }
    }

    public static class MenuListBean {
        /**
         * img : https://m.juandie.com/themes/5lux/assets/meilele/images/app/nav_aiqing.png
         * text : 爱情鲜花
         * type : view
         * url : https://m.juandie.com/topic-114.html?is_app=1
         * title : 爱情鲜花
         * cid : 5
         * filter_attr : 4756.0.0.0.0.0
         * order :
         * by :
         */

        private String img;
        private String text;
        private String type;
        private String url;
        private String title;
        private String cid;
        private String filter_attr;
        private String order;
        private String by;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public String getOrder() {
            return order;
        }

        public void setOrder(String order) {
            this.order = order;
        }

        public String getBy() {
            return by;
        }

        public void setBy(String by) {
            this.by = by;
        }
    }

    /**
     * "img":"https://m.juandie.com/themes/5lux/assets/meilele/images/topic/tiancheng19_app_banner.jpg",
     *                 "type":"view",
     *                 "url":"https://m.juandie.com/topic-926.html?is_app=1",
     *                 "title":"天秤座鲜花"
     */
    public static class BannerBean {
        private String img;
        private String type;
        private String url;
        private String title;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
