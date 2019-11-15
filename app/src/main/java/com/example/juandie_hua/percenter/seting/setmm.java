package com.example.juandie_hua.percenter.seting;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
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
import com.example.juandie_hua.ui.tab.Me;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.internet_if;
import com.example.juandie_hua.mainactivity.internet_landing;
import com.example.juandie_hua.mainactivity.internet_landing.re_jk;
import com.umeng.analytics.MobclickAgent;

public class setmm extends Activity implements re_jk {
	@ViewInject(R.id.setmm_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.setmm_imreturn)
	ImageView im_return;

	@ViewInject(R.id.setmm_rejiumm)
	RelativeLayout re_jiumm;
	@ViewInject(R.id.setmm_imjiumm)
	ImageView im_jiumm;
	@ViewInject(R.id.setmm_edjiumm)
	EditText ed_jiumm;

	@ViewInject(R.id.setmm_rexinmm)
	RelativeLayout re_xinmm;
	@ViewInject(R.id.setmm_imxinmm)
	ImageView im_xinmm;
	@ViewInject(R.id.setmm_edxinmm)
	EditText ed_xinmm;

	@ViewInject(R.id.setmm_rexinmmre)
	RelativeLayout re_xinmmre;
	@ViewInject(R.id.setmm_imxinmmre)
	ImageView im_xinmmre;
	@ViewInject(R.id.setmm_edxinmmre)
	EditText ed_xinmmre;

	@ViewInject(R.id.setmm_tetjiao)
	TextView te_ok;

	internet_landing inLanding;
	String mm1 = "", mm2 = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setmm);

		inLanding = new internet_landing(this);
		inLanding.setonc(this);
		x.view().inject(this);
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
		te_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (!(ed_jiumm.getText().toString().length() >= 6)) {
					Toast.makeText(setmm.this, "密码长度大于6位", Toast.LENGTH_SHORT)
							.show();
				} else {
					if (TextUtils.isEmpty(ed_jiumm.getText().toString())
							|| TextUtils.isEmpty(ed_xinmm.getText().toString())
							|| TextUtils.isEmpty(ed_xinmmre.getText()
									.toString())) {
						Toast.makeText(setmm.this, "请填写完整信息",
								Toast.LENGTH_SHORT).show();
					} else {

						if (!ed_xinmm.getText().toString()
								.equals(ed_xinmmre.getText().toString())) {
							Toast.makeText(setmm.this, "密码前后不一致",
									Toast.LENGTH_SHORT).show();
						} else {
							if (ed_jiumm.getText().toString()
									.equals(ed_xinmmre.getText().toString())) {
								Toast.makeText(setmm.this, "新密码与旧密码一样",
										Toast.LENGTH_SHORT).show();
							} else {
								if (ed_xinmm.getText().toString().length() <= 5) {
									Toast.makeText(setmm.this, "密码长度最小为6位",
											Toast.LENGTH_SHORT).show();
								} else {
									mm1 = ed_xinmm.getText().toString();
									mm2 = ed_jiumm.getText().toString();
									// 提交接口
									if (inLanding.if_inter()) {

										xutils_quit(ed_xinmm.getText()
												.toString(), ed_jiumm.getText()
												.toString());
									}
								}
							}
						}
					}
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
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

		setviewhw_lin(re_jiumm, w, (int) (w * 48 / 375.0), 0, 0, 0, 0);
		setviewhw_re(im_jiumm, (int) (w * 22 / 375.0), (int) (w * 22 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 13 / 375.0),
				(int) (w * 12 / 375.0), (int) (w * 13 / 375.0));
		setviewhw_lin(re_xinmm, w, (int) (w * 48 / 375.0), 0, 0, 0, 0);
		setviewhw_re(im_xinmm, (int) (w * 22 / 375.0), (int) (w * 22 / 375.0),
				(int) (w * 10 / 375.0), (int) (w * 13 / 375.0),
				(int) (w * 12 / 375.0), (int) (w * 13 / 375.0));
		setviewhw_lin(re_xinmmre, w, (int) (w * 48 / 375.0), 0, 0, 0, 0);
		setviewhw_re(im_xinmmre, (int) (w * 22 / 375.0),
				(int) (w * 22 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 13 / 375.0), (int) (w * 12 / 375.0),
				(int) (w * 13 / 375.0));
		setviewhw_lin(te_ok, (int) (w * 345 / 375.0), (int) (w * 40 / 375.0),
				(int) (w * 14 / 375.0), (int) (w * 14 / 375.0), 0, 0);
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

	private void xutils_quit(final String password, final String old_password) {
		String url = "https://app.juandie.com/api_mobile/user.php?act=ajax_modify_password&password="
				+ password
				+ "&old_password="
				+ old_password
				;
		
		Xutils_Get_Post.getInstance().get(url, new XCallBack() {
			
			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {

						Toast.makeText(setmm.this,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();

						finish();
						overridePendingTransition(R.anim.push_right_out,
								R.anim.push_right_in);
						Me.handler.sendEmptyMessage(0x003);
					} else {
						Toast.makeText(setmm.this,
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
			xutils_quit(mm1, mm2);
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
