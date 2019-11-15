package com.example.juandie_hua.percenter.myorder;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback.CancelledException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.example.juandie_hua.R;
import com.example.juandie_hua.alipay.sdk.pay.PayResult;
import com.example.juandie_hua.mainactivity.Xutils_Get_Post;
import com.example.juandie_hua.mainactivity.goods.MyListView;
import com.example.juandie_hua.mainactivity.other_web2;
import com.example.juandie_hua.mycar.Pay_fail1;
import com.example.juandie_hua.mycar.Pay_succ1;
import com.example.juandie_hua.percenter.myscanddingdan.orderdtals_web;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class myorder_adapter extends BaseAdapter {
	Context context;
	List<myorder_adaData> list;
	PopupWindow window;

	public myorder_adapter(Context context, List<myorder_adaData> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list == null ? 0 : list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final addview add;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.myorder_adapter, null);
			add = new addview();
			setview(add, convertView);

			setviewhw(add);

			convertView.setTag(add);
		} else
			add = (addview) convertView.getTag();
		final myorder_adaData data = list.get(position);

		myorder_adaptertwo ada = new myorder_adaptertwo(context, list.get(
				position).getList());
		add.listview.setAdapter(ada);

		add.te_bianhao.setText(data.getBhao());

		String type = data.getType();// 1待付款2待收货3待评价

		if (type.equals("1")) {
			add.te_it1.setVisibility(View.VISIBLE);
			add.te_it2.setText("取消订单");
			add.te_it2
					.setBackgroundResource(R.drawable.roundbackground_orderno);
			add.te_it2.setTextColor(Color.parseColor("#666666"));
			add.te_ztai1.setText("待付款");
		} else if (type.equals("2")) {
			add.te_it1.setVisibility(View.GONE);
			add.te_it2.setText("确认收货");
			add.te_it2.setTextColor(Color.parseColor("#da0000"));
			add.te_it2.setBackgroundResource(R.drawable.roundbackground_order);
			add.te_ztai1.setText("待收货");
		} else if (type.equals("3")) {
			add.te_it1.setVisibility(View.GONE);
			add.te_it2.setText("评价");
			add.te_it2.setTextColor(Color.parseColor("#da0000"));
			add.te_it2.setBackgroundResource(R.drawable.roundbackground_order);
			add.te_ztai1.setText("待评价");
		}

		add.te_it1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				fukuan(data.getorder_id(), add.te_it1);
			}
		});
		add.te_it2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (add.te_it2.getText().toString().equals("取消订单")) {
					quit_dialog(position, data.getorder_id());
				} else if (add.te_it2.getText().toString().equals("确认收货")) {
					queren_(data.getorder_id(), data.getPosition());
				} else if (add.te_it2.getText().toString().equals("评价")) {
					Intent i = new Intent();
					i.putExtra("id", data.getOrder_id());
					i.putExtra("id1", data.getBhao());
					i.putExtra("type", data.getPosition());
					i.setClass(context, myOrder_pinjia.class);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				}

			}
		});
		add.te_it3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (data.getType().equals("1")) {
					Intent i = new Intent();
					i.putExtra("id", data.getorder_id());
					i.setClass(context, myorder_detials.class);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				} else {
					Intent i = new Intent();
					i.putExtra("id", data.getBhao());
					i.setClass(context, orderdtals_web.class);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				}
			}
		});
		return convertView;
	}

	public class addview {
		LinearLayout lin_head, lin_wei;
		TextView te_ztai, te_ztai1, te_bianhao;
		TextView te_it1, te_it2, te_it3;
		MyListView listview;
	}

	private void setviewhw_lin(View v, int width, int height, int left,
			int top, int right, int bottom) {
		LayoutParams lp = new LayoutParams(width, height);
		lp.setMargins(left, top, right, bottom);
		v.setLayoutParams(lp);
	}

	private void setviewhw(addview add) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w = dm.widthPixels;

		setviewhw_lin(add.lin_head, w, (int) (w * 46 / 375.0), 0, 0, 0, 0);
		add.te_ztai.setPadding((int) (w * 14 / 375.0), 0,
				(int) (w * 2 / 375.0), 0);
		add.te_bianhao.setPadding(0, 0, (int) (w * 14 / 375.0), 0);

		setviewhw_lin(add.lin_wei, w, (int) (w * 27 / 375.0), 0, 0, 0,
				(int) (w * 20 / 375.0));
		setviewhw_lin(add.te_it1, (int) (w * 86 / 375.0),
				(int) (w * 27 / 375.0), (int) (w * 75 / 375.0), 0,
				(int) (w * 14 / 375.0), 0);
		setviewhw_lin(add.te_it2, (int) (w * 86 / 375.0),
				(int) (w * 27 / 375.0), 0, 0, (int) (w * 14 / 375.0), 0);
		setviewhw_lin(add.te_it3, (int) (w * 86 / 375.0),
				(int) (w * 27 / 375.0), 0, 0, (int) (w * 14 / 375.0), 0);
	}

	private void setview(addview add, View convertView) {
		add.lin_head = (LinearLayout) convertView
				.findViewById(R.id.myorderitem_linhed);
		add.lin_wei = (LinearLayout) convertView
				.findViewById(R.id.myorderitem_linte);

		add.te_ztai = (TextView) convertView
				.findViewById(R.id.myorderitem_teztai);
		add.te_ztai1 = (TextView) convertView
				.findViewById(R.id.myorderitem_teztai1);
		add.te_bianhao = (TextView) convertView
				.findViewById(R.id.myorderitem_teddhao);

		add.listview = (MyListView) convertView.findViewById(R.id.order_two);

		add.te_it1 = (TextView) convertView.findViewById(R.id.myorderitem_te1);
		add.te_it2 = (TextView) convertView.findViewById(R.id.myorderitem_te2);
		add.te_it3 = (TextView) convertView.findViewById(R.id.myorderitem_te3);
	}

	public void quit_dialog(final int position, final String order_id) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int height = dm.heightPixels;
		View view = LayoutInflater.from(context).inflate(R.layout.quit_comm1,
				null);

		TextView tetitl = (TextView) view.findViewById(R.id.comment_quitte);
		TextView textv_ok = (TextView) view.findViewById(R.id.gotocom_quitok);
		TextView textv_no = (TextView) view.findViewById(R.id.gotocom_quitno);
		LinearLayout lin = (LinearLayout) view
				.findViewById(R.id.comment_quitteq);

		tetitl.setText("您确定取消该订单？");
		textv_ok.setText("确定");
		textv_ok.setTextColor(Color.parseColor("#da0000"));

		setviewhw_lin(tetitl, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 120 / 750), 0, 0, 0, 0);
		setviewhw_lin(lin, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 80 / 750), 0, 0, 0, 0);

		tetitl.setPadding((int) (w_screen * 20 / 750.0), 0,
				(int) (w_screen * 20 / 750.0), 0);

		// 设置弹出框的宽高
		window = new PopupWindow(view, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 200 / 750.0));
		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.alpha = 0.8f;
		((Activity) context).getWindow().setAttributes(lp);
		// 设置背景
		// window.setBackgroundDrawable(context.getResources().getDrawable(
		// R.drawable.baisebj));
		window.setClippingEnabled(false);
		// 设置透明度
		// window.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window.setOutsideTouchable(true);

		// 设置焦点
		window.setFocusable(true);
		// 可以被触摸
		window.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
		if (android.os.Build.VERSION.SDK_INT >= 24) {
			int[] a = new int[2];
			tetitl.getLocationInWindow(a);
			window.showAtLocation(((Activity) context).getWindow()
					.getDecorView(), Gravity.NO_GRAVITY,
					(int) (w_screen * 175 / 750),
					(height - (int) (w_screen * 175 / 750)) / 2);
			window.update();
		} else {
			window.showAtLocation(tetitl, Gravity.CENTER, 0, 0);
			window.update();
		}
		window.update();
		window.setOnDismissListener(new OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = ((Activity) context)
						.getWindow().getAttributes();
				lp.alpha = 1f;
				((Activity) context).getWindow().setAttributes(lp);
			}
		});
		textv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xutils_getorderall(position, order_id);
				window.dismiss();
			}
		});
		textv_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
			}
		});
	}

	/**
	 * 确认收到货
	 * 
	 * @param position
	 */
	public void queren_(final String order_id, final String pos) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int height = dm.heightPixels;
		View view = LayoutInflater.from(context).inflate(R.layout.quit_comm1,
				null);

		TextView tetitl = (TextView) view.findViewById(R.id.comment_quitte);
		TextView textv_ok = (TextView) view.findViewById(R.id.gotocom_quitok);
		TextView textv_no = (TextView) view.findViewById(R.id.gotocom_quitno);
		LinearLayout lin = (LinearLayout) view
				.findViewById(R.id.comment_quitteq);

		tetitl.setText("您确定已经收到货了吗？");
		textv_ok.setText("确定");
		textv_no.setText("取消");
		textv_ok.setTextColor(Color.parseColor("#da0000"));

		setviewhw_lin(tetitl, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 120 / 750), 0, 0, 0, 0);
		setviewhw_lin(lin, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 80 / 750), 0, 0, 0, 0);

		tetitl.setPadding((int) (w_screen * 20 / 750.0), 0,
				(int) (w_screen * 20 / 750.0), 0);

		// 设置弹出框的宽高
		window = new PopupWindow(view, (int) (w_screen * 400 / 750.0),
				(int) (w_screen * 200 / 750.0));
		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.alpha = 0.8f;
		((Activity) context).getWindow().setAttributes(lp);
		// 设置背景
		// window.setBackgroundDrawable(context.getResources().getDrawable(
		// R.drawable.baisebj));
		window.setClippingEnabled(false);
		// 设置透明度
		// window.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window.setOutsideTouchable(true);

		// 设置焦点
		window.setFocusable(true);
		// 可以被触摸
		window.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
		if (android.os.Build.VERSION.SDK_INT >= 24) {
			int[] a = new int[2];
			tetitl.getLocationInWindow(a);
			window.showAtLocation(((Activity) context).getWindow()
					.getDecorView(), Gravity.NO_GRAVITY,
					(int) (w_screen * 175 / 750),
					(height - (int) (w_screen * 200 / 750)) / 2);
			window.update();
		} else {
			window.showAtLocation(tetitl, Gravity.CENTER, 0, 0);
			window.update();
		}
		window.update();
		window.setOnDismissListener(new OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				WindowManager.LayoutParams lp = ((Activity) context)
						.getWindow().getAttributes();
				lp.alpha = 1f;
				((Activity) context).getWindow().setAttributes(lp);
			}
		});
		textv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				xutils_qrshouhuo(order_id, pos);
				window.dismiss();
			}
		});
		textv_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
			}
		});
	}

	int zfb = 0;// 0代表支付宝，1代表微信

	/**
	 * 选择支付宝和微信支付
	 * 
	 * @param orderid
	 */
	public void fukuan(final String orderid, View v) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int w_screen = dm.widthPixels;
		int height = dm.heightPixels;
		View view = LayoutInflater.from(context).inflate(R.layout.fukuan, null);

		TextView textv_ok = (TextView) view.findViewById(R.id.fk_teok);
		TextView textv_no = (TextView) view.findViewById(R.id.fk_teqx);
		final LinearLayout re_zfb = (LinearLayout) view
				.findViewById(R.id.fk_rezfb);
		final LinearLayout re_wx = (LinearLayout) view
				.findViewById(R.id.fk_rewx);

		final LinearLayout re_jd = (LinearLayout) view
				.findViewById(R.id.fk_rejd);

		final ImageView im_zfbq = (ImageView) view
				.findViewById(R.id.fk_imzfbxz);
		final ImageView im_wxq = (ImageView) view.findViewById(R.id.fk_imwxxzq);
		final ImageView im_jdq = (ImageView) view.findViewById(R.id.fk_imjdxzq);

		re_zfb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				im_zfbq.setVisibility(View.VISIBLE);
				im_wxq.setVisibility(View.GONE);
				im_jdq.setVisibility(View.GONE);
				zfb = 0;
			}
		});
		re_wx.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				im_zfbq.setVisibility(View.GONE);
				im_jdq.setVisibility(View.GONE);
				im_wxq.setVisibility(View.VISIBLE);
				zfb = 1;
			}
		});
		re_jd.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				im_zfbq.setVisibility(View.GONE);
				im_wxq.setVisibility(View.GONE);
				im_jdq.setVisibility(View.VISIBLE);
				zfb = 2;
			}
		});

		// 设置弹出框的宽高
		window = new PopupWindow(view, w_screen, (int) (w_screen * 400 / 750));
		ColorDrawable cd = new ColorDrawable(0x000000);// 设置背景变暗
		window.setBackgroundDrawable(cd);
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.alpha = 0.8f;
		((Activity) context).getWindow().setAttributes(lp);
		// 设置背景
		// window.setBackgroundDrawable(context.getResources().getDrawable(
		// R.drawable.baisebj));
		window.setClippingEnabled(false);
		// 设置透明度
		// window.getBackground().setAlpha(200);
		// 设置动画,从底部出来
		window.setAnimationStyle(android.R.style.Animation_Dialog);
		// 点击空白区域消失
		window.setOutsideTouchable(true);

		// 设置焦点
		window.setFocusable(true);
		// 可以被触摸
		window.setTouchable(true);
		// 设置软键盘
		// window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		// 显示的位置,从底部显示
		// 设置popwindow显示位置
		if (android.os.Build.VERSION.SDK_INT >= 24) {
			int[] a = new int[2];
			textv_no.getLocationInWindow(a);
			window.showAtLocation(((Activity) context).getWindow()
					.getDecorView(), Gravity.NO_GRAVITY, 0, height
					- (int) (w_screen * 400 / 750));
			window.update();
		} else {
			window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
			window.update();
		}
		window.update();
		window.setOnDismissListener(new OnDismissListener() {// pop消失

			@Override
			public void onDismiss() {
				zfb = 0;
				WindowManager.LayoutParams lp = ((Activity) context)
						.getWindow().getAttributes();
				lp.alpha = 1f;
				((Activity) context).getWindow().setAttributes(lp);
			}
		});
		textv_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (zfb == 0) {
					xutils_zf(orderid, "14");
				} else if (zfb == 2) {
					xutils_zf(orderid, "15");
				} else {
					api = WXAPIFactory.createWXAPI(context,
							"wx0a9fcf0dc4ee88b0");
					api.registerApp("wx0a9fcf0dc4ee88b0");
					boolean isPaySupported = api.getWXAppSupportAPI() >= com.tencent.mm.opensdk.constants.Build.PAY_SUPPORTED_SDK_INT;
					if (!isPaySupported) {
						Toast.makeText(context, "亲，请下载微信app才可以微信支付",
								Toast.LENGTH_SHORT).show();
					} else {
						xutils_zf(orderid, "13");
					}
				}
				window.dismiss();
			}
		});
		textv_no.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				window.dismiss();
			}
		});
	}

	String sign = "";

	/**
	 * 取消订单
	 */
	private void xutils_getorderall(final int positin, String orderid) {
		String url = "https://app.juandie.com/api_mobile/user.php?act=ajax_order_del&order_id="
				+ orderid;

		Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						list.remove(positin);
						notifyDataSetChanged();
						Toast.makeText(context,
								jso1.getString("msg").toString(),
								Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(context,
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}

	String order_sn = "", weixinxx = "";
	String oder_info;

	private void xutils_zf(final String order_id, final String payment_id) {
		String url = "https://app.juandie.com/api_mobile/user.php?act=ajax_order_pay&order_id="
				+ order_id + "&payment_id=" + payment_id;
		final SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
		final SharedPreferences.Editor editor = preferences.edit();

		Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						JSONObject jso = jso1.getJSONObject("data");
						if (payment_id.equals("14")) {
							JSONObject data_obj = jso.getJSONObject("alipay");
							order_sn = data_obj.getString("out_trade_no");
							oder_info = data_obj.getString("order_string");
							zfb();
						} else if (payment_id.equals("13")) {
							JSONObject data_obj = jso.getJSONObject("wechat");
							weixinxx = data_obj.toString();
							String partnerid = data_obj.getString("mch_id");
							String prepayid = data_obj.getString("prepay_id");
							String noncestr = data_obj.getString("nonce_str");
							String timeStamp = data_obj.getString("timestamp");
							String sign = data_obj.getString("sign");
							order_sn = data_obj.getString("out_trade_no");
							editor.putString("orderid", order_sn);
							editor.commit();
							weixin(partnerid, prepayid, noncestr, timeStamp,
									sign);
						} else if (payment_id.equals("15")) {
							JSONObject data_ojd = jso.getJSONObject("jdpay");
							String url_jd = data_ojd.getString("pay_url");

							Intent i = new Intent();
							i.setClass(context, other_web2.class);
							i.putExtra("titl", "支付商品");
							i.putExtra("url", url_jd);
							context.startActivity(i);
						}

					} else {
						Toast.makeText(context,
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
				// TODO Auto-generated method stub

			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});
	}

	private final int SDK_PAY_FLAG = 1;

	/**
	 * 支付宝支付
	 */
	public void zfb() {

		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				PayTask alipay = new PayTask((Activity) context);
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
					((Activity) context).finish();
					// 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
					Intent i = new Intent();
					i.putExtra("type", "1");// 1成功，2失败
					i.putExtra("orderid", order_sn);
					i.putExtra("zffs", "支付宝");
					i.setClass(context, Pay_succ1.class);
					context.startActivity(i);

				} else {
					((Activity) context).finish();

					// 该笔订单真实的支付结果，需要依赖服务端的异步通知。
					Toast.makeText(context, "支付失败", Toast.LENGTH_SHORT).show();
					// 跳转支付失败页面，目前写跳转成功页面
					Intent i = new Intent();
					i.putExtra("type", "2");// 1成功，2失败
					i.putExtra("orderid", order_sn);
					i.setClass(context, Pay_fail1.class);
					context.startActivity(i);
				}
				break;
			}
			default:
				break;
			}
		};
	};

	private IWXAPI api;

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

	private void xutils_qrshouhuo(final String order_id, final String pos) {
		String url = "https://app.juandie.com/api_mobile/user.php?act=ajax_order_receive&order_id="
				+ order_id;

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);

		Xutils_Get_Post.getInstance().get(url, new Xutils_Get_Post.XCallBack() {

			@Override
			public void onResponse(String result) {
				JSONObject jso1;
				try {
					jso1 = new JSONObject(result);
					if (jso1.getString("status").equals("1")) {
						Toast.makeText(context, "确认收货成功", Toast.LENGTH_LONG)
								.show();
						if (pos.equals("0")) {
							myOrderAll.handler.sendEmptyMessage(0x01);
						} else if (pos.equals("2")) {
							myOrderDaishuo.handler.sendEmptyMessage(0x01);
						}
					} else {
						Toast.makeText(context,
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
				xutils_qrshouhuo(order_id, pos);
			}

			@Override
			public void onCancel(CancelledException cex) {
				// TODO Auto-generated method stub

			}
		});

	}
}
