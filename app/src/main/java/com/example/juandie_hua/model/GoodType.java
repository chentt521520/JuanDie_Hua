package com.example.juandie_hua.model;

import java.util.List;

public class GoodType {

    private List<GoodAttr> attr_list;
    private String filter_attr_name;

    public List<GoodAttr> getAttr_list() {
        return attr_list;
    }

    public void setAttr_list(List<GoodAttr> attr_list) {
        this.attr_list = attr_list;
    }

    public String getFilter_attr_name() {
        return filter_attr_name;
    }

    public void setFilter_attr_name(String filter_attr_name) {
        this.filter_attr_name = filter_attr_name;
    }
}
