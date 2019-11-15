package com.example.juandie_hua.percenter.qianbao;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.me.MyCouponActivity;
import com.example.juandie_hua.percenter.TimerTextView;

public class qianBao extends Activity {
	@ViewInject(R.id.qianbao_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.qianbao_imreturn)
	ImageView im_return;

	@ViewInject(R.id.qianbao_linyhq)
	LinearLayout lin_yhq;
	@ViewInject(R.id.qianbao_imyhq)
	ImageView im_yhq;
	@ViewInject(R.id.qianbao_teyhqset)
	TextView te_yhq;
	@ViewInject(R.id.qianbao_imyhqgo)
	ImageView im_yhqgo;

	@ViewInject(R.id.qianbao_linhbi)
	LinearLayout lin_hbi;
	@ViewInject(R.id.qianbao_imhbi)
	ImageView im_hbi;
	@ViewInject(R.id.qianbao_tehbiset)
	TextView te_hbi;
	@ViewInject(R.id.qianbao_imhbigo)
	ImageView im_hbigo;

	@ViewInject(R.id.qianbao_linjbi)
	LinearLayout lin_jbi;
	@ViewInject(R.id.qianbao_imjbi)
	ImageView im_jbi;
	@ViewInject(R.id.qianbao_tejbiset)
	TextView te_jbi;
	@ViewInject(R.id.qianbao_imjbigo)
	ImageView im_jbigo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qianbao);
//		if (Build.VERSION.SDK_INT >= 21) {
//			Window window = getWindow();
//			// 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
//			window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//			// 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//			window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//			// 设置状态栏颜色
//			window.setStatusBarColor(Color.parseColor("#f2f3f5"));
//		}
		x.view().inject(this);
//		xutils_getinfo();
		setviewhw();
		setviewlisten();
	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
			}
		});
		lin_yhq.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(qianBao.this, MyCouponActivity.class);
				i.putExtra("type", "0");// 1代表可以点击使用优惠券
				startActivity(i);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
		});
		lin_hbi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("type", "1");
				i.setClass(qianBao.this, myhbi.class);
				startActivity(i);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
		});
		lin_jbi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.putExtra("type", "2");
				i.setClass(qianBao.this, myhbi.class);
				startActivity(i);
				overridePendingTransition(R.anim.push_left_in,
						R.anim.push_left_out);
			}
		});
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);

		setviewhw_lin(lin_yhq, w, (int) (w * 50 / 375.0), 0, 0, 0, 0);
		setviewhw_lin(im_yhq, (int) (w * 30 / 375.0), (int) (w * 30 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 10 / 375.0), 0);
		setviewhw_lin(im_yhqgo, (int) (w * 7 / 375.0), (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 18 / 375.0),
				(int) (w * 14 / 375.0), 0);

		setviewhw_lin(lin_hbi, w, (int) (w * 50 / 375.0), 0, 0, 0, 0);
		setviewhw_lin(im_hbi, (int) (w * 30 / 375.0), (int) (w * 30 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 10 / 375.0), 0);
		setviewhw_lin(im_hbigo, (int) (w * 7 / 375.0), (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 18 / 375.0),
				(int) (w * 14 / 375.0), 0);

		setviewhw_lin(lin_jbi, w, (int) (w * 50 / 375.0), 0, 0, 0, 0);
		setviewhw_lin(im_jbi, (int) (w * 30 / 375.0), (int) (w * 30 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 10 / 375.0), 0);
		setviewhw_lin(im_jbigo, (int) (w * 7 / 375.0), (int) (w * 15 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 18 / 375.0),
				(int) (w * 14 / 375.0), 0);

	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
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
			TimerTextView.isc = false;
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		return false;
	}

	String sign = "";

	

}
