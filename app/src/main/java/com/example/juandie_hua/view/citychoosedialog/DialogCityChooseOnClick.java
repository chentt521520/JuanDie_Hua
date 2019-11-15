package com.example.juandie_hua.view.citychoosedialog;

//三级联动选择最终传值
public interface DialogCityChooseOnClick {
    void complete(String Ids,String cities);

    void onTabSelect(int pos, String provinceId, String cityId);

    void onItemSelect(int pos, String provinceId, String cityId);
}
