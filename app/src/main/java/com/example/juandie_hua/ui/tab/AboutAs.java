package com.example.juandie_hua.ui.tab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseFragment;
import com.example.juandie_hua.ui.good.GoodDetailsAty;
import com.example.juandie_hua.ui.MainActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.HashMap;

public class AboutAs extends BaseFragment {

    @ViewInject(R.id.back_page)
    ImageView im_return;
    @ViewInject(R.id.title_text)
    TextView te_tit;

    @ViewInject(R.id.ddanxq_web)
    WebView wb;

    @ViewInject(R.id.progressBar1)
    ProgressBar pbar;

    String url = "", titl = "";

    View view;

    /*
     * @see
     * android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater,
     * android.view.ViewGroup, android.os.Bundle)
     */
    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.tab_about_us, container, false);

            x.view().inject(this, view);
            im_return.setVisibility(View.GONE);
            setviewdata();
            setviewweb();
            setviewlisten();
        }
        return view;
    }

    private void setviewdata() {
        te_tit.setText("关于我们");
        url = "https://m.juandie.com/help-aboutus.html?is_app=1";
    }

    private void setviewlisten() {
        im_return.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (wb.canGoBack()) {
                    wb.goBack();
                } else {
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
                    i.setClass(getActivity(), GoodDetailsAty.class);
                    startActivity(i);
                    getActivity().overridePendingTransition(
                            R.anim.push_left_in, R.anim.push_left_out);
                    return true;
                } else if (url.contains("https://m.juandie.com/cart")) {
                    MainActivity.handler.sendEmptyMessage(0x002);

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
                if (newProgress == 100) {
                    pbar.setVisibility(View.GONE);// 加载完网页进度条消失
                } else {
                    pbar.setVisibility(View.VISIBLE);// 开始加载网页时显示进度条
                    pbar.setProgress(newProgress);// 设置进度值
                }
            }
        });
    }


    private void setviewweb() {
        wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        WebSettings settings = wb.getSettings();
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
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
