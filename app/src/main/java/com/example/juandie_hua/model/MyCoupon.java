package com.example.juandie_hua.model;

public class MyCoupon {

    /**
     * bonus_sn : 191018g346f
     * order_id : 0
     * start_time : 1571366176
     * end_time : 1602902176
     * type_name : 满1000减50
     * type_category_name : 新人券
     * type_money : 50.00
     * min_goods_amount : 1000.00
     * use_start_date : 1512028800
     * use_end_date : 1577779200
     * status : null
     * use_startdate : 2019-10-18
     * use_enddate : 2020-10-17
     * use_time_deadline : 使用期限：2019-10-18~2020-10-17
     */

    private String bonus_sn;
    private String order_id;
    private String start_time;
    private String end_time;
    private String type_name;
    private String type_category_name;
    private String type_money;
    private String min_goods_amount;
    private String use_start_date;
    private String use_end_date;
    private String status;
    private String use_startdate;
    private String use_enddate;
    private String use_time_deadline;



    public String getBonus_sn() {
        return bonus_sn;
    }

    public void setBonus_sn(String bonus_sn) {
        this.bonus_sn = bonus_sn;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getType_category_name() {
        return type_category_name;
    }

    public void setType_category_name(String type_category_name) {
        this.type_category_name = type_category_name;
    }

    public String getType_money() {
        return type_money;
    }

    public void setType_money(String type_money) {
        this.type_money = type_money;
    }

    public String getMin_goods_amount() {
        return min_goods_amount;
    }

    public void setMin_goods_amount(String min_goods_amount) {
        this.min_goods_amount = min_goods_amount;
    }

    public String getUse_start_date() {
        return use_start_date;
    }

    public void setUse_start_date(String use_start_date) {
        this.use_start_date = use_start_date;
    }

    public String getUse_end_date() {
        return use_end_date;
    }

    public void setUse_end_date(String use_end_date) {
        this.use_end_date = use_end_date;
    }

    public String getStatus() {
        return status == null ? "" : status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUse_startdate() {
        return use_startdate;
    }

    public void setUse_startdate(String use_startdate) {
        this.use_startdate = use_startdate;
    }

    public String getUse_enddate() {
        return use_enddate;
    }

    public void setUse_enddate(String use_enddate) {
        this.use_enddate = use_enddate;
    }

    public String getUse_time_deadline() {
        return use_time_deadline;
    }

    public void setUse_time_deadline(String use_time_deadline) {
        this.use_time_deadline = use_time_deadline;
    }
}
