package com.example.juandie_hua.mycar.orderpay;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;
import org.xutils.common.Callback.CancelledException;
import org.xutils.view.annotation.ViewInject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
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

import com.alipay.sdk.app.PayTask;
import com.example.juandie_hua.R;
import com.example.juandie_hua.alipay.sdk.pay.PayResult;
import com.example.juandie_hua.mainactivity.Landing;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post.XCallBack;
import com.example.juandie_hua.mainactivity.other_web2;
import com.example.juandie_hua.mycar.Pay_fail1;
import com.example.juandie_hua.mycar.Pay_succ1;
import com.example.juandie_hua.utils.StatusBarUtils;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.umeng.analytics.MobclickAgent;

public class pay_choose extends Activity {
	@ViewInject(R.id.paychoose_retop)
	RelativeLayout re_top;
	@ViewInject(R.id.paychoose_imreturn)
	ImageView im_return;

	@ViewInject(R.id.paychoose_payje)
	TextView te_payje;
	@ViewInject(R.id.paychoose_payje1)
	TextView te_payje1;

	@ViewInject(R.id.paychoose_tezffs)
	TextView te_zffs;

	@ViewInject(R.id.paychoose_rezfb)
	LinearLayout lin_zfbnew;
	@ViewInject(R.id.paychoose_imzfb)
	ImageView im_zfbnew;
	@ViewInject(R.id.paychoose_imzfbxz)
	ImageView im_zfbgonew;

	@ViewInject(R.id.paychoose_rewx)
	LinearLayout lin_wxnew;
	@ViewInject(R.id.paychoose_imwx)
	ImageView im_wxnew;
	@ViewInject(R.id.paychoose_imwxxzq)
	ImageView im_wxgonew;

	@ViewInject(R.id.paychoose_rejd)
	LinearLayout lin_jdnew;
	@ViewInject(R.id.paychoose_imjd)
	ImageView im_jdnew;
	@ViewInject(R.id.paychoose_imjdxzq)
	ImageView im_jdgonew;

	@ViewInject(R.id.paychoose_tepay)
	TextView te_pay;

	SharedPreferences preferences;
	SharedPreferences.Editor editor;

	String zffs_str = "";// 支付方式 例如：“13” 或者 “14” 13微信支付 14支付宝支付 15金东支付
	private IWXAPI api;

	Landing landing;
	String order_sn = "", weixinxx = "";
	String[] data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pay_choose);
		StatusBarUtils.with(this).setBarColor(R.color.white_fff);

		x.view().inject(this);

		api = WXAPIFactory.createWXAPI(pay_choose.this, "wx0a9fcf0dc4ee88b0");
		api.registerApp("wx0a9fcf0dc4ee88b0");
		preferences = PreferenceManager
				.getDefaultSharedPreferences(pay_choose.this);
		landing = new Landing(pay_choose.this, R.style.CustomDialog);

		setviewhw();
		setviewdata();
		setviewlisten();

	}

	private void setviewdata() {
		Intent i = getIntent();
		data = i.getStringArrayExtra("data");
		te_payje1.setText("￥" + data[24]);
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

		lin_zfbnew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setzffs(1);
			}
		});
		lin_wxnew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setzffs(2);
			}
		});
		lin_jdnew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				setzffs(3);
			}
		});

		te_pay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (zffs_str.equals("14")) {
					// 支付宝支付
					httpPost_makeorder(data[0], data[1], data[8], data[9],
							data[10], data[3], data[5], data[6], data[16],
							data[17], data[18], data[19], data[20], data[21],
							"14", data[13], data[14], data[22], data[7],
							data[15], 1);
				} else if (zffs_str.equals("15")) {
					httpPost_makeorder(data[0], data[1], data[8], data[9],
							data[10], data[3], data[5], data[6], data[16],
							data[17], data[18], data[19], data[20], data[21],
							"15", data[13], data[14], data[22], data[7],
							data[15], 3);

				} else {
					// 微信支付
					boolean isPaySupported = api.getWXAppSupportAPI() >= com.tencent.mm.opensdk.constants.Build.PAY_SUPPORTED_SDK_INT;
					if (!isPaySupported) {
						Toast.makeText(pay_choose.this, "亲，请下载微信app才可以微信支付",
								Toast.LENGTH_SHORT).show();
					} else {
						httpPost_makeorder(data[0], data[1], data[8], data[9],
								data[10], data[3], data[5], data[6], data[16],
								data[17], data[18], data[19], data[20],
								data[21], "13", data[13], data[14], data[22],
								data[7], data[15], 2);
					}

				}

			}
		});
	}

	private void setviewhw() {
		setzffs(1);
		DisplayMetrics dm = getResources().getDisplayMetrics();
		int w = dm.widthPixels;
		setviewhw_lin(re_top, w, (int) (w * 55 / 450.0), 0, 0, 0, 0);
		re_top.setBackgroundResource(R.drawable.biankuangxnew);

		setviewhw_re(im_return, (int) (w * 49 / 450.0), (int) (w * 25 / 450.0),
				0, (int) (w * 15 / 450.0), 0, 0);
		im_return.setPadding((int) (w * 12 / 450.0), 0, (int) (w * 12 / 450.0),
				0);

		setviewhw_lin(te_payje, w, (int) (w * 60 / 750.0), 0, 0, 0, 0);
		setviewhw_lin(te_payje1, w, (int) (w * 80 / 750.0), 0, 0, 0, 0);
		setviewhw_lin(te_zffs, w, (int) (w * 70 / 750.0), 0,
				(int) (w * 12 / 375.0), 0, 0);
		te_zffs.setPadding((int) (w * 12 / 375.0), 0, 0, 0);

		setviewhw_lin(lin_zfbnew, w - (int) (w * 28 / 375.0),
				(int) (w * 60 / 375.0), (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);
		setviewhw_lin(im_zfbgonew, (int) (w * 20 / 375.0),
				(int) (w * 30 / 375.0), (int) (w * 5 / 375.0),
				(int) (w * 15 / 375.0), (int) (w * 10 / 375.0),
				(int) (w * 15 / 375.0));
		setviewhw_lin(im_zfbnew, (int) (w * 40 / 375.0),
				(int) (w * 40 / 375.0), 0, (int) (w * 10 / 375.0),
				(int) (w * 5 / 375.0), 0);

		setviewhw_lin(lin_wxnew, w - (int) (w * 28 / 375.0),
				(int) (w * 60 / 375.0), (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);
		setviewhw_lin(im_wxgonew, (int) (w * 20 / 375.0),
				(int) (w * 30 / 375.0), (int) (w * 5 / 375.0),
				(int) (w * 15 / 375.0), (int) (w * 10 / 375.0), 0);
		setviewhw_lin(im_wxnew, (int) (w * 40 / 375.0), (int) (w * 40 / 375.0),
				0, (int) (w * 10 / 375.0), (int) (w * 5 / 375.0), 0);

		setviewhw_lin(lin_jdnew, w - (int) (w * 28 / 375.0),
				(int) (w * 60 / 375.0), (int) (w * 14 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);
		setviewhw_lin(im_jdgonew, (int) (w * 20 / 375.0),
				(int) (w * 30 / 375.0), (int) (w * 5 / 375.0),
				(int) (w * 15 / 375.0), (int) (w * 10 / 375.0), 0);
		setviewhw_lin(im_jdnew, (int) (w * 40 / 375.0), (int) (w * 40 / 375.0),
				0, (int) (w * 10 / 375.0), (int) (w * 5 / 375.0), 0);

		setviewhw_lin(te_pay, w * 2 / 3, (int) (w * 44 / 375.0), w * 1 / 6,
				(int) (w * 20 / 375.0), w * 1 / 6, 0);

	}

	/**
	 * 支付方式选择
	 * 
	 * @param i
	 */
	private void setzffs(int i) {
		switch (i) {
		case 1:
			zffs_str = "14";
			im_zfbgonew.setVisibility(View.VISIBLE);
			im_wxgonew.setVisibility(View.GONE);
			im_jdgonew.setVisibility(View.GONE);
			break;
		case 2:
			zffs_str = "13";
			im_zfbgonew.setVisibility(View.GONE);
			im_wxgonew.setVisibility(View.VISIBLE);
			im_jdgonew.setVisibility(View.GONE);
			break;

		case 3:
			zffs_str = "15";
			im_zfbgonew.setVisibility(View.GONE);
			im_wxgonew.setVisibility(View.GONE);
			im_jdgonew.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}

	/**
	 * 确认订单
	 * 
	 * @param consignee
	 * @param tel
	 * @param province
	 * @param city
	 * @param district
	 * @param address1
	 * @param buyer
	 * @param buyertel
	 * @param best_time
	 * @param bast_time_info
	 * @param time_hour
	 * @param time_minutes
	 * @param shippingfee
	 * @param dsfwf
	 * @param payment
	 * @param card_message
	 * @param postscript
	 * @param times_range
	 * @param bonus_sn
	 * @param type
	 */
	String sign = "";

	public void httpPost_makeorder(String consignee, String tel,
			String province, String city, String district, String address1,
			String buyer, String buyertel, String best_time,
			String bast_time_info, String time_hour, String time_minutes,
			String shippingfee, String dsfwf, String payment,
			String card_message, String postscript, String times_range,
			String bonus_sn, String sale_emp_id, final int type) {
		landing.show();
		HashMap<String, String> maps = new HashMap<>();
		maps.put("act", "done");
		maps.put("consignee", consignee);
		maps.put("province", province);
		maps.put("tel", tel);
		maps.put("country", "1");
		maps.put("city", city);
		maps.put("district", district);
		maps.put("address", address1);
		maps.put("consignee_post", "1");
		maps.put("buyer", buyer);
		maps.put("buyertel", buyertel);
		maps.put("best_time", best_time);
		maps.put("bast_time_info", bast_time_info);
		maps.put("time_hour", time_hour);
		maps.put("time_minutes", time_minutes);
		maps.put("shippingfee", shippingfee);
		maps.put("dsfwf", dsfwf);
		maps.put("payment", payment);
		maps.put("card_message", card_message);
		maps.put("postscript", postscript);
		maps.put("times_range", times_range);
		maps.put("bonus_sn", bonus_sn);
		maps.put("sale_emp_id", sale_emp_id);

		String url = "https://app.juandie.com/api_mobile/flow.php";

		Xutils_Get_Post.getInstance().post(url, maps, new XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject object;
				landing.dismiss();
				try {
					object = new JSONObject(result);
					int status = object.getInt("status");
					if (status == 1) {
						JSONObject jso = object.getJSONObject("data");
						
						String jrts = jso.getString("festival_delivery_tip");
						editor = preferences.edit();
						if (TextUtils.isEmpty(jrts)) {
							editor.putString("jrts", "0");
						} else
							editor.putString("jrts", jrts);
						editor.apply();
						
						if (type == 1) {
							JSONObject data_obj = jso.getJSONObject("alipay");
							order_sn = data_obj.getString("out_trade_no");
							oder_info = data_obj.getString("order_string");
							zfb();
						} else if (type == 2) {
							JSONObject data_obj = jso.getJSONObject("wechat");
							weixinxx = data_obj.toString();
							String partnerid = data_obj.getString("mch_id");
							String prepayid = data_obj.getString("prepay_id");
							String noncestr = data_obj.getString("nonce_str");
							String timeStamp = data_obj.getString("timestamp");
							String sign = data_obj.getString("sign");
							order_sn = data_obj.getString("out_trade_no");
							editor = preferences.edit();
							editor.putString("orderid", order_sn);
							editor.commit();
							weixin(partnerid, prepayid, noncestr, timeStamp,
									sign);
						} else {
							JSONObject data_ojd = jso.getJSONObject("jdpay");
							String url_jd = data_ojd.getString("pay_url");

							Intent i = new Intent();
							i.setClass(pay_choose.this, other_web2.class);
							i.putExtra("titl", "支付商品");
							i.putExtra("url", url_jd);
							startActivity(i);

						}

					} else {
						if (status == 0) {
							Toast.makeText(pay_choose.this,
									object.getString("msg"), Toast.LENGTH_SHORT)
									.show();
						}
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onFail(String result) {
				landing.dismiss();
			}

			@Override
			public void onCancel(CancelledException cex) {
				landing.dismiss();
			}
		});

	}

	String oder_info;
	private final int SDK_PAY_FLAG = 1;

	/**
	 * 支付宝支付
	 */
	public void zfb() {

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask(pay_choose.this);
				Map<String, String> result = alipay.payV2(oder_info, true);
				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		@SuppressWarnings("unused")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case SDK_PAY_FLAG: {
				@SuppressWarnings("unchecked")
				PayResult payResult = new PayResult(
						(Map<String, String>) msg.obj);
				/**
				 * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
				 */
				String resultInfo = payResult.getResult();// 同步返回需要验证的信息
				String resultStatus = payResult.getResultStatus();
				// 判断resultStatus 为9000则代表支付成功
				if (TextUtils.equals(resultStatus, "9000")) {
					finish();
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。

					Intent i = new Intent();
					i.putExtra("type", "1");// 1成功，2失败
					i.putExtra("orderid", order_sn);
					i.putExtra("zffs", "支付宝");
					i.setClass(pay_choose.this, Pay_succ1.class);
					startActivity(i);

				} else {
					finish();
					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(pay_choose.this, "支付失败", Toast.LENGTH_SHORT)
							.show();
					// 跳转支付失败页面，目前写跳转成功页面
					Intent i = new Intent();
					i.putExtra("type", "2");// 1成功，2失败
					// i.putExtra("orderid", order_sn);
					i.setClass(pay_choose.this, Pay_fail1.class);
					startActivity(i);
				}
				break;
			}
			default:
				break;
			}
		};
	};

	/**
	 * 微信支付
	 */

	public void weixin(String partnerid, String prepayid, String noncestr,
			String timeStamp, String sign1) {
		PayReq req = new PayReq();
		req.appId = "wx0a9fcf0dc4ee88b0";
		req.partnerId = partnerid;
		req.prepayId = prepayid;
		req.nonceStr = noncestr;
		req.timeStamp = timeStamp;
		req.packageValue = "Sign=WXPay";
		req.sign = sign1;
		req.extData = "app data"; // optional
		api.sendReq(req);
	}

	private void setviewhw_re(View v, int width, int height, int left, int top,
			int right, int bottom) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(width,
				height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
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
