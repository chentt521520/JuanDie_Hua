package com.example.juandie_hua.mycar;

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
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;

public class Pay_succ extends Activity {
	@ViewInject(R.id.paysuc_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.paysuc_imreturn)
	ImageView im_return;

	@ViewInject(R.id.paysuc_tetitl)
	TextView te_tit;

	@ViewInject(R.id.paysuc_imv)
	ImageView im_suc;

	@ViewInject(R.id.paysuc_tets)
	TextView te_ti1;
	@ViewInject(R.id.paysuc_tets1)
	TextView te_ti2;
	SharedPreferences preferences;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_succ);
		// if (Build.VERSION.SDK_INT >= 21) {
		// Window window = getWindow();
		// // 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
		// window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
		// window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		// // 设置状态栏颜色
		// window.setStatusBarColor(Color.parseColor("#f2f3f5"));
		// }
		x.view().inject(this);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(Pay_succ.this);
		setviewdata();
		setviewhw();
		setviewlisten();
	}

	private void setviewdata() {
		Intent i = getIntent();
		String type = i.getStringExtra("type");
		if (type.equals("1")) {
			te_ti1.setText("感谢您在本店购物！支付操作已成功！\n请记住您的订单号为："
					+ i.getStringExtra("orderid"));
			volley_getpaysuc(i.getStringExtra("orderid"));
		} else {
			// 支付失败的没有写
		}

	}

	private void setviewlisten() {
		im_return.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(Pay_succ.this, MainActivity.class));
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
				MainActivity.handler.sendEmptyMessage(0x001);
				finish();
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
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

		setviewhw_lin(im_suc, w, (int) (w * 200 / 375.0), 0,
				(int) (w * 80 / 375.0), 0, 0);

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

			startActivity(new Intent(Pay_succ.this, MainActivity.class));
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
	private void volley_getpaysuc(final String order_sn) {
		String url = "https://app.juandie.com/api_mobile/msg.php?act=pay_success&order_sn="
				+ order_sn;
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {

			@Override
			public void onResponse(String result) {
				try {
					JSONObject response = new JSONObject(result);
					if (response.getString("status").equals("1")) {
						JSONObject jso = response.getJSONObject("data");
						String yhq = jso.getString("bouns");
						Toast.makeText(Pay_succ.this, "支付成功，获得一张优惠券," + yhq,
								Toast.LENGTH_SHORT).show();
						te_ti2.setText("特赠予优惠券：" + yhq + "，感谢惠顾！");

					} else {
						String jsb = response.getString("msg");
						Toast.makeText(Pay_succ.this, jsb, Toast.LENGTH_SHORT)
								.show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
				volley_getpaysuc(order_sn);
			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}
}
