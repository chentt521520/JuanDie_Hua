package com.example.juandie_hua.model;

import java.util.List;

public class GoodSpecs {

    /**
     * title : 尺寸
     * items : [{"label":"35CM","id":"21104"},{"label":"45CM","id":"21105"},{"label":"55Cm","id":"21106"}]
     */

    private String title;
    private List<ItemsBean> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class ItemsBean {
        /**
         * label : 35CM
         * id : 21104
         */

        private String label;
        private String id;
        private boolean isCheck;

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }
    }
}
