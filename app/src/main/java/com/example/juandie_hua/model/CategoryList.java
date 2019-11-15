package com.example.juandie_hua.model;

import java.util.List;

public class CategoryList {


    /**
     * cat_id : 172
     * cat_name : 水果花篮
     * filter_attr :
     * is_show : 1
     * all_attr_list : []
     * cat_icon : https://app.juandie.com/themes/5lux/assets/juandie/images/cat_172.png
     */

    private String cat_id;
    private String cat_name;
    private String filter_attr;
    private String is_show;
    private String cat_icon;
    private List<AttrList> all_attr_list;
    private boolean isCheck;

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getFilter_attr() {
        return filter_attr;
    }

    public void setFilter_attr(String filter_attr) {
        this.filter_attr = filter_attr;
    }

    public String getIs_show() {
        return is_show;
    }

    public void setIs_show(String is_show) {
        this.is_show = is_show;
    }

    public String getCat_icon() {
        return cat_icon;
    }

    public void setCat_icon(String cat_icon) {
        this.cat_icon = cat_icon;
    }

    public List<AttrList> getAll_attr_list() {
        return all_attr_list;
    }

    public void setAll_attr_list(List<AttrList> all_attr_list) {
        this.all_attr_list = all_attr_list;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public static class AttrList{

        /**
         * filter_attr_name : 分类
         * attr_list : [{"attr_value":"全部","filter_attr":"0","url":"/category-187.html","selected":1,"attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","order":"sales_count","by":"desc"},{"attr_value":"耳饰","filter_attr":"20530","goods_id":"20530","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20530.html","order":"sales_count","by":"desc","selected":0},{"attr_value":"戒指","filter_attr":"20604","goods_id":"20604","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20604.html","order":"sales_count","by":"desc","selected":0},{"attr_value":"项链","filter_attr":"20563","goods_id":"20563","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20563.html","order":"sales_count","by":"desc","selected":0},{"attr_value":"手链","filter_attr":"20552","goods_id":"20552","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20552.html","order":"sales_count","by":"desc","selected":0},{"attr_value":"公仔","filter_attr":"20785","goods_id":"20785","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20785.html","order":"sales_count","by":"desc","selected":0},{"attr_value":"创意礼品","filter_attr":"20919","goods_id":"20919","attr_image":"https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png","url":"/category-187-attr20919.html","order":"sales_count","by":"desc","selected":0}]
         */

        private String filter_attr_name;
        private List<AttrListBean> attr_list;

        public String getFilter_attr_name() {
            return filter_attr_name;
        }

        public void setFilter_attr_name(String filter_attr_name) {
            this.filter_attr_name = filter_attr_name;
        }

        public List<AttrListBean> getAttr_list() {
            return attr_list;
        }

        public void setAttr_list(List<AttrListBean> attr_list) {
            this.attr_list = attr_list;
        }

        public static class AttrListBean {
            /**
             * attr_value : 全部
             * filter_attr : 0
             * url : /category-187.html
             * selected : 1
             * attr_image : https://app.juandie.com/themes/5lux/assets/meilele/images/category/47.png
             * order : sales_count
             * by : desc
             * goods_id : 20530
             */

            private String attr_value;
            private String filter_attr;
            private String url;
            private String selected;
            private String attr_image;
            private String order;
            private String by;
            private String goods_id;

            public String getAttr_value() {
                return attr_value;
            }

            public void setAttr_value(String attr_value) {
                this.attr_value = attr_value;
            }

            public String getFilter_attr() {
                return filter_attr;
            }

            public void setFilter_attr(String filter_attr) {
                this.filter_attr = filter_attr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }

            public String getAttr_image() {
                return attr_image;
            }

            public void setAttr_image(String attr_image) {
                this.attr_image = attr_image;
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

            public String getGoods_id() {
                return goods_id;
            }

            public void setGoods_id(String goods_id) {
                this.goods_id = goods_id;
            }
        }
    }
}
