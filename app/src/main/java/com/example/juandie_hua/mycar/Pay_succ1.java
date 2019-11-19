package com.example.juandie_hua.mycar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
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

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.App;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.other_web1;
import com.example.juandie_hua.ui.login.LoginAty;
import com.example.juandie_hua.utils.StatusBarUtils;

public class Pay_succ1 extends Activity {
	@ViewInject(R.id.payres_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.payres_tetitl)
	TextView te_tit;
	@ViewInject(R.id.payres_tetitl1)
	TextView te_tit1;

	@ViewInject(R.id.payres_imv)
	ImageView im_suc;
	@ViewInject(R.id.payres_teres)
	TextView te_ts;

	@ViewInject(R.id.payres_linbh)
	LinearLayout lin_bh;
	@ViewInject(R.id.payres_tebh)
	TextView te_bh;
	@ViewInject(R.id.payres_tebh1)
	TextView te_bh1;

	@ViewInject(R.id.payres_linzffs)
	LinearLayout lin_zffs;
	@ViewInject(R.id.payres_tezffs)
	TextView te_zffs;
	@ViewInject(R.id.payres_tezffs1)
	TextView te_zffs1;

	@ViewInject(R.id.payres_linyhq)
	LinearLayout lin_yhq;
	@ViewInject(R.id.payres_teyhq)
	TextView te_yhq;
	@ViewInject(R.id.payres_teyhq1)
	TextView te_yhq1;

	@ViewInject(R.id.payres_but)
	LinearLayout lin_but;
	@ViewInject(R.id.payres_tere)
	TextView te_rebuy;
	String order_id = "";

	@ViewInject(R.id.payres_tedd)
	TextView te_srtx;

	SharedPreferences preferences;
	SharedPreferences.Editor editor;
	
	@ViewInject(R.id.payres_tejrts)
	TextView te_jrts;

	Landing landing;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_resultac);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(Pay_succ1.this);
		landing = new Landing(Pay_succ1.this, R.style.CustomDialog);
		setviewdata();
		setviewhw();
		setviewlisten();
	}

	private void setviewdata() {
		
		String jrts = preferences.getString("jrts", "0");
		if (jrts.equals("0")) {
			te_jrts.setVisibility(View.GONE);
		} else {
			te_jrts.setVisibility(View.VISIBLE);
			te_jrts.setText("*" + jrts);
		}
		
		Intent i = getIntent();
		order_id = i.getStringExtra("orderid");
		volley_getpayres(order_id);
		te_bh1.setText(order_id);
		te_zffs1.setText(i.getStringExtra("zffs"));

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String timestr = formatter.format(curDate);

		String orderstr = preferences.getString("perorderid", "0");// 存储支付成功订单号信息
		String ordertime = preferences.getString("perordertime", "0");// 存储支付成功订单号信息

		if (orderstr.equals("0")) {
			orderstr = "";
			ordertime = "";
			orderstr += order_id + ",";
			orderstr = orderstr.substring(0, orderstr.length() - 1);
			ordertime += timestr + ",";
			ordertime = ordertime.substring(0, ordertime.length() - 1);
		} else {
			orderstr += "," + order_id;
			ordertime += "," + timestr;
		}

		editor = preferences.edit();
		editor.putString("perorderid", orderstr);
		editor.putString("perordertime", ordertime);
		editor.commit();

	}

	private void setviewlisten() {
		te_tit1.setOnClickListener(onc);
		te_rebuy.setOnClickListener(onc);
		te_srtx.setOnClickListener(onc);
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_lin(im_suc, w, (int) (w * 280 / 750.0), 0,
				(int) (w * 80 / 750.0), 0, 0);
		setviewhw_lin(te_ts, w, (int) (w * 130 / 750.0), 0, 0, 0, 0);
		setviewhw_lin(lin_bh, w, (int) (w * 80 / 750.0),
				(int) (w * 140 / 750.0), 0, 0, 0);
		setviewhw_lin(lin_zffs, w, (int) (w * 60 / 750.0),
				(int) (w * 140 / 750.0), 0, 0, 0);
		setviewhw_lin(lin_yhq, w, (int) (w * 60 / 750.0),
				(int) (w * 140 / 750.0), 0, 0, 0);

		setviewhw_lin(lin_but, w, (int) (w * 80 / 750.0),
				(int) (w * 0 / 750.0), (int) (w * 42 / 750.0),
				(int) (w * 0 / 750.0), 0);
		setviewhw_lin(te_rebuy, (int) (w * 450 / 750.0),
				(int) (w * 80 / 750.0), (int) (w * 150 / 750.0), 0,
				(int) (w * 150 / 750.0), 0);
		setviewhw_lin(te_srtx, (int) (w * 450 / 750.0), (int) (w * 80 / 750.0),
				(int) (w * 150 / 750.0), (int) (w * 40 / 750.0),
				(int) (w * 150 / 750.0), 0);

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

			startActivity(new Intent(Pay_succ1.this, MainActivity.class));
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			MainActivity.handler.sendEmptyMessage(0x001);
			finish();
			return false;
		}
		return false;
	}

	String sign = "";

	/**
	 * 支付成功反馈服务器
	 */
	private void volley_getpayres(final String order_sn) {
		landing.show();
		String url = "https://app.juandie.com/api_mobile/msg.php?act=pay_success&order_sn="
				+ order_sn;

		Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

			@Override
			public void onResponse(String result) {
				landing.dismiss();
				try {
					JSONObject response = new JSONObject(result);
					if (response.getString("status").equals("1")) {
						// JSONObject jso = response.getJSONObject("data");
						// String yhq = jso.getString("bouns");
						// Toast.makeText(Pay_succ1.this, "支付成功，获得一张优惠券," + yhq,
						// Toast.LENGTH_SHORT).show();
						// te_yhq1.setText(yhq);

					} else {
						// String jsb = response.getString("msg");
						// Toast.makeText(Pay_succ1.this, jsb,
						// Toast.LENGTH_SHORT)
						// .show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void onFail(String result) {
				landing.dismiss();
				volley_getpayres(order_sn);
			}

			@Override
			public void onCancel(CancelledException cex) {
				landing.dismiss();
			}
		});
	}

	OnClickListener onc = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.payres_tetitl1:

				startActivity(new Intent(Pay_succ1.this, MainActivity.class));
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				MainActivity.handler.sendEmptyMessage(0x001);
				finish();
				break;
			case R.id.payres_tere:

				startActivity(new Intent(Pay_succ1.this, MainActivity.class));
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				MainActivity.handler.sendEmptyMessage(0x001);
				finish();
				break;
			case R.id.payres_tedd:
				if (preferences.getString("cook", "0").equals("0")) {
					startActivity(new Intent(Pay_succ1.this, LoginAty.class));
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
				} else {

					Intent i = new Intent();
					i.putExtra("titl", "生日/纪念日提醒");
					i.putExtra("url",
                            "https://m.juandie.com/user_holiday.html?is_app=1&uid=" + App.getInstance().getUid());
					i.setClass(Pay_succ1.this, other_web1.class);
					startActivity(i);
					overridePendingTransition(R.anim.push_left_in,
							R.anim.push_left_out);
				}

				break;

			default:
				break;
			}
		}
	};
}
