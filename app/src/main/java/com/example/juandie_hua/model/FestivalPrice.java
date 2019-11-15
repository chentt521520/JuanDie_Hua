package com.example.juandie_hua.model;

public class FestivalPrice {

    /**
     * festival_open : true
     * festival_price_now : {"is_festival_price":0,"title":"平时价","price":182,"market_price":236}
     * festival_price_old : {"is_festival_price":1,"title":"23~25日圣诞价","price":212,"market_price":275}
     */

    private boolean festival_open;
    private FestivalPriceBean festival_price_now;
    private FestivalPriceBean festival_price_old;

    public boolean isFestival_open() {
        return festival_open;
    }

    public void setFestival_open(boolean festival_open) {
        this.festival_open = festival_open;
    }

    public FestivalPriceBean getFestival_price_now() {
        return festival_price_now;
    }

    public void setFestival_price_now(FestivalPriceBean festival_price_now) {
        this.festival_price_now = festival_price_now;
    }

    public FestivalPriceBean getFestival_price_old() {
        return festival_price_old;
    }

    public void setFestival_price_old(FestivalPriceBean festival_price_old) {
        this.festival_price_old = festival_price_old;
    }

    public static class FestivalPriceBean {
        /**
         * is_festival_price : 0
         * title : 平时价
         * price : 182
         * market_price : 236
         */

        private String is_festival_price;
        private String title;
        private String price;
        private String market_price;

        public String getIs_festival_price() {
            return is_festival_price;
        }

        public void setIs_festival_price(String is_festival_price) {
            this.is_festival_price = is_festival_price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMarket_price() {
            return market_price;
        }

        public void setMarket_price(String market_price) {
            this.market_price = market_price;
        }
    }
}
