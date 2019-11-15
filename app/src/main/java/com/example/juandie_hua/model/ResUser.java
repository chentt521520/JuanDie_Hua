package com.example.juandie_hua.model;

public class ResUser {

    /**
     * count0 : 0
     * count1 : 0
     * count2 : 0
     * user_info : {"user_name":"156***4017","user_type":"5","is_binding_account":"0","weixin_openid":"","third_login_user_id":"","is_app_wechat_user":"0","is_wechat_binding_account":"0","is_third_user":"0","mobile_phone":"15667074017"}
     * user_rank_info : {"total_order_amount":0,"c":"0","rank_type":"register","rank_text":"注册会员","rank_discount":"无特权","rank_discount_number":1}
     * bonus_not_used_count : 6
     * holiday_count : 0
     */

    private String count0;
    private String count1;
    private String count2;
    private UserInfo user_info;
    private UserRankInfoBean user_rank_info;
    private String bonus_not_used_count;
    private String holiday_count;

    public String getCount0() {
        return count0;
    }

    public void setCount0(String count0) {
        this.count0 = count0;
    }

    public String getCount1() {
        return count1;
    }

    public void setCount1(String count1) {
        this.count1 = count1;
    }

    public String getCount2() {
        return count2;
    }

    public void setCount2(String count2) {
        this.count2 = count2;
    }

    public UserInfo getUser_info() {
        return user_info;
    }

    public void setUser_info(UserInfo user_info) {
        this.user_info = user_info;
    }

    public UserRankInfoBean getUser_rank_info() {
        return user_rank_info;
    }

    public void setUser_rank_info(UserRankInfoBean user_rank_info) {
        this.user_rank_info = user_rank_info;
    }

    public String getBonus_not_used_count() {
        return bonus_not_used_count;
    }

    public void setBonus_not_used_count(String bonus_not_used_count) {
        this.bonus_not_used_count = bonus_not_used_count;
    }

    public String getHoliday_count() {
        return holiday_count;
    }

    public void setHoliday_count(String holiday_count) {
        this.holiday_count = holiday_count;
    }

    public static class UserInfo {
        /**
         * user_name : 156***4017
         * user_type : 5
         * is_binding_account : 0
         * weixin_openid :
         * third_login_user_id :
         * is_app_wechat_user : 0
         * is_wechat_binding_account : 0
         * is_third_user : 0
         * mobile_phone : 15667074017
         */

        private String user_name;
        private String user_type;
        private String is_binding_account;
        private String weixin_openid;
        private String third_login_user_id;
        private String is_app_wechat_user;
        private String is_wechat_binding_account;
        private String is_third_user;
        private String mobile_phone;

        public String getUser_name() {
            return user_name;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getIs_binding_account() {
            return is_binding_account;
        }

        public void setIs_binding_account(String is_binding_account) {
            this.is_binding_account = is_binding_account;
        }

        public String getWeixin_openid() {
            return weixin_openid;
        }

        public void setWeixin_openid(String weixin_openid) {
            this.weixin_openid = weixin_openid;
        }

        public String getThird_login_user_id() {
            return third_login_user_id;
        }

        public void setThird_login_user_id(String third_login_user_id) {
            this.third_login_user_id = third_login_user_id;
        }

        public String getIs_app_wechat_user() {
            return is_app_wechat_user;
        }

        public void setIs_app_wechat_user(String is_app_wechat_user) {
            this.is_app_wechat_user = is_app_wechat_user;
        }

        public String getIs_wechat_binding_account() {
            return is_wechat_binding_account;
        }

        public void setIs_wechat_binding_account(String is_wechat_binding_account) {
            this.is_wechat_binding_account = is_wechat_binding_account;
        }

        public String getIs_third_user() {
            return is_third_user;
        }

        public void setIs_third_user(String is_third_user) {
            this.is_third_user = is_third_user;
        }

        public String getMobile_phone() {
            return mobile_phone;
        }

        public void setMobile_phone(String mobile_phone) {
            this.mobile_phone = mobile_phone;
        }
    }

    public static class UserRankInfoBean {
        /**
         * total_order_amount : 0
         * c : 0
         * rank_type : register
         * rank_text : 注册会员
         * rank_discount : 无特权
         * rank_discount_number : 1
         */

        private int total_order_amount;
        private String c;
        private String rank_type;
        private String rank_text;
        private String rank_discount;
        private int rank_discount_number;

        public int getTotal_order_amount() {
            return total_order_amount;
        }

        public void setTotal_order_amount(int total_order_amount) {
            this.total_order_amount = total_order_amount;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }

        public String getRank_type() {
            return rank_type;
        }

        public void setRank_type(String rank_type) {
            this.rank_type = rank_type;
        }

        public String getRank_text() {
            return rank_text;
        }

        public void setRank_text(String rank_text) {
            this.rank_text = rank_text;
        }

        public String getRank_discount() {
            return rank_discount;
        }

        public void setRank_discount(String rank_discount) {
            this.rank_discount = rank_discount;
        }

        public int getRank_discount_number() {
            return rank_discount_number;
        }

        public void setRank_discount_number(int rank_discount_number) {
            this.rank_discount_number = rank_discount_number;
        }
    }
}
