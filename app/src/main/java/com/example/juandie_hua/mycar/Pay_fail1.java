package com.example.juandie_hua.mycar;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.utils.StatusBarUtils;

public class Pay_fail1 extends Activity {
	@ViewInject(R.id.payfail_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.payfail_tetitl)
	TextView te_tit;
	@ViewInject(R.id.payfail_tetitl1)
	TextView te_tit1;

	@ViewInject(R.id.payfail_imv)
	ImageView im_suc;
	@ViewInject(R.id.payfail_teres)
	TextView te_ts;

	@ViewInject(R.id.payfail_tets)
	TextView te_ts1;
	@ViewInject(R.id.payfail_tets1)
	TextView te_ts2;

	@ViewInject(R.id.payfail_but)
	LinearLayout lin_but;
	@ViewInject(R.id.payfail_tere)
	TextView te_rebuy;
	@ViewInject(R.id.payfail_tedd)
	TextView te_checkdd;
	String order_id = "";

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_fail1);
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
		preferences = PreferenceManager
				.getDefaultSharedPreferences(Pay_fail1.this);
		setviewdata();
		setviewhw();
		setviewlisten();
	}

	private void setviewdata() {
		Intent i = getIntent();
		order_id = i.getStringExtra("orderid");

	}

	private void setviewlisten() {
		te_tit1.setOnClickListener(onc);
		te_rebuy.setOnClickListener(onc);
		te_checkdd.setOnClickListener(onc);
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_lin(im_suc, w, (int) (w * 280 / 750.0), 0,
				(int) (w * 80 / 750.0), 0, 0);
		setviewhw_lin(te_ts, w, (int) (w * 130 / 750.0), 0, 0, 0, 0);

		setviewhw_lin(te_ts1, w, (int) (w * 80 / 750.0),
				(int) (w * 140 / 750.0), (int) (w * 10 / 750.0), 0, 0);
		setviewhw_lin(te_ts2, w, (int) (w * 40 / 750.0),
				(int) (w * 140 / 750.0), 0, 0, 0);

		setviewhw_lin(lin_but, w, (int) (w * 80 / 750.0),
				(int) (w * 0 / 750.0), (int) (w * 42 / 750.0),
				(int) (w * 0 / 750.0), 0);
		setviewhw_lin(te_rebuy, (int) (w * 450 / 750.0),
				(int) (w * 80 / 750.0), (int) (w * 150 / 750.0), 0,
				(int) (w * 150 / 750.0), 0);
		setviewhw_lin(te_checkdd, (int) (w * 0 / 750.0),
				(int) (w * 60 / 750.0), 0, 0, 0, 0);

	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			finish();
			startActivity(new Intent(Pay_fail1.this, MainActivity.class));
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			MainActivity.handler.sendEmptyMessage(0x001);
			return false;
		}
		return false;
	}

	OnClickListener onc = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.payfail_tetitl1:
				finish();
				startActivity(new Intent(Pay_fail1.this, MainActivity.class));
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				MainActivity.handler.sendEmptyMessage(0x001);
				break;
			case R.id.payfail_tere:
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				break;
			case R.id.payfail_tedd:
				editor = preferences.edit();
				editor.putString("zfddh", order_id);
				Toast.makeText(Pay_fail1.this, order_id, Toast.LENGTH_SHORT)
						.show();
				editor.commit();
				startActivity(new Intent(Pay_fail1.this, MainActivity.class));
				MainActivity.handler.sendEmptyMessage(0x004);
				Me.handler.sendEmptyMessage(0x007);
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				break;

			default:
				break;
			}
		}
	};
}
