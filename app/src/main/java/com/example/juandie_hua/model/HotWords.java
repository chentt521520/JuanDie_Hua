package com.example.juandie_hua.model;

public class HotWords {

    /**
     * text : 表白鲜花
     * type : category
     * cid : 5
     * filter_attr : 4792.4793.5112.0.0.0
     * order : sort_order
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
