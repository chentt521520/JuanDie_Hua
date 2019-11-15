package com.example.juandie_hua.ui.login;

import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.juandie_hua.R;
import com.example.juandie_hua.app.BaseActivity;
import com.example.juandie_hua.app.Constant;
import com.example.juandie_hua.ui.tab.Home;
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.MainActivity;
import com.example.juandie_hua.ui.tab.ShopCart;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.percenter.TimerTextView;
import com.example.juandie_hua.utils.SharedPreferenceUtils;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.example.juandie_hua.utils.StrUtils;
import com.example.juandie_hua.utils.ViewUtils;

public class Loginpho_bd extends BaseActivity {
	@ViewInject(R.id.loginp_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.loginp_imreturn)
	ImageView im_turn;
	@ViewInject(R.id.loginp_teclose)
	TextView te_close;

	@ViewInject(R.id.loginp_teph)
	TextView te_titlog;

	@ViewInject(R.id.loginp_lteph)
	LinearLayout lin_pho;
	@ViewInject(R.id.loginp_edph)
	EditText edit_pho;

	@ViewInject(R.id.loginp_linyzm)
	LinearLayout lin_yzm;
	@ViewInject(R.id.loginp_edyzm)
	EditText edit_yzm;
	@ViewInject(R.id.loginp_tegetyzm)
	TimerTextView te_yzm;

	@ViewInject(R.id.loginp_teok)
	TextView te_ok;

	@ViewInject(R.id.loginp_hen1)
	View v_hen1;
	@ViewInject(R.id.loginp_hen2)
	View v_hen2;

	String type = "wx";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.loginpho1);
		StatusBarUtils.with(this).fullScreen();

		x.view().inject(this);
		type = getIntent().getStringExtra("type");
		setviewhw();
		setviewlisten();

	}

	private void setviewlisten() {
		im_turn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Me.handler.sendEmptyMessage(0x003);
				if (getIntent().getStringExtra("type").equals("wx")
						|| getIntent().getStringExtra("type").equals("dsf")) {
					Loginphowx.myHandler.sendEmptyMessage(0x001);
				}
				TimerTextView.isc = false;
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
			}
		});
		te_yzm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (StrUtils.isMatchered(edit_pho.getText().toString())) {
					TimerTextView.isc = true;
					edit_pho.setEnabled(false);

					if (type.equals("dsf")) {
						httpPost_getcodedsf(edit_pho.getText().toString());
					} else
						httpPost_getcode(edit_pho.getText().toString());
				} else
					Toast.makeText(Loginpho_bd.this, "请输入正确的手机号",
							Toast.LENGTH_SHORT).show();
			}
		});
		te_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (StrUtils.isMatchered(edit_pho.getText().toString())) {
					TimerTextView.isc = true;
					if (edit_yzm.getText().toString().length() == 6) {
						if (type.equals("dsf")) {
							httpPost_longin2dsf(edit_pho.getText().toString(),
									edit_yzm.getText().toString());
						} else
						httpPost_longin2(edit_pho.getText().toString(),
								edit_yzm.getText().toString());

					} else
						Toast.makeText(Loginpho_bd.this, "请输入6位验证码",
								Toast.LENGTH_SHORT).show();
				} else
					Toast.makeText(Loginpho_bd.this, "请输入正确的手机号",
							Toast.LENGTH_SHORT).show();

			}
		});
		te_close.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// xutils_getlogin();
				Me.handler.sendEmptyMessage(0x003);
				if (getIntent().getStringExtra("type").equals("wx")) {
					Loginphowx.myHandler.sendEmptyMessage(0x001);
				}
				TimerTextView.isc = false;
				finish();
				overridePendingTransition(R.anim.push_right_out,
						R.anim.push_right_in);
			}
		});
	}

	String seeid = "";
	String sign = "";

	/**
	 * 发送短信验证码
	 * 
	 * @param phonenumber
	 */
	public void httpPost_getcode(String phonenumber) {

		String url = "https://app.juandie.com/api_mobile/user.php";
		HashMap<String, String> maps = new HashMap<>();
		maps.put("act", "app_send_sms_wechat_binding");
		maps.put("mobile_phone", phonenumber);

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject object;
				try {
					object = new JSONObject(result);
					int status = object.getInt("status");
					if (status == 1) {

						String uid = object.getString("uid");
						SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.uid,uid,"S");
						Fengmian.uid = uid;
						seeid = object.getString("PHPSESSID");

						Toast.makeText(Loginpho_bd.this, "验证码已发送到手机，请注意查收",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(Loginpho_bd.this,
								object.getString("msg"), Toast.LENGTH_SHORT)
								.show();
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}
	
	public void httpPost_getcodedsf(String phonenumber) {

		String url = "https://app.juandie.com/api_mobile/user.php";
		HashMap<String, String> maps = new HashMap<>();
		maps.put("act", "app_send_sms_third_binding");
		maps.put("mobile_phone", phonenumber);

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject object;
				try {
					object = new JSONObject(result);
					int status = object.getInt("status");
					if (status == 1) {

						String uid = object.getString("uid");
						SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.uid,uid,"S");
						Fengmian.uid = uid;
						seeid = object.getString("PHPSESSID");

						Toast.makeText(Loginpho_bd.this, "验证码已发送到手机，请注意查收",
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(Loginpho_bd.this,
								object.getString("msg"), Toast.LENGTH_SHORT)
								.show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}


	/**
	 * 短信验证码登录
	 * 
	 * @param phonenumber
	 */
	public void httpPost_longin2(String phonenumber, String captcha) {

		String url = "https://app.juandie.com/api_mobile/user.php";
		HashMap<String, String> maps = new HashMap<>();
		maps.put("act", "app_ajax_wechat_binding");
		maps.put("mobile_phone", phonenumber);
		maps.put("captcha", captcha);

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject object;
				try {
					object = new JSONObject(result);
					int status = object.getInt("status");
					if (status == 1) {
						String uid = object.getString("uid");
						String PHPSESSID = object.getString("PHPSESSID");
						SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.uid,uid,"S");
						SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.cook,PHPSESSID,"S");
						Fengmian.uid = uid;
//						if (preferences.getString("sye_dl", "0").equals("1")) {
//							editor = preferences.edit();
//							editor.putString("sye_dl", "0");
//							editor.commit();
//							startActivity(new Intent(Loginpho_bd.this,
//									haha.class));
//							Home.myHandler.sendEmptyMessage(0x004);
//						} else {
//							haha.handler.sendEmptyMessage(0x004);
//							Me.handler.sendEmptyMessage(0x003);
//							overridePendingTransition(R.anim.push_right_out,
//									R.anim.push_right_in);
//							finish();
//						}
						Home.myHandler.sendEmptyMessage(0x004);
						ShopCart.myHandler.sendEmptyMessage(0x001);
						Me.handler.sendEmptyMessage(0x003);
						Loginphowx.myHandler.sendEmptyMessage(0x001);
						overridePendingTransition(R.anim.push_right_out, R.anim.push_right_in);
						finish();
					} else {
						String jsb = object.getString("msg");
						Toast.makeText(Loginpho_bd.this, jsb,
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}
	public void httpPost_longin2dsf(String phonenumber, String captcha) {

		String url = "https://app.juandie.com/api_mobile/user.php";
		HashMap<String, String> maps = new HashMap<>();
		maps.put("act", "third_binding");
		maps.put("mobile_phone", phonenumber);
		maps.put("captcha", captcha);

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject object;
				try {
					object = new JSONObject(result);
					int status = object.getInt("status");
					if (status == 1) {
						String uid = object.getString("uid");
						SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.uid,uid,"S");
						Fengmian.uid = uid;

						String sye_dl= (String) SharedPreferenceUtils.getPreference(Loginpho_bd.this, Constant.sye_dl,"S");

						if (TextUtils.equals(sye_dl,"1")) {
							SharedPreferenceUtils.setPreference(Loginpho_bd.this, Constant.sye_dl,"0","S");
							startActivity(new Intent(Loginpho_bd.this, MainActivity.class));
							Home.myHandler.sendEmptyMessage(0x004);
						} else {
							if (getIntent().getStringExtra("type").equals("wx")) {
								Loginphowx.myHandler.sendEmptyMessage(0x001);
							}
							MainActivity.handler.sendEmptyMessage(0x004);
							Me.handler.sendEmptyMessage(0x003);
							overridePendingTransition(R.anim.push_right_out,
									R.anim.push_right_in);
							finish();
						}
					} else {
						String jsb = object.getString("msg");
						Toast.makeText(Loginpho_bd.this, jsb,
								Toast.LENGTH_SHORT).show();
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}


	
	private void setviewhw() {

		te_titlog.setText("绑定手机号");
		edit_pho.setHint("请输入要绑定的手机号");
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;

		ViewUtils.setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0,
				(int) (w * 90 / 750.0), 0, 0);
		ViewUtils.setviewhw_re(im_turn, (int) (w * 25 / 450.0), (int) (w * 25 / 450.0),
				(int) (w * 12 / 375.0), (int) (w * 15 / 450.0), 0, 0);

		ViewUtils.setviewhw_lin(te_titlog, w, LayoutParams.WRAP_CONTENT, 0,
				(int) (w * 180 / 750.0), 0, 0);

		ViewUtils.setviewhw_lin(lin_pho, w - (int) (w * 55 / 750.0),
				(int) (w * 55 / 750.0), (int) (w * 27 / 750.0),
				(int) (w * 90 / 750.0), 0, 0);
		ViewUtils.setviewhw_lin(lin_yzm, w - (int) (w * 55 / 750.0),
				(int) (w * 95 / 750.0), (int) (w * 27 / 750.0),
				(int) (w * 45 / 750.0), 0, 0);
		ViewUtils.setviewhw_lin(te_ok, w - (int) (w * 55 / 750.0),
				(int) (w * 82 / 750.0), (int) (w * 27 / 750.0),
				(int) (w * 60 / 750.0), 0, (int) (w * 60 / 750.0));
		ViewUtils.setviewhw_lin(v_hen1, w - (int) (w * 55 / 750.0),
				(int) (w * 2 / 750.0), (int) (w * 27 / 750.0),
				0, 0, 0);
		ViewUtils.setviewhw_lin(v_hen2, w - (int) (w * 55 / 750.0),
				(int) (w * 2 / 750.0), (int) (w * 27 / 750.0),
				0, 0,0);

	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			TimerTextView.isc = false;
			Me.handler.sendEmptyMessage(0x003);
			if (getIntent().getStringExtra("type").equals("wx")
					|| getIntent().getStringExtra("type").equals("dsf")) {
				Loginphowx.myHandler.sendEmptyMessage(0x001);
			}
			finish();
			overridePendingTransition(R.anim.push_right_out,
					R.anim.push_right_in);
			return false;
		}
		return false;
	}

}
