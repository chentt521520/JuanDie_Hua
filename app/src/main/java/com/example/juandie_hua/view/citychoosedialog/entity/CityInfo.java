package com.example.juandie_hua.view.citychoosedialog.entity;

import java.io.Serializable;

//市
public class CityInfo implements Serializable {

    private boolean isCheck;
    /**
     * region_id : 31
     * region_name : 宁夏
     */

    private String region_id;
    private String region_name;

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }
}
