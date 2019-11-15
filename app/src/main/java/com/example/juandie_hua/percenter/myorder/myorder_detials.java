package com.example.juandie_hua.percenter.myorder;

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
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.example.juandie_hua.mainactivity.goods.MyListView;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.umeng.analytics.MobclickAgent;

public class myorder_detials extends Activity implements re_jk {
	@ViewInject(R.id.orderdeta_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.orderdeta_imreturn)
	ImageView im_return;

	@ViewInject(R.id.orderdeta_linddhao)
	LinearLayout lin_ddhao;
	@ViewInject(R.id.orderdeta_teddhao)
	TextView te_ddhao;
	@ViewInject(R.id.orderdeta_teddhao1)
	TextView te_ddhao1;

	@ViewInject(R.id.orderdeta_linxiad)
	LinearLayout lin_xiad;
	@ViewInject(R.id.orderdeta_texiad)
	TextView te_xiad;
	@ViewInject(R.id.orderdeta_texiad1)
	TextView te_xiad1;

	@ViewInject(R.id.orderdeta_linddztai)
	LinearLayout lin_ddztai;
	@ViewInject(R.id.orderdeta_teddztai)
	TextView te_ddzt;
	@ViewInject(R.id.orderdeta_teddztai1)
	TextView te_ddzt1;

	@ViewInject(R.id.orderdeta_rename)
	RelativeLayout re_shr;
	@ViewInject(R.id.orderdeta_tename)
	TextView te_shr;
	@ViewInject(R.id.orderdeta_tename1)
	TextView te_shr1;

	@ViewInject(R.id.orderdeta_rephone)
	RelativeLayout re_pho;
	@ViewInject(R.id.orderdeta_tephone)
	TextView te_pho;
	@ViewInject(R.id.orderdeta_tephone1)
	TextView te_pho1;

	@ViewInject(R.id.orderdeta_rediz)
	RelativeLayout re_dzi;
	@ViewInject(R.id.orderdeta_tediz)
	TextView te_dzi;
	@ViewInject(R.id.orderdeta_tediz1)
	TextView te_dzi1;

	@ViewInject(R.id.orderdeta_retime)
	RelativeLayout re_time;
	@ViewInject(R.id.orderdeta_tetime)
	TextView te_time;
	@ViewInject(R.id.orderdeta_tetime1)
	TextView te_time1;

	@ViewInject(R.id.orderdeta_renamedin)
	RelativeLayout re_shrdin;
	@ViewInject(R.id.orderdeta_tenamedin)
	TextView te_shrdin;
	@ViewInject(R.id.orderdeta_tenamedin1)
	TextView te_shrdin1;

	@ViewInject(R.id.orderdeta_rephdin)
	RelativeLayout re_phodin;
	@ViewInject(R.id.orderdeta_tephdin)
	TextView te_phodin;
	@ViewInject(R.id.orderdeta_tephdin1)
	TextView te_phodin1;

	@ViewInject(R.id.orderdeta_reheka)
	RelativeLayout re_heka;
	@ViewInject(R.id.orderdeta_teheka)
	TextView te_heka;
	@ViewInject(R.id.orderdeta_teheka1)
	TextView te_heka1;

	@ViewInject(R.id.orderdeta_rebzhu)
	RelativeLayout re_beizhu;
	@ViewInject(R.id.orderdeta_tebzhu)
	TextView te_beizhu;
	@ViewInject(R.id.orderdeta_tebzhu1)
	TextView te_beizhu1;

	@ViewInject(R.id.orderdeta_reprice)
	RelativeLayout re_price;
	@ViewInject(R.id.orderdeta_teprice)
	TextView te_price;
	@ViewInject(R.id.orderdeta_teprice1)
	TextView te_price1;

	@ViewInject(R.id.orderdeta_repricez)
	RelativeLayout re_pricez;
	@ViewInject(R.id.orderdeta_tepricez)
	TextView te_pricez;
	@ViewInject(R.id.orderdeta_tepricez1)
	TextView te_price1z;

	@ViewInject(R.id.myorderitem_listview)
	MyListView listview;
	SharedPreferences preferences;
	List<myorder_adaDatatwo> list;
	myorder_adaptertwo ada;
	internet_landing inLanding;
	String order_id = "";

	@ViewInject(R.id.orderdeta_ds)
	RelativeLayout re_ds;
	@ViewInject(R.id.orderdeta_teds)
	TextView te_ds;
	@ViewInject(R.id.orderdeta_teds1)
	TextView te_ds1;

	@ViewInject(R.id.orderdeta_psf)
	RelativeLayout re_ps;
	@ViewInject(R.id.orderdeta_tepsf)
	TextView te_psds;
	@ViewInject(R.id.orderdeta_tepsf1)
	TextView te_ps1;

	@ViewInject(R.id.orderdeta_yhq)
	RelativeLayout re_yhq;
	@ViewInject(R.id.orderdeta_teyhq)
	TextView te_yhq;
	@ViewInject(R.id.orderdeta_teyhq1)
	TextView te_yhq1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myorder_detials);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);
		preferences = PreferenceManager
				.getDefaultSharedPreferences(myorder_detials.this);

		setviewdata();
		setviewhw();
		setviewlisten();
	}

	private void setviewdata() {
		list = new ArrayList<>();
		ada = new myorder_adaptertwo(myorder_detials.this, list);
		listview.setAdapter(ada);

		Intent i = getIntent();
		order_id = i.getStringExtra("id");
		inLanding = new internet_landing(myorder_detials.this);
		inLanding.setonc(this);
		if (inLanding.if_inter()) {
			xutils_getorderdetal(order_id);
		}
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
	}

	private void setviewhw() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0), 0);

		setviewhw_lin(lin_ddhao, w, (int) (w * 28 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		setviewhw_lin(lin_xiad, w, (int) (w * 28 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		setviewhw_lin(lin_ddztai, w, (int) (w * 28 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

		setviewhw_lin(re_shr, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		setviewhw_lin(re_pho, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

		setviewhw_lin(re_dzi, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

		setviewhw_lin(re_time, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

		setviewhw_lin(re_shrdin, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		setviewhw_lin(re_phodin, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

		re_heka.setPadding((int) (w * 12 / 375.0), (int) (w * 6 / 375.0), 0, 0);
		re_heka.setMinimumHeight((int) (w * 30 / 375.0));
		re_beizhu.setMinimumHeight((int) (w * 30 / 375.0));
		re_beizhu.setPadding((int) (w * 12 / 375.0), (int) (w * 6 / 375.0), 0,
				0);

		setviewhw_lin(re_price, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		setviewhw_lin(re_pricez, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);
		te_dzi1.setPadding(0, 0, (int) (w * 12 / 375.0), 0);

		setviewhw_lin(re_ds, w, (int) (w * 50 / 375.0), (int) (w * 12 / 375.0),
				0, 0, 0);
		setviewhw_lin(re_ps, w, (int) (w * 50 / 375.0), (int) (w * 12 / 375.0),
				0, 0, 0);
		setviewhw_lin(re_yhq, w, (int) (w * 50 / 375.0),
				(int) (w * 12 / 375.0), 0, 0, 0);

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

	private void xutils_getorderdetal(String order_id) {
		inLanding.showlanding();
		String url = "https://app.juandie.com/api_mobile/user.php?act=order_detail&order_id="
				+ order_id
				;
		
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {
			
			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						JSONObject data = jso1.getJSONObject("data");
						JSONArray jsa = data.getJSONArray("goods_list");
						for (int i = 0; i < jsa.length(); i++) {
							JSONObject dat = jsa.getJSONObject(i);
							list.add(new myorder_adaDatatwo(dat
									.getString("goods_id"), dat
									.getString("goods_number"), dat
									.getString("goods_thumb"), dat
									.getString("goods_name"), dat
									.getString("goods_price")));
						}

						JSONObject dataall = data.getJSONObject("order");
						te_ddzt1.setText(dataall.getString("order_status_text"));
						te_ddhao1.setText(dataall.getString("order_sn"));
						te_xiad1.setText(dataall.getString("formated_add_time"));
						te_shr1.setText(dataall.getString("consignee"));
						te_pho1.setText(dataall.getString("tel"));
						te_dzi1.setText(dataall.getString("address"));
						te_time1.setText(dataall.getString("best_time"));
						te_shrdin1.setText(dataall.getString("buyer"));
						te_phodin1.setText(dataall.getString("buyertel"));
						te_heka1.setText(dataall.getString("card_message"));
						te_beizhu1.setText(dataall.getString("postscript"));
						te_price1.setText("￥"
								+ dataall.getString("goods_amount"));
						te_price1z.setText("￥"
								+ dataall.getString("order_amount"));
						te_ds1.setText("￥"
								+ dataall
										.getString("distinct_time_service_fee_fee"));
						te_ps1.setText("￥" + dataall.getString("shipping_fee"));
						te_yhq1.setText("-￥" + dataall.getString("bonus"));

					} else {
						Toast.makeText(myorder_detials.this,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
					}
					ada.notifyDataSetChanged();
					inLanding.dismisslanding();
				} catch (JSONException e) {
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
		} );
	}

	public void onResume() {
		super.onResume();
		MobclickAgent.onResume(this);
	}

	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}

	@Override
	public void re_requestjk(View v) {
		if (new internet_if().isNetworkConnected(myorder_detials.this)) {
			inLanding.dismissinter();
			xutils_getorderdetal(order_id);
		} else {
			Toast.makeText(myorder_detials.this, "网络连接失败,请设置网络",
					Toast.LENGTH_SHORT).show();
		}
	}
}
