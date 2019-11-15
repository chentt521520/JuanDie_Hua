package com.example.juandie_hua.tuishong;

import java.io.File;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.jpush.android.api.JPushInterface;

import com.example.juandie_hua.R;
import com.example.juandie_hua.mainactivity.Fengmian;
import com.example.juandie_hua.ui.good.GoodDetailsAty;
import com.example.juandie_hua.ui.good.GoodListAty;
import com.example.juandie_hua.mainactivity.other_web;
import com.meiqia.meiqiasdk.util.MQIntentBuilder;

/**
 * 自定义接收器
 * 
 * 如果不定义这个 Receiver，则： 1) 默认用户会打开主界面 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "JIGUANG-Example";

	private String[] url_web;// 轮播图要跳转的网页
	private String[] url_webtit;// 轮播图要跳转的网页的标题
	private String[] goodsid_bin;// 轮播图要跳转的网页的标题

	@Override
	public void onReceive(Context context, Intent intent) {
		try {
			Bundle bundle = intent.getExtras();
			Logger.d(TAG, "[MyReceiver] onReceive - " + intent.getAction()
					+ ", extras: " + printBundle(bundle));

			if (JPushInterface.ACTION_REGISTRATION_ID
					.equals(intent.getAction())) {
				String regId = bundle
						.getString(JPushInterface.EXTRA_REGISTRATION_ID);
				Fengmian.regid = regId;
				Logger.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
				// send the Registration Id to your server...

			} else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent
					.getAction())) {
				Logger.d(
						TAG,
						"[MyReceiver] 接收到推送下来的自定义消息: "
								+ bundle.getString(JPushInterface.EXTRA_MESSAGE));
				processCustomMessage(context, bundle);

			} else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED
					.equals(intent.getAction())) {
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知");
				int notifactionId = bundle
						.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
				Logger.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);

			} else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent
					.getAction())) {
				Logger.d(TAG, "[MyReceiver] 用户点击打开了通知");

				JSONObject jso1 = new JSONObject(
						bundle.getString(JPushInterface.EXTRA_EXTRA));
				JSONObject jso = jso1.getJSONObject("click");

				url_web = new String[1];
				url_webtit = new String[1];
				goodsid_bin = new String[1];
				if (jso.getString("type").equals("view")) {
					url_webtit[0] = jso.getString("title");
					url_web[0] = jso.getString("url");
					goodsid_bin[0] = "0";
				} else if (jso.getString("type").equals("category")) {
					goodsid_bin[0] = "1";
					url_webtit[0] = jso.getString("cid") + ","
							+ jso.getString("filter_attr");

				} else if (jso.getString("type").equals("customer_service")) {
					goodsid_bin[0] = "c";
				} else {
					url_webtit[0] = "0";
					url_web[0] = "0";
					goodsid_bin[0] = jso.getString("goods_id");
				}

				if (goodsid_bin[0].equals("0")) {

					Intent i = new Intent();
					i.putExtra("titl", url_webtit[0]);
					i.putExtra("url", url_web[0]);
					i.setClass(context, other_web.class);

					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);

				} else if (goodsid_bin[0].equals("1")) {

					String[] ax = url_webtit[0].split(",");
					Intent i = new Intent();
					bundle = new Bundle();
					bundle.putString("cid", ax[0]);
					bundle.putString("keywords", "");
					bundle.putString("filter_attr", ax[1]);
					bundle.putString("order", "sort_order");
					bundle.putString("by", "desc");
					String[] dics = ax[1].split("\\.");
					String positionget = "";
					for (int j = 0; j < dics.length; j++) {
						String pos1 = "无";
						if (!dics[j].equals("0")) {
							pos1 = "00" + dics[j];
						}
						positionget += pos1 + ".";
					}
					String pos1 = positionget.substring(0,
							positionget.length() - 1);
					positionget = pos1;
					bundle.putString("positionget", positionget);

					i.putExtras(bundle);
					i.setClass(context, GoodListAty.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);

				} else if (goodsid_bin[0].equals("c")) {
					Intent intent1 = new MQIntentBuilder(context)
							.setCustomizedId(Fengmian.regid)
							.setPreSendImageMessage(new File("预发送图片的路径"))
							.build();
					context.startActivity(intent1);

				} else {
					Intent i = new Intent();
					i.putExtra("goods_id", goodsid_bin[0]);
					i.setClass(context, GoodDetailsAty.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
							| Intent.FLAG_ACTIVITY_CLEAR_TOP);
					context.startActivity(i);
					((Activity) context).overridePendingTransition(
							R.anim.push_left_in, R.anim.push_left_out);
				}

				// // 打开自定义的Activity
				// Intent i = new Intent(context, Tui_getact.class);
				// i.putExtras(bundle);
				// i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
				// | Intent.FLAG_ACTIVITY_CLEAR_TOP);
				// context.startActivity(i);

			} else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent
					.getAction())) {
				Logger.d(
						TAG,
						"[MyReceiver] 用户收到到RICH PUSH CALLBACK: "
								+ bundle.getString(JPushInterface.EXTRA_EXTRA));
				// 在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity，
				// 打开一个网页等..

			} else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent
					.getAction())) {
				boolean connected = intent.getBooleanExtra(
						JPushInterface.EXTRA_CONNECTION_CHANGE, false);
				Logger.w(TAG, "[MyReceiver]" + intent.getAction()
						+ " connected state change to " + connected);
			} else {
				Logger.d(TAG,
						"[MyReceiver] Unhandled intent - " + intent.getAction());
			}
		} catch (Exception e) {

		}

	}

	// 打印所有的 intent extra 数据
	private static String printBundle(Bundle bundle) {
		StringBuilder sb = new StringBuilder();
		for (String key : bundle.keySet()) {
			if (key.equals(JPushInterface.EXTRA_NOTIFICATION_ID)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getInt(key));
			} else if (key.equals(JPushInterface.EXTRA_CONNECTION_CHANGE)) {
				sb.append("\nkey:" + key + ", value:" + bundle.getBoolean(key));
			} else if (key.equals(JPushInterface.EXTRA_EXTRA)) {
				if (TextUtils.isEmpty(bundle
						.getString(JPushInterface.EXTRA_EXTRA))) {
					Logger.i(TAG, "This message has no Extra data");
					continue;
				}

				try {
					JSONObject json = new JSONObject(
							bundle.getString(JPushInterface.EXTRA_EXTRA));
					Iterator<String> it = json.keys();

					while (it.hasNext()) {
						String myKey = it.next();
						sb.append("\nkey:" + key + ", value: [" + myKey + " - "
								+ json.optString(myKey) + "]");
					}
				} catch (JSONException e) {
					Logger.e(TAG, "Get message extra JSON error!");
				}

			} else {
				sb.append("\nkey:" + key + ", value:" + bundle.getString(key));
			}
		}
		return sb.toString();
	}

	// send msg to haha
	private void processCustomMessage(Context context, Bundle bundle) {
		// if (haha.isForeground) {
		// String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
		// String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
		// Intent msgIntent = new Intent(haha.MESSAGE_RECEIVED_ACTION);
		// msgIntent.putExtra(haha.KEY_MESSAGE, message);
		// if (!ExampleUtil.isEmpty(extras)) {
		// try {
		// JSONObject extraJson = new JSONObject(extras);
		// if (extraJson.length() > 0) {
		// msgIntent.putExtra(haha.KEY_EXTRAS, extras);
		// }
		// } catch (JSONException e) {
		//
		// }
		//
		// }
		// LocalBroadcastManager.getInstance(context).sendBroadcast(msgIntent);
		// }
	}
}
