package com.example.juandie_hua.mainactivity;

import java.util.HashMap;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.good.GoodDetailsAty;
import com.example.juandie_hua.utils.StatusBarUtils;

public class other_web extends BaseActivity {


    @ViewInject(R.id.ddanxq_web)
    WebView wb;

    @ViewInject(R.id.progressBar1)
    ProgressBar pbar;

    String url = "", titl = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleContentView(R.layout.other_web);
        StatusBarUtils.with(this).setBarColor(R.color.white_fff);

        x.view().inject(this);
        setviewdata();
        setviewweb();
        setviewlisten();
    }

    private void setviewdata() {
        Intent i = getIntent();
        titl = i.getStringExtra("titl");
        this.getTitleView().setTitleText(titl);
        url = i.getStringExtra("url");
    }

    private void setviewlisten() {
        this.getTitleView().setLeftOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wb.canGoBack()) {
                    wb.goBack();
                } else {
                    finish();
                    overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
                }
            }
        });
        wb.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                wb.setEnabled(false);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.contains("https://m.juandie.com/user")) {// 登录
                    MainActivity.handler.sendEmptyMessage(0x004);
                    finish();
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);

                    return true;
                } else if (url.contains("https://m.juandie.com/goods-")
                        || url.contains("https://m.juandie.com/goods/")) {// 商品url

                    String goods_id = url;
                    if (url.contains("https://m.juandie.com/goods-")) {
                        goods_id = goods_id.replace(
                                "https://m.juandie.com/goods-", "").replace(
                                ".html", "");
                    } else
                        goods_id = goods_id.replace(
                                "https://m.juandie.com/goods/", "").replace(
                                ".html", "");
                    Intent i = new Intent();
                    i.putExtra("goods_id", goods_id);
                    i.setClass(other_web.this, GoodDetailsAty.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);
                    return true;
                } else if (url.contains("https://m.juandie.com/cart")) {
                    MainActivity.handler.sendEmptyMessage(0x002);
                    finish();
                    overridePendingTransition(R.anim.push_left_in,
                            R.anim.push_left_out);

                    return true;
                } else {
                    wb.loadUrl(url);
                    return false;
                }
            }
        });
        wb.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                // TODO 自动生成的方法存根

                if (newProgress == 100) {
                    pbar.setVisibility(View.GONE);// 加载完网页进度条消失
                } else {
                    pbar.setVisibility(View.VISIBLE);// 开始加载网页时显示进度条
                    pbar.setProgress(newProgress);// 设置进度值
                }
            }
        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && wb.canGoBack()) {
            wb.goBack();
            return true;
        } else {
            finish();
            overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
            return false;
        }
    }

    private void setviewweb() {
        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings settings = wb.getSettings();
        settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        wb.getSettings().setDomStorageEnabled(true);
        wb.getSettings().setJavaScriptEnabled(true);
        wb.getSettings().setBlockNetworkImage(false);
        wb.setVerticalScrollBarEnabled(false);
        wb.setHorizontalScrollBarEnabled(false);
        wb.getSettings().setLoadWithOverviewMode(true);
        HashMap<String, String> map = new HashMap<>();
        map.put("cook", "PHPSESSID=864895027854338");
        wb.loadUrl(url);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        wb.removeAllViews();
        wb.destroy();
    }
}
