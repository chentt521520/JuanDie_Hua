package com.example.juandie_hua.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.tab.ShopCart;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.helper.UiHelper;
import com.example.juandie_hua.mainactivity.adapter.FragmentAdapter;
import com.example.juandie_hua.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

//购物车activity界面
public class ShopCatActivity extends BaseActivity {

    ViewPager viewPager;
    private List<Fragment> listCarousel = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_cart);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        viewPager = findViewById(R.id.shopcatactivity_viewpage);

        listCarousel.add(new ShopCart(UiHelper.fromGoodDetail));
        FragmentAdapter carouselAdapter = new FragmentAdapter(getSupportFragmentManager(), listCarousel);
        viewPager.setAdapter(carouselAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        viewPager.notifyAll();
    }

}
