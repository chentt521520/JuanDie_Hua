package com.example.juandie_hua.mainactivity.adapter;

import android.view.View;

public interface OnGoodListCallback<T> {
    void setOnAddShopCallback(View view, T data);

    void setOnItemClickCallback(View view, T data);
}
