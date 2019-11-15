package com.example.juandie_hua.model;

import java.util.List;

public class SendDate {

    private List<DateValue> delivery_timer_range;
    private List<DateValue> delivery_timing_hour;
    private List<DateValue> delivery_timing_minute;

    public List<DateValue> getDelivery_timer_range() {
        return delivery_timer_range;
    }

    public void setDelivery_timer_range(List<DateValue> delivery_timer_range) {
        this.delivery_timer_range = delivery_timer_range;
    }

    public List<DateValue> getDelivery_timing_hour() {
        return delivery_timing_hour;
    }

    public void setDelivery_timing_hour(List<DateValue> delivery_timing_hour) {
        this.delivery_timing_hour = delivery_timing_hour;
    }

    public List<DateValue> getDelivery_timing_minute() {
        return delivery_timing_minute;
    }

    public void setDelivery_timing_minute(List<DateValue> delivery_timing_minute) {
        this.delivery_timing_minute = delivery_timing_minute;
    }

    public static class DateValue {
        /**
         * key : 16
         * value : 16-18点
         */

        private String key;
        private String value;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
