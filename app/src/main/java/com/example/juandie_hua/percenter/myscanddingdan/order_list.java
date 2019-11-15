package com.example.juandie_hua.percenter.myscanddingdan;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
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
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.percenter.myorder.myorder_adaDatatwo;
import com.umeng.analytics.MobclickAgent;

public class order_list extends Activity implements re_jk {
	@ViewInject(R.id.ddjg_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.ddjg_imreturn)
	ImageView im_return;

	@ViewInject(R.id.ddjg_listview)
	ListView listView;
	dingdan_adapter ada;
	List<dingdan_adaData> list;

	@ViewInject(R.id.ddjg_nodd)
	// 没有订单数据的时候
	LinearLayout layout_nogoods;

	@ViewInject(R.id.nogoods_tego)
	TextView te_noddgo;
	@ViewInject(R.id.nogoods_tecon)
	TextView te_noddms;

	internet_landing inLanding;
	String sjh = "", ddh = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.order_list);
		// if (Build.VERSION.SDK_INT >= 21) {
		// Window window = getWindow();
		// // 取消设置透明状态栏,使 ContentView 内容不再沉浸到状态栏下
		// window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		// // 需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
		// window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
		// // 设置状态栏颜色
		// window.setStatusBarColor(Color.parseColor("#f2f3f5"));
		// }
		inLanding = new internet_landing(this);
		inLanding.setonc(this);
		x.view().inject(this);
		setviewhw();
		setviewlisten();
		setviewdata();
	}

	private void setviewdata() {
		list = new ArrayList<dingdan_adaData>();
		ada = new dingdan_adapter(order_list.this, list);
		listView.setAdapter(ada);
		Intent i = getIntent();

		if (inLanding.if_inter()) {
			xutils_getorder(i.getStringExtra("sjh"), i.getStringExtra("ddh"));
		}

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
		te_noddgo.setVisibility(View.GONE);
		te_noddms.setText("抱歉，根据您提交的查询条件没有查询到订单！");

		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);

		setviewhw_lin(layout_nogoods, w, (int) (w * 280 / 375.0), 0, 0, 0, 0);
		layout_nogoods.setPadding(0, (int) (w * 100 / 375.0), 0, 0);
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

	String sign = "";

	private void xutils_getorder(final String buyer_tel, final String order_sn) {
		inLanding.showlanding();
		String url = "https://app.juandie.com/api_mobile/index.php?act=order_search&buyer_tel="
				+ buyer_tel
				+ "&order_sn="
				+ order_sn
				;
		
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {
			
			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						JSONObject data = jso1.getJSONObject("data");
						JSONArray data_1 = data.getJSONArray("order_list");
						for (int i = 0; i < data_1.length(); i++) {
							JSONObject data_ = data_1.getJSONObject(i);
							JSONArray data_2 = data_.getJSONArray("goods_list");
							List<myorder_adaDatatwo> list2 = new ArrayList<>();
							String order_id = "";
							for (int j = 0; j < data_2.length(); j++) {
								JSONObject data_j = data_2.getJSONObject(j);
								list2.add(new myorder_adaDatatwo(data_j
										.getString("goods_id"), data_j
										.getString("goods_number"), data_j
										.getString("goods_thumb"), data_j
										.getString("goods_name"), "无"));
								order_id = data_j.getString("order_id");
							}

							list.add(new dingdan_adaData(data_
									.getString("order_sn"), data_
									.getString("shipping_status"), data_
									.getString("order_amount"), list2, order_id));
						}
						inLanding.dismisslanding();
						ada.notifyDataSetChanged();
						if (list.size() >= 1) {
							layout_nogoods.setVisibility(View.GONE);
						}

					} else {
						Toast.makeText(order_list.this,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			@Override
			public void onFail(String result) {
				inLanding.dismisslanding();
			}
			
			@Override
			public void onCancel(CancelledException cex) {
				inLanding.dismisslanding();
			}
		});
	}

	@Override
	public void re_requestjk(View v) {
		if (new internet_if().isNetworkConnected(this)) {
			inLanding.dismissinter();
			xutils_getorder(sjh, ddh);
		} else {
			Toast.makeText(this, "网络连接失败,请设置网络", Toast.LENGTH_SHORT).show();
		}
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
