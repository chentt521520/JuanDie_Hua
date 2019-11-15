package com.example.juandie_hua.percenter.myscanddingdan;

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

import com.example.juandie_hua.R;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.StatusBarUtils;

public class orderdtals_web extends Activity {
	@ViewInject(R.id.ddanxq_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.ddanxq_imreturn)
	ImageView im_return;
	@ViewInject(R.id.ddanxq_web)
	WebView wb;
	String order_id = "";

	@ViewInject(R.id.progressBar1)
	ProgressBar pbar;

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.orderdtals_web);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		setviewweb();
		setviewhw();
		setviewlisten();
	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				TimerTextView.isc = false;
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
				// TODO Auto-generated method stub
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				// view.loadUrl(url);
				return true;
			}
		});

	}

	private void setviewhw() {

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_re(re_top, w, (int) (w * 37 / 375.0), 0, 0, 0, 0);
		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);
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
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		return false;
	}

	private void setviewweb() {
		Intent i = getIntent();
		order_id = i.getStringExtra("id");
		wb.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
		WebSettings settings = wb.getSettings();
		settings.setLayoutAlgorithm(LayoutAlgorithm.SINGLE_COLUMN);
		if (Build.VERSION.SDK_INT >= 21) {
			settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
		}// 解决高版本图片显示问题
		wb.getSettings().setDomStorageEnabled(true);
		wb.getSettings().setJavaScriptEnabled(true);
		wb.getSettings().setBlockNetworkImage(false);
		wb.setVerticalScrollBarEnabled(false);
		wb.setHorizontalScrollBarEnabled(false);
		wb.getSettings().setLoadWithOverviewMode(true);
		HashMap<String, String> map = new HashMap<>();
		map.put("cook", "PHPSESSID=864895027854338");
		wb.loadUrl("https://mnosu.juandie.com/ordersearch.php?order_sn=" + order_id
				+ "&is_app=1");
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

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		wb.removeAllViews();
		wb.destroy();
	}
}
