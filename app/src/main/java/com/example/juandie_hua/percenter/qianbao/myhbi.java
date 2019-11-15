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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.juandie_hua.R;
import com.example.juandie_hua.percenter.TimerTextView;

public class myhbi extends Activity {
	@ViewInject(R.id.hbi_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.hbi_imreturn)
	ImageView im_return;
	@ViewInject(R.id.hbi_tetit)
	TextView te_title;

	@ViewInject(R.id.hbi_number)
	RelativeLayout re_number;
	@ViewInject(R.id.hbi_tehbi)
	TextView te_hbi;
	@ViewInject(R.id.hbi_tehbi1)
	TextView te_hbinumber;
	@ViewInject(R.id.hbi_tehbi2)
	TextView te_hbizi;

	@ViewInject(R.id.hbi_teshouzhi)
	TextView te_shouzhi;

	@ViewInject(R.id.view_hbino)
	LinearLayout layout_nogoods;
	@ViewInject(R.id.nogoods_imhead)
	ImageView im_no;
	@ViewInject(R.id.nogoods_tecon)
	TextView te_no;
	@ViewInject(R.id.nogoods_tego)
	TextView te_no1;

	@ViewInject(R.id.hbi_listv)
	ListView listv_shouzhi;

	String type = "";// 1表示花币2表示金币

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myhbi);
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
	}

	private void setviewhw() {
		Intent i = getIntent();
		type = i.getStringExtra("type");
		if (type.equals("2")) {
			te_title.setText("我的金币");
			te_hbi.setText("可用金币:");

		}

		te_no1.setVisibility(View.GONE);
		te_no.setText("暂无相关记录");

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);

		setviewhw_lin(re_number, w, (int) (w * 130 / 375.0), 0, 0, 0, 0);

		setviewhw_re(te_hbi, RelativeLayout.LayoutParams.WRAP_CONTENT,
				(int) (w * 35 / 375.0), (int) (w * 30 / 375.0), 0, 0, 0);
		setviewhw_re(te_hbinumber, RelativeLayout.LayoutParams.WRAP_CONTENT,
				(int) (w * 64 / 375.0), (int) (w * 30 / 375.0),
				(int) (w * 35 / 375.0), (int) (w * 5 / 375.0), 0);
		setviewhw_lin(te_shouzhi, w, (int) (w * 40 / 375.0), 0, 0, 0, 0);
		te_shouzhi.setPadding((int) (w * 14 / 375.0), 0, 0, 0);

		setviewhw_lin(layout_nogoods, w, (int) (w * 150 / 375.0), 0,
				(int) (w * 100 / 375.0), 0, 0);
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
}
