package com.example.juandie_hua.model;

import java.util.List;

public class Coupon {

    /**
     * is_show_bonus : true
     * bonus_list : [{"type_name":"10元无门槛","type_money":"10.00","type_desc":"新人福利"},{"type_name":"满200减20","type_money":"20.00","type_desc":"新人福利"},{"type_name":"满300减30","type_money":"30.00","type_desc":"新人福利"},{"type_name":"满600减40","type_money":"40.00","type_desc":"新人福利"},{"type_name":"满1000减50","type_money":"50.00","type_desc":"新人福利"}]
     * field : is_receive_150_bonus
     */

    private boolean is_show_bonus;
    private String field;
    private List<BonusListBean> bonus_list;

    public boolean isIs_show_bonus() {
        return is_show_bonus;
    }

    public void setIs_show_bonus(boolean is_show_bonus) {
        this.is_show_bonus = is_show_bonus;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public List<BonusListBean> getBonus_list() {
        return bonus_list;
    }

    public void setBonus_list(List<BonusListBean> bonus_list) {
        this.bonus_list = bonus_list;
    }

    public static class BonusListBean {
        /**
         * type_name : 10元无门槛
         * type_money : 10.00
         * type_desc : 新人福利
         */

        private String type_name;
        private String type_money;
        private String type_desc;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public String getType_money() {
            return type_money;
        }

        public void setType_money(String type_money) {
            this.type_money = type_money;
        }

        public String getType_desc() {
            return type_desc;
        }

        public void setType_desc(String type_desc) {
            this.type_desc = type_desc;
        }
    }
}
