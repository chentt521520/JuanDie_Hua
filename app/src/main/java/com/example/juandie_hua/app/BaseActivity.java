package com.example.juandie_hua.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.view.CustomTitleView;

import org.xutils.common.util.DensityUtil;

public class BaseActivity extends FragmentActivity {

    private CustomTitleView titleView;
    public LinearLayout.LayoutParams layoutParamsFF = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        titleView = new CustomTitleView(this);
        layoutParamsFF = new LinearLayout.LayoutParams(-1, -1);
    }

    /**
     * 设置界面显示（含标题栏）
     */
    public void setTitleContentView(int resId) {
        this.setTitleContentView(LayoutInflater.from(this).inflate(resId, null));
    }

    /**
     * 设置界面显示（含标题栏）
     */
    public void setTitleContentView(View contentView) {
        setTitleView(contentView);
    }

    private void setTitleView(View contentView) {
        LinearLayout baseLayout = new LinearLayout(this);
        baseLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, DensityUtil.dip2px(45));
        baseLayout.addView(titleView, params);
        RelativeLayout contentLayout = new RelativeLayout(this);
        contentLayout.setPadding(0, 0, 0, 0);
        contentLayout.addView(contentView, layoutParamsFF);
        RelativeLayout.LayoutParams layoutParamsFW1 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParamsFW1.addRule(RelativeLayout.BELOW, titleView.getId());
        baseLayout.addView(contentLayout, layoutParamsFW1);
        this.setContentView(baseLayout);
    }

    public CustomTitleView getTitleView() {
        return this.titleView;
    }


    /**
     * 是否显示软键盘
     *
     * @param et   EditText
     * @param flag 是否弹出
     */
    public void showInput(EditText et, boolean flag) {
        InputMethodManager im = ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE));
        if (flag) {
            im.showSoftInput(et, 0);
        } else {
            //上下两种都可以
            im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//            im.hideSoftInputFromWindow(et.getWindowToken(), 0);
        }
    }

    public void toast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    public boolean isLogin() {
        return App.getInstance().isLogin();
    }

}
