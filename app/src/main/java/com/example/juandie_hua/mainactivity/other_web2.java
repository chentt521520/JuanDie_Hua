package com.example.juandie_hua.mainactivity;

import java.util.HashMap;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mycar.Pay_succ1;
import com.example.juandie_hua.utils.StatusBarUtils;

public class other_web2 extends Activity {
	@ViewInject(R.id.ddanxq_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.ddanxq_imreturn)
	ImageView im_return;
	@ViewInject(R.id.ddanxq_imcha)
	ImageView im_cha;
	@ViewInject(R.id.ddanxq_web)
	WebView wb;

	@ViewInject(R.id.ddanxq_tetit)
	TextView te_tit;

	@ViewInject(R.id.progressBar1)
	ProgressBar pbar;

	String url = "", titl = "";
	String url_jd = "";

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.other_web1);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		im_cha.setVisibility(View.GONE);
		setviewdata();
		setviewweb();
		setviewhw();
		setviewlisten();
	}

	private void setviewdata() {
		Intent i = getIntent();
		titl = i.getStringExtra("titl");
		te_tit.setText(titl);
		url = i.getStringExtra("url");
	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (url_jd.contains("jdpay/login?")
						|| url_jd.contains("jdpay/payCashier?")) {
					finish();
					overridePendingTransition(R.anim.push_right_out,
							R.anim.push_right_in);
				}
				// else if (url_jd.contains("respond.php")) {
				// startActivity(new Intent(other_web2.this,
				// haha.class));
				// overridePendingTransition(R.anim.push_left_in,
				// R.anim.push_left_out);
				// haha.handler.sendEmptyMessage(0x01);
				// }

				// if (wb.canGoBack()) {
				// wb.goBack();
				// } else {
				// finish();
				// overridePendingTransition(R.anim.push_right_out,
				// R.anim.push_right_in);
				// }

			}
		});

		im_cha.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (url_jd.contains("https://h5pay.example.juandie_hua.com/jdpay/login?")) {

				}
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
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
				url_jd = url;

				if (url_jd.contains("respond.php")) {
					String ordid = url_jd
							.replace(
									"http://m.zhuiluan.com/respond.php?code=alipay&order_sn=",
									"");
					Intent i = new Intent();
					i.setClass(other_web2.this, Pay_succ1.class);
					i.putExtra("orderid", ordid);
					i.putExtra("zffs", "京东支付");
					startActivity(i);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
					return true;
				} else
					return false;

				// if (url.contains("https://m.juandie.com/goods-")
				// || url.contains("https://m.juandie.com/goods/")) {// 商品url
				//
				// String goods_id = url;
				// if (url.contains("https://m.juandie.com/goods-")) {
				// goods_id = goods_id.replace(
				// "https://m.juandie.com/goods-", "").replace(
				// ".html", "");
				// } else
				// goods_id = goods_id.replace(
				// "https://m.juandie.com/goods/", "").replace(
				// ".html", "");
				// Intent i = new Intent();
				// i.putExtra("goods_id", goods_id);
				// i.setClass(other_web2.this, goods.class);
				// startActivity(i);
				// overridePendingTransition(R.anim.push_left_in,
				// R.anim.push_left_out);
				// return true;
				// } else if (url.contains("https://m.juandie.com/cart")) {
				// haha.handler.sendEmptyMessage(0x002);
				// finish();
				// overridePendingTransition(R.anim.push_left_in,
				// R.anim.push_left_out);
				//
				// return true;
				// } else {
				// wb.loadUrl(url);
				// return false;
				// }
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

	private void setviewhw() {

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_re(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);
		setviewhw_re(im_cha, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				(int) (w * 49 / 450.0), (int) (w * 15 / 450.0), 0, 0);
		im_cha.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);
	}

	private void setviewhw_re(View v, int width, int height, int left, int top,
			int right, int bottom) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
				height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && wb.canGoBack()) {
			wb.goBack();
			return true;
		} else {
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		// TODO Auto-generated method stub
		// if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
		// {
		// finish();
		// overridePendingTransition(R.anim.push_right_out,
		// R.anim.push_right_in);
		// return false;
		// }
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
		// TODO Auto-generated method stub
		super.onDestroy();
		wb.removeAllViews();
		wb.destroy();
	}
}
