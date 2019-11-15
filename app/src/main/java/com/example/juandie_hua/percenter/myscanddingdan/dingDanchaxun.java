package com.example.juandie_hua.percenter.myscanddingdan;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class dingDanchaxun extends Activity {
	@ViewInject(R.id.ddcheck_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.ddcheck_imreturn)
	ImageView im_return;

	@ViewInject(R.id.ddcheck_tecontent)
	TextView te_content;

	@ViewInject(R.id.ddcheck_reddh)
	RelativeLayout re_ddh;
	@ViewInject(R.id.ddcheck_teddh)
	TextView te_dd;
	@ViewInject(R.id.ddcheck_edddh)
	EditText ed_ddh;

	@ViewInject(R.id.ddcheck_resjh)
	RelativeLayout re_sjh;
	@ViewInject(R.id.ddcheck_tesjh)
	TextView te_sj;
	@ViewInject(R.id.ddcheck_edsjh)
	EditText ed_sjh;

	@ViewInject(R.id.ddcheck_techeck)
	TextView te_check;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dingdanchaxun);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);
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
		te_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(ed_ddh.getText().toString())
						&& TextUtils.isEmpty(ed_sjh.getText().toString())) {
					Toast.makeText(dingDanchaxun.this, "请填写订单号或者手机号至少一样",
							Toast.LENGTH_SHORT).show();
				} else {
					Intent i = new Intent();
					i.putExtra("sjh", ed_sjh.getText().toString());
					i.putExtra("ddh", ed_ddh.getText().toString());
					i.setClass(dingDanchaxun.this, order_list.class);
					startActivity(i);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
				}
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


		te_content.setPadding((int) (w * 14 / 375.0), (int) (w * 21 / 375.0),
				(int) (w * 75 / 375.0), (int) (w * 15 / 375.0));
		setviewhw_lin(re_ddh, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
		re_ddh.setPadding((int) (w * 14 / 375.0), 0, (int) (w * 21 / 375.0), 0);
		setviewhw_lin(re_sjh, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
		re_sjh.setPadding((int) (w * 14 / 375.0), 0, (int) (w * 21 / 375.0), 0);
		setviewhw_lin(te_check, (int) (w * 345 / 375.0),
				(int) (w * 40 / 375.0), (int) (w * 14 / 375.0),
				(int) (w * 14 / 375.0), 0, (int) (w * 14 / 375.0));

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
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		return false;
	}
	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
}
